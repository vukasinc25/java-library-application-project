package biblioteka;

import java.time.LocalDate;

import ljudi.Clan;
import ljudi.Zaposleni;


public class Iznajmljivanje {

    protected LocalDate datumIznajmljivanja;
    protected LocalDate datumVracanja;
    protected Zaposleni zaposleni;
    protected PrimerakKnjige primerak;
    protected Clan clan;
    
    public Iznajmljivanje() {
    	this.datumIznajmljivanja = null;
    	this.datumVracanja = null;
    	this.zaposleni = null;
    	this.primerak = null;
    	this.clan = null;
    }
    
    public Iznajmljivanje(LocalDate datumIznajmljivanja, LocalDate datumVracanja, Zaposleni zaposleni, PrimerakKnjige primerak,
     Clan clan) {
    	
    	this.datumIznajmljivanja = datumIznajmljivanja;
    	this.datumVracanja = datumVracanja;
    	this.zaposleni = zaposleni;
    	this.primerak = primerak;
    	this.clan = clan;
    
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

	public Zaposleni getZaposleni() {
		return zaposleni;
	}

	public void setZaposleni(Zaposleni zaposleni) {
		this.zaposleni = zaposleni;
	}

	public PrimerakKnjige getPrimerak() {
		return primerak;
	}

	public void setPrimerak(PrimerakKnjige primerak) {
		this.primerak = primerak;
	}

	public Clan getClan() {
		return clan;
	}

	public void setClan(Clan clan) {
		this.clan = clan;
	}
	
	@Override
	public String toString() {
		return "Iznajmljivanje [datumIznajmljivanja=" + datumIznajmljivanja + ", datumVracanja=" + datumVracanja
				+ ", zaposleni=" + zaposleni + ", clan=" + clan + ", primerak=" + primerak + "]";
	
	}

}