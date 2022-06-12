package swingDodavanje;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import biblioteka.Biblioteka;
import biblioteka.Knjiga;
import biblioteka.ZanrKnjige;
import enumeracije.JezikOriginala;
import net.miginfocom.swing.MigLayout;

public class KnjigaDodavanje extends JDialog {
	 private Biblioteka biblioteka;
	 private Knjiga knjiga;

	 private JLabel lblID = new JLabel("ID");
	 private JTextField txtID = new JTextField(20);
	 private JLabel lblINaslov = new JLabel("Naslov Knjige");
	 private JTextField txtNaslov = new JTextField(20);
	 private JLabel lblOriginalniNalsov = new JLabel("Originalsni Naslov");
	 private JTextField txtOriginalniNalsov = new JTextField(20);
	 private JLabel lblPisac = new JLabel("Pisac");
	 private JTextField txtPisac = new JTextField(20);
	 private JLabel lblGodinaObjavljivanja = new JLabel("Godina Objavljivanja");
	 private JTextField txtGodinaObjavljivanja = new JTextField(20);
	 private JezikOriginala[] jezici=JezikOriginala.values();
	 private JLabel lblOriginala = new JLabel("Originala:");
	 private JComboBox cmbxOriginala = new JComboBox(jezici);
	 private JLabel lblOpisKnjige = new JLabel("Opis Knjige");
	 private JTextField txtOpisKnjige = new JTextField(20);
	 private JLabel lblZanr = new JLabel("Zanr Knjige");
	 private JComboBox cmbxZanr = new JComboBox();
	 private JButton btnSave = new JButton("Save");
	 private JButton btnCancel = new JButton("Cancel");

	 
	 public KnjigaDodavanje(Biblioteka biblioteka) {
		 this.biblioteka = biblioteka;
		 setTitle("Dodavanje nove knjige:");
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
				KnjigaDodavanje.this.dispose();
				KnjigaDodavanje.this.setVisible(false);
			}
		});
		
		btnSave.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String id = txtID.getText().trim();
				String naslov = txtNaslov.getText().trim();
				String originalniNalsov = txtOriginalniNalsov.getText().trim();
				String pisac = txtPisac.getText().trim();
				String godinaObjavljianja = txtGodinaObjavljivanja.getText().trim();
				int godinaObjavljivanja2 = 0;
				try {
					int godinaObjavljivanja1 = Integer.parseInt(godinaObjavljianja);
					godinaObjavljivanja2 = godinaObjavljivanja1;
				}
				catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Mora biti numericka vrednost upisana","Greska",JOptionPane.WARNING_MESSAGE);
				}
//				String godinaObjavljivanja = txtGodinaObjavljivanja.getText().trim();
				String opis = txtOpisKnjige.getText().trim();
				int zanrId = cmbxZanr.getSelectedIndex();
				String jezikString = cmbxJezikOriginala.getSelectedItem().toString();
				JezikOriginala jezik = JezikOriginala.valueOf(jezikString);
				ZanrKnjige zanr = biblioteka.pronadjiZanr(Integer.toString(zanrId));
				if(naslov.equals("")||originalniNalsov.equals("")||pisac.equals("")||opis.equals("")) {
					JOptionPane.showMessageDialog(null, "Niste uneli sve podatke za dodavanje", "Greska",JOptionPane.WARNING_MESSAGE);
				}
				
				if(knjiga == null) {
//					String id, String naslovKnjige, String originalsniNaslovKnjige, String pisac,
//					int godinaObjavljanjaKnjige, Jezik jezikOriginala, String opisKnjige, ZanrKnjige zanr, boolean jeObrisana
//					String id = Integer.toString(biblioteka.getKnjige().size());
					Knjiga novaKnjiga = new Knjiga(id,naslov,originalniNalsov,pisac,godinaObjavljivanja2,jezik,opis,zanr,false);
					biblioteka.getKnjige().add(novaKnjiga);
				}
				else {
					knjiga.setNaslov(naslov);
					knjiga.setGodinaObjavljivanja(godinaObjavljivanja2);
					knjiga.setNaslov(originalniNalsov);
					knjiga.setOpisKnjige(opis);
					knjiga.setZanr(zanr);
					knjiga.setJezikOriginala(jezik);
					knjiga.setPisac(pisac);
				}
				try {
					biblioteka.sacuvajKnjige();
					KnjigaDodavanje.this.setVisible(false);
				}catch(IOException e1) {
					e1.printStackTrace();
				}
				
			}});
	}
			
	private void initGUI() {
		ArrayList<ZanrKnjige> zanrovi=biblioteka.getZanrovi();
		for(ZanrKnjige zanr : zanrovi) {
			cmbxZanr.addItem(zanr.getOpisZanra());
		}
		MigLayout mig = new MigLayout("wrap 2","[][]","[]10[]10[]");
		setLayout(mig);

		
		add(lblID);
		add(txtID);
		add(lblINaslov);
		add(txtNaslov);
		add(lblOriginalniNalsov);
		add(txtOriginalniNalsov);
		add(lblPisac);
		add(txtPisac);
		add(lblGodinaObjavljivanja);
		add(txtGodinaObjavljivanja);
		add(lblJezikOriginala);
		add(cmbxJezik);
		add(lblOpisKnjige);
		add(txtOpisKnjige);
		add(lblZanr);
		add(cmbxZanr);
		add(btnSave,"split 2");
		add(btnSave);
		add(btnCancel);
		
//		if(knjiga != null) {
//			txtNaslov.setText(knjiga.getNaslovKnjige());
//			txtOriginalniNalsov.setText(knjiga.getOriginalsniNaslovKnjige());
//			txtPisac.setText(knjiga.getPisac());
//			txtGodinaObjavljivanja.setText(Integer.toString(knjiga.getGodinaObjavljanjaKnjige()));
//			cmbxJezik.setSelectedItem(knjiga.getJezikOriginala());
//			cmbxZanr.setSelectedItem(knjiga.getZanr().getOpis());
//		}
	}
}
