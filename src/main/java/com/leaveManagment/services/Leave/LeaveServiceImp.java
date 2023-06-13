package com.leaveManagment.services.Leave;

import com.leaveManagment.entities.Leave;
import com.leaveManagment.entities.LeaveStatus;
import com.leaveManagment.repositories.LeaveRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.transaction.Transactional;
import java.util.List;
@Service
@RequiredArgsConstructor
@Transactional
public class LeaveServiceImp implements ILeaveService{
    private final LeaveRepository leaveRepository;
    @Override
    public List<Leave> getAllLeaves() {
        return leaveRepository.findAll();
    }
    @SneakyThrows
    @Override
    public Leave getLeaveById(int idLeave) {
        isLeaveArchived(idLeave);
        return leaveRepository.findById(idLeave).orElseThrow(ChangeSetPersister.NotFoundException::new);
    }
    @Override
    public Leave addLeave(Leave leave) {
        return leaveRepository.save(leave);
    }
    @SneakyThrows
    @Override
    public Leave updateLeave(Leave leave) {
        isLeaveArchived(leave.getId());
        return leaveRepository.save(leave);
    }
    @Override
    public void deleteLeave(int idLeave) {
        Leave leave = leaveRepository.findById(idLeave).orElse(null);
        Assert.notNull(leave, "leave not found");
        if (!leave.isArchived()) {
            leave.setArchived(true);
        }
    }

    @Override
    public List<Leave> getLeavesByUser(int idUser) {
        return null;
    }

    @Override
    public Boolean leaveResponse(int idLeave, LeaveStatus leaveStatus) {
        Leave leave = leaveRepository.findById(idLeave).orElse(null);
        if (leave != null) {
            leave.setLeaveStatus(leaveStatus);
            return true;
        }
        return false;
    }

    @Override
    public List<Leave> getAllLeavesNotArchived() {
        return leaveRepository.getLeavesByIsArchivedIsFalse();
    }

    public void isLeaveArchived(int idLeave) throws Exception {
        Leave leave = leaveRepository.findById(idLeave).orElse(null);
        if (leave != null && leave.isArchived()) {
            throw new IllegalAccessException();
        }
    }
}