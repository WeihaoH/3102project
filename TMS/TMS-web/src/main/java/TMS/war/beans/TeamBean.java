/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TMS.war.beans;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import persistence.Team;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author RoyLiu
 */
@Named(value = "teamBean")
@RequestScoped
public class TeamBean {
    @PersistenceContext(unitName = "TMS-PU")
    private EntityManager em;
    @Resource
    private javax.transaction.UserTransaction utx;
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
        Team team = new Team(UUID.randomUUID().toString());
        try {
            persist(team);
            setStatus("Created.");
        } catch (Exception e){
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            setStatus("Creation failed.");
        }
    }
    
    public String findAllTeams(){
        try {
            Query query = em.createQuery("SELECT t FROM Team t");
            setTeams(query.getResultList());
        } catch (Exception e) {
            System.err.println(e);
        }
        return "student/operationInterface";
    }        
    
    public void persist(Object object) {
        try {
            utx.begin();
            em.persist(object);
            utx.commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            throw new RuntimeException(e);
        }
    }
}
