package App;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dao.EventoDAO;
import dao.LocationDAO;
import dao.PartecipazioneDAO;
import dao.PersonaDAO;
import entities.Concerto;
import entities.Evento;
import entities.GaraDiAtletica;
import entities.Genere;
import entities.Location;
import entities.Partecipazione;
import entities.PartitaDiCalcio;
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

		Persona primaPers = new Persona("Mario", "Rossi", "MArio@gmail.com", LocalDate.of(1980, 3, 15), Sesso.MASCHIO);
		Persona secondaPers = new Persona("Laura", "bella", "laura@gmail.com", LocalDate.of(1995, 4, 25),
				Sesso.FEMMINA);

		Location primaLoc = new Location("Castello incantato", "Padova");
		Location secondaLoc = new Location("Giardino bello", "Catania");

		Evento primo = new Evento("Concerto musicale", LocalDate.now(), "inzio ore 15 e fine ore 20",
				TipoEvento.PUBBLICO, 450, primaLoc);
		Evento secondo = new Evento("Mostra Picasso", LocalDate.now(), "inzio ore 8 e fine ore 23", TipoEvento.PRIVATO,
				50, secondaLoc);

		Partecipazione primaPart = new Partecipazione(primaPers, primo, Stato.CONFERMATA);
		Partecipazione secondaPart = new Partecipazione(secondaPers, secondo, Stato.DA_CONFERMARE);

		PartitaDiCalcio primaParita = new PartitaDiCalcio("Big match", LocalDate.of(2023, 12, 15), "Pratita sportiva",
				TipoEvento.PUBBLICO, 6000, secondaLoc, "Napoli", "Juve", "Napoli", 5, 1);
		PartitaDiCalcio secondaParita = new PartitaDiCalcio("Big match", LocalDate.of(2023, 10, 10), "Pratita sportiva",
				TipoEvento.PUBBLICO, 8000, primaLoc, "Napoli", "Milan", "Napoli", 3, 0);

		Set<Persona> setAtleti = new HashSet<>();
		setAtleti.add(primaPers);
		setAtleti.add(secondaPers);

		List<Genere> generi = new ArrayList<>();
		generi.add(Genere.CLASSICO);
		generi.add(Genere.POP);
		generi.add(Genere.ROCK);

		GaraDiAtletica primaAtl = new GaraDiAtletica("Olimpiadi estive", LocalDate.of(2023, 8, 12), "gara di atletica",
				TipoEvento.PUBBLICO, 600000, secondaLoc, setAtleti, secondaPers);
		GaraDiAtletica secondaAtl = new GaraDiAtletica("Olimpiadi invernali", LocalDate.of(2023, 12, 4),
				"gara di atletica", TipoEvento.PUBBLICO, 600000, primaLoc, setAtleti, primaPers);

		Concerto primoConc = new Concerto("Max Pezzali", LocalDate.of(2023, 10, 2), "Tour Italiano",
				TipoEvento.PUBBLICO, 50000, primaLoc, Genere.POP, true);
		Concerto seconConc = new Concerto("Max briganti", LocalDate.of(2023, 5, 1), "Tour Italiano",
				TipoEvento.PUBBLICO, 50000, secondaLoc, Genere.POP, true);

		EventoDAO ev = new EventoDAO(em);
		PersonaDAO pr = new PersonaDAO(em);
		LocationDAO lc = new LocationDAO(em);
		PartecipazioneDAO pt = new PartecipazioneDAO(em);

//		pr.save(primaPers);
//		pr.save(secondaPers);
//		lc.save(primaLoc);
//		primo.setLocation(primaLoc);
//		ev.save(primo);
//		lc.save(secondaLoc);
//		secondo.setLocation(secondaLoc);
//		ev.save(secondo);
//		pt.save(primaPart);
//		pt.save(secondaPart);
//		ev.save(primaParita);
//		ev.save(secondaParita);
//		ev.save(primaAtl);
//		ev.save(primoConc);
//		ev.save(seconConc);
//		ev.save(secondaAtl);

		Evento concerto = ev.findDataById(UUID.fromString("759cdf17-9f88-4ae6-a2b4-374be0b4f0e2"));
		System.out.println(concerto);

		List<Concerto> listaConcerti = ev.getConcertiInStreaming(true);
		listaConcerti.forEach(s -> System.out.println(s));

		List<Concerto> concertoGenere = ev.getConcertiPerGenere(generi);
		concertoGenere.forEach(s -> System.out.println(s));

		em.close();
		emf.close();

	}

}
