import java.time.LocalDate;
import java.time.Period;

public class Human {
	private int year;
	private int month;
	private int day;
	private String firstname;
	private String lastname;
	private Gender gender;

	public Human(int year, int month, int day, String firstname, String lastname, Gender gender) {
		this.year = year;
		this.month = month;
		this.day = day;
		this.firstname = firstname;
		this.lastname = lastname;
		this.gender = gender;
		
	}
	
	public int getBirthYear() {
		return this.year;
	}
	
	public int getBirthMonth() {
		return this.month;
		
	}
	
	public int getBirthDay() {
		return this.day;
	}
	
	public String getFirstName() {
		return this.firstname;
	}
	
	public String getLastName() {
		return this.lastname;
	}
	
	public void setFirstName(String name) {
		this.firstname = name;
	}
	
	public void setLastName(String name) {
		this.lastname = name;
	}
	
	public Gender getGender() {
		return this.gender;
		
	}
	
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	
	public int calculateCurrentAgeInYears() {
		LocalDate today = LocalDate.now();
		LocalDate birthDate = LocalDate.of(this.year, this.month, this.day);
		int age = Period.between(birthDate, today).getYears();
		return age;
	}
}
