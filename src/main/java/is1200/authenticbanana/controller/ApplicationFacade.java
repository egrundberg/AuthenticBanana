/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is1200.authenticbanana.controller;

import is1200.authenticbanana.execptions.DataBaseException;
import is1200.authenticbanana.model.AvailableJobs;
import is1200.authenticbanana.model.Competence;
import is1200.authenticbanana.model.CompetenceProfile;
import is1200.authenticbanana.model.Language;
import is1200.authenticbanana.model.LanguagePK;
import is1200.authenticbanana.model.Person;
import is1200.authenticbanana.model.PersonDTO;
import is1200.authenticbanana.model.Role;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
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
     *
     * @param username
     * @return
     */
    public PersonDTO findPerson(String username) {
        List<String> i = em.createNamedQuery("Person.findByUsername")
                .setParameter("username", username)
                .getResultList();
        return returnPerson(i);

    }

    /**
     *
     * @param username
     * @param password
     * @return
     */
    public PersonDTO loginPerson(String username, String password) {
        List<String> i = em.createNamedQuery("Person.findByUsernameAndPassword")
                .setParameter("username", username)
                .setParameter("password", password)
                .getResultList();
        log.error("Michelle är bäst");
        return returnPerson(i);
    }

    private PersonDTO returnPerson(List i) {
        if (i.isEmpty()) {
            loginsFailed++;
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
     *
     * @param person
     * @throws DataBaseException
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
     * @param roleId
     * @return
     */
    public String getRoleName(Role roleId) {
        log.error("Get role");
        String roleName = em.find(Role.class, roleId.getRoleId()).getName();
        LanguagePK languagePK = new LanguagePK();
        languagePK.setLang(new Locale("en").getLanguage());
        languagePK.setL_id(roleName);
        return getWord(languagePK);
    }

    /**
     *
     * @param languagePK
     * @return
     */
    public String getWord(LanguagePK languagePK) {
        Language word = em.find(Language.class, languagePK);
        if (word == null) {
            languagePK.setLang("en");
            word = em.find(Language.class, languagePK);
            if (word == null) {
                return "No word?";
            } else {
                return word.getWord();
            }
        } else {
            return word.getWord();
        }
    }

//</editor-fold>
    
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
                AvailableJobs job = new AvailableJobs(availableJob);
                LanguagePK lPK = generateLanguagePK(job.getDescription(), locale.getLanguage());
                job.setDescription(getWord(lPK));
                availableJobs.add(job);
            }
        }
        return availableJobs;
    }

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
            Competence c = em.find(Competence.class, cp.getCompetenceId().getCompetenceId());
            CompetenceProfile cpNew = new CompetenceProfile(cp);
            cpNew.setTrans(getWord(generateLanguagePK(c.getName(), locale.getLanguage())));
            log.error("Compentece: " + cpNew.getTrans() + ", " + cpNew.getYearsOfExperience() + " years");
            cpList.add(cpNew);
        }
        return cpList;
    }
}
