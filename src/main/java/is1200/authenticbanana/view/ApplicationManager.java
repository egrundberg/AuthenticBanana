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
    @Size(min = 4, max = 32)
    private String username;
    @NotNull
    @Size(min = 8)
    private String password;
    private PersonDTO user;
    Locale swedishLocale = new Locale("sv");
    Locale noSwedishLocale = new Locale("en", "GB");
    private String location = Locale.getDefault().getLanguage();

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
    
        /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
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
    public int getSsn() {
        return ssn;
    }

    /**
     * @param ssn the ssn to set
     */
    public void setSsn(int ssn) {
        this.ssn = ssn;
    }

    /**
     * @return the mail
     */
    public String getMail() {
        return mail;
    }

    /**
     * @param mail the mail to set
     */
    public void setMail(String mail) {
        this.mail = mail;
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="User Management">
   public String registerLink(){
       return "success";
   }
   
   public String loginLink(){
       return "success";
   }

    public String findUser() {
        setUser(applicationFacade.findPerson(getUsername()));
        return "";
    }

    public String loginUser() {
        user = applicationFacade.loginPerson(username, password);
        if (user == null) {
            return "failure";
        } else {
            return applicationFacade.getRoleName(user.getRoleId(), Locale.getDefault());
        }
    }
    // </editor-fold>

    public String changeLocale() {
        if (Locale.getDefault().getLanguage().equals(swedishLocale.getLanguage())) {
            location = Locale.getDefault().getLanguage();
            Locale.setDefault(noSwedishLocale);
        } else {
            location = Locale.getDefault().getLanguage();
            Locale.setDefault(swedishLocale);
        }
        return "";
    }

    /**
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(String location) {
        this.location = location;
    }
}
