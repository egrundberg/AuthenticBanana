/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is1200.authenticbanana.model;

import java.util.Collection;
import java.util.Date;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author michelle
 */
public interface AvailableJobsDTO {
    
    /**
     *
     * @return
     */
    public Long getJobId();

    /**
     *
     * @param jobId
     */
    public void setJobId(Long jobId);

    /**
     *
     * @return
     */
    public Date getFromPeriod();

    /**
     *
     * @param fromPeriod
     */
    public void setFromPeriod(Date fromPeriod);

    /**
     *
     * @return
     */
    public Date getToPeriod();

    /**
     *
     * @param toPeriod
     */
    public void setToPeriod(Date toPeriod);

    /**
     *
     * @return
     */
    public Date getApplicationDate();

    /**
     *
     * @param applicationDate
     */
    public void setApplicationDate(Date applicationDate);

    /**
     *
     * @return
     */
    public Competence getCompetenceId();

    /**
     *
     * @param competenceId
     */
    public void setCompetenceId(Competence competenceId);

    /**
     *
     * @return
     */
   public String getDescription();

     /**
     *
     * @param description
     */
    public void setDescription(String description);

    /**
     *
     * @return a collection of applications
     */
    @XmlTransient
    public Collection<Application> getApplicationCollection();

    /**
     *
     * @param applicationCollection
     */
    public void setApplicationCollection(Collection<Application> applicationCollection);

    /**
     *
     * @return the title of the position
     */
    public String getJobTitle();

    /**
     *
     * @param jobTitle
     */
    public void setJobTitle(String jobTitle);  

    
}
