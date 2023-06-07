package com.leaveManagment.Repositories;

import com.leaveManagment.Entities.Claim;
import com.leaveManagment.Entities.ClaimPriority;
import com.leaveManagment.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClaimRepository extends JpaRepository<Claim,Integer> {
  List<Claim> findClaimByClaimPriority(ClaimPriority claimPriority);
}
