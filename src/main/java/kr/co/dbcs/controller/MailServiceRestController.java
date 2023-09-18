package kr.co.dbcs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.co.dbcs.service.MailService;

//@RestController
//@RequestMapping(value = "/login/mailConfirm")
//public class MailServiceRestController {
//	
//    @Autowired
//    MailService mailService;
//
//    //127.0.0.1:8080/ROOT/api/mail/confirm.json?email
//    @PostMapping(value = "/confirm.json")
//    public String mailConfirm(@RequestParam(name = "email") String email) throws Exception{
//        String code = mailService.sendSimpleMessage(email);
//        System.out.println("사용자에게 발송한 인증코드 ==> " + code);
//
//        return code;
//    }
//}

@RestController
@RequestMapping(value = "/mail")
public class MailServiceRestController {

    @Autowired
    MailService mailService;

    //127.0.0.1:8080/ROOT/api/mail/confirm.json?email
    @PostMapping(value = "/confirm")
    public String mailConfirm(@RequestParam(name = "email") String email) throws Exception{
        String code = mailService.sendSimpleMessage(email);
        System.out.println("사용자에게 발송한 인증코드 ==> " + code);

        return code;
    }
    
}