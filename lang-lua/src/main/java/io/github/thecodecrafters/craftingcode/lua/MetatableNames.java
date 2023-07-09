package io.github.thecodecrafters.craftingcode.lua;

import jdk.incubator.foreign.CLinker;
import jdk.incubator.foreign.MemorySegment;
import jdk.incubator.foreign.ResourceScope;

public class MetatableNames {
    private MetatableNames() {}

    public static final MemorySegment WRAPPED_JAVA_OBJECT = CLinker.toCString("cc_wrapped_java_object", ResourceScope.newImplicitScope());
    public static final MemorySegment WRAPPED_JAVA_THROWABLE = CLinker.toCString("cc_wrapped_java_throwable", ResourceScope.newImplicitScope());
}
