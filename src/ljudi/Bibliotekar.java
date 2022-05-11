package ljudi;


public class Bibliotekar extends Zaposleni {

  
    public Bibliotekar() {
    	super();
    }
    
    public Bibliotekar(String ime, String prezime, String jmbg, String adresa,
    		String id, Pol pol, String korisnickoIme, String lozinka, int plata) {
    	super(ime, prezime, jmbg, adresa, id, pol, korisnickoIme, lozinka, plata);
    }
    
    @Override
	public String toString() {
		return id + "|" + ime + "|" + prezime + "|" + jmbg + "|" + adresa + "|" + pol + "|" + korisnickoIme + "|" + lozinka + "|" + plata; 
	}
	
    

}