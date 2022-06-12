package swingPrikaz;

import java.awt.BorderLayout;
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

import ljudi.Administrator;
import biblioteka.Biblioteka;
import swingIzmena.AdminIzmena;
import ljudi.Clan;
import ljudi.Zaposleni;
//import projekatObjektno.ClanBiblioteke;
//import projekatObjektno.EmnumPol;
import swingDodavanje.AdminDodavanje;

public class AdminPrikaz extends JFrame{
	private JToolBar mainToolbar = new JToolBar();
	private JButton btnAdd = new JButton();
	private JButton btnEdit = new JButton();
	private JButton btnDelete = new JButton();
	private Zaposleni zaposleni;
	
	private DefaultTableModel tableModel;
	private JTable administratoriTabela;
	
	private Biblioteka biblioteka;
	private Administrator admin;

	public AdminPrikaz (Biblioteka biblioteka,Zaposleni zaposleni) {
		this.biblioteka = biblioteka;
		this.zaposleni = zaposleni;
		setTitle("Administratori:");
		setSize(300, 300);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		initGui();
		initActions();
	}
	private void initGui() {
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
		
//		String id, String ime, String prezime, String jMBG, String adresa, EmnumPol pol,
//		String korisnickaSifra, String korisnickoIme,double plata,boolean jeObrisan
		
		
		String[] zaglavlja = new String[] {"Id", "Ime", "Prezime", "JMBG", "Adresa", "POL", "KorisnickaSifra", "KorisnicoIme","Plata"};
		Object[][] sadrzaj = new Object[biblioteka.sviNeobrisaniAdministatori().size()][zaglavlja.length];
		
		for(int i=0; i<biblioteka.sviNeobrisaniAdministatori().size(); i++) {
			Administrator clan = biblioteka.sviNeobrisaniAdministatori().get(i);
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
		administratoriTabela = new JTable(tableModel);
		
		administratoriTabela.setRowSelectionAllowed(true);
		administratoriTabela.setColumnSelectionAllowed(false);
		administratoriTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		administratoriTabela.setDefaultEditor(Object.class, null);
		administratoriTabela.getTableHeader().setReorderingAllowed(false);
		
		JScrollPane scrollPane = new JScrollPane(administratoriTabela);
		add(scrollPane, BorderLayout.CENTER);

	}
	private void initActions() {
		btnEdit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = administratoriTabela.getSelectedRow();
				if(row == -1) {
					JOptionPane.showMessageDialog(null, "Morate da izaberete red koji zelite da promenite","Greska",JOptionPane.WARNING_MESSAGE);
				}
				else {
					String id = tableModel.getValueAt(row, 0).toString();
					Administrator admin = biblioteka.pronadjiAdmina(id);
					System.out.println(admin);
					AdminIzmena edit = new AdminIzmena(biblioteka,admin);
					edit.setVisible(true);
				}
			}
		});
		btnDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = administratoriTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.","Greska",JOptionPane.WARNING_MESSAGE);
				}
				else {
					int id = Integer.parseInt(tableModel.getValueAt(red, 0).toString());
					String naziv = tableModel.getValueAt(red, 1).toString();
					
					int izbor = JOptionPane.showConfirmDialog(null, "Da li ste sigurni da zelite da obrisete clana?",naziv + "- Potvrda brisanja",JOptionPane.YES_NO_OPTION);
					if(izbor == JOptionPane.YES_NO_OPTION) {
						Administrator c = biblioteka.getAdmin().get(id);
						c.setObrisan(true);
						System.out.println(biblioteka.getAdmin().toString());
						try {
							biblioteka.sacuvajAdministatore();
						}
						catch(IOException e1) {
							e1.printStackTrace();
						}
						tableModel.removeRow(red);
					}
				}
				
			}
		});
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AdminDodavanje da = new AdminDodavanje(biblioteka);
				da.setVisible(true);
				AdminPrikaz.this.dispose();
				AdminPrikaz.this.setVisible(false);
			}
		});
		
	}
	
}
