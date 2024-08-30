// BankGUI.java
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BankGUI extends JFrame {
    private AccountDetails[] accounts;
    private JTextArea textArea;

    public BankGUI(AccountDetails[] accounts) {
        this.accounts = accounts;
        createAndShowGUI();
    }

    private void createAndShowGUI() {
        setTitle("Banking System");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create components
        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1));

        JButton displayButton = new JButton("Display All Accounts");
        JButton searchButton = new JButton("Search Account");
        JButton depositButton = new JButton("Deposit");
        JButton withdrawButton = new JButton("Withdraw");
        JButton transferButton = new JButton("Transfer");
        JButton exitButton = new JButton("Exit");

        panel.add(displayButton);
        panel.add(searchButton);
        panel.add(depositButton);
        panel.add(withdrawButton);
        panel.add(transferButton);
        panel.add(exitButton);

        // Add components to the frame
        add(panel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Add action listeners
        displayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayAllAccounts();
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchAccount();
            }
        });

        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                depositAmount();
            }
        });

        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                withdrawAmount();
            }
        });

        transferButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                transferFunds();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        setVisible(true);
    }

    private void displayAllAccounts() {
        textArea.setText("");
        for (AccountDetails account : accounts) {
            account.displayAccount();
            textArea.append(account.toString() + "\n");
        }
    }

    private void searchAccount() {
        String accNumber = JOptionPane.showInputDialog(this, "Enter Account Number to Search:");
        for (AccountDetails account : accounts) {
            if (account.findAccount(accNumber)) {
                return;
            }
        }
        JOptionPane.showMessageDialog(this, "Account not found.");
    }

    private void depositAmount() {
        String accNumber = JOptionPane.showInputDialog(this, "Enter Account Number:");
        for (AccountDetails account : accounts) {
            if (account.findAccount(accNumber)) {
                String amountStr = JOptionPane.showInputDialog(this, "Enter amount to deposit:");
                try {
                    long amount = Long.parseLong(amountStr);
                    account.addFunds();
                    JOptionPane.showMessageDialog(this, "Deposit successful.");
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "Invalid amount.");
                }
                return;
            }
        }
        JOptionPane.showMessageDialog(this, "Account not found.");
    }

    private void withdrawAmount() {
        String accNumber = JOptionPane.showInputDialog(this, "Enter Account Number:");
        for (AccountDetails account : accounts) {
            if (account.findAccount(accNumber)) {
                String amountStr = JOptionPane.showInputDialog(this, "Enter amount to withdraw:");
                try {
                    long amount = Long.parseLong(amountStr);
                    account.withdrawFunds();
                    JOptionPane.showMessageDialog(this, "Withdrawal successful.");
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "Invalid amount.");
                }
                return;
            }
        }
        JOptionPane.showMessageDialog(this, "Account not found.");
    }

    private void transferFunds() {
        String fromAccNumber = JOptionPane.showInputDialog(this, "Enter your Account Number:");
        AccountDetails fromAccount = null;
        for (AccountDetails account : accounts) {
            if (account.findAccount(fromAccNumber)) {
                fromAccount = account;
                break;
            }
        }

        if (fromAccount == null) {
            JOptionPane.showMessageDialog(this, "Source account not found.");
            return;
        }

        String toAccNumber = JOptionPane.showInputDialog(this, "Enter recipient Account Number:");
        AccountDetails toAccount = null;
        for (AccountDetails account : accounts) {
            if (account.findAccount(toAccNumber)) {
                toAccount = account;
                break;
            }
        }

        if (toAccount == null) {
            JOptionPane.showMessageDialog(this, "Recipient account not found.");
            return;
        }

        String amountStr = JOptionPane.showInputDialog(this, "Enter amount to transfer:");
        try {
            long amount = Long.parseLong(amountStr);
            if (fromAccount.transferFunds(toAccount, amount)) {
                JOptionPane.showMessageDialog(this, "Transfer successful.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid amount.");
        }
    }
}
