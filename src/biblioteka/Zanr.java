package biblioteka;

public class Zanr {
	private String id;
	private String opis;
	private String oznaka;
	private boolean obrisan;
	
	public Zanr() {
	}


	public Zanr( String id,String opis, String oznaka,boolean obrisan) {
		super();
		this.opis = opis;
		this.id = id;
		this.oznaka = oznaka;
		this.obrisan = obrisan;
	}


	public String getOpis() {
		return opis;
	}


	public void setOpis(String opis) {
		this.opis = opis;
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


	public boolean getObrisan() {
		return obrisan;
	}


	public void setObrisan(boolean obrisan) {
		this.obrisan = obrisan;
	}

	@Override
	public String toString() {
		return "\n\nZanr: "
				+"\nId: " + id
				+"\nOpis: " + opis
				+ "\nOznaka: " + oznaka
				+ "\no=Obrisan: " + obrisan;
			
		
	}
}
