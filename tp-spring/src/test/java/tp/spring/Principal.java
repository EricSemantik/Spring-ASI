package tp.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Principal {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");

		IMusicien guitariste = context.getBean("guitariste", IMusicien.class);

		guitariste.jouer();

		IMusicien pianiste = context.getBean("pianiste", IMusicien.class);

		pianiste.jouer();

		context.close();

	}

}
