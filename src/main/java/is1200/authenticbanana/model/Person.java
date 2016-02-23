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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author michelle
 */
@Entity
@Table(name = "PERSON")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Person.findAll", query = "SELECT p.username FROM Person p"),
    @NamedQuery(name = "Person.findByUsername", query = "SELECT p.username FROM Person p WHERE p.username = :username"),
    @NamedQuery(name = "Person.findByName", query = "SELECT p.username FROM Person p WHERE p.name = :name"),
    @NamedQuery(name = "Person.findBySurname", query = "SELECT p.username FROM Person p WHERE p.surname = :surname"),
    @NamedQuery(name = "Person.findBySsn", query = "SELECT p.username FROM Person p WHERE p.ssn = :ssn"),
    @NamedQuery(name = "Person.findByEmail", query = "SELECT p.username FROM Person p WHERE p.email = :email"),
    @NamedQuery(name = "Person.findByPassword", query = "SELECT p.username FROM Person p WHERE p.password = :password"),
    @NamedQuery(name = "Person.findByUsernameAndPassword", query = "SELECT p.username FROM Person p WHERE p.password = :password AND p.username = :username")})
public class Person implements Serializable, PersonDTO {

    @OneToMany(mappedBy = "username")
    private Collection<Application> applicationCollection;

    private static final long serialVersionUID = 1L;
    @Id
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "USERNAME")
    private String username;
    @Size(min=1,max = 255)
    @Column(name = "NAME")
    private String name;
    @Size(min=1,max = 255)
    @Column(name = "SURNAME")
    private String surname;
    @Size(min = 4, max = 255)    
    @Column(name = "SSN")
    private String ssn;
    @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}"
            + "~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9"
            + "-]*[a-z0-9])?", message="Invalid email")
    @Size(min = 7,max = 255)
    @Column(name = "EMAIL")
    private String email;
    @Size(min = 4,max = 255)
    @Column(name = "PASSWORD")
    private String password;
    @JoinColumn(name = "ROLE_ID", referencedColumnName = "ROLE_ID")
    @ManyToOne
    private Role roleId;
    @OneToMany(mappedBy = "username")
    private Collection<Availability> availabilityCollection;
    @OneToMany(mappedBy = "username")
    private Collection<CompetenceProfile> competenceProfileCollection;

    public Person() {
    }

    public Person(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRoleId() {
        return roleId;
    }

    public void setRoleId(Role roleId) {
        this.roleId = roleId;
    }

    @XmlTransient
    public Collection<Availability> getAvailabilityCollection() {
        return availabilityCollection;
    }

    public void setAvailabilityCollection(Collection<Availability> availabilityCollection) {
        this.availabilityCollection = availabilityCollection;
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
        hash += (username != null ? username.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Person)) {
            return false;
        }
        Person other = (Person) object;
        if ((this.username == null && other.username != null) || (this.username != null && !this.username.equals(other.username))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "is1200.authenticbanana.model.Person[ username=" + username + " ]";
    }

    @XmlTransient
    public Collection<Application> getApplicationCollection() {
        return applicationCollection;
    }

    public void setApplicationCollection(Collection<Application> applicationCollection) {
        this.applicationCollection = applicationCollection;
    }
    
}
