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
    
    
    public void searchForPets(String FILE_NAME){
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
    
    public Pet adoptPet(int petId,ArrayList<Pet>petList){
        Pet petToRemove = null;
        for (Pet pet : petList) {
            if (pet.getPetId() == petId) {
                return petToRemove;
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
    
    
    public void volunteerForCommuntiy(){
        volunteer=true;}

    
    public void updateVolunteerStatus(String FILE_NAME, boolean newVolunteerStatus) {
    try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME));
         BufferedWriter writer = new BufferedWriter(new FileWriter("temp.txt"))) {

        String line;
        while ((line = reader.readLine()) != null) {
            if (line.contains("Volunteer=" + name)) {
                writer.write("Volunteer=" + name + "," + age + "," + phone + "," + email + "," + city + "," + newVolunteerStatus);
            } else {
                writer.write(line);
            }
            writer.newLine();
        }
    } catch (IOException e) {
        e.printStackTrace();
    }

    // Rename the temporary file to the original file
    File tempFile = new File("temp.txt");
    File originalFile = new File(FILE_NAME);
    tempFile.renameTo(originalFile);
}


     @Override
    public String toString() {
        return "User Name: " + name + "Age: " + age + "Phone: " + phone + "Email: " + email + "City: " + city + "Volunteer: " + volunteer;
    }
    
}
