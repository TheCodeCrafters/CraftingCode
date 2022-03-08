package io.github.thecodecrafters.craftingcode.dummy;

import io.github.thecodecrafters.craftingcode.langapi.Callable;
import io.github.thecodecrafters.craftingcode.langapi.Value;

import java.util.List;
import java.util.Map;

public class DummyValue implements Value {
	private static final Value NULL_VALUE = new DummyValue(null);
	private final Object value;

	public DummyValue(Object obj) {
		this.value = obj;
	}


	@Override
	public String asString() {
		return (String) value;
	}

	@Override
	public Character asChar() {
		return (char) value;
	}

	@Override
	public Boolean asBoolean() {
		return (boolean) value;
	}

	@Override
	public Short asShort() {
		return (Short) value;
	}

	@Override
	public Integer asInt() {
		return (Integer) value;
	}

	@Override
	public Long asLong() {
		return (Long) value;
	}

	@Override
	public Float asFloat() {
		return (Float) value;
	}

	@Override
	public Double asDouble() {
		return (Double) value;
	}

	@Override
	public Map<String, Value> asMap() {
		//noinspection unchecked
		return ( Map<String, Value> ) value;
	}

	@Override
	public List<Value> asList() {
		//noinspection unchecked
		return (List<Value>) value;
	}

	@Override
	public Callable asCallable() {
		return (Callable) value;
	}

	@Override
	public boolean isNull() {
		return value == null;
	}

	public static Value ofNull() {
		return NULL_VALUE;
	}
}
