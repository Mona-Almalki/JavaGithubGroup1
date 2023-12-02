package getalongwithmecommunity;

import java.util.Scanner;
import java.util.InputMismatchException;
import java.io.*;
import java.util.ArrayList;




public class GetAlongWithMeCommunity {

    private static final String FILE_NAME = "InfoFile.txt";
    private static final String User_File = "UserDB.txt";
    private static final ArrayList<Pet> petList = new ArrayList<>();
    private static final ArrayList<User> userList = new ArrayList<>();


    
    public static void main(String[] args) throws IOException {
        
        //
        Scanner scanner = new Scanner(System.in);

        // to read the info from file
        ReadFile();

        
        while (true) {
            displayMenu() ;
            
            int choice = getValidIntInput(scanner);
            scanner.nextLine();

            
            switch (choice) {
                case 1:
                    while(choice==1){
                        System.out.print("Enter your name: ");
                        String name = scanner.nextLine();
                
                        System.out.print("Enter your age: ");
                        int age = scanner.nextInt();
                        scanner.nextLine();
                
                        System.out.print("Enter your phone number: ");
                        String phone = scanner.nextLine();
                
                        System.out.print("Enter your email: ");
                        String email = scanner.nextLine();
                
                        System.out.print("Enter your city: ");
                        String city = scanner.nextLine();
                
                        // Create a User object with the collected information
                        User newUser = new User(name, age, phone, email, city);
                        userList.add(newUser);

                
                        // Register the user using the registerUser method
                        newUser.registerUser("UserDB.txt");
           
                        System.out.println("Enter any number to return to home page or 1 to contunue register a volunteer");
                        choice = getValidIntInput(scanner);
                    }break;
                    
                case 2:
                    while(choice==2){
                        registerPetInfo(scanner);
                        System.out.println("Enter any number to return to home page or 2 to contunue register a Pet");
                        choice = getValidIntInput(scanner);
                    }break;
                    
                case 3:
                     displayAllPets(); break;
                    
                case 4:
                    adopt(scanner);
                    break;
                    
                case 5:
                    System.out.println("Volunteer for the community");

                    // Ask for the user's name
                    System.out.print("Enter your name: ");
                    String userName = scanner.nextLine();

                    // Call the updateVolunteerStatus method
                    User.updateVolunteerStatus(userName, true);

                    break;
                case 6:
                    System.out.println("Showing all volunteers:");
                    User.displayAllVolunteers();
                    break;
                case 7:
                    System.out.println("Exiting Get Along with me community System. Goodbye!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }
        
    }
    
    private static void displayEvent(){

         System.out.println("\n                Welcome to Get Along with Me Community                ");
    }
    
    //Display menu
    private static void displayMenu(){
        System.out.println("\n                Welcome to Get Along with Me Community                ");
        System.out.println("________________________________________________________________________");
        System.out.println("Services:");
        System.out.println("1. User registration");
        System.out.println("________________________________________________________________________");
        System.out.println("2. Register a Pet");
        System.out.println("________________________________________________________________________");
        System.out.println("3. Show all Pet information");
        System.out.println("________________________________________________________________________");
        System.out.println("4. Adopt a Pet");
        System.out.println("________________________________________________________________________");
        System.out.println("5. Volunteer for the community");
        System.out.println("________________________________________________________________________");
        System.out.println("6. Show all Volunteer information");
        System.out.println("________________________________________________________________________");
        System.out.println("7. Exit");
        System.out.print("\nEnter your choice: ");
    }



    // read from file 
    public static void ReadFile() throws IOException{
        
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                
                //Read pet info from file
                if(parts[0].startsWith("petId=")){
                    String[] petInfoParts = parts[0].split("=");

                    if (parts.length == 4) {
                        if (petInfoParts.length == 2) {
                        int petId = Integer.parseInt(petInfoParts[1].trim());
                        String name = parts[1].substring(parts[1].indexOf("=") + 1).trim();
                        String type = parts[2].substring(parts[2].indexOf("=") + 1).trim();
                        int age = Integer.parseInt(parts[3].substring(parts[3].indexOf("=") + 1).trim());

                        Pet pet = new Pet(petId, name, type, age);
                        petList.add(pet);
                    
                    } else 
                        System.out.println("Invalid line: " + line);
                }}
        }}catch (IOException | NumberFormatException e){}

    }
    
    
    // Register pet info 
    public static void registerPetInfo(Scanner scanner){
        int petId;
        String name;
        String type;
        int age;
        
        System.out.println("Enter pet information:");

        System.out.print("Pet ID : ");
        petId = getValidIntInput(scanner);
        scanner.nextLine(); 
        if (!petExists(petId)) {

        System.out.print("Name: ");
        name = scanner.nextLine();

        System.out.print("Type: ");
        type = scanner.nextLine();

        System.out.print("Age: ");
        age = getValidIntInput(scanner);

        Pet pet = new Pet(petId, name, type, age);

            petList.add(pet);
            //save it to the file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
                writer.write(pet.toString());
                writer.newLine();
                System.out.println("Pet information has been successfully registered.");
                System.out.println("________________________________________________________________________");

            } catch (IOException e) {
                e.printStackTrace();}
            
        }
        
        else {
            System.out.println("Pet ID already exists. Please enter unique information.");
            System.out.println("________________________________________________________________________");
        } 
    }

     System.out.println("\n********** Adopt a Pet **********");

    System.out.print("Enter the pet ID: ");
    int petId = getValidIntInput(scanner);

    // Check if the pet with the given ID exists
    if (!petExists(petId)) {
        System.out.println("Pet with ID " + petId + " not found.");
        return;
    }

    // Read the entire file and store its contents
    ArrayList<String> fileContents = new ArrayList<>();
    try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] petInfo = line.split(",");
            if (petInfo.length >= 1) {
                String[] petIdInfo = petInfo[0].split("=");
                if (petIdInfo.length >= 2) {
                    int currentPetId = Integer.parseInt(petIdInfo[1]);

                    // Check if the line corresponds to the pet being adopted
                    if (currentPetId == petId) {
                        continue; // Skip the line to exclude the adopted pet
                    }
                }
            }

            // Add the line to the file contents
            fileContents.add(line);
        }
    } catch (IOException ex) {
        System.out.println("Error in reading from file");
        return;
    }

    // Update the file with modified pet information
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
        for (String line : fileContents) {
            writer.write(line);
            writer.newLine();
        }
    } catch (IOException ex) {
        System.out.println("Error in writing to file");
    }

    // Remove the pet from the list
    Pet petToRemove = null;
    for (Pet pet : petList) {
        if (pet.getPetId() == petId) {
            petToRemove = pet;
            break;
        }
    }

    if (petToRemove != null) {
        petList.remove(petToRemove);
        System.out.println("Pet with ID " + petId + " adopted successfully!");
    } else {
        System.out.println("Pet with ID " + petId + " not found.");
    }
}

// Check if pet exists
private static boolean petExists(int id) {
    for (Pet pet : petList) {
        if (pet.getPetId() == id) {
            return true;
        }
    }
    return false;
}
    
    // check int input
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
    
    public static void displayAllPets() {
    System.out.println("All saved pets:");
    for (Pet pet : petList) {
        System.out.println(pet);
    }
    System.out.println("______________________________");
}
  
}
    
    

