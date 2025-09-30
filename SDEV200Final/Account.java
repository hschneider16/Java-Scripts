public class Account extends User {
    private int accountId;
    private double balance;
    private String accountType;

    // Deposit function
    public void deposit(double amount) {
        balance += amount;
        System.out.println("Deposited: $" + amount);
    }

    // Withdrawal function
    public void withdraw(double amount) {
        // Withdraw if account has enough money
        if (balance >= amount) {
            balance -= amount;
            System.out.println("Withdrawn: $" + amount);
        } else {
            // Warn user of insufficient balance
            System.out.println("Insufficient balance.");
        }
    }

    // function for checking balance
    public void checkBalance() {
        System.out.println("Current balance: $" + balance);
    }
}