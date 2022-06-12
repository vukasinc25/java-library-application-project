package ljudi;

import enumeracije.Pol;

public abstract class Korisnik {

    protected String ime;
    protected String prezime;
    protected String jmbg;
    protected String adresa;
    protected String id;
    protected Pol pol;
    protected boolean obrisan;
    
    public Korisnik() {
    	this.ime = "";
    	this.prezime = "";
    	this.jmbg = "";
    	this.adresa = "";
    	this.id = "";
    	this.pol = null;
    }
    

	public Korisnik( String id, String prezime, String jmbg, String adresa, String ime, Pol pol) {
		super();
		this.ime = ime;
		this.prezime = prezime;
		this.jmbg = jmbg;
		this.adresa = adresa;
		this.id = id;
		this.pol = pol;
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

	public String getJmbg() {
		return jmbg;
	}

	public void setJmbg(String jmbg) {
		this.jmbg = jmbg;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public String getId() {
		System.out.println(id);
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	public Pol getPol() {
		return pol;
	}

	public void setPol(Pol pol) {
		this.pol = pol;
	}

}