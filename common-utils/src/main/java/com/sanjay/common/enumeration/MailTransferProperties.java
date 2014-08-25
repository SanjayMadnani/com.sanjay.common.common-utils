/**
 * 
 */
package com.sanjay.common.enumeration;

/**
 * @author sanjay.madnani
 * 
 */
public enum MailTransferProperties {
    True ("true"), False ("false");

    private String strMailTransferProperty;

    private MailTransferProperties(String property) {
        this.strMailTransferProperty = property;
    }

    public String strMailTransferProperty() {
        return this.strMailTransferProperty;
    }

}
