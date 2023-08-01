package kr.co.dbcs.aop;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@Aspect
public class LogAdvice {

    @Before("within(kr.co.dbcs..*)")
    public void logBefore() {

    }
}
