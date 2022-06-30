package biblioteka;

import enumeracije.Jezik;

public class Knjiga {
	private String id;
	private String naslov;
	private String original;
	private String pisac;
	private String godinaObjavljivanja;
	private String opis;
	private Jezik jezikk;
	private Zanr zanr;
	private boolean obrisan;
	
	public Knjiga() {
		super();
	}




	public Knjiga(String id, String naslov, String original, String pisac, String godinaObjavljivanja, String opis,
			Jezik jezikk, Zanr zanr,boolean obrisan) {
		super();
		this.id = id;
		this.naslov = naslov;
		this.original = original;
		this.pisac = pisac;
		this.godinaObjavljivanja = godinaObjavljivanja;
		this.opis = opis;
		this.jezikk = jezikk;
		this.zanr = zanr;
		this.obrisan=obrisan;
	}
	




	public String getId() {
		return id;
	}




	public void setId(String id) {
		this.id = id;
	}




	public String getNaslov() {
		return naslov;
	}




	public void setNaslov(String naslov) {
		this.naslov = naslov;
	}




	public String getOriginal() {
		return original;
	}




	public void setOriginal(String original) {
		this.original = original;
	}




	public String getPisac() {
		return pisac;
	}




	public void setPisac(String pisac) {
		this.pisac = pisac;
	}




	public String getGodinaObjavljivanja() {
		return godinaObjavljivanja;
	}




	public void setGodinaObjavljivanja(String godinaObjavljivanja) {
		this.godinaObjavljivanja = godinaObjavljivanja;
	}




	public String getOpis() {
		return opis;
	}




	public void setOpis(String opis) {
		this.opis = opis;
	}




	public Jezik getJezikk() {
		return jezikk;
	}




	public void setJezikk(Jezik jezikk) {
		this.jezikk = jezikk;
	}




	public Zanr getZanr() {
		return zanr;
	}




	public void setZanr(Zanr zanr) {
		this.zanr = zanr;
	}



	public boolean isObrisan() {
		return obrisan;
	}




	public void setObrisan(boolean obrisan) {
		this.obrisan = obrisan;
	}




	@Override
	public String toString() {
		return  "\n\nKnjiga:"
				+"\nId: " + id
				+"\nNaslov: " + naslov
				+ "\nOriginal: " + original
				+ "\nPisac: " + pisac
				+ "\nGodina objavljivanja: " + godinaObjavljivanja
				+"\nOpis: " + opis
				+ "\nJezik: " + jezikk
				+ "\nZanr: " + zanr.getOznaka()
				+ "\nObrisan: " + obrisan;
	}
	
	

}
