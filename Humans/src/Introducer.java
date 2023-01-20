
public class Introducer {

	public String createPublicIntroduction(Human person) {
		String intro;
		
		try {
			intro = String.format("I am pleased to introduce %s %s.", person.getFirstName(), person.getLastName());
			
			if (person instanceof Adult) {
				intro += String.format(" %s works at %s and their occupation is %s.", person.getGender().getVal(), ((Adult) person).getPlaceOfWork(), (((Adult) person).getOccupation()));
			}
			
			if (person instanceof Youth) {
				intro += String.format(" %s goes to %s and is in grade %d.", person.getGender().getVal(), ((Youth) person).getSchoolName(), ((Youth) person).getSchoolGrade());
				
				
			} 
			
			if (person instanceof WilliamAberhartStudent) {
				if (((WilliamAberhartStudent) person).getHomeRoomTeacher() == null) {
					intro += " Their homeroom is unknown at this time.";
					
				} else {
					intro += String.format(" %s belongs to %s's homeroom, which is in room %d.", person.getGender().getVal(), ((WilliamAberhartStudent) person).getHomeRoomTeacher(), ((WilliamAberhartStudent) person).getHomeRoom());
				}
			}
			
		} catch (Exception e) 
		{
			intro = "This person has issues and can't be introduced.";
		}
		
		
		
		
		return intro;
	}
}
