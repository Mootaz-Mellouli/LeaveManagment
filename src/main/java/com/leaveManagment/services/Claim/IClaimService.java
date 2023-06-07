package com.leaveManagment.services.Claim;

import com.leaveManagment.Entities.Claim;
import com.leaveManagment.Entities.ClaimPriority;
import com.leaveManagment.Entities.Team;
import com.leaveManagment.Entities.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IClaimService {
    // public Claim addClaim(Claim claim);
    public void deleteClaim(int idClaim);
    public Claim updateClaim(Claim claim, int idUser);
     public List<Claim>  getClaimByPriority(ClaimPriority claimPriority);
    public Claim addClaimAndAssginToUser(Claim claim);
    public List<Claim> getAllClaim();
    public String sendEmail(User user,String subject, String body);
    public int countClaimByTeam(Team team);

}
