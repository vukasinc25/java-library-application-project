package ljudi;


public abstract class Zaposleni extends Korisnik {

    protected String korisnickoIme;
    protected int plata;
    protected String lozinka;
    
    public Zaposleni() {
    	super();
    	this.korisnickoIme = "";
    	this.lozinka = "";
    	this.plata = 0;
    }
    
    public Zaposleni(String ime, String prezime, String jmbg, String adresa,
    		String id, Pol pol, String korisnickoIme, String lozinka, int plata) {
    	super(id, ime, prezime, jmbg, adresa, pol);
    	this.korisnickoIme = korisnickoIme;
    	this.lozinka = lozinka;
    	this.plata = plata;
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

	@Override
	public String toString() {
		return id + "|" + ime + "|" + prezime + "|" + jmbg + "|" + adresa + "|" + pol + "|" + korisnickoIme + "|" + lozinka + "|" + plata; 
	}
	
	protected void iznajmiKnjigu() {
        // TODO implement here
    }

    protected void registrujClana() {
        // TODO implement here
    }

}