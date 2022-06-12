package swingPrikaz;

import java.awt.BorderLayout;
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
	private JButton btnAdd = new JButton();
	private JButton btnEdit = new JButton();
	private JButton btnDelete = new JButton();
	private Zaposleni zaposleni;
	
	private DefaultTableModel tableModel;
	private JTable izdavanjeknjigaTabela;
	
	private Biblioteka biblioteka;

	public IznajmljivanjePrikaz(Biblioteka biblioteka,Zaposleni zaposleni) {
		this.biblioteka = biblioteka;
		this.zaposleni = zaposleni;
		setTitle("Kompozicije");
		setSize(300, 300);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		initGUI();
		initActions();
	}

	private void initGUI() {
		ImageIcon addIcon = new ImageIcon(getClass().getResource("/slike/add.gif"));
		btnAdd.setIcon(addIcon);
		ImageIcon editIcon = new ImageIcon(getClass().getResource("/slike/edit.gif"));
		btnEdit.setIcon(editIcon);
		ImageIcon deleteIcon = new ImageIcon(getClass().getResource("/slike/remove.gif"));
		btnDelete.setIcon(deleteIcon);
		
		mainToolbar.add(btnAdd);
		mainToolbar.add(btnEdit);
		mainToolbar.add(btnDelete);
		add(mainToolbar, BorderLayout.NORTH);

//		LocalDate datumIznajmljivanja, LocalDate datumVracanja, Zaposleni zaposleni,
//		ClanBiblioteke clan, ArrayList<PrimerakKnjige> primerak,boolean jeObrisan
		
		
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
