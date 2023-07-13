package com.leaveManagment.services.Leave;

import com.leaveManagment.entities.*;
import com.leaveManagment.repositories.LeaveRepository;
import com.leaveManagment.repositories.UserRepository;
import com.leaveManagment.services.User.IUserService;
import com.leaveManagment.services.User.UserServiceImp;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.transaction.Transactional;
import java.util.List;
@Service
@RequiredArgsConstructor
@Transactional
public class LeaveServiceImp implements ILeaveService{
    private final LeaveRepository leaveRepository;
    private final UserServiceImp userService;
    private final UserRepository userRepository;

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
    @SneakyThrows
    @Override
    public Leave addLeave(Leave leave, String matricule) {
        // TODO : number of days => demi-journéé actif seulement quand on choisit la meme date
        // ne7sbou ken wakt el type congé solde
        /*User user = userService.getCurrentUser2();
        if ( user != null) {
            leave.setUser(user);
        }*/
        Boolean teamAvailability;
        User user = userRepository.findUserByMatricule(matricule).orElse(null);
        if (user != null) {
            leave.setUser(user);
            leave.setTeamAvailability(checkTeamAvailability(user, leave));
        }
        if (leave.getStartDate().after(leave.getEndDate()) || (leave.getEndDate().before(leave.getStartDate()))) {
            throw new IllegalAccessException();
        }
        leave.setLeavePriority(getLeavePriority(leave.getLeaveType()));
        leave.setLeaveStatus(LeaveStatus.IN_PROGRESS);

        /*if (leave.getLeaveType() == LeaveType.CG_PAYE) {
            leave.setLeavePriority(checkLeavePriorityCGPaye(user));
        }*/
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
        //LeavePriority leavePriority = getLeavePriority(leave.getLeaveType(), user);
        if (leaveBalance > 0)
        {


        }
    }

    public void soldeConge() {
        // TODO : shceduler a chaque fin d'annee remize a zero du solde
    }

    public LeavePriority getLeavePriority(LeaveType leaveType)
    {
        // TODO : USER.getchildredn
        switch(leaveType) {
            case EVEN_FAM_DECES:
            case CG_MALADIE:
                return LeavePriority.HIGH;
            case CG_MATERN:
                    return LeavePriority.MEDIUM;
            case CG_PATERN:
                    return LeavePriority.MEDIUM;
            default:
                return LeavePriority.LOW;
        }
    }

    public Boolean checkTeamAvailability(User user, Leave leave) {
        Team team = user.getTeamUser();
        List<User> userList = userRepository.findUsersByTeamUserAndLeavesStartDateGreaterThanAndLeavesEndDateLessThan(
                team,
                leave.getStartDate(),
                leave.getEndDate()
        );
        System.out.println(userList.size());
        float numberUsersByTeam = team.getUserList().size(); //5
        int pourcentage = Math.round((30.0f * numberUsersByTeam) / 100.0f); // 1.5
        System.out.println(numberUsersByTeam);
        System.out.println(pourcentage);
        if(userList.size() >= pourcentage) {
            return false;
        }
        return true;
        // get leaves by date start and debut => if akther mil 30% => ? dire non
    }
}