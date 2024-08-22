import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public final class Store {

    private static Store instance;

    public static Store getInstance() {
        if (instance == null) {
            instance = new Store();
        }
        return instance;
    }

    public List<User> users;

    public List<BankAccount> bankAccounts;

    public Optional<User> loggedUser;

    public Store() {
        this.users = new ArrayList<>();
        this.bankAccounts = new ArrayList<>();
        this.users.add(new User("Mahammad Nabiyev", "1", "1"));
    }

    public void setUser(User user) {
        this.users.add(user);
    }

    public void getUsers() {
        for (User user : users) {
            System.out.println(user.getName());
        }

    }

    // public Optional<User> checkUserCredentials(String email, String password)
    // {
    // for(User user: users)
    // {
    // if(user.getEmail().equals(email) && user.getPassword().equals(password))
    // {
    // this.loggedUser = Optional.of(user);
    // return Optional.of(user);
    // }
    // }
    // return Optional.empty();
    // }

    public Optional<User> checkUserCredentials(String email, String password) {
        return users.stream()
                .filter(user -> user.getEmail().equals(email) && user.getPassword().equals(password))
                .findFirst()
                .map(user -> {
                    this.loggedUser = Optional.of(user);
                    return Optional.of(user);
                })
                .orElse(Optional.empty());
    }

    public Optional<User> isLogged() {
        return loggedUser;
    }

    public void logout() {
        this.loggedUser = Optional.empty();
        ColorLogger.logSuccess("Logged out successfully");
    }

    public void createBankAccount(String accountNumber, double balance) {
        String userId = this.loggedUser.get().getUserId();
        this.bankAccounts.add(new BankAccount(userId, accountNumber, balance));
    }

    public Optional<BankAccount> getBankAccount(String userId) {
        return bankAccounts.stream()
                .filter(account -> account.getUserId().equals(userId))
                .findFirst();
    }

    public Optional<BankAccount> getBankAccount() {

        if(bankAccounts.size() == 0)
            return Optional.empty();

        return bankAccounts.stream()
                .filter(account -> account.getUserId().equals(loggedUser.get().getUserId()))
                .findFirst();
    }

    public Optional<BankAccount> getBankAccountByNumber(String accountNumber) {
        return bankAccounts.stream()
                .filter(account -> account.getAccountNumber().equals(accountNumber))
                .findFirst();
    }

    public void updateBankAccount(BankAccount updatedAccount) {
        bankAccounts = bankAccounts.stream()
                .map(account -> account.getAccountNumber().equals(updatedAccount.getAccountNumber()) ? updatedAccount
                        : account)
                .collect(Collectors.toList());
    }

}