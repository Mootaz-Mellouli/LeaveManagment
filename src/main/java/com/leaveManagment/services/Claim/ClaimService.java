package com.leaveManagment.services.Claim;
import com.leaveManagment.entities.Claim;
import com.leaveManagment.entities.ClaimPriority;
import com.leaveManagment.entities.User;
import com.leaveManagment.repositories.ClaimRepo;
import com.leaveManagment.repositories.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ClaimService implements IClaimService {

    private final ClaimRepo claimRepo;

   private final UserRepo userRepo;

    @Override
    public Claim addClaim(Claim claim) {
        return claimRepo.save(claim);
    }

   @Override
    public String getClaimByStatus(Integer idUser, ClaimPriority claimPriority) {
        User user=userRepo.findById(idUser).orElse(null);
        return claimRepo.findClaimByClaimPriorityAndUser(claimPriority,user);
    }
}
