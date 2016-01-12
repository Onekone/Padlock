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
    public void testGo1() throws Exception {assertEquals (0, tester.go (new String[]{}));};
    @Test
    public void testGo2() throws Exception {assertEquals (0, tester.go (new String[]{"-h"}));};
    @Test
    public void testGo3() throws Exception {assertEquals (1, tester.go (new String[]{"-login", "XXX", "-pass", "XXXX"}));};
    @Test
    public void testGo4() throws Exception {assertEquals (2, tester.go (new String[]{"-login", "jdoe", "-pass", "XXXX"}));};
    @Test
    public void testGo5() throws Exception {assertEquals (0, tester.go (new String[]{"-login", "jdoe", "-pass", "sup3rpaZZ"}));};
    @Test
    public void testGo6() throws Exception {assertEquals (0, tester.go (new String[]{"-login", "jdoe", "-pass", "sup3rpaZZ", "-role", "READ", "-res", "a"}));};
    @Test
    public void testGo7() throws Exception {assertEquals (0, tester.go (new String[]{"-login", "jdoe", "-pass", "sup3rpaZZ", "-role", "READ", "-res", "a.b"}));};
    @Test
    public void testGo8() throws Exception {assertEquals (3, tester.go (new String[]{"-login", "jdoe", "-pass", "sup3rpaZZ", "-role", "XXX", "-res", "a.b"}));};
    @Test
    public void testGo9() throws Exception {assertEquals (4, tester.go (new String[]{"-login", "jdoe", "-pass", "sup3rpaZZ", "-role", "READ", "-res", "XXX"}));};
    @Test
    public void testGo10() throws Exception {assertEquals (4, tester.go (new String[]{"-login", "jdoe", "-pass", "sup3rpaZZ", "-role", "WRITE", "-res", "a"}));};
    @Test
    public void testGo11() throws Exception {assertEquals (4, tester.go (new String[]{"-login", "jdoe", "-pass", "sup3rpaZZ", "-role", "WRITE", "-res", "a.bc"}));};
    @Test
    public void testGo12() throws Exception {assertEquals (0, tester.go (new String[]{"-login", "jdoe", "-pass", "sup3rpaZZ", "-role", "READ", "-res", "a.b", "-ds", "2015-01-01", "-de", "2015-12-31", "-vol", "100"}));};
    @Test
    public void testGo13() throws Exception {assertEquals (5, tester.go (new String[]{"-login", "jdoe", "-pass", "sup3rpaZZ", "-role", "READ", "-res", "a.b", "-ds", "01-01-2015", "-de", "2015-12-31", "-vol", "100"}));};
    @Test
    public void testGo14() throws Exception {assertEquals (5, tester.go (new String[]{"-login", "jdoe", "-pass", "sup3rpaZZ", "-role", "READ", "-res", "a.b", "-ds", "2015-01-01", "-de", "2015-12-31", "-vol", "XXX"}));};
    }



