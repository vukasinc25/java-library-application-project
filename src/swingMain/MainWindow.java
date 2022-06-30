package swingMain;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextPane;

import biblioteka.Biblioteka;
import ljudi.Zaposleni;
import net.miginfocom.swing.MigLayout;
import swingPrikaz.AdminPrikaz;
import swingPrikaz.BibliotekaPrikaz;
import swingPrikaz.BibliotekarPrikaz;
import swingPrikaz.ClanPrikaz;
import swingPrikaz.IznajmljivanjePrikaz;
import swingPrikaz.KnjigePrikaz;
import swingPrikaz.PrimerakKnjigePrikaz;
import swingPrikaz.TipClanarinePrikaz;
import swingPrikaz.ZanrKnjigePrikaz;

//import osobe.Prodavac;
//import prodavnica.Prodavnica;

public class MainWindow extends JFrame{
	
	private JMenuBar mainMenu = new JMenuBar();
	private JMenu knjigeMenu = new JMenu("Knjige");
	private JMenu ljudiMenu = new JMenu("Clanovi");
	private JMenu bibliotekaMenu = new JMenu("Biblitoeka");
	private JMenuItem clanItem = new JMenuItem("Clan Biblioteke");
	private JMenuItem knjigeItem = new JMenuItem("Sve Knjige");
	private JMenuItem primerciItem = new JMenuItem("Primerci");
	private JMenuItem zanrKnjigeItem = new JMenuItem("Zanrovi");
	private JMenuItem tipClanarinejeItem = new JMenuItem("Tip Clanarine");
	private JMenuItem iznajmljivanjeKnjigeItem = new JMenuItem("Iznajmljivanje");
	private JMenu zaposleniMenu = new JMenu("Zaposleni");
	private JMenuItem bibliotekarItem = new JMenuItem("Bibliotekari");
	private JMenuItem administratorItem = new JMenuItem("Administartori");
	private JMenuItem bibliotekaItem = new JMenuItem("Biblioteka");
	private Biblioteka biblioteka;
	private Zaposleni prijavljeniKorisnik;
	private boolean isAdmin;
	private final JTextPane txtpnSada = new JTextPane();
	ImageIcon ikonica = new ImageIcon("src/slike/knjiga.png");
	private final JLabel label = new JLabel("");
	
	public MainWindow(Biblioteka biblioteka, Zaposleni prijavljeniKorisnik,boolean isAdmin) {
		setBackground(Color.WHITE);
		getContentPane().setBackground(Color.LIGHT_GRAY);
		this.biblioteka = biblioteka;
		this.prijavljeniKorisnik = prijavljeniKorisnik;
		this.isAdmin = isAdmin;
		setTitle("Biblioteka");
		setSize(800, 600);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		initMenu();
		initActions();
	}
	
	private void initMenu() {
		setIconImage(ikonica.getImage());
		mainMenu.setForeground(Color.WHITE);
		mainMenu.setBackground(Color.GRAY);
		mainMenu.setAlignmentX(Component.RIGHT_ALIGNMENT);
		setJMenuBar(mainMenu);
		mainMenu.add(knjigeMenu);
		knjigeMenu.add(knjigeItem);
		knjigeMenu.add(primerciItem);
		knjigeMenu.add(zanrKnjigeItem);
		knjigeMenu.add(iznajmljivanjeKnjigeItem);
		mainMenu.add(ljudiMenu);
		ljudiMenu.add(tipClanarinejeItem);
		ljudiMenu.add(clanItem);
		getContentPane().setLayout(new MigLayout("", "[784px]", "[179px][179px][179px]"));
		getContentPane().add(txtpnSada, "cell 0 0,grow");
		txtpnSada.setEditable(false);
		txtpnSada.setBackground(Color.LIGHT_GRAY);
		txtpnSada.setText("Ulogovan Zaposleni: " + prijavljeniKorisnik.getKorisnickoIme());
		
		
		getContentPane().add(label, "cell 0 2,grow");
		if(isAdmin) {
			mainMenu.add(zaposleniMenu);
			zaposleniMenu.add(bibliotekarItem);
			zaposleniMenu.add(administratorItem);
		}
		mainMenu.add(bibliotekaMenu);
		bibliotekaMenu.add(bibliotekaItem);
	}
	private void initActions() {
		primerciItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PrimerakKnjigePrikaz primerak = new PrimerakKnjigePrikaz(biblioteka);
				primerak.setVisible(true);
			};
		});
		knjigeItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				KnjigePrikaz knjiga = new KnjigePrikaz(biblioteka);
				knjiga.setVisible(true);
			};
		});
		clanItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ClanPrikaz clan = new ClanPrikaz(biblioteka);
				
				
				
				clan.setVisible(true);
			};
		});
		
		zanrKnjigeItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ZanrKnjigePrikaz zanr = new ZanrKnjigePrikaz(biblioteka);
				zanr.setVisible(true);
			};
		});
		
		tipClanarinejeItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TipClanarinePrikaz tip = new TipClanarinePrikaz(biblioteka);
				tip.setVisible(true);
			};
		});
		
		administratorItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AdminPrikaz admin = new AdminPrikaz(biblioteka);
				admin.setVisible(true);
			};
		});
	
		bibliotekarItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				BibliotekarPrikaz bibliotekari = new BibliotekarPrikaz(biblioteka);
				bibliotekari.setVisible(true);
			};
		});
		
		iznajmljivanjeKnjigeItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				IznajmljivanjePrikaz iznajmljivanje = new IznajmljivanjePrikaz(biblioteka,prijavljeniKorisnik);
				iznajmljivanje.setVisible(true);
			};
		});
	

		bibliotekaItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				BibliotekaPrikaz bibliotekaa = new BibliotekaPrikaz(biblioteka);
				bibliotekaa.setVisible(true);
			};
		});
	}
}
