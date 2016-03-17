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
import java.math.BigInteger;
import java.util.List;
import java.util.Locale;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.validation.constraints.*;
import javax.faces.context.FacesContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.security.*;
import is1200.authenticbanana.model.Application;

/**
 * Includes Variables, Getter, setter and constructors, User management,
 * applicant, recruiter and Set locale.
 *
 * Handles validation constraints, login, register new user, logout, roles
 * (applicant/recruiter), encryption and pdf generator.
 *
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
    @NotNull(message = "{mayNotBeNull}")
    private String newUsername;

    @Size(min = 1, max = 255, message = "{nameSizeError}")
    @NotNull(message = "{mayNotBeNull}")
    private String name;

    @Size(min = 1, max = 255, message = "{surnameSizeError}")
    @NotNull(message = "{mayNotBeNull}")
    private String surname;

    @Size(min = 4, max = 255, message = "{ssnSizeError}")
    @NotNull(message = "{mayNotBeNull}")
    private String ssn;

    @Pattern(regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|"
            + "}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9"
            + "-]*[a-z0-9])?", message = "{invalidEmail}")
    @Size(min = 4, max = 255, message = "{emailSizeError}")
    @NotNull(message = "{mayNotBeNull}")
    private String email;

    @NotNull(message = "{mayNotBeNull}")
    @Size(min = 4, max = 255, message = "{passwordSizeError}")
    private String newPassword;

    //RoleID is never set
    private final static long RECRUITER = 1L;
    private final static long APPLICANT = 2L;
    private String error;
    private long jobID;
    private AvailableJobs currentJob;
    private String role;
    private String databaseError = null;

//</editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Getters, Setters and Constructors">
    /**
     * A constructor without parameters
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

    /**
     * @param role the role to set
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * @return the databaseError
     */
    public String getDatabaseError() {
        return databaseError;
    }

    /**
     * @param databaseError the databaseError to set
     */
    public void setDatabaseError(String databaseError) {
        this.databaseError = databaseError;
    }

// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="User Management">
    /**
     * The user registers. If user was able to be registered
     *
     * @return success otherwise return failure
     *
     */
    public String registerUser() {
        if (applicationFacade.findPerson(newUsername) == null) {
            try {
                applicationFacade.registerUser(createPersonDTO());
            } catch (DataBaseException ex) {
                LOG.error(ex.getMessage());
                setDatabaseError("NotNull");
                return "failure";
            }
            databaseError = null;
            return "success";
        } else {
            setDatabaseError("NotNull");
            return "failure";
        }
    }

    /**
     * Handles login. If user exists, session starts and roleName is set
     *
     * @return roleName otherwise return ""
     */
    public String loginUser() throws NullPointerException {
        //LOG.error("Password sent: " + MD5(password));
        user = applicationFacade.loginPerson(username, MD5(password));
        if (user == null) {
            error = "NotNull";
            //throw new NullPointerException("Could not create user");
            return "";
        } else {
            error = null;
            String roleName = applicationFacade.getRoleName(user.getRoleId());
            LOG.error(roleName);
            return roleName;
        }
    }

    /**
     * Logout user. close session and sets user to null
     *
     * @return index
     */
    public String logoutUser() {
        user = null;
        return "index";
    }

    private PersonDTO createPersonDTO() {
        PersonDTO person = new Person();
        person.setUsername(newUsername);
        person.setPassword(MD5(newPassword));
        person.setName(name);
        person.setSurname(surname);
        person.setEmail(email);
        person.setSsn(ssn);
        Role r = new Role();
        r.setRoleId(APPLICANT);
        person.setRoleId(r);
        return person;
    }

    /**
     * Checks if the user has a role assigned to it
     *
     * @throws SecurityException If the user does not have the right privileges
     * to view the page
     */
    public void findApplicantRole() throws SecurityException {

        if (user == null || !user.getRoleId().getName().equals("applicant")) {
            throw new SecurityException("Not Logged In As Applicant");
        }
    }

    /**
     * Checks if the user has a role assigned to it
     *
     * @throws SecurityException If the user does not have the right privileges
     * to view the page
     */
    public void findRecruiterRole() throws SecurityException {
        if (user == null || !user.getRoleId().getName().equals("recruiter")) {
            throw new SecurityException("Not Logged In As Recruiter");
        }
    }

    /**
     * Hashes a word with MD5 hash
     *
     * @param word The word to be hashed
     * @return A hashed version of the word
     */
    public static String MD5(String word) {
        MessageDigest m;
        try {
            m = MessageDigest.getInstance("MD5");
            m.update(word.getBytes(), 0, word.length());
            word = new BigInteger(1, m.digest()).toString(16);
            //LOG.error("MD5: " + word);
            return word;
        } catch (NoSuchAlgorithmException ex) {
            LOG.error("MD5: " + word);
        }
        return word;
    }
// </editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Appliacant Stuff">
    private List<AvailableJobs> availableJobs;
    private List<CompetenceProfile> competences;
    private List<Availability> availabilitydates;

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
     * Saves jobID for chosen available job
     *
     * @param jobID
     * @return apply
     */
    public String apply(long jobID) {
        this.jobID = jobID;
        return "apply";
    }

    /**
     *
     * @return currentJob
     */
    public AvailableJobs getCurrentJob() {
        currentJob = applicationFacade.getCurrentJob(jobID, locale);
        return currentJob;
    }

    /**
     * Set currentJob
     *
     * @param job
     */
    public void setCurrentJob(AvailableJobs job) {
        currentJob = job;
    }

    /**
     * @return the available dates for user
     */
    public List<Availability> getAvailabilityDates() {
        availabilitydates = applicationFacade.getAvailableDates(locale, user);
        return availabilitydates;
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Set Locale">
    /**
     * Set locale to sv
     *
     * @return ""
     */
    public String setSvLocale() {
        Locale.setDefault(new Locale("sv"));
        FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale("sv"));
        locale = new Locale("sv");
        return "";
    }

    /**
     * Set locale to en
     *
     * @return ""
     */
    public String setEnLocale() {
        Locale.setDefault(new Locale("en"));
        FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale("en"));
        locale = new Locale("en");
        LOG.error("Set locale");
        return "";
    }
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Recruiter Stuff">

    private List<Application> applications;
    private List<AvailableJobs> jobs;

    /**
     * @return the applications
     */
    public List<Application> getApplications() {
        if (jobID != 0) {
            return applicationFacade.getApplications(jobID);
        } else {
            return null;
        }
    }

    /**
     * @param applications the applications to set
     */
    public void setApplications(List<Application> applications) {
        this.applications = applications;
    }

    /**
     * Sets the id for which applications to show
     *
     * @param jobID
     * @return The String view
     */
    public String findApplications(long jobID) {
        this.jobID = jobID;
        return "view";
    }

    private String pdfURL;

    /**
     * Generate a PDF document of all applications for a specific job
     *
     * @param appId The id of the application to be generated
     * @return The String "success" if the pdf was successfully generated
     */
    public String toPdf(Long appId) {
        pdfURL = applicationFacade.toPdf(appId, user);
        if (pdfURL == null) {
            return null;
        } else {
            return "success";
        }
    }

    /**
     * @return the jobs
     */
    public List<AvailableJobs> getJobs() {
        jobs = applicationFacade.getJobs(locale);
        return jobs;
    }

    /**
     * @param jobs the jobs to set
     */
    public void setJobs(List<AvailableJobs> jobs) {
        this.jobs = jobs;
    }

    /**
     * @return the pdfURL
     */
    public String getPdfURL() {
        return pdfURL;
    }

    /**
     * @param pdfURL the pdfURL to set
     */
    public void setPdfURL(String pdfURL) {
        this.pdfURL = pdfURL;
    }
//</editor-fold>
}
