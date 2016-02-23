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
    @NamedQuery(name = "AvailableJobs.findByApplicationDate", query = "SELECT a.applicationDate FROM AvailableJobs a WHERE a.applicationDate = :applicationDate")})
public class AvailableJobs implements Serializable {

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
    @JoinColumn(name = "DESCRIPTION", referencedColumnName = "L_ID")
    @ManyToOne
    private Language description;

    public AvailableJobs() {
    }

    public AvailableJobs(Long jobId) {
        this.jobId = jobId;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public Date getFromPeriod() {
        return fromPeriod;
    }

    public void setFromPeriod(Date fromPeriod) {
        this.fromPeriod = fromPeriod;
    }

    public Date getToPeriod() {
        return toPeriod;
    }

    public void setToPeriod(Date toPeriod) {
        this.toPeriod = toPeriod;
    }

    public Date getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(Date applicationDate) {
        this.applicationDate = applicationDate;
    }

    public Competence getCompetenceId() {
        return competenceId;
    }

    public void setCompetenceId(Competence competenceId) {
        this.competenceId = competenceId;
    }

    public Language getDescription() {
        return description;
    }

    public void setDescription(Language description) {
        this.description = description;
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
    
}
