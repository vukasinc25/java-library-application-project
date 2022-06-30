package biblioteka;

import java.time.LocalDate;

import ljudi.Clan;
import ljudi.Zaposleni;

public class Iznajmljivanje {
	private String id;
	private LocalDate datumIznajmljivanja;
	private LocalDate datumVracanja;
	private Knjiga knjiga;
	private Primerak primerak;
	private Clan clan;
	private Zaposleni zaposleni;
	
	private boolean obrisan;
	
	public Iznajmljivanje() {
	}
	public Iznajmljivanje(String id,Primerak primerak,Clan clan,LocalDate datumIznajmljivanja, LocalDate datumVracanja,
			  Zaposleni zaposleni,boolean obrisan) {
		super();
		this.id=id;
		this.datumIznajmljivanja = datumIznajmljivanja;
		this.datumVracanja = datumVracanja;
		this.primerak = primerak;
		this.clan = clan;
		this.zaposleni=zaposleni;
		this.obrisan = obrisan;
	}
	


	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public LocalDate getDatumIznajmljivanja() {
		return datumIznajmljivanja;
	}
	public void setDatumIznajmljivanja(LocalDate datumIznajmljivanja) {
		this.datumIznajmljivanja = datumIznajmljivanja;
	}
	public LocalDate getDatumVracanja() {
		return datumVracanja;
	}
	public void setDatumVracanja(LocalDate datumVracanja) {
		this.datumVracanja = datumVracanja;
	}
	public Knjiga getKnjiga() {
		return knjiga;
	}
	public void setKnjiga(Knjiga knjiga) {
		this.knjiga = knjiga;
	}
	public Primerak getPrimerak() {
		return primerak;
	}
	public void setPrimerak(Primerak primerak) {
		this.primerak = primerak;
	}
	public Clan getClan() {
		return clan;
	}
	public void setClan(Clan clan) {
		this.clan = clan;
	}
	public Zaposleni getZaposleni() {
		return zaposleni;
	}
	public void setZaposleni(Zaposleni zaposleni) {
		this.zaposleni = zaposleni;
	}
	public boolean getObrisan() {
		return obrisan;
	}
	public void setObrisan(boolean obrisan) {
		this.obrisan = obrisan;
	}
	@Override
	public String toString() {
		return "\nIznajmljena od: " + datumIznajmljivanja
			 + "\nIznajmljena do: " + datumVracanja
			+ "\nPrimerak: " + primerak
			+ "\nClan: " + clan
			+ "\nZaposleni: " + zaposleni		
			+ "\nObrisan: " + obrisan;
	}
	
	
	
}
