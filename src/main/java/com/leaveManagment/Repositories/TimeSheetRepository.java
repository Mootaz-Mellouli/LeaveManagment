package com.leaveManagment.Repositories;

import com.leaveManagment.Entities.TimeSheet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeSheetRepository extends JpaRepository<TimeSheet, Integer> {
}