// Main.java
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        // Sample data for demonstration purposes
        AccountDetails[] accounts = new AccountDetails[2];
        accounts[0] = new AccountDetails();
        accounts[0].createAccount();
        accounts[1] = new AccountDetails();
        accounts[1].createAccount();

        // Launch GUI
        SwingUtilities.invokeLater(() -> new BankGUI(accounts));
    }
}
