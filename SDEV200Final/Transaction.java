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

    public void makeTransaction(double amount, String type) {
        this.amount = amount;
        this.type = type;
        
        // Update account balance
        if (type.equals("deposit")) {
            account.deposit(amount);
        } else if (type.equals("withdraw")) {
            account.withdraw(amount);
        }
        
        System.out.println("Transaction completed successfully.");
    }
}