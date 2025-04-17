package group31;

import java.sql.Connection;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import group31.classes.User.*;
import group31.classes.User.Roles.*;


public class App 
{
    static Scanner scanner = new Scanner(System.in);
    static int input = 0;
    private static UserService userService;
    private static User loggedUser = new User();

    public static void main( String[] args ) throws Exception
    {
        Connection connection = DBConnection.getConnection();
        UserDAO DAO = new UserDAO(connection);
        userService = new UserService(DAO);

        System.out.println("___________");
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("Resize terminal upward until you can see the line");
        System.out.println("then hit enter");
        scanner.nextLine();

        
        //checks to see if there is an admin account in the database, if not, adds an admin
        if (userService.getUser("Admin", "Admin").getRole() == "missing") {
            Admin admin = new Admin("Admin", "Admin",
            "Admin@gmail.com", "1013333333", "3 Admin St", "admin");
            userService.addUser(admin);
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
        }
        
        //if there is no user logged in, brings up main menu
        //if a user is logged in, brings up different menus based on role
        if (loggedUser.getRole() == "missing") {
            while (true) {   
                System.out.println();
                System.out.println();
                System.out.println();
                System.out.println();
                System.out.println( "GYM MANAGEMENT SYSTEM" );
                System.out.println("-----------------------");
                System.out.println("Enter a number to choose an option");
                System.out.println();
                System.out.println("1. Register");
                System.out.println("2. Login");
                System.out.println("3. Exit");
                
                try {
                    input = Integer.parseInt(scanner.nextLine());
                } catch (Exception e) {
                    
                }   
                
                //checks user input
                switch (input) {
                    case 1:
                        System.out.println();
                        System.out.println();
                        System.out.println();
                        System.out.println();
                        System.out.println( "REGISTRATION" );
                        System.out.println("-----------------------");
                        System.out.println();

                        String username = "";
                        while (true) {
                            System.out.println("Username: ");
                            username = scanner.nextLine();
                            if (username.toLowerCase() == "missing") {
                                System.err.println("Username in use");
                                TimeUnit.SECONDS.sleep(1);
                            } else {
                                break;
                            }
                        }
                        

                        System.out.println();
                        System.out.println("Password: ");
                        String password = scanner.nextLine();
                        
                        System.out.println();
                        System.out.println("Email: ");
                        String email = scanner.nextLine();
                        
                        System.out.println();
                        System.out.println("Phone Number: ");
                        String phoneNumber = scanner.nextLine();

                        System.out.println();
                        System.out.println("Address: ");
                        String address = scanner.nextLine();

                        System.out.println();
                        System.out.println("1. Member");
                        System.out.println("2. Trainer");
                        System.out.println("3. Admin");
                        String role = scanner.nextLine();


                        //creates new User
                        User user = new User();
                        switch (role) {
                            case "1":
                                role = "member";
                                Member member = new Member(username, password, email, phoneNumber, address, role);
                                user = member;
                                break;
                            case "2":
                                role = "trainer";
                                Trainer trainer = new Trainer(username, password, email, phoneNumber, address, role);
                                user = trainer;
                                break;
                            case "3":
                                role = "admin";
                                Admin admin = new Admin(username, password, email, phoneNumber, address, role);
                                user = admin;
                                break;
                            default:
                                break;    
                        }
                        userService.addUser(user);
                        break;
                    case 2:
                        System.out.println();
                        System.out.println();
                        System.out.println();
                        System.out.println();
                        System.out.println( "LOGIN" );
                        System.out.println("-----------------------");
                        System.out.println();

                        System.out.println("Username: ");
                        username = scanner.nextLine();

                        System.out.println();
                        System.out.println("Password: ");
                        password = scanner.nextLine();

                        //Checks if a user gets returned before logging user in
                        if (userService.getUser(username, password).getUsername() == "missing") {
                            System.out.println();
                            System.err.println("Login failed");
                            TimeUnit.SECONDS.sleep(1);
                        } else {
                            System.out.println();
                            System.out.println("Login success!");
                            TimeUnit.SECONDS.sleep(1);
                            loggedUser = userService.getUser(username, password);
                        }
                        break;
                    case 3:
                        System.exit(0);
                    default:
                        System.err.println("ENTER VALID NUMBER");
                        TimeUnit.SECONDS.sleep(1);
                        break;
                }
            }    
        } else if (loggedUser.getRole() == "member") {
            while (true) {
                System.out.println();
                System.out.println();
                System.out.println();
                System.out.println();

                //displays logged user
                if (loggedUser.getUsername() != "missing") {
                    System.out.println("User: " + loggedUser.getUsername());
                }

                System.out.println("-----------------------");
                System.out.println();
                
                //changes options based on membership status
                //no membership means no workout classes!
                //<WIP>

                // </WIP>

                System.out.println("1. View Workout Classes");
                System.out.println("2. Purchase Membership");
                System.out.println("3. View Membership Expenses");
                System.out.println("4. Exit");
                
                try {
                    input = Integer.parseInt(scanner.nextLine());
                } catch (Exception e) {
                    
                }   

                switch (input) {
                    case 1:
                        
                        break;
                    case 2:
                    
                        break;
                    case 3:

                        break;
                    case 4:
                        System.exit(0);
                    default:
                        System.err.println("ENTER VALID NUMBER");
                        TimeUnit.SECONDS.sleep(1);
                        break;
                }
            }


        } else if (loggedUser.getRole() == "trainer") {
            while (true) {
                System.out.println();
                System.out.println();
                System.out.println();
                System.out.println();

                //displays logged user
                if (loggedUser.getUsername() != "missing") {
                    System.out.println("User: " + loggedUser.getUsername());
                }

                System.out.println("-----------------------");
                System.out.println();

                System.out.println("1. View Your Workout Classes");
                System.out.println("2. Manage Your Workout Classes");
                System.out.println("3. Purchase Membership");
                System.out.println("4. Exit");
                
                try {
                    input = Integer.parseInt(scanner.nextLine());
                } catch (Exception e) {
                    
                }   

                switch (input) {
                    case 1:
                        
                        break;
                    case 2:
                    
                        break;
                    case 3:

                        break;
                    case 4:
                        System.exit(0);
                    default:
                        System.err.println("ENTER VALID NUMBER");
                        TimeUnit.SECONDS.sleep(1);
                        break;
                }
            }


        } else if (loggedUser.getRole() == "admin") {
            while (true) {
                System.out.println();
                System.out.println();
                System.out.println();
                System.out.println();

                //displays logged user
                if (loggedUser.getUsername() != "missing") {
                    System.out.println("User: " + loggedUser.getUsername());
                }

                System.out.println("-----------------------");
                System.out.println();

                System.out.println("1. Manage Users");
                System.out.println("2. View Memberships");
                System.out.println("3. Exit");
                
                try {
                    input = Integer.parseInt(scanner.nextLine());
                } catch (Exception e) {
                    
                }   

                switch (input) {
                    case 1:
                        
                        break;
                    case 2:
                    
                        break;
                    case 3:
                        System.exit(0);
                    default:
                        System.err.println("ENTER VALID NUMBER");
                        TimeUnit.SECONDS.sleep(1);
                        break;
                }
            }
        }  else {
            System.err.println("How did you get here?");
        }
    }
};
