package com.leaveManagment.Services.Team;

import com.leaveManagment.Entities.Leave;
import com.leaveManagment.Entities.Team;
import com.leaveManagment.Entities.User;
import com.leaveManagment.Repositories.TeamRepository;
import com.leaveManagment.Repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamServiceImp implements ITeamService{

    private final TeamRepository teamRepository;
    private final UserRepository userRepository;

    @Override
    public List<Team> retrieveAllTeams() {
        return teamRepository.findAll();
    }

    @Override
    public Team addTeam(Team t) {
        return teamRepository.save(t);
    }

    @Override
    public Team updateTeam(Team t) {
        return teamRepository.save(t);
    }

    @Override
    public Team retrieveTeam(Integer idETeam) {
        return teamRepository.findById(idETeam).orElse(null);
    }

    @Override
    public void deleteTeam(Integer idTeam) {
        teamRepository.deleteById(idTeam);
    }

    @Override
    @Transactional
    public void affectUserToTeam(Integer idUser, Integer idTeam) {
        User user = userRepository.findById(idUser).orElse(null);
        Team team = teamRepository.findById(idTeam).orElse(null);
        Assert.notNull(user,"User ne doit pas etre null");
        Assert.notNull(team,"Team ne doit pas etre null");
        List<User> users = new ArrayList<>();
        users.add(user);
        team.setUserList(users);
        teamRepository.save(team);
    }

    @Override
    @Transactional
    public void desaffectUserFromTeam(Integer idUser, Integer idTeam) {
        User user = userRepository.findById(idUser).orElse(null);
        Team team = teamRepository.findById(idTeam).orElse(null);
        Assert.notNull(user,"User ne doit pas etre null");
        Assert.notNull(team,"Team ne doit pas etre null");
        team.getUserList().remove(user);
        teamRepository.save(team);
    }

    @Override
    public List<Leave> getLeavesByTeam(Integer idTeam) {
        Team team = teamRepository.findById(idTeam).orElse(null);
        Assert.notNull(team,"Team ne doit pas etre null");
        List<Leave> leaves = new ArrayList<>();
        team.getUserList().forEach(user -> leaves.addAll(user.getLeaves()) );
        return leaves;
    }
}
