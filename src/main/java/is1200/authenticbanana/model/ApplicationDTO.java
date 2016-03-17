/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is1200.authenticbanana.model;

/**
 *  This class is a DTO, interface, to the table Application, which means that
 *  the view can access the database's information securely - abstracting the
 *  underlying structure of the database.
 * @author michelle
 */
public interface ApplicationDTO {
    
    /**
     *
     * @return the application's ID
     */
    public Long getAppId();

    /**
     *
     * @param appId
     */
    public void setAppId(Long appId);

    /**
     *
     * @return the applicant's cover letter
     */
    public String getPLetter();

    /**
     *
     * @param pLetter
     */
    public void setPLetter(String pLetter);

    /**
     *
     * @return the applicant's username
     */
    public Person getUsername();

    /**
     *
     * @param username
     */
    public void setUsername(Person username);    
}
