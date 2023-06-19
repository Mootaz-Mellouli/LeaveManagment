package com.leaveManagment.repositories;

import com.leaveManagment.entities.TimeSheet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeSheetRepository extends JpaRepository<TimeSheet, Integer> {
}