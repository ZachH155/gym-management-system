package group31.classes.User.Roles;
import group31.classes.User.User;

public class Trainer extends User {
    
    //constructor
    public Trainer(String username, String password, String email, String address, String role) {
        super(username, password, email, address, role);
        setUsername(username);
        setPassword(password);
        setEmail(email);
        setRole(role);
    }
}
