/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is1200.authenticbanana.view;

import is1200.authenticbanana.controller.ApplicationFacade;
import is1200.authenticbanana.execptions.DataBaseException;
import is1200.authenticbanana.model.Availability;
import is1200.authenticbanana.model.AvailableJobs;
import is1200.authenticbanana.model.CompetenceProfile;
import is1200.authenticbanana.model.Person;
import is1200.authenticbanana.model.PersonDTO;
import is1200.authenticbanana.model.Role;
import java.io.Serializable;
import java.util.List;
import java.util.Locale;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.validation.constraints.*;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Erik
 */
@ManagedBean(name = "applicationManager")
@SessionScoped
public class ApplicationManager implements Serializable {

    //<editor-fold defaultstate="collapsed" desc="Variables">
    @EJB
    private ApplicationFacade applicationFacade;

    private final static Logger LOG = LogManager.getLogger(ApplicationManager.class);

    private PersonDTO user;
    private Locale locale = Locale.getDefault();

    /**
     * Login variables
     */
    @NotNull(message = "{usernameNull}")
    @Size(min = 1, max = 255)
    private String username;
    @NotNull(message = "{passwordNull}")
    private String password;

    /**
     * New user variables
     */
    @Size(min = 4, max = 255, message = "{usernameSizeError}")
    @NotNull
    private String newUsername;

    @Size(min = 1, max = 255, message = "{nameSizeError}")
    @NotNull
    private String name;

    @Size(min = 1, max = 255, message = "{surnameSizeError}")
    @NotNull
    private String surname;

    @Size(min = 4, max = 255, message = "{ssnSizeError}")
    @NotNull
    private String ssn;

    @Pattern(regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|"
            + "}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9"
            + "-]*[a-z0-9])?", message = "{invalidEmail")
    @Size(min = 4, max = 255, message = "{emailSizeError}")
    @NotNull
    private String email;

    @NotNull
    @Size(min = 4, max = 255, message = "{passwordSizeError}")
    private String newPassword;

    //RoleID is never set
    private final static long RECRUITER = 1L;
    private final static long APPLICANT = 2L;
    private String error;

    //</editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Getters, Setters and Constructors">
    /**
     *
     */
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
     * @return the locale
     */
    public Locale getLocale() {
        return locale;
    }

    /**
     * @param locale the locale to set
     */
    public void setLocale(Locale locale) {
        this.locale = locale;
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

    /**
     *
     * @return
     */
    public String registerUser() {
        if (applicationFacade.findPerson(newUsername) == null) {
            try {
                applicationFacade.registerUser(createPersonDTO());
            } catch (DataBaseException ex) {
                LOG.error(ex.getMessage());
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

    /**
     *
     * @return
     */
    public String loginUser() {
        user = applicationFacade.loginPerson(username, password);
        if (user == null) {
            error = "NotNull";
            return "";
        } else {
            error = null;
            HttpSession session = SessionBean.getSession();
            String roleName = applicationFacade.getRoleName(user.getRoleId());
            if (roleName.equals("recruiter")) {
                session.setAttribute("recruiter", roleName);
            } else {
                session.setAttribute("applicant", roleName);
            }
            return roleName;
        }
    }

    //Logout, invalidate session
    /**
     *
     * @return
     */
    public String logoutUser() {
        HttpSession session = SessionBean.getSession();
        session.invalidate();
        return "index";
    }

    private PersonDTO createPersonDTO() {
        PersonDTO person = new Person();
        person.setUsername(newUsername);
        person.setPassword(newPassword);
        person.setName(name);
        person.setSurname(surname);
        person.setEmail(email);
        person.setSsn(ssn);
        Role r = new Role();
        r.setRoleId(APPLICANT);
        person.setRoleId(r);
        return person;
    }

    // </editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Appliacant stuff">
    private List<AvailableJobs> availableJobs;
    private List<CompetenceProfile> competences;
    private List <Availability> availabilitydates;

    /**
     * @return the availableJobs
     */
    public List<AvailableJobs> getAvailableJobs() {
        availableJobs = applicationFacade.getAvailableJobs(locale);
        return availableJobs;
    }

    /**
     * @param availableJobs the availableJobs to set
     */
    public void setAvailableJobs(List<AvailableJobs> availableJobs) {
        this.availableJobs = availableJobs;
    }

    
      /**
     * @return the availableJobs
     */
    public List <Availability> getAvailabilityDates() {
        availabilitydates = applicationFacade.getAvailableDates(locale, user);
        return availabilitydates;
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Set Locale">
    /**
     *
     * @return
     */
    public String setSvLocale() {
        Locale.setDefault(new Locale("sv"));
        FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale("sv"));
        locale = new Locale("sv");
        return "";
    }

    /**
     *
     * @return
     */
    public String setEnLocale() {
        Locale.setDefault(new Locale("en"));
        FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale("en"));
        locale = new Locale("en");
        LOG.error("Set locale");
        return "";
    }
//</editor-fold>

    /**
     * @return the competences
     */
    public List<CompetenceProfile> getCompetences() {
        competences = applicationFacade.getPersonCompetences(locale, user);
        return competences;
    }

    /**
     * @param competences the competences to set
     */
    public void setCompetences(List<CompetenceProfile> competences) {
        this.competences = competences;
    }

    /**
     * @return the error
     */
    public String getError() {
        return error;
    }

    /**
     * @param error the error to set
     */
    public void setError(String error) {
        this.error = error;
    }
}
