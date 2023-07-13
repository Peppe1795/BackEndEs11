package entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Partita di calcio")
@Getter
@Setter
@NoArgsConstructor
@NamedQuery(name = "getPartiteVinteInCasa", query = "SELECT d FROM PartitaDiCalcio d WHERE d.squadraVincente = :squadraVincente")

public class PartitaDiCalcio extends Evento {
	private String squadraDiCasa;
	private String squadraOspite;
	private String squadraVincente;
	private int golSquadraDiCasa;
	private int golSquadraOspite;

	public PartitaDiCalcio(String titolo, LocalDate dataEvento, String descrizione, TipoEvento tipoEvento,
			int numeroMassimoPartecipanti, Location location, String squadraDiCasa, String squadraOspite,
			String squadraVincente, int golSquadraDiCasa, int golSquadraOspite) {
		super(titolo, dataEvento, descrizione, tipoEvento, numeroMassimoPartecipanti, location);
		this.squadraDiCasa = squadraDiCasa;
		this.squadraOspite = squadraOspite;
		this.squadraVincente = squadraVincente;
		this.golSquadraDiCasa = golSquadraDiCasa;
		this.golSquadraOspite = golSquadraOspite;
	}

}
