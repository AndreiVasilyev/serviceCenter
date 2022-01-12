package by.epam.jwdsc.service.impl;

import by.epam.jwdsc.service.EmailService;

public class EmailServiceImpl implements EmailService {

    @Override
    public void sendEmail(String toEmail){

    }
/*
    EmailMessage emailMessage = new EmailMessage();
        emailMessage.setFrom(FROM);
        emailMessage.setLogin(FROM);
        emailMessage.setPassword(PASSWORD);
        emailMessage.setSubject(params[3].replaceAll("&&&", " "));
    String[] adminEmails = params[1].replaceAll("&&&", " ").trim().split(" ");
        for (String adminEmail : adminEmails) {
        emailMessage.addTo(adminEmail);
    }
    String bookParams = params[2].replaceAll("###", "\n").replaceAll("&&&", " ");
    String message = params[4].replaceAll("&&&", " ") + "\n\n" + bookParams;
        emailMessage.setMessage(message);
        try {
        emailService.sendViaGmail(emailMessage);
    } catch (EmailExceptionService e) {
        e.printStackTrace();
    }
        return "0";
*/
}
