package tp.spring;

import org.springframework.beans.factory.annotation.Autowired;

public class Principal {

	@Autowired
	private IMusicien guitariste;

	@Autowired
	private IMusicien pianiste;

	public void run(String[] args) {

		guitariste.jouer();

		pianiste.jouer();

	}

}
