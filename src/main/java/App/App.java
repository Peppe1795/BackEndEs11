package App;

import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dao.EventoDAO;
import dao.LocationDAO;
import dao.PartecipazioneDAO;
import dao.PersonaDAO;
import entities.Evento;
import entities.Location;
import entities.Partecipazione;
import entities.Persona;
import entities.Sesso;
import entities.Stato;
import entities.TipoEvento;
import util.JpaUtil;

public class App {

	private static EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();
	private static Logger log = LoggerFactory.getLogger(App.class);

	public static void main(String[] args) {
		EntityManager em = emf.createEntityManager();

		Evento primo = new Evento("Concerto musicale", LocalDate.now(), "inzio ore 15 e fine ore 20",
				TipoEvento.PUBBLICO, 450);
		Evento secondo = new Evento("Mostra Picasso", LocalDate.now(), "inzio ore 8 e fine ore 23", TipoEvento.PRIVATO,
				50);

		Persona primaPers = new Persona("Mario", "Rossi", "MArio@gmail.com", LocalDate.of(1980, 3, 15), Sesso.MASCHIO);
		Persona secondaPers = new Persona("Laura", "bella", "laura@gmail.com", LocalDate.of(1995, 4, 25),
				Sesso.FEMMINA);

		Location primaLoc = new Location("Castello incantato", "Padova");
		Location secondaLoc = new Location("Giardino bello", "Catania");

		Partecipazione primaPart = new Partecipazione(primaPers, primo, Stato.CONFERMATA);
		Partecipazione secondaPart = new Partecipazione(secondaPers, secondo, Stato.DA_CONFERMARE);

		EventoDAO ev = new EventoDAO(em);
		PersonaDAO pr = new PersonaDAO(em);
		LocationDAO lc = new LocationDAO(em);
		PartecipazioneDAO pt = new PartecipazioneDAO(em);

		pr.save(primaPers);
		pr.save(secondaPers);
		lc.save(primaLoc);
		primo.setLocation(primaLoc);
		ev.save(primo);
		lc.save(secondaLoc);
		secondo.setLocation(secondaLoc);
		ev.save(secondo);
		pt.save(primaPart);
		pt.save(secondaPart);

		em.close();
		emf.close();

	}

}
