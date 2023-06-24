package com.leaveManagment.controllers;

import com.leaveManagment.Entities.*;
import com.leaveManagment.services.Claim.ClaimService;
import com.leaveManagment.services.Claim.IClaimService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/claim")
@AllArgsConstructor
public class ClaimController{
    private final ClaimService claimService;

    @DeleteMapping("/deleteClaim/{idClaim}")
    @CrossOrigin
    public void deleteClaim(@PathVariable int idClaim, @RequestParam ClaimStatus claimStatus) {
        claimService.deleteClaim(idClaim,claimStatus);
    }

    @PutMapping("/updateClaim")
    public Claim updateClaim(@RequestBody Claim claim, @PathVariable int idUser) {
        return claimService.updateClaim(claim,idUser);
    }

    @PostMapping("/addClaim")
    @CrossOrigin
    public Claim addClaimAndAssginToUser(@RequestBody Claim claim) {
        return claimService.addClaimAndAssginToUser(claim);
    }

    @GetMapping("/getAllClaim")
    @CrossOrigin
    public List<Claim> getAllClaim() {
        return claimService.getAllClaim();
    }

    /* @PostMapping("/sendMail")
     public String sendEmail(String to, String cc, String subject, String body){
         return claimService.sendEmail(to,cc,subject,body);
     }*/
    @PostMapping("/sendMail")
    public String sendEmail(User user, String subject, String body){
        return claimService.sendEmail(user,subject,body);
    }

    @GetMapping("/count")
    public int countClaimByTeam( Team team) {
        return claimService.countClaimByTeam(team);
    }

    @GetMapping("/getPriority/{claimPriority}")
    public List<Claim>  getClaimByPriority(@PathVariable("claimPriority") ClaimPriority claimPriority){
        return claimService.getClaimByPriority(claimPriority);
   }
}
