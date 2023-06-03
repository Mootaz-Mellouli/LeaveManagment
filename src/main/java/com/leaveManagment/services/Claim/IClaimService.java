package com.leaveManagment.services.Claim;

import com.leaveManagment.entities.Claim;
import com.leaveManagment.entities.ClaimPriority;
import com.leaveManagment.entities.Leave;

import java.util.List;

public interface IClaimService {
    public Claim addClaim(Claim claim);
    public void deleteClaim(int idClaim);
    public Claim updateClaim(Claim claim);
    public String  getClaimByStatus(Integer idUser, ClaimPriority claimPriority);
    public List<Claim> getAllClaim();

}
