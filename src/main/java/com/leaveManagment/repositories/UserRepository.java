package com.leaveManagment.repositories;

import com.leaveManagment.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserByMatricule(String matricule);
    void deleteUserByMatricule(String matricule);
    User findUserByMatriculeAndPassword(String matricule , String password);
}