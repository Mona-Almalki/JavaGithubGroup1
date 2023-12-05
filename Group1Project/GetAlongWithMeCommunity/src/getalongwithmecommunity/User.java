package getalongwithmecommunity;

import getalongwithmecommunity.Pet;
import java.io.*;
import java.util.ArrayList;

public class User {
    
    private String name;
    private int age;
    private String phone;
    private String email;
    private String city;
    private Boolean volunteer=false;

    public User(String name, int age, String phone, String email, String city) {
        this.name = name;
        this.age = age;
        this.phone = phone;
        this.email = email;
        this.city = city;
    }
    
    public String getName() {
        return name;
    }
    
    public boolean isVolunteer() {
        return volunteer;
    }
    

    public void registerUser(String FILE_NAME) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(toString());
            writer.newLine();
            System.out.println("User information has been successfully registered.");
            System.out.println("________________________________________________________________________");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    public String searchForPets(String FILE_NAME){
        String lines="";
        try(BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines+=line;
            }
        }catch (IOException ex) {
           return "error in reading file";
        }
        return lines;
    }
    
    public Pet adoptPet(int petId,ArrayList<Pet>petList){
        Pet petToRemove = null;
        for (Pet pet : petList) {
            if (pet.getPetId() == petId) {
                return petToRemove=pet;
            }
        }
        return petToRemove; 
    }
    
    
    public void addPetForAdopting(String FILE_NAME,Pet pet){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(pet.toString());
            writer.newLine();
            recieveApproval();
            
        } catch (IOException e) {
            e.printStackTrace();}
       
    }
    
    public void recieveApproval(){
        System.out.println("Pet information has been successfully registered.");
        System.out.println("________________________________________________________________________");

    }
    
    
    // Method to update volunteer status based on username
    public static void updateVolunteerStatus(String userName, boolean status) {
        ArrayList<String> fileContents = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("UserDB.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("User Name= " + userName)) {
                    // Update the volunteer status in the line
                    line = line.replace("Volunteer= false", "Volunteer= " + status);
                }
                fileContents.add(line);
            }
        } catch (IOException ex) {
            System.out.println("Error in reading from file");
            return;
        }

        // Overwrite the file with modified volunteer status
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("UserDB.txt"))) {
            for (String line : fileContents) {
                writer.write(line);
                writer.newLine();
            }
            System.out.println("Volunteer status updated successfully!");
        } catch (IOException ex) {
            System.out.println("Error in writing to file");
        }
    }
    
    public static void displayAllVolunteers() {
        try (BufferedReader reader = new BufferedReader(new FileReader("UserDB.txt"))) {
            String line;
            System.out.println("Volunteers:");

            while ((line = reader.readLine()) != null) {
                if (line.contains("Volunteer= true")) {
                    System.out.println(line);
                }
            }

            System.out.println("______________________________");
        } catch (IOException ex) {
            System.out.println("Error in reading from file");
        }
    }

    
     @Override
    public String toString() {
        return "User Name= " + name + " Age= " + age + " Phone= " + phone + " Email= " + email + " City= " + city + " Volunteer= " + volunteer +"\n";
    }
    
}
