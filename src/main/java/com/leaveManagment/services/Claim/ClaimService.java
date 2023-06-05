package com.leaveManagment.services.Claim;
import com.leaveManagment.Entities.Claim;
import com.leaveManagment.Entities.ClaimPriority;
import com.leaveManagment.Entities.User;
import com.leaveManagment.Repositories.UserRepository;
import com.leaveManagment.Repositories.ClaimRepository;
import com.leaveManagment.services.User.IUserService;
import com.leaveManagment.services.User.UserServiceImp;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ClaimService implements IClaimService {

    private final ClaimRepository claimRepository;
    private final UserServiceImp userService;
    private final UserRepository userRepo;

    @Override
    public Claim addClaim(Claim claim) {

        return claimRepository.save(claim);
    }

    @Override
    public void deleteClaim(int idClaim) {
        claimRepository.deleteById(idClaim);
    }

    @Override
    public Claim updateClaim(Claim claim, int idUser) {
        User user=userRepo.findById(idUser).orElse(null);
        claim.setUserClaim(user);
        return claimRepository.save(claim);
    }

    @Override
    public Claim addClaimAndAssginToUser(Claim claim, int idUser) {
        /*User currentUser = userService.getCurrentUser();
        if (currentUser != null && currentUser.getIdUser() == idUser) {
            claim.setUserClaim(currentUser);
            return claimRepository.save(claim);
        } else {
            throw new RuntimeException("User not found or unauthorized"); // Handle the case when the user is not found or unauthorized
        }*/

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            User currentUser = null;

            if (authentication.getPrincipal() instanceof User) {
                currentUser = (User) authentication.getPrincipal();
            } else if (authentication.getPrincipal() instanceof User) {
                currentUser = ((User) authentication.getPrincipal());
            }

            if (currentUser != null && currentUser.getIdUser() == idUser) {
                claim.setUserClaim(currentUser);
                return claimRepository.save(claim);
            } else {
                throw new RuntimeException("User not found or unauthorized");
            }
        } else {
            throw new RuntimeException("User not found or unauthorized");
        }
    }

   /* @Override
    public String getClaimByStatus(Integer idUser, ClaimPriority claimPriority) {
        User user=userRepo.findById(idUser).orElse(null);
        return claimRepository.findClaimByClaimPriorityAndUser(claimPriority,user);
    }*/

    @Override
    public List<Claim> getAllClaim() {
        return claimRepository.findAll();
    }
}
