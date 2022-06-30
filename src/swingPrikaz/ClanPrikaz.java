package swingPrikaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

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
import swingDodavanje.ClanDodavanje;

public class ClanPrikaz extends JFrame {
	private JToolBar mainToolbar = new JToolBar();
	private JButton btnAdd = new JButton("Dodaj");
	private JButton btnEdit = new JButton("Izmeni");
	private JButton btnDelete = new JButton("Obrisi");
	ImageIcon ikonica = new ImageIcon("src/slike/knjiga.png");
	
	private DefaultTableModel tableModel;
	private JTable clanoviTabela;
	 
	private Biblioteka biblioteka;
	
	public ClanPrikaz(Biblioteka biblioteka) {
		this.biblioteka = biblioteka;
		setTitle("Clanovi");
		setSize(600, 300);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		try {
			biblioteka.proveriAktivnost();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		getContentPane().add(mainToolbar, BorderLayout.SOUTH);		
		mainToolbar.setBackground(Color.LIGHT_GRAY);
		btnAdd.setBackground(Color.LIGHT_GRAY);
		btnEdit.setBackground(Color.LIGHT_GRAY);
		btnDelete.setBackground(Color.LIGHT_GRAY);
		mainToolbar.add(btnAdd);
		mainToolbar.add(btnEdit);
		mainToolbar.add(btnDelete);
		setIconImage(ikonica.getImage());
		initGUI();
		initActions();
		
		}
	private void initGUI() {
		mainToolbar.add(btnAdd);
		mainToolbar.add(btnEdit);		 
		mainToolbar.add(btnDelete);		
		add(mainToolbar, BorderLayout.SOUTH);
		
		ArrayList<Clan>neobrisaniClanovi=biblioteka.sviNeobrisaniClanovi();
		String[] zaglavlja = new String[] {"Broj clanske karte", "Ime", "Prezime", "Status"};
		Object[][] sadrzaj = new Object[neobrisaniClanovi.size()][zaglavlja.length];
		for(int i=0; i<neobrisaniClanovi.size(); i++) {
			
			Clan clan = neobrisaniClanovi.get(i);		
			sadrzaj[i][0] = clan.getBrojClanskeKarte();
			sadrzaj[i][1] = clan.getIme();
			sadrzaj[i][2] = clan.getPrezime();
			sadrzaj[i][3] = clan.getAktivnost();

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
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = clanoviTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					int id =Integer.parseInt(tableModel.getValueAt(red, 0).toString());
					String naziv = tableModel.getValueAt(red, 1).toString();
					
					int izbor = JOptionPane.showConfirmDialog(null, 
							"Da li ste sigurni da zelite da obrisete clana?", 
							naziv + " - Porvrda brisanja", JOptionPane.YES_NO_OPTION);
					if(izbor == JOptionPane.YES_OPTION) {
						Clan c =biblioteka.getClanovi().get(id);
						c.setObrisan(true);
						System.out.println(biblioteka.getClanovi().toString());
						try {
							biblioteka.sacuvajClanove();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
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
				ClanDodavanje clanDodavanje = new ClanDodavanje(biblioteka);
				clanDodavanje.setVisible(true);
				ClanPrikaz.this.dispose();
				ClanPrikaz.this.setVisible(false);
			}
		});
		
		btnEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = clanoviTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					String id = tableModel.getValueAt(red, 0).toString();
					Clan clan = biblioteka.pronadjiClana(id);
					ClanDodavanje editClanovi = new ClanDodavanje(biblioteka,clan );
					editClanovi.setVisible(true);
					ClanPrikaz.this.dispose();
					ClanPrikaz.this.setVisible(false);
				}
			}
		});
	}

}

