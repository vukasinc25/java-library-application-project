package swingPrikaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import biblioteka.Biblioteka;
import biblioteka.Iznajmljivanje;
import ljudi.Zaposleni;

public class IznajmljivanjePrikaz extends JFrame{
	private JToolBar mainToolbar = new JToolBar();
	private final JButton btnDodaj = new JButton("Dodaj Bibliotekara");
	private final JButton btnIzmeni = new JButton("Izmeni Bibliotekara");
	private final JButton btnIzbrisi = new JButton("Izbrisi Bibliotekara");
	private Zaposleni zaposleni;
	
	private DefaultTableModel tableModel;
	private JTable izdavanjeknjigaTabela;
	ImageIcon ikonica = new ImageIcon("src/slike/knjiga.png");
	
	private Biblioteka biblioteka;

	public IznajmljivanjePrikaz(Biblioteka biblioteka,Zaposleni zaposleni) {
		this.biblioteka = biblioteka;
		this.zaposleni = zaposleni;
		setTitle("Kompozicije");
		setSize(600,400);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		initGUI();
		initActions();
	}

	private void initGUI() {
		getContentPane().add(mainToolbar, BorderLayout.SOUTH);
		mainToolbar.setBackground(Color.LIGHT_GRAY);
		btnDodaj.setBackground(Color.LIGHT_GRAY);
		btnIzmeni.setBackground(Color.LIGHT_GRAY);
		btnIzbrisi.setBackground(Color.LIGHT_GRAY);
		mainToolbar.add(btnDodaj);
		mainToolbar.add(btnIzmeni);
		mainToolbar.add(btnIzbrisi);
		setIconImage(ikonica.getImage());
		
		String[] zaglavlja = new String[] {"DatumIznajmljivanja", "DatumVracanja", "Zaposleni", "Clanovi", "Primerci"};
		Object[][] sadrzaj = new Object[biblioteka.svaNeobrisanaIzdavanja().size()][zaglavlja.length];
		
		for(int i=0; i<biblioteka.svaNeobrisanaIzdavanja().size(); i++) {
			Iznajmljivanje clan = biblioteka.svaNeobrisanaIzdavanja().get(i);
//			Knjiga knjiga = biblioteka.pronadjiDisk(clan);
			sadrzaj[i][0] = clan.getDatumIznajmljivanja();
			sadrzaj[i][1] = clan.getDatumVracanja();
			sadrzaj[i][2] = clan.getZaposleni().getId();
			sadrzaj[i][3] = clan.getClan().getId();
			sadrzaj[i][4] = clan.getPrimerak();
			
//			sadrzaj[i][2] = disk == null ? "--" : disk.getNaziv();
		}
		
		tableModel = new DefaultTableModel(sadrzaj, zaglavlja);
		izdavanjeknjigaTabela = new JTable(tableModel);
		
		izdavanjeknjigaTabela.setRowSelectionAllowed(true);
		izdavanjeknjigaTabela.setColumnSelectionAllowed(false);
		izdavanjeknjigaTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		izdavanjeknjigaTabela.setDefaultEditor(Object.class, null);
		izdavanjeknjigaTabela.getTableHeader().setReorderingAllowed(false);
		
		JScrollPane scrollPane = new JScrollPane(izdavanjeknjigaTabela);
		add(scrollPane, BorderLayout.CENTER);

	}

	private void initActions() {
		
	}
}
