package swingIzmena;

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

public class TipIzmena extends JDialog{
	private Biblioteka biblioteka;
	private TipClanarine tipClanarine;

	 private JLabel lblID = new JLabel("ID");
	 private JTextField txtID = new JTextField(20);
	 private JLabel lblNaziv = new JLabel("Naziv");
	 private JTextField txtNaziv = new JTextField(20);
	 private JLabel lblCena = new JLabel("Cena");
	 private JTextField txtCena = new JTextField(20);
	 private JButton btnSave = new JButton("Save");
	 private JButton btnCancel = new JButton("Cancel");
	 
	 public TipIzmena(Biblioteka biblioteka, TipClanarine tipClanarine) {
		 this.biblioteka = biblioteka;
		 this.tipClanarine = tipClanarine;
		 setTitle("Dodavanje novi zanr");
		 setSize(500,500);
		 setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		 setLocationRelativeTo(null);
		 initGUI();
		 initActions();
	 }

	private void initActions() {	
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TipIzmena.this.dispose();
				TipIzmena.this.setVisible(false);
			}
		});
		
		btnSave.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String id = txtID.getText().trim();
				String cena = txtCena.getText().trim();
				int cena2 = 0;
				try {
					int cena1 = Integer.parseInt(cena);
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
						TipIzmena.this.setVisible(false);
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
		txtID.setEditable(false);
		add(lblNaziv);
		add(txtNaziv);
		add(lblCena);
		add(txtCena);
		add(btnSave);
		add(btnCancel);
		
		if(tipClanarine != null) {
			txtID.setText(tipClanarine.getId());
			txtNaziv.setText(tipClanarine.getTip());
			txtCena.setText(Double.toString(tipClanarine.getCena()));
		}
		
	}
}
