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
    
    
    public Long getCompetenceProfileId();

    public void setCompetenceProfileId(Long competenceProfileId);

    public BigDecimal getYearsOfExperience();

    public void setYearsOfExperience(BigDecimal yearsOfExperience);

    public Competence getCompetenceId();

    public void setCompetenceId(Competence competenceId);

    public Person getUsername();

    public void setUsername(Person username);

    
    
}
