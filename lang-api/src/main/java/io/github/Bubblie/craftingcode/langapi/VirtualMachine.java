package io.github.Bubblie.craftingcode.langapi;

import java.lang.invoke.MethodHandle;
import java.util.Map;
import java.util.UUID;

public interface VirtualMachine {
	void registerClass( Class<?> clazz );
	void registerFunction( MethodHandle handle );
	void registerModule( Map<String, ?> module );

	Context createContext();
	Context getContext(UUID uuid);
	void deleteContext(UUID uuid);

	LanguageProvider getProvider();
}
