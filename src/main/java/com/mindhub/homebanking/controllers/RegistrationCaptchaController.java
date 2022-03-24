package com.mindhub.homebanking.controllers;

import com.mindhub.homebanking.dtos.ClientDTO;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.registration.OnRegistrationCompleteEvent;
import com.mindhub.homebanking.captcha.ICaptchaService;
import com.mindhub.homebanking.service.IClientService;
import com.mindhub.homebanking.util.GenericResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

import javax.servlet.http.HttpServletRequest;


@RestController
public class RegistrationCaptchaController {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    private IClientService iClientService;

    @Autowired
    private ICaptchaService captchaService;


    @Autowired
    private ApplicationEventPublisher eventPublisher;

    public RegistrationCaptchaController() {
        super();
    }

    // Registration
    @PostMapping("/user/registrationCaptcha")
    public GenericResponse captchaRegisterUserAccount(@Valid final ClientDTO clientDTO, final HttpServletRequest request) {

        final String response = request.getParameter("g-recaptcha-response");
        captchaService.processResponse(response);

        return registerNewUserHandler(clientDTO, request);
    }



    private GenericResponse registerNewUserHandler(final ClientDTO clientDTO, final HttpServletRequest request) {
        LOGGER.debug("Registering user account with information: {}", clientDTO);

        final Client registered = iClientService.registerNewClientAccount(clientDTO);
        eventPublisher.publishEvent(new OnRegistrationCompleteEvent(registered, request.getLocale(), getAppUrl(request)));
        return new GenericResponse("success");
    }


    private String getAppUrl(HttpServletRequest request) {
        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }

}
