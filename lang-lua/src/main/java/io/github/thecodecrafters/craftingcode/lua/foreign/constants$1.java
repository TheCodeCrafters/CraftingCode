// Generated by jextract

package io.github.thecodecrafters.craftingcode.lua.foreign;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import jdk.incubator.foreign.*;
import static jdk.incubator.foreign.CLinker.*;
class constants$1 {

    static final FunctionDescriptor lua_Writer$FUNC = FunctionDescriptor.of(C_INT,
        C_POINTER,
        C_POINTER,
        C_LONG,
        C_POINTER
    );
    static final MethodHandle lua_Writer$MH = RuntimeHelper.downcallHandle(
        "(Ljdk/incubator/foreign/MemoryAddress;Ljdk/incubator/foreign/MemoryAddress;JLjdk/incubator/foreign/MemoryAddress;)I",
        constants$1.lua_Writer$FUNC, false
    );
    static final FunctionDescriptor lua_Alloc$FUNC = FunctionDescriptor.of(C_POINTER,
        C_POINTER,
        C_POINTER,
        C_LONG,
        C_LONG
    );
    static final MethodHandle lua_Alloc$MH = RuntimeHelper.downcallHandle(
        "(Ljdk/incubator/foreign/MemoryAddress;Ljdk/incubator/foreign/MemoryAddress;JJ)Ljdk/incubator/foreign/MemoryAddress;",
        constants$1.lua_Alloc$FUNC, false
    );
    static final FunctionDescriptor lua_newstate$FUNC = FunctionDescriptor.of(C_POINTER,
        C_POINTER,
        C_POINTER
    );
    static final MethodHandle lua_newstate$MH = RuntimeHelper.downcallHandle(
        Lua.LIBRARIES, "lua_newstate",
        "(Ljdk/incubator/foreign/MemoryAddress;Ljdk/incubator/foreign/MemoryAddress;)Ljdk/incubator/foreign/MemoryAddress;",
        constants$1.lua_newstate$FUNC, false
    );
    static final FunctionDescriptor lua_close$FUNC = FunctionDescriptor.ofVoid(
        C_POINTER
    );
    static final MethodHandle lua_close$MH = RuntimeHelper.downcallHandle(
        Lua.LIBRARIES, "lua_close",
        "(Ljdk/incubator/foreign/MemoryAddress;)V",
        constants$1.lua_close$FUNC, false
    );
}


