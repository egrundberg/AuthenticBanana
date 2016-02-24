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
     * @return
     */
    public String getUsername();

    /**
     *
     * @param username
     */
    public void setUsername(String username);

    /**
     *
     * @return
     */
    public String getName();

    /**
     *
     * @param name
     */
    public void setName(String name);

    /**
     *
     * @return
     */
    public String getSurname();

    /**
     *
     * @param surname
     */
    public void setSurname(String surname);

    /**
     *
     * @return
     */
    public String getSsn();

    /**
     *
     * @param ssn
     */
    public void setSsn(String ssn);

    /**
     *
     * @return
     */
    public String getEmail();

    /**
     *
     * @param email
     */
    public void setEmail(String email);

    /**
     *
     * @return
     */
    public String getPassword();

    /**
     *
     * @param password
     */
    public void setPassword(String password);

    /**
     *
     * @return
     */
    public Role getRoleId();

    /**
     *
     * @param roleId
     */
    public void setRoleId(Role roleId);

    /**
     *
     * @return
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
     * @return
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
     * @return
     */
    @XmlTransient
    public Collection<Application> getApplicationCollection();

    /**
     *
     * @param applicationCollection
     */
    public void setApplicationCollection(Collection<Application> applicationCollection);
    
}
