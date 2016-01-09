package groupPadlock;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase
{
     /**
     * Create the test case
     *
     * @param testName name of the test case
     */
        public AppTest( String testName )
    {
        super( testName );
    }
    /**
     * @return the suite of tests being tested
     */
        public static Test suite()
        {
            return new TestSuite( AppTest.class );
        }
        /**
         * Rigourous Test :-)
         */
        public void testApp()
        {
            assertTrue( true );
        }

    public void testGo() throws Exception {
        App tester = new App();
        //tester.main(new String[]{});
        try {
            int i=0;
            System.out.println("-"+ (++i) +"---------------------");
            assertEquals (0, tester.go (new String[]{}));
            System.out.println("-"+ (++i) +"---------------------");
            assertEquals (0, tester.go (new String[]{"-h"}));
            System.out.println("-"+ (++i) +"---------------------");
            assertEquals (1, tester.go (new String[]{"-login", "XXX", "-pass", "XXXX"}));
            System.out.println("-"+ (++i) +"---------------------");
            assertEquals (2, tester.go (new String[]{"-login", "jdoe", "-pass", "XXXX"}));
            System.out.println("-"+ (++i) +"---------------------");
            assertEquals (0, tester.go (new String[]{"-login", "jdoe", "-pass", "sup3rpaZZ"}));
            System.out.println("-"+ (++i) +"---------------------");
            assertEquals (0, tester.go (new String[]{"-login", "jdoe", "-pass", "sup3rpaZZ", "-role", "READ", "-res", "a"}));
            System.out.println("-"+ (++i) +"---------------------");
            assertEquals (0, tester.go (new String[]{"-login", "jdoe", "-pass", "sup3rpaZZ", "-role", "READ", "-res", "a.b"}));
            System.out.println("-"+ (++i) +"---------------------");
            assertEquals (3, tester.go (new String[]{"-login", "jdoe", "-pass", "sup3rpaZZ", "-role", "XXX", "-res", "a.b"}));
            System.out.println("-"+ (++i) +"---------------------");
            assertEquals (4, tester.go (new String[]{"-login", "jdoe", "-pass", "sup3rpaZZ", "-role", "READ", "-res", "XXX"}));
            System.out.println("-"+ (++i) +"---------------------");
            assertEquals (4, tester.go (new String[]{"-login", "jdoe", "-pass", "sup3rpaZZ", "-role", "WRITE", "-res", "a"}));
            System.out.println("-"+ (++i) +"---------------------");
            assertEquals (4, tester.go (new String[]{"-login", "jdoe", "-pass", "sup3rpaZZ", "-role", "WRITE", "-res", "a.bc"}));
            System.out.println("-"+ (++i) +"---------------------");
            assertEquals (0, tester.go (new String[]{"-login", "jdoe", "-pass", "sup3rpaZZ", "-role", "READ", "-res", "a.b", "-ds", "2015-01-01", "-de", "2015-12-31", "-vol", "100"}));
            System.out.println("-"+ (++i) +"---------------------");
            assertEquals (5, tester.go (new String[]{"-login", "jdoe", "-pass", "sup3rpaZZ", "-role", "READ", "-res", "a.b", "-ds", "01-01-2015", "-de", "2015-12-31", "-vol", "100"}));
            System.out.println("-"+ (++i) +"---------------------");
            assertEquals (5, tester.go (new String[]{"-login", "jdoe", "-pass", "sup3rpaZZ", "-role", "READ", "-res", "a.b", "-ds", "2015-01-01", "-de", "2015-12-31", "-vol", "XXX"}));

        }
        catch (junit.framework.AssertionFailedError e) {System.out.println("Something went wrong");}
    }
}


