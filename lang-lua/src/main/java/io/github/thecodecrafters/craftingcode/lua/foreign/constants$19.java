// Generated by jextract

package io.github.thecodecrafters.craftingcode.lua.foreign;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import jdk.incubator.foreign.*;
import static jdk.incubator.foreign.CLinker.*;
class constants$19 {

    static final FunctionDescriptor luaopen_eris$FUNC = FunctionDescriptor.of(C_INT,
        C_POINTER
    );
    static final MethodHandle luaopen_eris$MH = RuntimeHelper.downcallHandle(
        Lua.LIBRARIES, "luaopen_eris",
        "(Ljdk/incubator/foreign/MemoryAddress;)I",
        constants$19.luaopen_eris$FUNC, false
    );
    static final FunctionDescriptor luaL_openlibs$FUNC = FunctionDescriptor.ofVoid(
        C_POINTER
    );
    static final MethodHandle luaL_openlibs$MH = RuntimeHelper.downcallHandle(
        Lua.LIBRARIES, "luaL_openlibs",
        "(Ljdk/incubator/foreign/MemoryAddress;)V",
        constants$19.luaL_openlibs$FUNC, false
    );
    static final FunctionDescriptor luaL_checkversion_$FUNC = FunctionDescriptor.ofVoid(
        C_POINTER,
        C_DOUBLE,
        C_LONG
    );
    static final MethodHandle luaL_checkversion_$MH = RuntimeHelper.downcallHandle(
        Lua.LIBRARIES, "luaL_checkversion_",
        "(Ljdk/incubator/foreign/MemoryAddress;DJ)V",
        constants$19.luaL_checkversion_$FUNC, false
    );
    static final FunctionDescriptor luaL_getmetafield$FUNC = FunctionDescriptor.of(C_INT,
        C_POINTER,
        C_INT,
        C_POINTER
    );
    static final MethodHandle luaL_getmetafield$MH = RuntimeHelper.downcallHandle(
        Lua.LIBRARIES, "luaL_getmetafield",
        "(Ljdk/incubator/foreign/MemoryAddress;ILjdk/incubator/foreign/MemoryAddress;)I",
        constants$19.luaL_getmetafield$FUNC, false
    );
    static final FunctionDescriptor luaL_callmeta$FUNC = FunctionDescriptor.of(C_INT,
        C_POINTER,
        C_INT,
        C_POINTER
    );
    static final MethodHandle luaL_callmeta$MH = RuntimeHelper.downcallHandle(
        Lua.LIBRARIES, "luaL_callmeta",
        "(Ljdk/incubator/foreign/MemoryAddress;ILjdk/incubator/foreign/MemoryAddress;)I",
        constants$19.luaL_callmeta$FUNC, false
    );
    static final FunctionDescriptor luaL_tolstring$FUNC = FunctionDescriptor.of(C_POINTER,
        C_POINTER,
        C_INT,
        C_POINTER
    );
    static final MethodHandle luaL_tolstring$MH = RuntimeHelper.downcallHandle(
        Lua.LIBRARIES, "luaL_tolstring",
        "(Ljdk/incubator/foreign/MemoryAddress;ILjdk/incubator/foreign/MemoryAddress;)Ljdk/incubator/foreign/MemoryAddress;",
        constants$19.luaL_tolstring$FUNC, false
    );
}

