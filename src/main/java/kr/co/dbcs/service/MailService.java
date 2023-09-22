package kr.co.dbcs.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Map;

public interface MailService {

    MimeMessage creatMessage(String to) throws MessagingException, UnsupportedEncodingException;

    String createKey();

    String sendSimpleMessage(String to) throws Exception;

//	MimeMessage createResetPasswordMessage(String to) throws MessagingException, UnsupportedEncodingException;

//	boolean memberMapper(String to, String username) throws Exception;

	MimeMessage createResetPasswordMessage(String to, String username) throws MessagingException, UnsupportedEncodingException;

	boolean sendResetPasswordEmail(String email, String username)  throws Exception;
	
	
}
