package com.example.service;
import com.example.model.User;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;

@Service
public class UserService {
    private static final String FILE_PATH = "users.txt";
    public boolean save(User user, String FILE_PATH) {
        try (FileWriter writer = new FileWriter(FILE_PATH, true)) {
            writer.write(user.getFirstName() + "," + user.getLastName() + "," + user.getContactNumber() + "\n");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false; // User saved successfully
        }

        }


    public List<User> getUsers(String FILE_PATH) {
        List<User> users = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {

            String line;
            while ((line = reader.readLine()) != null) {

                String[] userData = line.split(",");

                if (userData.length == 3) {

                    String firstName = userData[0];

                    String lastName = userData[1];

                    String contactNumber = userData[2];
                    User user = new User(firstName, lastName, contactNumber);
                    users.add(user);
                }
            }
        } catch (IOException e) {

            e.printStackTrace();

        }
        return users;
}
    public boolean updateUser(String contactNumber, User updatedUser, String FILE_PATH) {
        List<User> users = getUsers(FILE_PATH);

        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            if (user.getContactNumber().equals(contactNumber)) {
                users.set(i, updatedUser);
                saveUsersToFile(users,FILE_PATH);
                return true;
            }
        }

        return false;
    }

    private void saveUsersToFile(List<User> users,String FILE_PATH) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH))) {
            for (User user : users) {
                writer.println(user.getFirstName() + "," + user.getLastName() + "," + user.getContactNumber());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public boolean deleteUser(String contactNumber,String FILE_PATH) {
        List<User> users = getUsers(FILE_PATH);
        Iterator<User> iterator = users.iterator();

        boolean userDeleted = false;
        while (iterator.hasNext()) {
            User user = iterator.next();
            if (user.getContactNumber().equals(contactNumber)) {
                iterator.remove();
                userDeleted = true;
            }
        }

        if (userDeleted) {
            saveUsersToFile(users,FILE_PATH);
        }

        return userDeleted;
    }
}












