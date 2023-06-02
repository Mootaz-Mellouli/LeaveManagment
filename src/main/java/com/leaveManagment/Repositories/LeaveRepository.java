package com.leaveManagment.Repositories;

import com.leaveManagment.Entities.Leave;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaveRepository extends JpaRepository<Leave, Integer> {
}