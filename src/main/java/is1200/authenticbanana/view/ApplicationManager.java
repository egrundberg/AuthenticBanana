/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is1200.authenticbanana.view;

import is1200.authenticbanana.controller.ApplicationFacade;
import is1200.authenticbanana.execptions.DataBaseException;
import is1200.authenticbanana.model.Person;
import is1200.authenticbanana.model.PersonDTO;
import is1200.authenticbanana.model.Role;
import java.io.Serializable;
import java.util.Locale;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.validation.constraints.*;
import javax.faces.context.FacesContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Erik
 */
@ManagedBean(name = "applicationManager")
@SessionScoped
public class ApplicationManager implements Serializable {
    
    @EJB
    private ApplicationFacade applicationFacade;

    
   private final static Logger log = LogManager.getLogger(ApplicationManager.class);

    /**
     * Login variables
     */
    @NotNull
    @Size(min = 4, max = 32)
    private String username;
    @NotNull
    @Size(min = 8)
    private String password;
    private int loginsFailed = 0;
    
    private PersonDTO user;

    /**
     * New user variables
     */
    @Size(min = 1, max = 255)
    private String newUsername;
    @Size(min = 1, max = 255)
    private String name;
    @Size(min = 1, max = 255)
    private String surname;
    @Size(min = 4, max = 255)
    private String ssn;
    //@Pattern(regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message = "Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(min = 7, max = 255)
    private String email;
    @NotNull
    @Size(min = 4, max = 255)
    private String newPassword;
    @NotNull
    private Role roleId;

    // <editor-fold defaultstate="collapsed" desc="Getters, Setters and Constructors">
    public ApplicationManager() {  }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
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
     * @return the user
     */
    public PersonDTO getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(PersonDTO user) {
        this.user = user;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * @param surname the surname to set
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * @return the ssn
     */
    public String getSsn() {
        return ssn;
    }

    /**
     * @param ssn the ssn to set
     */
    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    /**
     * @return the mail
     */
    public String getMail() {
        return getEmail();
    }

    /**
     * @param email the mail to set
     */
    public void setMail(String email) {
        this.setEmail(email);
    }

    /**
     * @return the newUsername
     */
    public String getNewUsername() {
        return newUsername;
    }

    /**
     * @param newUsername the newUsername to set
     */
    public void setNewUsername(String newUsername) {
        this.newUsername = newUsername;
    }

    /**
     * @return the newPassword
     */
    public String getNewPassword() {
        return newPassword;
    }

    /**
     * @param newPassword the newPassword to set
     */
    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the roleId
     */
    public Role getRoleId() {
        return roleId;
    }

    /**
     * @param roleId the roleId to set
     */
    public void setRoleId(Role roleId) {
        this.roleId = roleId;
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="User Management">
    /**
     * ??
     *
     * @return
     */
    public String registerLink() {
        return "success";
    }
    
    public String registerUser() {
        if (applicationFacade.findPerson(newUsername) == null) {
            try {
                applicationFacade.registerUser(createPersonDTO());
            } catch (DataBaseException ex) {
                log.error(ex.getMessage());
                return "failure";
            }
            return "success";
        } else {
            return "failure";
        }
    }

    /**
     * ??
     *
     * @return
     */
    public String loginLink() {
        return "success";
    }
    
    public String findUser() {
        setUser(applicationFacade.findPerson(getUsername()));
        return "";
    }
    
    public String loginUser() {
        user = applicationFacade.loginPerson(username, password);
        if (user == null) {
            loginsFailed++;
            if (loginsFailed == 3) {
                log.error("Login failed three times in a row");
            }
            return "failure";
        } else {
            loginsFailed = 0;
            return applicationFacade.getRoleName(user.getRoleId(), new Locale("en"));
        }
    }

    // </editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Set Locale">
    public String setSvLocale() {
        Locale.setDefault(new Locale("sv"));
        FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale("sv"));
        return "";
    }
    
    public String setEnLocale() {
        Locale.setDefault(new Locale("en"));
        FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale("en"));
        return "";
    }
//</editor-fold>

    private PersonDTO createPersonDTO() {
        PersonDTO person = new Person();
        person.setUsername(newUsername);
        person.setPassword(newPassword);
        person.setName(name);
        person.setSurname(surname);
        person.setEmail(email);
        person.setSsn(ssn);
        person.setRoleId(roleId);
        return person;
    }
}
