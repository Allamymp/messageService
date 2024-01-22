package org.portfolio.emailsender.service;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.portfolio.emailsender.DTO.SendEmailDTO;
import org.springframework.stereotype.Service;

import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service
public class EmailService {

    public void send(SendEmailDTO data) {
      String host = getHost(data.myEmail());
      int port = getEmailPort();
      String myEmail = data.myEmail();
      String myPassword = data.myPassword();


        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);

        Authenticator auth = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myEmail, myPassword.toCharArray());
            }
        };

        Session session = Session.getInstance(props);
        try{
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(String.join(",", contacts())));
            message.setSubject("Assunto do Email");
            message.setText("Corpo do Email");

            // Enviar a mensagem
            Transport.send(message);

            System.out.println("Email enviado com sucesso!");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private String extractDomain(String email) {

        String regular = "@(.+)$";
        Pattern pattern = Pattern.compile(regular);
        Matcher matcher = pattern.matcher(email);
        if (matcher.find()) {
           return matcher.group(1);
        } else {
            return null;
        }
    }
    private String getHost(String email){
        String domain = extractDomain(email);
        switch (domain){
            case("gmail".compareToIgnoreCase(domain)){

            }
        }
    }
};

