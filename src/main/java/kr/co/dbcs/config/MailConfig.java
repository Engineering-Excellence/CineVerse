package kr.co.dbcs.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.lang.NonNull;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Objects;
import java.util.Properties;

@Configuration
@PropertySource("classpath:mail.properties")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MailConfig {

    private final Environment env;

    @Bean
    public JavaMailSender naverMailService() {

        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost(env.getProperty("mail.host"));
        javaMailSender.setUsername(env.getProperty("mail.username"));  // 네이버 아이디
        javaMailSender.setPassword(env.getProperty("mail.password"));  // 네이버 비번
        javaMailSender.setPort(Integer.parseInt(Objects.requireNonNull(env.getProperty("mail.port"))));
        javaMailSender.setJavaMailProperties(getMailProperties());

        return javaMailSender;
    }

    @NonNull
    private Properties getMailProperties() {

        Properties properties = new Properties();
        properties.setProperty("mail.transport.protocol", env.getProperty("mail.transport.protocol"));
        properties.setProperty("mail.smtp.auth", env.getProperty("mail.smtp.auth"));
        properties.setProperty("mail.smtp.starttls.enable", env.getProperty("mail.smtp.starttls.enable"));
        properties.setProperty("mail.debug", env.getProperty("mail.debug"));
        properties.setProperty("mail.smtp.ssl.trust", env.getProperty("mail.smtp.ssl.trust"));
        properties.setProperty("mail.smtp.ssl.enable", env.getProperty("mail.smtp.ssl.enable"));

        return properties;
    }
}
