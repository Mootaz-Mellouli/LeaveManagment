package com.leaveManagment.repositories;

import com.leaveManagment.entities.Leave;
import com.leaveManagment.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}