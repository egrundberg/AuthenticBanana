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
public interface CompetenceDTO {
    
    /**
     *
     * @return
     */
    public Long getCompetenceId();

    /**
     *
     * @param competenceId
     */
    public void setCompetenceId(Long competenceId);
     /**
     * @return the name
     */
    public String getName();

    /**
     */
    public void setName(String name);
   /**
     *
     * @return a collection of competence profiles
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
     * @return a collection of available positions
     */
    @XmlTransient
    public Collection<AvailableJobs> getAvailableJobsCollection();

    /**
     *
     * @param availableJobsCollection
     */
    public void setAvailableJobsCollection(Collection<AvailableJobs> availableJobsCollection);
    
}
