package swingPrikaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import biblioteka.Biblioteka;
import ljudi.Bibliotekar;
import ljudi.Zaposleni;
import swingDodavanje.BibliotekarDodavanje;
import swingIzmena.BibliotekarIzmena;
import javax.swing.SwingConstants;

public class BibliotekarPrikaz extends JFrame{
	private JToolBar mainToolbar = new JToolBar();
	private Zaposleni zaposleni;
	
	private DefaultTableModel tableModel;
	private JTable bibiliotekariTabela;
	
	private Biblioteka biblioteka;
	private Bibliotekar bibliotekar;
	private final JButton btnDodaj = new JButton("Dodaj Bibliotekara");
	private final JButton btnIzmeni = new JButton("Izmeni Bibliotekara");
	private final JButton btnIzbrisi = new JButton("Izbrisi Bibliotekara");
	ImageIcon ikonica = new ImageIcon("src/slike/knjiga.png");

	public BibliotekarPrikaz (Biblioteka biblioteka,Zaposleni zaposleni) {
		this.biblioteka = biblioteka;
		this.zaposleni = zaposleni;
		setTitle("Bibliotekari");
		setSize(600, 400);
		setIconImage(ikonica.getImage());
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
		
		String[] zaglavlja = new String[] {"JMBG", "Adresa", "ID", "Ime", "Prezime", "Pol", "Lozinka", "KorisnicoIme","Plata"};
		Object[][] sadrzaj = new Object[biblioteka.sviNeobrisaniBibliotekari().size()][zaglavlja.length];
		
		for(int i=0; i<biblioteka.sviNeobrisaniBibliotekari().size(); i++) {
			Bibliotekar clan = biblioteka.sviNeobrisaniBibliotekari().get(i);
//			Knjiga knjiga = biblioteka.pronadjiDisk(clan);
			sadrzaj[i][0] = clan.getId();
			sadrzaj[i][1] = clan.getIme();
			sadrzaj[i][2] = clan.getPrezime();
			sadrzaj[i][3] = clan.getJmbg();
			sadrzaj[i][4] = clan.getAdresa();
			sadrzaj[i][5] = clan.getPol();
			sadrzaj[i][6] = clan.getLozinka();
			sadrzaj[i][7] = clan.getKorisnickoIme();
			sadrzaj[i][8] = clan.getPlata();
//			sadrzaj[i][2] = disk == null ? "--" : disk.getNaziv();
		}
		
		tableModel = new DefaultTableModel(sadrzaj, zaglavlja);
		bibiliotekariTabela = new JTable(tableModel);
		
		bibiliotekariTabela.setRowSelectionAllowed(true);
		bibiliotekariTabela.setColumnSelectionAllowed(false);
		bibiliotekariTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		bibiliotekariTabela.setDefaultEditor(Object.class, null);
		bibiliotekariTabela.getTableHeader().setReorderingAllowed(false);
		
		JScrollPane scrollPane = new JScrollPane(bibiliotekariTabela);
		getContentPane().add(scrollPane, BorderLayout.CENTER);
	}

	private void initActions() {
		btnIzbrisi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = bibiliotekariTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.","Greska",JOptionPane.WARNING_MESSAGE);
				}
				else {
					int id = Integer.parseInt(tableModel.getValueAt(red, 0).toString());
					String naziv = tableModel.getValueAt(red, 1).toString();
					
					int izbor = JOptionPane.showConfirmDialog(null, "Da li ste sigurni da zelite da obrisete clana?",naziv + "- Potvrda brisanja",JOptionPane.YES_NO_OPTION);
					if(izbor == JOptionPane.YES_NO_OPTION) {
						Bibliotekar c = biblioteka.getBibliotekar().get(id);
						c.setObrisan(true);
						System.out.println(biblioteka.getBibliotekar().toString());
						try {
							biblioteka.sacuvajBibliotekre();
						}
						catch(IOException e1) {
							e1.printStackTrace();
						}
						tableModel.removeRow(red);
					}
				}
			}
		});
		
		btnDodaj.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				BibliotekarDodavanje da = new BibliotekarDodavanje(biblioteka, bibliotekar);
				da.setVisible(true);
				BibliotekarPrikaz.this.dispose();
				BibliotekarPrikaz.this.setVisible(false);
			}
		});
		
		btnIzmeni.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = bibiliotekariTabela.getSelectedRow();
				if(row == -1) {
					JOptionPane.showMessageDialog(null, "Morate da izaberete red koji zelite da proemnite","Greska",JOptionPane.WARNING_MESSAGE);
				}
				String id = tableModel.getValueAt(row, 0).toString();
				Bibliotekar bibliotekar = biblioteka.pronadjiBibliotekara(id);
				System.out.println(bibliotekar);
				BibliotekarIzmena edit = new BibliotekarIzmena(biblioteka,bibliotekar);
				edit.setVisible(true);
			}
		});
	}
}
