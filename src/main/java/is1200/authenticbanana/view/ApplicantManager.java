/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is1200.authenticbanana.view;

import is1200.authenticbanana.controller.ApplicationFacade;
import is1200.authenticbanana.execptions.DataBaseException;
import is1200.authenticbanana.model.AvailableJobs;
import is1200.authenticbanana.model.AvailableJobsDTO;
import is1200.authenticbanana.model.Person;
import is1200.authenticbanana.model.PersonDTO;
import is1200.authenticbanana.model.Role;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.validation.constraints.*;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Erik
 */
@ManagedBean(name = "applicantManager")
@SessionScoped
public class ApplicantManager implements Serializable {

    @EJB
    private ApplicationFacade applicationFacade;
    private final static Logger LOG = LogManager.getLogger(ApplicationFacade.class);
    private PersonDTO user;
    private List<AvailableJobs> availableJobs;
    private Date date = new Date();
    private Locale locale = Locale.getDefault();;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    Calendar calendar = Calendar.getInstance();
    //<editor-fold defaultstate="collapsed" desc="Appliacant stuff">

    //public List<ApplicationDTO> //</editor-fold>
    


    
    
    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return the locale
     */
    public Locale getLocale() {
        return locale;
    }

    /**
     * @param locale the locale to set
     */
    public void setLocale(Locale locale) {
        this.locale = locale;
    }
    
    /**
     * @return the user
     */
    public PersonDTO getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(PersonDTO user) {
        this.user = user;
    }

}
