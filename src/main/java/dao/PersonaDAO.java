package dao;

import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import entities.Persona;

public class PersonaDAO {

	private final EntityManager em;

	public PersonaDAO(EntityManager em) {

		this.em = em;
	}

	public void save(Persona s) {

		EntityTransaction t = em.getTransaction();
		t.begin();

		em.persist(s);

		t.commit();

		System.out.println("Dato Salvato correttamente!!!!!");
	}

	public Persona findDataById(UUID id) {
		Persona found = em.find(Persona.class, id);
		return found;
	}

	public void delete(UUID id) {
		Persona found = em.find(Persona.class, id);
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
}
