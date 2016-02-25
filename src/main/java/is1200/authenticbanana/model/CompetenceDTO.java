/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is1200.authenticbanana.model;

import java.util.Collection;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author michelle
 */
public interface CompetenceDTO {
    
    /**
     *
     * @return
     */
    public Long getCompetenceId();

    /**
     *
     * @param competenceId
     */
    public void setCompetenceId(Long competenceId);
     /**
     * @return the name_se
     */
    public String getName();

    /**
     * @param name_se the name_se to set
     */
    public void setName(String name);

    @XmlTransient
    public Collection<CompetenceProfile> getCompetenceProfileCollection();

    /**
     *
     * @param competenceProfileCollection
     */
    public void setCompetenceProfileCollection(Collection<CompetenceProfile> competenceProfileCollection);

    
}
