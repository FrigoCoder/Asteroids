
package frigo.asteroids.core;

import static org.apache.commons.lang3.builder.ToStringStyle.SHORT_PREFIX_STYLE;

import java.io.Serializable;
import java.util.Objects;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.SerializationUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class Value implements Serializable, Cloneable {

    private static final int DONALD_KNUTH_RECOMMENDED_PRIME_YOU_NITPICKS = 31;

    @Override
    public boolean equals (Object object) {
        if( object == null || this.getClass() != object.getClass() ){
            return false;
        }
        Value that = (Value) object;
        return Objects.deepEquals(serialize(), that.serialize());
    }

    @Override
    public int hashCode () {
        return getClass().hashCode() * DONALD_KNUTH_RECOMMENDED_PRIME_YOU_NITPICKS + ArrayUtils.hashCode(serialize());
    }

    @Override
    public String toString () {
        return ReflectionToStringBuilder.toString(this, SHORT_PREFIX_STYLE);
    }

    @Override
    public Value clone () {
        return SerializationUtils.clone(this);
    }

    public byte[] serialize () {
        return SerializationUtils.serialize(this);
    }

    public static <T extends Value> T deserialize (byte[] data) {
        return (T) SerializationUtils.deserialize(data);
    }

}
