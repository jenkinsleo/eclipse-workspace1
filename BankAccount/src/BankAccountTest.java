//2021-04-15 (for JUnit4)
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class BankAccountTest
{
	static BankAccount first;
	static BankAccount second;
	static BankAccount third;

	@Before
	public void setup() {
		
		if (first == null) {
			first = new BankAccount("first");
		}
		if (second == null) {
			second = new BankAccount("second",0);
		}
		if (third == null) {
			third = new BankAccount("third",0,0,0);
		}
	}

	@Test
	public void test01Accessors()
	{
		BankAccount chequing1 = new BankAccount("chequing1");        
		BankAccount chequing2 = new BankAccount("chequing2", 100);
		BankAccount saving1 = new BankAccount("saving1", 100, 2.00, 5.0);

		assertEquals("chequing1",  chequing1.getAccountName());
		assertEquals("chequing2",  chequing2.getAccountName());
		assertEquals("saving1",  saving1.getAccountName());

		assertEquals(0,  chequing1.getBalance(), 0.00);
		assertEquals(100.0,  chequing2.getBalance(), 0.00);
		assertEquals(100.0,  saving1.getBalance(), 0.00);

		assertEquals(0,  chequing1.getWithdrawalFee(), 0.00);
		assertEquals(0,  chequing2.getWithdrawalFee(), 0.00);
		assertEquals(2.0,  saving1.getWithdrawalFee(), 0.00);

		assertEquals(0,  chequing1.getAnnualInterestRate(), 0.00);
		assertEquals(0,  chequing2.getAnnualInterestRate(), 0.00);
		assertEquals(5.0,  saving1.getAnnualInterestRate(), 0.00);

		BankAccount longName1 = new BankAccount("A really long name that won't fit");
		BankAccount longName2 = new BankAccount("Some other really long name that won't fit", 100);
		BankAccount longName3 = new BankAccount("Yet another really long name", 100, 2.00, 5.0);
		
		assertEquals("A really long name t",  longName1.getAccountName());
		assertEquals("Some other really lo",  longName2.getAccountName());
		assertEquals("Yet another really l",  longName3.getAccountName());
	}

	@Test
	public void test02Mutators()
	{
		BankAccount chequing1 = new BankAccount("chequing1");        

		//test for setters
		chequing1.setWithdrawalFee(3.0);
		assertEquals(3.0,  chequing1.getWithdrawalFee(), 0.00);

		chequing1.setAnnualInterestRate(4.0);
		assertEquals(4.0,  chequing1.getAnnualInterestRate(), 0.00);
	}

	@Test
	public void test03Deposits()
	{                	
		BankAccount chequing1 = new BankAccount("chequing1");      
		BankAccount chequing2 = new BankAccount("chequing2", 100);

		//test various deposits and withdrawals
		chequing1.setWithdrawalFee(3.0);

		chequing1.deposit(500);
		assertEquals(500.0,  chequing1.getBalance(), 0.00);

		chequing1.withdraw(200);
		assertEquals(297.0,  chequing1.getBalance(), 0.00);			
		assertEquals(false,  chequing1.isOverDrawn());

		chequing1.withdraw(300);
		assertEquals(-6.0,  chequing1.getBalance(), 0.00);
		assertEquals(true,  chequing1.isOverDrawn());

		chequing2.deposit(0.42);
		assertEquals(100.42,  chequing2.getBalance(), 0.00);

		//test if a deposit of a fraction of a cent is still stored
		chequing2.deposit(0.001);
		assertEquals(100.421,  chequing2.getBalance(), 0.0001);

		chequing2.deposit(0.005);
		assertEquals(100.426,  chequing2.getBalance(), 0.0001);

	}

	@Test
	public void test04ToString() {    	

		BankAccount chequing2 = new BankAccount("chequing2", 100);

		assertEquals("BankAccount: name = 'chequing2'; balance = $100.00",  chequing2.toString());

		chequing2.withdraw(100);
		assertEquals("BankAccount: name = 'chequing2'; balance = $0.00",  chequing2.toString());

		chequing2.withdraw(100);
		assertEquals("BankAccount: name = 'chequing2'; balance = ($100.00)",  chequing2.toString());

		chequing2.deposit(200);
		assertEquals("BankAccount: name = 'chequing2'; balance = $100.00",  chequing2.toString());

		chequing2.deposit(0.42);
		assertEquals("BankAccount: name = 'chequing2'; balance = $100.42",  chequing2.toString());

		//test rounding after a deposit of a fraction of a cent
		chequing2.deposit(0.001);
		assertEquals("BankAccount: name = 'chequing2'; balance = $100.42",  chequing2.toString());

		//test rounding after a deposit of a fraction of a cent
		chequing2.deposit(0.005);
		assertEquals("BankAccount: name = 'chequing2'; balance = $100.43",  chequing2.toString());

		BankAccount longName1 = new BankAccount("A really long name that won't fit", 100);
		assertEquals("BankAccount: name = 'chequing2'; balance = $100.43",  chequing2.toString());
		
	}

	@Test
	public void test05AddAnnualInterest() {

		BankAccount saving1 = new BankAccount("saving1", 100, 2.00, 5.0);
		BankAccount saving2 = new BankAccount("saving2",-100, 2.00, 5.0);

		saving1.addAnnualInterest();
		assertEquals(105.00,  saving1.getBalance(), 0.00);

		saving1.addAnnualInterest();
		assertEquals(110.25,  saving1.getBalance(), 0.00);

		saving2.addAnnualInterest();
		assertEquals(-100,  saving2.getBalance(), 0.00);

	}

	@Test
	public void test06NextAccountID() {
		
		assertEquals(1000000, first.getAccountID());
		assertEquals(1000001, second.getAccountID());
		assertEquals(1000002, third.getAccountID());

		long nextSerialNumber = BankAccount.getNextAccountID();
		//test accessor twice to ensure that it doesn't mutate the class
		assertEquals(nextSerialNumber, BankAccount.getNextAccountID());
		BankAccount newAccount1 = new BankAccount("newAccount1");
		assertEquals(nextSerialNumber, newAccount1.getAccountID());
		//test accessor twice to ensure it doesn't mutate the instance
		assertEquals(nextSerialNumber, newAccount1.getAccountID());

		BankAccount newAccount2 = new BankAccount("newAccount2",0);
		assertEquals(nextSerialNumber + 1, newAccount2.getAccountID());

		BankAccount newAccount3 = new BankAccount("newAccount3",0,0,0);
		assertEquals(nextSerialNumber + 2, newAccount3.getAccountID());
	}


}

