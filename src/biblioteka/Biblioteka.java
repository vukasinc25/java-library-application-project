package biblioteka;

import java.time.LocalDate;
import java.util.ArrayList;
import ljudi.Clan;
import ljudi.TipClanarine;
import ljudi.Zaposleni;

public class Biblioteka {

    protected String naziv;
    protected String adresa;
    protected String telefon;
    protected LocalDate radiOd;
    protected LocalDate radiDo;
    protected String id;
    protected ArrayList<Knjiga> knjige;
    protected ArrayList<ZanrKnjige> zanrovi;
    protected ArrayList<Clan> clanBiblioteke;
    protected ArrayList<Iznajmljivanje> iznajmljivanjeKnjige;
    protected ArrayList<TipClanarine> tipClanarine;
    protected ArrayList<PrimerakKnjige> primerakKnjige;
    protected ArrayList<Zaposleni> zaposleni;

    
    public Biblioteka() {
    	
    	this.naziv = "";
    	this.adresa = "";
    	this.telefon = "";
    	this.id = "";
    	this.radiOd = null;
    	this.radiDo = null;
    	this.knjige = null;
    	this.zanrovi = null;
    	this.tipClanarine = null;
    
    }
    
    public Biblioteka(String naziv, String adresa, String telefon, LocalDate radiOd, LocalDate radiDo, String id) {
    	
    	super();
    	this.naziv = naziv;
    	this.adresa = adresa;
    	this.telefon = telefon;
    	this.id = id;
    	this.radiOd = radiOd;
    	this.radiDo = radiDo;
    	this.knjige = null;
    	this.zanrovi = null;
    	this.tipClanarine = null;
    	//Ubaci citanje tipa clanarine
    }

}