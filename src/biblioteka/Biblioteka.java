package biblioteka;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import ljudi.Administrator;
import ljudi.Bibliotekar;
import ljudi.Clan;
import ljudi.Pol;
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
    protected ArrayList<Administrator> admin;
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

    public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public String getAdresa() {
		return adresa;
	}
	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}
	public String getTelefon() {
		return telefon;
	}
	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}
	public LocalDate getRadiOd() {
		return radiOd;
	}
	public void setRadiOd(LocalDate radiOd) {
		this.radiOd = radiOd;
	}
	public LocalDate getRadiDo() {
		return radiDo;
	}
	public void setRadiDo(LocalDate radiDo) {
		this.radiDo = radiDo;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public ArrayList<Knjiga> getKnjige() {
		return knjige;
	}
	public void setKnjige(ArrayList<Knjiga> knjige) {
		this.knjige = knjige;
	}
	public ArrayList<ZanrKnjige> getZanrovi() {
		return zanrovi;
	}
	public void setZanrovi(ArrayList<ZanrKnjige> zanrovi) {
		this.zanrovi = zanrovi;
	}
	public ArrayList<Administrator> getAdmin() {
		return admin;
	}
	public void setAdmin(ArrayList<Administrator> admin) {
		this.admin = admin;
	}
	public ArrayList<Clan> getClanBiblioteke() {
		return clanBiblioteke;
	}
	public void setClanBiblioteke(ArrayList<Clan> clanBiblioteke) {
		this.clanBiblioteke = clanBiblioteke;
	}
	public ArrayList<Iznajmljivanje> getIznajmljivanjeKnjige() {
		return iznajmljivanjeKnjige;
	}
	public void setIznajmljivanjeKnjige(ArrayList<Iznajmljivanje> iznajmljivanjeKnjige) {
		this.iznajmljivanjeKnjige = iznajmljivanjeKnjige;
	}
	public ArrayList<TipClanarine> getTipClanarine() {
		return tipClanarine;
	}
	public void setTipClanarine(ArrayList<TipClanarine> tipClanarine) {
		this.tipClanarine = tipClanarine;
	}
	public ArrayList<PrimerakKnjige> getPrimerakKnjige() {
		return primerakKnjige;
	}
	public void setPrimerakKnjige(ArrayList<PrimerakKnjige> primerakKnjige) {
		this.primerakKnjige = primerakKnjige;
	}
	public ArrayList<Zaposleni> getZaposleni() {
		return zaposleni;
	}
	public void setZaposleni(ArrayList<Zaposleni> zaposleni) {
		this.zaposleni = zaposleni;
	}
	
	
	public static ArrayList<TipClanarine>  citajClanarine(String fajlClanovi) throws IOException{
		ArrayList<TipClanarine> tipClanarine = new ArrayList<TipClanarine>();
		File file = new File(fajlClanovi);
		BufferedReader citanje = new BufferedReader(new FileReader(file));
		String line1 = null;
		while((line1 = citanje.readLine())!= null) {
			String[]nizClanova = line1.split("\\|");
			String id = nizClanova[0];
			String tip = nizClanova[1];
			int cena = Integer.parseInt(nizClanova[2]);
			TipClanarine tip2  = new TipClanarine(id, tip, cena);
			tipClanarine.add(tip2);
		}
		citanje.close();
		return tipClanarine;
		
	}
	public void upisiTipClanarine(TipClanarine t) throws IOException{
//		ArrayList<TipClanarine> tip = tipUpis;
		File file = new File("src/fajlovi/tipclanarine.txt");
		BufferedWriter writer = new BufferedWriter(new FileWriter(file,true));
//		for(TipClanarine t:tip) {
			String sb = t.getTip()+ "|"+ t.getId()+ "|"+t.getCena();
			writer.write(sb);
			writer.newLine();;
// 		}
		writer.close();
	}
	
	public void obrisiKnjigu(String id) throws IOException {
		//ArrayList<Knjiga> knjige = new ArrayList<Knjiga>();
		File fajl = new File("src/fajlovi/knjige.txt");
		BufferedReader citaj = new BufferedReader(new FileReader(fajl));
		String line = null;
		ArrayList<String> lista = new ArrayList<>();
		while((line = citaj.readLine())!= null) {
			String[] niz = line.split("\\|");
			System.out.println(niz[6]);
			System.out.println(id);
			if(!(id.equals(niz[6]))) {
				lista.add(line);
				}			
		}
		citaj.close();
		BufferedWriter writer = new BufferedWriter(new FileWriter(fajl));
		for(String l: lista) {
			writer.write(l);
			writer.newLine();
		}
		writer.close();
	}
	
	public void azurirajKnjige(String id) throws IOException {
		//ArrayList<Knjiga> knjige = new ArrayList<Knjiga>();
		File fajl = new File("src/fajlovi/knjige.txt");
		BufferedReader citaj = new BufferedReader(new FileReader(fajl));
		String line = null;
		ArrayList<String> lista = new ArrayList<>();
		while((line = citaj.readLine())!= null) {
			String[] niz = line.split("\\|");
			System.out.println(niz[6]);
			System.out.println(id);
			if(!(id.equals(niz[6]))) {
				lista.add(line);
				}	
			else {
				lista.add("Izmenjeno");
			}
		}
		citaj.close();
		BufferedWriter writer = new BufferedWriter(new FileWriter(fajl));
		for(String l: lista) {
			writer.write(l);
			writer.newLine();
		}
		writer.close();
	}
	
	public ArrayList<Knjiga> citajKnjige(String imeFajla) throws IOException{
		ArrayList<Knjiga> knjige = new ArrayList<Knjiga>();
		File fajl = new File(imeFajla);
		BufferedReader citaj = new BufferedReader(new FileReader(fajl));
		String line = null;
		while((line = citaj.readLine())!= null) {
			String [] niz = line.split("\\|");
			String id  = niz[5];
			String naslov = niz[0];
			String originalniNaslov = niz[1];
			String pisac = niz[2];
			int godinaObjavljivanja = Integer.parseInt(niz[3]);
			String jezikOroginala = niz[7];
			JezikOriginala jezikOriginala = JezikOriginala.Engleski;
			for (JezikOriginala j: JezikOriginala.values()) {
				if(j.name().equalsIgnoreCase(jezikOroginala)){
					jezikOriginala = j;
				}
			}
			String opisKnjige = niz[4];
			ArrayList<ZanrKnjige> zanrovi = citajZanr("src/fajlovi/zanrovi.txt");
			ZanrKnjige zanr = null;
			for (ZanrKnjige z : zanrovi) {
				if(z.getId().equals(niz[6])) {
					zanr = z;
				}
			}
			Knjiga knjiga = new Knjiga(naslov, originalniNaslov, pisac, godinaObjavljivanja, opisKnjige, id, zanr, jezikOriginala);
			knjige.add(knjiga);
		}
		citaj.close();
		return knjige;
	}
	public void upisiFajl(Knjiga k1) throws IOException{
//		ArrayList<Knjiga> knjige = kjnigeUpis;
		File file = new File("src/fajlovi/knjige.txt");
		BufferedWriter writer = new BufferedWriter(new FileWriter(file,true));
		String sb = k1.getNaslov() +"|"+ k1.getOriginalniNaslov() + "|"+k1.getPisac()+ "|"+k1.getGodinaObjavljivanja() +"|"+ k1.getOpisKnjige()+ "|" + k1.getId()+"|"+k1.getZanr().getId()+"|"+k1.getJezikOriginala();
		writer.write(sb);
		writer.newLine();
		writer.close();
		
	}
	
	public ArrayList<ZanrKnjige> citajZanr(String imeFajla) throws IOException{
		ArrayList<ZanrKnjige> zanrknjige = new ArrayList<ZanrKnjige>();
		File fajl = new File(imeFajla);
		BufferedReader citaj = new BufferedReader(new FileReader(fajl));
		String line = null;
		while((line = citaj.readLine())!= null) {
			String [] niz = line.split("\\|");
			String id = niz[2];
			String oznaka = niz[0];
			String opisZanra = niz[1];
			ZanrKnjige zanr = new ZanrKnjige(oznaka, opisZanra, id);
			zanrknjige.add(zanr);
		}
		citaj.close();
		return zanrknjige;	
	}
	public void upisiZanr(ZanrKnjige z) throws IOException{
		File file = new File("src/fajlovi/zanrovi.txt");
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		String sb = z.getOznaka() + "|"+ z.getOpisZanra() + "|" + z.getId();
		writer.write(sb);
		writer.newLine();
		writer.close();
	}
	
	public void upisiFajl(Biblioteka k ) throws IOException{
		File file = new File("src/fajlovi/biblioteka.txt");
		BufferedWriter writer = new BufferedWriter(new FileWriter(file,true));
		
		String sb = k.getAdresa() +"|"+ k.getId() +"|"+ k.getNaziv() + "|"+k.getTelefon()+ "|"+k.getRadiOd() +"|" +k.getRadiDo();
		writer.write(sb);
		writer.newLine();
		writer.close();
		
	}

	public ArrayList<Administrator> citajAdministratora(String imeFajla) throws IOException{
		ArrayList<Administrator> administartor = new ArrayList<Administrator>();
		File fajl = new File(imeFajla);
		BufferedReader citaj = new BufferedReader(new FileReader(fajl));
		String line = null;
		while((line = citaj.readLine())!= null) {
			String [] niz = line.split("\\|");
			String id = niz[4];
			String ime = niz[0];
			String prezime = niz[1];
			String jmbg = niz[2];
			String adresa = niz[3];
			String pol = niz[5];
			Pol defpol = Pol.MUSKO;
			for(Pol p:Pol.values()) {
				if(p.name().equalsIgnoreCase(pol)){
					defpol = p;
				}
			}
			String lozinka = niz[7];
			String korisnickoIme = niz[6];
			int plata = Integer.parseInt(niz[8]);
			Administrator admin = new Administrator(ime,prezime,jmbg,adresa,id,defpol,korisnickoIme,lozinka,plata);
			administartor.add(admin);
			}
		citaj.close();
		return administartor;	
	}
	public void upisiFajlAdministartor(Administrator a) throws IOException{
		File file = new File("src/fajlovi/administartor.txt");
		BufferedWriter writer = new BufferedWriter(new FileWriter(file,true));
		String sb = a.getId() +"|"+ a.getIme() + "|"+a.getPrezime()+ "|"+a.getJmbg() +"|"+ a.getAdresa()+ "|" +a.getPol() +"|"+a.getLozinka()+"|"+a.getKorisnickoIme();
		writer.write(sb);
		writer.newLine();
		writer.close();
	}
	public ArrayList<Bibliotekar> citajBibliotekara(String imeFajla) throws IOException{
		ArrayList<Bibliotekar> bibliotekar = new ArrayList<Bibliotekar>();
		File fajl = new File(imeFajla);
		BufferedReader citaj = new BufferedReader(new FileReader(fajl));
		String line = null;
		while((line = citaj.readLine())!= null) {
			String [] niz = line.split("\\|");
			String id = niz[4];
			String ime = niz[0];
			String prezime = niz[1];
			String jmbg = niz[2];
			String adresa = niz[3];
			String poll = niz[5];
			Pol pol = Pol.MUSKO;
			for(Pol p:Pol.values()) {
				if(p.name().equalsIgnoreCase(poll)){
					pol = p;
				}
			}
			String lozinka = niz[7];
			String korisnickoIme = niz[6];
			int plata = Integer.parseInt(niz[8]);
			Zaposleni bibl = new Bibliotekar(id,ime,prezime,jmbg,adresa,pol,lozinka,korisnickoIme, plata);
			bibliotekar.add((Bibliotekar) bibl);
			}
		citaj.close();
		return bibliotekar;	
	}
	public void upisiFajlBibliotekar(Bibliotekar b) throws IOException{
//		ArrayList<Knjiga> knjige = kjnigeUpis;
		File file = new File("src/fajlovi/bibliotekar.txt");
		BufferedWriter writer = new BufferedWriter(new FileWriter(file,true));
//		for (Knjiga k: knjige) {
			String sb = b.getId() +"|"+ b.getIme() + "|"+b.getPrezime()+ "|"+b.getJmbg() +"|"+ b.getAdresa()+ "|" +b.getPol() +"|"+b.getLozinka()+"|"+b.getKorisnickoIme();
			writer.write(sb);
			writer.newLine();
//		}
		writer.close();
	}
	
	public static ArrayList<Clan> citajClanove(String fajlClanovi)throws IOException{
		ArrayList<Clan> clanovi = new ArrayList<Clan>();
		File claoviFile = new File(fajlClanovi);
		BufferedReader citanje = new BufferedReader(new FileReader(claoviFile));
		String line1 = null;
		while((line1 = citanje.readLine())!= null) {
			String[]nizClanova = line1.split("\\|");
			String id = nizClanova[4];
			String ime = nizClanova[0];
			String prezime = nizClanova[1];
			String jmbg = nizClanova[2];
			String adresa = nizClanova[3];
			String polClana = nizClanova[5];
			Pol defpol = Pol.MUSKO;
			for(Pol pol:Pol.values()) {
				if(pol.name().equalsIgnoreCase(polClana)) {
					defpol = pol;
				}
			}
			String brClanskeKarte  = nizClanova[6];
			LocalDate datumUplate = LocalDate.parse(nizClanova[7]);
			int uplacenoMeseci = Integer.parseInt(nizClanova[8]);
			boolean aktivan = Boolean.parseBoolean(nizClanova[9]);
			ArrayList<TipClanarine> tip = citajClanarine("src/fajlovi/tipclanarine.txt");
			TipClanarine tipClanarinel = null;
			for (TipClanarine t : tip) {
				if(t.getId().equals(nizClanova[10])) {
					tipClanarinel = t;
				}
			}
			Clan clan = new Clan(ime, prezime, jmbg, adresa, id, defpol, brClanskeKarte, datumUplate, uplacenoMeseci, aktivan, tipClanarinel);
			clanovi.add(clan);
		}
		citanje.close();
		return clanovi;
	}
	public void upisiFajlClanBiblioteke(Clan c) throws IOException{
//	ArrayList<Knjiga> knjige = kjnigeUpis;
	File file = new File("src/fajlovi/clanbiblioteke.txt");
	BufferedWriter writer = new BufferedWriter(new FileWriter(file,true));
//	for (Knjiga k: knjige) {
		String sb = c.getId() +"|"+ c.getIme() + "|"+c.getPrezime()+ "|"+c.getJmbg() +"|"+ c.getAdresa()+ "|" +c.getPol() +"|"+c.getBrClanskeKarte()+"|"+c.getDatumUplate()+"|"+c.getUplacenoMeseci()+ "|"+c.isAktivan()+"|"+c.getTipClanarine();
		writer.write(sb);
		writer.newLine();
//	}
	writer.close();
}
	
	
	public ArrayList<Iznajmljivanje> citajIznajmljivanje(String imeFajla) throws IOException{
		ArrayList<Iznajmljivanje> izdknjige = new ArrayList<Iznajmljivanje>();
		File fajl = new File(imeFajla);
		BufferedReader citaj = new BufferedReader(new FileReader(fajl));
		String line = null;
		while((line = citaj.readLine())!= null) {
			String [] niz = line.split("\\|");
			LocalDate datumIznajmljivanja = LocalDate.parse(niz[0]);
			LocalDate datumVracanja = LocalDate.parse(niz[1]);
			ArrayList<Clan> clanovi = citajClanove("src/fajlovi/clanbiblioteke.txt");
			Clan clan = null;
			for (Clan t : clanovi) {
				if(t.getId().equals(niz[3])) {
					clan = t;
				}
			}
			ArrayList<Bibliotekar> bibliotekari = citajBibliotekara("src/fajlovi/bibliotekar.txt");
			Zaposleni zaposleni = null;
			for (Bibliotekar t : bibliotekari) {
				if(t.getId().equals(niz[2])) {
					zaposleni = t;
				}
			}
			if(zaposleni == null) {
				ArrayList<Administrator> administartor = citajAdministratora("src/fajlovi/administrator.txt");
				for (Administrator t : administartor) {
					if(t.getId().equals(niz[3])) {
						zaposleni = t;
					}
				}
			}
			ArrayList<PrimerakKnjige> primerciKnjige = citajPrimerke("src/fajlovi/primerakKnjige.txt");
			PrimerakKnjige primerak = null;
			for (PrimerakKnjige t : primerciKnjige) {
				if(t.getId().equals(niz[4])) {
					primerak = t;
				}
			}
			Iznajmljivanje izdavanje = new Iznajmljivanje(datumIznajmljivanja,datumVracanja,zaposleni,primerak,clan);
			izdknjige.add(izdavanje);
		}
		citaj.close();
		return izdknjige;
		
	}
	
	public void upisiIznajmljivanje(Iznajmljivanje t) throws IOException{
//		ArrayList<TipClanarine> tip = tipUpis;
		File file = new File("src/fajlovi/Iznajmljivanje.txt");
		BufferedWriter writer = new BufferedWriter(new FileWriter(file,true));
//		for(TipClanarine t:tip) {
			String sb = t.getDatumIznajmljivanja()+ "|"+ t.getDatumVracanja()+ "|"+t.getZaposleni()+ "|"+ t.getClan()+ "|"+ t.getPrimerak();
			writer.write(sb);
			writer.newLine();;
// 		}
		writer.close();
	}
	
	
	
	public ArrayList<PrimerakKnjige> citajPrimerke(String imeFajla) throws IOException{
		ArrayList<PrimerakKnjige> primerakKnjige = new ArrayList<PrimerakKnjige>();
		File fajl = new File(imeFajla);
		BufferedReader citaj = new BufferedReader(new FileReader(fajl));
		String line = null;
		while((line = citaj.readLine())!= null) {
			String [] niz = line.split(",");
			String id  = niz[0];
			int brStrana = Integer.parseInt(niz[1]);
			boolean tipPoveza= Boolean.parseBoolean(niz[6]);
			int godinaStampanja = Integer.parseInt(niz[2]);
			boolean izdata = Boolean.parseBoolean(niz[4]);
			ArrayList<Knjiga> listaKnjiga = citajKnjige("src/fajlovi/knjige.txt");
			Knjiga knjiga = null;
			for (Knjiga k: listaKnjiga) {
				if(k.getId().equals(niz[5])) {
					knjiga = k;
				}
			}
			String jezikStampanja = niz[3];
			PrimerakKnjige primerak = new PrimerakKnjige(id, brStrana, godinaStampanja, jezikStampanja, izdata, knjiga, tipPoveza);
			primerakKnjige.add(primerak);	
		}
		citaj.close();
		return primerakKnjige;
	}
	public void upisiPrimerakKnjige(PrimerakKnjige p) throws IOException{
//		ArrayList<PrimerakKnjige> primerak = tipUpis;
		File file = new File("src/fajlovi/primerakKnjige.txt");
		BufferedWriter writer = new BufferedWriter(new FileWriter(file,true));
//		for(PrimerakKnjige p: primerak) {
			String pr = p.getId()+ "|"+ p.getBrStrana()+"|"+p.isTipPoveza()+"|"+p.getGodinaStampanja()+"|"+p.isIzdata()+"|"+p.getKnjiga();
			writer.write(pr);
			writer.newLine();
// 		}
		writer.close();
	}
	
}