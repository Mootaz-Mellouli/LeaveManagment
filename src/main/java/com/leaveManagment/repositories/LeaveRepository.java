package com.leaveManagment.repositories;

import com.leaveManagment.entities.Leave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LeaveRepository extends JpaRepository<Leave, Integer> {

    List<Leave> getLeavesByIsArchivedIsFalse();
    List<Leave> getLeavesByIsArchivedIsTrue();
}