package com.leaveManagment.repositories;

import com.leaveManagment.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findUserByMatricule(String matricule);
    void deleteUserByMatricule(String matricule);
    User findUserByMatriculeAndPassword(String matricule , String password);
}