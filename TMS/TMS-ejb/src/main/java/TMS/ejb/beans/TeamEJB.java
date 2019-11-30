/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TMS.ejb.beans;

import javax.ejb.Stateless;
import TMS.ejb.persistence.Team;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.UUID;


/**
 *
 * @author RoyLiu
 */
@Stateless
public class TeamEJB extends AbstractFacade<Team> implements TeamEJBLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext(unitName = "SEG3102_TMS-EJB-PU")
    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager(){
        return em;
    }
    
    public TeamEJB(){
        super(Team.class);
    }
    
    @Override
    public boolean createTeam(){
        Team team = new Team(UUID.randomUUID().toString());
        try {
            create(team);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
