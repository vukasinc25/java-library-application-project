package swingPrikaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import biblioteka.Biblioteka;
import swingMain.BibliotekaIzmena;

public class BibliotekaPrikaz extends JFrame{
	private JToolBar mainToolbar = new JToolBar();
	private JButton btnEdit = new JButton("Izmeni");
	ImageIcon ikonica = new ImageIcon("src/slike/knjiga.png");
	
	
	private DefaultTableModel tableModel;
	private JTable bibliotekaTabela;
	private Biblioteka biblioteka;
	public BibliotekaPrikaz(Biblioteka biblioteka) {
		this.biblioteka = biblioteka;
		setTitle("Biblioteka");
		setSize(600, 300);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().add(mainToolbar, BorderLayout.SOUTH);		
		mainToolbar.setBackground(Color.LIGHT_GRAY);
		btnEdit.setBackground(Color.LIGHT_GRAY);
		mainToolbar.add(btnEdit);
		setIconImage(ikonica.getImage());
		initGUI();
		initActions();

	
		}
	
	private void initGUI() {
		mainToolbar.add(btnEdit);
		add(mainToolbar, BorderLayout.SOUTH);
		String[] zaglavlja = new String[] {"Naziv", "Adresa", "Telefon", "Id", "Radno vreme od", "Radno vreme do"};
		Object[][] sadrzaj = new Object[1][zaglavlja.length];

			
		sadrzaj[0][0] = biblioteka.getNaziv();
		sadrzaj[0][1] = biblioteka.getAdresa();
		sadrzaj[0][2] = biblioteka.getTelefon();
		sadrzaj[0][3] = biblioteka.getId();
		sadrzaj[0][4] = biblioteka.getRadnoVremeOd();
		sadrzaj[0][5] = biblioteka.getRadnoVremeDo();
		
		tableModel = new DefaultTableModel(sadrzaj, zaglavlja);
		bibliotekaTabela = new JTable(tableModel);
		
		bibliotekaTabela.setRowSelectionAllowed(true);
		bibliotekaTabela.setColumnSelectionAllowed(false);
		bibliotekaTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		bibliotekaTabela.setDefaultEditor(Object.class, null);
		bibliotekaTabela.getTableHeader().setReorderingAllowed(false);
		
		JScrollPane scrollPane = new JScrollPane(bibliotekaTabela);
		add(scrollPane, BorderLayout.CENTER);
			
		}
	
	private void initActions() {
		btnEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					BibliotekaIzmena editBiblioteka = new BibliotekaIzmena(biblioteka);
					editBiblioteka.setVisible(true);
					BibliotekaPrikaz.this.dispose();
					BibliotekaPrikaz.this.setVisible(false);
			}

		});
	}	
}