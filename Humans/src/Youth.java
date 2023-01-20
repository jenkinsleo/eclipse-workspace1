
public class Youth extends Human{

	
	private int grade;
	private String school;
	public Youth(int year, int month, int day, String firstname, String lastname, Gender gender, int grade, String school) {
		super(year, month, day, firstname, lastname, gender);
		this.grade = grade;
		this.school = school;
	}
	
	
	public int getSchoolGrade() {
		return this.grade;
	}
	
	public String getSchoolName() {
		return this.school;
	}
	
	public void setGrade(int grade) {
		this.grade = grade;
	}
	
	public void setSchool(String school) {
		if (school != WilliamAberhartStudent.ABE_NAME ||  this.school == WilliamAberhartStudent.ABE_NAME) {
			
		
			this.school = school;
		}
	}
}
