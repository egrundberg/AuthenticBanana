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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *  This class represents the table Role in the database.
 *  It holds information about a user which indicates their privileges that 
 *  they would have on the website. A role can either be Applicant or Recruiter.
 * 
 * @author michelle
 */
@Entity
@Table(name = "ROLE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Role.findAll", query = "SELECT r.roleId FROM Role r"),
    @NamedQuery(name = "Role.findByRoleId", query = "SELECT r.name FROM Role r WHERE r.roleId = :roleId")})
public class Role implements Serializable {

    @Size(max = 255)
    @Column(name = "NAME")
    private String name;
    @OneToMany(mappedBy = "roleId")
    private Collection<Person> personCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ROLE_ID")
    private Long roleId;
   

    /**
     * The constructor
     */
    public Role() {
    }

    /**
     *
     * @param roleId
     */
    public Role(Long roleId) {
        this.roleId = roleId;
    }

    /**
     *
     * @return the id that a person has
     */
    public Long getRoleId() {
        return roleId;
    }

    /**
     *
     * @param roleId
     */
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    /**
     *
     * @return
     */
    public int hashCode() {
        int hash = 0;
        hash += (roleId != null ? roleId.hashCode() : 0);
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
        if (!(object instanceof Role)) {
            return false;
        }
        Role other = (Role) object;
        if ((this.roleId == null && other.roleId != null) || (this.roleId != null && !this.roleId.equals(other.roleId))) {
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
        return "is1200.authenticbanana.model.Role[ roleId=" + roleId + " ]";
    }

    /**
     *
     * @return the name of the role
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return a collections of persons that has a specific role
     */
    @XmlTransient
    public Collection<Person> getPersonCollection() {
        return personCollection;
    }

    /**
     *
     * @param personCollection
     */
    public void setPersonCollection(Collection<Person> personCollection) {
        this.personCollection = personCollection;
    }
    
}
