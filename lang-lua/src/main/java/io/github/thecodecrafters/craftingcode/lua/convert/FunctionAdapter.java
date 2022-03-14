package io.github.thecodecrafters.craftingcode.lua.convert;

import io.github.thecodecrafters.craftingcode.lua.foreign.Lua;
import jdk.incubator.foreign.*;
import org.jetbrains.annotations.Nullable;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Objects;
import java.util.stream.IntStream;

import static jdk.incubator.foreign.MemoryLayouts.ADDRESS;

public class FunctionAdapter {
    private record Converter(@Nullable MethodHandle toLua, @Nullable MethodHandle fromLua) {
    }

    private static final Map<Class<?>, Converter> CONVERTERS;

    static {
        final var lookup = MethodHandles.lookup();
        try {
            CONVERTERS = Map.of(
                    String.class, new Converter(
                            lookup.findStatic(FunctionAdapter.class, "stringToLua", MethodType.methodType(int.class, MemoryAddress.class, String.class)),
                            lookup.findStatic(FunctionAdapter.class, "stringFromLua", MethodType.methodType(String.class, MemoryAddress.class, int.class))
                    ),
                    void.class, new Converter(
                            lookup.findStatic(FunctionAdapter.class, "voidToLua", MethodType.methodType(int.class, MemoryAddress.class)),
                            null
                    )
            );
        } catch (NoSuchMethodException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public static MemoryAddress adaptToLua(MethodHandle javaHandle, ResourceScope scope) {
        // TODO: validate argument count (lua_gettop)
        var type = javaHandle.type();
        var rtype = type.returnType();
        var rConverter = Objects.requireNonNull(CONVERTERS.get(rtype).toLua());
        var collectArguments = MethodHandles.collectArguments(rConverter, 1, javaHandle);
        var pCount = type.parameterCount();

        for (int i = 0; i < pCount; i++) {
            var ptype = type.parameterType(i);
            var pConverter = MethodHandles.insertArguments(Objects.requireNonNull(CONVERTERS.get(ptype).fromLua()), 1, i + 1);
            int[] reordered = IntStream.rangeClosed(0, pCount - i).toArray();
            reordered[0] = 1;
            reordered[1] = 0;
            var permuted = MethodHandles.permuteArguments(collectArguments, collectArguments.type().dropParameterTypes(1, 2).insertParameterTypes(0, ptype), reordered);
            collectArguments = MethodHandles.foldArguments(permuted, pConverter);
        }
        return CLinker.getInstance().upcallStub(collectArguments, Lua.lua_CFunction, scope);
    }

    private static int stringToLua(MemoryAddress L, String s) {
        try (ResourceScope scope = ResourceScope.newConfinedScope()) {
            MemorySegment cs = CLinker.toCString(s, scope);
            Lua.lua_pushlstring(L, cs, cs.byteSize() - 1);
        }
        return 1;
    }

    private static String stringFromLua(MemoryAddress L, int idx) {
        try (ResourceScope scope = ResourceScope.newConfinedScope()) {
            MemorySegment lenSegment = MemorySegment.allocateNative(ADDRESS, scope);
            MemoryAddress address = Lua.lua_tolstring(L, idx, lenSegment);
            int len = (int) MemoryAccess.getAddress(lenSegment).toRawLongValue();

            byte[] bytes = new byte[len];
            MemorySegment.ofArray(bytes)
                    .copyFrom(address.asSegment(len, scope));
            return new String(bytes, StandardCharsets.UTF_8);
        }
    }

    private static int voidToLua(MemoryAddress L) {
        return 0;
    }

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException {
        var lookup = MethodHandles.lookup();
        var adapted = adaptToLua(lookup.findStatic(FunctionAdapter.class, "test", MethodType.methodType(void.class, String.class, String.class)), ResourceScope.newImplicitScope());
        System.out.println(adapted);
    }

    private static void test(String s, String s2) {
    }
}
