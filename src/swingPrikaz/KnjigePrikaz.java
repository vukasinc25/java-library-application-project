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

import biblioteka.Biblioteka;
import biblioteka.Knjiga;
import ljudi.Zaposleni;
import swingDodavanje.KnjigaDodavanje;
import swingIzmena.KnjigaIzmena;

public class KnjigePrikaz extends JFrame{
	private JToolBar mainToolbar = new JToolBar();
	private JButton btnAdd = new JButton();
	private JButton btnEdit = new JButton();
	private JButton btnDelete = new JButton();
	private Zaposleni zaposleni;
	
	private DefaultTableModel tableModel;
	private JTable knjigeTabela;
	
	private Biblioteka biblioteka;
	private Knjiga knjiga;

	public KnjigePrikaz (Biblioteka biblioteka,Zaposleni zaposleni) {
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
		
		String[] zaglavlja = new String[] {"Id", "Naslov Knjige", "Pisac", "Godina Objavljivanja", "JezikOriginala", "Opis Knjige","ZanrKnjige"};
		Object[][] sadrzaj = new Object[biblioteka.sveNeobrisaneKnjige().size()][zaglavlja.length];
		
		for(int i=0; i<biblioteka.sveNeobrisaneKnjige().size(); i++) {
			Knjiga knjiga = biblioteka.sveNeobrisaneKnjige().get(i);
//			Knjiga knjiga = biblioteka.pronadjiDisk(knjiga);
			sadrzaj[i][0] = knjiga.getId();
			sadrzaj[i][1] = knjiga.getNaslov();
			sadrzaj[i][2] = knjiga.getPisac();
			sadrzaj[i][3] = knjiga.getGodinaObjavljivanja();
			sadrzaj[i][4] = knjiga.getJezikOriginala();
			sadrzaj[i][5] = knjiga.getOpisKnjige();
			sadrzaj[i][6] = knjiga.getZanr().getId();
//			sadrzaj[i][2] = disk == null ? "--" : disk.getNaziv();
		}
		
		tableModel = new DefaultTableModel(sadrzaj, zaglavlja);
		knjigeTabela = new JTable(tableModel);
		
		knjigeTabela.setRowSelectionAllowed(true);
		knjigeTabela.setColumnSelectionAllowed(false);
		knjigeTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		knjigeTabela.setDefaultEditor(Object.class, null);
		knjigeTabela.getTableHeader().setReorderingAllowed(false);
		
		JScrollPane scrollPane = new JScrollPane(knjigeTabela);
		add(scrollPane, BorderLayout.CENTER);
	}

	private void initActions() {
		btnDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = knjigeTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.","Greska",JOptionPane.WARNING_MESSAGE);
				}
				else {
					int id = Integer.parseInt(tableModel.getValueAt(red, 0).toString());
					String naziv = tableModel.getValueAt(red, 1).toString();
					
					int izbor = JOptionPane.showConfirmDialog(null, "Da li ste sigurni da zelite da obrisete clana?",naziv + "- Potvrda brisanja",JOptionPane.YES_NO_OPTION);
					if(izbor == JOptionPane.YES_NO_OPTION) {
						Knjiga c = biblioteka.getKnjige().get(id);
						c.setObrisan(true);
						System.out.println(biblioteka.getKnjige().toString());
						try {
							biblioteka.sacuvajKnjige();
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
				KnjigaDodavanje da = new KnjigaDodavanje(biblioteka);
				da.setVisible(true);
				KnjigePrikaz.this.dispose();
				KnjigePrikaz.this.setVisible(false);
			}
		});
		btnEdit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = knjigeTabela.getSelectedRow();
				if(row == -1) {
					JOptionPane.showMessageDialog(null, "Morate da izaberete red koji zelite da promenite","Greska",JOptionPane.WARNING_MESSAGE);
				}
				else {
					String id = tableModel.getValueAt(row, 0).toString();
					Knjiga knjiga = biblioteka.pronadjiKnjigu(id);
					KnjigaIzmena edit = new KnjigaIzmena(biblioteka,knjiga);
					edit.setVisible(true);
				}
			}
		});
		
	}
}
