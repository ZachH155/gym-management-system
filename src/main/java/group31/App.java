package group31;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import group31.classes.User.*;
import group31.classes.User.Roles.*;
import group31.classes.WorkoutClass.*;
import group31.classes.Membership.*;


public class App 
{
    static Scanner scanner = new Scanner(System.in);
    static int input = 0;
    private static UserService userService;
    private static WorkoutClassService workoutClassService;
    private static MembershipService membershipService;
    private static User loggedUser = new User();

    public static void RegisterUser(String role) throws InterruptedException, SQLException {
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

        User user = new User();
        while (true) {
            System.out.println();
            System.out.println("1. Member");
            System.out.println("2. Trainer");

            //only admins can make another admin account
            if (role.equals("admin")) {
                System.out.println("3. Admin");
            }
            String role2 = scanner.nextLine();


            //creates new User
            switch (role2) {
                case "1":
                    role2 = "member";
                    Member member = new Member(username, password, email, phoneNumber, address, role2);
                    user = member;
                    break;
                case "2":
                    role2 = "trainer";
                    Trainer trainer = new Trainer(username, password, email, phoneNumber, address, role2);
                    user = trainer;
                    break;
                case "3":
                    if (role.equals("admin")) {
                        role2 = "admin";
                        Admin admin = new Admin(username, password, email, phoneNumber, address, role2);
                        user = admin;
                    } else {
                        System.err.println("ENTER A VALID NUMBER");
                        TimeUnit.SECONDS.sleep(1);
                    }
                    break;
                default:
                    System.err.println("ENTER A VALID NUMBER");
                    TimeUnit.SECONDS.sleep(1);
                    break;    
            }
            if (user.getRole().equals("missing")) {
                
            } else {
                break;
            }
        }

        userService.addUser(user);
    }

    public static void main( String[] args ) throws Exception
    {
        Connection connection = DBConnection.getConnection();
        UserDAO UDAO = new UserDAO(connection);
        userService = new UserService(UDAO);
        WorkoutClassDAO WDAO = new WorkoutClassDAO(connection);
        workoutClassService = new WorkoutClassService(WDAO);
        MembershipDAO MDAO = new MembershipDAO(connection);
        membershipService = new MembershipService(MDAO);

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
            
        while (true) {
            //if there is no user logged in, brings up main menu
            //if a user is logged in, brings up different menus based on role
            if (loggedUser.getRole().equals("missing")) {
                
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
                } catch (Exception e) {}
                
                //checks user input
                switch (input) {
                    case 1:
                        RegisterUser(loggedUser.getRole());
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
                        String username = scanner.nextLine();

                        System.out.println();
                        System.out.println("Password: ");
                        String password = scanner.nextLine();

                        //Checks if a user gets returned before logging user in
                        if (userService.getUser(username, password).getUsername().equals("missing")) {
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
                    
            } else if (loggedUser.getRole().equals("member")) {
                while (true) {
                    System.out.println();
                    System.out.println();
                    System.out.println();
                    System.out.println();
                    System.out.println("User: " + loggedUser.getUsername());
                    System.out.println("-----------------------");
                    System.out.println();
                    System.out.println("1. View Workout Classes");

                    //if user has no membership, they can purchase one
                    // if they have a membership, they can cancel it
                    if (membershipService.getMembership(loggedUser.getUsername()).getCustomer() == "missing") {
                        System.out.println("2. Purchase Membership");
                    } else {
                        System.out.println("2. Cancel Membership");
                    }
                    
                    System.out.println("3. View Membership Expenses");
                    System.out.println("4. Exit");
                    
                    try {
                        input = Integer.parseInt(scanner.nextLine());
                    } catch (Exception e) {}   
                    

                    
                    switch (input) {
                        case 1:
                            System.out.println();
                            System.out.println();
                            System.out.println();
                            System.out.println();
                            for (WorkoutClass i : workoutClassService.getAllWorkoutClasses()) {
                                System.out.println("------");
                                System.out.println("Title: " + i.getTitle());
                                System.out.println("Trainer: " + i.getTrainer());
                                System.out.println("Description: " + i.getDescription());
                            }
                            
                            System.out.println();
                            System.out.println("Press enter to go back");
                            scanner.nextLine();
                            break;
                        case 2:
                            if (membershipService.getMembership(loggedUser.getUsername()).getCustomer().equals("missing")) {
                                System.out.println();
                                System.out.println();
                                System.out.println();
                                System.out.println();
                                System.out.println("A Membership costs $10/month,");
                                System.out.println("you will get access to the Gym,");
                                System.out.println("and will be able to take Workout Classes");
                                System.out.println();
                                System.out.println("1. Purchase");
                                System.out.println("2. Go back");

                                try {
                                    input = Integer.parseInt(scanner.nextLine());
                                } catch (Exception e) {}
                                

                                switch (input) {
                                    case 1:
                                        Membership membership = new Membership(loggedUser.getUsername());
                                        membershipService.addMembership(membership);
                                        break;
                                    case 2:
                                        break;
                                    default:
                                        System.err.println("ENTER VALID NUMBER");
                                        TimeUnit.SECONDS.sleep(1);
                                        break;
                                }
                            
                            } else {
                                System.out.println();
                                System.out.println();
                                System.out.println();
                                System.out.println();
                                System.out.println("Do you want to cancel your Membership?");
                                System.out.println("you will lose access to the Gym,");
                                System.out.println("and will not be able to take Workout Classes");
                                System.out.println();
                                System.out.println("1. Cancel Membership");
                                System.out.println("2. Go back");

                                try {
                                    input = Integer.parseInt(scanner.nextLine());
                                } catch (Exception e) {}

                                switch (input) {
                                    case 1:
                                        membershipService.deleteMembership(loggedUser.getUsername());
                                        break;
                                    case 2:
                                        break;
                                    default:
                                        System.err.println("ENTER VALID NUMBER");
                                        TimeUnit.SECONDS.sleep(1);
                                        break;
                                }
                                break;
                            }
                            
                        case 3:
                            System.out.println();
                            System.out.println();
                            System.out.println();
                            System.out.println();
                            System.out.println("User: " + loggedUser.getUsername());
                            System.out.println("MEMBERSHIP EXPENSES");
                            System.out.println("-----------------------");
                            System.out.println();

                            if (membershipService.getMembership(loggedUser.getUsername()).getCustomer().equals("missing")) {
                                System.out.println("Monthly: " + 0);
                                System.out.println("Yearly: " + (0 * 12));

                                System.out.println();
                                System.out.println("Press enter to go back");
                                scanner.nextLine();
                            } else {
                                int cost = membershipService.getMembership(loggedUser.getUsername()).getCost();

                                System.out.println("Monthly: " + cost);
                                System.out.println("Yearly: " + (cost * 12));

                                System.out.println();
                                System.out.println("Press enter to go back");
                                scanner.nextLine();
                            }
                            
                            break;
                        case 4:
                            System.exit(0);
                        default:
                            System.err.println("ENTER VALID NUMBER");
                            TimeUnit.SECONDS.sleep(1);
                            break;
                        }
                    }
            } else if (loggedUser.getRole().equals("trainer")) {
                while (true) {
                    System.out.println();
                    System.out.println();
                    System.out.println();
                    System.out.println();
                    System.out.println("User: " + loggedUser.getUsername());
                    System.out.println("-----------------------");
                    System.out.println();

                    System.out.println("1. Your Workout Classes");

                    //if user has no membership, they can purchase one
                    // if they have a membership, they can cancel it
                    if (membershipService.getMembership(loggedUser.getUsername()).getCustomer() == "missing") {
                        System.out.println("2. Purchase Membership");
                    } else {
                        System.out.println("2. Cancel Membership");
                    }
                    System.out.println("3. Exit");
                    
                    try {
                        input = Integer.parseInt(scanner.nextLine());
                    } catch (Exception e) {}   
                    

                    switch (input) {
                        case 1:
                            System.out.println();
                            System.out.println();
                            System.out.println();
                            System.out.println();
                            System.out.println("User: " + loggedUser.getUsername());
                            System.out.println("YOUR WORKOUT CLASS MANAGEMENT");
                            System.out.println("-----------------------");
                            System.out.println();
                            System.out.println("1. View all Workout Classes");
                            System.out.println("2. View a Workout Class");
                            System.out.println("3. Add a new Workout Class");
                            System.out.println("4. Delete a Workout Class");
                            System.out.println("5. Exit");

                            try {
                                input = Integer.parseInt(scanner.nextLine());
                            } catch (Exception e) {}  

                            String title;
                            switch (input) {
                                
                                case 1:
                                    System.out.println();
                                    System.out.println();
                                    System.out.println();
                                    System.out.println();
                                    for (WorkoutClass i : workoutClassService.getAllWorkoutClassesTrainer(loggedUser.getUsername())) {
                                        System.out.println("------");
                                        System.out.println("Title: " + i.getTitle());
                                        System.out.println("Trainer: " + i.getTrainer());
                                        System.out.println("Description: " + i.getDescription());
                                    }
                                    System.out.println();
                                    System.out.println("Press enter to go back");
                                    scanner.nextLine();
                                    break;
                                case 2:
                                    System.out.println();
                                    System.out.println();
                                    System.out.println();
                                    System.out.println("Title of workout class: ");
                                    title = scanner.nextLine();
                                    System.out.println();
                                    System.out.println();
                                    System.out.println();
                                    System.out.println();
                                    System.out.println("Title: " + workoutClassService.getWorkoutClass(title, loggedUser.getUsername()).getTitle());
                                    System.out.println("Trainer: " + workoutClassService.getWorkoutClass(title, loggedUser.getUsername()).getTrainer());
                                    System.out.println("Description: " + workoutClassService.getWorkoutClass(title, loggedUser.getUsername()).getDescription());
                                    System.out.println();
                                    System.out.println("Press enter to go back");
                                    scanner.nextLine();
                                    break;
                                case 3:
                                    System.out.println();
                                    System.out.println();
                                    System.out.println();
                                    System.out.println("Workout Class title: ");
                                    title = scanner.nextLine();
                                    System.out.println();

                                    //if description is more than 255 characters long (pgadmin varchar limit)
                                    //then it will prompt user to try again
                                    String description;
                                    while (true) {
                                        System.out.println("Description: ");
                                        System.out.println("(255 characters max)");
                                        description = scanner.nextLine();
                                        if (description.length() < 255) {
                                            break;
                                        } else {
                                            System.err.println("DESCRIPTION MUST BE LESS THAN 255 CHARACTERS");
                                        }
                                    }
                                    
                                    WorkoutClass workoutClass = new WorkoutClass(title, loggedUser.getUsername(), description);
                                    workoutClassService.addWorkoutClass(workoutClass);
                                    break;
                                case 4:
                                    System.out.println();
                                    System.out.println();
                                    System.out.println();
                                    System.out.println();
                                    System.out.println("Title: ");
                                    title = scanner.nextLine();
                                    System.out.println();
                                    System.out.println();
                                    System.out.println();
                                    workoutClassService.getWorkoutClass(title, loggedUser.getUsername());
                                    System.out.println();
                                    System.out.println("Is this the Workout Class you want to delete?");
                                    System.out.println();
                                    System.out.println("1. Yes");
                                    System.out.println("2. No");

                                    try {
                                        input = Integer.parseInt(scanner.nextLine());
                                    } catch (Exception e) {}  

                                    switch (input) {
                                        case 1:
                                            workoutClassService.deleteWorkoutClass(title);
                                            break;
                                        case 2:
                                            break;
                                        default:
                                            System.err.println("ENTER VALID NUMBER");
                                            TimeUnit.SECONDS.sleep(1);
                                            break;
                                    }
                                    

                                    break;
                                case 5:
                                    System.exit(0);
                                default:
                                    System.err.println("ENTER VALID NUMBER");
                                    TimeUnit.SECONDS.sleep(1);
                                    break;
                            }

                            break;
                        case 2:
                            if (membershipService.getMembership(loggedUser.getUsername()).getCustomer().equals("missing")) {
                                System.out.println();
                                System.out.println();
                                System.out.println();
                                System.out.println();
                                System.out.println("A Membership costs $10/month,");
                                System.out.println("you will get access to the Gym,");
                                System.out.println("and will be able to take Workout Classes");
                                System.out.println();
                                System.out.println("1. Purchase");
                                System.out.println("2. Go back");

                                try {
                                    input = Integer.parseInt(scanner.nextLine());
                                } catch (Exception e) {}

                                switch (input) {
                                    case 1:
                                        Membership membership = new Membership(loggedUser.getUsername());
                                        membershipService.addMembership(membership);
                                        break;
                                    case 2:
                                        break;
                                    default:
                                        System.err.println("ENTER VALID NUMBER");
                                        TimeUnit.SECONDS.sleep(1);
                                        break;
                                }
                            
                            } else {
                                System.out.println();
                                System.out.println();
                                System.out.println();
                                System.out.println();
                                System.out.println("Do you want to cancel your Membership?");
                                System.out.println("you will lose access to the Gym,");
                                System.out.println("and will not be able to take Workout Classes");
                                System.out.println();
                                System.out.println("1. Cancel Membership");
                                System.out.println("2. Go back");

                                try {
                                    input = Integer.parseInt(scanner.nextLine());
                                } catch (Exception e) {}
                                

                                switch (input) {
                                    case 1:
                                        membershipService.deleteMembership(loggedUser.getUsername());
                                        break;
                                    case 2:
                                        break;
                                    default:
                                        System.err.println("ENTER VALID NUMBER");
                                        TimeUnit.SECONDS.sleep(1);
                                        break;
                                }
                                break;
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


            } else if (loggedUser.getRole().equals("admin")) {
                while (true) {
                    System.out.println();
                    System.out.println();
                    System.out.println();
                    System.out.println();
                    System.out.println("User: " + loggedUser.getUsername());
                    System.out.println("-----------------------");
                    System.out.println();

                    System.out.println("1. Manage Users");
                    System.out.println("2. View Memberships");
                    System.out.println("3. Exit");
                    
                    try {
                        input = Integer.parseInt(scanner.nextLine());
                    } catch (Exception e) {}   
                    

                    switch (input) {
                        case 1:
                            System.out.println();
                            System.out.println();
                            System.out.println();
                            System.out.println(); 
                            System.out.println("User: " + loggedUser.getUsername());
                            System.out.println("USER MANAGEMENT");
                            System.out.println("-----------------------");
                            System.out.println();
                            System.out.println("1. View all Users");
                            System.out.println("2. View a User");
                            System.out.println("3. Register new User");
                            System.out.println("4. Delete User");
                            System.out.println("5. Exit");

                            try {
                                input = Integer.parseInt(scanner.nextLine());
                            } catch (Exception e) {}   

                            switch (input) {
                                case 1:
                                    System.out.println();
                                    System.out.println();
                                    System.out.println();
                                    System.out.println();

                                    for (User i : userService.getAllUsers()) {
                                        System.out.println("------");
                                        System.out.println("Username: " + i.getUsername());
                                        System.out.println("Email: " + i.getEmail());
                                        System.out.println("Phone Number: " + i.getPhoneNumber());
                                        System.out.println("Address: " + i.getAddress());
                                        System.out.println("Role: " + i.getRole());
                                    }

                                    System.out.println();
                                    System.out.println("Press enter to go back");
                                    scanner.nextLine();
                                    
                                    break;
                                case 2:
                                    System.out.println();
                                    System.out.println();
                                    System.out.println();
                                    System.out.println();
                                    System.out.println("Username: ");
                                    String username = scanner.nextLine();

                                    User user = userService.getUser(username);

                                    System.out.println();
                                    System.out.println();
                                    System.out.println();
                                    System.out.println();
                                    System.out.println("Username: " + user.getUsername());
                                    System.out.println("Email: " + user.getEmail());
                                    System.out.println("Phone Number: " + user.getPhoneNumber());
                                    System.out.println("Address: " + user.getAddress());
                                    System.out.println("Role: " + user.getRole());
                                

                                    System.out.println();
                                    System.out.println("Press enter to go back");
                                    scanner.nextLine();
                                    break;
                                case 3:
                                    RegisterUser(loggedUser.getRole());
                                    break;
                                case 4:
                                    System.out.println();
                                    System.out.println();
                                    System.out.println();
                                    System.out.println();
                                    System.out.println("Username: ");
                                    String username2 = scanner.nextLine();
                                    User user2 = userService.getUser(username2);

                                    System.out.println();
                                    System.out.println();
                                    System.out.println();
                                    System.out.println();
                                    System.out.println("Username: " + user2.getUsername());
                                    System.out.println("Email: " + user2.getEmail());
                                    System.out.println("Phone Number: " + user2.getPhoneNumber());
                                    System.out.println("Address: " + user2.getAddress());
                                    System.out.println("Role: " + user2.getRole());

                                    System.out.println();
                                    System.out.println("Is this the correct User?");
                                    System.out.println();
                                    System.out.println("1. Yes");
                                    System.out.println("2. No");

                                    try {
                                        input = Integer.parseInt(scanner.nextLine());
                                    } catch (Exception e) {}

                                    switch (input) {
                                        case 1:
                                            userService.deleteUser(username2);
                                            break;
                                        case 2:
                                            break;
                                        default:
                                            System.err.println("ENTER VALID NUMBER");
                                            TimeUnit.SECONDS.sleep(1);
                                            break;
                                    }
                                    break;
                                case 5:
                                    System.exit(0);
                                default:
                                    System.err.println("ENTER VALID NUMBER");
                                    TimeUnit.SECONDS.sleep(1);
                                    break;
                            }

                            break;
                        case 2:
                            System.out.println();
                            System.out.println();
                            System.out.println();
                            System.out.println();

                            int cost = 0;
                            for (Membership i : membershipService.getAllMemberships()) {
                                cost += i.getCost();
                                System.out.println("------");
                                System.out.println("Customer: " + i.getCustomer());
                                System.out.println("Cost: " + i.getCost());
                                System.out.println("Description: " + i.getDescription());
                            }

                            System.out.println();
                            System.out.println("Monthly Revenue: " + cost);
                            System.out.println("Yearly Revenue: " + (cost * 12));

                            System.out.println("Scroll up to see Memberships");
                            System.out.println("Press enter to go back");
                            scanner.nextLine();

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
    }
};
