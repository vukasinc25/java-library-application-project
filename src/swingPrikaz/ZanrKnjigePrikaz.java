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
import biblioteka.ZanrKnjige;
import ljudi.Zaposleni;
import swingDodavanje.ZanrDodavanje;
import swingIzmena.ZanrIzmena;

public class ZanrKnjigePrikaz extends JFrame{
	private JToolBar mainToolbar = new JToolBar();
	private JButton btnAdd = new JButton();
	private JButton btnEdit = new JButton();
	private JButton btnDelete = new JButton();
	private Zaposleni zaposleni;
	
	private DefaultTableModel tableModel;
	private JTable zanroviTabela;
	
	private Biblioteka biblioteka;
	private ZanrKnjige zanr;

	public ZanrKnjigePrikaz (Biblioteka biblioteka,Zaposleni zaposleni) {
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

		String[] zaglavlja = new String[] {"Id", "Opis", "Oznaka"};
		Object[][] sadrzaj = new Object[biblioteka.sviNeobrisaniZanrovi().size()][zaglavlja.length];
		
		for(int i=0; i<biblioteka.sviNeobrisaniZanrovi().size(); i++) {
			ZanrKnjige zanr = biblioteka.sviNeobrisaniZanrovi().get(i);
//			Knjiga knjiga = biblioteka.pronadjiDisk(zanr);
			sadrzaj[i][0] = zanr.getId();
			sadrzaj[i][1] = zanr.getOpisZanra();
			sadrzaj[i][2] = zanr.getOznaka();
//			sadrzaj[i][2] = disk == null ? "--" : disk.getNaziv();
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
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.","Greska",JOptionPane.WARNING_MESSAGE);
				}
				else {
					int id = Integer.parseInt(tableModel.getValueAt(red, 0).toString());
					String naziv = tableModel.getValueAt(red, 1).toString();
					
					int izbor = JOptionPane.showConfirmDialog(null, "Da li ste sigurni da zelite da obrisete clana?",naziv + "- Potvrda brisanja",JOptionPane.YES_NO_OPTION);
					if(izbor == JOptionPane.YES_NO_OPTION) {
						ZanrKnjige c = biblioteka.getZanrovi().get(id);
						c.setObrisan(true);
						System.out.println(biblioteka.getZanrovi().toString());
						try {
							biblioteka.sacuvajZanrKnjige();
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
				ZanrDodavanje da = new ZanrDodavanje(biblioteka);
				da.setVisible(true);
				ZanrKnjigePrikaz.this.dispose();
				ZanrKnjigePrikaz.this.setVisible(false);
			}
		});
		btnEdit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = zanroviTabela.getSelectedRow();
				if(row == -1) {
					JOptionPane.showMessageDialog(null, "Morate da izaberete red koji zelite da promenite","Greska",JOptionPane.WARNING_MESSAGE);
				}
				else {
					String id = tableModel.getValueAt(row, 0).toString();
					ZanrKnjige zanr = biblioteka.pronadjiZanr(id);
					System.out.println(zanr);
					ZanrIzmena edit = new ZanrIzmena(biblioteka,zanr);
					edit.setVisible(true);
				}
				
			}
		});
	}
}
