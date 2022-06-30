package biblioteka;

import java.io.IOException;

import swingMain.Login;

public class Main {
	public static String lokacijaZanr = "src/fajlovi/zanr.txt";
	public static String lokacijaAdmin = "src/fajlovi/administratori.txt";
	public static String lokacijaKnjige= "src/fajlovi/knjige.txt";
	public static String lokacijaPrimerak= "src/fajlovi/primerak.txt";
	public static String lokacijaBibliotekar = "src/fajlovi/bibliotekari.txt";
	public static String lokacijaClanovi = "src/fajlovi/clanovi.txt";
	public static String lokacijaIznajmljivanje= "src/fajlovi/iznajmljivanje.txt";
	public static String lokacijaBiblioteka= "src/fajlovi/biblioteka.txt";
	public static String lokacijaTip= "src/fajlovi/tip.txt";
	
	public static void main(String[] args) throws IOException {
		Biblioteka biblioteka = new Biblioteka();
		biblioteka.citajBiblioteku(lokacijaBiblioteka);
		biblioteka.citajClanarine(lokacijaTip);
		biblioteka.citajClanove(lokacijaClanovi);
		biblioteka.citajAdministratora(lokacijaAdmin);
		biblioteka.citajBibliotekare(lokacijaBibliotekar);
		biblioteka.citajZanroveIzFajla(lokacijaZanr);
		biblioteka.citajKnjige(lokacijaKnjige);
		biblioteka.citajPrimerke(lokacijaPrimerak);
		biblioteka.citajIzdavanjeKnjige(lokacijaIznajmljivanje);
		Login loginProzor= new Login(biblioteka);
		loginProzor.setVisible(true);

}

		
}
