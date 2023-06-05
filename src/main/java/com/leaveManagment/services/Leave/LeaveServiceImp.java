package com.leaveManagment.services.Leave;

import com.leaveManagment.Entities.Leave;
import com.leaveManagment.Repositories.LeaveRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class LeaveServiceImp implements ILeaveService{
    private final LeaveRepository leaveRepository;
    @Override
    public List<Leave> getAllLeaves() {
        return leaveRepository.findAll();
    }
    @Override
    public Leave getLeaveById(int idLeave) {
        return leaveRepository.findById(idLeave).orElse(null);
    }
    @Override
    public Leave addLeave(Leave leave) {
        return leaveRepository.save(leave);
    }
    @Override
    public Leave updateLeave(Leave leave) {
        return leaveRepository.save(leave);
    }
    @Override
    public void deleteLeave(int idLeave) {
        leaveRepository.deleteById(idLeave);
    }
}