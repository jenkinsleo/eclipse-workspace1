package ex006;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class TrueFalseQuestionTest {

	@Test
	public void testResponse() {

		TrueFalseQuestion tfq = new TrueFalseQuestion();

		//incorrect responses
		tfq.setText("James Gosling is an alumni of William Aberhart High School.");
		tfq.setAnswer("true");
		assertEquals(true, tfq.checkAnswer("True"));
		assertEquals(true, tfq.checkAnswer("true"));
		assertEquals(true, tfq.checkAnswer("tRuE"));
		assertEquals(true, tfq.checkAnswer("t"));
		assertEquals(true, tfq.checkAnswer("T"));
		assertEquals(false, tfq.checkAnswer("f"));

		tfq.setText("The original name for Java was Oak.");
		tfq.setAnswer("false");
		assertEquals(true, tfq.checkAnswer("False"));
		assertEquals(true, tfq.checkAnswer("false"));
		assertEquals(true, tfq.checkAnswer("fAlSe"));
		assertEquals(true, tfq.checkAnswer("f"));
		assertEquals(true, tfq.checkAnswer("F"));
		assertEquals(false, tfq.checkAnswer("t"));

	}

	@Test
	public void testGetText() {
		TrueFalseQuestion tfq = new TrueFalseQuestion();

		tfq.setText("Albert Einstein was the inventer of Java.");
		assertEquals("Answer True or False: Albert Einstein was the inventer of Java.", tfq.getText());
		
		tfq.setText("The original name for Java was Oak.");
		assertEquals("Answer True or False: The original name for Java was Oak.", tfq.getText());
		
	}
	
	@Test
	public void testValidation() {
		
		//this test is here to keep you honest!
		
		TrueFalseQuestion tfq = new TrueFalseQuestion();
		
		//check if tqf indeed inherits from Question
		assertEquals(true, tfq instanceof Question);
		assertEquals(false, tfq.getClass().equals(Question.class));
				

		//the Question class has a backdoor method that resets the instance variables in the superclass
		//this checks that you are using the superclass's version of these variables
		tfq.validation("test");		
		assertEquals("Answer True or False: test", tfq.getText());
		assertEquals("test", tfq.getAnswer());
				
		tfq.validation("other");		
		assertEquals("Answer True or False: other", tfq.getText());
		assertEquals("other", tfq.getAnswer());
		
		
		
	}

}
