/* Copyright (C) 2014, 2015 Sanjay Madnani
 * 
 * This file is free to use: you can redistribute it and/or modify it under the terms of the GPL General Public License
 * V2 as published by the Free Software Foundation, subject to the following conditions:
 * 
 * The above copyright notice should never be changed and should always included wherever this file is used.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY.
 * See the GNU General Public License V2 for more details. */
package com.sanjay.common.enumeration;

/**
 * Contains String representation of boolean properties required for sending a mail.
 * 
 * @author sanjay.madnani
 * @see com.sanjay.common.dto.OutboundDTO
 * @see com.sanjay.common.util.OutboundMailUtil
 */
public enum MailTransferProperties {
    TRUE ("true"), FALSE ("false");

    private final String strMailTransferProperty;

    private MailTransferProperties(final String property) {
        this.strMailTransferProperty = property;
    }

    /**
     * Return caller enumeration assign string value.
     * 
     * @return string value corresponding to a caller enumeration.
     */
    public String strMailTransferProperty() {
        return this.strMailTransferProperty;
    }

}
