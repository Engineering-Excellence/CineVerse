package kr.co.dbcs.aop;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Log4j2
@Aspect
@Component
public class LogAdvice {

    @Before("execution(* kr.co.dbcs..*(..))")
    public void logBefore(JoinPoint joinPoint) {

        StringBuilder logMsg = new StringBuilder();
        logMsg.append(joinPoint.getSignature().getDeclaringTypeName());
        logMsg.append(" - ");
        logMsg.append(joinPoint.getSignature().getName());
        logMsg.append("(");

        Object[] args = joinPoint.getArgs();
        if (args != null && args.length > 0) {
            for (int i = 0; i < args.length; i++) {
                logMsg.append(args[i]);

                if (i < args.length - 1) {
                    logMsg.append(", ");
                }
            }
        }
        logMsg.append(")");

        log.info(logMsg);
    }
}
