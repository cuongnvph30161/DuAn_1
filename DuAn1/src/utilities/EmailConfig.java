/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilities;

import jakarta.mail.Authenticator;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import java.util.Properties;

/**
 *
 * @author Admin
 */
public class EmailConfig {

    private final String host;
    private final int port;
    private final String username;
    private final String password;
    private final boolean starttlsEnabled;

    public EmailConfig(String host, int port, String username, String password, boolean starttlsEnabled) {
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
        this.starttlsEnabled = starttlsEnabled;
    }

    public Properties getProperties() {
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", String.valueOf(port));
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", String.valueOf(starttlsEnabled));
        return props;
    }

    public Session getSession() {
        return Session.getInstance(getProperties(),
                new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
    }
}
