// Generated by jextract

package io.github.thecodecrafters.craftingcode.lua.foreign;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import jdk.incubator.foreign.*;
import static jdk.incubator.foreign.CLinker.*;
class constants$4 {

    static final FunctionDescriptor lua_isstring$FUNC = FunctionDescriptor.of(C_INT,
        C_POINTER,
        C_INT
    );
    static final MethodHandle lua_isstring$MH = RuntimeHelper.downcallHandle(
        Lua.LIBRARIES, "lua_isstring",
        "(Ljdk/incubator/foreign/MemoryAddress;I)I",
        constants$4.lua_isstring$FUNC, false
    );
    static final FunctionDescriptor lua_iscfunction$FUNC = FunctionDescriptor.of(C_INT,
        C_POINTER,
        C_INT
    );
    static final MethodHandle lua_iscfunction$MH = RuntimeHelper.downcallHandle(
        Lua.LIBRARIES, "lua_iscfunction",
        "(Ljdk/incubator/foreign/MemoryAddress;I)I",
        constants$4.lua_iscfunction$FUNC, false
    );
    static final FunctionDescriptor lua_isinteger$FUNC = FunctionDescriptor.of(C_INT,
        C_POINTER,
        C_INT
    );
    static final MethodHandle lua_isinteger$MH = RuntimeHelper.downcallHandle(
        Lua.LIBRARIES, "lua_isinteger",
        "(Ljdk/incubator/foreign/MemoryAddress;I)I",
        constants$4.lua_isinteger$FUNC, false
    );
    static final FunctionDescriptor lua_isuserdata$FUNC = FunctionDescriptor.of(C_INT,
        C_POINTER,
        C_INT
    );
    static final MethodHandle lua_isuserdata$MH = RuntimeHelper.downcallHandle(
        Lua.LIBRARIES, "lua_isuserdata",
        "(Ljdk/incubator/foreign/MemoryAddress;I)I",
        constants$4.lua_isuserdata$FUNC, false
    );
    static final FunctionDescriptor lua_type$FUNC = FunctionDescriptor.of(C_INT,
        C_POINTER,
        C_INT
    );
    static final MethodHandle lua_type$MH = RuntimeHelper.downcallHandle(
        Lua.LIBRARIES, "lua_type",
        "(Ljdk/incubator/foreign/MemoryAddress;I)I",
        constants$4.lua_type$FUNC, false
    );
    static final FunctionDescriptor lua_typename$FUNC = FunctionDescriptor.of(C_POINTER,
        C_POINTER,
        C_INT
    );
    static final MethodHandle lua_typename$MH = RuntimeHelper.downcallHandle(
        Lua.LIBRARIES, "lua_typename",
        "(Ljdk/incubator/foreign/MemoryAddress;I)Ljdk/incubator/foreign/MemoryAddress;",
        constants$4.lua_typename$FUNC, false
    );
}


