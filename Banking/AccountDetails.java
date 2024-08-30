import java.util.InputMismatchException;
import java.util.Scanner;

public class AccountDetails {
    private String accNo;
    private String accType;
    private String holderName;
    private long balance;
    private static Scanner scanner = new Scanner(System.in);

    public void createAccount() {
        try {
            System.out.print("Enter Account Number: ");
            accNo = scanner.next();

            System.out.print("Enter Account Type: ");
            accType = scanner.next();

            System.out.print("Enter Holder Name: ");
            holderName = scanner.next();

            System.out.print("Enter Initial Balance: ");
            balance = scanner.nextLong();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid number for the balance.");
            scanner.next(); // Clear the invalid input
        }
    }

    public void displayAccount() {
        System.out.println("Account Number: " + accNo);
        System.out.println("Account Type: " + accType);
        System.out.println("Holder Name: " + holderName);
        System.out.println("Balance: " + balance);
    }

    public boolean findAccount(String accountNumber) {
        if (accNo.equals(accountNumber)) {
            displayAccount();
            return true;
        }
        return false;
    }

    public void addFunds() {
        try {
            System.out.print("Enter amount to deposit: ");
            long amount = scanner.nextLong();
            balance += amount;
            System.out.println("Deposited " + amount + ". New Balance: " + balance);
        } catch (InputMismatchException e) {
            System.out.println("Invalid amount. Please enter a valid number.");
            scanner.next(); // Clear the invalid input
        }
    }

    public void withdrawFunds() {
        try {
            System.out.print("Enter amount to withdraw: ");
            long amount = scanner.nextLong();
            if (amount > balance) {
                System.out.println("Insufficient funds. Withdrawal failed.");
            } else {
                balance -= amount;
                System.out.println("Withdrew " + amount + ". New Balance: " + balance);
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid amount. Please enter a valid number.");
            scanner.next(); // Clear the invalid input
        }
    }

    public boolean transferFunds(AccountDetails recipient, long amount) {
        if (amount <= balance) {
            balance -= amount;
            recipient.balance += amount;
            System.out.println("Transferred " + amount + " to account " + recipient.accNo);
            return true;
        } else {
            System.out.println("Insufficient funds. Transfer failed.");
            return false;
        }
    }
}
