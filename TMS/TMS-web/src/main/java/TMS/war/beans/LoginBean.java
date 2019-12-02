/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package TMS.war.beans;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpSession;
import persistence.UserAccount;

/**
 *
 * @author ssome
 */
@Named(value = "loginBean")
@RequestScoped
public class LoginBean {
    private String userId;
    private String password;
    private String status;
    @PersistenceContext(unitName = "TMS-PU")
    private EntityManager em;
    @Resource
    private javax.transaction.UserTransaction utx;
    /**
     * Creates a new instance of LoginBean
     */
    public LoginBean() {
    }

    /**
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    public void Login() throws IOException {
         UserAccount acc = em.find(UserAccount.class, userId);
         if (acc != null) {
             try {
                 // check password
                 byte[] salt = acc.getSalt();
                 String saltString = new String(salt, "UTF-8");
                 String checkPass = saltString+password;
                 MessageDigest digest = MessageDigest.getInstance("SHA-256");
                 byte[] checkPassHash = digest.digest(checkPass.getBytes("UTF-8"));
                 if (Arrays.equals(checkPassHash, acc.getPassword())) {
                     //login ok - set user in session context
                     HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
                     if(acc.getRole().equals("student")){
                        session.setAttribute("Student", acc);
                        FacesContext.getCurrentInstance().getExternalContext().redirect("/TMS-web/faces/student/operationInterface.xhtml");
                     }else{
                        session.setAttribute("Teacher", acc);
                        FacesContext.getCurrentInstance().getExternalContext().redirect("/TMS-web/faces/teacher/operationInterface.xhtml");
                     }                  
                     status="Login Successful - " + "Welcome " + acc.getFirstname(); 
                 } else {
                    status="Invalid Login, Please Try again"; 
                 }
             } catch (UnsupportedEncodingException | NoSuchAlgorithmException ex) {
                 Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
             }
         } else {
             status="Invalid Login, Please Try again";
         }
    }

    public String logout() throws IOException {
        // invalidate session to remove User
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        session.invalidate();
        FacesContext.getCurrentInstance().getExternalContext().redirect("/TMS-web/faces/index.xhtml");
        // navigate to index - see faces-config.xml for navigation rules
        return "logout";
    }
    
}
