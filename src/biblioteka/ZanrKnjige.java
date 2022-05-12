package biblioteka;


public class ZanrKnjige {

	protected String id;
    protected String oznaka;
    protected String opisZanra;
    
    
    public ZanrKnjige() {
    	
    	this.id = "";
    	this.oznaka = "";
    	this.opisZanra = "";
    	
    }

    public ZanrKnjige(String oznaka, String opisZanra, String id) {
    	
    	this.id = id;
    	this.oznaka = oznaka;
    	this.opisZanra = opisZanra;
    	
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
    
    @Override
    public String toString() {
    	return oznaka + "|" + opisZanra + "|" + id;
    }
    
}