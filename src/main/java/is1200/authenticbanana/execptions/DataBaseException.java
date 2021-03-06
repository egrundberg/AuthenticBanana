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
 * An exception used for specifing errors in the database.
 * @author Erik
 */
public class DataBaseException extends Exception {

    /**
     *
     * @param message
     */
    public DataBaseException(String message) {
        super(message);
    }

    /**
     *
     * @param message
     * @param throwable
     */
    public DataBaseException(String message, Throwable throwable) {
        super(message, throwable);
    }

}