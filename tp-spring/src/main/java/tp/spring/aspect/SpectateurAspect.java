package tp.spring.aspect;

import org.aspectj.lang.JoinPoint;

import tp.spring.Pianiste;

public class SpectateurAspect {
	public void assoir(JoinPoint joinPoint) {
		System.out.print("Les spectateurs s'assoient");
		if(joinPoint.getTarget() instanceof Pianiste) {
			System.out.println(" calmement");
		} else {
			System.out.println(" bruyemment");
		}
	}
	
	public void applaudir() {
		System.out.println("Les spectateurs applaudissent");
	}
	
	public void rembourser(Exception ex) {
		System.out.println("BOUH Remboursez !!! ("+ex.getMessage()+")");
	}
}
