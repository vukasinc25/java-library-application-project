package swingDodavanje;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import biblioteka.Biblioteka;
import ljudi.TipClanarine;
import net.miginfocom.swing.MigLayout;

public class TipDodavanje extends JDialog{
	private Biblioteka biblioteka;
	private TipClanarine tipClanarine;

	
//	String id, String naziv, double cena
	 private JLabel lblID = new JLabel("ID");
	 private JTextField txtID = new JTextField(20);
	 private JLabel lblNaziv = new JLabel("Naziv");
	 private JTextField txtNaziv = new JTextField(20);
	 private JLabel lblCena = new JLabel("Cena");
	 private JTextField txtCena = new JTextField(20);
	 private JButton btnSave = new JButton("Save");
	 private JButton btnCancel = new JButton("Cancel");
	 
	 public TipDodavanje(Biblioteka biblioteka) {
		 this.biblioteka = biblioteka;
		 setTitle("Dodavanje novi zanr");
		 setSize(500,500);
		 setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		 setLocationRelativeTo(null);
//		 cmbxEmnumPol.setModel(new DefaultComboBoxModel<EmnumPol>(EmnumPol.values()));
		 initGUI();
		 initActions();
	 }

	private void initActions() {	
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TipDodavanje.this.dispose();
				TipDodavanje.this.setVisible(false);
			}
		});
		
		btnSave.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String id = txtID.getText().trim();
				String cena = txtCena.getText().trim();
				Double cena2 = (double) 0;
				try {
					Double cena1 = Double.parseDouble(cena);
					cena2 = cena1;
				}catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Mora biti numericka vrednost upisana","Greska",JOptionPane.WARNING_MESSAGE);
				}
				String naziv = txtNaziv.getText().trim();
				
				if(id.equals("")||cena.equals("")||naziv.equals("")) {
					JOptionPane.showMessageDialog(null, "Moraju sva polja da budu popunjena","Greska",JOptionPane.WARNING_MESSAGE);
				}
				
				else {
					if(tipClanarine == null) {
						TipClanarine zanr = new TipClanarine(id,naziv,cena2,false);
						biblioteka.getTipClanarine().add(zanr);
					}else {
						tipClanarine.setId(id);
						tipClanarine.setTip(naziv);
						tipClanarine.setCena(cena2);
					}
					try {
						biblioteka.sacuvajTipClanarine();
						TipDodavanje.this.setVisible(false);
					}catch (IOException e1) {
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
		add(lblNaziv);
		add(txtNaziv);
		add(lblCena);
		add(txtCena);
		add(btnSave);
		add(btnCancel);
		
	}
}
