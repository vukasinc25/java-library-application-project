package swingMain;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import biblioteka.Biblioteka;
import ljudi.Bibliotekar;
import biblioteka.Iznajmljivanje;
import ljudi.Zaposleni;

import ljudi.Bibliotekar;
import biblioteka.Biblioteka;
import java.awt.Component;
import javax.swing.JTextPane;
import java.awt.BorderLayout;
import java.awt.Color;
import net.miginfocom.swing.MigLayout;
import swingPrikaz.AdminPrikaz;
import swingPrikaz.BibliotekarPrikaz;
import swingPrikaz.ClanPrikaz;
import swingPrikaz.IznajmljivanjePrikaz;
import swingPrikaz.KnjigePrikaz;
import swingPrikaz.PrimerakKnjigePrikaz;
import swingPrikaz.TipClanarinePrikaz;
import swingPrikaz.ZanrKnjigePrikaz;

import javax.swing.JLabel;
import java.awt.GridLayout;

//import osobe.Prodavac;
//import prodavnica.Prodavnica;

public class MainWindow extends JFrame{

	private static final long serialVersionUID = 1L;
	private JMenuBar mainMenu = new JMenuBar();
	private JMenu knjigeMenu = new JMenu("Knjige");
	private JMenu ljudiMenu = new JMenu("Clanovi");
	private JMenuItem clanItem = new JMenuItem("Clan Biblioteke");
	private JMenuItem knjigeItem = new JMenuItem("Sve Knjige");
	private JMenuItem primerciItem = new JMenuItem("Primerci");
	private JMenuItem zanrKnjigeItem = new JMenuItem("Zanrovi");
	private JMenuItem tipClanarinejeItem = new JMenuItem("Tip Clanarine");
	private JMenuItem iznajmljivanjeKnjigeItem = new JMenuItem("Iznajmljivanje");
	private JMenu zaposleniMenu = new JMenu("Zaposleni");
	private JMenuItem bibliotekarItem = new JMenuItem("Bibliotekari");
	private JMenuItem administratorItem = new JMenuItem("Administartori");
	private Biblioteka biblioteka;
	private Zaposleni prijavljeniKorisnik;
	private boolean isAdmin;
	private final JTextPane txtpnSada = new JTextPane();
	ImageIcon ikonica = new ImageIcon("src/slike/knjiga.png");
	private final JLabel lblNewLabel = new JLabel("JAO MAMA");
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
		
		getContentPane().add(lblNewLabel, "cell 0 1,grow");
		
		getContentPane().add(label, "cell 0 2,grow");
		if(isAdmin) {
			mainMenu.add(zaposleniMenu);
			zaposleniMenu.add(bibliotekarItem);
			zaposleniMenu.add(administratorItem);
		}
	}
	
	private void initActions() {
		bibliotekarItem.addActionListener(new ActionListener() { /*Bibliotekar*/
			@Override
			public void actionPerformed(ActionEvent e) {
				BibliotekarPrikaz pp = new BibliotekarPrikaz(biblioteka,prijavljeniKorisnik);
				pp.setVisible(true);
			}
		});
		iznajmljivanjeKnjigeItem.addActionListener(new ActionListener() { /*Bibliotekar*/
			@Override
			public void actionPerformed(ActionEvent e) {
				IznajmljivanjePrikaz pp = new IznajmljivanjePrikaz(biblioteka,prijavljeniKorisnik);
				pp.setVisible(true);
			}
		});
		
		tipClanarinejeItem.addActionListener(new ActionListener() { /*TipClanarine*/
			@Override
			public void actionPerformed(ActionEvent e) {
				TipClanarinePrikaz pp = new TipClanarinePrikaz(biblioteka,prijavljeniKorisnik);
				pp.setVisible(true);
			}
		});
		
		administratorItem.addActionListener(new ActionListener() { /*Administratori*/
			@Override
			public void actionPerformed(ActionEvent e) {
				AdminPrikaz pp = new AdminPrikaz(biblioteka,prijavljeniKorisnik);
				pp.setVisible(true);
			}
		});
		
		primerciItem.addActionListener(new ActionListener() { /*PrimerakKnjige*/
			@Override
			public void actionPerformed(ActionEvent e) {
				PrimerakKnjigePrikaz kp = new PrimerakKnjigePrikaz(biblioteka,prijavljeniKorisnik);
				kp.setVisible(true);
			}
		});
		
		clanItem.addActionListener(new ActionListener() { /*ClanBiblioteke*/
			@Override
			public void actionPerformed(ActionEvent e) {
				ClanPrikaz dp = new ClanPrikaz(biblioteka,prijavljeniKorisnik);
				dp.setVisible(true);
			}
		});
		
		knjigeItem.addActionListener(new ActionListener() { /*Knjige*/
			@Override
			public void actionPerformed(ActionEvent e) {
				KnjigePrikaz kp = new KnjigePrikaz(biblioteka,prijavljeniKorisnik);
				kp.setVisible(true);
			}
		});
		
		zanrKnjigeItem.addActionListener(new ActionListener() { /*ZanrKnjige*/
			@Override
			public void actionPerformed(ActionEvent e) {
				ZanrKnjigePrikaz kp = new ZanrKnjigePrikaz(biblioteka,prijavljeniKorisnik);
				kp.setVisible(true);
			}
		});
	}
}
