// Generated by jextract

package io.github.thecodecrafters.craftingcode.lua.foreign;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import jdk.incubator.foreign.*;
import static jdk.incubator.foreign.CLinker.*;
class constants$14 {

    static final FunctionDescriptor lua_concat$FUNC = FunctionDescriptor.ofVoid(
        C_POINTER,
        C_INT
    );
    static final MethodHandle lua_concat$MH = RuntimeHelper.downcallHandle(
        Lua.LIBRARIES, "lua_concat",
        "(Ljdk/incubator/foreign/MemoryAddress;I)V",
        constants$14.lua_concat$FUNC, false
    );
    static final FunctionDescriptor lua_len$FUNC = FunctionDescriptor.ofVoid(
        C_POINTER,
        C_INT
    );
    static final MethodHandle lua_len$MH = RuntimeHelper.downcallHandle(
        Lua.LIBRARIES, "lua_len",
        "(Ljdk/incubator/foreign/MemoryAddress;I)V",
        constants$14.lua_len$FUNC, false
    );
    static final FunctionDescriptor lua_stringtonumber$FUNC = FunctionDescriptor.of(C_LONG,
        C_POINTER,
        C_POINTER
    );
    static final MethodHandle lua_stringtonumber$MH = RuntimeHelper.downcallHandle(
        Lua.LIBRARIES, "lua_stringtonumber",
        "(Ljdk/incubator/foreign/MemoryAddress;Ljdk/incubator/foreign/MemoryAddress;)J",
        constants$14.lua_stringtonumber$FUNC, false
    );
    static final FunctionDescriptor lua_getallocf$FUNC = FunctionDescriptor.of(C_POINTER,
        C_POINTER,
        C_POINTER
    );
    static final MethodHandle lua_getallocf$MH = RuntimeHelper.downcallHandle(
        Lua.LIBRARIES, "lua_getallocf",
        "(Ljdk/incubator/foreign/MemoryAddress;Ljdk/incubator/foreign/MemoryAddress;)Ljdk/incubator/foreign/MemoryAddress;",
        constants$14.lua_getallocf$FUNC, false
    );
    static final FunctionDescriptor lua_setallocf$FUNC = FunctionDescriptor.ofVoid(
        C_POINTER,
        C_POINTER,
        C_POINTER
    );
    static final MethodHandle lua_setallocf$MH = RuntimeHelper.downcallHandle(
        Lua.LIBRARIES, "lua_setallocf",
        "(Ljdk/incubator/foreign/MemoryAddress;Ljdk/incubator/foreign/MemoryAddress;Ljdk/incubator/foreign/MemoryAddress;)V",
        constants$14.lua_setallocf$FUNC, false
    );
    static final FunctionDescriptor lua_Hook$FUNC = FunctionDescriptor.ofVoid(
        C_POINTER,
        C_POINTER
    );
}


