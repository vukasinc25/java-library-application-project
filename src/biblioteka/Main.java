package biblioteka;

import java.io.IOException;

import swingMain.Login;

public class Main {
	public static void main(String[] args) throws IOException {
		Biblioteka biblioteka = new Biblioteka();
		biblioteka.citajClanove("src/fajlovi/clanovi.txt");
		biblioteka.citajAdministratora("src/fajlovi/administratori.txt");
		biblioteka.citajClanarine("src/fajlovi/tip.txt");
		biblioteka.citajKnjige("src/fajlovi/knjige.txt");
		biblioteka.citajBiblioteku("src/fajlovi/biblioteka.txt");
		biblioteka.citajIzdavanjeKnjige("src/fajlovi/iznajmljivanje.txt");
		biblioteka.citajBibliotekare("src/fajlovi/bibliotekari.txt");
		biblioteka.citajZanroveIzFajla("src/fajlovi/zanr.txt");
		biblioteka.citajPrimerke("src/fajlovi/primerak.txt");
		Login login= new Login(biblioteka);
		login.setVisible(true);
}

		
}
