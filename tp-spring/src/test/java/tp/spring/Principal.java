package tp.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import tp.spring.config.ApplicationConfig;

public class Principal {

	public static void main(String[] args) {
//		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		
		IMusicien guitariste = context.getBean("guitariste", IMusicien.class);

		guitariste.jouer();

		IMusicien pianiste = context.getBean("pianiste", IMusicien.class);

		pianiste.jouer();

		context.close();

	}

}
