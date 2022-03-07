package io.github.thecodecrafters.craftingcode.core;

import jdk.incubator.foreign.*;
import net.fabricmc.api.ModInitializer;

import java.lang.invoke.MethodType;

public class CraftingCode implements ModInitializer {
	@Override
	public void onInitialize() {
		System.out.println("Hello fucking world!");
		CLinker linker = CLinker.getInstance();
		SymbolLookup systemLookup = CLinker.systemLookup();
		MemoryAddress printf = systemLookup.lookup("printf").orElseThrow();
		try(ResourceScope scope = ResourceScope.newConfinedScope()) {
			linker.downcallHandle(printf, MethodType.methodType(int.class, MemoryAddress.class), FunctionDescriptor.of(CLinker.C_INT, CLinker.C_POINTER)).invoke(CLinker.toCString("Hello, Panama world!\n", scope).address());
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}
}
