package ljudi;

import enumeracije.Pol;

public class Bibliotekar extends Zaposleni {

  
    public Bibliotekar() {
    	super();
    }
    
    public Bibliotekar(String ime, String prezime, String jmbg, String adresa,
    		String id, Pol pol, String korisnickoIme, String lozinka, int plata, boolean obrisan) {
    	super(ime, prezime, jmbg, adresa, id, pol, korisnickoIme, lozinka, plata, obrisan);
    }
    
    @Override
	public String toString() {
		return id + "|" + ime + "|" + prezime + "|" + jmbg + "|" + adresa + "|" +
	pol + "|" + korisnickoIme + "|" + lozinka + "|" + plata + "|" + obrisan; 
	}
	
    

}