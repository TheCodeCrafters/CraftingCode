// Generated by jextract

package io.github.thecodecrafters.craftingcode.lua.foreign;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import jdk.incubator.foreign.*;
import static jdk.incubator.foreign.CLinker.*;
class constants$16 {

    static final FunctionDescriptor lua_setupvalue$FUNC = FunctionDescriptor.of(C_POINTER,
        C_POINTER,
        C_INT,
        C_INT
    );
    static final MethodHandle lua_setupvalue$MH = RuntimeHelper.downcallHandle(
        Lua.LIBRARIES, "lua_setupvalue",
        "(Ljdk/incubator/foreign/MemoryAddress;II)Ljdk/incubator/foreign/MemoryAddress;",
        constants$16.lua_setupvalue$FUNC, false
    );
    static final FunctionDescriptor lua_upvalueid$FUNC = FunctionDescriptor.of(C_POINTER,
        C_POINTER,
        C_INT,
        C_INT
    );
    static final MethodHandle lua_upvalueid$MH = RuntimeHelper.downcallHandle(
        Lua.LIBRARIES, "lua_upvalueid",
        "(Ljdk/incubator/foreign/MemoryAddress;II)Ljdk/incubator/foreign/MemoryAddress;",
        constants$16.lua_upvalueid$FUNC, false
    );
    static final FunctionDescriptor lua_upvaluejoin$FUNC = FunctionDescriptor.ofVoid(
        C_POINTER,
        C_INT,
        C_INT,
        C_INT,
        C_INT
    );
    static final MethodHandle lua_upvaluejoin$MH = RuntimeHelper.downcallHandle(
        Lua.LIBRARIES, "lua_upvaluejoin",
        "(Ljdk/incubator/foreign/MemoryAddress;IIII)V",
        constants$16.lua_upvaluejoin$FUNC, false
    );
    static final FunctionDescriptor lua_sethook$FUNC = FunctionDescriptor.ofVoid(
        C_POINTER,
        C_POINTER,
        C_INT,
        C_INT
    );
    static final MethodHandle lua_sethook$MH = RuntimeHelper.downcallHandle(
        Lua.LIBRARIES, "lua_sethook",
        "(Ljdk/incubator/foreign/MemoryAddress;Ljdk/incubator/foreign/MemoryAddress;II)V",
        constants$16.lua_sethook$FUNC, false
    );
    static final FunctionDescriptor lua_gethook$FUNC = FunctionDescriptor.of(C_POINTER,
        C_POINTER
    );
    static final MethodHandle lua_gethook$MH = RuntimeHelper.downcallHandle(
        Lua.LIBRARIES, "lua_gethook",
        "(Ljdk/incubator/foreign/MemoryAddress;)Ljdk/incubator/foreign/MemoryAddress;",
        constants$16.lua_gethook$FUNC, false
    );
    static final FunctionDescriptor lua_gethookmask$FUNC = FunctionDescriptor.of(C_INT,
        C_POINTER
    );
    static final MethodHandle lua_gethookmask$MH = RuntimeHelper.downcallHandle(
        Lua.LIBRARIES, "lua_gethookmask",
        "(Ljdk/incubator/foreign/MemoryAddress;)I",
        constants$16.lua_gethookmask$FUNC, false
    );
}


