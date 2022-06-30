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
import ljudi.Admin;
import net.miginfocom.swing.MigLayout;

public class AdminDodavanje extends JFrame{
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
	private Admin admin;
	
	public AdminDodavanje(Biblioteka biblioteka) {
		this.biblioteka=biblioteka;
		setSize(500,1000);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		initGUI();
		initActions();
		pack();
	}
	public AdminDodavanje(Biblioteka biblioteka, Admin admin) {
		this.biblioteka=biblioteka;	
		this.admin=admin;
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
		
		if(admin!=null) {
			txtIme.setText(admin.getIme());
			txtPrezime.setText(admin.getPrezime());
			txtAdresa.setText(admin.getAdresa());
			txtJMBG.setText(admin.getJMBG());
			txtPlata.setText(admin.getPlata());
			txtKorisnickoIme.setText(admin.getKorisnickoIme());
			txtKorisnickoIme.setEnabled(false);
			txtLozinka.setText(admin.getLozinka());
			cbPol.setSelectedItem(admin.getPol());


		}
	}
	public void initActions() {
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AdminDodavanje.this.dispose();
				AdminDodavanje.this.setVisible(false);
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
					if(admin==null) {
						
						if(biblioteka.pronadjiAminaPoKorisnickomImenu(korisnickoIme)!=null) {
							JOptionPane.showMessageDialog(null, "Korisnicko ime vec postoji!", "Greska",
							JOptionPane.WARNING_MESSAGE);
						}
						String id= Integer.toString(biblioteka.getAdministratori().size());	
						Admin noviAdmin=new Admin(id,ime,prezime,adresa,JMBG,plata,korisnickoIme,lozinka,pol,false);
						biblioteka.getAdministratori().add(noviAdmin);
						
					}
					else {
						admin.setAdresa(adresa);
						admin.setIme(korisnickoIme);
						admin.setJMBG(JMBG);
						admin.setKorisnickoIme(korisnickoIme);
						admin.setLozinka(lozinka);
						admin.setPlata(plata);
						admin.setPol(pol);
						admin.setPrezime(prezime);
					}
					try {
						biblioteka.sacuvajAdmine();
						AdminDodavanje.this.setVisible(false);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				
				}
			});
	}}

