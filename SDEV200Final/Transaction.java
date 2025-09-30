import java.util.Date;

public class Transaction {
    private int transactionId;
    private double amount;
    private Date date;
    private String type;
    private User user;
    private Account account;

    public Transaction(Account account) {
        this.account = account;
    }

    // Function for creating transactions
    public void makeTransaction(double amount, String type) {
        this.amount = amount;
        this.type = type;
        
        // Update account balance
        if (type.equals("deposit")) {
            // Update with deposited amount
            account.deposit(amount);
        } else if (type.equals("withdraw")) {
            // Update with withdrawn amount
            account.withdraw(amount);
        }
        
        System.out.println("Transaction completed successfully.");
    }
}