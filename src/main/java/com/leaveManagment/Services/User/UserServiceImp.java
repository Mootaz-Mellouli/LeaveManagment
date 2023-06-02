package com.leaveManagment.Services.User;

import com.leaveManagment.Dto.LoginDTO;
import com.leaveManagment.Entities.Leave;
import com.leaveManagment.Entities.User;
import com.leaveManagment.LoginMessage;
import com.leaveManagment.Repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements IUserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;
    @Override
    public List<User> retrieveAllUsers() {
        return userRepository.findAll();
    }
    @Override
    public User addUser(User u) {
        u.setPassword(passwordEncoder.encode(u.getPassword()));
        return userRepository.save(u);
    }
    @Override
    public User updateUser(User u) {
        return userRepository.save(u);
    }
    @Override
    public User retrieveUser(Integer idUser) {
        return userRepository.findById(idUser).orElse(null);
    }
    @Override
    public void deleteUser(Integer idUser) {
        userRepository.deleteById(idUser);
    }
    @Override
    public List<Leave> getLeavesByUser(Integer idUser) {
        User user = userRepository.findById(idUser).orElse(null);
        Assert.notNull(user,"User must be null");
        List<Leave> leaves = new ArrayList<>();
        user.getLeaves().forEach(leave -> leaves.add(leave) );
        return leaves;
    }
    @Override
    public LoginMessage loginUser(LoginDTO loginDTO) {
        User user = userRepository.findUserByMatricule(loginDTO.getMatricule());
        if (user != null) {
            String password = loginDTO.getPassword();
            String encodedPassword = user.getPassword();
            Boolean isPwdRight = passwordEncoder.matches(password, encodedPassword);
            if (isPwdRight) {
                Optional<User> user1 = Optional.ofNullable(userRepository.findUserByMatriculeAndPassword(loginDTO.getMatricule(), encodedPassword));
                if (user1.isPresent()) {
                    return new LoginMessage("Login Success", true);
                } else {
                    return new LoginMessage("Login Failed", false);
                }
            } else {
                return new LoginMessage("password Not Match", false);
            }
        }else {
            return new LoginMessage("Matricule not exits", false);
        }
    }
}
