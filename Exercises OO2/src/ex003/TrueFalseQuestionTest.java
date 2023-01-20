package ex003;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.Test;

import junit.framework.TestCase;

public class TrueFalseQuestionTest extends TestCase {

	public void testCorrectResponses() {
		
		TrueFalseQuestion tfq = new TrueFalseQuestion();

		//correct responses
		tfq.setText("Albert Einstein was the inventer of Java.");
		tfq.setAnswer("false");
		assertEquals(true, tfq.checkAnswer("false"));

		tfq.setText("The original name for Java was Oak.");
		tfq.setAnswer("true");
		assertEquals(true, tfq.checkAnswer("true"));
	}
	
	public void testCaseInsensitive() {

		TrueFalseQuestion tfq = new TrueFalseQuestion();

		//correct responses, case insensitive
		tfq.setText("Albert Einstein was the inventer of Java.");
		tfq.setAnswer("false");
		assertEquals(true, tfq.checkAnswer("fALSe"));

		tfq.setText("The original name for Java was Oak.");
		tfq.setAnswer("TrUE");
		assertEquals(true, tfq.checkAnswer("true"));
	}

	public void testOneLetterResponse() {

		TrueFalseQuestion tfq = new TrueFalseQuestion();

		//correct responses, one letter
		tfq.setText("Albert Einstein was the inventer of Java.");
		tfq.setAnswer("false");
		assertEquals(true, tfq.checkAnswer("f"));

		tfq.setText("The original name for Java was Oak.");
		tfq.setAnswer("TrUE");
		assertEquals(true, tfq.checkAnswer("t"));
	}

	public void testIncorrectResponse() {

		TrueFalseQuestion tfq = new TrueFalseQuestion();

		//incorrect responses
		tfq.setText("Albert Einstein was the inventer of Java.");
		tfq.setAnswer("true");
		assertEquals(false, tfq.checkAnswer("f"));

		tfq.setText("The original name for Java was Oak.");
		tfq.setAnswer("false");
		assertEquals(false, tfq.checkAnswer("t"));

	}
	
	 public void testDisplay() {
	      assertEquals(true,  testConsoleApplication("", "Answer True or False: Albert Einstein was the inventer of Java.\r\n$"));
	  }
	 

    private boolean testConsoleApplication(String input, String expectedOutput) {

        ByteArrayOutputStream myOut = new ByteArrayOutputStream();

        InputStream stdin = null;
        PrintStream stdout = null;
        String standardOutput = "";

		TrueFalseQuestion tfq = new TrueFalseQuestion();

		//correct responses
		tfq.setText("Albert Einstein was the inventer of Java.");
		tfq.setAnswer("false");
        
        try {
            stdin = System.in;
            stdout = System.out;

            System.setIn(new ByteArrayInputStream(input.getBytes()));
            System.setOut(new PrintStream(myOut));

            tfq.display();

           standardOutput = myOut.toString();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            System.setIn(stdin);
            System.setOut(stdout);
        }
        
        boolean result = standardOutput.matches(expectedOutput);

        System.out.println("--------------------------------------------------------------------------------");        
        System.out.println("input:" + input.replace("\n", ""));
        System.out.println("expected: " + expectedOutput.replace("\r\n", "\\r\\n"));
        System.out.println("returned: " + standardOutput.replace("\r\n", "\\r\\n"));
        System.out.println(result ? "PASS" : "FAIL");
        
        return result;
    }	
}
