package io.github.thecodecrafters.craftingcode.lua.convert;

import io.github.thecodecrafters.craftingcode.langapi.Callable;
import io.github.thecodecrafters.craftingcode.langapi.Value;
import io.github.thecodecrafters.craftingcode.lua.foreign.Lua;
import jdk.incubator.foreign.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

import static io.github.thecodecrafters.craftingcode.lua.foreign.Lua.*;
import static jdk.incubator.foreign.MemoryLayouts.ADDRESS;

public class LuaConverter {
    public interface BaseValue extends Value {
        BaseValue NULL_VALUE = new BaseValue() {
            @Override
            public boolean isNull() {
                return true;
            }

            @Override
            public String toString() {
                return "LuaValue[null]";
            }
        };

        @Override
        default String asString() {
            throw new UnsupportedOperationException();
        }

        @Override
        default Character asChar() {
            throw new UnsupportedOperationException();
        }

        @Override
        default Boolean asBoolean() {
            throw new UnsupportedOperationException();
        }

        @Override
        default Short asShort() {
            throw new UnsupportedOperationException();
        }

        @Override
        default Integer asInt() {
            throw new UnsupportedOperationException();
        }

        @Override
        default Long asLong() {
            throw new UnsupportedOperationException();
        }

        @Override
        default Float asFloat() {
            throw new UnsupportedOperationException();
        }

        @Override
        default Double asDouble() {
            throw new UnsupportedOperationException();
        }

        @Override
        default Map<String, Value> asMap() {
            throw new UnsupportedOperationException();
        }

        @Override
        default List<Value> asList() {
            throw new UnsupportedOperationException();
        }

        @Override
        default Callable asCallable() {
            throw new UnsupportedOperationException();
        }

        @Override
        default boolean isNull() {
            return false;
        }

        @Override
        default boolean isString() {
            return false;
        }

        @Override
        default boolean isCharacter() {
            return false;
        }

        @Override
        default boolean isBoolean() {
            return false;
        }

        @Override
        default boolean isDottedNumber() {
            return false;
        }

        @Override
        default boolean isUndottedNumber() {
            return false;
        }

        @Override
        default boolean isMap() {
            return false;
        }

        @Override
        default boolean isList() {
            return false;
        }

        @Override
        default boolean isCallable() {
            return false;
        }
    }

    public static void objectToLua(@NotNull MemoryAddress L, @Nullable Object o) {
        if (o == null)
            lua_pushnil(L);
        else if (o instanceof String s)
            stringToLua(L, s);
        else if (o instanceof Integer i)
            integerToLua(L, i);
        else if (o instanceof Long i)
            integerToLua(L, i);
        else if (o instanceof Short i)
            integerToLua(L, i);
        else if (o instanceof Byte i)
            integerToLua(L, i);
        else if (o instanceof Float f)
            floatingPointToLua(L, f);
        else if (o instanceof Double f)
            floatingPointToLua(L, f);
        else if (o instanceof Value v)
            valueToLua(L, v);
        else
            throw new IllegalArgumentException("Unsupported object type " + o.getClass().getName() + ": " + o);
    }

    public static @NotNull Value valueFromLua(@NotNull MemoryAddress L, int idx) {
        final int type = lua_type(L, idx);
        if (type == LUA_TNIL() || type == LUA_TNONE()) {
            return BaseValue.NULL_VALUE;
        } else if (type == LUA_TBOOLEAN()) {
            boolean v = lua_toboolean(L, idx) != 0;
            return new BaseValue() {
                @Override
                public Boolean asBoolean() {
                    return v;
                }

                @Override
                public boolean isBoolean() {
                    return true;
                }

                @Override
                public String toString() {
                    return "LuaValue[" + v + ']';
                }
            };
        } else if (type == LUA_TLIGHTUSERDATA()) {
            throw new UnsupportedOperationException();
        } else if (type == LUA_TNUMBER()) {
            if (lua_isinteger(L, idx) != 0) {
                long v = lua_tointeger(L, idx);
                return new BaseValue() {
                    @Override
                    public Short asShort() {
                        return (short) v;
                    }

                    @Override
                    public Integer asInt() {
                        return (int) v;
                    }

                    @Override
                    public Long asLong() {
                        return v;
                    }

                    @Override
                    public Float asFloat() {
                        return (float) v;
                    }

                    @Override
                    public Double asDouble() {
                        return (double) v;
                    }

                    @Override
                    public boolean isUndottedNumber() {
                        return true;
                    }

                    @Override
                    public String toString() {
                        return "LuaValue[" + v + ']';
                    }
                };
            } else {
                double v = lua_tonumber(L, idx);
                return new BaseValue() {
                    @Override
                    public Short asShort() {
                        return (short) v;
                    }

                    @Override
                    public Integer asInt() {
                        return (int) v;
                    }

                    @Override
                    public Long asLong() {
                        return (long) v;
                    }

                    @Override
                    public Float asFloat() {
                        return (float) v;
                    }

                    @Override
                    public Double asDouble() {
                        return v;
                    }

                    @Override
                    public boolean isDottedNumber() {
                        return true;
                    }

                    @Override
                    public String toString() {
                        return "LuaValue[" + v + ']';
                    }
                };
            }
        } else if (type == LUA_TSTRING()) {
            String v = stringFromLua(L, idx);
            return new BaseValue() {
                @Override
                public Character asChar() {
                    if (v.length() != 1) {
                        throw new IllegalStateException("Cannot convert string of length " + v.length() + " to char");
                    }
                    return v.charAt(0);
                }

                @Override
                public String asString() {
                    return v;
                }

                @Override
                public boolean isString() {
                    return true;
                }

                @Override
                public String toString() {
                    return "LuaValue[\"" + v + "\"]";
                }
            };
        } else if (type == LUA_TTABLE()) {
            throw new UnsupportedOperationException();
        } else if (type == LUA_TFUNCTION()) {
            throw new UnsupportedOperationException();
        } else if (type == LUA_TUSERDATA()) {
            throw new UnsupportedOperationException();
        } else if (type == LUA_TTHREAD()) {
            throw new UnsupportedOperationException();
        }
        throw new UnsupportedOperationException();
    }

    public static void valueToLua(@NotNull MemoryAddress L, @NotNull Value val) {
        if (val.isNull())
            lua_pushnil(L);
        else if (val.isString())
            stringToLua(L, val.asString());
        else
            throw new IllegalArgumentException("Cannot convert " + val + " to Lua");
    }

    public static int stringToLua(@NotNull MemoryAddress L, String s) {
        try (ResourceScope scope = ResourceScope.newConfinedScope()) {
            MemorySegment cs = CLinker.toCString(s, scope);
            Lua.lua_pushlstring(L, cs, cs.byteSize() - 1);
        }
        return 1;
    }

    public static @NotNull String stringFromLua(@NotNull MemoryAddress L, int idx) {
        try (ResourceScope scope = ResourceScope.newConfinedScope()) {
            MemorySegment lenSegment = MemorySegment.allocateNative(ADDRESS, scope);
            MemoryAddress address = Lua.lua_tolstring(L, idx, lenSegment);
            int len = (int) MemoryAccess.getAddress(lenSegment).toRawLongValue();

            if (len == 0)
                return "";

            byte[] bytes = new byte[len];
            MemorySegment.ofArray(bytes)
                    .copyFrom(address.asSegment(len, scope));
            return new String(bytes, StandardCharsets.UTF_8);
        }
    }

    public static int integerToLua(@NotNull MemoryAddress L, long i) {
        lua_pushinteger(L, i);
        return 1;
    }

    public static long integerFromLua(@NotNull MemoryAddress L, int idx) {
        return lua_tointeger(L, idx);
    }

    public static int floatingPointToLua(@NotNull MemoryAddress L, double f) {
        lua_pushnumber(L, f);
        return 1;
    }

    public static double floatingPointFromLua(@NotNull MemoryAddress L, int idx) {
        return lua_tonumber(L, idx);
    }
}
