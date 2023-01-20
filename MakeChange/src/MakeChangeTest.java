//v5 2019.11.12

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MakeChangeTest {

    boolean isWindows = true;
	int testCount = 0;
	final String PROMPT = "?:";
	
	public MakeChangeTest() {
	    String os = System.getProperty("os.name");
	    isWindows = os.startsWith("Windows");
	}

    @Test
    public void test01() {
        assertEquals("toonies:0; loonies:0; quarters:1; dimes:1; nickels:1",  testConsoleApplication("0.41" )  );
    }    
    @Test
    public void test02() {
        assertEquals("toonies:0; loonies:0; quarters:1; dimes:1; nickels:1",  testConsoleApplication("0.41" )  );
    }
    @Test
    public void test03() {    
        assertEquals("toonies:0; loonies:0; quarters:1; dimes:2; nickels:0",  testConsoleApplication("0.44" )  );
    }
    @Test
    public void test04() {    
        assertEquals("toonies:0; loonies:1; quarters:1; dimes:0; nickels:1",  testConsoleApplication("1.31" )  );
    }
    @Test
    public void test05() {    
        assertEquals("toonies:1; loonies:0; quarters:1; dimes:0; nickels:1",  testConsoleApplication("2.31" )  );
    }
    @Test
    public void test06() {    
        assertEquals("toonies:1; loonies:1; quarters:1; dimes:0; nickels:1",  testConsoleApplication("3.31" )  );
    }
    @Test
    public void test07() {    
        assertEquals("toonies:9; loonies:1; quarters:0; dimes:1; nickels:1",  testConsoleApplication("19.15" )  );
    }
    @Test
    public void test08() {    
        assertEquals("toonies:0; loonies:0; quarters:0; dimes:0; nickels:0",  testConsoleApplication("0.00" )  );
    }
    @Test
    public void test09() {    
        assertEquals("toonies:0; loonies:0; quarters:0; dimes:0; nickels:0",  testConsoleApplication("0.01" )  );
    }
    @Test
    public void test10() {    
        assertEquals("toonies:0; loonies:0; quarters:0; dimes:0; nickels:0",  testConsoleApplication("0.02" )  );
    }
    @Test
    public void test11() {    
        assertEquals("toonies:0; loonies:0; quarters:0; dimes:0; nickels:1",  testConsoleApplication("0.03" )  );
    }
    @Test
    public void test12() {    
        assertEquals("toonies:0; loonies:0; quarters:0; dimes:0; nickels:1",  testConsoleApplication("0.04" )  );
    }
    @Test
    public void test13() {    
        assertEquals("toonies:0; loonies:0; quarters:0; dimes:0; nickels:1",  testConsoleApplication("0.05" )  );
    }
    @Test
    public void test14() {    
        assertEquals("toonies:0; loonies:0; quarters:0; dimes:0; nickels:1",  testConsoleApplication("0.06" )  );
    }
    @Test
    public void test15() {    
        assertEquals("toonies:0; loonies:0; quarters:0; dimes:0; nickels:1",  testConsoleApplication("0.07")  );
    }
    @Test
    public void test16() {    
        assertEquals("toonies:0; loonies:0; quarters:0; dimes:1; nickels:0",  testConsoleApplication("0.08" )  );
    }
    @Test
    public void test17() {    
        assertEquals("toonies:0; loonies:0; quarters:0; dimes:1; nickels:0",  testConsoleApplication("0.09" )  );
    }
    @Test
    public void test18() {    
        assertEquals("toonies:0; loonies:0; quarters:0; dimes:1; nickels:0",  testConsoleApplication("0.10" )  );
    }
    @Test
    public void test19() {    
        assertEquals("toonies:0; loonies:0; quarters:3; dimes:2; nickels:0",  testConsoleApplication("0.97" )  );
    }
    @Test
    public void test20() {    
        assertEquals("toonies:0; loonies:1; quarters:0; dimes:0; nickels:0",  testConsoleApplication("0.98" )  );
    }
    @Test
    public void test21() {    
        assertEquals("toonies:0; loonies:1; quarters:0; dimes:0; nickels:0",  testConsoleApplication("0.99" )  );
    }
    @Test
    public void test22() {    
        assertEquals("toonies:1; loonies:0; quarters:0; dimes:0; nickels:0",  testConsoleApplication("1.99")  );
    }
    @Test
    public void test23() {    
        assertEquals("toonies:0; loonies:0; quarters:0; dimes:0; nickels:0",  testConsoleApplication("0" )  );
    }
    @Test
    public void test24() {    
        assertEquals("INVALID",  testConsoleApplication("lots" )  );
    }
    @Test
    public void test25() {    
        assertEquals("INVALID",  testConsoleApplication("123.4t" )  );
    }
    @Test
    public void test26() {    
        assertEquals("INVALID",  testConsoleApplication("\n" )  );    
    }
    
    private String testConsoleApplication(String input) {

        ByteArrayOutputStream myOut = new ByteArrayOutputStream();

        InputStream stdin = null;
        PrintStream stdout = null;
        String standardOutput = "";

        testCount++;
        
        try {
            stdin = System.in;
            stdout = System.out;
            
            System.setIn(new ByteArrayInputStream(input.getBytes()));
            System.setOut(new PrintStream(myOut));

           MakeChange.main(null);           

           standardOutput = myOut.toString();
           if (isWindows) {
               //strip away the CR and the LF
               standardOutput = standardOutput.substring(0, standardOutput.length() - 2);
           }
           else {
               //strip away the LF
               standardOutput = standardOutput.substring(0, standardOutput.length() - 1);               
           }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            System.setIn(stdin);
            System.setOut(stdout);
        }
        
        int promptIndex = standardOutput.lastIndexOf(PROMPT);     
        String outputFromPrompt = (promptIndex == - 1 ? standardOutput : standardOutput.substring(promptIndex + PROMPT.length(), standardOutput.length()) );
//        String clippedOutput = standardOutput;
//        if (standardOutput.length() >= expectedOutput.length()) {
//            clippedOutput = standardOutput.substring(standardOutput.length() - expectedOutput.length() , standardOutput.length());
//        }
        
        return outputFromPrompt;
    }
    

}
