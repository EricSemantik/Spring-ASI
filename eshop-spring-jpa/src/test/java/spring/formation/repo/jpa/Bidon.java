package spring.formation.repo.jpa;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import spring.formation.config.ApplicationConfig;
import spring.formation.model.Fournisseur;
import spring.formation.repo.IFournisseurRepository;

public class Bidon {

	public static void main(String[] args) {
//		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		IFournisseurRepository repoFournisseur = context.getBean(IFournisseurRepository.class);

		Fournisseur fournisseur = new Fournisseur();

		fournisseur.setNom("F1");
		fournisseur.setResponsable("RESP");

		fournisseur = repoFournisseur.save(fournisseur);

		context.close();
	}

}
