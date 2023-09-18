package kr.co.dbcs.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

public interface MailService {

    MimeMessage creatMessage(String to) throws MessagingException, UnsupportedEncodingException;

    String createKey();

    String sendSimpleMessage(String to) throws Exception;
}
