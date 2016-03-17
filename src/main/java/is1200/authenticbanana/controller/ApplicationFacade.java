/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * is1200.authenticbanana.controller is a package for comunication between the view and the database
 */
package is1200.authenticbanana.controller;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import is1200.authenticbanana.execptions.DataBaseException;
import is1200.authenticbanana.model.Application;
import is1200.authenticbanana.model.Availability;
import is1200.authenticbanana.model.AvailableJobs;
import is1200.authenticbanana.model.CompetenceProfile;
import is1200.authenticbanana.model.Language;
import is1200.authenticbanana.model.LanguagePK;
import is1200.authenticbanana.model.Person;
import is1200.authenticbanana.model.PersonDTO;
import is1200.authenticbanana.model.Role;
import java.io.FileOutputStream;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This class acts as a controller for fetching and storing data in a database
 *
 * @author Erik
 */
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@Stateful
public class ApplicationFacade {

    //<editor-fold defaultstate="collapsed" desc="Variables">
    @PersistenceContext(unitName = "is1200_AuthenticBanana_war_1.0PU")
    private EntityManager em;
    private final static Logger log = LogManager.getLogger(ApplicationFacade.class);
    private int loginsFailed = 0;

//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Login/Register">
    /**
     * Find a user with a specific username
     *
     * @param username The specific username
     * @return a user if found, null otherwise
     */
    public PersonDTO findPerson(String username) {
        List<String> i = em.createNamedQuery("Person.findByUsername")
                .setParameter("username", username)
                .getResultList();
        return returnPerson(i);
    }

    /**
     * Find a user with matching username and password
     *
     * @param username The specific username
     * @param password The specific password
     * @return a user if found, null otherwise
     */
    public PersonDTO loginPerson(String username, String password) {
        //log.error("Username: " + username);
        //log.error("Password: " + password);
        List<String> i = em.createNamedQuery("Person.findByUsernameAndPassword")
                .setParameter("username", username)
                .setParameter("password", password)
                .getResultList();
        return returnPerson(i);
    }

    private PersonDTO returnPerson(List i) {
        if (i.isEmpty()) {
            loginsFailed++;
            log.error("Login failed");
            if (loginsFailed == 3) {
                log.error("Login failed three times in a row");
                loginsFailed = 0;
            }
            return null;
        }
        Person found = em.find(Person.class, i.get(0));
        loginsFailed = 0;
        return found;
    }

    /**
     * Adds a user to the database
     *
     * @param person The person to store in the database
     * @throws DataBaseException if the user could not be added
     */
    public void registerUser(PersonDTO person) throws DataBaseException {
        if (em.find(Person.class, person.getUsername()) != null) {
            throw new DataBaseException("Could not create user");
        } else {
            try {
                em.persist(person);
            } catch (Exception ex) {
                log.error(ex.getMessage());
                throw new DataBaseException("Could not create user");
            }
        }
    }

//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Language Stuff">
    private LanguagePK generateLanguagePK(String word, String language) {
        LanguagePK languagePK = new LanguagePK();
        languagePK.setL_id(word);
        languagePK.setLang(language);
        return languagePK;
    }

    /**
     *
     * @param roleId The id of the role
     * @return the name of a roleId
     * @see is1200.authenticbanana.model.Role
     */
    public String getRoleName(Role roleId) {
        log.error("Get role");
        String roleName = em.find(Role.class, roleId.getRoleId()).getName();
        LanguagePK languagePK = new LanguagePK();
        languagePK.setLang(new Locale("en").getLanguage());
        languagePK.setL_id(roleName);
        return getWord(languagePK);
    }

    private String getWord(LanguagePK languagePK) {
        Language word = em.find(Language.class, languagePK);
        if (word == null) {
            languagePK.setLang("en");
            word = em.find(Language.class, languagePK);
            if (word == null) {
                return languagePK.getL_id();
            } else {
                return word.getWord();
            }
        } else {
            return word.getWord();
        }
    }

    private AvailableJobs setJobTranslations(AvailableJobs availableJob, Locale locale) {
        AvailableJobs job = new AvailableJobs(availableJob);
        LanguagePK lPKDesc = generateLanguagePK(job.getDescription(), locale.getLanguage());
        job.setDescription(getWord(lPKDesc));
        LanguagePK lPKTitle = generateLanguagePK(job.getJobTitle(), locale.getLanguage());
        job.setJobTitle(getWord(lPKTitle));
        return job;
    }

//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Applicant methods">
    /**
     *
     * @param locale The locale to get the correct translations
     * @return a list with all the jobs that are currently available for
     * applications
     */
    public List<AvailableJobs> getAvailableJobs(Locale locale) {
        List<Long> list = em.createNamedQuery("AvailableJobs.findBylateApplicationDate")
                .getResultList();
        if (list.isEmpty()) {
            log.error("No jobs");
            return null;
        }
        List<AvailableJobs> availableJobs = new ArrayList<>();
        for (Long jobId : list) {
            AvailableJobs availableJob = em.find(AvailableJobs.class, jobId);
            Date date = new Date();
            if (availableJob.getApplicationDate().after(date)) {
                availableJobs.add(setJobTranslations(availableJob, locale));
            }
        }
        return availableJobs;
    }

    /**
     *
     * @param locale The locale to get the correct translations
     * @param user The specific user to find competences for
     * @return a list of a specific persons competences, translated to the
     * chosen locales language
     */
    public List<CompetenceProfile> getPersonCompetences(Locale locale, PersonDTO user) {
        List<CompetenceProfile> cpList = new ArrayList<>();
        List<CompetenceProfile> list = em.createNamedQuery("CompetenceProfile.findAllByUsername")
                .setParameter("username", user)
                .getResultList();
        if (list.isEmpty()) {
            log.error("No Competences");
            return null;
        }
        for (CompetenceProfile cp : list) {
            CompetenceProfile cpNew = new CompetenceProfile(cp);
            cpNew.setTrans(getWord(generateLanguagePK(cp.getCompetenceId().getName(), locale.getLanguage())));
            log.error("Compentece: " + cpNew.getTrans() + ", " + cpNew.getYearsOfExperience() + " years");
            cpList.add(cpNew);
        }
        return cpList;
    }

    /**
     *
     * @param jobID The job to get translated
     * @param locale The locale to get the correct translations
     * @return a translated version of the job
     */
    public AvailableJobs getCurrentJob(long jobID, Locale locale) {
        return setJobTranslations(em.find(AvailableJobs.class, jobID), locale);
    }

    /**
     *
     * @param locale The locale to get the correct translations
     * @param user The specific user to find dates for
     * @return a list with all the dates when a specific user is available, null
     * if no dates are found
     */
    public List<Availability> getAvailableDates(Locale locale, PersonDTO user) {
        List<Availability> list = em.createNamedQuery("Availability.findByUsername")
                .setParameter("username", user)
                .getResultList();
        if (list.isEmpty()) {
            log.error("No from-date");
            return null;
        }
        return list;
    }

//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Recruiter methods">

    /**
     *
     * @param applicationID
     * @return the applicationID
     */
    public Application getApplication(Long applicationID) {
        return em.find(Application.class, applicationID);
    }

    /**
     *
     * @param jobID
     * @return A list of applications for a specific job
     */
    public List<Application> getApplications(long jobID) {
        AvailableJobs job = em.find(AvailableJobs.class, jobID);
        if (job == null) {
            log.error("No appplications");
            return null;
        }
        List<Application> applications = (List<Application>) job.getApplicationCollection();
        return applications;
    }
    
    
    /**
     * Generates a PDF of an application
     * @param appId
     * @param user
     * @return The path to the saved PDF
     */
    public String toPdf(Long appId, PersonDTO user) {
        try {
            Document document = new Document(PageSize.A4);
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy_HH_mm_ss");
            String date = sdf.format(new Date());
            System.out.println(date);
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            System.out.println(ec.getRealPath(""));
            String pdfURL = "/recruiter/Applications_" + date + ".pdf";
            PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(ec.getRealPath("") + pdfURL));
            document.open();
            document.addAuthor("Authentic Banana");
            document.addCreator(user.getName() + " " + user.getSurname());
            document.addSubject("Application");
            document.addCreationDate();
            document.addTitle("Application");

            Application application = getApplication(appId);
            XMLWorkerHelper worker = XMLWorkerHelper.getInstance();

            System.out.println(application.getUsername().getName());

            //GET CONTENT TO PDF
            String html = "<h2 style='color: Black'>Application for "
                    + application.getJobId().getJobTitle()
                    + "</h2>"
                    + "<h3 style='color: Black'>"
                    + "First name: "
                    + application.getUsername().getName()
                    + "</h3><h3 style='color: Black'>"
                    + "Surname: "
                    + application.getUsername().getSurname()
                    + "</h3><h3 style='color: Black'>"
                    + "Email: "
                    + application.getUsername().getEmail()
                    + "</h3><p>"
                    + application.getPLetter()
                    + "</p>";

            worker.parseXHtml(pdfWriter, document, new StringReader(html));
            document.close();
            System.out.println("PDF Done");
            return pdfURL;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
//</editor-fold>

    /**
     *
     * @param locale
     * @return A translated list of all jobs
     */
    public List<AvailableJobs> getJobs(Locale locale) {
        List<Long> list = em.createNamedQuery("AvailableJobs.findAllJobs")
                .getResultList();
        if (list.isEmpty()) {
            log.error("No jobs");
            return null;
        }
        List<AvailableJobs> jobs = new ArrayList<>();
        for (Long jobId : list) {
            AvailableJobs job = em.find(AvailableJobs.class, jobId);
            jobs.add(setJobTranslations(job, locale));
        }
        return jobs;
    }
}
