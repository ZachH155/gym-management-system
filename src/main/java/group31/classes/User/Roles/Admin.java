package group31.classes.User.Roles;
import group31.classes.User.User;

public class Admin extends User {

    //constructor
    public Admin(String username, String password, String email, String phoneNumber, String address, String role) {
        super(username, password, email, phoneNumber, address, role);
        setUsername(username);
        setPassword(password);
        setEmail(email);
        setPhoneNumber(phoneNumber);
        setAddress(address);
        setRole(role);
    }
    
}
