package biblioteka;

import java.io.IOException;
import java.time.LocalDate;

import biblioteka.Biblioteka;
import biblioteka.Iznajmljivanje;
import biblioteka.Knjiga;
import biblioteka.PrimerakKnjige;
import biblioteka.ZanrKnjige;
import enumeracije.JezikOriginala;
import enumeracije.Pol;
import ljudi.Administrator;
import ljudi.Clan;
import ljudi.TipClanarine;
import swingMain.Login;

public class Main {

	public static void main(String[] args) throws IOException {
		Biblioteka biblioteka = new Biblioteka("Naziv","Adresa","Telefon", LocalDate.parse("2007-02-03"),LocalDate.parse("2008-05-05"),"ID");
		biblioteka.citajAdministratora();
		biblioteka.citajBibliotekara();
		biblioteka.citajAdministratora();
		biblioteka.citajClanarine();
		biblioteka.citajZanr();
		biblioteka.citajKnjige();
		biblioteka.citajPrimerke();
		biblioteka.citajClanove();
		Login login = new Login(biblioteka);
		login.setVisible(true);
		
//		Biblioteka biblioteka = new Biblioteka("a","b","c",LocalDate.parse("2007-02-03"),LocalDate.parse("2008-05-05"),"d");
//		try {
//			biblioteka.upisiFajl(biblioteka);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	
		//ZanrKnjige zanr = new ZanrKnjige("zanr","zanrOpis","1");
		//System.out.println(biblioteka.zanrovi);
		//Knjiga knjiga = new Knjiga("Naslov","OrgNaslov","Pisac",1920,"Opis","1",zanr, JezikOriginala.Engleski);
		//System.out.println(biblioteka.citajKnjige("src/fajlovi/knjige.txt"));
		//try {
			//biblioteka.upisiFajl(knjiga);

		//} catch (IOException e1) {
			//e1.printStackTrace();
		//}
		//biblioteka.azurirajKnjige("2");
		
		//Administrator admin = new Administrator("Jovan", "Jovanovic","123456789","MarsalaTita5","1",Pol.ZENSKO,"joca123","jocasifra2", 2000, false);
		//biblioteka.upisiFajlAdministartor();
//		System.out.println(biblioteka.citajKnjige("src/fajlovi/knjige.txt"));
//		TipClanarine tip = new TipClanarine("nvjvr","nvjnvje",0);
//		biblioteka.upisiTipClanarine(tip);
//		Clan clan = new Clan("1","Mika","Mikic","8658568568568","ZikeZikica",Pol.ZENSKO,"vyvbi",LocalDate.parse("2009-02-03"),6,true,tip);
//		biblioteka.upisiFajlClanBiblioteke(clan);
		//PrimerakKnjige primerak = new PrimerakKnjige("1", 135, 1990, JezikOriginala.Engleski, true,knjiga,false);
		//biblioteka.upisiPrimerakKnjige(primerak);
		//Iznajmljivanje izdavanje = new Iznajmljivanje(LocalDate.parse("2007-02-03"),LocalDate.parse("2007-02-03"),zaposleni,clan,primerak);
		//biblioteka.upisiIznajmljivanje(izdavanje);
//		System.out.println(biblioteka.citajClanove("src/fajlovi/clanbiblioteke.txt"));
//		System.out.println(biblioteka.citajClanove("src/fajlovi/clanbiblioteke.txt"));
//		System.out.println(biblioteka.citajAdministratora("src/fajlovi/administartor.txt"));
		}

	}
