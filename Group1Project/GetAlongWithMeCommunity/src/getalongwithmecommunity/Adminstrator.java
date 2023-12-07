import getalongwithmecommunity.Pet;
import java.io.*;
import java.util.ArrayList;


public class Adminstrator {
    
    
   public void removePetFromSystem(String FILE_NAME, Pet petToRemove, ArrayList<Pet> petList) {
    if (petToRemove != null && petList.contains(petToRemove)) {
        // Remove the pet from the list in memory
        petList.remove(petToRemove);

        // Read the entire file and store its contents
        ArrayList<String> fileContents = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Check if the line corresponds to the pet being adopted
                if (!line.startsWith("petId=" + petToRemove.getPetId())) {
                    fileContents.add(line);
                }
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
        System.out.println("Pet not found in the list. No modifications made.");
    }
}

    
    
    public boolean approveAdopting(ArrayList<Pet>petList,int petId){
        for (Pet pet : petList) {
            if (pet.getPetId() == petId) {
                return true;
            }
        }
        return false;
    }
    
    public boolean approveRegistration(ArrayList<Pet>petList,int petId){
        for (Pet pet : petList) {
            if (pet.getPetId() == petId) {
                return false;
            }
        }
        return true;
    }
    
     public void addEvent(String FILE_NAME,String eventID,String eventName,String eventDate,String eventPlace){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            writer.write("EventID="+eventID+" ,EventName="+eventName+" ,EventDate="+eventDate+" ,EventPlace="+eventPlace);
            System.out.println("Event information added successfully!");
        } catch (IOException ex) {
            System.out.println("Error in writing to file");
            }
    }
    
    
    public String getEventById(String fileName, String eventId) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] fields = line.split(",");
            if (fields.length > 0 && fields[0].contains(eventId)) {
                return line;}
        }
        
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Return null if the event with the specified ID is not found
        return null;
    }
}
