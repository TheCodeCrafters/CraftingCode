package io.github.Bubblie.craftingcode.langapi;

import org.jetbrains.annotations.Nullable;

public interface Callable {
	@Nullable
	Object invoke( Object... args );
}
