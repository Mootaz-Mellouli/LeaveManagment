package com.leaveManagment.controllers;

import com.leaveManagment.Entities.Claim;
import com.leaveManagment.Entities.ClaimPriority;
import com.leaveManagment.services.Claim.ClaimService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/claim")
@AllArgsConstructor
public class ClaimController {
    private final ClaimService claimService;

    @PostMapping("/addClaim/{idUser}")
    public Claim addClaimAndAssginToUser(@RequestBody Claim claim, @PathVariable int idUser) {
        return claimService.addClaimAndAssginToUser(claim,idUser);
    }

   /* @GetMapping("/getStatut/{idUser}")
    public String  getClaimByStatus(@PathVariable Integer idUser,@RequestBody ClaimPriority claimPriority){
        return claimService.getClaimByStatus(idUser,claimPriority);
   }*/
}
