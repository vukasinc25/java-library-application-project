package swingMain;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import biblioteka.Biblioteka;
import net.miginfocom.swing.MigLayout;

public class BibliotekaIzmena extends JFrame {
	private JLabel lblNaziv = new JLabel("Naziv");
	private JTextField txtNaziv = new JTextField(20);
	private JLabel lblAdresa = new JLabel("Adresa");
	private JTextField txtAdresa = new JTextField(20);
	private JLabel lblTelefon = new JLabel("Telefon");
	private JTextField txtTelefon = new JTextField(20);
	private JLabel lblId = new JLabel("ID");
	private JTextField txtId = new JTextField(20);
	private JLabel lblRadnoVremeOd = new JLabel("Radno vreme od ");
	private JTextField txtRadnoVremeOd = new JTextField(20);
	private JLabel lblRadnoVremeDo = new JLabel("Radno vreme do");
	private JTextField txtRadnoVremeDo = new JTextField(20);
	private Biblioteka biblioteka;

	private JButton btnOk = new JButton("OK");
	private JButton btnCancel = new JButton("Cancel");
	
	public BibliotekaIzmena(Biblioteka biblioteka) {
		this.biblioteka=biblioteka;	
		setTitle("Dodavanje");
		setSize(500,1000);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		initGUI();
		initActions();
		pack();
	}
	private void initGUI() {
		MigLayout mig = new MigLayout("wrap 2", "[][]", "[]10[][]10[]");
		setLayout(mig);
		add(lblNaziv);
		add(txtNaziv);
		add(lblAdresa);
		add(txtAdresa);
		add(lblTelefon);
		add(txtTelefon);
		add(lblId);
		add(txtId);
		add(lblRadnoVremeOd);
		add(txtRadnoVremeOd);
		add(lblRadnoVremeDo);
		add(txtRadnoVremeDo);
		add(btnCancel);
		add(btnOk);
		
		txtNaziv.setText(biblioteka.getNaziv());
		txtAdresa.setText(biblioteka.getAdresa());
		txtTelefon.setText(biblioteka.getTelefon());
		txtId.setText(biblioteka.getId());
		txtId.setEnabled(false);
		txtRadnoVremeOd.setText(biblioteka.getRadnoVremeOd());
		txtRadnoVremeDo.setText(biblioteka.getRadnoVremeDo());
	}
	private boolean validacija() {
		boolean ok = true;
		String poruka = "Molimo popravite sledece greske u unosu:\n";
		
		try {
			Integer.parseInt(txtTelefon.getText().trim());
		}catch (NumberFormatException e) {
			poruka += "- Telefon mora biti broj\n";
			ok = false;
		}
		
		if(ok == false) {
			JOptionPane.showMessageDialog(null, poruka, "Neispravni podaci", JOptionPane.WARNING_MESSAGE);
		}
		return ok;
	}
	public void initActions() {
		
	
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				BibliotekaIzmena.this.dispose();
				BibliotekaIzmena.this.setVisible(false);
				
			}});
		
		
		
		
		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(validacija()) {
					String naziv=txtNaziv.getText().trim();
					String adresa=txtAdresa.getText().trim();
					String telefon=txtTelefon.getText().trim();
					String id=txtId.getText().trim();
					String radnoVrOd=txtRadnoVremeOd.getText().trim();
					String radnoVrDo=txtRadnoVremeDo.getText().trim();
					
					if(naziv.equals("") || adresa.equals("") || telefon.equals("") || id.equals("")
							|| radnoVrOd.equals("") || radnoVrDo.equals("")) {
						JOptionPane.showMessageDialog(null, "Niste uneli sve podatke za dodavanje.", "Greska", JOptionPane.WARNING_MESSAGE);
					}
					else {
						biblioteka.setNaziv(naziv);
						biblioteka.setAdresa(adresa);
						biblioteka.setTelefon(telefon);
						biblioteka.setId(id);
						biblioteka.setRadnoVremeOd(radnoVrDo);
						biblioteka.setRadnoVremeDo(radnoVrDo);
						
						try {
							biblioteka.sacuvajBilioteku();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						BibliotekaIzmena.this.setVisible(false);
					}
				
				}
			
			

			
			}
		});
		}

}