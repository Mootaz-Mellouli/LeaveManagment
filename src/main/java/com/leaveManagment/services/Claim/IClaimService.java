package com.leaveManagment.services.Claim;

import com.leaveManagment.entities.Claim;
import com.leaveManagment.entities.ClaimPriority;

public interface IClaimService {
    public Claim addClaim(Claim claim);
    public String  getClaimByStatus(Integer idUser, ClaimPriority claimPriority);
}
