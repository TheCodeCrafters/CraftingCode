// Generated by jextract

package io.github.thecodecrafters.craftingcode.lua.foreign;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import jdk.incubator.foreign.*;
import static jdk.incubator.foreign.CLinker.*;
class constants$18 {

    static final FunctionDescriptor luaopen_string$FUNC = FunctionDescriptor.of(C_INT,
        C_POINTER
    );
    static final MethodHandle luaopen_string$MH = RuntimeHelper.downcallHandle(
        Lua.LIBRARIES, "luaopen_string",
        "(Ljdk/incubator/foreign/MemoryAddress;)I",
        constants$18.luaopen_string$FUNC, false
    );
    static final FunctionDescriptor luaopen_utf8$FUNC = FunctionDescriptor.of(C_INT,
        C_POINTER
    );
    static final MethodHandle luaopen_utf8$MH = RuntimeHelper.downcallHandle(
        Lua.LIBRARIES, "luaopen_utf8",
        "(Ljdk/incubator/foreign/MemoryAddress;)I",
        constants$18.luaopen_utf8$FUNC, false
    );
    static final FunctionDescriptor luaopen_bit32$FUNC = FunctionDescriptor.of(C_INT,
        C_POINTER
    );
    static final MethodHandle luaopen_bit32$MH = RuntimeHelper.downcallHandle(
        Lua.LIBRARIES, "luaopen_bit32",
        "(Ljdk/incubator/foreign/MemoryAddress;)I",
        constants$18.luaopen_bit32$FUNC, false
    );
    static final FunctionDescriptor luaopen_math$FUNC = FunctionDescriptor.of(C_INT,
        C_POINTER
    );
    static final MethodHandle luaopen_math$MH = RuntimeHelper.downcallHandle(
        Lua.LIBRARIES, "luaopen_math",
        "(Ljdk/incubator/foreign/MemoryAddress;)I",
        constants$18.luaopen_math$FUNC, false
    );
    static final FunctionDescriptor luaopen_debug$FUNC = FunctionDescriptor.of(C_INT,
        C_POINTER
    );
    static final MethodHandle luaopen_debug$MH = RuntimeHelper.downcallHandle(
        Lua.LIBRARIES, "luaopen_debug",
        "(Ljdk/incubator/foreign/MemoryAddress;)I",
        constants$18.luaopen_debug$FUNC, false
    );
    static final FunctionDescriptor luaopen_package$FUNC = FunctionDescriptor.of(C_INT,
        C_POINTER
    );
    static final MethodHandle luaopen_package$MH = RuntimeHelper.downcallHandle(
        Lua.LIBRARIES, "luaopen_package",
        "(Ljdk/incubator/foreign/MemoryAddress;)I",
        constants$18.luaopen_package$FUNC, false
    );
}


