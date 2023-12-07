/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg251project;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author baker
 */
public class AdminstratorTest {
    
    public AdminstratorTest() {
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

   
    @Test
    public void testAddEvent() {
        System.out.println("addEvent");
        String FILE_NAME = "EventDB.txt";
        String eventID = "1123";
        String eventName = "Test Event";
        String eventDate = "2023-12-03";
        String eventPlace = "Jeddah";
        Adminstrator instance = new Adminstrator();
        instance.addEvent(FILE_NAME, eventID, eventName, eventDate, eventPlace);
        String testedEvent=instance.getEventById(FILE_NAME,eventID);
        assertNotNull("Event should be added to the database",testedEvent);
        assertTrue("Retrieved event should contain EventID", testedEvent.contains("EventID=" + eventID));
        assertTrue("Retrieved event should contain EventName", testedEvent.contains("EventName=" + eventName));
        assertTrue("Retrieved event should contain EventDate", testedEvent.contains("EventDate=" + eventDate));
        assertTrue("Retrieved event should contain EventPlace", testedEvent.contains("EventPlace=" + eventPlace));

    }
    
}
