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
    
    
    
    
    public Long getCompetenceId();

    public void setCompetenceId(Long competenceId);
     /**
     * @return the name_se
     */
    public String getName_se();

    /**
     * @param name_se the name_se to set
     */
    public void setName_se(String name_se);

    /**
     * @return the name_en
     */
    public String getName_en();

    /**
     * @param name_en the name_en to set
     */
    public void setName_en(String name_en);
    @XmlTransient
    public Collection<CompetenceProfile> getCompetenceProfileCollection();

    public void setCompetenceProfileCollection(Collection<CompetenceProfile> competenceProfileCollection);

    
}
