/**
 * 
 */
package com.sanjay.common.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import javax.mail.Session;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import com.sanjay.common.enumeration.MailTransferProperties;

/**
 * @author sanjay.madnani
 * 
 */
public class OutboundMailUtilTest {

    private static OutboundMailUtil mailUtil;
    private static Session session;

    /**
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        mailUtil = new OutboundMailUtil();
    }

    /**
     * @throws java.lang.Exception
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        mailUtil = null;
    }

    @Before
    public void setUp() throws Exception {
        if (session == null) {
            session = null;
        }
    }

    /**
     * Test method for
     * {@link com.sanjay.common.util.OutboundMailUtil#getSmtpSession(java.lang.String, java.lang.String, java.lang.String, java.lang.String, com.sanjay.common.enumeration.MailTransferProperties, com.sanjay.common.enumeration.MailTransferProperties, com.sanjay.common.enumeration.MailTransferProperties, com.sanjay.common.enumeration.MailTransferProperties)}
     * .
     */
    // @Test
    public final void testGetSmtpSession() {
        session =
                mailUtil.getSmtpSession("username@gmail.com", "password", "smtp.gmail.com", "587",
                        MailTransferProperties.True, MailTransferProperties.False, MailTransferProperties.True,
                        MailTransferProperties.True);
        assertTrue(session.getDebug());
        assertEquals("smtp.gmail.com", session.getProperty("mail.smtp.host"));
        assertEquals("mail.smtp.port", session.getProperty("587"));
        assertEquals("mail.smtp.starttls.enable", session.getProperty("true"));
        assertEquals("mail.smtp.ssl.enable", session.getProperty("false"));
        assertEquals("true", session.getProperty("mail.smtp.auth"));
    }

    /**
     * Test method for
     * {@link com.sanjay.common.util.OutboundMailUtil#sendMail(javax.mail.Session, java.util.List, java.util.List, java.util.List, java.lang.String, java.lang.String, java.io.File)}
     * .
     */
    // @Test
    public final void testSendMail() {
        fail("Not yet implemented");
    }

}
