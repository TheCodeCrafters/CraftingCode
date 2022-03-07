package io.github.bubblie.craftingcode.langapi;

import java.util.List;
import java.util.Map;

/**
 * A value is something the VM will provide the java side if a java function was called with parameters.
 */
public interface Value {
	String asString();
	char asChar();
	boolean asBoolean();
	short asShort();
	int asInt();
	long asLong();
	float asFloat();
	double asDouble();
	Map<String, Value> asMap();
	List<Value> asList();

	boolean isNull();
}
