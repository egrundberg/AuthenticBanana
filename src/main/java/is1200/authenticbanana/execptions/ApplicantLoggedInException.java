/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 * is1200.authenticbanana.exceptions is a package for defining custom exceptions
 */
package is1200.authenticbanana.execptions;

/**
 *
 * @author Erik
 */
public class ApplicantLoggedInException extends Exception {

    /**
     *
     * @param message
     */
    public ApplicantLoggedInException(String message) {
        super(message);
    }

    /**
     *
     * @param message
     * @param throwable
     */
    public ApplicantLoggedInException(String message, Throwable throwable) {
        super(message, throwable);
    }

}