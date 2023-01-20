package ex003;

//You are going to add a true/false type question to the question heirachy. Call it
//TrueFalseQuestion
//
//When the quiz is displayed, we want this text to preceed the question.
//  Answer True or False: question here
//We want the "Answer True or False: " to be prepended automatically.
//So if the user sets the text of the question to "Albert Einstein was the inventer of Java."
//the question will be displayed to the user like this
//   Answer True or False: Albert Einstein was the inventer of Java.
//
//Capitalization should not matter when determining if the answer is correct. Also t for true
//and f for false should be accepted.
//
//You will need to override three methods although not everyone will override the
//exact same three methods
//
//You will not need to add instance variables
//
//As a starting point, make the changes necessary to prepend the question submitted by the user with
// Answer True or False:

public class TrueFalseQuestion
{
// TODO: as a starting point, override the appropriate method(s) to present the question as specified
	String text;
	boolean answer;
	
	public TrueFalseQuestion() {
		
	}
	
	public void setAnswer(String ans) {
		
		System.out.println(ans);
	}
	
	public void setText(String txt) {
		this.text = txt;
	}
	
	public boolean checkAnswer(String ans) {
		System.out.println(String.format("%s, %s", ans.toLowerCase(), String.valueOf(this.answer)));
		
		
		if (ans.toLowerCase() == String.valueOf(this.answer)) {
			return true;
		} else {
			return false;
		}
	}
	
	public void display() {
		System.out.println(String.format("Answer True or False:", null));
	}

}
