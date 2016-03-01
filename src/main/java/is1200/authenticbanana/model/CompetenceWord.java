package is1200.authenticbanana.model;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author michelle
 */
 @Embeddable

public class CompetenceWord implements Serializable {

    @Column
    private String word;
    @Column
    private BigDecimal years;    

    /**
     * @return the word
     */
    public String getWord() {
        return word;
    }

    /**
     * @param word the word to set
     */
    public void setWord(String word) {
        this.word = word;
    }

    /**
     * @return the years
     */
    public BigDecimal getYears() {
        return years;
    }

    /**
     * @param years the years to set
     */
    public void setYears(BigDecimal years) {
        this.years = years;
    }
}

