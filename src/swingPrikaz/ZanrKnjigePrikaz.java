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
import biblioteka.Zanr;
import swingDodavanje.ZanrDodavanje;

public class ZanrKnjigePrikaz extends JFrame {
	private JToolBar mainToolbar = new JToolBar();
	private JButton btnAdd = new JButton("Dodaj");
	private JButton btnEdit = new JButton("Izmeni");
	private JButton btnDelete = new JButton("Obrisi");
	
	ImageIcon ikonica = new ImageIcon("src/slike/knjiga.png");
	
	private DefaultTableModel tableModel;
	private JTable zanroviTabela;
	 
	private Biblioteka biblioteka;
	
	public ZanrKnjigePrikaz(Biblioteka biblioteka) {
		this.biblioteka = biblioteka;
		setTitle("Zanrovi");
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
		
		ArrayList<Zanr>neobrisaniZanrovi=biblioteka.sviNeobrisaniZanrovi();
		String[] zaglavlja = new String[] {"Id", "Opis", "Oznaka"};
		Object[][] sadrzaj = new Object[neobrisaniZanrovi.size()][zaglavlja.length];
		for(int i=0; i<neobrisaniZanrovi.size(); i++) {
			
			Zanr zanr = neobrisaniZanrovi.get(i);		
			sadrzaj[i][0] = zanr.getId();
			sadrzaj[i][1] = zanr.getOpis();
			sadrzaj[i][2] = zanr.getOznaka();
			
		}
		tableModel = new DefaultTableModel(sadrzaj, zaglavlja);
		zanroviTabela = new JTable(tableModel);
		
		zanroviTabela.setRowSelectionAllowed(true);
		zanroviTabela.setColumnSelectionAllowed(false);
		zanroviTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		zanroviTabela.setDefaultEditor(Object.class, null);
		zanroviTabela.getTableHeader().setReorderingAllowed(false);
		
		JScrollPane scrollPane = new JScrollPane(zanroviTabela);
		add(scrollPane, BorderLayout.CENTER);
	
	}
	private void initActions() {
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = zanroviTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					int id =Integer.parseInt(tableModel.getValueAt(red, 0).toString());
					String naziv = tableModel.getValueAt(red, 1).toString();
					
					int izbor = JOptionPane.showConfirmDialog(null, 
							"Da li ste sigurni da zelite da obrisete zanr?", 
							naziv + " - Porvrda brisanja", JOptionPane.YES_NO_OPTION);
					if(izbor == JOptionPane.YES_OPTION) {
						Zanr z =biblioteka.getZanrovi().get(id);
						z.setObrisan(true);
						try {
							biblioteka.sacuvajZanrove();;
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
				ZanrDodavanje zanrDodavanje = new ZanrDodavanje(biblioteka);
				zanrDodavanje.setVisible(true);
				ZanrKnjigePrikaz.this.dispose();
				ZanrKnjigePrikaz.this.setVisible(false);
			}
		});

		btnEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = zanroviTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					String id = tableModel.getValueAt(red, 0).toString();
					Zanr zanr = biblioteka.pronadjiZanr(id);
					ZanrDodavanje editZanr = new ZanrDodavanje(biblioteka, zanr);
					editZanr.setVisible(true);
					ZanrKnjigePrikaz.this.dispose();
					ZanrKnjigePrikaz.this.setVisible(false);
				}
			}
		});
	}

}
