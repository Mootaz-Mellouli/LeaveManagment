package com.leaveManagment.Controllers;

import com.leaveManagment.Entities.Leave;
import com.leaveManagment.Entities.Team;
import com.leaveManagment.Services.Team.ITeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/team")
@RequiredArgsConstructor
@CrossOrigin
public class TeamController {
    private final ITeamService teamService;
    @GetMapping()
    public List<Team> retrieveAllTeams (){
        return teamService.retrieveAllTeams();
    }

    @GetMapping("/{team-id}")
    public Team retrieveTeam(@PathVariable("team-id") Integer teamId) {
        return teamService.retrieveTeam(teamId);
    }

    @PostMapping()
    public void addTeam(@RequestBody Team team) {

        teamService.addTeam(team);
    }

    @PutMapping()
    public Team updateTeam(@RequestBody Team team) {
        return teamService.updateTeam(team);
    }

    @DeleteMapping("/{team-id}")
    public void removeTeam(@PathVariable("team-id") Integer teamId) {
        teamService.deleteTeam(teamId);
    }

    @PutMapping("/{user-id}/{team-id}")
    public void affectUserToTeam(@PathVariable("user-id") Integer userid,@PathVariable("team-id") Integer teamId) {
         teamService.affectUserToTeam(userid,teamId);
    }
    @PutMapping("/desaffectUserToTeam/{user-id}/{team-id}")
    public void desaffectUserToTeam(@PathVariable("user-id") Integer userid,@PathVariable("team-id") Integer teamId) {
        teamService.desaffectUserFromTeam(userid,teamId);
    }
    @GetMapping("/retrieveLeavesByTeam/{team-id}")
    public List<Leave> retrieveLeavesByTeam(@PathVariable("team-id") Integer teamId) {
        return teamService.getLeavesByTeam(teamId);
    }
}
