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
public interface AvailabilityDTO {
    
    /**
     *
     * @return the availability ID
     */
    public Long getAvailabilityId();

    /**
     *
     * @param availabilityId
     */
    public void setAvailabilityId(Long availabilityId);

    /**
     *
     * @return the from date when the applicant is available
     */
    public Date getFromDate();

    /**
     *
     * @param fromDate 
     */
    public void setFromDate(Date fromDate);

    /**
     *
     * @return the to date when the applicant is available
     */
    public Date getToDate();

    /**
     *
     * @param toDate
     */
    public void setToDate(Date toDate);

    /**
     *
     * @return the username
     */
    public Person getUsername();

    /**
     *
     * @param username
     */
    public void setUsername(Person username);
    
    
}
