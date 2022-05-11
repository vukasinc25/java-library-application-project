package ljudi;


public class TipClanarine {

	protected String id;
    protected String tip;
    protected int cena;

    public TipClanarine() {
    	this.id = "";
    	this.tip = "";
    	this.cena = 0;
    }
    
    public TipClanarine(String id, String tip, int cena) {
    	this.id = id;
    	this.tip = tip;
    	this.cena = cena;
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public int getCena() {
		return cena;
	}

	public void setCena(int cena) {
		this.cena = cena;
	}
    
	@Override
	public String toString(){
		return id + "|" + tip + "|" + cena;
	}
    
    
}