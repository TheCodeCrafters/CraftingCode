package io.github.thecodecrafters.craftingcode.langapi;

import java.util.List;
import java.util.Map;

/**
 * A value is something the VM will provide the java side if a java function was called with parameters.
 */
public interface Value {
	String asString();
	Character asChar();
	Boolean asBoolean();
	Short asShort();
	Integer asInt();
	Long asLong();
	Float asFloat();
	Double asDouble();
	Map<String, Value> asMap();
	List<Value> asList();
	Callable asCallable();

	boolean isNull();
}
