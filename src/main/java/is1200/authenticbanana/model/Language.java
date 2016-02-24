/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is1200.authenticbanana.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author michelle
 */
@Entity
@Table(name = "LANGUAGE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Language.findAll", query = "SELECT l.lId FROM Language l"),
    @NamedQuery(name = "Language.findByLId", query = "SELECT l.word FROM Language l WHERE l.lId = :lId"),
    @NamedQuery(name = "Language.findByLang", query = "SELECT l.lang FROM Language l WHERE l.lang = :lang")})

//
//@Embeddable
//public class LanguagePK implements Serializable { 
//    @Column
//    private String fieldA;
//    @Column
//    private String fieldB;
//}


public class Language implements Serializable {

    @EmbeddedId
    protected is1200.authenticbanana.model.LanguagePK languagePK;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "L_ID")
    private String lId;
    @Size(max = 255)
    @Id
    @Column(name = "LANG")
    private String lang;
    @Lob
    @Column(name = "WORD")
    private String word;
    @OneToMany(mappedBy = "name")
    private Collection<Role> roleCollection;
    @OneToMany(mappedBy = "description")
    private Collection<AvailableJobs> availableJobsCollection;
    @OneToMany(mappedBy = "name")
    private Collection<Competence> competenceCollection;

    /**
     *
     */
    public Language() {
    }

    /**
     *
     * @param lId
     */
    public Language(String lId) {
        this.lId = lId;
    }

    /**
     *
     * @return the id of the specific language
     */
    public String getLId() {
        return lId;
    }

    /**
     *
     * @param lId
     */
    public void setLId(String lId) {
        this.lId = lId;
    }

    /**
     *
     * @return
     */
    public String getLang() {
        return lang;
    }

    /**
     *
     * @param lang
     */
    public void setLang(String lang) {
        this.lang = lang;
    }

    /**
     *
     * @return
     */
    public String getWord() {
        return word;
    }

    /**
     *
     * @param word
     */
    public void setWord(String word) {
        this.word = word;
    }

    /**
     *
     * @return
     */
    @XmlTransient
    public Collection<Role> getRoleCollection() {
        return roleCollection;
    }

    /**
     *
     * @param roleCollection
     */
    public void setRoleCollection(Collection<Role> roleCollection) {
        this.roleCollection = roleCollection;
    }

    /**
     *
     * @return
     */
    @XmlTransient
    public Collection<AvailableJobs> getAvailableJobsCollection() {
        return availableJobsCollection;
    }

    /**
     *
     * @param availableJobsCollection
     */
    public void setAvailableJobsCollection(Collection<AvailableJobs> availableJobsCollection) {
        this.availableJobsCollection = availableJobsCollection;
    }

    /**
     *
     * @return
     */
    @XmlTransient
    public Collection<Competence> getCompetenceCollection() {
        return competenceCollection;
    }

    /**
     *
     * @param competenceCollection
     */
    public void setCompetenceCollection(Collection<Competence> competenceCollection) {
        this.competenceCollection = competenceCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (lId != null ? lId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Language)) {
            return false;
        }
        Language other = (Language) object;
        if ((this.lId == null && other.lId != null) || (this.lId != null && !this.lId.equals(other.lId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "is1200.authenticbanana.model.Language[ lId=" + lId + " ]";
    }

    public is1200.authenticbanana.model.LanguagePK getLanguagePK() {
        return languagePK;
    }

    public void setLanguagePK(is1200.authenticbanana.model.LanguagePK languagePK) {
        this.languagePK = languagePK;
    }
    
}
