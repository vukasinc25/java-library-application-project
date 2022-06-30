package ljudi;

import enumeracije.Pol;

public class Zaposleni extends Korisnik {

    protected String korisnickoIme;
    protected String lozinka;
    protected String plata;
    protected String id;
    protected boolean obrisan;
    
    public Zaposleni() {
    	
    }


	public Zaposleni( String id,String ime, String prezime,String adresa, String JMBG, String plata,String korisnickoIme, String lozinka,
			  Pol pol,boolean obrisan) {
		super(ime, prezime, JMBG, adresa, pol,obrisan);
		this.korisnickoIme = korisnickoIme;
		this.lozinka = lozinka;
		this.plata = plata;
		this.id = id;
	}



	public String getKorisnickoIme() {
		return korisnickoIme;
	}

	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}

	public String getLozinka() {
		return lozinka;
	}

	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}

	public String getPlata() {
		return plata;
	}

	public void setPlata(String plata) {
		this.plata = plata;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isObrisan() {
		return obrisan;
	}

	public void setObrisan(boolean obrisan) {
		this.obrisan = obrisan;
	}

	public void DodavanjeNovihClanova() {
		
	}
	
	@Override
	public String toString() {
		return "\n\nZaposleni: " 
		    + "\nId: " + id
		    + super.toString()
		    + "\nPlata: " + plata
			+"\nKorisnicko ime: " + korisnickoIme
			+ "\nLozinka: " + lozinka
			+ "\nObrisan: " + obrisan;
	}
	
    
}
