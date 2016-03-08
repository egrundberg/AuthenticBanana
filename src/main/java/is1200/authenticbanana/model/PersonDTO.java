/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is1200.authenticbanana.model;

import java.util.Collection;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author michelle
 */
public interface PersonDTO {

    /**
     *
     * @return username of the user
     */
    public String getUsername();

    /**
     *
     * @param username
     */
    public void setUsername(String username);

    /**
     *
     * @return the persons first name
     */
    public String getName();

    /**
     *
     * @param name
     */
    public void setName(String name);

    /**
     *
     * @return the persons surname
     */
    public String getSurname();

    /**
     *
     * @param surname
     */
    public void setSurname(String surname);

    /**
     *
     * @return the persons social security number
     */
    public String getSsn();

    /**
     *
     * @param ssn
     */
    public void setSsn(String ssn);

    /**
     *
     * @return the persons email
     */
    public String getEmail();

    /**
     *
     * @param email
     */
    public void setEmail(String email);

    /**
     *
     * @return the persons password
     */
    public String getPassword();

    /**
     *
     * @param password
     */
    public void setPassword(String password);

    /**
     *
     * @return the id of the role that the user has
     */
    public Role getRoleId();

    /**
     *
     * @param roleId
     */
    public void setRoleId(Role roleId);

    /**
     *
     * @return a collection of the persons availability
     */
    @XmlTransient
    public Collection<Availability> getAvailabilityCollection();

    /**
     *
     * @param availabilityCollection
     */
    public void setAvailabilityCollection(Collection<Availability> availabilityCollection);

    /**
     *
     * @return a collection of competences that the person has
     */
    @XmlTransient
    public Collection<CompetenceProfile> getCompetenceProfileCollection();

    /**
     *
     * @param competenceProfileCollection
     */
    public void setCompetenceProfileCollection(Collection<CompetenceProfile> competenceProfileCollection);

     /**
     *
     * @return a collection of the user's applications
     */
    @XmlTransient

    public Collection<Application> getApplicationCollection();

    /**
     *
     * @param applicationCollection
     */
   
    public void setApplicationCollection(Collection<Application> applicationCollection);
    
}
