package com.leaveManagment.controllers;

import com.leaveManagment.Dto.LoginDTO;
import com.leaveManagment.Entities.Leave;
import com.leaveManagment.Entities.User;
import com.leaveManagment.LoginMessage;
import com.leaveManagment.services.User.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@CrossOrigin
public class UserController {
    private final IUserService iUserService;
    @GetMapping()
    public List<User> retrieveAllUsers (){return iUserService.retrieveAllUsers();}
    @GetMapping("/{user-id}")
    public User retrieveUser(@PathVariable("user-id") Integer userId) { return iUserService.retrieveUser(userId);}
    @PostMapping("/addUser")
    public User addUser(@RequestBody User user) { return iUserService.addUser(user); }

    @PutMapping("/updateUser")
    public User updateTeam(@RequestBody User user) {return iUserService.updateUser(user);}

    @DeleteMapping("/{user-idUser}")
    public void removeUser(@PathVariable("user-idUser") Integer userId) {iUserService.deleteUser(userId);}

    @GetMapping("/retrieveLeavesByUser/{userid}")
    public List<Leave> retrieveLeavesByTeam(@PathVariable("userid") Integer userId) {
        return iUserService.getLeavesByUser(userId);
    }
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginDTO loginDTO){
        LoginMessage loginMessage = iUserService.loginUser(loginDTO);
        return ResponseEntity.ok(loginMessage);
    }
}
