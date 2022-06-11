package ljudi;

import biblioteka.Biblioteka;

public abstract class Zaposleni extends Korisnik {

    protected String korisnickoIme;
    protected int plata;
    protected String lozinka;
    protected boolean obrisan;
    protected Biblioteka biblioteka;
    
    public Zaposleni() {
    	super();
    	this.korisnickoIme = "";
    	this.lozinka = "";
    	this.plata = 0;
    	this.obrisan = false;

    }
    
    public Zaposleni(String ime, String prezime, String jmbg, String adresa,
    		String id, Pol pol, String korisnickoIme, String lozinka, int plata, boolean obrisan) {
    	super(id, ime, prezime, jmbg, adresa, pol);
    	this.korisnickoIme = korisnickoIme;
    	this.lozinka = lozinka;
    	this.plata = plata;
    	this.obrisan = obrisan;
    }
    
    public boolean isObrisan() {
		return obrisan;
	}
	public void setObrisan(boolean obrisan) {
		this.obrisan = obrisan;
	}
    public String getKorisnickoIme() {
		return korisnickoIme;
	}
	
	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}
	public int getPlata() {
		return plata;
	}
	public void setPlata(int plata) {
		this.plata = plata;
	}
	public String getLozinka() {
		return lozinka;
	}
	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}
	
	public Biblioteka getBiblioteka() {
		return biblioteka;
	}
	public void setBiblioteka(Biblioteka biblioteka) {
		this.biblioteka = biblioteka;
	}

	@Override
	public String toString() {
		return id + "|" + ime + "|" + prezime + "|" + jmbg + "|" + adresa + "|"
	+ pol + "|" + korisnickoIme + "|" + lozinka + "|" + plata + "|" + obrisan ; 
	}
	
	protected void iznajmiKnjigu() {
        // TODO implement here
    }

    protected void registrujClana() {
        // TODO implement here
    }

}