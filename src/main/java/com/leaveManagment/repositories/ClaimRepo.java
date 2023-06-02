package com.leaveManagment.repositories;

import com.leaveManagment.entities.Claim;
import com.leaveManagment.entities.ClaimPriority;
import com.leaveManagment.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClaimRepo extends JpaRepository<Claim,Integer> {
  String findClaimByClaimPriorityAndUser(ClaimPriority claimPriority, User user);

}
