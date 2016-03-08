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
import javax.validation.constraints.NotNull;
//import javax.xml.bind.httpimport;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlRootElement;
//www.piliapp.com/mysql-syntax-check/nnotation.XmlRootElement;

/**
 *
 * @author michelle
 */
@Entity
@Table(name = "AVAILABILITY")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Availability.findAll", query = "SELECT a.availabilityId FROM Availability a"),
    @NamedQuery(name = "Availability.findByAvailabilityId", query = "SELECT a FROM Availability a WHERE a.availabilityId = :availabilityId"),
    @NamedQuery(name = "Availability.findByFromDate", query = "SELECT a.availabilityId FROM Availability a WHERE a.fromDate = :fromDate"),
    @NamedQuery(name = "Availability.findByToDate", query = "SELECT a.availabilityId FROM Availability a WHERE a.toDate = :toDate"),
    @NamedQuery(name = "Availability.findByUsername", query = "SELECT a FROM Availability a WHERE a.username = :username")})
public class Availability implements Serializable, AvailabilityDTO {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AVAILABILITY_ID")
    private Long availabilityId;
    @Column(name = "FROM_DATE")
    @Temporal(TemporalType.DATE)
    private Date fromDate;
    @Column(name = "TO_DATE")
    @Temporal(TemporalType.DATE)
    private Date toDate;
    @JoinColumn(name = "USERNAME", referencedColumnName = "USERNAME")
    @ManyToOne
    private Person username;

    /**
     *
     */
    public Availability() {
    }

    /**
     *
     * @param availabilityId
     */
    public Availability(Long availabilityId) {
        this.availabilityId = availabilityId;
    }

    @Override
    public Long getAvailabilityId() {
        return availabilityId;
    }

    @Override
    public void setAvailabilityId(Long availabilityId) {
        this.availabilityId = availabilityId;
    }

    @Override
    public Date getFromDate() {
        return fromDate;
    }

    /**
     *
     * @param fromDate
     */
    @Override
    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    @Override
    public Date getToDate() {
        return toDate;
    }

    @Override
    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    @Override
    public Person getUsername() {
        return username;
    }

    @Override
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
        hash += (availabilityId != null ? availabilityId.hashCode() : 0);
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
        if (!(object instanceof Availability)) {
            return false;
        }
        Availability other = (Availability) object;
        if ((this.availabilityId == null && other.availabilityId != null) || (this.availabilityId != null && !this.availabilityId.equals(other.availabilityId))) {
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
        return "is1200.authenticbanana.model.Availability[ availabilityId=" + availabilityId + " ]";
    }
    
}
