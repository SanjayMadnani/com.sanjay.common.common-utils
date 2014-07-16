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

import java.util.Arrays;

import com.sanjay.common.constants.CommonConstants;

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
     * Instantiate an ApplicationException.
     * 
     * @param errorKey String bundle key for retrieving error message.
     * @param severity severity of the Exception.
     * @param cause the cause (which is saved for later retrieval by the {@link #getCause()} method). (A <tt>null</tt>
     *            value is permitted, and indicates that the cause is nonexistent or unknown.)
     * @param params array of string for error info.
     */
    public ApplicationException(final String errorKey, final ApplicationSeverity severity, final Throwable cause,
            final String... params) {
        super(cause);
        this.errorKey = errorKey;
        this.severity = severity;
        this.params = params;
    }

    /**
     * Instantiate an ApplicationException.
     * 
     * @param errorKey String bundle key for retrieving error message.
     * @param severity severity of the Exception.
     * @param cause the cause (which is saved for later retrieval by the {@link #getCause()} method). (A <tt>null</tt>
     *            value is permitted, and indicates that the cause is nonexistent or unknown.)
     * @param objects array of objects for error info.
     */
    public ApplicationException(final String errorKey, final ApplicationSeverity severity, final Throwable cause,
            final Object... objects) {
        super(cause);
        this.errorKey = errorKey;
        this.severity = severity;
        this.objects = objects;
    }

    /**
     * Instantiate an ApplicationException.
     * 
     * @param errorKey String bundle key for retrieving error message.
     * @param severity severity of the Exception.
     * @param cause the cause (which is saved for later retrieval by the {@link #getCause()} method). (A <tt>null</tt>
     *            value is permitted, and indicates that the cause is nonexistent or unknown.)
     */
    public ApplicationException(final String errorKey, final ApplicationSeverity severity, final Throwable cause) {
        super(cause);
        this.errorKey = errorKey;
        this.severity = severity;
    }

    /**
     * Instantiate an ApplicationException.
     * 
     * @param errorCode int bundle code for retrieving error message.
     * @param severity severity of the Exception.
     * @param cause the cause (which is saved for later retrieval by the {@link #getCause()} method). (A <tt>null</tt>
     *            value is permitted, and indicates that the cause is nonexistent or unknown.)
     * @param objects array of objects for error info.
     */
    public ApplicationException(final int errorCode, final ApplicationSeverity severity, final Throwable cause,
            final Object... objects) {
        super(cause);
        this.errorCode = errorCode;
        this.severity = severity;
        this.objects = objects;
    }

    /**
     * Instantiate an ApplicationException.
     * 
     * @param errorKey String bundle key for retrieving error message.
     * @param severity severity of the Exception.
     */
    public ApplicationException(final String errorKey, final ApplicationSeverity severity) {
        this(errorKey, severity, null, (String[]) null);
    }

    /**
     * Instantiate an ApplicationException.
     * 
     * @param errorCode int bundle code for retrieving error message.
     * @param severity severity of the Exception.
     */
    public ApplicationException(final int errorCode, final ApplicationSeverity severity) {
        this(errorCode, severity, null, (Object[]) null);
    }

    /**
     * Instantiate an ApplicationException.
     * 
     * @param errorCode int bundle code for retrieving error message.
     * @param throwable the cause (which is saved for later retrieval by the {@link #getCause()} method). (A
     *            <tt>null</tt> value is permitted, and indicates that the cause is nonexistent or unknown.)
     */
    public ApplicationException(final int errorCode, final Throwable throwable) {
        this(errorCode, null, throwable, (Object[]) null);
    }

    /**
     * Instantiate an ApplicationException.
     * 
     * @param errorKey String bundle key for retrieving error message.
     */
    public ApplicationException(final String errorKey) {
        this(errorKey, null, null, (String[]) null);
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
        if (params == CommonConstants.NULL) {
            return new String[0];
        } else {
            return Arrays.copyOf(params, params.length);
        }
    }

    /**
     * @return the objects
     */
    public Object[] getObjects() {
        if (objects == CommonConstants.NULL) {
            return new Object[0];
        } else {
            return Arrays.copyOf(objects, objects.length);
        }
    }

    /* (non-Javadoc)
     * 
     * @see java.lang.Object#toString() */
    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(CommonConstants.EXCEPTION_STRING_APPENDER1);
        buffer.append(CommonConstants.EXCEPTION_STRING_APPENDER2);
        buffer.append(this.errorKey);
        buffer.append(CommonConstants.EXCEPTION_STRING_APPENDER3);
        buffer.append(this.severity);
        buffer.append(CommonConstants.EXCEPTION_STRING_APPENDER4);
        if (this.getCause() == CommonConstants.NULL) {
            buffer.append(CommonConstants.EXCEPTION_STRING_APPENDER5);
        } else {
            buffer.append(this.getCause().toString());
        }
        if (this.params != CommonConstants.NULL && this.params.length > 0) {
            buffer.append(CommonConstants.EXCEPTION_STRING_APPENDER6);
            buffer.append(Arrays.toString(this.params));
        }
        if (this.objects != CommonConstants.NULL && this.objects.length > 0) {
            buffer.append(CommonConstants.EXCEPTION_STRING_APPENDER6);
            buffer.append(this.objects);
        }
        return buffer.toString();
    }

}
