package kr.co.dbcs.service;

import java.io.UnsupportedEncodingException;
import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements MailService {
	
	@Autowired
	JavaMailSender emailSender;
	
	private String ePw;

	@Override
	public MimeMessage creatMessage(String to) throws MessagingException, UnsupportedEncodingException {
		System.out.println("메일받을 사용자" + to);
		System.out.println("인증번호" + ePw);
		
		MimeMessage message = emailSender.createMimeMessage();
		
		message.addRecipients(RecipientType.TO, to);
		message.setSubject("[CineVerse] 회원가입 이메일 인증코드입니다.");
		
		String msgg = "";
        msgg += "<h1>안녕하세요</h1>";
        msgg += "<h1>CineVerse 입니다</h1>";
        msgg += "<br>";
        msgg += "<p>아래 인증코드를 암호변경 페이지에 입력해주세요</p>";
        msgg += "<br>";
        msgg += "<br>";
        msgg += "<div align='center' style='border:1px solid black'>";
        msgg += "<h3 style='color:blue'>회원가입 인증코드 입니다</h3>";
        msgg += "<div style='font-size:130%'>";
        msgg += "<strong>" + ePw + "</strong></div><br/>" ; // 메일에 인증번호 ePw 넣기
        msgg += "</div>";
        
        message.setText(msgg, "utf-8", "html");
        
        message.setFrom(new InternetAddress("kjy970715@naver.com", "jy"));
        
		return message;
	}

	@Override
	public String createKey() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();
        String key = random.ints(leftLimit, rightLimit + 1)
                                       .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                                       .limit(targetStringLength)
                                       .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                                       .toString();
        System.out.println("생성된 랜덤 인증코드"+ key);
        return key;
	}

	@Override
	public String sendSimpleMessage(String to) throws Exception {

        ePw = createKey(); // 랜덤 인증코드 생성
        System.out.println("********생성된 랜덤 인증코드******** => " + ePw);

        MimeMessage message = creatMessage(to); // "to" 로 메일 발송

        System.out.println("********생성된 메시지******** => " + message);


        try { // 예외처리
            emailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException();
        }

        return ePw; //
	}

}
