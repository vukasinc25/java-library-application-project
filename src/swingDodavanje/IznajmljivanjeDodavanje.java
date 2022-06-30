package swingDodavanje;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import biblioteka.Biblioteka;
import biblioteka.Iznajmljivanje;
import biblioteka.Primerak;
import ljudi.Clan;
import ljudi.Zaposleni;
import net.miginfocom.swing.MigLayout;

public class IznajmljivanjeDodavanje  extends JFrame{

	private JLabel lblDatumIznajmljivanja=new JLabel("Datum iznajmljivanja: ");
	private JTextField txtDatumIznajmljivanja=new JTextField(20);
	private JLabel lblDatumVracanja=new JLabel("Datum vracanja: ");
	private JTextField txtDatumVracanja=new JTextField(20);
	private JLabel lblIzdao=new JLabel("Izdao: ");
	private JTextField txtIzdao=new JTextField();
	private JLabel lblIznajmio=new JLabel("Iznajmio: ");
	private JComboBox cbIznajmio=new JComboBox();
	private JLabel lblPrimerak=new JLabel("Primerak: ");
	
	DefaultListModel model = new DefaultListModel();
	private JList listPrimerak = new JList(model);
	ArrayList<Primerak> primerakA = new ArrayList<Primerak>();
	
	private JComboBox cbPrimerak=new JComboBox();
	private JButton btnOk = new JButton("OK");
	private JButton btnCancel = new JButton("Cancel");
	private Biblioteka biblioteka;
	private Iznajmljivanje iznajmljivanje;
	private Zaposleni prijavljeni;
	
	
	public IznajmljivanjeDodavanje(Biblioteka biblioteka,Zaposleni prijavljeni) {
		this.biblioteka=biblioteka;
		this.prijavljeni=prijavljeni;
		setSize(1000,1000);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		initGUI();
		initActions();
		pack();
	}
	public IznajmljivanjeDodavanje(Biblioteka biblioteka,Zaposleni prijavljeni, Iznajmljivanje iznajmljivanje) {
		this.biblioteka=biblioteka;	
		this.prijavljeni=prijavljeni;
		this.iznajmljivanje=iznajmljivanje;
		setTitle("Dodavanje");
		setSize(1000,1000);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		initGUI();
		initActions();
		pack();
	}
	public void initGUI() {
		ArrayList<Clan> clanovi=biblioteka.sviNeobrisaniClanovi();
		for(Clan clan:clanovi) {
			cbIznajmio.addItem(clan.getBrojClanskeKarte());
		}
//		ArrayList<Primerak> primerci=biblioteka.sviNeobrisaniPrimerci();
//		for(Primerak primerak:primerci) {
//			cbPrimerak.addItem(primerak.getId());
//		}
		ArrayList<Primerak> primerci=biblioteka.sviNeobrisaniPrimerci();
		for(Primerak primerak:primerci) {
			model.addElement(primerak.getId());
		}
		txtIzdao.setText(prijavljeni.getKorisnickoIme());
	
		MigLayout mig = new MigLayout("wrap 2", "[][]", "[]10[][]10[]");
		setLayout(mig);
		
		
		add(lblPrimerak);
		add(listPrimerak);
		//add(cbPrimerak);
		add(lblIzdao);
		add(txtIzdao);
		add(lblIznajmio);
		add(cbIznajmio);
		add(lblDatumIznajmljivanja);
		add(txtDatumIznajmljivanja);
		add(lblDatumVracanja);
		add(txtDatumVracanja);
		add(btnOk);
		add(btnCancel);
		
		txtIzdao.setText(prijavljeni.getKorisnickoIme());
		//ArrayList<Primerak> primerak = new ArrayList<Primerak>();
		if(iznajmljivanje!=null) {
			
			
			//cbPrimerak.setSelectedItem(iznajmljivanje.getPrimerak().getId());
			cbIznajmio.setSelectedItem(iznajmljivanje.getClan().getBrojClanskeKarte());
			String datumIznajmljivanja =iznajmljivanje.getDatumIznajmljivanja().toString();
			txtDatumIznajmljivanja.setText(datumIznajmljivanja);
			String datumVracanja =iznajmljivanje.getDatumVracanja().toString();
			txtDatumVracanja.setText(datumVracanja);
			txtIzdao.setText(iznajmljivanje.getZaposleni().getKorisnickoIme());
		}
		txtIzdao.setEnabled(false);
	}
	public void initActions() {
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				IznajmljivanjeDodavanje.this.dispose();
				IznajmljivanjeDodavanje.this.setVisible(false);
			}
		});
		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				//int primerakId=cbPrimerak.getSelectedIndex();
				int[] indeks = listPrimerak.getSelectedIndices();
				//System.out.println(indeks);
				//int counter = 0;
				for (int i:indeks) {
					primerakA.add(biblioteka.sviNeobrisaniPrimerci().get(i));
					//counter += 1;
				}
				System.out.println(primerakA);
				Primerak primerak = primerakA.get(0);
				
				int iznajmioId=cbIznajmio.getSelectedIndex();
				Clan clan=biblioteka.sviNeobrisaniClanovi().get(iznajmioId);
				DateTimeFormatter dateFormatter= DateTimeFormatter.ofPattern("yyyy-MM-dd");
				String datumIznajmljivanjaString=txtDatumIznajmljivanja.getText().trim();
				LocalDate datumIznajmljivanja=LocalDate.parse(datumIznajmljivanjaString);
				String datumVracanjaString=txtDatumVracanja.getText().trim();
				LocalDate datumVracanja=LocalDate.parse(datumVracanjaString);

				if(datumIznajmljivanja.equals("") || datumVracanja.equals("")) {
					JOptionPane.showMessageDialog(null, "Niste uneli sve podatke za dodavanje.", "Greska", JOptionPane.WARNING_MESSAGE);
				}
				else {
					if(iznajmljivanje==null) {
						String id= Integer.toString(biblioteka.getIznajmljivanja().size());	
						Iznajmljivanje novoIznajmljivanje=new Iznajmljivanje(id,primerak,clan,datumIznajmljivanja,datumVracanja,prijavljeni,false);
						biblioteka.getIznajmljivanja().add(novoIznajmljivanje);	
						}
					else {
						iznajmljivanje.setClan(clan);
						iznajmljivanje.setDatumIznajmljivanja(datumIznajmljivanja);
						iznajmljivanje.setDatumVracanja(datumVracanja);
						iznajmljivanje.setPrimerak(primerak);
					}
					try {
						biblioteka.sacuvajIznajmljivanje();
						IznajmljivanjeDodavanje.this.setVisible(false);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
	}}
					

