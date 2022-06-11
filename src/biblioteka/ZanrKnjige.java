package biblioteka;


public class ZanrKnjige {

	protected String id;
    protected String oznaka;
    protected String opisZanra;
    protected boolean obrisan;
    
    
    public ZanrKnjige() {
    	
    	this.id = "";
    	this.oznaka = "";
    	this.opisZanra = "";
    	this.obrisan = false;
    	
    }

    public ZanrKnjige(String oznaka, String opisZanra, String id, boolean obrisan) {
    	
    	this.id = id;
    	this.oznaka = oznaka;
    	this.opisZanra = opisZanra;
    	this.obrisan = false;
    	
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOznaka() {
		return oznaka;
	}

	public void setOznaka(String oznaka) {
		this.oznaka = oznaka;
	}

	public String getOpisZanra() {
		return opisZanra;
	}

	public void setOpisZanra(String opisZanra) {
		this.opisZanra = opisZanra;
	}
	
    public boolean isObrisan() {
		return obrisan;
	}
	public void setObrisan(boolean obrisan) {
		this.obrisan = obrisan;
	}

	@Override
    public String toString() {
    	return oznaka + "|" + opisZanra + "|" + id + "|" + obrisan;
    }
    
}