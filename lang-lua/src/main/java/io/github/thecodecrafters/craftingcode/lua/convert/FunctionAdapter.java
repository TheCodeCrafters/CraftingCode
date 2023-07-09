package io.github.thecodecrafters.craftingcode.lua.convert;

import io.github.thecodecrafters.craftingcode.langapi.Value;
import io.github.thecodecrafters.craftingcode.lua.LuaContext;
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
            MethodHandle integerToLua = lookup.findStatic(LuaConverter.class, "integerToLua", MethodType.methodType(void.class, MemoryAddress.class, long.class));
            MethodHandle floatingPointToLua = lookup.findStatic(LuaConverter.class, "floatingPointToLua", MethodType.methodType(void.class, MemoryAddress.class, double.class));
            MethodHandle luaContextByState = lookup.findStatic(LuaContext.class, "getContextByState", MethodType.methodType(LuaContext.class, Addressable.class));
            CONVERTERS = Map.ofEntries(
                    Map.entry(String.class, new Converter(
                            lookup.findStatic(LuaConverter.class, "stringToLua", MethodType.methodType(int.class, MemoryAddress.class, String.class)),
                            lookup.findStatic(LuaConverter.class, "stringFromLua", MethodType.methodType(String.class, MemoryAddress.class, int.class))
                    )),
                    Map.entry(byte.class, new Converter(
                            integerToLua.asType(MethodType.methodType(int.class, MemoryAddress.class, byte.class)),
                            null
                    )),
                    Map.entry(short.class, new Converter(
                            integerToLua.asType(MethodType.methodType(int.class, MemoryAddress.class, short.class)),
                            null
                    )),
                    Map.entry(int.class, new Converter(
                            integerToLua.asType(MethodType.methodType(int.class, MemoryAddress.class, int.class)),
                            null
                    )),
                    Map.entry(long.class, new Converter(
                            integerToLua,
                            null
                    )),
                    Map.entry(float.class, new Converter(
                            floatingPointToLua.asType(MethodType.methodType(int.class, MemoryAddress.class, float.class)),
                            null
                    )),
                    Map.entry(double.class, new Converter(
                            floatingPointToLua,
                            null
                    )),
                    Map.entry(void.class, new Converter(
                            lookup.findStatic(FunctionAdapter.class, "voidToLua", MethodType.methodType(int.class, MemoryAddress.class)),
                            null
                    )),
                    Map.entry(Object.class, new Converter(
                            lookup.findStatic(LuaConverter.class, "objectToLua", MethodType.methodType(void.class, MemoryAddress.class, Object.class)),
                            null
                    )),
                    Map.entry(Value.class, new Converter(
                            null,
                            lookup.findStatic(LuaConverter.class, "valueFromLua", MethodType.methodType(Value.class, MemoryAddress.class, int.class))
                    )),
                    Map.entry(Throwable.class, new Converter(
                            MethodHandles.foldArguments(lookup.findVirtual(LuaContext.class, "wrapThrowable", MethodType.methodType(void.class, Addressable.class, Throwable.class)), luaContextByState)
                                    .asType(MethodType.methodType(void.class, MemoryAddress.class, Throwable.class)),
                            lookup.findStatic(LuaContext.class, "getWrappedObject", MethodType.methodType(Object.class, Addressable.class, int.class))
                                    .asType(MethodType.methodType(Object.class, MemoryAddress.class, int.class))
                    ))
            );
        } catch (NoSuchMethodException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public static @NotNull MemoryAddress adaptToLua(@NotNull MethodHandle javaHandle, @NotNull ResourceScope scope) {
        // TODO: validate argument count (lua_gettop), varargs
        // TODO: exception handling
        var type = javaHandle.type();
        var rtype = type.returnType();
        var rConverter = Objects.requireNonNull(CONVERTERS.get(rtype).toLua());
        var collectArguments = MethodHandles.collectArguments(rConverter, 1, javaHandle);
        var pCount = type.parameterCount();

        for (int i = 0, j = 0; i < pCount; i++, j++) {
            var ptype = type.parameterType(i);
            if (i == 0 && ptype == MemoryAddress.class) {
                j--;
                int[] reordered = IntStream.concat(IntStream.of(0), IntStream.rangeClosed(0, pCount - 1)).toArray();
                collectArguments = MethodHandles.permuteArguments(collectArguments, collectArguments.type().dropParameterTypes(0, 1), reordered);
                continue;
            }
            var pConverter = MethodHandles.insertArguments(Objects.requireNonNull(CONVERTERS.get(ptype).fromLua()), 1, j + 1);
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
}
