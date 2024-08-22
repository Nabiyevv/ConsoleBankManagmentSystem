import java.util.UUID;

public class User{
    
    private String userID;
    private String name;
    private String email;
    private String password;

    public User(String name, String email, String password){
        this.name = name;
        this.email = email;
        this.password = password;
        this.setUserId();
    }
    
    public String getUserId()
    {
        return this.userID;
    }

    public String getName(){
        return name;
    }

    public String getEmail()
    {
        return email;
    }

    public String getPassword()
    {
        return password;
    }

    private void setUserId()
    {
        userID = UUID.randomUUID().toString();
    }

}