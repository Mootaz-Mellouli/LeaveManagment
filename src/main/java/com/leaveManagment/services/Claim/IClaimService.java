package com.leaveManagment.services.Claim;

import com.leaveManagment.Entities.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IClaimService {
     public Boolean claimChecked(int id,int idUser);
    public void deleteClaim(int idClaim);
    public Claim updateClaim(int id, Claim claim);
     public List<Claim>  getClaimByPriority(ClaimPriority claimPriority);
    public Claim addClaimAndAssginToUser(Claim claim);
    public List<Claim> getAllClaim();
    public String sendEmail(User user,String subject, String body);
    public int countClaimByTeam(Team team);

}
