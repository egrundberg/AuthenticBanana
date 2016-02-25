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
public interface RoleDTO {
    
    /**
     *
     * @return
     */
    public Long getRoleId();

    /**
     *
     * @param roleId
     */
    public void setRoleId(Long roleId);

    /**
     *
     * @param name
     */
    public void setName(String name);

    /**
     * @return the name
     */
    public String getName();

    /**
     *
     * @return
     */
    @XmlTransient
    public Collection<Person> getPersonCollection();

    /**
     *
     * @param personCollection
     */
    public void setPersonCollection(Collection<Person> personCollection);

    
}
