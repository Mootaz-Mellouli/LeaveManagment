package com.leaveManagment.Repositories;

import com.leaveManagment.Entities.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Integer> {
}