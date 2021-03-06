/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is1200.authenticbanana.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *  This class represents the table Language in the database.
 *  It holds all words that can be translated on the website, and in ones 
 *  application. 
 * @author michelle
 */
@Entity
@Table(name = "LANGUAGE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Language.findAll", query = "SELECT l.lId FROM Language l"),
    @NamedQuery(name = "Language.findByLId", query = "SELECT l.word FROM Language l WHERE l.lId = :lId"),
    @NamedQuery(name = "Language.findByLang", query = "SELECT l.lang FROM Language l WHERE l.lang = :lang")})

//
//@Embeddable
//public class LanguagePK implements Serializable { 
//    @Column
//    private String fieldA;
//    @Column
//    private String fieldB;
//}


public class Language implements Serializable {

    /**
     *
     */
    @EmbeddedId
    protected is1200.authenticbanana.model.LanguagePK languagePK;

    private static final long serialVersionUID = 1L;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "L_ID")
    private String lId;
    @Size(max = 255)
    
    @Column(name = "LANG")
    private String lang;
    @Lob
    @Column(name = "WORD")
    private String word;

    /**
     * The constructor
     */
    public Language() {
    }

    /**
     *
     * @param lId
     */
    public Language(String lId) {
        this.lId = lId;
    }

    /**
     *
     * @return the id of the specific language
     */
    public String getLId() {
        return lId;
    }

    /**
     *
     * @param lId
     */
    public void setLId(String lId) {
        this.lId = lId;
    }

    /**
     *
     * @return the language of the selected word
     */
    public String getLang() {
        return lang;
    }

    /**
     *
     * @param lang
     */
    public void setLang(String lang) {
        this.lang = lang;
    }

    /**
     *
     * @return the correct translation in the respective language
     */
    public String getWord() {
        return word;
    }

    /**
     *
     * @param word
     */
    public void setWord(String word) {
        this.word = word;
    }

    /**
     *
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (lId != null ? lId.hashCode() : 0);
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
        if (!(object instanceof Language)) {
            return false;
        }
        Language other = (Language) object;
        if ((this.lId == null && other.lId != null) || (this.lId != null && !this.lId.equals(other.lId))) {
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
        return "is1200.authenticbanana.model.Language[ lId=" + lId + " ]";
    }

    /**
     *
     * @return the primary key of the table
     */
    public is1200.authenticbanana.model.LanguagePK getLanguagePK() {
        return languagePK;
    }

    /**
     *
     * @param languagePK
     */
    public void setLanguagePK(is1200.authenticbanana.model.LanguagePK languagePK) {
        this.languagePK = languagePK;
    }
    
}
