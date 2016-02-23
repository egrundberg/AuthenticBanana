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
    
    
     public Long getRoleId();

    public void setRoleId(Long roleId);

  
 
    public void setName(String name);

    /**
     * @return the name_en
     */
    public String getName();

  

    @XmlTransient
    public Collection<Person> getPersonCollection();

    public void setPersonCollection(Collection<Person> personCollection);

    
}
