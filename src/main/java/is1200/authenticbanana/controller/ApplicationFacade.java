/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is1200.authenticbanana.controller;

import is1200.authenticbanana.execptions.DataBaseException;
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

    public PersonDTO findPerson(String username) {
        List<String> i = em.createNamedQuery("Person.findByUsername")
                .setParameter("username", username)
                .getResultList();

        return returnPerson(i);

    }

    public PersonDTO loginPerson(String username, String password) {
        List<String> i = em.createNamedQuery("Person.findByUsernameAndPassword")
                .setParameter("username", username)
                .setParameter("password", password)
                .getResultList();
        log.error("Michelle är bäst");
        return returnPerson(i);
    }

    public String getRoleName(Role roleId, Locale local) {

        if (local.getLanguage().equals("sv")) {
            return em.find(Role.class, roleId.getRoleId()).getName_se();
        } else {
            return em.find(Role.class, roleId.getRoleId()).getName_en();
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

    public void registerUser(PersonDTO person) throws DataBaseException {
        if (em.find(Person.class, person.getUsername()) == null) {
            em.persist(person);
        } else {
            throw new DataBaseException("Could not create user");
        }
    }
}
