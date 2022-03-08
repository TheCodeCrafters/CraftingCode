package io.github.thecodecrafters.craftingcode.dummy;

import io.github.thecodecrafters.craftingcode.langapi.Context;
import io.github.thecodecrafters.craftingcode.langapi.LanguageProvider;
import io.github.thecodecrafters.craftingcode.langapi.Value;
import io.github.thecodecrafters.craftingcode.langapi.VirtualMachine;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class DummyVirtualMachine implements VirtualMachine {
	private final Map<UUID, DummyContext> map = new HashMap<>();
	private final Map<String, Value> globals = new HashMap<>();
	private final LanguageProvider provider;

	public DummyVirtualMachine( LanguageProvider provider ) {
		this.provider = provider;
	}

	@Override
	public void registerClass(Class<?> clazz) {
		try {
			this.globals.put( clazz.getSimpleName(), DummyValue.ofCallable( MethodHandles.lookup().findConstructor( clazz, MethodType.methodType(clazz) ) ) );
		} catch (NoSuchMethodException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void registerFunction( String name, MethodHandle handle ) {
		this.globals.put( name, DummyValue.ofCallable( handle ) );
	}

	@Override
	public void registerModule(Map<String, Value> module) {
		// no op
	}

	@Override
	public Context getContext(UUID uuid) {
		return this.map.containsKey( uuid ) ? this.map.get( uuid ) : this.map.put( uuid, new DummyContext( this.globals ) );
	}

	@Override
	public void deleteContext(UUID uuid) {
		this.map.remove( uuid );
	}

	@Override
	public LanguageProvider getProvider() {
		return this.provider;
	}
}
