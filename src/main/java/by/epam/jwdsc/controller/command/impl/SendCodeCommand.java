package by.epam.jwdsc.controller.command.impl;

import by.epam.jwdsc.controller.command.Command;
import by.epam.jwdsc.controller.command.Router;
import by.epam.jwdsc.service.EmailService;
import by.epam.jwdsc.service.ServiceProvider;
import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletRequest;

import static by.epam.jwdsc.controller.command.Router.RouterType.RESPONSE_BODY;

public class SendCodeCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        String answer = "some message";
        String json = new Gson().toJson(answer);
        String toEmail=request.getParameter("email");
        //generate confirmation code

        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        EmailService emailService = serviceProvider.getEmailService();
       // emailService.sendEmail(toEmail);





        return new Router(RESPONSE_BODY, json);
    }
}
