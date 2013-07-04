
package frigo.asteroids.core;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.sameInstance;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class ValueTest {

    private static class EmptyValue extends Value {
    }

    private static class IntegerValue extends Value {

        private Integer field;

        public IntegerValue (int field) {
            this.field = field;
        }
    }

    private Value value = new Value();
    private Value empty = new EmptyValue();
    private IntegerValue int1 = new IntegerValue(1);
    private IntegerValue int2 = new IntegerValue(2);

    private IntegerValue int1Spy = new IntegerValue(1);
    private IntegerValue int1Clone = (IntegerValue) int1.clone();
    private byte[] int1Serialized = int1.serialize();
    private IntegerValue int1Deserialized = Value.deserialize(int1Serialized);

    @Test
    public void value_does_not_equal_null () {
        assertThat(value.equals(null), is(false));
    }

    @Test
    public void does_not_equal_new_object () {
        assertThat(value, not(new Object()));
    }

    @Test
    public void different_types_are_not_equal () {
        assertThat(value, not(empty));
    }

    @Test
    public void different_types_have_different_hashcode () {
        assertThat(value.hashCode(), not(empty.hashCode()));
    }

    @Test
    public void different_contents_are_not_equal () {
        assertThat(int1, not(int2));
    }

    @Test
    public void different_contents_have_different_hashcode () {
        assertThat(int1.hashCode(), not(int2.hashCode()));
    }

    @Test
    public void same_content_are_equal () {
        assertThat(int1, is(int1Spy));
    }

    @Test
    public void same_content_have_same_hashcode () {
        assertThat(int1.hashCode(), is(int1Spy.hashCode()));
    }

    @Test
    public void toString_works_correctly () {
        assertThat(int1.toString(),
            is(getClass().getSimpleName() + "." + int1.getClass().getSimpleName() + "[field=1]"));
    }

    @Test
    public void clone_equals_to_original () {
        assertThat(int1Clone, is(int1));
    }

    @Test
    public void clone_is_not_original () {
        assertThat(int1Clone, not(sameInstance(int1)));
    }

    @Test
    public void clone_is_deep_clone () {
        assertThat(int1Clone.field, not(sameInstance(int1.field)));
    }

    @Test
    public void deserialized_returns_an_equal_object () {
        assertThat(int1Deserialized, is(int1));
    }

    @Test
    public void deserialized_does_not_return_the_original () {
        assertThat(int1Deserialized, not(sameInstance(int1)));
    }

    @Test
    public void deserialized_have_different_field_instances () {
        assertThat(int1Deserialized.field, not(sameInstance(int1.field)));
    }
}
