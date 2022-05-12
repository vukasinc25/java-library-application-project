package biblioteka;

//import java.io.BufferedReader;
//import java.io.BufferedWriter;
//import java.io.File;
//import java.io.FileReader;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.util.ArrayList;

public class PrimerakKnjige {

    protected String id;
    protected int brStrana;
    protected int godinaStampanja;
    protected String jezikStampanja;
    protected boolean izdata;
    protected Knjiga knjiga;
    protected boolean tipPoveza;
    
    public PrimerakKnjige(String id, int brStrana, int godinaStampanja, 
    		String jezikStampanja, boolean izdata, Knjiga knjiga, boolean tipPoveza) {
    	
    	this.id = id;
    	this.brStrana = brStrana;
    	this.godinaStampanja = godinaStampanja;
    	this.jezikStampanja = jezikStampanja;
    	this.izdata = izdata;
    	this.tipPoveza = tipPoveza;
    }
    
    public PrimerakKnjige() {
 
    	this.id = "";
    	this.brStrana = 0;
    	this.godinaStampanja = 0;
    	this.jezikStampanja = "";
    	this.izdata = false;
    	this.tipPoveza = false;
    }
    
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getBrStrana() {
		return brStrana;
	}

	public void setBrStrana(int brStrana) {
		this.brStrana = brStrana;
	}

	public int getGodinaStampanja() {
		return godinaStampanja;
	}

	public void setGodinaStampanja(int godinaStampanja) {
		this.godinaStampanja = godinaStampanja;
	}

	public String getJezikStampanja() {
		return jezikStampanja;
	}

	public void setJezikStampanja(String jezikStampanja) {
		this.jezikStampanja = jezikStampanja;
	}

	public boolean isIzdata() {
		return izdata;
	}

	public void setIzdata(boolean izdata) {
		this.izdata = izdata;
	}

	public Knjiga getKnjiga() {
		return knjiga;
	}

	public void setKnjiga(Knjiga knjiga) {
		this.knjiga = knjiga;
	}

	public boolean isTipPoveza() {
		return tipPoveza;
	}

	public void setTipPoveza(boolean tipPoveza) {
		this.tipPoveza = tipPoveza;
	}
    
    @Override
    public String toString() {
    	return knjiga + "|" + id + "|" + brStrana + "|" + tipPoveza + "|" + 
    			godinaStampanja + "|" + izdata + "|" + jezikStampanja;
    }
    
//    public ArrayList<PrimerakKnjige> ucitajPrimerak(String imeFajla) throws IOException{
//		ArrayList<PrimerakKnjige> knjige = new ArrayList<PrimerakKnjige>();
//		File fajl = new File(imeFajla);
//		BufferedReader citaj = new BufferedReader(new FileReader(fajl));
//		String line = null;
//		while((line = citaj.readLine())!= null) {
//			String [] niz = line.split("|");
//			int id  = Integer.parseInt(niz[0]);
//			int brStrana = Integer.parseInt(niz[1]);
//			int godinaStampanja = Integer.parseInt(niz[3]);
//			boolean jeliIznajmljena = Boolean.parseBoolean(niz[4]);
//			Knjiga knjiga = new Knjiga(opisKnjige,niz[5]);
//			boolean tipPoveza= Boolean.parseBoolean(niz[2]);
//			PrimerakKnjige primerak = new PrimerakKnjige(id, brStrana, godinaStampanja,jezikStampanja,izdata,knjiga,tipPoveza);
//			knjige.add(primerak);	
//		}
//		citaj.close();
//		return null;
//    
//	}
//	public static void upisiPrimerak(ArrayList<PrimerakKnjige> tipUpis, String imeFajla) throws IOException{
//		ArrayList<PrimerakKnjige> primerak = tipUpis;
//		File file = new File(imeFajla);
//		BufferedWriter writer = new BufferedWriter(new FileWriter(file,true));
//		for(PrimerakKnjige p: primerak) {
//			String pr = p.getBrStrana()+ "|"+ p.getGodinaStampanja()+"|"+p.getId()+"|"+p.getKnjiga();
//			writer.write(pr);
//			writer.newLine();;
// 		}
//		writer.close();
//	
//	}


}