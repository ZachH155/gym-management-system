package group31.classes.User.Roles;
import group31.classes.User.User;

public class Member extends User {
    
    //constuctor
    public Member(String username, String password, String email, String address, String role) {
        super(username, password, email, address, role);
        setUsername(username);
        setPassword(password);
        setEmail(email);
        setRole(role);
    }
}
