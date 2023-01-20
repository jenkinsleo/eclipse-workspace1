import java.time.LocalDateTime;
public class Transaction {

    private LocalDateTime transactionTime;
    private double amount;
    private String description;
    
    public Transaction(LocalDateTime transactionTime, double amount, String description){
        this.transactionTime = transactionTime;
        this.amount = amount;
        this.description = description;
    }
        
    public Transaction(double amount, String description){
            this.transactionTime = LocalDateTime.now();
            this.amount = amount; 
            this.description = description; 
    }
    
    public String getDescription(){
        return description;
    }
    
    public LocalDateTime getTransactionTime(){
        return transactionTime;
    }
    
    public double getAmount(){
        return amount;
    }
    
    public String toString() {
    	return String.format("%s: time = %s; amount = %.2f; description = %s", this.getClass().toString(), this.transactionTime.toString(), this.amount, this.description);
    }
    
    public Transaction clone() {
        return this;
    }
}