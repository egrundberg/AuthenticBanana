/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is1200.authenticbanana.model;

import java.util.Collection;
import javax.xml.bind.annotation.XmlTransient;

/**
 *  This class is a DTO, interface, to the table Role, which means that
 *  the view can access the database's information securely - abstracting the
 *  underlying structure of the database.
 * @author michelle
 */
public interface RoleDTO {
    
    /**
     *
     * @return the id of the specific role
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
     * @return the name of the role
     */
    public String getName();

    
      /**
     *
     * @return a collections of persons that has a specific role
     */
    @XmlTransient
    public Collection<Person> getPersonCollection();
    /**
     *
     * @param personCollection
     */
    public void setPersonCollection(Collection<Person> personCollection);
    
}
