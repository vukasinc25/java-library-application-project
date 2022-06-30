package biblioteka;

import java.io.IOException;

import swingMain.Login;

public class Main {
	public static String PATH_TO_ADMIN = "src/fajlovi/administratori.txt";
	public static String PATH_TO_KNJIGE= "src/fajlovi/knjige.txt";
	public static String PATH_TO_BIBLIOTEKAR = "src/fajlovi/bibliotekari.txt";
	public static String PATH_TO_CLANOVI = "src/fajlovi/clanovi.txt";
	public static String PATH_TO_IZNAJMLJIVANJE= "src/fajlovi/iznajmljivanje.txt";
	public static String PATH_TO_PRIMERAK= "src/fajlovi/primerak.txt";
	public static String PATH_TO_TIP= "src/fajlovi/tip.txt";
	public static String PATH_TO_ZANR = "src/fajlovi/zanr.txt";
	public static String PATH_TO_BIBLIOTEKA= "src/fajlovi/biblioteka.txt";
	public static void main(String[] args) throws IOException {
		Biblioteka biblioteka = new Biblioteka();
		biblioteka.citajBiblioteku(PATH_TO_BIBLIOTEKA);
		biblioteka.citajClanarine(PATH_TO_TIP);
//		biblioteka.proveriAktivnost(PATH_TO_CLANOVI);
		biblioteka.citajClanove(PATH_TO_CLANOVI);
		biblioteka.citajAdministratora(PATH_TO_ADMIN);
		biblioteka.citajBibliotekare(PATH_TO_BIBLIOTEKAR);
		biblioteka.citajZanroveIzFajla(PATH_TO_ZANR);
		biblioteka.citajKnjige(PATH_TO_KNJIGE);
		biblioteka.citajPrimerke(PATH_TO_PRIMERAK);
		biblioteka.citajIzdavanjeKnjige(PATH_TO_IZNAJMLJIVANJE);
//		biblioteka.proveriAktivnost(PATH_TO_CLANOVI);
		Login loginProzor= new Login(biblioteka);
		loginProzor.setVisible(true);
//		addKnjige prozor=new addKnjige(biblioteka);
//		prozor.setVisible(true);
}

		
}
