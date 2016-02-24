/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is1200.authenticbanana.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author michelle
 */
 @Embeddable

public class LanguagePK {

    @Column
    private String L_id;
    @Column
    private String Lang;
}

