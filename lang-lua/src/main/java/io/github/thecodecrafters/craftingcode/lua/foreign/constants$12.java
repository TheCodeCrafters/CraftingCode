// Generated by jextract

package io.github.thecodecrafters.craftingcode.lua.foreign;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import jdk.incubator.foreign.*;
import static jdk.incubator.foreign.CLinker.*;
class constants$12 {

    static final FunctionDescriptor lua_setuservalue$FUNC = FunctionDescriptor.ofVoid(
        C_POINTER,
        C_INT
    );
    static final MethodHandle lua_setuservalue$MH = RuntimeHelper.downcallHandle(
        Lua.LIBRARIES, "lua_setuservalue",
        "(Ljdk/incubator/foreign/MemoryAddress;I)V",
        constants$12.lua_setuservalue$FUNC, false
    );
    static final FunctionDescriptor lua_callk$FUNC = FunctionDescriptor.ofVoid(
        C_POINTER,
        C_INT,
        C_INT,
        C_LONG,
        C_POINTER
    );
    static final MethodHandle lua_callk$MH = RuntimeHelper.downcallHandle(
        Lua.LIBRARIES, "lua_callk",
        "(Ljdk/incubator/foreign/MemoryAddress;IIJLjdk/incubator/foreign/MemoryAddress;)V",
        constants$12.lua_callk$FUNC, false
    );
    static final FunctionDescriptor lua_pcallk$FUNC = FunctionDescriptor.of(C_INT,
        C_POINTER,
        C_INT,
        C_INT,
        C_INT,
        C_LONG,
        C_POINTER
    );
    static final MethodHandle lua_pcallk$MH = RuntimeHelper.downcallHandle(
        Lua.LIBRARIES, "lua_pcallk",
        "(Ljdk/incubator/foreign/MemoryAddress;IIIJLjdk/incubator/foreign/MemoryAddress;)I",
        constants$12.lua_pcallk$FUNC, false
    );
    static final FunctionDescriptor lua_load$FUNC = FunctionDescriptor.of(C_INT,
        C_POINTER,
        C_POINTER,
        C_POINTER,
        C_POINTER,
        C_POINTER
    );
    static final MethodHandle lua_load$MH = RuntimeHelper.downcallHandle(
        Lua.LIBRARIES, "lua_load",
        "(Ljdk/incubator/foreign/MemoryAddress;Ljdk/incubator/foreign/MemoryAddress;Ljdk/incubator/foreign/MemoryAddress;Ljdk/incubator/foreign/MemoryAddress;Ljdk/incubator/foreign/MemoryAddress;)I",
        constants$12.lua_load$FUNC, false
    );
    static final FunctionDescriptor lua_dump$FUNC = FunctionDescriptor.of(C_INT,
        C_POINTER,
        C_POINTER,
        C_POINTER,
        C_INT
    );
    static final MethodHandle lua_dump$MH = RuntimeHelper.downcallHandle(
        Lua.LIBRARIES, "lua_dump",
        "(Ljdk/incubator/foreign/MemoryAddress;Ljdk/incubator/foreign/MemoryAddress;Ljdk/incubator/foreign/MemoryAddress;I)I",
        constants$12.lua_dump$FUNC, false
    );
    static final FunctionDescriptor lua_yieldk$FUNC = FunctionDescriptor.of(C_INT,
        C_POINTER,
        C_INT,
        C_LONG,
        C_POINTER
    );
    static final MethodHandle lua_yieldk$MH = RuntimeHelper.downcallHandle(
        Lua.LIBRARIES, "lua_yieldk",
        "(Ljdk/incubator/foreign/MemoryAddress;IJLjdk/incubator/foreign/MemoryAddress;)I",
        constants$12.lua_yieldk$FUNC, false
    );
}


