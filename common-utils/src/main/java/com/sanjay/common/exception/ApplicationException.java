/* Copyright (C) 2014, 2015 Sanjay Madnani
 * 
 * This file is free to use: you can redistribute it and/or modify it under the terms of the GPL General Public License
 * V2 as published by the Free Software Foundation, subject to the following conditions:
 * 
 * The above copyright notice should never be changed and should always included wherever this file is used.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY.
 * See the GNU General Public License V2 for more details. */
package com.sanjay.common.exception;

/**
 * @author SANJAY
 * 
 */
public class ApplicationException extends Exception {

    /**
     * serialVersionUID unique key.
     */
    private static final long serialVersionUID = 565698485L;
    /**
     * error bundle key.
     */
    private transient String errorKey;
    
    private Integer errorCode;
    /**
     * The error severity.
     */
    private transient ApplicationSeverity severity;
    /**
     * Specific error Info.
     */
    private transient String[] params;
    /**
     * Specific error Info.
     */
    private transient Object[] objects;

    /**
     * Instantiate an ApplicationException
     * 
     * @param errorKey String bundle key for retrieving error message.
     * @param severity severity of the Exception.
     * @param cause the cause (which is saved for later retrieval by the {@link #getCause()} method). (A <tt>null</tt>
     *            value is permitted, and indicates that the cause is nonexistent or unknown.)
     * @param params array of string for error info.
     */
    public ApplicationException(String errorKey, ApplicationSeverity severity, Throwable cause, String ...params) {
        super(cause);
        this.errorKey = errorKey;
        this.severity = severity;
        this.params = params;
    }

    /**
     * @param errorKey
     * @param severity
     * @param objects
     */
    public ApplicationException(String errorKey, ApplicationSeverity severity, Throwable cause, Object ...objects) {
        super(cause);
        this.errorKey = errorKey;
        this.severity = severity;
        this.objects = objects;
    }

    /**
     * @param errorKey
     * @param severity
     */
    public ApplicationException(String errorKey, ApplicationSeverity severity, Throwable cause) {
        super(cause);
        this.errorKey = errorKey;
        this.severity = severity;
    }
    
    /**
     * 
     * @param errorCode
     * @param severity
     * @param cause
     * @param objects
     */
    public ApplicationException(int errorCode, ApplicationSeverity severity, Throwable cause, Object ...objects) {
        super(cause);
        this.errorCode = errorCode;
        this.severity = severity;
        this.objects = objects;
    }
    
    /**
     * 
     * @param errorKey
     * @param severity
     */
    public ApplicationException(String errorKey, ApplicationSeverity severity) {
        this(errorKey, severity, null, (String[])null);
    }
    
    /**
     * 
     * @param errorCode
     * @param severity
     */
    public ApplicationException(int errorCode, ApplicationSeverity severity) {
        this(errorCode, severity, null, (Object[])null);
    }
    
    /**
     * 
     * @param errorCode
     * @param throwable
     */
    public ApplicationException(int errorCode, Throwable throwable) {
        this(errorCode, null, throwable, (Object[])null);
    }
    
    /**
     * 
     * @param errorKey
     */
    public ApplicationException(String errorKey) {
        this(errorKey, null, null, (String[])null);
    }

    /**
     * @return the errorCode
     */
    public Integer getErrorCode() {
        return errorCode;
    }

    /**
     * @param errorCode the errorCode to set
     */
    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * @return the errorKey
     */
    public String getErrorKey() {
        return errorKey;
    }

    /**
     * @return the severity
     */
    public ApplicationSeverity getSeverity() {
        return severity;
    }

    /**
     * @return the params
     */
    public String[] getParams() {
        return params;
    }

    /**
     * @return the objects
     */
    public Object[] getObjects() {
        return objects;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return null;
    }

}
