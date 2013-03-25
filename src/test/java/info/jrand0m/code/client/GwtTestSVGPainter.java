package info.jrand0m.code.client;

import com.google.gwt.junit.client.GWTTestCase;
import org.junit.Ignore;

/**
 * GWT JUnit <b>integration</b> tests must extend GWTTestCase.
 * Using <code>"GwtTest*"</code> naming pattern exclude them from running with
 * surefire during the test phase.
 * <p/>
 * If you run the tests using the Maven command line, you will have to
 * navigate with your browser to a specific url given by Maven.
 * See http://mojo.codehaus.org/gwt-maven-plugin/user-guide/testing.html
 * for details.
 */

public class GwtTestSVGPainter extends GWTTestCase {

    /**
     * Must refer to a valid module that sources this class.
     */
    public String getModuleName() {
        return "info.jrand0m.code.SVGPainterJUnit";
    }

    /**
     * Tests the FieldVerifier.
     */
    public void testFieldVerifier() {
        assertFalse("TODO: make tests", true);
    }


}
