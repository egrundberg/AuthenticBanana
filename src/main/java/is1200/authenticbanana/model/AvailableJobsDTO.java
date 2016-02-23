/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is1200.authenticbanana.model;

import java.util.Date;

/**
 *
 * @author michelle
 */
public interface AvailableJobsDTO {
    
    
     public Long getJobId();

    public void setJobId(Long jobId);

    public Date getFromPeriod();

    public void setFromPeriod(Date fromPeriod);

    public Date getToPeriod();

    public void setToPeriod(Date toPeriod);

    public Date getApplicationDate();

    public void setApplicationDate(Date applicationDate);

    public Competence getCompetenceId();

    public void setCompetenceId(Competence competenceId);

    public Language getDescription();

    public void setDescription(Language description);

    
}
