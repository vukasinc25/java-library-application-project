package swingIzmena;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import biblioteka.Biblioteka;
import enumeracije.Pol;
import ljudi.Bibliotekar;
import net.miginfocom.swing.MigLayout;

public class BibliotekarIzmena extends JDialog{
	private Biblioteka biblioteka;
	 private Bibliotekar bibliotekar;

	 private JLabel lblID = new JLabel("ID");
	 private JTextField txtID = new JTextField(20);
	 private JLabel lblIme = new JLabel("Ime");
	 private JTextField txtIme = new JTextField(20);
	 private JLabel lblPrezime = new JLabel("Prezime");
	 private JTextField txtPrezime = new JTextField(20);
	 private JLabel lblJMBG = new JLabel("JMBG");
	 private JTextField txtJMBG = new JTextField(20);
	 private JLabel lblAdresa = new JLabel("Adresa");
	 private JTextField txtAdresa = new JTextField(20);
	 private Pol[] pol = Pol.values();
	 private JLabel lblPol = new JLabel("Pol");
	 private JComboBox cmbxPol = new JComboBox(pol);
	 private JLabel lblKorisnickoIme = new JLabel("Korisnicko Ime");
	 private JTextField txtKorisnickoIme = new JTextField(20);
	 private JLabel lblKorisnickaSifra = new JLabel("Korisnicka Sifra");
	 private JTextField txtKorisnickaSifra = new JTextField(20);
	 private JLabel lblPlata = new JLabel("Plata");
	 private JTextField txtPlata = new JTextField(20);
	 private JButton btnSave = new JButton("Save");
	 private JButton btnCancel = new JButton("Cancel");
	 
	 public BibliotekarIzmena(Biblioteka biblioteka,Bibliotekar bibliotekar) {
		 this.biblioteka = biblioteka;
		 this.bibliotekar = bibliotekar;
		 setTitle("Dodavanje novog bibliotekara");
		 setSize(500,500);
		 setResizable(false);
		 setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		 setLocationRelativeTo(null);
		 initGUI();
		 initActions();
	 }

	private void initActions() {	
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				BibliotekarIzmena.this.dispose();
				BibliotekarIzmena.this.setVisible(false);
			}
		});

		btnSave.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String id = txtID.getText().trim();
				String ime = txtIme.getText().trim();
				String prezime = txtPrezime.getText().trim();
				String JMBG = txtJMBG.getText().trim();
				String adresa = txtAdresa.getText().trim();
				String polString=cmbxPol.getSelectedItem().toString();
				Pol pol= Pol.valueOf(polString);
				String sifra = txtKorisnickaSifra.getText().trim();
				String koriskickoIme = txtKorisnickoIme.getText().trim();
				String plata = txtPlata.getText().trim();
				
				if(id.equals("")||ime.equals("")||prezime.equals("")||JMBG.equals("")||adresa.equals("")||sifra.equals("")||koriskickoIme.equals("")||plata.equals("")) {
					JOptionPane.showMessageDialog(null, "Niste uneli sve podatke za dodavanje.", "Greska", JOptionPane.WARNING_MESSAGE);
				}
				else {
					if(bibliotekar == null) {
						if(biblioteka.pronadjiAdmina(koriskickoIme)!= null) {
							JOptionPane.showMessageDialog(null, "Korisnicko ime vec postoji!", "Greska",JOptionPane.WARNING_MESSAGE);	
						}
					}
					else {
						bibliotekar.setId(id);
						bibliotekar.setIme(ime);
						bibliotekar.setPrezime(prezime);
						bibliotekar.setJmbg(JMBG);
						bibliotekar.setAdresa(adresa);
						bibliotekar.setPol(pol);
						bibliotekar.setLozinka(sifra);
						bibliotekar.setKorisnickoIme(koriskickoIme);
						bibliotekar.setPlata(plata);
					}
					try {
						biblioteka.sacuvajBibliotekre();
						BibliotekarIzmena.this.setVisible(false);
					}catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		});
	}

	private void initGUI() {
		MigLayout mig = new MigLayout("wrap 2","[][]","[]10[]10[]");
		setLayout(mig);
		
		add(lblID);
		add(txtID);
		txtID.setEditable(false);
		txtJMBG.setEditable(false);
		txtKorisnickoIme.setEnabled(false);
		add(lblIme);
		add(txtIme);
		add(lblPrezime);
		add(txtPrezime);
		add(lblJMBG);
		add(txtJMBG);
		add(lblAdresa);
		add(txtAdresa);
		add(lblPol);
		add(cmbxPol);
		add(lblKorisnickoIme);
		add(txtKorisnickoIme);
		add(lblKorisnickaSifra);
		add(txtKorisnickaSifra);
		add(lblPlata);
		add(txtPlata);
		add(btnSave);
		add(btnCancel);
		
		if(bibliotekar != null) {
			txtID.setText(bibliotekar.getId());
			txtIme.setText(bibliotekar.getIme());
			txtPrezime.setText(bibliotekar.getPrezime());
			txtJMBG.setText(bibliotekar.getJmbg());
			txtAdresa.setText(bibliotekar.getAdresa());
			cmbxPol.setSelectedItem(bibliotekar.getPol());
			txtKorisnickoIme.setText(bibliotekar.getKorisnickoIme());
			txtKorisnickaSifra.setText(bibliotekar.getLozinka());
			txtPlata.setText(bibliotekar.getPlata());
		}
		
	}
}
