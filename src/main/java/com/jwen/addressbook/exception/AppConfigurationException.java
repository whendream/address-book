package com.jwen.addressbook.exception;



/**
 * @author jun.wen
 *
 */

public class AppConfigurationException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public AppConfigurationException(String message, Throwable cause) {
        super(message, cause);
    }
}
