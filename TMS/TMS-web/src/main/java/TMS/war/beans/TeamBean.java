/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TMS.war.beans;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.ejb.EJB;
import TMS.ejb.beans.TeamEJBLocal;
import TMS.ejb.persistence.Team;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author RoyLiu
 */
@Named(value = "teamBean")
@RequestScoped
public class TeamBean {
    @EJB
    private TeamEJBLocal teamEJB;
    private String status;
    private List<Team> teams;
    
    /**
     * Creates a new instance of TeamBean
     */
    public TeamBean() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }
    
    public void createTeam(){
        if (teamEJB.createTeam()) {
            setStatus("Created.");
        } else {
            setStatus("Creation failed.");
        }
    }
    
    public String findAllTeams(){
        List<Team> resultList = teamEJB.findAll();
        if (resultList != null) {
            setTeams(new ArrayList<>(resultList));
        }
        for (Team team : resultList) {
            
        }
        return "home";
    }        
}
