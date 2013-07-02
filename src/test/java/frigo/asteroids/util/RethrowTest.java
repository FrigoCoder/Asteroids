
package frigo.asteroids.util;

import static frigo.asteroids.util.Rethrow.unchecked;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class RethrowTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void checked_exception_can_be_thrown_without_declaring_it () {
        thrown.expect(Exception.class);
        methodWithUndeclaredCheckedException();
    }

    private void methodWithUndeclaredCheckedException () {
        throw unchecked(new Exception());
    }

    @Test
    public void checked_exception_can_be_rethrown_without_declaring_it () {
        thrown.expect(Exception.class);
        methodThatRethrowsACheckedException();
    }

    private void methodThatRethrowsACheckedException () {
        try{
            methodWithDeclaredCheckedException();
        }catch( Exception e ){
            throw unchecked(e);
        }
    }

    private void methodWithDeclaredCheckedException () throws Exception {
        throw new Exception();
    }

}
