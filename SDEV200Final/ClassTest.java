public class ClassTest {
    public static void main(String[] args) {
        // Testing User class
        User user = new User() {};
        user.login("username123", "password123");

        // Testing Account class
        Account account = new Account();
        account.deposit(1000);
        account.withdraw(700);
        account.checkBalance();
        
        // Testing Transaction class
        Transaction transaction = new Transaction(account);
        transaction.makeTransaction(200, "withdraw");
        
        // Check balance after the transaction
        account.checkBalance();
    }
}