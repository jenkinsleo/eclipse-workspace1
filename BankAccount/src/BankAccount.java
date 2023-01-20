import java.util.*;
import java.time.LocalDateTime;

public class BankAccount {
	
	private static int accounts = 999999;
	private int accountId;
	
	private String accountName;
	private double accountBalance;
	private double withdrawalFee;
	private double annualInterestRate;
	private ArrayList<Transaction> transactions = new ArrayList<Transaction>();

	public BankAccount(String name, double balance, double fee, double interest) {
		this.accountName = this.truncate(name, 20);
		this.accountBalance = balance;
		this.withdrawalFee = fee;
		this.annualInterestRate = interest;
		this.accountId = this.setAccountId();
		
		
	}
	
	private int setAccountId() {
		accounts += 1;
		return accounts;
	}
	
	public BankAccount(String name, double balance) {
		this(name, balance, 0.00, 0.00);
	}
	public BankAccount(String name) {
		this(name, 0, 0.00, 0.00);
	}
	
	private String truncate(String value, int length)
	{
	  if (value != null && value.length() > length)
	    value = value.substring(0, length);
	  return value;
	}
	
	public String getAccountName() {
		return this.accountName;
	}
	
	public double getBalance() {
		return this.accountBalance;
	}
	
	public double getAnnualInterestRate() {
		return this.annualInterestRate;
	}
	
	public int getAccountID() {
		return this.accountId;
	}
	
	public static int getNextAccountID() {
		return accounts + 1;
	}
	
	public double getWithdrawalFee() {
		return this.withdrawalFee;
	}
	
	public void setWithdrawalFee(double fee) {
		this.withdrawalFee = fee;
	}
	public void setAnnualInterestRate(double rate) {
		this.annualInterestRate = rate;
	}
	//helper method for inserting into the array
	private void insertArray(Transaction transactionObject) {
		if (this.transactions.size() > 0) {
			for (int i = 0; i < this.transactions.size(); i ++) {
				Transaction current = this.transactions.get(i);
				LocalDateTime transactionObjectTime = transactionObject.getTransactionTime();
				LocalDateTime currentTime = current.getTransactionTime();
				if (transactionObjectTime.isAfter(currentTime)) {
					this.transactions.add(i, transactionObject);
				} else if(transactionObjectTime.isBefore(currentTime)) {
					this.transactions.add(i - 1, transactionObject);
				}
			}
		} else {
			this.transactions.add(transactionObject);
		}
		
	}
	
	public void deposit(double amount) {
		deposit(LocalDateTime.now(), amount, String.format("deposit"));
	}
	// overloading the deposits
	public void deposit(double amount, String description) {
		deposit(LocalDateTime.now(), amount, description);
	}
	public void deposit(LocalDateTime time, double amount, String description) {
		this.accountBalance += amount;
		Transaction transaction = new Transaction(time, amount, description);
		this.transactions.add(transaction);
	}
	
	//overloading the withdraws
	public void withdraw(double amount) {
		withdraw(LocalDateTime.now(), amount, String.format("withdraw"));
	}
	public void withdraw(double amount, String description) {
		withdraw(LocalDateTime.now(), amount, description);
	}
	public void withdraw(LocalDateTime time, double amount, String description) {
		this.accountBalance -= amount;
		this.accountBalance -= this.withdrawalFee;
		Transaction transaction = new Transaction(time, amount * -1, description);
		insertArray(transaction);
	}
	public boolean isOverDrawn() {
		if (this.accountBalance < 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public void addAnnualInterest() {
		if (this.isOverDrawn() == false) {
			this.accountBalance *= (this.annualInterestRate / 100) + 1;
		}
	}
	
	public String toString() {
		String balanceString = "";
		
		if (this.isOverDrawn() == true) {
			balanceString = String.format("($%.2f)", Math.abs(this.accountBalance));
		} else {
			balanceString = String.format("$%.2f", this.accountBalance);
		}
		
		return String.format("BankAccount: name = '%s'; balance = %s", this.accountName, balanceString);
	}
}
