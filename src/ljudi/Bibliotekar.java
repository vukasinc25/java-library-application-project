package ljudi;

import enumeracije.Pol;

public class Bibliotekar extends Zaposleni {

  
    public Bibliotekar() {
    	super();
    }
    
    public Bibliotekar(String id, String prezime, String jmbg, String adresa,
    		String ime, Pol pol, String korisnickoIme, String lozinka, int plata, boolean obrisan) {
    	super(id, prezime, jmbg, adresa,ime , pol, korisnickoIme, lozinka, plata, obrisan);
    }
    
    @Override
	public String toString() {
		return id + "|" + ime + "|" + prezime + "|" + jmbg + "|" + adresa + "|" +
	pol + "|" + korisnickoIme + "|" + lozinka + "|" + plata + "|" + obrisan; 
	}
	
    

}