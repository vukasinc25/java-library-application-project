package swingDodavanje;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import biblioteka.Biblioteka;
import enumeracije.Pol;
import ljudi.Clan;
import ljudi.TipClanarine;
import net.miginfocom.swing.MigLayout;

public class ClanDodavanje extends JFrame {
	private Pol[] pol=Pol.values();
	private JLabel lblPol=new JLabel("Pol: ");
	private JComboBox cbPol=new JComboBox(pol);
//	private JLabel lblBrojClanskeKarte=new JLabel("Broj clanske karte: ");
//	private JTextField txtBrojClanskeKarte=new JTextField(20);
	private JLabel lblDatumPoslednjeUplate=new JLabel("Datum poslednje uplate: ");
	private JTextField txtDatumPoslednjeUplate=new JTextField(20);
	private JLabel lblBrMeseci=new JLabel("Broj meseci: ");
	private JTextField txtBrMeseci=new JTextField(20);
	private JLabel lblIme=new JLabel("Ime: ");
	private JTextField txtIme=new JTextField(20);
	private JLabel lblPrezime=new JLabel("Prezime: ");
	private JTextField txtPrezime=new JTextField(20);
	private JLabel lblJMBG=new JLabel("JMBG: ");
	private JTextField txtJMBG=new JTextField(20);
	private JLabel lblAdresa=new JLabel("Adresa: ");
	private JTextField txtAdresa=new JTextField(20);
	private JLabel lblAktivnost=new JLabel("Aktivan: ");
	private JCheckBox chbAktivnost=new JCheckBox();
	private JLabel lblTipClanarine= new JLabel("Tip clanarine: ");
	private JComboBox<String> cbTipClanarine= new JComboBox<String>();
	private Biblioteka biblioteka;
	private Clan clan;
	private JButton btnOk = new JButton("OK");
	private JButton btnCancel = new JButton("Cancel");
	
	
	public ClanDodavanje(Biblioteka biblioteka) {
		this.biblioteka=biblioteka;
		setSize(500,1000);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		initGUI();
		initActions();
		pack();
	}
	public ClanDodavanje(Biblioteka biblioteka,Clan clan) {
		this.biblioteka=biblioteka;	
		this.clan=clan;
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
		ArrayList<TipClanarine> tipovi=biblioteka.sviNeobrisaniTipovi();
		for(TipClanarine tipClanarine: tipovi) {
			cbTipClanarine.addItem(tipClanarine.getOpis());
		}
	
		MigLayout mig = new MigLayout("wrap 2", "[][]", "[]10[][]10[]");
		setLayout(mig);
		
//		add(lblBrojClanskeKarte);
//		add(txtBrojClanskeKarte);
		add(lblDatumPoslednjeUplate);
		add(txtDatumPoslednjeUplate);
		add(lblBrMeseci);
		add(txtBrMeseci);
		add(lblTipClanarine);
		add(cbTipClanarine);
		add(lblAktivnost);
		add(chbAktivnost);
		add(lblIme);
		add(txtIme);
		add(lblPrezime);
		add(txtPrezime);
		add(lblJMBG);
		add(txtJMBG);
		add(lblAdresa);
		add(txtAdresa);
		add(lblPol);
		add(cbPol);
		add(btnOk);
		add(btnCancel);
		
		if(clan!=null) {
			String datum =clan.getDatPoslednjeUplate().toString();
			txtDatumPoslednjeUplate.setText(datum);
			txtBrMeseci.setText(clan.getBrMeseci());
			cbTipClanarine.setSelectedItem(clan.getTip().getOpis());
			chbAktivnost.setSelected(clan.getAktivnost());
			txtIme.setText(clan.getIme());
			txtPrezime.setText(clan.getPrezime());
			txtJMBG.setText(clan.getJMBG());
			txtAdresa.setText(clan.getAdresa());
			cbPol.setSelectedItem(clan.getPol());
		}

	}
	public void initActions() {
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ClanDodavanje.this.dispose();
				ClanDodavanje.this.setVisible(false);
				
			}
		});
		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
//				String brojClanskeKarte=txtBrojClanskeKarte.getText().trim();
				DateTimeFormatter dateFormatter= DateTimeFormatter.ofPattern("yyyy-MM-dd");
				String datumPoslednjeUplateString=txtDatumPoslednjeUplate.getText().trim();
				LocalDate datumPoslednjeUplate=LocalDate.parse(datumPoslednjeUplateString,dateFormatter);
				int tipid = cbTipClanarine.getSelectedIndex();
				String BrMeseci=txtBrMeseci.getText().trim();
				TipClanarine tipClanarine=biblioteka.sviNeobrisaniTipovi().get(tipid);
				String ime=txtIme.getText().trim();
				String prezime=txtPrezime.getText().trim();
				String polString=cbPol.getSelectedItem().toString();
				String JMBG=txtJMBG.getText().trim();
				String adresa=txtAdresa.getText().trim();
				boolean isSelected = chbAktivnost.isSelected();
				Pol pol=Pol.valueOf(polString);

				if(ime.equals("") || prezime.equals("") || adresa.equals("") || JMBG.equals("")
						|| datumPoslednjeUplate.equals("")) {
					JOptionPane.showMessageDialog(null, "Niste uneli sve podatke za dodavanje.", "Greska", JOptionPane.WARNING_MESSAGE);
				}
				else {
					if(clan ==null) {
						double cena=Integer.parseInt(biblioteka.getClanovi().get(tipid).getTip().getCena());
						if(Integer.parseInt(txtBrMeseci.getText().trim())>=6 && Integer.parseInt(txtBrMeseci.getText().trim())<12) {
							cena=(Integer.parseInt(txtBrMeseci.getText().trim())*cena)-(Integer.parseInt(txtBrMeseci.getText().trim())*cena*0.1);
						}
						else if(Integer.parseInt(txtBrMeseci.getText().trim())>=12) {
							cena=(Integer.parseInt(txtBrMeseci.getText().trim())*cena)-(Integer.parseInt(txtBrMeseci.getText().trim())*cena*0.2);
						}
						else {
							cena=Integer.parseInt(txtBrMeseci.getText().trim())*cena;
						}
						JOptionPane.showMessageDialog(rootPane, "Cena je:"+cena);
						String BrojClanskeKarte= Integer.toString(biblioteka.getClanovi().size());	
						Clan noviClan=new Clan(BrojClanskeKarte, datumPoslednjeUplate,BrMeseci, tipClanarine, isSelected, ime, prezime, JMBG, adresa,pol, false);
						biblioteka.getClanovi().add(noviClan);
					}
					else {
						clan.setAdresa(adresa);
						clan.setAktivnost(isSelected);
						clan.setBrMeseci(BrMeseci);
						clan.setDatPoslednjeUplate(datumPoslednjeUplate);
						clan.setIme(ime);
						clan.setJMBG(JMBG);
						clan.setPrezime(prezime);
						clan.setTip(tipClanarine);
						clan.setPol(pol);
					}
					try {
						biblioteka.sacuvajClanove();
						ClanDodavanje.this.setVisible(false);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
	}
		
}
