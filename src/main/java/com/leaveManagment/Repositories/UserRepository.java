package com.leaveManagment.Repositories;

import com.leaveManagment.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserByMatricule( String matricule);
    User findUserByMatriculeAndPassword(String matricule , String password);
}