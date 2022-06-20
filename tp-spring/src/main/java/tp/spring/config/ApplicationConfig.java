package tp.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import tp.spring.Guitare;
import tp.spring.Guitariste;
import tp.spring.IInstrument;
import tp.spring.IMusicien;

@Configuration
@ComponentScan("tp.spring")
public class ApplicationConfig {
	
	@Bean
	public IInstrument guitare() {
		return new Guitare();
	}
	
	@Bean
	public IMusicien guitariste(IInstrument guitare) {
		Guitariste guitariste = new Guitariste();
		guitariste.setInstrument(guitare);
		guitariste.setMorceau("Remember Me");
		
		return guitariste;
	}
}
