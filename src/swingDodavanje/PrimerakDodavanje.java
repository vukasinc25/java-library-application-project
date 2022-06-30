package swingDodavanje;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import biblioteka.Biblioteka;
import biblioteka.Knjiga;
import biblioteka.Primerak;
import enumeracije.Jezik;
import enumeracije.Korice;
import net.miginfocom.swing.MigLayout;

public class PrimerakDodavanje extends JFrame{
	private Jezik[] jezici=Jezik.values();
	private Korice[] korice=Korice.values();
	private JLabel lblKnjiga= new JLabel("Knjiga: ");
	private JComboBox cbKnjiga=new JComboBox();
	private JLabel lblBrStrana=new JLabel("Broj strana: ");
	private JTextField txtBrStrana=new JTextField(20);
	private JLabel lblGodStampanja=new JLabel("Godina stampanja: ");
	private JTextField txtGodStampanja=new JTextField(20);
	private JLabel lblKorice= new JLabel("Korice: ");
	private JComboBox cbKorice=new JComboBox(korice);
	private JLabel lblJezik= new JLabel("Jezik: ");
	private JComboBox cbJezik=new JComboBox(jezici);
	private JLabel lblIznajmljena=new JLabel("Iznajmljena: ");
	private JCheckBox chbIznajmljena=new JCheckBox();
	private JButton btnOk = new JButton("OK");
	private JButton btnCancel = new JButton("Cancel");
	
	private Biblioteka biblioteka;
	private Primerak primerak;
	
	public PrimerakDodavanje(Biblioteka biblioteka) {
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
	public PrimerakDodavanje(Biblioteka biblioteka, Primerak primerak) {
		this.biblioteka=biblioteka;	
		this.primerak=primerak;
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
		ArrayList<Knjiga> knjige=biblioteka.sveNeobrisaneKnjige();
		for (Knjiga knjiga:knjige) {
			cbKnjiga.addItem(knjiga.getNaslov());
		}

		MigLayout mig = new MigLayout("wrap 2", "[][]", "[]10[][]10[]");
		setLayout(mig);
		
		add(lblKnjiga);
		add(cbKnjiga);
		add(lblBrStrana);
		add(txtBrStrana);
		add(lblGodStampanja);
		add(txtGodStampanja);
		add(lblKorice);
		add(cbKorice);
		add(lblJezik);
		add(cbJezik);
		add(lblIznajmljena);
		add(chbIznajmljena);
		
		add(btnOk);
		add(btnCancel);
		
		if(primerak!=null) {
			cbKnjiga.setSelectedItem(primerak.getKnjiga().getNaslov());
			txtBrStrana.setText(primerak.getBrStrana());
			txtGodStampanja.setText(primerak.getGodStampanja());
			cbKorice.setSelectedItem(primerak.getKorica());
			cbJezik.setSelectedItem(primerak.getJezikk());
			chbIznajmljena.setSelected(primerak.getIznajmljena());
		}
	}
	public void initActions() {
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PrimerakDodavanje.this.dispose();
				PrimerakDodavanje.this.setVisible(false);
			}
		});
		
		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int knjigaId = cbKnjiga.getSelectedIndex();
				String brStrana = txtBrStrana.getText().trim();
				String godStampanja = txtGodStampanja.getText().trim();
				String jezikString=cbJezik.getSelectedItem().toString();
				Jezik jezik=Jezik.valueOf(jezikString);
				String koricaString=cbKorice.getSelectedItem().toString();
				Korice korica=Korice.valueOf(koricaString);
				Knjiga knjiga=biblioteka.sveNeobrisaneKnjige().get(knjigaId);
				boolean isSelected = chbIznajmljena.isSelected();
				if(brStrana.equals("") || godStampanja.equals("") ) {
					JOptionPane.showMessageDialog(null, "Niste uneli sve podatke za dodavanje.", "Greska", JOptionPane.WARNING_MESSAGE);
				}
				else {
					if(primerak ==null) {
						String id= Integer.toString(biblioteka.getPrimerci().size());	
						Primerak noviPrimerak=new Primerak(id,knjiga, brStrana, godStampanja, korica, jezik, isSelected, false);
						biblioteka.getPrimerci().add(noviPrimerak);
					}
					else {
						primerak.setIznajmljena(isSelected);
						primerak.setBrStrana(brStrana);
						primerak.setGodStampanja(godStampanja);
						primerak.setJezikk(jezik);
						primerak.setKnjiga(knjiga);
						primerak.setKorica(korica);
					}
					try {
						biblioteka.sacuvajPrimerke();
						PrimerakDodavanje.this.setVisible(false);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			
					
				}
				}
				
		});
		
	}}
