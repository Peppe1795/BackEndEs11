package dao;

import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import entities.Concerto;
import entities.Evento;
import entities.Genere;

public class EventoDAO {

	private final EntityManager em;

	public EventoDAO(EntityManager em) {

		this.em = em;
	}

	public void save(Evento s) {

		EntityTransaction t = em.getTransaction();
		t.begin();

		em.persist(s);

		t.commit();

		System.out.println("Dato Salvato correttamente!!!!!");
	}

	public Evento findDataById(UUID id) {
		Evento found = em.find(Evento.class, id);
		return found;
	}

	public void delete(UUID id) {
		Evento found = em.find(Evento.class, id);
		if (found != null) {

			EntityTransaction t = em.getTransaction();
			t.begin();
			em.remove(found);
			t.commit();

			System.out.println("Dato eliminato correttamente");
		} else {
			System.out.println("Id errato non corrisponde a nessun dato nell'archivio!!!!");
		}

	}

	public void refresh(UUID id) {
		Evento found = em.find(Evento.class, id);

		found.setTitolo("Ciccio Pasticcio");

		System.out.println("PRE REFRESH");
		System.out.println(found);

		em.refresh(found);

		System.out.println("POST REFRESH");
		System.out.println(found);
	}

	public List<Concerto> getConcertiInStreaming(boolean isInStreaming) {
		TypedQuery<Concerto> streaming = em
				.createQuery("SELECT s FROM Concerto s WHERE s.isInStreaming= :isInStreaming", Concerto.class);
		streaming.setParameter("isInStreaming", isInStreaming);
		return streaming.getResultList();
	}

	public List<Concerto> getConcertiPerGenere(List<Genere> generiConcerto) {
		TypedQuery<Concerto> genere = em
				.createQuery("SELECT s FROM Concerto s WHERE s.generiConcerto IN: generiConcerto ", Concerto.class);
		genere.setParameter("generiConcerto", generiConcerto);
		return genere.getResultList();
	}

}
