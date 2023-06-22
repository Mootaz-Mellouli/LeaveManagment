package com.leaveManagment.controllers;

import com.leaveManagment.entities.Leave;
import com.leaveManagment.entities.Team;
import com.leaveManagment.entities.User;
import com.leaveManagment.services.Team.ITeamService;
import com.leaveManagment.services.User.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
    private final IUserService userService;
    @GetMapping()
    public List<User> retrieveAllUsers (){
        return userService.retrieveAllUsers();
    }
    @PostMapping()
    public void addUser(@RequestBody User user) {
        userService.addUser(user);
    }

    @PutMapping()
    public User updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }
}
