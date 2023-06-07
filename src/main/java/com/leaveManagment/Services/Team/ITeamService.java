package com.leaveManagment.Services.Team;

import com.leaveManagment.Entities.Leave;
import com.leaveManagment.Entities.Team;

import java.util.List;

public interface ITeamService {
    List<Team> retrieveAllTeams();

    Team addTeam(Team t);

    Team updateTeam (Team t);

    Team retrieveTeam (Integer idETeam);

    void deleteTeam(Integer idTeam);
    void affectUserToTeam(Integer idUser,Integer idTeam);
    void desaffectUserFromTeam(Integer idUser,Integer idTeam);
    List<Leave> getLeavesByTeam(Integer idTeam);
}