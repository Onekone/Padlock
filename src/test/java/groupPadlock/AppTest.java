package groupPadlock;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {
    App tester;
    @Before
    public void testBefore() throws Exception {
    tester = new App ();
    }

    @Test
    public void testGo() throws Exception {
        assertEquals (0, tester.go (new String[]{}));
        assertEquals (0, tester.go (new String[]{"-h"}));
        assertEquals (1, tester.go (new String[]{"-login", "XXX", "-pass", "XXXX"}));
        assertEquals (2, tester.go (new String[]{"-login", "jdoe", "-pass", "XXXX"}));
        assertEquals (0, tester.go (new String[]{"-login", "jdoe", "-pass", "sup3rpaZZ"}));
        assertEquals (0, tester.go (new String[]{"-login", "jdoe", "-pass", "sup3rpaZZ", "-role", "READ", "-res", "a"}));
        assertEquals (0, tester.go (new String[]{"-login", "jdoe", "-pass", "sup3rpaZZ", "-role", "READ", "-res", "a.b"}));
        assertEquals (3, tester.go (new String[]{"-login", "jdoe", "-pass", "sup3rpaZZ", "-role", "XXX", "-res", "a.b"}));
        assertEquals (4, tester.go (new String[]{"-login", "jdoe", "-pass", "sup3rpaZZ", "-role", "READ", "-res", "XXX"}));
        assertEquals (4, tester.go (new String[]{"-login", "jdoe", "-pass", "sup3rpaZZ", "-role", "WRITE", "-res", "a"}));
        assertEquals (4, tester.go (new String[]{"-login", "jdoe", "-pass", "sup3rpaZZ", "-role", "WRITE", "-res", "a.bc"}));
        assertEquals (0, tester.go (new String[]{"-login", "jdoe", "-pass", "sup3rpaZZ", "-role", "READ", "-res", "a.b", "-ds", "2015-01-01", "-de", "2015-12-31", "-vol", "100"}));
        assertEquals (5, tester.go (new String[]{"-login", "jdoe", "-pass", "sup3rpaZZ", "-role", "READ", "-res", "a.b", "-ds", "01-01-2015", "-de", "2015-12-31", "-vol", "100"}));
        assertEquals (5, tester.go (new String[]{"-login", "jdoe", "-pass", "sup3rpaZZ", "-role", "READ", "-res", "a.b", "-ds", "2015-01-01", "-de", "2015-12-31", "-vol", "XXX"}));
    }
}


