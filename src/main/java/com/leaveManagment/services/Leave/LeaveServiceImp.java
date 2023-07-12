package com.leaveManagment.services.Leave;

import com.leaveManagment.entities.*;
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
        // TODO : number of days => demi-journéé actif seulement quand on choisit la meme date
        User user = new User();
       /* user.setFirstName("mootaz");
        user.setLastName("mellouli");
        leave.setUser(user);*/
        leave.setLeaveStatus(LeaveStatus.IN_PROGRESS);
        if (leave.getLeaveType() == LeaveType.CG_PAYE) {
            leave.setLeavePriority(checkLeavePriorityCGPaye(user));
        }
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
    public List<Leave> getAllLeavesNotArchived() {
        return leaveRepository.getLeavesByIsArchivedIsFalse();
    }

    @Override
    public List<Leave> getArchivedLeaves() {
        return leaveRepository.getLeavesByIsArchivedIsTrue();
    }

    public void isLeaveArchived(int idLeave) throws Exception {
        Leave leave = leaveRepository.findById(idLeave).orElse(null);
        if (leave != null && leave.isArchived()) {
            throw new IllegalAccessException();
        }
    }

    public void calculConge(User user, Leave leave) {
        // 2.5 jours par mois
        // 30 jours max par an
        // anciennete de salarié dans l'entreprise
        // situation familial
        // congé d'été => 24 jours max
        // quand le congé approuvé la calendrier est mis a jour
        float leaveBalance = user.getLeaveBalance();
        LeavePriority leavePriority = getLeavePriority(leave.getLeaveType(), user);
        if (leaveBalance > 0)
        {


        }
    }

    public void soldeConge() {
        // TODO : shceduler a chaque fin d'annee remize a zero du solde
    }

    public LeavePriority getLeavePriority(LeaveType leaveType, User user)
    {
        switch(leaveType) {
            case EVEN_FAM_DECES:
            case CG_MALADIE:
                return LeavePriority.HIGH;
            case CG_MATERN:
                if (user.getChildren() > 0)
                    return LeavePriority.MEDIUM;
            case CG_PATERN:
                if (user.getChildren() > 0)
                    return LeavePriority.MEDIUM;
            default:
                return LeavePriority.LOW;
        }
    }

    public LeavePriority checkLeavePriorityCGPaye(User user) {
        user.getTeamList();
        return null;
    }
}