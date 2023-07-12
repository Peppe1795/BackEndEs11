package entities;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "gestione_eventi")
@Getter
@Setter
@NoArgsConstructor

public class Evento {

	@Id
	@GeneratedValue
	private UUID id;

	private String titolo;
	private LocalDate dataEvento;
	private String descrizione;

	@Enumerated(EnumType.STRING)
	private TipoEvento tipoEvento;
	private int numeroMassimoPartecipanti;
	@OneToMany
	private Set<Partecipazione> listaPArtecipazione;
	@ManyToOne
	private Location location;

	public Evento(String titolo, LocalDate dataEvento, String descrizione, TipoEvento tipoEvento,
			int numeroMassimoPartecipanti) {
		this.titolo = titolo;
		this.dataEvento = dataEvento;
		this.descrizione = descrizione;
		this.tipoEvento = tipoEvento;
		this.numeroMassimoPartecipanti = numeroMassimoPartecipanti;
	}

	@Override
	public String toString() {
		return "Evento [id=" + id + ", titolo=" + titolo + ", dataEvento=" + dataEvento + ", descrizione=" + descrizione
				+ ", tipoEvento=" + tipoEvento + ", numeroMassimoPartecipanti=" + numeroMassimoPartecipanti
				+ ", location=" + location + "]";
	}

}