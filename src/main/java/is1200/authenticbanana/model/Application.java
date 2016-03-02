/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is1200.authenticbanana.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author michelle
 */
@Entity
@Table(name = "APPLICATION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Application.findAll", query = "SELECT a.appId FROM Application a"),
    @NamedQuery(name = "Application.findJob", query = "SELECT a.jobid FROM Application a WHERE a.jobId =: jobId"),
    @NamedQuery(name = "Application.findByAppId", query = "SELECT a.appId FROM Application a WHERE a.appId = :appId")})
public class Application implements Serializable {

    @JoinColumn(name = "JOB_ID", referencedColumnName = "JOB_ID")
    @ManyToOne(optional = false)
    private AvailableJobs jobId;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "APP_ID")
    private Long appId;
    @Lob
    @Column(name = "P_LETTER")
    private String pLetter;
    @JoinColumn(name = "USERNAME", referencedColumnName = "USERNAME")
    @ManyToOne
    private Person username;

    /**
     *
     */
    public Application() {
    }

    /**
     *
     * @param appId
     */
    public Application(Long appId) {
        this.appId = appId;
    }

    /**
     *
     * @return the application's Id
     */
    public Long getAppId() {
        return appId;
    }

    /**
     *
     * @param appId
     */
    public void setAppId(Long appId) {
        this.appId = appId;
    }

    /**
     *
     * @return personal letter from the application
     */
    public String getPLetter() {
        return pLetter;
    }

    /**
     *
     * @param pLetter
     */
    public void setPLetter(String pLetter) {
        this.pLetter = pLetter;
    }

    /**
     *
     * @return the username of the applicant
     */
    public Person getUsername() {
        return username;
    }

    /**
     *
     * @param username
     */
    public void setUsername(Person username) {
        this.username = username;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (appId != null ? appId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Application)) {
            return false;
        }
        Application other = (Application) object;
        if ((this.appId == null && other.appId != null) || (this.appId != null && !this.appId.equals(other.appId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "is1200.authenticbanana.model.Application[ appId=" + appId + " ]";
    }

    public AvailableJobs getJobId() {
        return jobId;
    }

    public void setJobId(AvailableJobs jobId) {
        this.jobId = jobId;
    }
    
}
