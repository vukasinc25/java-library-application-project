package biblioteka;

//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileReader;
//import java.io.IOException;
import java.time.LocalDate;
//import java.util.ArrayList;

import ljudi.Clan;
//import ljudi.Pol;
import ljudi.Zaposleni;


public class Iznajmljivanje {

    protected LocalDate datumIznajmljivanja;
    protected LocalDate datumVracanja;
    protected Zaposleni zaposleni;
    protected PrimerakKnjige primerak;
    protected Clan clan;
    protected boolean obrisan;
    
    public Iznajmljivanje() {
    	
    	this.datumIznajmljivanja = null;
    	this.datumVracanja = null;
    	this.zaposleni = null;
    	this.primerak = null;
    	this.clan = null;
    	this.obrisan = false;
    	
    }
    
    public Iznajmljivanje(LocalDate datumIznajmljivanja, LocalDate datumVracanja, Zaposleni zaposleni, PrimerakKnjige primerak,
     Clan clan) {
    	
    	this.datumIznajmljivanja = datumIznajmljivanja;
    	this.datumVracanja = datumVracanja;
    	this.zaposleni = zaposleni;
    	this.primerak = primerak;
    	this.clan = clan;
    	this.obrisan = false;
    
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
	
	public boolean isObrisan() {
		return obrisan;
	}
	public void setObrisan(boolean obrisan) {
		this.obrisan = obrisan;
	}

	@Override
	public String toString() {
		return "Iznajmljivanje [datumIznajmljivanja=" + datumIznajmljivanja + ", datumVracanja=" + datumVracanja
				+ ", zaposleni=" + zaposleni + ", clan=" + clan + ", primerak=" + primerak + "]";
	
	}

//	public ArrayList<Iznajmljivanje> citajFajl(String imeFajla) throws IOException{
//		ArrayList<Iznajmljivanje> izdknjige = new ArrayList<Iznajmljivanje>();
//		File fajl = new File(imeFajla);
//		BufferedReader citaj = new BufferedReader(new FileReader(fajl));
//		String line = null;
//		while((line = citaj.readLine())!= null) {
//			String [] niz = line.split("|");
//			LocalDate datumIznajmljivanja = LocalDate.parse(niz[0]);
//			LocalDate datumVracanja = LocalDate.parse(niz[1]);
//			String id = niz[0];
//			String ime = niz[1];
//			String prezime = niz[2];
//			String jMBG = niz[3];
//			String adresa = niz[4];
//			Pol defpol = Pol.MUSKO;
//			for(Pol pol:Pol.values()) {
//				if(pol.name().equalsIgnoreCase(polClana)) {
//					defpol = pol; 
//				}
//			Zaposleni zaposleni = new Zaposleni(id,ime,prezime,jMBG,adresa,pol,lozinka,korisnickoIme);
//			// public Iznajmljivanje(LocalDate datumIznajmljivanja, LocalDate datumVracanja, Zaposleni zaposleni, PrimerakKnjige primerak,
//		     //Clan clan) {
//		}
//		citaj.close();
//		return izdknjige;
//		
//		}
//	}
}