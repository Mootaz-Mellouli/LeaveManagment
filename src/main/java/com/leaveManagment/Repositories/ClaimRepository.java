package com.leaveManagment.Repositories;

import com.leaveManagment.Entities.Claim;
import com.leaveManagment.Entities.ClaimPriority;
import com.leaveManagment.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClaimRepository extends JpaRepository<Claim,Integer> {
 // String findClaimByClaimPriorityAndUser(ClaimPriority claimPriority, User user);

}
