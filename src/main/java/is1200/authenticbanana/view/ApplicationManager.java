/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is1200.authenticbanana.view;

import is1200.authenticbanana.controller.ApplicationFacade;
import is1200.authenticbanana.model.PersonDTO;
import java.io.Serializable;
import java.util.Locale;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.validation.constraints.*;

/**
 *
 * @author Erik
 */
@ManagedBean(name = "applicationManager")
@SessionScoped
public class ApplicationManager implements Serializable {

    @EJB
    private ApplicationFacade applicationFacade;
    @NotNull
    @Size(min=4, max=32)
    private String username;
    @NotNull
    @Size(min=8)
    private String password;
    private PersonDTO user;
    Locale local = Locale.getDefault();
    
    // <editor-fold defaultstate="collapsed" desc="Getters, Setters and Constructors">
    public ApplicationManager() {
    }

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

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="User Management">
    public String findUser() {
        setUser(applicationFacade.findPerson(getUsername()));
        return "";
    }

    public String loginUser() {
        user = applicationFacade.loginPerson(username, password);
        if (user == null) {
            return "failure";
        } else {
            return applicationFacade.getRoleName(user.getRoleId(), local);
        }
    }
    // </editor-fold>
    
    public String changeLocale(){
        String hej = "hej";
   return hej; }
}
