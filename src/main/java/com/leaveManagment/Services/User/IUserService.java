package com.leaveManagment.Services.User;

import com.leaveManagment.Dto.LoginDTO;
import com.leaveManagment.Entities.Leave;
import com.leaveManagment.Entities.User;
import com.leaveManagment.LoginMessage;

import java.util.List;

public interface IUserService {
    List<User> retrieveAllUsers();

    User addUser(User u);

    User updateUser (User u);

    User retrieveUser (Integer idUser);

    void deleteUser(Integer idUser);
    List<Leave> getLeavesByUser(Integer idUser);
    LoginMessage loginUser(LoginDTO loginDTO);
    User getCurrentUser();
}
