/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is1200.authenticbanana.view;

import is1200.authenticbanana.controller.ApplicationFacade;
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

/**
 *
 * @author Erik
 */
@ManagedBean(name = "applicationManager")
@SessionScoped
public class ApplicationManager implements Serializable {

    @EJB
    private ApplicationFacade applicationFacade;

    /**
     * Login variables
     */
    @NotNull
    @Size(min = 4, max = 32)
    private String username;
    @NotNull
    @Size(min = 8)
    private String password;

    private PersonDTO user;

    /**
     * New user variables
     */
    @NotNull
    @Size(min = 1, max = 255)
    private String newUsername;
    @Size(min = 1, max = 255)
    private String name;
    @Size(min = 1, max = 255)
    private String surname;
    @Size(min = 4, max = 255)
    private String ssn;
    @Pattern(regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message = "Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(min = 7, max = 255)
    private String email;
    @NotNull
    @Size(min = 4, max = 255)
    private String newPassword;
    @NotNull
    private Role roleId;

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
        return email;
    }

    /**
     * @param mail the mail to set
     */
    public void setMail(String email) {
        this.email = email;
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="User Management">
    /**
     * Register new user
     *
     * @return
     */
    public String registerLink() {
        return "success";
    }

    public String registerUser() {
        if (applicationFacade.findPerson(newUsername) != null) {
            applicationFacade.registerUser(createPersonDTO());
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
            return "failure";
        } else {
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
