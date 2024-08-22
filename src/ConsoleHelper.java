import java.util.Optional;
import java.util.Scanner;

class ConsoleHelper {

    private Scanner sc;
    private Store store;

    public ConsoleHelper() {
        sc = new Scanner(System.in);
        this.store = Store.getInstance();
    }

    public void printAuthMenu() {
        System.out.println("--------Welcome To Bank System---------");
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("3. Exit");
        // String value =
        switch (sc.nextLine()) {
            case "1":
                clearConsole();
                this.printLogin();
                break;
            case "2":
                clearConsole();
                this.printRegister();
            default:
                break;
        }
    }

    public void printLogin() {
        System.out.println("Please enter your email: ");
        String email = sc.nextLine();
        System.out.println("Please enter your password: ");
        String password = sc.nextLine();

        // Check user exist or not
        Optional<User> result = store.checkUserCredentials(email, password);

        if (result.isPresent()) {
            User user = result.get();

            System.out.println("Login successful! Welcome, " + user.getName() + ".");

            clearConsole();
            this.printMenu();
        } else {
            System.out.println("Invalid Credentials");
            clearConsole();
            this.printAuthMenu();
        }

    }

    public void printRegister() {
        System.out.println("Please enter your name");
        String name = sc.nextLine();
        System.out.println("Please enter your email: ");
        String email = sc.nextLine();
        System.out.println("Please enter your password: ");
        String password = sc.nextLine();

        store.setUser(new User(name, email, password));
        System.out.println("User Added Succesfully");

        this.printAuthMenu();
    }

    public void printMenu() {
        Optional<BankAccount> bankAccount = store.getBankAccount();

        if (bankAccount.isPresent())
            System.out.println("1. View Account Details");
        else
            System.out.println("1. Create New Bank Account");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Transfer");
        System.out.println("5. Check Balance");
        System.out.println("6. Logout");
        System.out.println("7. Exit");

        String value = sc.nextLine();
        clearConsole();

        switch (value) {
            case "1":
                if (bankAccount.isEmpty()) {
                    System.out.println("Enter your account number: ");
                    String accountNumber = sc.nextLine();
                    System.out.println("Enter your initial balance: ");
                    double balance = Double.parseDouble(sc.nextLine());
                    store.createBankAccount(accountNumber, balance);
                    clearConsole();
                    System.out.println("Account created successfully!");
                } else {
                    this.printAccountDetail(bankAccount.get().getAccountNumber());
                }
                break;

            case "2":
                System.out.println("Enter amount to deposit: ");
                double depositAmount = Double.parseDouble(sc.nextLine());

                if (bankAccount.isPresent()) {
                    BankAccount account = bankAccount.get();
                    account.deposit(depositAmount);
                    store.updateBankAccount(account); // Update the bankAccounts list
                    clearConsole();
                    System.out.println("Amount deposited successfully!");
                } else {
                    System.out.println("No bank account found.");
                }
                break;

            case "3":
                System.out.println("Enter amount to withdraw: ");
                double withdrawAmount = Double.parseDouble(sc.nextLine());

                if (bankAccount.isPresent()) {
                    BankAccount account = bankAccount.get();
                    if (account.getBalance() < withdrawAmount)
                        System.out.println("You dont have Enough balance!");

                    else {
                        account.withdraw(withdrawAmount);
                        store.updateBankAccount(account); // Update the bankAccounts list
                        System.out.println("Amount withdrawn successfully!");
                    }

                } else {
                    System.out.println("No bank account found.");
                }
                break;

            case "4":
                System.out.println("Enter recipient account number: ");
                String recipientAccountNumber = sc.nextLine();
                System.out.println("Enter amount to transfer: ");
                double transferAmount = Double.parseDouble(sc.nextLine());

                Optional<BankAccount> optionalSenderAccount = bankAccount;

                if (optionalSenderAccount.isPresent()) {
                    BankAccount senderAccount = optionalSenderAccount.get();
                    Optional<BankAccount> optionalRecipientAccount = store
                            .getBankAccountByNumber(recipientAccountNumber);
                    if (optionalRecipientAccount.isPresent()) {
                        BankAccount recipientAccount = optionalRecipientAccount.get();
                        senderAccount.transfer(recipientAccount, transferAmount);
                        store.updateBankAccount(senderAccount); // Update the sender account
                        store.updateBankAccount(recipientAccount); // Update the recipient account
                        System.out.println("Amount transferred successfully!");
                    } else {
                        System.out.println("Recipient account not found.");
                    }
                } else {
                    System.out.println("No bank account found.");
                }
                break;

            case "5":
                double balance = store.getBankAccount().get().getBalance();
                System.out.println("Your current balance is: " + balance);
                break;

            case "6":
                store.logout();
                this.printAuthMenu();
                break;

            case "7":
                System.exit(0);
                break;

            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }
        printMenu();
    }

    public void printAccountDetail(String accountNumber) {
        BankAccount account = store.getBankAccountByNumber(accountNumber).get();
        if (account != null) {
            System.out.println("Account Number: " + account.getAccountNumber());
            System.out.println("Account Holder: " + account.getAccountHolderName());
            System.out.println("Account Balance: " + account.getBalance());
        } else {
            System.out.println("Account not found.");
        }
    }

    public void clearConsole() {
        // Clear the console
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

}