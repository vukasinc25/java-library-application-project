package ljudi;

import enumeracije.Pol;

public abstract class Korisnik {
	protected String ime;
	protected String prezime;
	protected String JMBG;
	protected String adresa;
	protected Pol pol;
	protected boolean obrisan;
	
	public Korisnik() {
		
	}
	
	public Korisnik(String ime, String prezime, String jMBG, String adresa, Pol pol, boolean obrisan) {
		super();
		this.ime = ime;
		this.prezime = prezime;
		JMBG = jMBG;
		this.adresa = adresa;
		this.pol = pol;
		this.obrisan = obrisan;
	}
	
	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	public String getPrezime() {
		return prezime;
	}
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
	public String getJMBG() {
		return JMBG;
	}
	public void setJMBG(String jMBG) {
		JMBG = jMBG;
	}
	public String getAdresa() {
		return adresa;
	}
	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}
	public Pol getPol() {
		return pol;
	}
	public void setPol(Pol pol) {
		this.pol = pol;
	}
	
	public boolean isObrisan() {
		return obrisan;
	}
	public void setObrisan(boolean obrisan) {
		this.obrisan = obrisan;
	}
	@Override
	public String toString() {
		return "\nIme: " + ime
			 + "\nPrezime: " + prezime
			 + "\nJMBG: " + JMBG
			 + "\nAdresa: " + adresa
			 + "\nPol: "+ pol
			 ;
	}
	
	
	
}
