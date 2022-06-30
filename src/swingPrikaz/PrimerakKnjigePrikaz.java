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
import biblioteka.Primerak;
import swingDodavanje.PrimerakDodavanje;

public class PrimerakKnjigePrikaz extends JFrame {
	private JToolBar mainToolbar = new JToolBar();
	private JButton btnAdd = new JButton("Dodaj");
	private JButton btnEdit = new JButton("Izmeni");
	private JButton btnDelete = new JButton("Obrisi");
	ImageIcon ikonica = new ImageIcon("src/slike/knjiga.png");
	
	private DefaultTableModel tableModel;
	private JTable primerciTabela;
	 
	private Biblioteka biblioteka;
	
	public PrimerakKnjigePrikaz(Biblioteka biblioteka) {
		this.biblioteka = biblioteka;
		setTitle("Primerci");
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
		
		ArrayList<Primerak>neobrisaniPrimerci=biblioteka.sviNeobrisaniPrimerci();
		String[] zaglavlja = new String[] {"Id", "Naziv", "Jezik"};
		Object[][] sadrzaj = new Object[neobrisaniPrimerci.size()][zaglavlja.length];
		for(int i=0; i<neobrisaniPrimerci.size(); i++) {
			
			Primerak primerak = neobrisaniPrimerci.get(i);		
			sadrzaj[i][0] = primerak.getId();
			sadrzaj[i][1] = primerak.getKnjiga().getNaslov();
			sadrzaj[i][2] = primerak.getJezikk();
			
		}
		tableModel = new DefaultTableModel(sadrzaj, zaglavlja);
		primerciTabela = new JTable(tableModel);
		
		primerciTabela.setRowSelectionAllowed(true);
		primerciTabela.setColumnSelectionAllowed(false);
		primerciTabela.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		primerciTabela.setDefaultEditor(Object.class, null);
		primerciTabela.getTableHeader().setReorderingAllowed(false);
		
		JScrollPane scrollPane = new JScrollPane(primerciTabela);
		add(scrollPane, BorderLayout.CENTER);
	
	}
	private void initActions() {
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = primerciTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					int id =Integer.parseInt(tableModel.getValueAt(red, 0).toString());
					String naziv = tableModel.getValueAt(red, 1).toString();
					
					int izbor = JOptionPane.showConfirmDialog(null, 
							"Da li ste sigurni da zelite da obrisete primerak?", 
							naziv + " - Porvrda brisanja", JOptionPane.YES_NO_OPTION);
					if(izbor == JOptionPane.YES_OPTION) {
						Primerak p =biblioteka.getPrimerci().get(id);
						p.setObrisan(true);
						System.out.println(biblioteka.getPrimerci().toString());
						try {
							biblioteka.sacuvajPrimerke();
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
				PrimerakDodavanje primerakDodavanje = new PrimerakDodavanje(biblioteka);
				primerakDodavanje.setVisible(true);
				PrimerakKnjigePrikaz.this.dispose();
				PrimerakKnjigePrikaz.this.setVisible(false);
			}
		});
		
		btnEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = primerciTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					String id = tableModel.getValueAt(red, 0).toString();
					Primerak primerak = biblioteka.pronadjiPrimerak(id);
					PrimerakDodavanje editPrimerka = new PrimerakDodavanje(biblioteka, primerak);
					editPrimerka.setVisible(true);
					PrimerakKnjigePrikaz.this.dispose();
					PrimerakKnjigePrikaz.this.setVisible(false);
				}
			}
		});
	}

}
