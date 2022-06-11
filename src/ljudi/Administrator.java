package ljudi;

import enumeracije.Pol;

//import java.io.BufferedReader;
//import java.io.BufferedWriter;
//import java.io.File;
//import java.io.FileReader;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.util.ArrayList;
//import biblioteka.Knjiga;

public class Administrator extends Zaposleni {

   
    public Administrator() {
    	super();
    }
    
    public Administrator(String ime, String prezime, String jmbg, String adresa,
    		String id, Pol pol, String korisnickoIme, String lozinka, int plata, boolean obrisan) {
    	super(ime, prezime, jmbg, adresa, id, pol, korisnickoIme, lozinka, plata, obrisan);
    }

    @Override
    public String toString() {
    	return id + "|" + ime + "|" +  prezime + "|" +  jmbg + "|" + adresa + "|" +  pol
    			+ "|" +  korisnickoIme + "|" +  lozinka + "|" + plata + "|" + obrisan;
    }
    
    protected void registrujZaposlenog() {
        // TODO implement here
    }
}