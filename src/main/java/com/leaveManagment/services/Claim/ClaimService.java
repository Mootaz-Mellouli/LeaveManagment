package com.leaveManagment.services.Claim;
import com.leaveManagment.Entities.*;
import com.leaveManagment.Repositories.UserRepository;
import com.leaveManagment.Repositories.ClaimRepository;
import com.leaveManagment.services.User.UserServiceImp;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.internet.MimeMessage;
import java.util.List;

@Service
@AllArgsConstructor
public class ClaimService implements IClaimService {

    private final ClaimRepository claimRepository;
    private final UserServiceImp userService;
    private final UserRepository userRepo;
    private final JavaMailSender javaMailSender;

    /*@Override
    public Claim addClaim(Claim claim) {

        return claimRepository.save(claim);
    }*/

    @Override
    public void deleteClaim(int idClaim, ClaimStatus claimStatus) {
        Claim claim = claimRepository.findById(idClaim).orElse(null);
        if (claim != null && claim.getClaimStatus() == ClaimStatus.ON_HOLD) {
            claimRepository.deleteById(idClaim);
        } else {
            throw new IllegalStateException("The claim can only be deleted if its status is 'ON_HOLD'.");
        }
    }

    @Override
    public Claim updateClaim(Claim claim, int idUser) {
        User user=userRepo.findById(idUser).orElseThrow(() -> new IllegalArgumentException("user not found with this id "+idUser));
        claim.setUserClaim(user);
        return claimRepository.save(claim);
    }

    @Override
    public List<Claim> getAllClaim() {
        return claimRepository.findAll();
    }
    @Override
    public Claim addClaimAndAssginToUser(Claim claim) {
        // User currentUser = userService.getCurrentUser();
        User currentUser =new User();
        currentUser.setIdUser(2);
        currentUser.setEmail("amdouniamani777@gmail.com");
        currentUser.setPassword("bicha1234");
        currentUser.setMatricule("AAN");
        currentUser.setRole(Role.ADMIN);

        Assert.isNull(currentUser,"User not found or unauthorized");

        if (currentUser != null ) {
            claim.setUserClaim(currentUser);
            Claim savedClaim=claimRepository.save(claim);

            Role role = currentUser.getRole();
            if (role == Role.ADMIN || role == Role.SUPER_ADMIN) {
                sendEmail(currentUser, "New Claim Added", claim.getDescription());
            }
            return savedClaim;
        } else {
            throw new RuntimeException("User not found or unauthorized");
        }
    }
    @Value("${spring.mail.username}")
    private String fromEmail;
    @Override
    public String sendEmail(User user,String subject, String body) {
       try {
           MimeMessage mimeMessage=javaMailSender.createMimeMessage();
           MimeMessageHelper mimeMessageHelper=new MimeMessageHelper(mimeMessage,true);
           mimeMessageHelper.setFrom(user.getEmail());
           // supposons que cette email est l'email de  ADMIN
           mimeMessageHelper.setTo(fromEmail);
           mimeMessageHelper.setSubject(subject);
           mimeMessageHelper.setText(body);
           // mimeMessageHelper.setCc(cc);
           // mimeMessageHelper.setSubject("the "+user.getFirstName()+" "+user.getLastName()+"create claim");
           // mimeMessageHelper.setText(claim.getDescription());
           javaMailSender.send(mimeMessage);
           return "mail send";
       }catch (Exception e){
           throw new RuntimeException(e);
       }
    }

    @Override
    public int countClaimByTeam(Team team) {
        List<Claim> allClaims = claimRepository.findAll();
        int count = 0;
        for (Claim claim : allClaims) {
            User userClaim = claim.getUserClaim();
            for (Team userTeam : userClaim.getTeamList()) {
                if (userTeam.getNameTeam().equals(team.getNameTeam())) {
                    count++;
                    break;
                }
            }
        }
        return count;
    }
    @Override
    public List<Claim> getClaimByPriority(ClaimPriority claimPriority) {
     return claimRepository.findClaimByClaimPriority(claimPriority);
    }
}
