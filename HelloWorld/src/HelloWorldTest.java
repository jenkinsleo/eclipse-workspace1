import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

public class HelloWorldTest extends TestCase {
    @Test
    public void test() {
        assertEquals("Hello, Java!", HelloWorld.createGreeting("Java"));
        assertEquals("Hello, Mr. Gosling!", HelloWorld.createGreeting("Mr. Gosling"));
    }
}
