/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is1200.authenticbanana.model;

import java.math.BigDecimal;

/**
 *
 * @author michelle
 */
public interface CompetenceProfileDTO {
    
    /**
     *
     * @return the id of the competence profile
     */
    public Long getCompetenceProfileId();

    /**
     *
     * @param competenceProfileId
     */
    public void setCompetenceProfileId(Long competenceProfileId);

    /**
     *
     * @return the amount of years in experience
     */
    public BigDecimal getYearsOfExperience();

    /**
     *
     * @param yearsOfExperience
     */
    public void setYearsOfExperience(BigDecimal yearsOfExperience);

    /**
     *
     * @return the id of the competence
     */
    public Competence getCompetenceId();

    /**
     *
     * @param competenceId
     */
    public void setCompetenceId(Competence competenceId);

    /**
     *
     * @return the user's username
     */
    public Person getUsername();

    /**
     *
     * @param username
     */
    public void setUsername(Person username);
    
    /**
     *
     * @return the translation of the word
     */
    public String getTrans();

    /**
     *
     * @param trans
     */
    public void setTrans(String trans);
    

    
    
}
