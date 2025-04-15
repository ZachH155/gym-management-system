package group31;

import java.util.Scanner;

import group31.classes.User.User;

/**
 * Hello world!
 *
 */
public class App 
{
    static Scanner scanner = new Scanner(System.in);
    static int input = 0;

    public static void main( String[] args ) throws Exception
    {
        
        System.out.println( "GYM MANAGEMENT SYSTEM" );
        System.out.println("-----------------------");
        System.out.println("Enter a number to choose an option");
        System.out.println();
        System.out.println("1. Register");
        System.out.println("2. Login");
        
        try {
            input = Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            throw new Exception("INPUT VALID NUMBER");
        }
        
        
        
        //checks user input
        if (input == 1) {
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println( "REGISTRATION" );
            System.out.println("-----------------------");
            System.out.println();

            System.out.println("Username: ");
            String username = scanner.nextLine();

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


            //checks if username, email, etc. already exists WIP
            //if (joe mama) {String sfdfgas = "A&W"}

            //creates new User
            if (role.equals("1")) {
                role = "member";
            }
            if (role.equals("2")) {
                role = "trainer";
            }
            if (role.equals("3")) {
                role = "admin";
            }


            User user = new User(username, password, email, phoneNumber, address, role);

        } else if (input == 2){
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println( "LOGIN" );
            System.out.println("-----------------------");
            System.out.println("Enter a number to choose an option");
            System.out.println();

        } else {
            throw new Exception("ENTER VALID NUMBER");
        }
    }
    
};
