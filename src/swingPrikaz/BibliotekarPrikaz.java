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
import ljudi.Bibliotekar;
import swingDodavanje.BibliotekarDodavanje;


public class BibliotekarPrikaz extends JFrame {
	private JToolBar mainToolbar = new JToolBar();
	private JButton btnAdd = new JButton("Dodaj");
	private JButton btnEdit = new JButton("Izmeni");
	private JButton btnDelete = new JButton("Obrisi");
	ImageIcon ikonica = new ImageIcon("src/slike/knjiga.png");
	private DefaultTableModel tableModel;
	private JTable BibliotekariTabela;
	 
	private Biblioteka biblioteka;
	
	public BibliotekarPrikaz(Biblioteka biblioteka) {
		this.biblioteka = biblioteka;
		setTitle("Bibliotekari");
		setSize(600, 300);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
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
		
		ArrayList<Bibliotekar>neobrisaniBibliotekari=biblioteka.sviNeobrisaniBibliotekari();
		String[] zaglavlja = new String[] {"ID", "Korisnicko ime", "lozinka", "Pol"};
		Object[][] sadrzaj = new Object[neobrisaniBibliotekari.size()][zaglavlja.length];
		for(int i=0; i<neobrisaniBibliotekari.size(); i++) {
			
			Bibliotekar b = neobrisaniBibliotekari.get(i);		
			sadrzaj[i][0] = b.getId();
			sadrzaj[i][1] = b.getKorisnickoIme();
			sadrzaj[i][2] = b.getLozinka();
			sadrzaj[i][3] = b.getPol();

		}
		tableModel = new DefaultTableModel(sadrzaj, zaglavlja);
		BibliotekariTabela = new JTable(tableModel);
		
		BibliotekariTabela.setRowSelectionAllowed(true);
		BibliotekariTabela.setColumnSelectionAllowed(false);
		BibliotekariTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		BibliotekariTabela.setDefaultEditor(Object.class, null);
		BibliotekariTabela.getTableHeader().setReorderingAllowed(false);
		
		JScrollPane scrollPane = new JScrollPane(BibliotekariTabela);
		add(scrollPane, BorderLayout.CENTER);
	
	}
	private void initActions() {
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = BibliotekariTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					int id =Integer.parseInt(tableModel.getValueAt(red, 0).toString());
					String naziv = tableModel.getValueAt(red, 1).toString();
					
					int izbor = JOptionPane.showConfirmDialog(null, 
							"Da li ste sigurni da zelite da obrisete bibliotekara?", 
							naziv + " - Porvrda brisanja", JOptionPane.YES_NO_OPTION);
					if(izbor == JOptionPane.YES_OPTION) {
						Bibliotekar b =biblioteka.getBibliotekari().get(id);
						b.setObrisan(true);
						System.out.println(biblioteka.getBibliotekari().toString());
						try {
							biblioteka.sacuvajBibliotekare();
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
				BibliotekarDodavanje bibliotekarDodavanje = new BibliotekarDodavanje(biblioteka);
				bibliotekarDodavanje.setVisible(true);
				BibliotekarPrikaz.this.dispose();
				BibliotekarPrikaz.this.setVisible(false);
			}
		});
//		
		btnEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = BibliotekariTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					String id = tableModel.getValueAt(red, 0).toString();
					Bibliotekar bibliotekar = biblioteka.pronadjiBibliotekara(id);
					BibliotekarDodavanje editBibliotekar = new BibliotekarDodavanje(biblioteka, bibliotekar);
					editBibliotekar.setVisible(true);
					BibliotekarPrikaz.this.dispose();
					BibliotekarPrikaz.this.setVisible(false);
				}
			}
		});
	}

}


