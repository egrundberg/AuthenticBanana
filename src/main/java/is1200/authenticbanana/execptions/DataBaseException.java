/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is1200.authenticbanana.execptions;

/**
 *
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