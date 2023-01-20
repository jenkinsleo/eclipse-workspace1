//2020-02-10
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public class BankAccountExtendedTest extends junit.framework.TestCase
{        
    public void testDeposits()
    {                   
        //test various overloaded methods for deposits and withdrawals
    	BankAccount chequing1 = new BankAccount("chequing1");
        chequing1.setWithdrawalFee(3.0);
        
        chequing1.deposit(500.0);
        assertEquals(500.0,  chequing1.getBalance(), 0);

        chequing1.deposit(500.0, "deposit 2");
        assertEquals(1000.0,  chequing1.getBalance(), 0);

        chequing1.deposit(LocalDateTime.now(), 500.0, "deposit 3");
        assertEquals(1500.0,  chequing1.getBalance(), 0);

        chequing1.withdraw(1000.0);
        assertEquals(497.0,  chequing1.getBalance(), 0);            
        assertEquals(false,  chequing1.isOverDrawn());

        chequing1.withdraw(200.0, "withdrawal 2");
        assertEquals(294.0,  chequing1.getBalance(), 0);            
        assertEquals(false,  chequing1.isOverDrawn());
                    
        chequing1.withdraw(LocalDateTime.now(),300.0,"withdrawal 3");
        assertEquals(-9.0,  chequing1.getBalance(), 0);
        assertEquals(true,  chequing1.isOverDrawn());
        
        BankAccount chequing2 = new BankAccount("chequing2", 100);
        chequing2.deposit(0.42, "deposit 2");
        assertEquals(100.42,  chequing2.getBalance(), 0.00);
        chequing2.deposit(0.001,  "deposit 3");
        assertEquals(100.421,  chequing2.getBalance(), 0.001);
        
    }
    
    public void testGetTransactions()
    {
    	final String TRANSACTION_1 = "Transaction 1";
    	final String TRANSACTION_2 = "Transaction 2";
    	final String TRANSACTION_3 = "Transaction 3";
    	final String TRANSACTION_4 = "Transaction 4";
    	
    	LocalDateTime startTimeOfTest = LocalDateTime.now();
    	BankAccount chequing1 = new BankAccount("chequing1");    	
    	
        //note that these are out of chronological order!!
        LocalDateTime dateTime1 = LocalDateTime.of(LocalDate.of(1999, 12, 30), LocalTime.of(15, 15, 30));    	    	
    	LocalDateTime dateTime2 = LocalDateTime.of(LocalDate.of(2000, 01, 01), LocalTime.of(14, 15, 30));
    	LocalDateTime dateTime3 = LocalDateTime.of(LocalDate.of(1999, 12, 31), LocalTime.of(13, 15, 30));
    	LocalDateTime dateTime4 = LocalDateTime.of(LocalDate.of(2000, 01, 02), LocalTime.of(12, 15, 30));

    	//these dateTimes are used to test startTime and endTime on getTransactions()
    	LocalDateTime dateTime5 = dateTime3.plusSeconds(1);
    	LocalDateTime dateTime6 = dateTime2.minusSeconds(1);
    	LocalDateTime dateTime7 = LocalDateTime.of(LocalDate.of(1999, 12, 31), LocalTime.of(0, 0, 0));
    	LocalDateTime dateTime8 = LocalDateTime.of(LocalDate.of(2000, 01, 01), LocalTime.of(23, 59, 59));

        //create a number of transactions
    	chequing1.deposit(dateTime1, 600, TRANSACTION_1);
    	chequing1.withdraw(dateTime2, 200, TRANSACTION_2);
    	chequing1.withdraw(dateTime3, 300, TRANSACTION_3);
    	chequing1.deposit(dateTime4, 100, TRANSACTION_4);

    	ArrayList<Transaction> noBoundTransactionsList = chequing1.getTransactions(null, null);
    	assertEquals(4, noBoundTransactionsList.size());
    	//order should be: [Transaction 1, Transaction 3, Transaction 2, Transaction 1]
    	assertEquals(TRANSACTION_1, noBoundTransactionsList.get(0).getDescription());
    	assertEquals(TRANSACTION_3, noBoundTransactionsList.get(1).getDescription());
    	assertEquals(TRANSACTION_2, noBoundTransactionsList.get(2).getDescription());
    	assertEquals(TRANSACTION_4, noBoundTransactionsList.get(3).getDescription());

    	//test endTime argument
    	ArrayList<Transaction> endTimeBoundedTransactionsList = chequing1.getTransactions(null, dateTime3);
    	assertEquals(2, endTimeBoundedTransactionsList.size());
    	assertEquals(TRANSACTION_1, endTimeBoundedTransactionsList.get(0).getDescription());
    	assertEquals(TRANSACTION_3, endTimeBoundedTransactionsList.get(1).getDescription());

    	//test startTime argument
    	ArrayList<Transaction> startTimeBoundedtransactionsList = chequing1.getTransactions(dateTime2, null);
    	assertEquals(2, startTimeBoundedtransactionsList.size());
    	assertEquals(TRANSACTION_2, startTimeBoundedtransactionsList.get(0).getDescription());
    	assertEquals(TRANSACTION_4, startTimeBoundedtransactionsList.get(1).getDescription());
    	
    	//test both start and endTime argument
    	ArrayList<Transaction> bothTimeBoundedTransactionsList = chequing1.getTransactions(dateTime7, dateTime8);
    	assertEquals(2, bothTimeBoundedTransactionsList.size());    	    
    	assertEquals(TRANSACTION_3, bothTimeBoundedTransactionsList.get(0).getDescription());
    	assertEquals(TRANSACTION_2, bothTimeBoundedTransactionsList.get(1).getDescription());
    	
    	//test if transactions without dateTime and without description will be added to end of list
        chequing1.deposit(50);
        chequing1.withdraw(50);                
        //test if deposits and withdrawals without dateTime are still added to the list
        chequing1.deposit(50, "deposit 3");
        chequing1.withdraw(50, "withdrawal 3");
        ArrayList<Transaction> transactionsList5 = chequing1.getTransactions(null, null);
        assertEquals(8, transactionsList5.size());
        
        //test if transactions made without a given dateTime were given a dateTime equivalent to 'now'
        //it is enough to test if the given dateTime is not before the start of this test
        assertEquals(false, startTimeOfTest.isAfter(transactionsList5.get(4).getTransactionTime()));
        assertEquals(false, startTimeOfTest.isAfter(transactionsList5.get(5).getTransactionTime()));
        assertEquals(false, startTimeOfTest.isAfter(transactionsList5.get(6).getTransactionTime()));
        assertEquals(false, startTimeOfTest.isAfter(transactionsList5.get(7).getTransactionTime()));
        
        //test if transactions are returned in correct order
        for (int i = 0; i < transactionsList5.size() - 1; i++) {
            Transaction earlierTranscation = transactionsList5.get(i);
            Transaction laterTranscation = transactionsList5.get(i+1);
            assertEquals(false, (laterTranscation.getTransactionTime().isBefore(earlierTranscation.getTransactionTime())));            
        }
        


    }
   
}

