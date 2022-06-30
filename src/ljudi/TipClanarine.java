package ljudi;

public class TipClanarine {

	private String id;
	private String opis;
	private String cena;
	private boolean obrisan;
	
	public TipClanarine() {
		super();
	}

	public TipClanarine(String id, String opis, String cena,boolean obrisan) {
		super();
		this.id = id;
		this.opis = opis;
		this.cena = cena;
		this.obrisan = obrisan;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public String getCena() {
		return cena;
	}

	public void setCena(String cena) {
		this.cena = cena;
	}

	public boolean getObrisan() {
		return obrisan;
	}

	public void setObrisan(boolean obrisan) {
		this.obrisan = obrisan;
	}





	@Override
	public String toString() {
		return "\nId: " + id
			 + "\nOpis: " + opis
			 + "\nCena: " + cena
			 + "\nObrisan: "+ obrisan;
	}
	
}
