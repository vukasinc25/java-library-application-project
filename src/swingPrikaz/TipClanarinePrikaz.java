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
import ljudi.TipClanarine;
import swingDodavanje.TipDodavanje;

public class TipClanarinePrikaz extends JFrame {
	private JToolBar mainToolbar = new JToolBar();
	private JButton btnAdd = new JButton("Dodaj");
	private JButton btnEdit = new JButton("Izmeni");
	private JButton btnDelete = new JButton("Obrisi");
	ImageIcon ikonica = new ImageIcon("src/slike/knjiga.png");
	
	private DefaultTableModel tableModel;
	private JTable tipoviTabela;
	 
	private Biblioteka biblioteka;
	
	public TipClanarinePrikaz(Biblioteka biblioteka) {
		this.biblioteka = biblioteka;
		setTitle("Tipovi Clanarine");
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
		
		ArrayList<TipClanarine>neobrisaniTipovi=biblioteka.sviNeobrisaniTipovi();
		String[] zaglavlja = new String[] {"Id", "Opis", "Cena"};
		Object[][] sadrzaj = new Object[neobrisaniTipovi.size()][zaglavlja.length];
		for(int i=0; i<neobrisaniTipovi.size(); i++) {
			
			TipClanarine tip = neobrisaniTipovi.get(i);		
			sadrzaj[i][0] = tip.getId();
			sadrzaj[i][1] = tip.getOpis();
			sadrzaj[i][2] = tip.getCena();
			
		}
		tableModel = new DefaultTableModel(sadrzaj, zaglavlja);
		tipoviTabela = new JTable(tableModel);
		
		tipoviTabela.setRowSelectionAllowed(true);
		tipoviTabela.setColumnSelectionAllowed(false);
		tipoviTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tipoviTabela.setDefaultEditor(Object.class, null);
		tipoviTabela.getTableHeader().setReorderingAllowed(false);
		
		JScrollPane scrollPane = new JScrollPane(tipoviTabela);
		add(scrollPane, BorderLayout.CENTER);
	
	}
	private void initActions() {
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = tipoviTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					int id =Integer.parseInt(tableModel.getValueAt(red, 0).toString());
					String naziv = tableModel.getValueAt(red, 1).toString();
					
					int izbor = JOptionPane.showConfirmDialog(null, 
							"Da li ste sigurni da zelite da obrisete tip clanarine?", 
							naziv + " - Porvrda brisanja", JOptionPane.YES_NO_OPTION);
					if(izbor == JOptionPane.YES_OPTION) {
						TipClanarine t =biblioteka.getTipovi().get(id);
						t.setObrisan(true);
						try {
							biblioteka.sacuvajTipoveClanarine();
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
				TipDodavanje tipDodavanje = new TipDodavanje(biblioteka);
				tipDodavanje.setVisible(true);
				TipClanarinePrikaz.this.dispose();
				TipClanarinePrikaz.this.setVisible(false);
			}
		});
		
		btnEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = tipoviTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					String id = tableModel.getValueAt(red, 0).toString();
					TipClanarine tipClanarine = biblioteka.pronadjiTip(id);
					TipDodavanje tip = new TipDodavanje(biblioteka, tipClanarine);
					tip.setVisible(true);
					TipClanarinePrikaz.this.dispose();
					TipClanarinePrikaz.this.setVisible(false);
				}
			}
		});
	}

}
