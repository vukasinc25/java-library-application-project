package ljudi;

import java.time.LocalDate;


public class Clan extends Korisnik {

    protected String brClanskeKarte;
    protected LocalDate datumUplate;
    protected int uplacenoMeseci;
    protected boolean aktivan;
    protected TipClanarine tipClanarine1;
    protected boolean obrisan;

    public Clan() {
    	super();
    	this.brClanskeKarte = "";
    	this.datumUplate = null;
    	this.uplacenoMeseci = 0;
    	this.aktivan =  false;
    	this.tipClanarine1 =  null;	
    	this.obrisan = false;

    }

    public Clan(String ime, String prezime, String jmbg, String adresa,
    		String id, Pol pol, String brClanskeKarte, LocalDate datumUplate,
    		int uplacenoMeseci, boolean aktivan, TipClanarine tipClanarine1, boolean obrisan) {
    	super(ime, prezime, id, jmbg, adresa, pol);
    	this.brClanskeKarte = brClanskeKarte;
    	this.datumUplate = datumUplate;
    	this.uplacenoMeseci = uplacenoMeseci;
    	this.aktivan =  aktivan;
    	this.tipClanarine1 =  tipClanarine1;
    	this.obrisan = obrisan;
    }

	public String getBrClanskeKarte() {
		return brClanskeKarte;
	}
	public void setBrClanskeKarte(String brClanskeKarte) {
		this.brClanskeKarte = brClanskeKarte;
	}
	public LocalDate getDatumUplate() {
		return datumUplate;
	}
	public void setDatumUplate(LocalDate datumUplate) {
		this.datumUplate = datumUplate;
	}
	public int getUplacenoMeseci() {
		return uplacenoMeseci;
	}
	public void setUplacenoMeseci(int uplacenoMeseci) {
		this.uplacenoMeseci = uplacenoMeseci;
	}
	public boolean isAktivan() {
		return aktivan;
	}
	public void setAktivan(boolean aktivan) {
		this.aktivan = aktivan;
	}
	public TipClanarine getTipClanarine() {
		return tipClanarine1;
	}
	public void setTipClanarine(TipClanarine tipClanarine) {
		this.tipClanarine1 = tipClanarine;
	}
	public boolean isObrisan() {
		return obrisan;
	}
	public void setObrisan(boolean obrisan) {
		this.obrisan = obrisan;
	}

	@Override
	public String toString() {
		return id + "|" + ime + "|" + prezime + "|" + jmbg + "|" + 
	adresa + "|" + pol + "|" + brClanskeKarte + "|" + datumUplate + "|" + 
				uplacenoMeseci + "|" + aktivan + "|" + tipClanarine1 +"|"+ obrisan; 
	}
	
    
    
}