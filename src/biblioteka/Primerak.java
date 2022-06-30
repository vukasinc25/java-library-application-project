package biblioteka;

import enumeracije.Jezik;
import enumeracije.Korice;

public class Primerak {
	private Knjiga knjiga;
	private String brStrana;
	private String godStampanja;
	private String id;
	private boolean obrisan;
	private Korice korica;
	private Jezik jezikk;
	private boolean iznajmljena;
	
	public Primerak() {
	}

	public Primerak( String id,Knjiga knjiga, String brStrana, String godStampanja, Korice korica, Jezik jezikk,
			boolean iznajmljena,boolean obrisan) {
		super();
		this.knjiga = knjiga;
		this.brStrana = brStrana;
		this.godStampanja = godStampanja;
		this.id = id;
		this.obrisan = obrisan;
		this.korica = korica;
		this.jezikk = jezikk;
		this.iznajmljena = iznajmljena;
	}
	
	public Knjiga getKnjiga() {
		return knjiga;
	}



	public void setKnjiga(Knjiga knjiga) {
		this.knjiga = knjiga;
	}



	public String getBrStrana() {
		return brStrana;
	}



	public void setBrStrana(String brStrana) {
		this.brStrana = brStrana;
	}



	public String getGodStampanja() {
		return godStampanja;
	}



	public void setGodStampanja(String godStampanja) {
		this.godStampanja = godStampanja;
	}



	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public boolean getObrisan() {
		return obrisan;
	}



	public void setObrisan(boolean obrisan) {
		this.obrisan = obrisan;
	}



	public Korice getKorica() {
		return korica;
	}



	public void setKorica(Korice korica) {
		this.korica = korica;
	}



	public Jezik getJezikk() {
		return jezikk;
	}



	public void setJezikk(Jezik jezikk) {
		this.jezikk = jezikk;
	}



	public boolean getIznajmljena() {
		return iznajmljena;
	}



	public void setIznajmljena(boolean iznajmljena) {
		this.iznajmljena = iznajmljena;
	}


	@Override
	public String toString() {
		return 	"\n\nPrimerak: "
			 + "\nId: " + id
			 + "\nKnjiga: " + knjiga
			 + "\nBroj strana: " + brStrana
			 + "\nGodina Stampanja: " + godStampanja
			 + "\nKorice: " + korica
			 + "\nJezik: " + jezikk
			 + "\nIznajmljena: " + iznajmljena;
		
	}
	
	
}
