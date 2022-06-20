package tp.spring.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LolAspect {

	
	@Around("execution(public String tp.spring.*.toString())")
	public String stop(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		System.out.println("préparation de l'instrument");
		
		return (String) proceedingJoinPoint.proceed();
		
	}
}
