package com.leaveManagment.services.Team;

import com.leaveManagment.entities.Leave;
import com.leaveManagment.entities.Team;
import com.leaveManagment.entities.User;

import java.util.List;

public interface ITeamService {
    List<Team> retrieveAllTeams();

    Team addTeam(Team t);

    Team updateTeam (Team t);

    Team retrieveTeam (Integer idETeam);

    void archiveTeam(Integer idTeam);
    void affectUserToTeam(Integer idUser,Integer idTeam);
    void desaffectUserFromTeam(Integer idUser,Integer idTeam);
    List<Leave> getLeavesByTeam(Integer idTeam);
}
