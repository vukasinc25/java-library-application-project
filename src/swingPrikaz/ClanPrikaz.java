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
import ljudi.Clan;
import ljudi.Zaposleni;
import swingDodavanje.ClanDodavanje;
import swingIzmena.ClanIzmena;


public class ClanPrikaz extends JFrame{
	private JToolBar mainToolbar = new JToolBar();
	private Zaposleni zaposleni;
	private DefaultTableModel tableModel;
	private JTable clanoviTabela;
	
	private Biblioteka biblioteka;
	private Clan clan;
	
	private final JButton btnDodaj = new JButton("Dodaj Clana");
	private final JButton btnIzmeni = new JButton("Izmeni Clana");
	private final JButton btnIzbrisi = new JButton("Izbrisi Clana");

	public ClanPrikaz (Biblioteka biblioteka,Zaposleni zaposleni) {
		this.biblioteka = biblioteka;
		this.zaposleni = zaposleni;
		setTitle("Clanovi");
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

		
		String[] zaglavlja = new String[] {"Id", "Ime", "Prezime", "JMBG", "POL", "BR.ClanskeKarte", "DatumPoslednje uplate", "BrojMEseciClanarine","TipClanarine"};
		Object[][] sadrzaj = new Object[biblioteka.sviNeobrisaniClanoviBiblioteke().size()][zaglavlja.length];
		
		for(int i=0; i<biblioteka.sviNeobrisaniClanoviBiblioteke().size(); i++) {
			Clan clan = biblioteka.sviNeobrisaniClanoviBiblioteke().get(i);
//			Knjiga knjiga = biblioteka.pronadjiDisk(clan);
			sadrzaj[i][0] = clan.getId();
			sadrzaj[i][1] = clan.getIme();
			sadrzaj[i][2] = clan.getPrezime();
			sadrzaj[i][3] = clan.getJmbg();
			sadrzaj[i][4] = clan.getPol();
			sadrzaj[i][5] = clan.getBrClanskeKarte();
			sadrzaj[i][6] = clan.getDatumUplate();
			sadrzaj[i][7] = clan.getUplacenoMeseci();
			sadrzaj[i][8] = clan.getTipClanarine().getId();
//			sadrzaj[i][2] = disk == null ? "--" : disk.getNaziv();
		}
		
		tableModel = new DefaultTableModel(sadrzaj, zaglavlja);
		clanoviTabela = new JTable(tableModel);
		
		clanoviTabela.setRowSelectionAllowed(true);
		clanoviTabela.setColumnSelectionAllowed(false);
		clanoviTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		clanoviTabela.setDefaultEditor(Object.class, null);
		clanoviTabela.getTableHeader().setReorderingAllowed(false);
		
		JScrollPane scrollPane = new JScrollPane(clanoviTabela);
		add(scrollPane, BorderLayout.CENTER);
		
	}

	private void initActions() {
		btnIzbrisi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = clanoviTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.","Greska",JOptionPane.WARNING_MESSAGE);
				}
				else {
					int id = Integer.parseInt(tableModel.getValueAt(red, 0).toString());
					String naziv = tableModel.getValueAt(red, 1).toString();
					
					int izbor = JOptionPane.showConfirmDialog(null, "Da li ste sigurni da zelite da obrisete clana?",naziv + "- Potvrda brisanja",JOptionPane.YES_NO_OPTION);
					if(izbor == JOptionPane.YES_NO_OPTION) {
						Clan c = biblioteka.getClanBiblioteke().get(id);
						c.setObrisan(true);
						System.out.println(biblioteka.getClanBiblioteke().toString());
						try {
							biblioteka.sacuvajClanove();
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
				ClanDodavanje da = new ClanDodavanje(biblioteka, clan);
				da.setVisible(true);
				ClanPrikaz.this.dispose();
				ClanPrikaz.this.setVisible(false);
			}
		});
		
		btnIzmeni.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = clanoviTabela.getSelectedRow();
				if(row == -1) {
					JOptionPane.showMessageDialog(null, "Morate da izberete red koji zelite da promenite","Greska",JOptionPane.WARNING_MESSAGE);
				}
				String id = tableModel.getValueAt(row, 0).toString();
				Clan clan = biblioteka.pronadjiClana(id);
				ClanIzmena edit = new ClanIzmena(biblioteka, clan);
				edit.setVisible(true);
			}
		});
	}
}