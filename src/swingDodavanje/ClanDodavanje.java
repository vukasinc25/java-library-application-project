package swingDodavanje;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import biblioteka.Biblioteka;
import enumeracije.Pol;
import ljudi.Clan;
import ljudi.TipClanarine;
import net.miginfocom.swing.MigLayout;

public class ClanDodavanje extends JDialog{
	private Biblioteka biblioteka;
	private Clan clan;
	
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
	 private JLabel lblbrClankarte = new JLabel("brClankarte");
	 private JTextField txtbrClankarte = new JTextField(20);
	 private JLabel lbldatumPoslednjeUplate = new JLabel("datumPoslednjeUplate");
	 private JTextField txtdatumPoslednjeUplate = new JTextField(20);
	 private JLabel lblbrojmecesi = new JLabel("Broj meseci clanarine");
	 private JTextField txtbrojmeseci = new JTextField(20);
	 private JLabel lblAktivan = new JLabel("Aktivan");
	 private JCheckBox chbAktivnost =new JCheckBox();
	 private JLabel lblTip = new JLabel("Tip Clanarine");
	 private JComboBox cmbxTip = new JComboBox();
	 private JButton btnSave = new JButton("Save");
	 private JButton btnCancel = new JButton("Cancel");
	 
	 public ClanDodavanje(Biblioteka biblioteka,Clan clan) {
		 this.biblioteka = biblioteka;
		 this.clan = clan;
		 setTitle("Dodavanje novog bibliotekara");
		 setSize(400,500);
		 setResizable(false);
		 setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		 setLocationRelativeTo(null);
//		 cmbxPol.setModel(new DefaultComboBoxModel<Pol>(Pol.values()));
		 initGUI();
		 initActions();
		 pack();
	 }

	private void initActions() {	
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ClanDodavanje.this.dispose();
				ClanDodavanje.this.setVisible(false);
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
				String polStr = cmbxPol.getSelectedItem().toString();
				Pol pol = Pol.valueOf(polStr);
				String brClankarte = txtbrClankarte.getText().trim();
				String datum = txtdatumPoslednjeUplate.getText().trim();
				LocalDate datum2 = null;
				try {
					LocalDate datum1 = LocalDate.parse(datum);
					datum2 = datum1;
				}
				catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1,"Greska",JOptionPane.WARNING_MESSAGE);
				}
				
				
				
				
				String brojMecesi = txtbrojmeseci.getText().trim();
				int brojMeseci2 = 0;
				try {
					int brojMeseci1 = Integer.parseInt(brojMecesi);
					brojMeseci2 = brojMeseci1;
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1,"Greska",JOptionPane.WARNING_MESSAGE);
				}
				
				
				
				
				boolean isAktivan = chbAktivnost.isSelected();
				int tipid = cmbxTip.getSelectedIndex();
				TipClanarine tipClanarine = biblioteka.sviNeobrisaniTipovi().get(tipid);
				
				
				
				if(id.equals("")||ime.equals("")||prezime.equals("")||JMBG.equals("")||adresa.equals("")||brClankarte.equals("")||brojMecesi.equals("")) {
					JOptionPane.showMessageDialog(null, "Moraju sva polja da budu popunjena","Greska",JOptionPane.WARNING_MESSAGE);
				}
				else {
					if(clan == null) {
						Clan noviClan = new Clan(id,ime,prezime,JMBG,adresa,pol,brClankarte,datum2,brojMeseci2,isAktivan,tipClanarine,false);
						biblioteka.getClanBiblioteke().add(noviClan);
					}
					else {
						clan.setId(id);
						clan.setIme(ime);
						clan.setPrezime(prezime);
						clan.setJmbg(JMBG);
						clan.setAdresa(adresa);
						clan.setPol(pol);
						clan.setBrClanskeKarte(brClankarte);
						clan.setDatumUplate(datum2);
						clan.setUplacenoMeseci(brojMeseci2);
						clan.setAktivan(isAktivan);
						clan.setTipClanarine(tipClanarine);
					}
					try {
						biblioteka.sacuvajClanove();
						ClanDodavanje.this.setVisible(false);
					}
					catch (Exception e1) {
						e1.printStackTrace();
					}
				}	
			}
		});
	}

	private void initGUI() {
		ArrayList<TipClanarine> tipovi = biblioteka.sviNeobrisaniTipovi();
		for(TipClanarine tipClanarine : tipovi) {
			cmbxTip.addItem(tipClanarine.getTip());
		}
		
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
		add(lblbrClankarte);
		add(txtbrClankarte);
		add(lbldatumPoslednjeUplate);
		add(txtdatumPoslednjeUplate);
		add(lblbrojmecesi);
		add(txtbrojmeseci);
		add(lblAktivan);
		add(chbAktivnost);
		add(lblTip);
		add(cmbxTip);
		add(btnSave);
		add(btnCancel);
		
	}
}
