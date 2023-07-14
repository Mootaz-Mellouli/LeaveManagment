package com.leaveManagment.services.Leave;


import com.leaveManagment.entities.Leave;
import com.leaveManagment.entities.LeaveStatus;


import java.util.List;

public interface ILeaveService {
    List<Leave> getAllLeaves();
    Leave getLeaveById(int idLeave);
    Leave addLeave(Leave leave, String matricule);
    Leave updateLeave(Leave leave, String matricule);
    void deleteLeave(int idLeave);
    List<Leave> getLeavesByUser(String matricule);
    List<Leave> getAllLeavesNotArchived();
    List<Leave> getArchivedLeaves();
}
