/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package TMS.war.beans;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Date;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import persistence.UserAccount;

/**
 *
 * @author ssome
 */
@Named(value = "signInBean")
@RequestScoped
public class SignInBean {
    
    private String userId;
    private String firstname;
    private String lastname;
    private String email;   
    private String password;
    private String roletype;
    @PersistenceContext(unitName = "TMS-PU")
    private EntityManager em;
    @Resource
    private javax.transaction.UserTransaction utx;
    
    private String status;

    
    
   private static final Map<String,Object> roleVal; 
   static{
      roleVal = new LinkedHashMap<>();
      roleVal.put("Student", "student");
      roleVal.put("Teacher", "teacher");
   }

   public Map<String, Object> getRoleVal() {
      return roleVal;
   }
    /**
     * Creates a new instance of SignInBean
     */
    public SignInBean() {
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
     * @return the firstname
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * @param firstname the firstname to set
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * @return the lastname
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * @param lastname the lastname to set
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * @return the birthDate
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param Email the Email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

	public String getRoletype() {
		return roletype;
	}

	public void setRoletype(String roletype) {
		this.roletype = roletype;
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
     * @return the status message
     */
    public String getStatus() {
        return status;
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
    
    public void addUser() {
        try {
            UserAccount acc = new UserAccount();
            acc.setUserId(userId);
            acc.setFirstname(firstname);
            acc.setLastname(lastname);
            acc.setEmail(email);
            System.out.println(roletype);
            acc.setRole(roletype);
            // randomly generate salt value
            final Random r = new SecureRandom();
            byte[] salt = new byte[32];
            r.nextBytes(salt);
            String saltString = new String(salt, "UTF-8");
            // hash password using SHA-256 algorithm
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            String saltedPass = saltString+password;
            byte[] passhash = digest.digest(saltedPass.getBytes("UTF-8"));
            acc.setSalt(salt);
            acc.setPassword(passhash);
            persist(acc);
            status="New Account Created Fine";
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException | RuntimeException ex ) {
            Logger.getLogger(SignInBean.class.getName()).log(Level.SEVERE, null, ex);
            status="Error While Creating New Account";
        }
    }
    
}
