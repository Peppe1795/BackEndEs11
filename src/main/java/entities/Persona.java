package entities;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor

public class Persona {
	@Id
	@GeneratedValue
	private UUID id;
	private String nome;
	private String cognome1;
	private String email;
	private LocalDate dataDiNascita;
	@Enumerated(EnumType.STRING)
	private Sesso sesso;
	@OrderBy("dataEvento ASC")
	@OneToMany(mappedBy = "persona")
	private Set<Partecipazione> listaPartecipazioni;

	public Persona(String nome, String cognome1, String email, LocalDate dataDiNascita, Sesso sesso) {
		super();
		this.nome = nome;
		this.cognome1 = cognome1;
		this.email = email;
		this.dataDiNascita = dataDiNascita;
		this.sesso = sesso;
	}

	public void addPartecipazione(Partecipazione partecipazione) {
		listaPartecipazioni.add(partecipazione);
		partecipazione.setPersona(this);
	}

}
