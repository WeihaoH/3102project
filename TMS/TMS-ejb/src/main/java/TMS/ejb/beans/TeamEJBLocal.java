/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TMS.ejb.beans;

import javax.ejb.Local;
import TMS.ejb.persistence.Team;
import java.util.List;

/**
 *
 * @author RoyLiu
 */
@Local
public interface TeamEJBLocal {
    void create(Team team);
    
    void edit(Team team);
    
    void remove(Team team);
    
    Team find(Object id);
    
    List<Team> findAll();
    
    List<Team> findRange(int[] range);
    
    int count();
    
    public boolean createTeam();
}
