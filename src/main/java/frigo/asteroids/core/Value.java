
package frigo.asteroids.core;

import static org.apache.commons.lang3.builder.ToStringStyle.SHORT_PREFIX_STYLE;

import java.io.Serializable;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.SerializationUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class Value implements Serializable {

    @Override
    public boolean equals (Object that) {
        if( that == null ){
            return false;
        }
        if( this.getClass() != that.getClass() ){
            return false;
        }
        Value thatSerializable = (Value) that;
        return ArrayUtils.isEquals(serialize(), thatSerializable.serialize());
    }

    @Override
    public int hashCode () {
        return getClass().hashCode() * 31 + ArrayUtils.hashCode(serialize());
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
