package com.leaveManagment.services.User;

import com.leaveManagment.dto.LoginDTO;
import com.leaveManagment.entities.Leave;
import com.leaveManagment.entities.User;
import com.leaveManagment.LoginMessage;
import com.leaveManagment.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import java.util.UUID;

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
        Assert.notNull(u,"User is empty");
        u.setMatricule(generateMatricule());
        u.setPassword(passwordEncoder.encode(u.getPassword()));
        return userRepository.save(u);
    }
    @Override
    public User updateUser(User u) {
        return userRepository.save(u);
    }
    @Override
    public User retrieveUser(String matricule) {
        return userRepository.findUserByMatricule(matricule);
    }
    @Override
    public void deleteUser(String matricule) {
        Assert.notNull(matricule,"idUser is empty");
        userRepository.deleteUserByMatricule(matricule);
    }
    @Override
    public List<Leave> getLeavesByUser(String matricule) {
        User user = userRepository.findUserByMatricule(matricule);
        Assert.notNull(user,"User must be not null");
        List<Leave> leaves = new ArrayList<>();
        user.getLeaves().forEach(leave -> leaves.add(leave) );
        return leaves;
    }
    public User getCurrentUser() {
        return ((User) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal());
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
    private String generateMatricule() {
        String uniqueId = UUID.randomUUID().toString();
        String matricule = "user" + uniqueId;

        return matricule;
    }
}
