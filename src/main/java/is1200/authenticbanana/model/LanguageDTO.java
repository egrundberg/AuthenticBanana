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
public interface LanguageDTO {
    
     public String getLId();

    public void setLId(String lId);

    public String getLang();

    public void setLang(String lang);

    public String getWord();

    public void setWord(String word);

    @XmlTransient
    public Collection<Role> getRoleCollection();

    public void setRoleCollection(Collection<Role> roleCollection);
    @XmlTransient
    public Collection<AvailableJobs> getAvailableJobsCollection();

    public void setAvailableJobsCollection(Collection<AvailableJobs> availableJobsCollection);

    @XmlTransient
    public Collection<Competence> getCompetenceCollection();

    public void setCompetenceCollection(Collection<Competence> competenceCollection);
    
}
