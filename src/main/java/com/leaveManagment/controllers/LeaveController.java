package com.leaveManagment.controllers;

import com.leaveManagment.Entities.Leave;
import com.leaveManagment.services.Leave.ILeaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/leave")
public class LeaveController {
    private final ILeaveService leaveService;
    @GetMapping
    public List<Leave> getAllLeaves() {
        return leaveService.getAllLeaves();
    }
    @GetMapping("/{idLeave}")
    public Leave getLeaveById(@PathVariable int idLeave) {
        return leaveService.getLeaveById(idLeave);
    }
    @PostMapping("/add")
    public Leave addLeave(@RequestBody Leave leave) {
        return leaveService.addLeave(leave);
    }
    @PutMapping("/update")
    public Leave updateLeave(@RequestBody Leave leave) {
        return leaveService.updateLeave(leave);
    }
    @DeleteMapping("/delete/{idLeave}")
    public void deleteLeave(@PathVariable int idLeave) {
        leaveService.deleteLeave(idLeave);
    }
}