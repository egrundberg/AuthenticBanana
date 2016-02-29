/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is1200.authenticbanana.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author michelle
 */
@Entity
@Table(name = "AVAILABLE_JOBS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AvailableJobs.findAll", query = "SELECT a.competenceId FROM AvailableJobs a"),
    @NamedQuery(name = "AvailableJobs.findByJobId", query = "SELECT a.jobId FROM AvailableJobs a WHERE a.jobId = :jobId"),
    @NamedQuery(name = "AvailableJobs.findByFromPeriod", query = "SELECT a.fromPeriod FROM AvailableJobs a WHERE a.fromPeriod = :fromPeriod"),
    @NamedQuery(name = "AvailableJobs.findByToPeriod", query = "SELECT a.toPeriod FROM AvailableJobs a WHERE a.toPeriod = :toPeriod"),
    @NamedQuery(name = "AvailableJobs.findByApplicationDate", query = "SELECT a.applicationDate FROM AvailableJobs a WHERE a.applicationDate = :applicationDate"),
    @NamedQuery(name = "AvailableJobs.findBylateApplicationDate", query = "SELECT a.jobId FROM AvailableJobs a")})
public class AvailableJobs implements Serializable {

    @Size(max = 255)
    @Column(name = "DESCRIPTION")
    private String description;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "JOB_ID")
    private Long jobId;
    @Column(name = "FROM_PERIOD")
    @Temporal(TemporalType.DATE)
    private Date fromPeriod;
    @Column(name = "TO_PERIOD")
    @Temporal(TemporalType.DATE)
    private Date toPeriod;
    @Column(name = "APPLICATION_DATE")
    @Temporal(TemporalType.DATE)
    private Date applicationDate;
    @JoinColumn(name = "COMPETENCE_ID", referencedColumnName = "COMPETENCE_ID")
    @ManyToOne
    private Competence competenceId;

    /**
     *
     */
    public AvailableJobs() {
    }

    /**
     *
     * @param jobId
     */
    public AvailableJobs(Long jobId) {
        this.jobId = jobId;
    }
    
    /**
     *
     * @param job
     */
    public AvailableJobs(AvailableJobs job) {
        this.jobId = job.getJobId();
        this.applicationDate = job.getApplicationDate();
        this.competenceId = job.getCompetenceId();
        this.description = job.getDescription();
        this.fromPeriod = job.getFromPeriod();
        this.toPeriod = job.getToPeriod();
    }
    
    

    /**
     *
     * @return the id of the specific job
     */
    public Long getJobId() {
        return jobId;
    }

    /**
     *
     * @param jobId
     */
    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    /**
     *
     * @return gets the period from which the applicant can work
     */
    public Date getFromPeriod() {
        return fromPeriod;
    }

    /**
     *
     * @param fromPeriod
     */
    public void setFromPeriod(Date fromPeriod) {
        this.fromPeriod = fromPeriod;
    }

    /**
     *
     * @return gets the period from which the applicant can work to
     */
    public Date getToPeriod() {
        return toPeriod;
    }

    /**
     *
     * @param toPeriod
     */
    public void setToPeriod(Date toPeriod) {
        this.toPeriod = toPeriod;
    }

    /**
     *
     * @return the last day when the application can be made
     */
    public Date getApplicationDate() {
        return applicationDate;
    }

    /**
     *
     * @param applicationDate
     */
    public void setApplicationDate(Date applicationDate) {
        this.applicationDate = applicationDate;
    }

    /**
     *
     * @return gets the id of the competence which is necessary by the applicant
     */
    public Competence getCompetenceId() {
        return competenceId;
    }

    /**
     *
     * @param competenceId
     */
    public void setCompetenceId(Competence competenceId) {
        this.competenceId = competenceId;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (jobId != null ? jobId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AvailableJobs)) {
            return false;
        }
        AvailableJobs other = (AvailableJobs) object;
        if ((this.jobId == null && other.jobId != null) || (this.jobId != null && !this.jobId.equals(other.jobId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "is1200.authenticbanana.model.AvailableJobs[ jobId=" + jobId + " ]";
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
}
