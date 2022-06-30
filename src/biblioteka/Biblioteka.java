package biblioteka;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import enumeracije.Jezik;
import enumeracije.Korice;
import enumeracije.Pol;
import ljudi.Admin;
import ljudi.Bibliotekar;
import ljudi.Clan;
import ljudi.TipClanarine;
import ljudi.Zaposleni;

public class Biblioteka {
	protected String naziv;
	protected String adresa;
	protected String telefon;
	protected String id;
	protected String radnoVremeOd;
	protected String radnoVremeDo;
	private ArrayList<Admin> administratori;
	private ArrayList<Bibliotekar> bibliotekari;
	private ArrayList<Clan> clanovi;
	private ArrayList<Knjiga> knjige;
	private ArrayList<Primerak> primerci;
	private ArrayList<TipClanarine> tipovi;
	private ArrayList<Zanr> zanrovi;
	private ArrayList<Iznajmljivanje> iznajmljivanja;
	
	public Biblioteka() {
		this.naziv="";
		this.adresa="";
		this.telefon="";
		this.id="";
		this.radnoVremeDo="";
		this.radnoVremeOd="";
		this.administratori = new ArrayList<Admin>();
		this.knjige = new ArrayList<Knjiga>();
		this.clanovi = new ArrayList<Clan>();
		this.bibliotekari = new ArrayList<Bibliotekar>();
		this.primerci = new ArrayList<Primerak>();
		this.tipovi = new ArrayList<TipClanarine>();
		this.zanrovi = new ArrayList<Zanr>();
		this.iznajmljivanja = new ArrayList<Iznajmljivanje>();
	}

	public Biblioteka(String naziv, String adresa, String telefon, String id, String radnoVremeOd,
			String radnoVremeDo) {
		super();
		this.naziv = naziv;
		this.adresa = adresa;
		this.telefon = telefon;
		this.id = id;
		this.radnoVremeOd = radnoVremeOd;
		this.radnoVremeDo = radnoVremeDo;
		this.administratori = new ArrayList<Admin>();
		this.knjige = new ArrayList<Knjiga>();
		this.clanovi = new ArrayList<Clan>();
		this.bibliotekari = new ArrayList<Bibliotekar>();
		this.primerci = new ArrayList<Primerak>();
		this.tipovi = new ArrayList<TipClanarine>();
		this.zanrovi = new ArrayList<Zanr>();
		this.iznajmljivanja = new ArrayList<Iznajmljivanje>();

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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRadnoVremeOd() {
		return radnoVremeOd;
	}

	public void setRadnoVremeOd(String radnoVremeOd) {
		this.radnoVremeOd = radnoVremeOd;
	}

	public String getRadnoVremeDo() {
		return radnoVremeDo;
	}

	public void setRadnoVremeDo(String radnoVremeDo) {
		this.radnoVremeDo = radnoVremeDo;
	}
	

	@Override
	public String toString() {
		return "\nNaziv: " + naziv
			 + "\nAdresa: " + adresa
			 + "\nTelefon: " + telefon
			 + "\nId: " + id
			 +"\nRadno vreme od: " + radnoVremeOd
			 +" do: " + radnoVremeDo;
	}

	public ArrayList<Admin> getAdministratori() {
		return administratori;
	}

	public void setAdministratori(ArrayList<Admin> administratori) {
		this.administratori = administratori;
	}

	public ArrayList<Bibliotekar> getBibliotekari() {
		return bibliotekari;
	}

	public void setBibliotekari(ArrayList<Bibliotekar> bibliotekari) {
		this.bibliotekari = bibliotekari;
	}

	public ArrayList<Clan> getClanovi() {
		return clanovi;
	}

	public void setClanovi(ArrayList<Clan> clanovi) {
		this.clanovi = clanovi;
	}

	public ArrayList<Knjiga> getKnjige() {
		return knjige;
	}

	public void setKnjige(ArrayList<Knjiga> knjige) {
		this.knjige = knjige;
	}

	public ArrayList<Primerak> getPrimerci() {
		return primerci;
	}

	public void setPrimerci(ArrayList<Primerak> primerci) {
		this.primerci = primerci;
	}

	public ArrayList<TipClanarine> getTipovi() {
		return tipovi;
	}

	public void setTipovi(ArrayList<TipClanarine> tipovi) {
		this.tipovi = tipovi;
	}

	public ArrayList<Zanr> getZanrovi() {
		return zanrovi;
	}

	public void setZanrovi(ArrayList<Zanr> zanrovi) {
		this.zanrovi = zanrovi;
	}

	public ArrayList<Iznajmljivanje> getIznajmljivanja() {
		return iznajmljivanja;
	}

	public void setIznajmljivanja(ArrayList<Iznajmljivanje> iznajmljivanja) {
		this.iznajmljivanja = iznajmljivanja;
	}


	
	public void upisClanova(Clan cl) throws IOException{

		File file=new File("src/txt/clanovi.txt");
		BufferedWriter writer = new BufferedWriter(new FileWriter(file,true));
		String linija=cl.getBrojClanskeKarte() + "|" +cl.getDatPoslednjeUplate() + "|" +cl.getBrMeseci() + "|" +
		cl.getTip().getId()+ "|"+ cl.getAktivnost()+ "|" +cl.getIme() + "|" + cl.getPrezime() + "|" + cl.getJMBG() + "|" + cl.getAdresa() + "|" + cl.getPol() + "|"
		+cl.isObrisan() ;
		writer.write(linija);
		writer.newLine();
		writer.close();
	}
		
	public void obrisiClana(Clan cl) {
		this.clanovi.remove(cl);
	}
	
	public ArrayList<Clan> sviNeobrisaniClanovi() {
		ArrayList<Clan> neobrisani = new ArrayList<Clan>();
		for(Clan clan : clanovi) {
			if(!clan.isObrisan()) {
				neobrisani.add(clan);
			}
		}
		return neobrisani;
	}
	
	public Clan pronadjiClana(String id) {
		for (Clan clan : clanovi) {
			if(clan.getBrojClanskeKarte().equals(id)) {
				return clan;
			}
		}
		return null;
	}
	public Clan pronadjiClanaPoKorisnickomImenu(String ime) {
		for (Clan clan : clanovi) {
			if(clan.getIme().equals(ime)) {
				return clan;
			}
		}
		return null;
	}
	
	public void upisBibliotekara(Bibliotekar b) throws IOException{
		File file=new File("src/txt/bibliotekar.txt");
		BufferedWriter writer = new BufferedWriter(new FileWriter(file,true));
		String linija=b.getId() + "|" +b.getIme() + "|" + b.getPrezime() + "|" +b.getAdresa() + "|" +  b.getJMBG() + "|" +
		b.getPlata() +"|" +b.getKorisnickoIme() + "|" + b.getLozinka() + "|" +b.getPol() + "|" + 
		b.isObrisan();
		writer.write(linija);
		writer.newLine();
		writer.close();
	}
	
	public void obrisiBibliotekara(Bibliotekar b) {
		this.bibliotekari.remove(b);
	}
	
	public ArrayList<Bibliotekar> sviNeobrisaniBibliotekari() {
		ArrayList<Bibliotekar> neobrisani = new ArrayList<Bibliotekar>();
		for(Bibliotekar bibliotekar: bibliotekari) {
			if(!bibliotekar.isObrisan()) {
				neobrisani.add(bibliotekar);
			}
		}
		return neobrisani;
	}
	
	public Bibliotekar pronadjiBibliotekara(String id) {
		for (Bibliotekar bibliotekar : bibliotekari) {
			if(bibliotekar.getId().equals(id)) {
				return bibliotekar;
			}
		}
		return null;
	}
	public Bibliotekar pronadjiBibliotekaraPoKorisnickomImenu(String korisnickoIme) {
		for (Bibliotekar bibliotekar : bibliotekari) {
			if(bibliotekar.getKorisnickoIme().equals(korisnickoIme)) {
				return bibliotekar;
			}
		}
		return null;
	}
	public void upisAdministratora(Admin a) throws IOException{

		File file=new File("src/txt/administrator.txt");
		BufferedWriter writer = new BufferedWriter(new FileWriter(file,true));
		String linija= a.getId() + "|" +a.getIme() + "|" + a.getPrezime() + "|" +a.getAdresa() + "|"+ a.getJMBG() + "|"  +
		 a.getPlata() + "|" +a.getKorisnickoIme() + "|" + a.getLozinka() + "|" +a.getPol() +   "|"  +
		a.isObrisan();
		writer.write(linija);
		writer.newLine();
		writer.close();
	}
	
	public void obrisiAdmna(Admin a) {
		this.administratori.remove(a);
	}

	public ArrayList<Admin> sviNeobrisaniAdmini() {
		ArrayList<Admin> neobrisani = new ArrayList<Admin>();
		for(Admin admin : administratori) {
			if(!admin.isObrisan()) {
				neobrisani.add(admin);
			}
		}
		return neobrisani;
	}
	
	public Admin pronadjiAdmina(String id) {
		for (Admin admin : administratori) {
			if(admin.getId().equals(id)) {
				return admin;
			}
		}
		return null;
	}
	public Admin pronadjiAminaPoKorisnickomImenu(String korisnickoIme) {
		for(Admin admin:administratori) {
			if(admin.getKorisnickoIme().equals(korisnickoIme)) {
				return admin;
			}
			
		}
		return null;
	}

	public void upisPrimerka(Primerak p) throws IOException{
		File file=new File("src/txt/primerak.txt");
		BufferedWriter writer = new BufferedWriter(new FileWriter(file,true));
		String linija=p.getId() + "|" + p.getKnjiga().getId() + "|" +p.getBrStrana() + "|" +  p.getGodStampanja() + "|" +
		p.getKorica()+"|"+  p.getJezikk() + "|" + p.getIznajmljena() + "|" + p.getObrisan();
		writer.write(linija);
		writer.newLine();
		writer.close();
	}
	
	public void obrisiPrimerak(Primerak p) {
		this.primerci.remove(p);
	}
	
	public ArrayList<Primerak> sviNeobrisaniPrimerci() {
		ArrayList<Primerak> neobrisani = new ArrayList<Primerak>();
		for(Primerak primerak: primerci) {
			if(!primerak.getObrisan()) {
				neobrisani.add(primerak);
			}
		}
		return neobrisani;
	}
	
	public Primerak pronadjiPrimerak(String id) {
		for (Primerak primerak :primerci) {
			if(primerak.getId().equals(id)) {
				return primerak;
			}
		}
		return null;
	}
	public void upisKnjige(Knjiga t) throws IOException{
		File file=new File("src/txt/knjige.txt");
		BufferedWriter writer = new BufferedWriter(new FileWriter(file,true));
		String linija=t.getId() + "|" +t.getNaslov() + "|" +t.getOriginal()+ "|" +t.getPisac() + "|" + t.getGodinaObjavljivanja() + "|" +
		  t.getOpis() + "|" + t.getJezikk() + "|" + t.getZanr().getId() + "|" + t.isObrisan();
		writer.write(linija);
		writer.newLine();
		writer.close();
		
		
	}
	
	public void obrisiKnjgu(Knjiga t) {
		this.knjige.remove(t);
	}
	
	public ArrayList<Knjiga> sveNeobrisaneKnjige() {
		ArrayList<Knjiga> neobrisane = new ArrayList<Knjiga>();
		for(Knjiga knjiga : knjige) {
			if(!knjiga.isObrisan()) {
				neobrisane.add(knjiga);
			}
		}
		return neobrisane;
	}
	
	public Knjiga pronadjiKnjigu(String id) {
		for (Knjiga knjiga : knjige) {
			if(knjiga.getId().equals(id)) {
				return knjiga;
			}
		}
		return null;
	}

	public void upisZanra(Zanr zanr) throws IOException{
		File file=new File("src/txt/zanr.txt");
		BufferedWriter writer = new BufferedWriter(new FileWriter(file,true));
		String linija= zanr.getId()+"|" +zanr.getOpis()+ "|" + zanr.getOznaka() + "|"+ zanr.getObrisan();
		writer.write(linija);
		writer.newLine();
		writer.close();
	
		
	}

	public void obrisiZanr(Zanr zanr) {
		this.zanrovi.remove(zanr);
	}
	
	public Zanr pronadjiZanr(String id) {
		for (Zanr zanr : zanrovi) {
			if(zanr.getId().equals(id)) {
				return zanr;
			}
		}
		return null;
	}
	
	public ArrayList<Zanr> sviNeobrisaniZanrovi() {
		ArrayList<Zanr> neobrisani = new ArrayList<Zanr>();
		for(Zanr zanr : zanrovi) {
			if(!zanr.getObrisan()) {
				neobrisani.add(zanr);
			}
		}
		return neobrisani;
	}
	public void upisTipa(TipClanarine c) throws IOException{
		File file=new File("src/txt/tip.txt");
		BufferedWriter writer = new BufferedWriter(new FileWriter(file,true));
		String linija=c.getId()+ "|" + c.getOpis() + "|" + c.getCena() + "|" + c.getObrisan();
		writer.write(linija);
		writer.newLine();
		writer.close();
	}

	public void obrisiTipClanarine(TipClanarine c) {
		this.tipovi.remove(c);
	}
	
	public TipClanarine pronadjiTip(String id) {
		for (TipClanarine tip : tipovi) {
			if(tip.getId().equals(id)) {
				return tip;
			}
		}
		return null;
	}
	
	
	public ArrayList<TipClanarine> sviNeobrisaniTipovi() {
		ArrayList<TipClanarine> neobrisani = new ArrayList<TipClanarine>();
		for(TipClanarine tip: tipovi) {
			if(!tip.getObrisan()) {
				neobrisani.add(tip);
			}
		}
		return neobrisani;
	}

	public void upisiIznajmljivanje(Iznajmljivanje i) throws IOException{
		File file=new File("src/txt/iznajmljivanje.txt");
		BufferedWriter writer = new BufferedWriter(new FileWriter(file,true));
		String linija= i.getId() + "|" + i.getPrimerak().getId() + "|" + i.getClan().getIme() + "|" + i.getDatumIznajmljivanja()+ "|" + 
		i.getDatumVracanja() + "|" + i.getZaposleni().getKorisnickoIme() + "|" + i.getObrisan();
		writer.write(linija);
		writer.newLine();
		writer.close();
	}
	
	public void obrisiIznajmljivanje(Iznajmljivanje i) {
		this.iznajmljivanja.remove(i);
	}
	
	public ArrayList<Iznajmljivanje> svaNeobrisanaIznajmljivanja() {
		ArrayList<Iznajmljivanje> neobrisani = new ArrayList<Iznajmljivanje>();
		for(Iznajmljivanje iznajmljivanje : iznajmljivanja) {
			if(!iznajmljivanje.getObrisan()) {
				neobrisani.add(iznajmljivanje);
			}
		}
		return neobrisani;
	}
	
	public Iznajmljivanje pronadjiIznajmljivanje(String id) {
		for (Iznajmljivanje iznajmljivanje : iznajmljivanja) {
			if(iznajmljivanje.getId().equals(id)) {
				return iznajmljivanje;
			}
		}
		return null;
	}
	
	

	
	public Zaposleni login(String korisnickoIme, String lozinka) {
		
		for(Admin admin : administratori) {
			if(admin.getKorisnickoIme().equalsIgnoreCase(korisnickoIme) &&
					admin.getLozinka().equals(lozinka) && !admin.isObrisan()) {
				return admin;
			}
		
		}
		for (Bibliotekar bibliotekar:bibliotekari) {
			if(bibliotekar.getKorisnickoIme().equalsIgnoreCase(korisnickoIme)&& bibliotekar.getLozinka().equals(lozinka) && 
					!bibliotekar.isObrisan()) {
				return bibliotekar;
			}
		}
		return null;
	}
	
	
	public void citajKnjige(String imeFajla) throws IOException{
			File fajl = new File(imeFajla);
			BufferedReader citaj = new BufferedReader(new FileReader(fajl));
			String line = null;
			while((line = citaj.readLine())!= null) {
				String [] niz = line.split("\\|");
				String id  = niz[0];
				String naslov = niz[1];
				String originalsniNaslovKnjige = niz[2];
				String pisac = niz[3];
				String godinaObjavljanjaKnjige =niz[4];
				String opisKnjige = niz[5];
				String jezikOroginala = niz[6];
				Jezik jezikOriginala = Jezik.SRPSKI;
				for (Jezik j: Jezik.values()) {
					if(j.name().equalsIgnoreCase(jezikOroginala)){
						jezikOriginala = j;
					}
				}
				Zanr zanr = null;
				for (Zanr z : zanrovi) {
					if(z.getId().equals(niz[7])) {
						zanr = z;
					}
				}
				boolean obrisan = Boolean.parseBoolean(niz[8]);

				Knjiga knjiga = new Knjiga(id, naslov, originalsniNaslovKnjige, pisac, godinaObjavljanjaKnjige, opisKnjige, jezikOriginala, zanr, obrisan);
				knjige.add(knjiga);
			}
			citaj.close();
		}
	

	public void citajZanroveIzFajla(String string) throws IOException{
		File fajl = new File(string);
		BufferedReader citaj = new BufferedReader(new FileReader(fajl));
		String line = null;
		while((line = citaj.readLine())!= null) {
			String [] niz = line.split("\\|");
			String id = niz[0];
			String opis = niz[1];
			String oznaka = niz[2];
			boolean obrisan = Boolean.parseBoolean(niz[3]);
			
			Zanr zanr = new Zanr(id,opis,oznaka,obrisan);
			zanrovi.add(zanr);
		}
		citaj.close();
	}
	
	public void citajAdministratora(String imeFajla) throws IOException{
		File fajl = new File(imeFajla);
		BufferedReader citaj = new BufferedReader(new FileReader(fajl));
		String line = null;
		while((line = citaj.readLine())!= null) {
			String [] niz = line.split("\\|");
			String id = niz[0];
			String ime = niz[1];
			String prezime = niz[2];
			String adresa = niz[3];
			String JMBG = niz[4];
			String plata=niz[5];	
			String korisnickoIme = niz[6];
			String korisnickaSifra = niz[7];
			String polString = niz[8];
			Pol pol = Pol.MUSKI;
			if(polString.equals(Pol.ZENSKI)) {
				pol=Pol.ZENSKI;
			}
			boolean obrisan = Boolean.parseBoolean(niz[9]);

			
		
			Admin admin = new Admin(id,ime,prezime,adresa,JMBG,plata,korisnickoIme,korisnickaSifra,pol,obrisan);
			administratori.add(admin);
			}
		citaj.close();	
	}

	public void citajClanove(String fajlClanovi)throws IOException{
		File claoviFile = new File(fajlClanovi);
		BufferedReader citanje = new BufferedReader(new FileReader(claoviFile));
		String line1 = null;
		
		while((line1 = citanje.readLine())!= null) {
			String[]nizClanova = line1.split("\\|");
			String brClankarte  = nizClanova[0];
			String datumPoslednjeUplateString =nizClanova[1];
			LocalDate datum=LocalDate.parse(datumPoslednjeUplateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			String brojMeseciClanarine = nizClanova[2];
			TipClanarine tip1 = null;
			for (TipClanarine t : tipovi) {
				if(t.getId().equals(nizClanova[3])) {
					tip1 = t;
				}
			}
			boolean aktivan = Boolean.parseBoolean(nizClanova[4]);
			String ime = nizClanova[5];
			String prezime = nizClanova[6];
			String JMBG = nizClanova[7];
			String adresa = nizClanova[8];
			String polClana = nizClanova[9];
			Pol pol =Pol.MUSKI;
			if(polClana.equals(Pol.ZENSKI)) {
				pol=Pol.ZENSKI;
			}
			boolean obrisan = Boolean.parseBoolean(nizClanova[10]);

			
			Clan clan = new Clan(brClankarte,datum, brojMeseciClanarine,tip1,aktivan,ime,prezime,JMBG,
					adresa,pol,obrisan);
			clanovi.add(clan);
		}
		citanje.close();
		
	}
	public void citajClanarine(String fajlClanovi) throws IOException{
		File file = new File(fajlClanovi);
		BufferedReader citanje = new BufferedReader(new FileReader(file));
		String line1 = null;
		while((line1 = citanje.readLine())!= null) {
			String[]nizClanova = line1.split("\\|");
			String id = nizClanova[0];
			String naziv = nizClanova[1];
			String cena = nizClanova[2];
			boolean obrisan = Boolean.parseBoolean(nizClanova[3]);
			TipClanarine tip = new TipClanarine(id,naziv,cena,obrisan);
			tipovi.add(tip);
		}
		citanje.close();
		
	
	
		
	}
	
	public void citajBiblioteku(String imeFajla) throws IOException{
		File fajl = new File(imeFajla);
		BufferedReader citaj = new BufferedReader(new FileReader(fajl));
		String line = null;
		while((line = citaj.readLine())!= null) {
			String [] niz = line.split("\\|");
			String idString = niz[0];
			String nazivString = niz[1];
			String adresaString = niz[2];
			String telefonString = niz[3];
			String radnoVrOdString = niz[4];
			String radnoVrDoString=niz[5];
			this.id=idString;
			this.naziv=nazivString;
			this.adresa=adresaString;
			this.telefon=telefonString;
			this.radnoVremeOd=radnoVrOdString;
			this.radnoVremeDo=radnoVrDoString;
			}
		citaj.close();
		
	}
	public void sacuvajBilioteku() throws IOException {
		File file=new File(Main.lokacijaBiblioteka);
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			String linija= this.getId()+ "|" +this.getNaziv() + "|" + this.getAdresa() + "|" + this.getTelefon() + "|" + this.getRadnoVremeOd() + "|" + this.getRadnoVremeDo();
			writer.write(linija);
			writer.newLine();
		

		writer.close();
	}
	public void citajBibliotekare(String imeFajla) throws IOException{
		File fajl = new File(imeFajla);
		BufferedReader citaj = new BufferedReader(new FileReader(fajl));
		String line = null;
		while((line = citaj.readLine())!= null) {
			String [] niz = line.split("\\|");
			String id = niz[0];
			String ime = niz[1];
			String prezime = niz[2];
			String adresa = niz[3];
			String JMBG = niz[4];
			String plata=niz[5];	
			String korisnickoIme = niz[6];
			String korisnickaSifra = niz[7];
			String polString = niz[8];
			Pol pol =Pol.MUSKI;
			if(polString.equals(Pol.ZENSKI)) {
				pol=Pol.ZENSKI;
			}
			boolean obrisan = Boolean.parseBoolean(niz[8]);
			Bibliotekar bibliotekar = new Bibliotekar(id,ime,prezime,adresa,JMBG,plata,korisnickoIme,korisnickaSifra,pol,obrisan);
			bibliotekari.add(bibliotekar);
			}
		citaj.close();
		
	}

	public void citajIzdavanjeKnjige(String imeFajla) throws IOException{
		File fajl = new File(imeFajla);
		BufferedReader citaj = new BufferedReader(new FileReader(fajl));
		String line = null;
		while((line = citaj.readLine())!= null) {
			String [] niz = line.split("\\|");
			String id = niz[0];
			Primerak primerKnjige = pronadjiPrimerak(niz[1]);
			Clan clan1=pronadjiClanaPoKorisnickomImenu(niz[2]);
			String datumIznajmljivanjaString = niz[3];
			LocalDate datumIznajmljivanja=LocalDate.parse(datumIznajmljivanjaString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			String datumVracanjaString = niz[4];
			LocalDate datumVracanja=LocalDate.parse(datumVracanjaString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			Zaposleni zaposleni=pronadjiAminaPoKorisnickomImenu(niz[5]);
			if(zaposleni==null) {
				zaposleni=pronadjiBibliotekaraPoKorisnickomImenu(niz[5]);
			}
			boolean obrisan = Boolean.parseBoolean(niz[6]);

			Iznajmljivanje izdavanje = new Iznajmljivanje(id,primerKnjige,clan1,datumIznajmljivanja,datumVracanja,
					zaposleni,obrisan);
			iznajmljivanja.add(izdavanje);
		}
		citaj.close();
		
		
	}
	public void citajPrimerke(String fajlPrimerci) throws IOException{
		File file= new File(fajlPrimerci);
		BufferedReader citanje= new BufferedReader(new FileReader(file));
		String line1=null;
		while((line1=citanje.readLine())!=null) {
			String [] nizPrimeraka=line1.split("\\|");
			String id = nizPrimeraka[0];
			String knjigaId = nizPrimeraka[1];
			String brStrana = nizPrimeraka[2];
			String godStampanja = nizPrimeraka[3];
			String koriceString = nizPrimeraka[4];
			Korice korice=Korice.MEKE;
			if(koriceString.equals(Korice.TVRDE)) {
				korice=Korice.TVRDE;
				
			}
			String jezikString = nizPrimeraka[5];
			Jezik jezikk=Jezik.SRPSKI;
			for(Jezik jezik:Jezik.values()) {
				if(jezikString.equals(jezik)) {
					jezikk=jezik;
				}
			}
			Knjiga knjiga=pronadjiKnjigu(knjigaId);
			
			boolean iznajmljena = Boolean.parseBoolean(nizPrimeraka[6]);
			boolean obrisan = Boolean.parseBoolean(nizPrimeraka[7]);
			
			Primerak primerak=new Primerak(id,knjiga,brStrana,godStampanja,korice,jezikk,iznajmljena,obrisan);
			primerci.add(primerak);
		}
		citanje.close();
	}

	public void sacuvajPrimerke() throws IOException {
		File file=new File(Main.lokacijaPrimerak);
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		for(Primerak p:primerci) {
			String linija=p.getId() + "|" + p.getKnjiga().getId() + "|" +p.getBrStrana() + "|" +  p.getGodStampanja() + "|" +
					p.getKorica()+"|"+  p.getJezikk() + "|" + p.getIznajmljena() + "|" + p.getObrisan();
			writer.write(linija);
			writer.newLine();
		}

		writer.close();
	}
	
	public void sacuvajKnjige() throws IOException{
		File file=new File(Main.lokacijaKnjige);
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		for(Knjiga t : knjige) {
			String linija=t.getId() + "|" +t.getNaslov() + "|" +t.getOriginal()+ "|" +t.getPisac() + "|" + t.getGodinaObjavljivanja() + "|" +
					  t.getOpis() + "|" + t.getJezikk() + "|" + t.getZanr().getId() + "|" + t.isObrisan();
			writer.write(linija);
			writer.newLine();
		}
		writer.close();
	}

	public void sacuvajClanove() throws IOException{
		File file=new File(Main.lokacijaClanovi);
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		for(Clan c : clanovi) {
			String linija=c.getBrojClanskeKarte() + "|" +c.getDatPoslednjeUplate() + "|" +c.getBrMeseci() + "|" +
					c.getTip().getId()+ "|"+ c.getAktivnost()+ "|" +c.getIme() + "|" + c.getPrezime() + "|" + c.getJMBG() + "|" + c.getAdresa() + "|" + c.getPol() + "|"
					+c.isObrisan() ;
			writer.write(linija);
			writer.newLine();
		}
		writer.close();
	}
	
	public void sacuvajZanrove() throws IOException{
		File file=new File(Main.lokacijaZanr);
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		for(Zanr z : zanrovi) {
			String linija= z.getId()+"|" +z.getOpis()+ "|" + z.getOznaka() +"|"+ z.getObrisan();
			writer.write(linija);
			writer.newLine();
		}
		writer.close();
	}
	
	public void sacuvajTipoveClanarine() throws IOException{
		File file=new File(Main.lokacijaTip);
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		for(TipClanarine c : tipovi) {
			String linija=c.getId()+ "|" + c.getOpis() + "|" + c.getCena() + "|" + c.getObrisan();
			writer.write(linija);
			writer.newLine();
		}
		writer.close();
	}
	
	public void sacuvajAdmine() throws IOException{
		File file=new File(Main.lokacijaAdmin);
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		for(Admin a : administratori) {
			String linija= a.getId() + "|" +a.getIme() + "|" + a.getPrezime() + "|" +a.getAdresa() + "|"+ a.getJMBG() + "|"  +
					 a.getPlata() + "|" +a.getKorisnickoIme() + "|" + a.getLozinka() + "|" +a.getPol() +   "|"  +
					a.isObrisan();
			writer.write(linija);
			writer.newLine();
		}
		writer.close();
	}
	
	public void sacuvajBibliotekare() throws IOException{
		File file=new File(Main.lokacijaBibliotekar);
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		for(Bibliotekar b : bibliotekari) {
			String linija= b.getId() + "|" +b.getIme() + "|" + b.getPrezime() + "|" +b.getAdresa() + "|"+ b.getJMBG() + "|"  +
					 b.getPlata() + "|" +b.getKorisnickoIme() + "|" + b.getLozinka() + "|" +b.getPol() +   "|"  +
					b.isObrisan();
			writer.write(linija);
			writer.newLine();
		}
		writer.close();
	}


	
public void sacuvajIznajmljivanje() throws IOException {
	File file=new File(Main.lokacijaIznajmljivanje);
	BufferedWriter writer = new BufferedWriter(new FileWriter(file));
	for(Iznajmljivanje i:iznajmljivanja) {
		String linija= i.getId() + "|" + i.getPrimerak().getId() + "|" + i.getClan().getIme() + "|" + i.getDatumIznajmljivanja()+ "|" + 
				i.getDatumVracanja() + "|" + i.getZaposleni().getKorisnickoIme() + "|" + i.getObrisan();
		writer.write(linija);
		writer.newLine();
	}

	writer.close();
}


public void proveriAktivnost() throws IOException{
	LocalDate trenutniDatum=LocalDate.now();
	for(Clan clan:clanovi) {
		LocalDate datum = clan.getDatPoslednjeUplate();
		String brMeseci=clan.getBrMeseci();
		int brojMeseci=Integer.parseInt(brMeseci);
		LocalDate noviDatum=datum.plusMonths(brojMeseci);
		boolean isAfter = trenutniDatum.isAfter(noviDatum);
		if(isAfter==true) {
			clan.setAktivnost(false);
		}
		else {
			clan.setAktivnost(true);	
		}
		;
	}
}
}

	


