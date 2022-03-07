package io.github.bubblie.craftingcode.util;

import sun.misc.Unsafe;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Field;

public class PanamaEnabler {
    public static void enablePanama() {
        try {
            final Field unsafeField = Unsafe.class.getDeclaredField("theUnsafe");
            unsafeField.setAccessible(true);
            final Unsafe unsafe = (Unsafe) unsafeField.get(null);
            final Field implLookupField = MethodHandles.Lookup.class.getDeclaredField("IMPL_LOOKUP");
            final MethodHandles.Lookup implLookup = (MethodHandles.Lookup) unsafe.getObject(
                    unsafe.staticFieldBase(implLookupField),
                    unsafe.staticFieldOffset(implLookupField)
            );
            final MethodHandle loadModuleHandle = implLookup.findStatic(
                    Class.forName("jdk.internal.module.Modules"),
                    "loadModule",
                    MethodType.methodType( Module.class, String.class )
            );
            loadModuleHandle.invoke( "jdk.incubator.foreign" );
            implLookup.findSetter(
                    Module.class,
                    "enableNativeAccess",
                    boolean.class
            ).invoke(
                    (Module) implLookup.findStaticGetter(
                            Module.class,
                            "ALL_UNNAMED_MODULE",
                            Module.class
                    ).invokeExact(),
                    true
            );
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
