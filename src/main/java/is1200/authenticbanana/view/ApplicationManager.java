/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is1200.authenticbanana.view;

import is1200.authenticbanana.controller.ApplicationFacade;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Erik
 */
@ManagedBean(name = "applicationManager")
@SessionScoped
public class ApplicationManager implements Serializable{
    @EJB
    private ApplicationFacade applicationFacade;
    String greeting = "Hello";
}
