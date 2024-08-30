// BankingSystem.java
import java.util.Scanner;

public class BankingSystem {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("How many customers do you want to enter? ");
        int customerCount = scanner.nextInt();
        AccountDetails[] accounts = new AccountDetails[customerCount];

        for (int i = 0; i < accounts.length; i++) {
            accounts[i] = new AccountDetails();
            accounts[i].createAccount();
        }

        handleMenu(accounts, 0);
    }

    static void handleMenu(AccountDetails[] accounts, int option) {
        System.out.println("\n ***Banking System Application***");
        System.out.println("1. Display all account details");
        System.out.println("2. Search by Account Number");
        System.out.println("3. Deposit Amount");
        System.out.println("4. Withdraw Amount");
        System.out.println("5. Exit");
        System.out.println("Enter your choice: ");
        option = scanner.nextInt();

        switch (option) {
            case 1:
                displayAllAccounts(accounts, 0);
                break;
            case 2:
                searchAccount(accounts, 0);
                break;
            case 3:
                depositAmount(accounts, 0);
                break;
            case 4:
                withdrawAmount(accounts, 0);
                break;
            case 5:
                System.out.println("Goodbye!");
                return;
            default:
                System.out.println("Invalid choice. Please try again.");
        }

        handleMenu(accounts, option);
    }

    static void displayAllAccounts(AccountDetails[] accounts, int index) {
        if (index < accounts.length) {
            accounts[index].displayAccount();
            displayAllAccounts(accounts, index + 1);
        }
    }

    static void searchAccount(AccountDetails[] accounts, int index) {
        if (index < accounts.length) {
            System.out.print("Enter account number to search: ");
            String searchNumber = scanner.next();
            boolean found = accounts[index].findAccount(searchNumber);
            if (found) return;
            searchAccount(accounts, index + 1);
        } else {
            System.out.println("Search failed! Account does not exist.");
        }
    }

    static void depositAmount(AccountDetails[] accounts, int index) {
        if (index < accounts.length) {
            System.out.print("Enter account number: ");
            String depositNumber = scanner.next();
            boolean found = accounts[index].findAccount(depositNumber);
            if (found) {
                accounts[index].addFunds();
                return;
            }
            depositAmount(accounts, index + 1);
        } else {
            System.out.println("Search failed! Account does not exist.");
        }
    }

    static void withdrawAmount(AccountDetails[] accounts, int index) {
        if (index < accounts.length) {
            System.out.print("Enter account number: ");
            String withdrawNumber = scanner.next();
            boolean found = accounts[index].findAccount(withdrawNumber);
            if (found) {
                accounts[index].withdrawFunds();
                return;
            }
            withdrawAmount(accounts, index + 1);
        } else {
            System.out.println("Search failed! Account does not exist.");
        }
    }
}
