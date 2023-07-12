package com.leaveManagment.repositories;

import com.leaveManagment.entities.Claim;
import com.leaveManagment.entities.ClaimPriority;
import com.leaveManagment.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClaimRepository extends JpaRepository<Claim,Integer> {
  List<Claim> findClaimByClaimPriority(ClaimPriority claimPriority);
}
