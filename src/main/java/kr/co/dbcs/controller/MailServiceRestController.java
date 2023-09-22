package kr.co.dbcs.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import kr.co.dbcs.service.MailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping(value = "/mail")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MailServiceRestController {

    private final MailService mailService;

    //127.0.0.1:8080/ROOT/api/mail/confirm.json?email
    @PostMapping(value = "/confirm")
    public String mailConfirm(@RequestParam(name = "email") String email) throws Exception {
        String code = mailService.sendSimpleMessage(email);
        log.info("사용자에게 발송한 인증코드 ==> {}", code);
        return code;
    }
    
    @PostMapping(value="/resetpwdmail")
    @ResponseBody
    public boolean resetPassword(@RequestParam(name = "email") String email,
            @RequestParam(name = "username") String username) throws Exception {
    	
    	return mailService.sendResetPasswordEmail(email, username);

    }

}