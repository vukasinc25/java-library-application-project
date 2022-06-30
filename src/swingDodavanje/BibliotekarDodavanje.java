package swingDodavanje;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import biblioteka.Biblioteka;
import enumeracije.Pol;
import ljudi.Bibliotekar;
import net.miginfocom.swing.MigLayout;

public class BibliotekarDodavanje extends JFrame {
	private Pol[] pol=Pol.values();
	private JLabel lblIme=new JLabel("Ime: ");
	private JTextField txtIme=new JTextField(20);
	private JLabel lblPrezime=new JLabel("Prezime: ");
	private JTextField txtPrezime=new JTextField(20);
	private JLabel lblAdresa=new JLabel("Adresa: ");
	private JTextField txtAdresa=new JTextField(20);
	private JLabel lblJMBG=new JLabel("JMBG: ");
	private JTextField txtJMBG=new JTextField(20);
	private JLabel lblPlata=new JLabel("Plata: ");
	private JTextField txtPlata=new JTextField(20);
	private JLabel lblKorisnickoIme=new JLabel("KorisnickoIme: ");
	private JTextField txtKorisnickoIme=new JTextField(20);
	private JLabel lblLozinka=new JLabel("Lozinka: ");
	private JTextField txtLozinka=new JTextField(20);
	private JLabel lblPol=new JLabel("Pol: ");
	private JComboBox cbPol=new JComboBox(pol);
	private JButton btnOk = new JButton("OK");
	private JButton btnCancel = new JButton("Cancel");
	
	
	private Biblioteka biblioteka;
	private Bibliotekar bibliotekar;
	
	public BibliotekarDodavanje(Biblioteka biblioteka) {
		this.biblioteka=biblioteka;
		setSize(500,1000);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		initGUI();
		initActions();
		pack();
	}
	public BibliotekarDodavanje(Biblioteka biblioteka, Bibliotekar bibliotekar) {
		this.biblioteka=biblioteka;	
		this.bibliotekar=bibliotekar;
		setTitle("Dodavanje");
		setSize(500,1000);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		initGUI();
		initActions();
		pack();
	}
	public void initGUI() {
		MigLayout mig = new MigLayout("wrap 2", "[][]", "[]10[][]10[]");
		setLayout(mig);
		
		add(lblIme);
		add(txtIme);
		add(lblPrezime);
		add(txtPrezime);
		add(lblAdresa);
		add(txtAdresa);
		add(lblJMBG);
		add(txtJMBG);
		add(lblPlata);
		add(txtPlata);
		add(lblKorisnickoIme);
		add(txtKorisnickoIme);
		add(lblLozinka);
		add(txtLozinka);
		add(lblPol);
		add(cbPol);
		add(btnOk);
		add(btnCancel);
		
		if(bibliotekar!=null) {
			txtIme.setText(bibliotekar.getIme());
			txtPrezime.setText(bibliotekar.getPrezime());
			txtAdresa.setText(bibliotekar.getAdresa());
			txtJMBG.setText(bibliotekar.getJMBG());
			txtPlata.setText(bibliotekar.getPlata());
			txtKorisnickoIme.setText(bibliotekar.getKorisnickoIme());
			txtKorisnickoIme.setEnabled(false);
			txtLozinka.setText(bibliotekar.getLozinka());
			cbPol.setSelectedItem(bibliotekar.getPol());


		}
	}
	public void initActions() {
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				BibliotekarDodavanje.this.dispose();
				BibliotekarDodavanje.this.setVisible(false);
			}
		});
		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String ime=txtIme.getText().trim();
				String prezime=txtPrezime.getText().trim();
				String adresa=txtAdresa.getText().trim();
				String JMBG=txtJMBG.getText().trim();
				String plata=txtPlata.getText().trim();
				String korisnickoIme=txtKorisnickoIme.getText().trim();
				String lozinka=txtLozinka.getText().trim();
				String polString=cbPol.getSelectedItem().toString();
				Pol pol=Pol.valueOf(polString);

				if(ime.equals("") || prezime.equals("") || adresa.equals("") || JMBG.equals("") || plata.equals("") 
						|| korisnickoIme.equals("") || lozinka.equals("")) {
					JOptionPane.showMessageDialog(null, "Niste uneli sve podatke za dodavanje.", "Greska", JOptionPane.WARNING_MESSAGE);
				}
				else {
					if(bibliotekar==null) {
						
						if(biblioteka.pronadjiAminaPoKorisnickomImenu(korisnickoIme)!=null) {
							JOptionPane.showMessageDialog(null, "Korisnicko ime vec postoji!", "Greska",
							JOptionPane.WARNING_MESSAGE);
						}
						String id= Integer.toString(biblioteka.getBibliotekari().size());	
						Bibliotekar noviBibliotekar=new Bibliotekar(id,ime,prezime,adresa,JMBG,plata,korisnickoIme,lozinka,pol,false);
						biblioteka.getBibliotekari().add(noviBibliotekar);
						
					}
					else {
						bibliotekar.setAdresa(adresa);
						bibliotekar.setIme(korisnickoIme);
						bibliotekar.setJMBG(JMBG);
						bibliotekar.setKorisnickoIme(korisnickoIme);
						bibliotekar.setLozinka(lozinka);
						bibliotekar.setPlata(plata);
						bibliotekar.setPol(pol);
						bibliotekar.setPrezime(prezime);
					}
					try {
						biblioteka.sacuvajBibliotekare();;
						BibliotekarDodavanje.this.setVisible(false);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				
				}
			});
	}
	
}
