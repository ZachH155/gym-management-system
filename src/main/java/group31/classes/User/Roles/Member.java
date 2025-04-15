package group31.classes.User.Roles;
import group31.classes.User.User;

public class Member extends User {
    
    //constuctor
    public Member(String username, String password, String email, String phoneNumber, String address, String role) {
        super(username, password, email, phoneNumber, address, role);
        setUsername(username);
        setPassword(password);
        setEmail(email);
        setPhoneNumber(phoneNumber);
        setAddress(address);
        setRole(role);
    }
}
