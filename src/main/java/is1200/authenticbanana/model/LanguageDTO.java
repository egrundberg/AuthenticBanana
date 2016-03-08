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
public interface LanguageDTO {
    
    /**
     *
     * @return the id of a language
     */
    public String getLId();

    /**
     *
     * @param lId
     */
    public void setLId(String lId);

    /**
     *
     * @return the language of the word
     */
    public String getLang();

    /**
     *
     * @param lang
     */
    public void setLang(String lang);

    /**
     *
     * @return the correct translation of the selected word/description
     */
    public String getWord();

    /**
     *
     * @param word
     */
    public void setWord(String word);

    /**
     *
     * @return
     */
    @XmlTransient
    public Collection<Role> getRoleCollection();

    /**
     *
     * @param roleCollection
     */
    public void setRoleCollection(Collection<Role> roleCollection);

    /**
     *
     * @return a collection of the available positions
     */
    @XmlTransient
    public Collection<AvailableJobs> getAvailableJobsCollection();

    /**
     *
     * @param availableJobsCollection
     */
    public void setAvailableJobsCollection(Collection<AvailableJobs> availableJobsCollection);

    /**
     *
     * @return a collection of competences
     */
    @XmlTransient
    public Collection<Competence> getCompetenceCollection();

    /**
     *
     * @param competenceCollection
     */
    public void setCompetenceCollection(Collection<Competence> competenceCollection);
    
     /**
     *
     * @return the primary key of the table
     */
    public is1200.authenticbanana.model.LanguagePK getLanguagePK();
    /**
     *
     * @param languagePK
     */
    public void setLanguagePK(is1200.authenticbanana.model.LanguagePK languagePK);
}
