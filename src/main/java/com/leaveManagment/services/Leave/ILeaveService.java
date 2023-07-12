package com.leaveManagment.services.Leave;

import com.leaveManagment.entities.Leave;

import java.util.List;

public interface ILeaveService {
    List<Leave> getAllLeaves();
    Leave getLeaveById(int idLeave);
    Leave addLeave(Leave leave);
    Leave updateLeave(Leave leave);
    void deleteLeave(int idLeave);
}
