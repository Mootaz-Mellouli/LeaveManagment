package com.leaveManagment.services.User;

import com.leaveManagment.entities.Leave;
import com.leaveManagment.entities.Team;
import com.leaveManagment.entities.User;

import java.util.List;

public interface IUserService {
    List<User> retrieveAllUsers();
    void addUser(User t);

    User updateUser (User t);
}
