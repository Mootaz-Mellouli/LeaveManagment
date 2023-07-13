package com.leaveManagment.controllers;

import com.leaveManagment.entities.Leave;
import com.leaveManagment.services.Leave.ILeaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/leave")
@CrossOrigin
public class LeaveController {
    private final ILeaveService leaveService;
    @PreAuthorize("hasAnyRole('ADMIN', 'SUPER_ADMIN')")
    @GetMapping("/all")
    public List<Leave> getAllLeaves() {
        return leaveService.getAllLeaves();
    }
    @GetMapping("/{idLeave}")
    public Leave getLeaveById(@PathVariable int idLeave) {
        return leaveService.getLeaveById(idLeave);
    }
    @PostMapping("/add/{matricule}")
    public Leave addLeave(@RequestBody Leave leave,@PathVariable String matricule) {
        return leaveService.addLeave(leave,matricule);
    }
    @PutMapping("/update")
    public Leave updateLeave(@RequestBody Leave leave) {
        return leaveService.updateLeave(leave);
    }
    @DeleteMapping("/delete/{idLeave}")
    public void deleteLeave(@PathVariable int idLeave) {
        leaveService.deleteLeave(idLeave);
    }
    @PreAuthorize("hasAnyRole('ADMIN', 'SUPER_ADMIN')")
    @GetMapping
    public List<Leave> getAllLeavesNotArchived() {
        return leaveService.getAllLeavesNotArchived();
    }
    @PreAuthorize("hasAnyRole('ADMIN', 'SUPER_ADMIN')")
    @GetMapping("/archived")
    public List<Leave> getArchivedLeaves() {
        return leaveService.getArchivedLeaves();
    }
    /*@PutMapping("/response")
    public Leave leaveResponse(Leave leave) {
        return leaveService.leaveResponse(leave);
    }*/
}