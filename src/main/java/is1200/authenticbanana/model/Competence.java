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
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "COMPETENCE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Competence.findAll", query = "SELECT c.competenceId FROM Competence c"),
    @NamedQuery(name = "Competence.findByCompetenceId", query = "SELECT c.competenceId FROM Competence c WHERE c.competenceId = :competenceId"),
    @NamedQuery(name = "Competence.findNameSE", query = "SELECT c.competenceId FROM Competence c WHERE c.name_se = :name"),
    @NamedQuery(name = "Competence.findByNameEN", query = "SELECT c.competenceId FROM Competence c WHERE c.name_en = :name")
})
public class Competence implements Serializable, CompetenceDTO {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "COMPETENCE_ID")
    private Long competenceId;
    @Size(max = 255)
    @Column(name = "NAME_SE")
    private String name_se;
     @Size(max = 255)
    @Column(name = "NAME_EN")
    private String name_en;
  
    @OneToMany(mappedBy = "competenceId")
    private Collection<CompetenceProfile> competenceProfileCollection;

    public Competence() {
    }

    public Competence(Long competenceId) {
        this.competenceId = competenceId;
    }

    public Long getCompetenceId() {
        return competenceId;
    }

    public void setCompetenceId(Long competenceId) {
        this.competenceId = competenceId;
    }

    
      /**
     * @return the name_se
     */
    public String getName_se() {
        return name_se;
    }

    /**
     * @param name_se the name_se to set
     */
    public void setName_se(String name_se) {
        this.name_se = name_se;
    }

    /**
     * @return the name_en
     */
    public String getName_en() {
        return name_en;
    }

    /**
     * @param name_en the name_en to set
     */
    public void setName_en(String name_en) {
        this.name_en = name_en;
    }
 

    @XmlTransient
    public Collection<CompetenceProfile> getCompetenceProfileCollection() {
        return competenceProfileCollection;
    }

    public void setCompetenceProfileCollection(Collection<CompetenceProfile> competenceProfileCollection) {
        this.competenceProfileCollection = competenceProfileCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (competenceId != null ? competenceId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Competence)) {
            return false;
        }
        Competence other = (Competence) object;
        if ((this.competenceId == null && other.competenceId != null) || (this.competenceId != null && !this.competenceId.equals(other.competenceId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "is1200.authenticbanana.model.Competence[ competenceId=" + competenceId + " ]";
    }

  
    
}
