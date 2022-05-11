package biblioteka;


public class PrimerakKnjige {

    protected String id;
    protected int brStrana;
    protected int godinaStampanja;
    protected String jezikStampanja;
    protected boolean izdata;
    protected Knjiga knjiga;
    protected boolean tipPoveza;
    

    public PrimerakKnjige() {
 
    	this.id = "";
    	this.brStrana = 0;
    	this.godinaStampanja = 0;
    	this.jezikStampanja = "";
    	this.izdata = false;
    	this.tipPoveza = false;
    }
    
    public PrimerakKnjige(String id, int brStrana, int godinaStampanja, 
    		String jezikStampanja, boolean izdata, Knjiga knjiga, boolean tipPoveza) {
    	
    	this.id = id;
    	this.brStrana = brStrana;
    	this.godinaStampanja = godinaStampanja;
    	this.jezikStampanja = jezikStampanja;
    	this.izdata = izdata;
    	this.tipPoveza = tipPoveza;
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getBrStrana() {
		return brStrana;
	}

	public void setBrStrana(int brStrana) {
		this.brStrana = brStrana;
	}

	public int getGodinaStampanja() {
		return godinaStampanja;
	}

	public void setGodinaStampanja(int godinaStampanja) {
		this.godinaStampanja = godinaStampanja;
	}

	public String getJezikStampanja() {
		return jezikStampanja;
	}

	public void setJezikStampanja(String jezikStampanja) {
		this.jezikStampanja = jezikStampanja;
	}

	public boolean isIzdata() {
		return izdata;
	}

	public void setIzdata(boolean izdata) {
		this.izdata = izdata;
	}

	public Knjiga getKnjiga() {
		return knjiga;
	}

	public void setKnjiga(Knjiga knjiga) {
		this.knjiga = knjiga;
	}

	public boolean isTipPoveza() {
		return tipPoveza;
	}

	public void setTipPoveza(boolean tipPoveza) {
		this.tipPoveza = tipPoveza;
	}
    
    @Override
    public String toString() {
    	return knjiga + "|" + id + "|" + brStrana + "|" + tipPoveza + "|" + 
    			godinaStampanja + "|" + izdata;
    }


}