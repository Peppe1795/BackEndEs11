package dao;

import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import entities.Location;

public class LocationDAO {

	private final EntityManager em;

	public LocationDAO(EntityManager em) {

		this.em = em;
	}

	public void save(Location s) {

		EntityTransaction t = em.getTransaction();
		t.begin();

		em.persist(s);

		t.commit();

		System.out.println("Dato Salvato correttamente!!!!!");
	}

	public Location findDataById(UUID id) {
		Location found = em.find(Location.class, id);
		return found;
	}

	public void delete(UUID id) {
		Location found = em.find(Location.class, id);
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
