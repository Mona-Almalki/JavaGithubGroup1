package getalongwithmecommunity;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class GetAlongWithMeCommunity {

    
    
    public static void main(String[] args) {
        
        LinkedList volunteerList = new LinkedList();
        Scanner scanner = new Scanner(System.in);

        
        //
        ArrayList<Pet> petList = new ArrayList<>(); 

        
        while (true) {
            System.out.println("\n                Welcome to Get Along with me community                ");
            System.out.println("________________________________________________________________________");
            System.out.println("Services:");
            System.out.println("1. Register a Volunteer");
            System.out.println("________________________________________________________________________");
            System.out.println("2. Register a Pet");
            System.out.println("________________________________________________________________________");
            System.out.println("3. Exit");
            System.out.print("\nEnter your choice: ");
            
            

            int choice = getValidIntInput(scanner);
            scanner.nextLine();

            
            switch (choice) {
                case 1:
                    while(choice==1){
                        registerVolunteer(scanner, volunteerList);
                        System.out.println("Enter any number to return to home page or one to continue register a volunteer");
                        choice = getValidIntInput(scanner);
                    }break;
                    
                case 2:
                    while(choice==2){
                        petList.add(registerPetInfo(scanner));
                        System.out.println("Enter any number to return to home page or one to continue register a volunteer");
                        choice = getValidIntInput(scanner);
                    }break;
                    
                case 3:
                    System.out.println("Exiting Get Along with me community System. Goodbye!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
            
            
            
        }
        

    }
    
    
    
    ////volunteer registration function
    public static void registerVolunteer(java.util.Scanner scanner, LinkedList volunteerList) {
        System.out.print("Enter Your Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Your Age: ");
        int age = getValidIntInput(scanner);
        scanner.nextLine();
        System.out.print("Enter Your Phone Number: ");
        String phone = scanner.nextLine();
        System.out.print("Enter Your Email Address: ");
        String email = scanner.nextLine();
        System.out.print("Enter Your City: ");
        String city = scanner.nextLine();

        volunteerList.append(name, age, phone, email, city);
        System.out.println("Volunteer registered successfully!");
        System.out.println("________________________________________________________________________");

    }
    
    
    
    // Register pet info 
    public static Pet registerPetInfo(Scanner scanner){
        
        int petId;
        String name;
        String type;
        int age;
        
        System.out.println("Enter pet information:");

        System.out.print("Pet ID: ");
        petId = getValidIntInput(scanner);
        scanner.nextLine(); 

        System.out.print("Name: ");
        name = scanner.nextLine();

        System.out.print("Type: ");
        type = scanner.nextLine();

        System.out.print("Age: ");
        age = getValidIntInput(scanner);

        Pet pet = new Pet(petId, name, type, age);
        System.out.println("Pet Registered successfully!");
        System.out.println("________________________________________________________________________");

        return pet;
        
     }
    
    
    // check 
    private static int getValidIntInput(Scanner scanner) {
    int input = 0;
    boolean validInput = false;

    while (!validInput) {
        try {
            
            if (scanner.hasNextInt()) {
                input = scanner.nextInt();
                validInput = true;
            } else {
                System.out.println("Invalid input ,Please enter an integer: ");
                scanner.next();
            }
        } catch (InputMismatchException e) {
            System.out.println("Exception occurred: " + e.getMessage());
            scanner.next();
        }
    }

    return input;
}

    
}
    
    

