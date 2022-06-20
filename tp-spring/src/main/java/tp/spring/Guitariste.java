package tp.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Guitariste implements IMusicien {

	private String morceau = "Vive le vent";
	
	@Autowired
	@Qualifier("ukulele")
	private IInstrument instrument;

	public Guitariste() {
		super();
	}

	
	public Guitariste(IInstrument instrument) {
		super();
		this.instrument = instrument;
	}

	@Override
	public void jouer() {
		System.out.println("Le guitariste joue " + this.morceau + " : " + instrument.toString());
	}

	public String getMorceau() {
		return morceau;
	}

	public void setMorceau(String morceau) {
		this.morceau = morceau;
	}

	public IInstrument getInstrument() {
		return instrument;
	}

	
	public void setInstrument(IInstrument instrument) {
		this.instrument = instrument;
	}

}
