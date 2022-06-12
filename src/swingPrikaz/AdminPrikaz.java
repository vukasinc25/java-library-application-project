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
import ljudi.Zaposleni;
//import projekatObjektno.ClanBiblioteke;
//import projekatObjektno.EmnumPol;
import swingDodavanje.AdminDodavanje;
import java.awt.Color;

public class AdminPrikaz extends JFrame{
	private JToolBar mainToolbar = new JToolBar();
	private Zaposleni zaposleni;
	ImageIcon ikonica = new ImageIcon("src/slike/knjiga.png");
	
	private DefaultTableModel tableModel;
	private JTable administratoriTabela;
	
	private Biblioteka biblioteka;
	private Administrator admin;
	
	private final JButton btnDodaj = new JButton("Dodaj Admina");
	private final JButton btnIzmeni = new JButton("Izmeni Admina");
	private final JButton btnIzbrisi = new JButton("Izbrisi Admina");

	public AdminPrikaz (Biblioteka biblioteka,Zaposleni zaposleni) {
		this.biblioteka = biblioteka;
		this.zaposleni = zaposleni;
		setTitle("Administratori");
		setSize(600,400);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setIconImage(ikonica.getImage());
		initGui();
		initActions();
	}
	private void initGui() {
		mainToolbar.setBackground(Color.LIGHT_GRAY);
		
		getContentPane().add(mainToolbar, BorderLayout.SOUTH);		
		btnDodaj.setBackground(Color.LIGHT_GRAY);
		
		mainToolbar.add(btnDodaj);
		btnIzmeni.setBackground(Color.LIGHT_GRAY);
		mainToolbar.add(btnIzmeni);
		btnIzbrisi.setBackground(Color.LIGHT_GRAY);
		mainToolbar.add(btnIzbrisi);
			
		String[] zaglavlja = new String[] {"ID", "Adresa", "Ime", "Prezime", "JMBG", "Pol", "Lozinka", "KorisnicoIme","Plata"};
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
		getContentPane().add(scrollPane, BorderLayout.CENTER);

	}
	private void initActions() {
		btnIzmeni.addActionListener(new ActionListener() {
			
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
		btnIzbrisi.addActionListener(new ActionListener() {
			
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
		btnDodaj.addActionListener(new ActionListener() {
			
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
