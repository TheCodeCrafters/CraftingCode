package io.github.thecodecrafters.craftingcode.lua;

import jdk.incubator.foreign.MemoryAddress;
import jdk.incubator.foreign.MemorySegment;
import jdk.incubator.foreign.ResourceScope;

public class RegistryIds {
    private RegistryIds() {}
    private static final MemorySegment SEGMENT = MemorySegment.allocateNative(1, ResourceScope.newImplicitScope());

    public static final MemoryAddress ID = SEGMENT.address();
}
