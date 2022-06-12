package swingDodavanje;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import biblioteka.Biblioteka;
import enumeracije.Pol;
import ljudi.Administrator;
import net.miginfocom.swing.MigLayout;

public class AdminDodavanje extends JDialog{
	 private Biblioteka biblioteka;
	 private Administrator administrator;
//	 int index;
	 
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
	 
	 public AdminDodavanje(Biblioteka biblioteka) {
		 this.biblioteka = biblioteka;
		 setTitle("Dodavanje novog administratora");
		 setSize(500,500);
		 setResizable(false);
		 setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		 setLocationRelativeTo(null);
		 initGUI();
		 initActions();
	 }

	private void initGUI() {
		MigLayout mig = new MigLayout("wrap 2","[][]","[]10[]10[]");
		setLayout(mig);
		
		add(lblID);
		add(txtID);
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
	}

	private void initActions() {
		 
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AdminDodavanje.this.dispose();
				AdminDodavanje.this.setVisible(false);
			}
		});
		
		btnSave.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String id = txtID.getText().trim();
				String ime = txtIme.getText().trim();
				String prezime = txtPrezime.getText().trim();
				String jmbg = txtJMBG.getText().trim();
				String adresa = txtAdresa.getText().trim();
				String polString=cmbxPol.getSelectedItem().toString();
				Pol defpol= Pol.valueOf(polString);
				String korisnickoIme = txtKorisnickoIme.getText().trim();
				String lozinka = txtKorisnickaSifra.getText().trim();
				String plata = txtPlata.getText().trim();
				
				if(id.equals("")||ime.equals("")||prezime.equals("")||jmbg.equals("")||adresa.equals("")||lozinka.equals("")||korisnickoIme.equals("")||plata.equals("")) {
					JOptionPane.showMessageDialog(null, "Niste uneli sve podatke za dodavanje.", "Greska", JOptionPane.WARNING_MESSAGE);
				}
				else {
					if(administrator == null) {
						if(biblioteka.pronadjiAdmina(korisnickoIme)!= null) {
							JOptionPane.showMessageDialog(null, "Korisnicko ime vec postoji!", "Greska",JOptionPane.WARNING_MESSAGE);	
						}
						Administrator noviadmin= new Administrator(ime,prezime,jmbg,adresa,id,defpol,korisnickoIme,lozinka,plata, obrisan);
						biblioteka.getAdmin().add(noviadmin);
					}
//					else {
//						administrator.setId(id);
//						administrator.setIme(ime);
//						administrator.setPrezime(prezime);
//						administrator.setJMBG(JMBG);
//						administrator.setAdresa(adresa);
//						administrator.setPol(pol);
//						administrator.setKorisnickaSifra(sifra);
//						administrator.setKorisnickoIme(korisnickoIme);
//						administrator.setPlata(plata);
//					}
					try {
						biblioteka.sacuvajAdministatore();
						AdminDodavanje.this.setVisible(false);
					}catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		
	}
}
