package io.github.bubblie.craftingcode.langapi;

import org.jetbrains.annotations.Nullable;

public interface Callable {
	@Nullable
	Object invoke( Object... args );
}
