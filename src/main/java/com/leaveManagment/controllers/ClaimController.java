package com.leaveManagment.controllers;

import com.leaveManagment.entities.Claim;
import com.leaveManagment.entities.ClaimPriority;
import com.leaveManagment.services.Claim.ClaimService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/claim")
@AllArgsConstructor
public class ClaimController {
    private final ClaimService claimService;

    @PostMapping("/addClaim")
    public Claim addClaim(@RequestBody Claim claim) {
        return claimService.addClaim(claim);
    }

    @GetMapping("/getStatut/{idUser}")
    public String  getClaimByStatus(@PathVariable Integer idUser,@RequestBody ClaimPriority claimPriority){
        return claimService.getClaimByStatus(idUser,claimPriority);
   }
}
