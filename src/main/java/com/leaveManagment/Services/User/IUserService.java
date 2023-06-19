package com.leaveManagment.services.User;

import com.leaveManagment.dto.LoginDTO;
import com.leaveManagment.entities.Leave;
import com.leaveManagment.entities.User;
import com.leaveManagment.LoginMessage;

import java.util.List;

public interface IUserService {
    List<User> retrieveAllUsers();

    User addUser(User u);

    User updateUser (User u);

    User retrieveUser (String matricule);

    void deleteUser(String matricule);
    List<Leave> getLeavesByUser(String matricule);
    LoginMessage loginUser(LoginDTO loginDTO);
    User getCurrentUser();
}
