/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is1200.authenticbanana.controller;

import is1200.authenticbanana.execptions.DataBaseException;
import is1200.authenticbanana.model.Language;
import is1200.authenticbanana.model.LanguagePK;
import is1200.authenticbanana.model.Person;
import is1200.authenticbanana.model.PersonDTO;
import is1200.authenticbanana.model.Role;
import java.util.List;
import java.util.Locale;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
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

    @PersistenceContext(unitName = "is1200_AuthenticBanana_war_1.0PU")
    private EntityManager em;
    private final static Logger log = LogManager.getLogger(ApplicationFacade.class);
    private int loginsFailed = 0;

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

    /**
     *
     * @param roleId
     * @param local
     * @return
     */
    public String getRoleName(Role roleId, Locale local) {
        log.error("Get role");
        String roleName = em.find(Role.class, roleId.getRoleId()).getName();
        LanguagePK languagePK = new LanguagePK();
        languagePK.setLang(local.getLanguage());
        languagePK.setL_id(roleName);
        Language word = em.find(Language.class, languagePK);
        if (word != null) {
            return word.getWord();
        } else {
            log.error("No role");
            return "No role?";
        }
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
}
