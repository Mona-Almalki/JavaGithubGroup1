package getalongwithmecommunity;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.io.*;




public class GetAlongWithMeCommunity {

    private static final String FILE_NAME = "InfoFile.txt";
    private static final LinkedList volunteerList = new LinkedList();
    private static final ArrayList<Pet> petList = new ArrayList<>();

    public static void main(String[] args) {
        
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
                        registerVolunteer(scanner, volunteerList);
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
                    PrintInfo(); break;
                    
                case 4:
                    adopt(scanner);
                    break;
                case 5:
                    System.out.println("Exiting Get Along with me community System. Goodbye!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }
        
    }
    
    
    //Display menu
    private static void displayMenu(){
        System.out.println("\n                Welcome to Get Along with Me Community                ");
        System.out.println("________________________________________________________________________");
        System.out.println("Services:");
        System.out.println("1. Register a Volunteer");
        System.out.println("________________________________________________________________________");
        System.out.println("2. Register a Pet");
        System.out.println("________________________________________________________________________");
        System.out.println("3. Show all saved Pet information");
        System.out.println("________________________________________________________________________");
        System.out.println("4. Adopt a Pet");
        System.out.println("________________________________________________________________________");
        System.out.println("5. Exit");
        System.out.print("\nEnter your choice: ");
    }
    
    
    
    /// read from file 
    public static void ReadFile(){
        
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
                
                
                //Read Volunteer info from file
                else if(parts[0].startsWith("Volunteer")) {
                    String[] volunteerInfoParts = parts[0].split("=");
                    if (volunteerInfoParts.length == 2) {
                        String name = volunteerInfoParts[1].trim();
                        int age = Integer.parseInt(parts[1].substring(parts[1].indexOf("=") + 1).trim());
                        String phone = parts[2].substring(parts[2].indexOf("=") + 1).trim();
                        String email = parts[3].substring(parts[3].indexOf("=") + 1).trim();
                        String city = parts[4].substring(parts[4].indexOf("=") + 1).trim();

                        volunteerList.append(name, age, phone, email, city);} 
                    else {
                        System.out.println("Invalid line: " + line); }
    }}}catch (IOException | NumberFormatException e){}

    }
    
    
    // Print pet info 
    public static void PrintPetInfo(){
        try(BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if(line.contains("petId")&&line.contains("Approve"))
                    System.out.println(line);
                
            }
       
        }catch (IOException ex) {
           System.out.print("error in reading file");
        }
    }
    
    
    
    // Volunteer registration function
    public static void registerVolunteer(Scanner scanner, LinkedList volunteerList) {
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

        Volunteer volunteer = new Volunteer(name, age, phone, email, city);
        volunteerList.append(name, age, phone, email, city);

        // Save volunteer information to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(volunteer.toString());
            writer.newLine();
            System.out.println("Volunteer registered successfully!");
            System.out.println("________________________________________________________________________");

        } catch (IOException e) {}
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

     public static void adopt(Scanner scanner) {
    System.out.println("\n********** Adopt a Pet **********");

    System.out.print("Enter the pet ID: ");
    int petId = getValidIntInput(scanner);

    // Find the pet with the given ID
    Pet petToRemove = null;
    for (Pet pet : petList) {
        if (pet.getPetId() == petId) {
            petToRemove = pet;
            break;
        }
    }

    // Remove the pet from the list
    if (petToRemove != null) {
        petList.remove(petToRemove);
        System.out.println("Pet with ID " + petId + " adopted successfully!");

        // Read the entire file and store its contents
        ArrayList<String> fileContents = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.startsWith("Volunteer")) { // Exclude volunteer lines
                    String[] petInfo = line.split(",");
                    int currentPetId = Integer.parseInt(petInfo[0].split("=")[1]);

                    // Check if the line corresponds to the pet being adopted
                    if (currentPetId == petId) {
                        continue; // Skip the line to exclude the adopted pet
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
            System.out.println("Pet information updated successfully!");
        } catch (IOException ex) {
            System.out.println("Error in writing to file");
        }
    } else {
        System.out.println("Pet with ID " + petId + " not found.");
    }
}
    
    
    
    // check if pet exist
    private static boolean petExists(int id) {
        for (Pet pet: petList) 
           if(pet.getPetId()==id) 
               return true;
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

    
}
    
    

