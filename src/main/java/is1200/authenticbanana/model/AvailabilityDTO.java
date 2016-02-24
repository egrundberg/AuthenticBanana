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
     * @return
     */
    public Long getAvailabilityId();

    /**
     *
     * @param availabilityId
     */
    public void setAvailabilityId(Long availabilityId);

    /**
     *
     * @return
     */
    public Date getFromDate();

    /**
     *
     * @param fromDate
     */
    public void setFromDate(Date fromDate);

    /**
     *
     * @return
     */
    public Date getToDate();

    /**
     *
     * @param toDate
     */
    public void setToDate(Date toDate);

    /**
     *
     * @return
     */
    public Person getUsername();

    /**
     *
     * @param username
     */
    public void setUsername(Person username);
    
    
}
