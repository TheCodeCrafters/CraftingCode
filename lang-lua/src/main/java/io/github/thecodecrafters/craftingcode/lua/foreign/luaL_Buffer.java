// Generated by jextract

package io.github.thecodecrafters.craftingcode.lua.foreign;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import jdk.incubator.foreign.*;
import static jdk.incubator.foreign.CLinker.*;
public class luaL_Buffer {

    static final MemoryLayout $struct$LAYOUT = MemoryLayout.structLayout(
        C_POINTER.withName("b"),
        C_LONG.withName("size"),
        C_LONG.withName("n"),
        C_POINTER.withName("L"),
        MemoryLayout.sequenceLayout(8192, C_CHAR).withName("initb")
    ).withName("luaL_Buffer");
    public static MemoryLayout $LAYOUT() {
        return luaL_Buffer.$struct$LAYOUT;
    }
    static final VarHandle b$VH = MemoryHandles.asAddressVarHandle($struct$LAYOUT.varHandle(long.class, MemoryLayout.PathElement.groupElement("b")));
    public static VarHandle b$VH() {
        return luaL_Buffer.b$VH;
    }
    public static MemoryAddress b$get(MemorySegment seg) {
        return (jdk.incubator.foreign.MemoryAddress)luaL_Buffer.b$VH.get(seg);
    }
    public static void b$set( MemorySegment seg, MemoryAddress x) {
        luaL_Buffer.b$VH.set(seg, x);
    }
    public static MemoryAddress b$get(MemorySegment seg, long index) {
        return (jdk.incubator.foreign.MemoryAddress)luaL_Buffer.b$VH.get(seg.asSlice(index*sizeof()));
    }
    public static void b$set(MemorySegment seg, long index, MemoryAddress x) {
        luaL_Buffer.b$VH.set(seg.asSlice(index*sizeof()), x);
    }
    static final VarHandle size$VH = $struct$LAYOUT.varHandle(long.class, MemoryLayout.PathElement.groupElement("size"));
    public static VarHandle size$VH() {
        return luaL_Buffer.size$VH;
    }
    public static long size$get(MemorySegment seg) {
        return (long)luaL_Buffer.size$VH.get(seg);
    }
    public static void size$set( MemorySegment seg, long x) {
        luaL_Buffer.size$VH.set(seg, x);
    }
    public static long size$get(MemorySegment seg, long index) {
        return (long)luaL_Buffer.size$VH.get(seg.asSlice(index*sizeof()));
    }
    public static void size$set(MemorySegment seg, long index, long x) {
        luaL_Buffer.size$VH.set(seg.asSlice(index*sizeof()), x);
    }
    static final VarHandle n$VH = $struct$LAYOUT.varHandle(long.class, MemoryLayout.PathElement.groupElement("n"));
    public static VarHandle n$VH() {
        return luaL_Buffer.n$VH;
    }
    public static long n$get(MemorySegment seg) {
        return (long)luaL_Buffer.n$VH.get(seg);
    }
    public static void n$set( MemorySegment seg, long x) {
        luaL_Buffer.n$VH.set(seg, x);
    }
    public static long n$get(MemorySegment seg, long index) {
        return (long)luaL_Buffer.n$VH.get(seg.asSlice(index*sizeof()));
    }
    public static void n$set(MemorySegment seg, long index, long x) {
        luaL_Buffer.n$VH.set(seg.asSlice(index*sizeof()), x);
    }
    static final VarHandle L$VH = MemoryHandles.asAddressVarHandle($struct$LAYOUT.varHandle(long.class, MemoryLayout.PathElement.groupElement("L")));
    public static VarHandle L$VH() {
        return luaL_Buffer.L$VH;
    }
    public static MemoryAddress L$get(MemorySegment seg) {
        return (jdk.incubator.foreign.MemoryAddress)luaL_Buffer.L$VH.get(seg);
    }
    public static void L$set( MemorySegment seg, MemoryAddress x) {
        luaL_Buffer.L$VH.set(seg, x);
    }
    public static MemoryAddress L$get(MemorySegment seg, long index) {
        return (jdk.incubator.foreign.MemoryAddress)luaL_Buffer.L$VH.get(seg.asSlice(index*sizeof()));
    }
    public static void L$set(MemorySegment seg, long index, MemoryAddress x) {
        luaL_Buffer.L$VH.set(seg.asSlice(index*sizeof()), x);
    }
    public static MemorySegment initb$slice(MemorySegment seg) {
        return seg.asSlice(32, 8192);
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

