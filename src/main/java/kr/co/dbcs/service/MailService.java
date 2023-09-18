package kr.co.dbcs.service;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

public interface MailService {
	
	MimeMessage creatMessage(String to) throws MessagingException, UnsupportedEncodingException;
	
	String createKey();
		
	String sendSimpleMessage(String to) throws Exception;

}
