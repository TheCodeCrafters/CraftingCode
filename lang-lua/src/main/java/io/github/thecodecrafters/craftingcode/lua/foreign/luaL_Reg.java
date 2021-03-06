// Generated by jextract

package io.github.thecodecrafters.craftingcode.lua.foreign;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import jdk.incubator.foreign.*;
import static jdk.incubator.foreign.CLinker.*;
public class luaL_Reg {

    static final MemoryLayout $struct$LAYOUT = MemoryLayout.structLayout(
        C_POINTER.withName("name"),
        C_POINTER.withName("func")
    ).withName("luaL_Reg");
    public static MemoryLayout $LAYOUT() {
        return luaL_Reg.$struct$LAYOUT;
    }
    static final VarHandle name$VH = MemoryHandles.asAddressVarHandle($struct$LAYOUT.varHandle(long.class, MemoryLayout.PathElement.groupElement("name")));
    public static VarHandle name$VH() {
        return luaL_Reg.name$VH;
    }
    public static MemoryAddress name$get(MemorySegment seg) {
        return (jdk.incubator.foreign.MemoryAddress)luaL_Reg.name$VH.get(seg);
    }
    public static void name$set( MemorySegment seg, MemoryAddress x) {
        luaL_Reg.name$VH.set(seg, x);
    }
    public static MemoryAddress name$get(MemorySegment seg, long index) {
        return (jdk.incubator.foreign.MemoryAddress)luaL_Reg.name$VH.get(seg.asSlice(index*sizeof()));
    }
    public static void name$set(MemorySegment seg, long index, MemoryAddress x) {
        luaL_Reg.name$VH.set(seg.asSlice(index*sizeof()), x);
    }
    static final VarHandle func$VH = MemoryHandles.asAddressVarHandle($struct$LAYOUT.varHandle(long.class, MemoryLayout.PathElement.groupElement("func")));
    public static VarHandle func$VH() {
        return luaL_Reg.func$VH;
    }
    public static MemoryAddress func$get(MemorySegment seg) {
        return (jdk.incubator.foreign.MemoryAddress)luaL_Reg.func$VH.get(seg);
    }
    public static void func$set( MemorySegment seg, MemoryAddress x) {
        luaL_Reg.func$VH.set(seg, x);
    }
    public static MemoryAddress func$get(MemorySegment seg, long index) {
        return (jdk.incubator.foreign.MemoryAddress)luaL_Reg.func$VH.get(seg.asSlice(index*sizeof()));
    }
    public static void func$set(MemorySegment seg, long index, MemoryAddress x) {
        luaL_Reg.func$VH.set(seg.asSlice(index*sizeof()), x);
    }
    public static lua_CFunction func (MemorySegment segment) {
        return lua_CFunction.ofAddress(func$get(segment));
    }
    public static long sizeof() { return $LAYOUT().byteSize(); }
    public static MemorySegment allocate(SegmentAllocator allocator) { return allocator.allocate($LAYOUT()); }
    public static MemorySegment allocate(ResourceScope scope) { return allocate(SegmentAllocator.ofScope(scope)); }
    public static MemorySegment allocateArray(int len, SegmentAllocator allocator) {
        return allocator.allocate(MemoryLayout.sequenceLayout(len, $LAYOUT()));
    }
    public static MemorySegment allocateArray(int len, ResourceScope scope) {
        return allocateArray(len, SegmentAllocator.ofScope(scope));
    }
    public static MemorySegment ofAddress(MemoryAddress addr, ResourceScope scope) { return RuntimeHelper.asArray(addr, $LAYOUT(), 1, scope); }
}


