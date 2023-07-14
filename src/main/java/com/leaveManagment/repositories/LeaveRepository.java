package com.leaveManagment.repositories;

import com.leaveManagment.entities.Leave;
import com.leaveManagment.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LeaveRepository extends JpaRepository<Leave, Integer> {

    List<Leave> getLeavesByIsArchivedIsFalse();
    List<Leave> getLeavesByIsArchivedIsTrue();

    @Query("select l from Leave l where l.user = ?1 and l.isArchived = false")
    List<Leave> getLeavesByUserAndIsArchivedIsFalse(User user);
}