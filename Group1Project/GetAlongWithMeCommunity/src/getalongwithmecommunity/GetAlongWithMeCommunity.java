
package getalongwithmecommunity;

import java.util.Scanner;

public class GetAlongWithMeCommunity {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //volunteer registration function 
        LinkedList volunteerList = new LinkedList();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n                     Volunteer Registration                     ");
            System.out.println("__________________________________________________________________");
            System.out.println("1. Register a Volunteer");
            System.out.println("2. Exit");
            System.out.print("\nEnter your choice: ");
            
            

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter Your Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Your Age: ");
                    int age = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Your Phone Number: ");
                    String phone = scanner.nextLine();
                    System.out.print("Enter Your Email: ");
                    String email = scanner.nextLine();
                    System.out.print("Enter Your City: ");
                    String city = scanner.nextLine();

                    volunteerList.append(name, age, phone, email, city);
                    System.out.println("Volunteer registered successfully!");
                    break;
                case 2:
                    System.out.println("Exiting Volunteer Registration System. Goodbye!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }

    }
    
}
