package com.example.demo;
import com.example.model.User;
import com.example.service.UserService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.io.*;

public class UserServiceTest {



    UserService userService = new UserService();


    @Test
    public void testSave() throws IOException {
        // Create a temporary file for testing
        File tempFile = File.createTempFile("temp_users", ".txt");
        String filePath = tempFile.getAbsolutePath();


        // Create a mock User object
        User user = new User("John", "Doe", "1234567890");

        // Call the save() method
        boolean result = userService.save(user,filePath);

        // Verify the result
        assertTrue(result);

        // Verify the content of the file
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line = reader.readLine();
            assertEquals("John,Doe,1234567890", line);
            assertNull(reader.readLine()); // Ensure there is only one line in the file
        }

        // Delete the temporary file
        tempFile.delete();
    }
    @Test
    public void testGetUsers() {
        // Create a temporary file with test data
        String filePath = "temp_users1.txt";
        User user = new User("Lee", "Rato", "0985654567");
        userService.save(user,filePath);

        // Call the getUsers() method
        List<User> users = userService.getUsers(filePath);

        assertEquals(1, users.size());

        // Verify the user data
        User user1 = users.get(0);
        assertEquals("Lee", user1.getFirstName());
        assertEquals("Rato", user1.getLastName());
        assertEquals("0985654567", user1.getContactNumber());
        deleteFile(filePath);
    }
    public static void deleteFile(String filePath) {
        File file = new File(filePath);

        if (file.exists()) {
            if (file.delete()) {
                System.out.println("File deleted successfully.");
            } else {
                System.out.println("Unable to delete the file.");
            }
        } else {
            System.out.println("File does not exist.");
        }
    }


}


