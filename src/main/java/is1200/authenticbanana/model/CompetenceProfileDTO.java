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
     * @return
     */
    public Long getCompetenceProfileId();

    /**
     *
     * @param competenceProfileId
     */
    public void setCompetenceProfileId(Long competenceProfileId);

    /**
     *
     * @return
     */
    public BigDecimal getYearsOfExperience();

    /**
     *
     * @param yearsOfExperience
     */
    public void setYearsOfExperience(BigDecimal yearsOfExperience);

    /**
     *
     * @return
     */
    public Competence getCompetenceId();

    /**
     *
     * @param competenceId
     */
    public void setCompetenceId(Competence competenceId);

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
