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
    
    public void createTeam(){
        if (teamEJB.createTeam()) {
            setStatus("Created!!!!!");
        } else {
            setStatus("Creation failed. Fuck you!!!");
        }
    }
    
}
