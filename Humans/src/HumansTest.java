//2021/10/02
import java.time.LocalDate;
import java.time.LocalDateTime;

import junit.framework.TestCase;

public class HumansTest extends TestCase {

	final static int SIMPSONS_BASE_DATE = 1992;
	int dateAdjustment = 0;

	Human burns;
	Human barney;
	Adult homer;
	Adult otto;
	Youth lisa;
	Youth bart;
	WilliamAberhartStudent nemo;
	WilliamAberhartStudent gosling;

	private void createSimpsons() {

		dateAdjustment = LocalDateTime.now().getYear() - SIMPSONS_BASE_DATE;

		burns = new Human(1888 + dateAdjustment, 03, 11, "Montgomery", "Burns", Gender.MALE);
		barney = new Human(1953 + dateAdjustment, 04, 20, "Barney", "Gumble", Gender.MALE);
		homer = new Adult(1953 + dateAdjustment, 04, 19, "Homer", "Simpson", Gender.MALE,
				"Springfield Nuclear Power Plant", "Nuclear Safety Inspector");
		otto = new Adult(1963  + dateAdjustment, 01, 18, "Otto", "Mann", Gender.MALE,
				"Springfield Elementary School", "Bus Driver");
		bart = new Youth(1982 + dateAdjustment, 02, 03, "Bart", "Simpson", Gender.MALE, 4,
				"Springfield Elementary");
		lisa = new Youth(1984 + dateAdjustment, 07, 28, "Lisa", "Simpson", Gender.FEMALE, 2,
				"Springfield Elementary");
		nemo = new WilliamAberhartStudent(1972 + dateAdjustment, 04, 26,
				"Sine", "Nomine", Gender.FEMALE, 11, 310, "Wehnes");
		gosling = new WilliamAberhartStudent(1955, 05, 19,
				"James", "Gosling", Gender.MALE, 12, 310, null);

		//author's note: yes, I did the research on these birthdates :-)
	}

	public void testConstructors() {
		createSimpsons();

		//should not fail; these are more to ensure code compilation
		assertEquals(true, burns != null);
		assertEquals(true, homer != null);
		assertEquals(true, lisa != null);
		assertEquals(true, nemo != null);
	}

	public void testAccessors() {
		createSimpsons();

		assertEquals(1888 + dateAdjustment, burns.getBirthYear());
		assertEquals(3, burns.getBirthMonth());
		assertEquals(11, burns.getBirthDay());
		assertEquals("Montgomery", burns.getFirstName());
		assertEquals("Burns", burns.getLastName());

		assertEquals(1953 + dateAdjustment, barney.getBirthYear());
		assertEquals(4, barney.getBirthMonth());
		assertEquals(20, barney.getBirthDay());
		assertEquals("Barney", barney.getFirstName());
		assertEquals("Gumble", barney.getLastName());

		assertEquals(1953 + dateAdjustment, homer.getBirthYear());
		assertEquals(4, homer.getBirthMonth());
		assertEquals(19, homer.getBirthDay());
		assertEquals("Homer", homer.getFirstName());
		assertEquals("Simpson", homer.getLastName());
		assertEquals("Nuclear Safety Inspector", homer.getOccupation());
		assertEquals("Springfield Nuclear Power Plant", homer.getPlaceOfWork());

		assertEquals(1963 + dateAdjustment, otto.getBirthYear());
		assertEquals(1, otto.getBirthMonth());
		assertEquals(18, otto.getBirthDay());
		assertEquals("Otto", otto.getFirstName());
		assertEquals("Mann", otto.getLastName());
		assertEquals("Bus Driver", otto.getOccupation());
		assertEquals("Springfield Elementary School", otto.getPlaceOfWork());

		assertEquals(1982 + dateAdjustment, bart.getBirthYear());
		assertEquals(2, bart.getBirthMonth());
		assertEquals(3, bart.getBirthDay());
		assertEquals("Bart", bart.getFirstName());
		assertEquals("Simpson",bart.getLastName());
		assertEquals(4, bart.getSchoolGrade());
		assertEquals("Springfield Elementary", bart.getSchoolName());

		assertEquals(1984 + dateAdjustment, lisa.getBirthYear());
		assertEquals(7, lisa.getBirthMonth());
		assertEquals(28, lisa.getBirthDay());
		assertEquals("Lisa", lisa.getFirstName());
		assertEquals("Simpson", lisa.getLastName());
		assertEquals(2, lisa.getSchoolGrade());
		assertEquals("Springfield Elementary", lisa.getSchoolName());

		assertEquals(1972 + dateAdjustment, nemo.getBirthYear());
		assertEquals(04, nemo.getBirthMonth());
		assertEquals(26, nemo.getBirthDay());
		assertEquals("Sine", nemo.getFirstName());
		assertEquals("Nomine", nemo.getLastName());
		assertEquals(11, nemo.getSchoolGrade());
		assertEquals("William Aberhart High School", nemo.getSchoolName());

		assertEquals(1955, gosling.getBirthYear());
		assertEquals(05, gosling.getBirthMonth());
		assertEquals(19, gosling.getBirthDay());
		assertEquals("James", gosling.getFirstName());
		assertEquals("Gosling", gosling.getLastName());
		assertEquals(12, gosling.getSchoolGrade());
		assertEquals("William Aberhart High School", gosling.getSchoolName());
	}

	public void testMutators() {
		createSimpsons();

		burns.setFirstName("Monty");
		burns.setLastName("Burnsy");
		assertEquals("Monty", burns.getFirstName());
		assertEquals("Burnsy", burns.getLastName());

		homer.setFirstName("Ned");
		homer.setLastName("Flanders");
		assertEquals("Ned", homer.getFirstName());
		assertEquals("Flanders", homer.getLastName());
	}

	public void testTypes() {
		createSimpsons();

		Human person1 = nemo;		
		assertEquals(true, person1 instanceof Human);
		assertEquals(true, person1 instanceof Youth);
		assertEquals(true, person1 instanceof WilliamAberhartStudent);
		assertEquals(false, person1 instanceof Adult);

		Human person2 = homer;		
		assertEquals(true, person2 instanceof Human);
		assertEquals(false, person2 instanceof Youth);
		assertEquals(false, person2 instanceof WilliamAberhartStudent);
		assertEquals(true, person2 instanceof Adult);

		Human person3 = burns;		
		assertEquals(true, person3 instanceof Human);
		assertEquals(false, person3 instanceof Youth);
		assertEquals(false, person3 instanceof WilliamAberhartStudent);
		assertEquals(false, person3 instanceof Adult);

	}


	public void testCalculateCurrentAge() {
		;
		Adult bloggins;
		LocalDateTime birthDate;
		LocalDateTime currentDate = LocalDateTime.now();

		birthDate = currentDate.minusYears(40).minusMonths(1).minusDays(3);
		//should be 40 years 1 month 3 days old
		bloggins = new Adult(birthDate.getYear(), birthDate.getMonthValue(), birthDate.getDayOfMonth(), "Bill", "Bloggins", Gender.MALE,
				"RCN", "Sailor");		
		assertEquals(40, bloggins.calculateCurrentAgeInYears());

		birthDate = currentDate.minusYears(40).minusDays(1);
		//should be 40 years 1 day old
		bloggins = new Adult(birthDate.getYear(), birthDate.getMonthValue(), birthDate.getDayOfMonth(), "Bill", "Bloggins", Gender.MALE,
				"RCN", "Sailor");		
		assertEquals(40, bloggins.calculateCurrentAgeInYears());

		birthDate = currentDate.minusYears(40);
		//should be exactly 40 years old
		bloggins = new Adult(birthDate.getYear(), birthDate.getMonthValue(), birthDate.getDayOfMonth(), "Bill", "Bloggins", Gender.MALE,
				"RCN", "Sailor");		
		assertEquals(40, bloggins.calculateCurrentAgeInYears());

		birthDate = currentDate.minusYears(40).plusMonths(1);
		//should be 39 years 11 months old
		bloggins = new Adult(birthDate.getYear(), birthDate.getMonthValue(), birthDate.getDayOfMonth(), "Bill", "Bloggins", Gender.MALE,
				"RCN", "Sailor");		
		assertEquals(39, bloggins.calculateCurrentAgeInYears());

		birthDate = currentDate.minusYears(40).plusDays(1);
		//should be 39 years 364 days old
		bloggins = new Adult(birthDate.getYear(), birthDate.getMonthValue(), birthDate.getDayOfMonth(), "Bill", "Bloggins", Gender.MALE,
				"RCN", "Sailor");		
		assertEquals(39, bloggins.calculateCurrentAgeInYears());

		birthDate = currentDate.minusYears(40).plusMonths(1).minusDays(3);
		//should be 39 years 11 months 25-28 days old
		bloggins = new Adult(birthDate.getYear(), birthDate.getMonthValue(), birthDate.getDayOfMonth(), "Bill", "Bloggins", Gender.MALE,
				"RCN", "Sailor");		
		assertEquals(39, bloggins.calculateCurrentAgeInYears());

		if (currentDate.getMonthValue() != 12 && currentDate.getDayOfMonth() != 31) {
			birthDate =  LocalDate.of(currentDate.getYear() - 65, 12, 31).atStartOfDay();
			//age should be always be one year less than the difference between birth year and current year
			bloggins = new Adult(currentDate.getYear() - 65, birthDate.getMonthValue(), birthDate.getDayOfMonth(), "Bill", "Bloggins", Gender.MALE,
					"RCN", "Sailor");		
			assertEquals(currentDate.getYear() - birthDate.getYear() - 1  , bloggins.calculateCurrentAgeInYears());
		} else {
			System.out.println("We unfortunately can't test today whether the age is calculated correctly!");
			System.out.println("Enjoy new year's eve and come back tomorrow");			
		}


	}

	public void testPublicIntroduction() {
		createSimpsons();

		String BURNS_INDTRODUCTION = "I am pleased to introduce Montgomery Burns.";
		String BARNEY_INDTRODUCTION = "I am pleased to introduce Barney Gumble.";
		String HOMER_INDTRODUCTION = "I am pleased to introduce Homer Simpson. He works at Springfield Nuclear Power Plant and their occupation is Nuclear Safety Inspector.";
		String OTTO_INDTRODUCTION = "I am pleased to introduce Otto Mann. He works at Springfield Elementary School and their occupation is Bus Driver.";
		String BART_INDTRODUCTION = "I am pleased to introduce Bart Simpson. He goes to Springfield Elementary and is in grade 4.";
		String LISA_INDTRODUCTION = "I am pleased to introduce Lisa Simpson. She goes to Springfield Elementary and is in grade 2.";
		String NEMO_INDTRODUCTION = "I am pleased to introduce Sine Nomine. She goes to William Aberhart High School and is in grade 11. She belongs to Wehnes's homeroom, which is in room 310.";
		String GOSLING_INDTRODUCTION = "I am pleased to introduce James Gosling. He goes to William Aberhart High School and is in grade 12. Their homeroom is unknown at this time.";

		Introducer introducer = new Introducer();

		System.out.println(introducer.createPublicIntroduction(burns));
		assertEquals(BURNS_INDTRODUCTION, introducer.createPublicIntroduction(burns));

		System.out.println(introducer.createPublicIntroduction(barney));
		assertEquals(BARNEY_INDTRODUCTION, introducer.createPublicIntroduction(barney));

		System.out.println(introducer.createPublicIntroduction(homer));		
		assertEquals(HOMER_INDTRODUCTION, introducer.createPublicIntroduction(homer));

		System.out.println(introducer.createPublicIntroduction(otto));
		assertEquals(OTTO_INDTRODUCTION, introducer.createPublicIntroduction(otto));

		System.out.println(introducer.createPublicIntroduction(bart));
		assertEquals(BART_INDTRODUCTION, introducer.createPublicIntroduction(bart));

		System.out.println(introducer.createPublicIntroduction(lisa));
		assertEquals(LISA_INDTRODUCTION, introducer.createPublicIntroduction(lisa));

		System.out.println(introducer.createPublicIntroduction(nemo));
		assertEquals(NEMO_INDTRODUCTION, introducer.createPublicIntroduction(nemo));

		System.out.println(introducer.createPublicIntroduction(gosling));
		assertEquals(GOSLING_INDTRODUCTION, introducer.createPublicIntroduction(gosling));

		ConfusedNameHuman heywood = new ConfusedNameHuman(1990, 1, 1, "Hugh", "Jazz", Gender.MALE);
		System.out.println(introducer.createPublicIntroduction(heywood));
		assertEquals("This person has issues and can't be introduced.", introducer.createPublicIntroduction(heywood));

	}

	//a Human who doesn't know their own name...
	class ConfusedNameHuman extends Human {

		String[] names;

		public ConfusedNameHuman(int birthYear, int birthMonth, int birthDay, String firstName, String lastName , Gender gender) 
		{
			super(birthYear, birthMonth, birthDay, firstName, lastName, gender);
			names = new String[2];
			names[1] = firstName;
			names[0] = lastName;
		}

		public String getFirstName() {
			return names[2];
		}

	}
	
	//a Human who doesn't know their own age...
	class ConfuseAgedHuman extends Human {

		String[] names;

		public ConfuseAgedHuman(int birthYear, int birthMonth, int birthDay, String firstName, String lastName , Gender gender) 
		{
			super(birthYear, birthMonth, birthDay, firstName, lastName, gender);
			names = new String[2];
			names[1] = firstName;
			names[0] = lastName;
		}

		public int calculateCurrentAgeInYears() 
		{
			return 16 / 0;
		}

	}	
	
}
