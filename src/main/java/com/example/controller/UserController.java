package com.example.controller;

import com.example.model.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController

public class UserController {
    private static final String FILE_PATH = "users.txt";
    private final UserService userService;
    @Autowired
    public UserController(UserService userService){
        this.userService= userService;
    }
@GetMapping("/")
public String homeRoute(){
       return "WELCOME TO RESTFUL OPERATIONS" ;
}

    @PostMapping("/save")
    public String  createUser(@RequestBody User user){

        boolean isSaved = userService.save(user,FILE_PATH);
        if(isSaved){
            return "user Saved Successfully";
        }
        return "failed to Save User";
    }
    @GetMapping("/getAll")
    public List<User> getUsers(){
        return userService.getUsers(FILE_PATH);

    }
    @PutMapping("/update")
    public String updateUser(@RequestBody User user){
       boolean isUpdated = userService.updateUser(user.getContactNumber(),user,FILE_PATH);
        if(isUpdated){
            return "user Updated Successfully";
        }
        return "failed to Update User";
    }
    @DeleteMapping("/delete")
    public String deleteUser(@RequestBody User user){
        boolean isDeleted = userService.deleteUser(user.getContactNumber(),FILE_PATH);//based on the object i have ive decided to use contact number as a unique identifier for deleting to avoid using first and last name as that would delete people who have identical names
        if(isDeleted){
            return "user Deleted Successfully";
        }
        return "failed to Delete User";
    }

}
