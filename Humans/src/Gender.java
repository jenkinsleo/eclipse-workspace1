
public enum Gender {
	MALE("He"),
	FEMALE("She");
	
	private String val;
	Gender(String string) {
		// TODO Auto-generated constructor stub
		this.val = string;
	}
	
	
	public String getVal() {
        return val;
    }
}
