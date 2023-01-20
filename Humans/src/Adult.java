public class Adult extends Human {
	
	private String placeofwork;
	private String occupation;
	public Adult(int year, int month, int day, String firstname, String lastname, Gender gender, String placeofwork, String occupation) {
		super(year, month, day, firstname, lastname, gender);
		// TODO Auto-generated constructor stub
		this.placeofwork = placeofwork;
		this.occupation = occupation;
	}
	
	public String getOccupation() {
		return this.occupation;
	}
	
	public String getPlaceOfWork() {
		return this.placeofwork;
	}
	
	public void setOccupation(String newo) {
		this.occupation = newo;
	}
	
	public void setPlaceOfWork(String newo) {
		this.placeofwork = newo;
	}
}