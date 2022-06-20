package tp.spring.aspect;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class SpectateurBisAspect {
	@Pointcut("execution(public void tp.spring.*.jouer())")
	public void iMusicienJouerPointCut() {
		
	}
	
	@Before("iMusicienJouerPointCut()")
	public void assoir() {
		System.out.println("Bis Les spectateurs s'assoient");
	}
	
	@AfterReturning(pointcut = "iMusicienJouerPointCut()", returning = "result")
	public void applaudir() {
		System.out.println("Bis Les spectateurs applaudissent");
	}
	
	@AfterThrowing(pointcut = "iMusicienJouerPointCut()", throwing = "ex")
	public void rembourser(Exception ex) {
		System.out.println("Bis BOUH Remboursez !!! ("+ex.getMessage()+")");
	}
}
