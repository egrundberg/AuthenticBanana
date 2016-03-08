/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is1200.authenticbanana.model;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author michelle
 */
@Entity
@Table(name = "COMPETENCE_PROFILE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CompetenceProfile.findAll", query = "SELECT c.competenceProfileId FROM CompetenceProfile c"),
    @NamedQuery(name = "CompetenceProfile.findAllByUsername", query = "SELECT c FROM CompetenceProfile c WHERE c.username = :username"),
    @NamedQuery(name = "CompetenceProfile.findByCompetenceProfileId", query = "SELECT c.competenceProfileId FROM CompetenceProfile c WHERE c.competenceProfileId = :competenceProfileId"),
    @NamedQuery(name = "CompetenceProfile.findByYearsOfExperience", query = "SELECT c.competenceProfileId FROM CompetenceProfile c WHERE c.yearsOfExperience = :yearsOfExperience"),
    @NamedQuery(name = "CompetenceProfile.findTranslation", query = "SELECT c.trans FROM CompetenceProfile c WHERE c.trans = :trans")})


public class CompetenceProfile implements Serializable, CompetenceProfileDTO {

    @Lob
    @Column(name = "TRANS")
    private String trans;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "COMPETENCE_PROFILE_ID")
    private Long competenceProfileId;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "YEARS_OF_EXPERIENCE")
    private BigDecimal yearsOfExperience;
    @JoinColumn(name = "COMPETENCE_ID", referencedColumnName = "COMPETENCE_ID")
    @ManyToOne
    private Competence competenceId;
    @JoinColumn(name = "USERNAME", referencedColumnName = "USERNAME")
    @ManyToOne
    private Person username;

    /**
     *
     */
    public CompetenceProfile() {
    }
    
    /**
     *
     * @param cp
     */
    public CompetenceProfile(CompetenceProfile cp)
    {
        this.competenceId = cp.competenceId;
        this.competenceProfileId = cp.competenceProfileId;
        this.username = cp.username;
        this.yearsOfExperience = cp.yearsOfExperience;
        this.trans = cp.trans;
    }

    /**
     *
     * @param competenceProfileId
     */
    public CompetenceProfile(Long competenceProfileId) {
        this.competenceProfileId = competenceProfileId;
    }

    /**
     *
     * @return gets the competence profile id
     */
    public Long getCompetenceProfileId() {
        return competenceProfileId;
    }

    /**
     *
     * @param competenceProfileId
     */
    public void setCompetenceProfileId(Long competenceProfileId) {
        this.competenceProfileId = competenceProfileId;
    }

    /**
     *
     * @return the years of experience of that person
     */
    public BigDecimal getYearsOfExperience() {
        return yearsOfExperience;
    }

    /**
     *
     * @param yearsOfExperience
     */
    public void setYearsOfExperience(BigDecimal yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    /**
     *
     * @return gets the id of that person's competence
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

    /**
     *
     * @return gets the username of the applicant
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

    /**
     *
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (competenceProfileId != null ?   competenceProfileId.hashCode() : 0);
        return hash;
    }

    /**
     *
     * @param object
     * @return
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CompetenceProfile)) {
            return false;
        }
        CompetenceProfile other = (CompetenceProfile) object;
        if ((this.competenceProfileId == null && other.competenceProfileId != null) || (this.competenceProfileId != null && !this.competenceProfileId.equals(other.competenceProfileId))) {
            return false;
        }
        return true;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "is1200.authenticbanana.model.CompetenceProfile[ competenceProfileId=" + competenceProfileId + " ]";
    }

    /**
     *
     * @return
     */
    @Override
    public String getTrans() {
        return trans;
    }

    /**
     *
     * @param trans
     */
    @Override
    public void setTrans(String trans) {
        this.trans = trans;
    }
    
}
