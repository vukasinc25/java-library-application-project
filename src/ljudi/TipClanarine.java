package ljudi;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class TipClanarine {

	protected String id;
    protected String tip;
    protected int cena;
    protected boolean obrisan;

    public TipClanarine() {
    	this.id = "";
    	this.tip = "";
    	this.cena = 0;
    	this.obrisan = false;
    }
    
    public TipClanarine(String id, String tip, int cena, boolean obrisan) {
    	this.id = id;
    	this.tip = tip;
    	this.cena = cena;
    	this.obrisan = obrisan;
    }

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTip() {
		return tip;
	}
	public void setTip(String tip) {
		this.tip = tip;
	}
	public int getCena() {
		return cena;
	}
	public void setCena(int cena) {
		this.cena = cena;
	}
	
	public boolean isObrisan() {
		return obrisan;
	}
	public void setObrisan(boolean obrisan) {
		this.obrisan = obrisan;
	}

	@Override
	public String toString(){
		return id + "|" + tip + "|" + cena;
	}
    
//	public static ArrayList<TipClanarine>  citajClanarine(String fajlClanovi) throws IOException{
//		ArrayList<TipClanarine> tipClanarine = new ArrayList<TipClanarine>();
//		File file = new File(fajlClanovi);
//		BufferedReader citanje = new BufferedReader(new FileReader(file));
//		String line1 = null;
//		while((line1 = citanje.readLine())!= null) {
//			String[]nizClanova = line1.split("|");
//			String id = nizClanova[0];
//			String tip = nizClanova[1];
//			int cena = Integer.parseInt(nizClanova[2]);
//			TipClanarine tip2  = new TipClanarine(id, tip, cena);
//			tipClanarine.add(tip2);
//		}
//		citanje.close();
//		return tipClanarine;
//		
//	}
//	public static void upisiFajl(ArrayList<TipClanarine> tipUpis, String imeFajla) throws IOException{
//		ArrayList<TipClanarine> tip = tipUpis;
//		File file = new File(imeFajla);
//		BufferedWriter writer = new BufferedWriter(new FileWriter(file,true));
//		for(TipClanarine t:tip) {
//			String sb = t.getTip()+ "|"+ t.getId()+ "|"+t.getCena();
//			writer.write(sb);
//			writer.newLine();;
// 		}
//		writer.close();
//		
//	}
}