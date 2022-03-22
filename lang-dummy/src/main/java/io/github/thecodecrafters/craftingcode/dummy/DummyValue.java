package io.github.thecodecrafters.craftingcode.dummy;

import io.github.thecodecrafters.craftingcode.langapi.Callable;
import io.github.thecodecrafters.craftingcode.langapi.Value;

import java.lang.invoke.MethodHandle;
import java.lang.reflect.Constructor;
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

	@Override
	public boolean isString() {
		return value instanceof String;
	}

	@Override
	public boolean isCharacter() {
		return value instanceof Character;
	}

	@Override
	public boolean isBoolean() {
		return value instanceof Boolean;
	}

	@Override
	public boolean isDottedNumber() {
		return value instanceof Double || value instanceof Float;
	}

	@Override
	public boolean isUndottedNumber() {
		return value instanceof Short || value instanceof Integer;
	}

	@Override
	public boolean isMap() {
		return value instanceof Map<?,?>;
	}

	@Override
	public boolean isList() {
		return value instanceof List<?>;
	}

	@Override
	public boolean isCallable() {
		return value instanceof Callable;
	}


	public static Value ofNull() {
		return NULL_VALUE;
	}

	public static Value ofCallable(MethodHandle callable) {
		return new DummyValue( new DummyCallable( callable ) );
	}
}
