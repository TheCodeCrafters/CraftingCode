package io.github.thecodecrafters.craftingcode.lua.convert;

import io.github.thecodecrafters.craftingcode.langapi.Value;
import io.github.thecodecrafters.craftingcode.lua.foreign.Lua;
import jdk.incubator.foreign.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.Map;
import java.util.Objects;
import java.util.stream.IntStream;

public class FunctionAdapter {
    private record Converter(@Nullable MethodHandle toLua, @Nullable MethodHandle fromLua) {
    }

    private static final Map<Class<?>, Converter> CONVERTERS;

    static {
        final var lookup = MethodHandles.lookup();
        try {
            lookup.ensureInitialized(LuaConverter.class);
            MethodHandle integerToLua = lookup.findStatic(LuaConverter.class, "integerToLua", MethodType.methodType(int.class, MemoryAddress.class, long.class));
            MethodHandle floatingPointToLua = lookup.findStatic(LuaConverter.class, "floatingPointToLua", MethodType.methodType(int.class, MemoryAddress.class, double.class));
            CONVERTERS = Map.of(
                    String.class, new Converter(
                            lookup.findStatic(LuaConverter.class, "stringToLua", MethodType.methodType(int.class, MemoryAddress.class, String.class)),
                            lookup.findStatic(LuaConverter.class, "stringFromLua", MethodType.methodType(String.class, MemoryAddress.class, int.class))
                    ),
                    byte.class, new Converter(
                            integerToLua.asType(MethodType.methodType(int.class, MemoryAddress.class, byte.class)),
                            null
                    ),
                    short.class, new Converter(
                            integerToLua.asType(MethodType.methodType(int.class, MemoryAddress.class, short.class)),
                            null
                    ),
                    int.class, new Converter(
                            integerToLua.asType(MethodType.methodType(int.class, MemoryAddress.class, int.class)),
                            null
                    ),
                    long.class, new Converter(
                            integerToLua,
                            null
                    ),
                    float.class, new Converter(
                            floatingPointToLua.asType(MethodType.methodType(int.class, MemoryAddress.class, float.class)),
                            null
                    ),
                    double.class, new Converter(
                            floatingPointToLua,
                            null
                    ),
                    void.class, new Converter(
                            lookup.findStatic(FunctionAdapter.class, "voidToLua", MethodType.methodType(int.class, MemoryAddress.class)),
                            null
                    ),
                    Object.class, new Converter(
                            lookup.findStatic(LuaConverter.class, "objectToLua", MethodType.methodType(void.class, MemoryAddress.class, Object.class)),
                            null
                    ),
                    Value.class, new Converter(
                            null,
                            lookup.findStatic(LuaConverter.class, "valueFromLua", MethodType.methodType(Value.class, MemoryAddress.class, int.class))
                    )
            );
        } catch (NoSuchMethodException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public static @NotNull MemoryAddress adaptToLua(@NotNull MethodHandle javaHandle, @NotNull ResourceScope scope) {
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
        if (collectArguments.type().returnType() != int.class) {
            if (collectArguments.type().returnType() != void.class)
                throw new IllegalStateException("Return type of Java->Lua converter must be int or void");
            collectArguments = MethodHandles.filterReturnValue(collectArguments, MethodHandles.constant(int.class, 1));
        }
        return CLinker.getInstance().upcallStub(collectArguments, Lua.lua_CFunction, scope);
    }

    private static int voidToLua(@NotNull MemoryAddress ignoredL) {
        return 0;
    }

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException {
        var lookup = MethodHandles.lookup();
        var adapted = adaptToLua(lookup.findStatic(FunctionAdapter.class, "test", MethodType.methodType(void.class, String.class, String.class)), ResourceScope.newImplicitScope());
        System.out.println(adapted);
    }
}
