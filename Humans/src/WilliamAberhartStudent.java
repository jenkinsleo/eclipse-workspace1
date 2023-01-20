
public class WilliamAberhartStudent extends Youth{
	public static String ABE_NAME = "William Aberhart High School";
	
	private int homeroom;
	private String teacher;

	public WilliamAberhartStudent(int year, int month, int day, String firstname, String lastname, Gender gender,
			int grade, int homeroom, String teacher) {
		super(year, month, day, firstname, lastname, gender, grade, ABE_NAME);
		
		this.homeroom = homeroom;
		this.teacher = teacher;
		// TODO Auto-generated constructor stub
	}
	
	public int getHomeRoom() {
		return this.homeroom;
	}
	
	public String getHomeRoomTeacher() {
		return this.teacher;
	}
	
	public void setHomeRoom(int r) {
		this.homeroom = r;
	}
	
	public void setHomeRoomTeacher(String teach) {
		this.teacher = teach;
	}
}
