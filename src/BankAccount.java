

public class BankAccount
{
    
    private String userId;

    private String accountNumber;

    private double balance;
    
    public BankAccount(String userId,String accountNumber, double balance){
        this.userId = userId;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }
    
    public String getAccountNumber(){
        return accountNumber;
    }

    public String getUserId() {
        return userId;
    }

    public String getAccountHolderName()
    {
        return Store.getInstance().users.stream()
        .filter(user -> user.getUserId().equals(Store.getInstance().loggedUser.get().getUserId()))
        .findFirst().get().getName();
    }
    
    public double getBalance(){
        return balance;
    }
    
    public void deposit(double amount){
        balance += amount;
    }
    
    public void withdraw(double amount){
        balance -= amount;
    }
    
    public void transfer(BankAccount account, double amount){
        withdraw(amount);
        account.deposit(amount);
    }
    
    public String toString(){
        return "Account Number: " + accountNumber + "\nBalance: " + balance;
    }

}