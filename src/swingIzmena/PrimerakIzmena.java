package swingIzmena;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import biblioteka.Biblioteka;
import biblioteka.Knjiga;
import biblioteka.PrimerakKnjige;
import net.miginfocom.swing.MigLayout;

public class PrimerakIzmena extends JDialog{
	 private Biblioteka biblioteka;
	 private PrimerakKnjige primerak;
	 
	 
	 private JLabel lblID = new JLabel("ID");
	 private JTextField txtID = new JTextField(20);
	 private JLabel lblBrStrana = new JLabel("Broj Strana");
	 private JTextField txtBrStrana = new JTextField(20);
	 private JLabel lblTipPoveza = new JLabel("Tvrd povez");
	 private JCheckBox txtTipPoveza = new JCheckBox();
	 private JLabel lblGodinaSt = new JLabel("Godina Stampanja");
	 private JTextField txtGodinaSt = new JTextField(20);
	 private JLabel lbljeliIznajmljena = new JLabel("jeliIznajmljena");
	 private JCheckBox txtjeliIznajmljena = new JCheckBox();
	 private JLabel lblKnjiga = new JLabel("Knjiga");
	 private JComboBox cmbxKnjiga = new JComboBox();
	 private JButton btnSave = new JButton("Save");
	 private JButton btnCancel = new JButton("Cancel");
	 
	 public PrimerakIzmena(Biblioteka biblioteka, PrimerakKnjige primerak) {
		 this.biblioteka = biblioteka;
		 this.primerak = primerak;
		 setTitle("Dodavanje novi primerak");
		 setSize(500,500);
		 setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		 setLocationRelativeTo(null);
		 initGUI();
		 initActions();
		 pack();
	 }

	private void initActions() {
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				PrimerakIzmena.this.dispose();
				PrimerakIzmena.this.setVisible(false);
			}
		});
		btnSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String id = txtID.getText().trim();
				String brStrana = txtBrStrana.getText().trim();
				int brStrana1 = 0;
				try {
					int brStrana2 = Integer.parseInt(brStrana);
					brStrana1 = brStrana2;
				}
				catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Mora biti numericka vrednost upisana","Greska",JOptionPane.WARNING_MESSAGE);
				}
				boolean tipPoveza = txtTipPoveza.isSelected();
				String godinaStampanja = txtGodinaSt.getText().trim();
				int godinaStampanja1 = 0;
				try {
					int godinaStampanja2 = Integer.parseInt(godinaStampanja);
					godinaStampanja1 = godinaStampanja2;
				}
				catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Mora biti numericka vrednost upisana","Greska",JOptionPane.WARNING_MESSAGE);
				}
				boolean jelIznajmljena = txtjeliIznajmljena.isSelected();
				int knjigaid = cmbxKnjiga.getSelectedIndex();
				Knjiga knjiga = biblioteka.sveNeobrisaneKnjige().get(knjigaid);
				
				if(id.equals("")||brStrana.equals("")||godinaStampanja.equals("")) {
					JOptionPane.showMessageDialog(null, "Moraju sva polja da budu popunjena","Greska",JOptionPane.WARNING_MESSAGE);
				}
				else {
					if(primerak == null) {
						PrimerakKnjige primerak = new PrimerakKnjige(id, brStrana1, godinaStampanja1, jelIznajmljena, knjiga, tipPoveza, false);
						biblioteka.getPrimerakKnjige().add(primerak);
					}
					else {
						primerak.setId(id);
						primerak.setBrStrana(brStrana1);
						primerak.setTipPoveza(tipPoveza);
						primerak.setGodinaStampanja(godinaStampanja1);
						primerak.setIzdata(jelIznajmljena);
						primerak.setKnjiga(knjiga);
					}
					try {
						biblioteka.sacuvajPrimerke();
						PrimerakIzmena.this.setVisible(false);
					}
					catch (Exception e1) {
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
		add(lblBrStrana);
		add(txtBrStrana);
		add(lblTipPoveza);
		add(txtTipPoveza);
		add(lblGodinaSt);
		add(txtGodinaSt);
		add(lbljeliIznajmljena);
		add(txtjeliIznajmljena);
		add(lblKnjiga);
		add(cmbxKnjiga);
		add(new JLabel());
//		add(btnSave,"split 2");
		add(btnSave);
		add(btnCancel);
		
		ArrayList<Knjiga> knjige = biblioteka.sveNeobrisaneKnjige();
		for(Knjiga knjiga : knjige) {
			cmbxKnjiga.addItem(knjiga.getNaslov());
		}
		
		if(primerak != null) {
			txtID.setText(primerak.getId());
			txtBrStrana.setText(Integer.toString(primerak.getBrStrana()));
			txtTipPoveza.setSelected(primerak.isTipPoveza());
			txtGodinaSt.setText(Integer.toString(primerak.getGodinaStampanja()));
			txtjeliIznajmljena.setSelected(primerak.isIzdata());
			cmbxKnjiga.setSelectedItem(primerak.getKnjiga().getNaslov());
			
		}
	}
}
