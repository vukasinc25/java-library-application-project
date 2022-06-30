package ljudi;

import java.time.LocalDate;

import enumeracije.Pol;

public class Clan extends Korisnik {

    private String brojClanskeKarte;
    private LocalDate datPoslednjeUplate;
    private String brMeseci;
    private TipClanarine tip;
    private boolean aktivnost;
    
    public Clan() {
    	super();
    }

	public Clan(String brojClanskeKarte, LocalDate datPoslednjeUplate, String brMeseci, TipClanarine tip,
			boolean aktivnost, String ime, String prezime, String JMBG, String adresa, Pol pol,boolean obrisan) {
		super(ime, prezime, JMBG, adresa,pol,obrisan);
		this.brojClanskeKarte = brojClanskeKarte;
		this.brMeseci = brMeseci;
		this.tip=tip;
		this.aktivnost = aktivnost;
		this.datPoslednjeUplate=datPoslednjeUplate;
	}
	

	public String getBrojClanskeKarte() {
		return brojClanskeKarte;
	}

	public void setBrojClanskeKarte(String brojClanskeKarte) {
		this.brojClanskeKarte = brojClanskeKarte;
	}

	public LocalDate getDatPoslednjeUplate() {
		return datPoslednjeUplate;
	}

	public void setDatPoslednjeUplate(LocalDate datPoslednjeUplate) {
		this.datPoslednjeUplate = datPoslednjeUplate;
	}

	public String getBrMeseci() {
		return brMeseci;
	}

	public void setBrMeseci(String brMeseci) {
		this.brMeseci = brMeseci;
	}

	
	public boolean getAktivnost() {
		return aktivnost;
	}

	public void setAktivnost(boolean aktivnost) {
		this.aktivnost = aktivnost;
	}
	
	public TipClanarine getTip() {
		return tip;
	}

	public void setTip(TipClanarine tip) {
		this.tip = tip;
	}
	
	
	@Override
	public String toString() {
		return "\nClan: " 
			+"\nBroj clanske karte: " + brojClanskeKarte
			 + "\nDatum poslednje uplate: " + datPoslednjeUplate
			 + "\nBroj meseci: " + brMeseci
			 + "\nTip clanarine: " + tip
			 + "\nAktivnost: " + aktivnost
			 + super.toString()
			 + "\nObrisan: " + obrisan;
	}

	
	
	
    

}