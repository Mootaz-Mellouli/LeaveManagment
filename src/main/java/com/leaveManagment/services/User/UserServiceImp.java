package com.leaveManagment.services.User;

import com.leaveManagment.entities.Leave;
import com.leaveManagment.entities.Team;
import com.leaveManagment.entities.User;
import com.leaveManagment.repositories.TeamRepository;
import com.leaveManagment.repositories.UserRepository;
import com.leaveManagment.services.Team.ITeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements IUserService {

    private final TeamRepository teamRepository;
    private final UserRepository userRepository;


    @Override
    public List<User> retrieveAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void addUser(User u) {
        userRepository.save(u);
    }

    @Override
    public User updateUser(User u) {
        return userRepository.save(u);
    }

}
