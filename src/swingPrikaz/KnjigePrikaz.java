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
import biblioteka.Knjiga;
import swingDodavanje.KnjigaDodavanje;

public class KnjigePrikaz extends JFrame {
	private JToolBar mainToolbar = new JToolBar();
	private JButton btnAdd = new JButton("Dodaj");
	private JButton btnEdit = new JButton("Izmeni");
	private JButton btnDelete = new JButton("Obrisi");
	
	ImageIcon ikonica = new ImageIcon("src/slike/knjiga.png");
	
	private DefaultTableModel tableModel;
	private JTable knjigeTabela;
	 
	private Biblioteka biblioteka;
	
	public KnjigePrikaz(Biblioteka biblioteka) {
		this.biblioteka = biblioteka;
		setTitle("Knjige");
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
		
		ArrayList<Knjiga>neobrisaneLKnjige=biblioteka.sveNeobrisaneKnjige();
		String[] zaglavlja = new String[] {"Id", "Naziv", "Pisac" , "Zanr"};
		Object[][] sadrzaj = new Object[neobrisaneLKnjige.size()][zaglavlja.length];
		for(int i=0; i<neobrisaneLKnjige.size(); i++) {
			
			Knjiga knjiga =neobrisaneLKnjige.get(i);		
			sadrzaj[i][0] =knjiga.getId();
			sadrzaj[i][1] = knjiga.getNaslov();
			sadrzaj[i][2] = knjiga.getPisac();
			sadrzaj[i][3] = knjiga.getZanr().getOpis();

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
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					String naziv = tableModel.getValueAt(red, 0).toString();
					Knjiga p =biblioteka.pronadjiKnjigu(naziv);
					int izbor = JOptionPane.showConfirmDialog(null, 
							"Da li ste sigurni da zelite da obrisete knjigu?", 
							naziv + " - Porvrda brisanja", JOptionPane.YES_NO_OPTION);
					if(izbor == JOptionPane.YES_OPTION) {
						Knjiga k=biblioteka.getKnjige().get(red);
						k.setObrisan(true);
						try {
							biblioteka.sacuvajKnjige();
						} catch(IOException e1) {
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
				KnjigaDodavanje KnjigaDodavanje = new KnjigaDodavanje(biblioteka);
				KnjigaDodavanje.setVisible(true);
				
				KnjigePrikaz.this.dispose();
				KnjigePrikaz.this.setVisible(false);
			}
		});
		
		btnEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = knjigeTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
					}else {
						String id = tableModel.getValueAt(red, 0).toString();
						Knjiga knjiga = biblioteka.pronadjiKnjigu(id);
						KnjigaDodavanje editKnjige=new KnjigaDodavanje(biblioteka,knjiga);
						editKnjige.setVisible(true);
						KnjigePrikaz.this.dispose();
						KnjigePrikaz.this.setVisible(false);
						
					}
				}
			});
		}

	}


