package ex006;
// This is provided for reference. You cannot modify this file!

/**
   A question with a text and an answer.
*/
public class Question
{
    private String text;
    private String answer;

    public Question()
    {
        text = "";
        answer = "";
    }

    //accessors
    public String getText()
	{
	 	return text;
	}
    
    public String getAnswer() {
    	return answer;
    }

    public boolean checkAnswer(String response)
    {
        return response.equals(answer);
    }
	    
    //mutators
    public final void setText(String questionText)
    {
        text = questionText;
    }

    public final void setAnswer(String correctResponse)
    {
        answer = correctResponse;
    }

    
	//This method is here to ensure that that the superclass's instance variables are being used instead of
    //instance variables in subclasses. You would not want to have different answers at different layers of 
    //your hierarchy.
    public final void validation(String code) {
    	this.text = code;
    	this.answer = code;
    }

}
