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
import ljudi.Administrator;
import net.miginfocom.swing.MigLayout;

public class AdminIzmena extends JDialog{
	 private Biblioteka biblioteka;
	 private Administrator administrator;
	 
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
	 
	 public AdminIzmena(Biblioteka biblioteka, Administrator administrator) {
		 this.biblioteka = biblioteka;
		 this.administrator = administrator;
		 setTitle("Dodavanje Admina");
		 setSize(600,400);
		 setResizable(false);
		 setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		 setLocationRelativeTo(null);
		 initGUI();
		 initActions();
		 pack();
	 }

	private void initGUI() {
		MigLayout mig = new MigLayout("wrap 2","[][]","[]10[]10[]");
		setLayout(mig);
		
		add(lblID);
		add(txtID);
		txtID.setEditable(false);
		txtJMBG.setEditable(false);
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
		
		if(administrator != null) {
			txtID.setText(administrator.getId());
			txtIme.setText(administrator.getIme());
			txtPrezime.setText(administrator.getPrezime());
			txtAdresa.setText(administrator.getAdresa());
			txtJMBG.setText(administrator.getJmbg());
			cmbxPol.setSelectedItem(administrator.getPol());
			txtKorisnickoIme.setText(administrator.getKorisnickoIme());
			txtKorisnickoIme.setEnabled(false);
			txtKorisnickaSifra.setText(administrator.getLozinka());
			txtPlata.setText(String.valueOf(administrator.getPlata()));
		}
	}

	private void initActions() { 
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AdminIzmena.this.dispose();
				AdminIzmena.this.setVisible(false);
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
				int plata2 = 0;
				try {
					int plata1 = Integer.parseInt(plata);
					plata2 = plata1;
				}
				catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Mora biti numericka vrednost upisana","Greska",JOptionPane.WARNING_MESSAGE);
				}
				
				if(id.equals("")||ime.equals("")||prezime.equals("")||JMBG.equals("")||adresa.equals("")||sifra.equals("")||koriskickoIme.equals("")||plata.equals("")) {
					JOptionPane.showMessageDialog(null, "Niste uneli sve podatke za dodavanje.", "Greska", JOptionPane.WARNING_MESSAGE);
				}
					if(administrator == null) {
						if(biblioteka.pronadjiAdmina(koriskickoIme)!= null) {
							JOptionPane.showMessageDialog(null, "Korisnicko ime vec postoji!", "Greska",JOptionPane.WARNING_MESSAGE);	
						}
					}
					else {
						administrator.setId(id);
						administrator.setIme(ime);
						administrator.setPrezime(prezime);
						administrator.setJmbg(JMBG);
						administrator.setAdresa(adresa);
						administrator.setPol(pol);
						administrator.setLozinka(sifra);
						administrator.setKorisnickoIme(koriskickoIme);
						administrator.setPlata(plata2);
					}
					try {
						biblioteka.sacuvajAdministatore();
						AdminIzmena.this.setVisible(false);
					}catch (Exception e1) {
						e1.printStackTrace();
					}
				}
//			}
		});
		
	}
}
