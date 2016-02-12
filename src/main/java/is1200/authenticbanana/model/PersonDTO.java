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
    
    
    
     public String getUsername();

    public void setUsername(String username);

    public String getName();

    public void setName(String name);

    public String getSurname();
    public void setSurname(String surname);
    public String getSsn();

    public void setSsn(String ssn);

    public String getEmail();

    public void setEmail(String email);
    public String getPassword();

    public void setPassword(String password);

    public Role getRoleId();

    public void setRoleId(Role roleId);
    @XmlTransient
    public Collection<Availability> getAvailabilityCollection();
    public void setAvailabilityCollection(Collection<Availability> availabilityCollection);
    @XmlTransient
    public Collection<CompetenceProfile> getCompetenceProfileCollection();

    public void setCompetenceProfileCollection(Collection<CompetenceProfile> competenceProfileCollection);

    
    
}
