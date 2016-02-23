/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is1200.authenticbanana.model;

/**
 *
 * @author michelle
 */
public interface ApplicationDTO {
    
    
      public Long getAppId();
    public void setAppId(Long appId);

    public String getPLetter();

    public void setPLetter(String pLetter);

    public Person getUsername();

    public void setUsername(Person username);    
}
