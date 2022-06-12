package biblioteka;

import enumeracije.JezikOriginala;

public class Knjiga {

    protected String naslov;
    protected String originalniNaslov;
    protected String pisac;
    protected int godinaObjavljivanja;
    protected String opisKnjige;
    protected String id;
    protected ZanrKnjige zanr;
    protected JezikOriginala jezikOriginala;
    protected boolean obrisan;
    
    public Knjiga(String naslov, String originalniNaslov, String pisac,
    		int godinaObjavljivanja, String opisKnjige, String id, ZanrKnjige zanr, JezikOriginala jezikOriginala, boolean obrisan)
    {
    	this.naslov = naslov;
    	this.originalniNaslov = originalniNaslov;
    	this.pisac = pisac;
    	this.godinaObjavljivanja = godinaObjavljivanja;
    	this.opisKnjige = opisKnjige;
    	this.id = id;
    	this.zanr = zanr;
    	this.jezikOriginala = jezikOriginala;
    	this.obrisan = false;
    }
    
    public Knjiga() {
    	super();
    	this.naslov = "";
    	this.originalniNaslov = "";
    	this.pisac = "";
    	this.godinaObjavljivanja = 0;
    	this.opisKnjige = "";
    	this.id = "";
    	this.zanr = null;
    	this.jezikOriginala = null;
    	this.obrisan = false;
    }
    
    public String getNaslov() {
		return naslov;
	}

	public void setNaslov(String naslov) {
		this.naslov = naslov;
	}

	public String getOriginalniNaslov() {
		return originalniNaslov;
	}

	public void setOriginalniNaslov(String originalniNaslov) {
		this.originalniNaslov = originalniNaslov;
	}

	public String getPisac() {
		return pisac;
	}

	public void setPisac(String pisac) {
		this.pisac = pisac;
	}

	public int getGodinaObjavljivanja() {
		return godinaObjavljivanja;
	}

	public void setGodinaObjavljivanja(int godinaObjavljivanja) {
		this.godinaObjavljivanja = godinaObjavljivanja;
	}

	public String getOpisKnjige() {
		return opisKnjige;
	}

	public void setOpisKnjige(String opisKnjige) {
		this.opisKnjige = opisKnjige;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ZanrKnjige getZanr() {
		return zanr;
	}

	public void setZanr(ZanrKnjige zanr) {
		this.zanr = zanr;
	}

	public JezikOriginala getJezikOriginala() {
		return jezikOriginala;
	}

	public void setJezikOriginala(JezikOriginala jezikOriginala) {
		this.jezikOriginala = jezikOriginala;
	}
	
	public boolean isObrisan() {
		return obrisan;
	}
	public void setObrisan(boolean obrisan) {
		this.obrisan = obrisan;
	}

	@Override
	public String toString() {
		return naslov + "|" + originalniNaslov + "|" + pisac + "|" + 
	godinaObjavljivanja + "|" + opisKnjige + "|" + zanr + "|" + jezikOriginala + "|" + id + "|" + obrisan;
	}

}