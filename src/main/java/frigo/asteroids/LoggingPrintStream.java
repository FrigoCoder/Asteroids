
package frigo.asteroids;

import java.io.PrintStream;

import org.apache.log4j.Logger;

public class LoggingPrintStream extends PrintStream {

    private static final Logger LOGGER = Logger.getLogger(LoggingPrintStream.class);

    public LoggingPrintStream (PrintStream out) {
        super(out);
    }

    @Override
    public void println (String string) {
        LOGGER.info(string);
    }

}
