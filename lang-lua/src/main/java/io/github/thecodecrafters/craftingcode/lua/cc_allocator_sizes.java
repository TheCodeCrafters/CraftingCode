package io.github.thecodecrafters.craftingcode.lua;

import jdk.incubator.foreign.*;

import java.lang.invoke.VarHandle;

import static io.github.thecodecrafters.craftingcode.lua.foreign.Lua.size_t;

public class cc_allocator_sizes {

    static final MemoryLayout $struct$LAYOUT = MemoryLayout.structLayout(
            size_t.withName("usedMemory"),
            size_t.withName("maxMemory")
    ).withName("cc_allocator_sizes");

    public static MemoryLayout $LAYOUT() {
        return cc_allocator_sizes.$struct$LAYOUT;
    }

    static final VarHandle usedMemory$VH = $struct$LAYOUT.varHandle(long.class, MemoryLayout.PathElement.groupElement("usedMemory"));

    public static VarHandle usedMemory$VH() {
        return cc_allocator_sizes.usedMemory$VH;
    }

    public static long usedMemory$get(MemorySegment seg) {
        return (long) cc_allocator_sizes.usedMemory$VH.get(seg);
    }

    public static void usedMemory$set(MemorySegment seg, long x) {
        cc_allocator_sizes.usedMemory$VH.set(seg, x);
    }

    public static long usedMemory$get(MemorySegment seg, long index) {
        return (long) cc_allocator_sizes.usedMemory$VH.get(seg.asSlice(index * sizeof()));
    }

    public static void usedMemory$set(MemorySegment seg, long index, long x) {
        cc_allocator_sizes.usedMemory$VH.set(seg.asSlice(index * sizeof()), x);
    }

    static final VarHandle maxMemory$VH = $struct$LAYOUT.varHandle(long.class, MemoryLayout.PathElement.groupElement("maxMemory"));

    public static VarHandle maxMemory$VH() {
        return cc_allocator_sizes.maxMemory$VH;
    }

    public static long maxMemory$get(MemorySegment seg) {
        return (long) cc_allocator_sizes.maxMemory$VH.get(seg);
    }

    public static void maxMemory$set(MemorySegment seg, long x) {
        cc_allocator_sizes.maxMemory$VH.set(seg, x);
    }

    public static long maxMemory$get(MemorySegment seg, long index) {
        return (long) cc_allocator_sizes.maxMemory$VH.get(seg.asSlice(index * sizeof()));
    }

    public static void maxMemory$set(MemorySegment seg, long index, long x) {
        cc_allocator_sizes.maxMemory$VH.set(seg.asSlice(index * sizeof()), x);
    }

    public static long sizeof() {
        return $LAYOUT().byteSize();
    }

    public static MemorySegment allocate(SegmentAllocator allocator) {
        return allocator.allocate($LAYOUT());
    }

    public static MemorySegment allocate(ResourceScope scope) {
        return allocate(SegmentAllocator.ofScope(scope));
    }

    public static MemorySegment allocateArray(int len, SegmentAllocator allocator) {
        return allocator.allocate(MemoryLayout.sequenceLayout(len, $LAYOUT()));
    }

    public static MemorySegment allocateArray(int len, ResourceScope scope) {
        return allocateArray(len, SegmentAllocator.ofScope(scope));
    }

    public static MemorySegment ofAddress(MemoryAddress addr, ResourceScope scope) {
        return addr.asSegment($LAYOUT().byteSize(), scope);
    }
}


