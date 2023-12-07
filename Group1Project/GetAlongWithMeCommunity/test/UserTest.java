

package pkg251project;

import java.io.*;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;



public class UserTest {
    
    public UserTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    

    
    
    
    /**
     * Test of adoptPet method, of class User.
     */
    @Test
    public void testAdoptPet() {
        System.out.println("adoptPet");
        Pet pet1 = new Pet(1001, "Leon", "Cat",1);
        Pet pet2 = new Pet(1002, "Max", "Dog",3);
        
        ArrayList<Pet> petList = new ArrayList<>();
        petList.add(pet1);
        petList.add(pet2);
        
        User test=new User();
        int petId1=petList.get(0).getPetId();
        Pet test1=test.adoptPet(petId1, petList);
        
        int petId2=petList.get(1).getPetId();
        Pet test2=test.adoptPet(petId2, petList);
        
        //null
        Pet test3=test.adoptPet(1000, petList);
        
        //Testing
        assertNotNull("Pet info that will remove",test1);
        assertNotNull("Pet info that will remove",test2);
        
        assertNull("No pet with this PetId",test3);
        
        assertEquals(petId1,1001);
        assertEquals(petId2,1002);
        
    }
    
     
    @Test
    public void testAddPetForAdopting() {
        System.out.println("addPetForAdopting");
        
        String FILE_NAME = "TestPetDB.txt";
        try (PrintWriter writer = new PrintWriter(FILE_NAME)) {
            writer.print("");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Pet pet1 = new Pet(1001, "Leon", "Cat",1);
        User instance = new User();
        
        // Test
        instance.addPetForAdopting(FILE_NAME, pet1);
        String petDetails = instance.searchForPets(FILE_NAME);
        assertTrue("Pet details should be present in the file", petDetails.contains(pet1.toString()));
        assertEquals(pet1.toString(),petDetails);
    }

    
    
     
    @Test
    public void testUpdateVolunteerStatus() {
        System.out.println("updateVolunteerStatus");
        String name="Mona";
        boolean isVol=true;
        //Mona in UserDB are not volunteer 
        User.updateVolunteerStatus(name, isVol);
        // to Check if the volunteer status has been updated:
        assertEquals(isVol,getVolunteerStatus(name));
        
    }
    
    public boolean getVolunteerStatus(String userName) {
        try (BufferedReader reader = new BufferedReader(new FileReader("UserDB.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("User Name= " + userName)) {
                    return line.contains("Volunteer= true") ? true : false;
                }
            }
        } catch (IOException ex) {
            System.out.println("Error in reading from file");
        }
        
        return false;
    }


    
    

}
