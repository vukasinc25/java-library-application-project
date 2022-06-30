package swingDodavanje;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import biblioteka.Biblioteka;
import biblioteka.Knjiga;
import biblioteka.Zanr;
import enumeracije.Jezik;
import net.miginfocom.swing.MigLayout;

public class KnjigaDodavanje extends JFrame{
	private Jezik[] jezici=Jezik.values();
//	private JLabel lblGreeting = new JLabel("DODAJ KNJIGU");
	private JLabel lblNaslov = new JLabel("Naslov");
	private JTextField txtNaslov = new JTextField(20);
	private JLabel lblOriginal = new JLabel("Original:");
	private JTextField txtOriginal = new JTextField(20);
	private JLabel lblPisac = new JLabel("Pisac:");
	private JTextField txtPisac = new JTextField(20);
	private JLabel lblGodinaObj = new JLabel("Godina objavljivanja:");
	private JTextField txtGodinaObj= new JTextField(20);
	private JLabel lblOpis = new JLabel("Opis:");
	private JTextField txtOpis = new JTextField(20);
	private JLabel lblJezik = new JLabel("Jezik:");
	private JComboBox cbJezik=new JComboBox(jezici);
	private JLabel lblZanr = new JLabel("Zanr:");
	private JComboBox cbZanr= new JComboBox();

	
	
	private JButton btnOk = new JButton("OK");
	private JButton btnCancel = new JButton("Cancel");
	
	private Biblioteka biblioteka;
	private Knjiga knjiga;
	

	
	public KnjigaDodavanje(Biblioteka biblioteka) {
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
	
	public KnjigaDodavanje(Biblioteka biblioteka, Knjiga knjiga) {
		this.biblioteka=biblioteka;	
		this.knjiga=knjiga;
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
		ArrayList<Zanr> zanrovi=biblioteka.sviNeobrisaniZanrovi();
		for(Zanr zanr: zanrovi) {
			cbZanr.addItem(zanr.getOpis());
		}
		MigLayout mig = new MigLayout("wrap 2", "[][]", "[]10[][]10[]");
		setLayout(mig);
		
//		add(lblGreeting, "span 2");
		add(lblNaslov);
		add(txtNaslov);
		add(lblOriginal);
		add(txtOriginal);
		add(lblPisac);
		add(txtPisac);
		add(lblGodinaObj);
		add(txtGodinaObj);
		add(lblOpis);
		add(txtOpis);
		add(lblJezik);
		add(cbJezik);
		add(lblZanr);
		add(cbZanr);
		add(new JLabel());
		add(btnOk, "split 2");
		add(btnCancel);
		
		if(knjiga != null) {
			txtNaslov.setText(knjiga.getNaslov());
			txtOriginal.setText(knjiga.getOriginal());
			txtPisac.setText(knjiga.getPisac());
			txtGodinaObj.setText(knjiga.getGodinaObjavljivanja());
			txtOpis.setText(knjiga.getOpis());
			cbJezik.setSelectedItem(knjiga.getJezikk());
			cbZanr.setSelectedItem(knjiga.getZanr().getOpis());;
		}
	}

	public void initActions() {
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				KnjigaDodavanje.this.dispose();
				KnjigaDodavanje.this.setVisible(false);
			}
		});
		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String naslov = txtNaslov.getText().trim();
				String original = txtOriginal.getText().trim();
				String pisac = txtPisac.getText().trim();
				String godinaObjavljivanja = txtGodinaObj.getText().trim();
				String opis = txtOpis.getText().trim();
				int zanrId = cbZanr.getSelectedIndex();
				String jezikString=cbJezik.getSelectedItem().toString();
				Jezik jezik=Jezik.valueOf(jezikString);
				Zanr zanr=biblioteka.pronadjiZanr(Integer.toString(zanrId));
				
				if(naslov.equals("") || original.equals("") || pisac.equals("") || godinaObjavljivanja.equals("") || opis.equals("") ) {
					JOptionPane.showMessageDialog(null, "Niste uneli sve podatke za dodavanje.", "Greska", JOptionPane.WARNING_MESSAGE);
				}
				else {
				
					if(knjiga ==null) {
						String id= Integer.toString(biblioteka.getKnjige().size());	
						Knjiga novaKnjiga=new Knjiga(id, naslov, original, pisac, godinaObjavljivanja, opis, jezik, zanr, false);
						biblioteka.getKnjige().add(novaKnjiga);
					}
					else {
						knjiga.setNaslov(naslov);
						knjiga.setGodinaObjavljivanja(godinaObjavljivanja);
						knjiga.setOriginal(original);
						knjiga.setOpis(opis);
						knjiga.setZanr(zanr);
						knjiga.setJezikk(jezik);
						knjiga.setPisac(pisac);
					}
					try {
						biblioteka.sacuvajKnjige();
						KnjigaDodavanje.this.setVisible(false);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					
	
				}
			}
		});
		
		
		

		
		
}}