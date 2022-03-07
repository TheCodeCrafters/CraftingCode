package io.github.bubblie.craftingcode.langapi;

import java.util.List;
import java.util.Map;

public interface Value {
	String asString();
	char asChar();
	short asShort();
	int asInt();
	long asLong();
	float asFloat();
	double asDouble();
	Map<String, ?> asMap();
	List<?> asList();
}
