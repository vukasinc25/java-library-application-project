package swingPrikaz;

import java.awt.BorderLayout;
import java.awt.Color;
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
import ljudi.TipClanarine;
import ljudi.Zaposleni;
import swingDodavanje.TipDodavanje;
import swingIzmena.TipIzmena;

public class TipClanarinePrikaz extends JFrame{
	private JToolBar mainToolbar = new JToolBar();
	private final JButton btnDodaj = new JButton("Dodaj Tip");
	private final JButton btnIzmeni = new JButton("Izmeni Tip");
	private final JButton btnIzbrisi = new JButton("Izbrisi Tip");
	private Zaposleni zaposleni;
	private DefaultTableModel tableModel;
	private JTable tipclanarineTabela;
	ImageIcon ikonica = new ImageIcon("src/slike/knjiga.png");
	private Biblioteka biblioteka;
	private TipClanarine tipClanarine;

	public TipClanarinePrikaz (Biblioteka biblioteka,Zaposleni zaposleni) {
		this.biblioteka = biblioteka;
		this.zaposleni = zaposleni;
		setTitle("Tipovi Clanarine");
		setSize(600, 400);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		initGUI();
		initActions();
	}

	private void initGUI() {
		getContentPane().add(mainToolbar, BorderLayout.SOUTH);		
		mainToolbar.setBackground(Color.LIGHT_GRAY);
		btnDodaj.setBackground(Color.LIGHT_GRAY);
		btnIzmeni.setBackground(Color.LIGHT_GRAY);
		btnIzbrisi.setBackground(Color.LIGHT_GRAY);
		mainToolbar.add(btnDodaj);
		mainToolbar.add(btnIzmeni);
		mainToolbar.add(btnIzbrisi);
		setIconImage(ikonica.getImage());

		String[] zaglavlja = new String[] {"Naziv","ID", "Cena"};
		Object[][] sadrzaj = new Object[biblioteka.sviNeobrisaniTipovi().size()][zaglavlja.length];
		
		for(int i=0; i<biblioteka.sviNeobrisaniTipovi().size(); i++) {
			TipClanarine clan = biblioteka.sviNeobrisaniTipovi().get(i);
			sadrzaj[i][0] = clan.getId();
			sadrzaj[i][1] = clan.getTip();
			sadrzaj[i][2] = clan.getCena();
		}
		
		tableModel = new DefaultTableModel(sadrzaj, zaglavlja);
		tipclanarineTabela = new JTable(tableModel);
		
		tipclanarineTabela.setRowSelectionAllowed(true);
		tipclanarineTabela.setColumnSelectionAllowed(false);
		tipclanarineTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tipclanarineTabela.setDefaultEditor(Object.class, null);
		tipclanarineTabela.getTableHeader().setReorderingAllowed(false);
		
		JScrollPane scrollPane = new JScrollPane(tipclanarineTabela);
		add(scrollPane, BorderLayout.CENTER);
		
	}

	private void initActions() {
		btnIzbrisi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = tipclanarineTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.","Greska",JOptionPane.WARNING_MESSAGE);
				}
				else {
					int id = Integer.parseInt(tableModel.getValueAt(red, 1).toString());
					String naziv = tableModel.getValueAt(red, 0).toString();		
					int izbor = JOptionPane.showConfirmDialog(null, "Da li ste sigurni da zelite da obrisete clana?",naziv + "- Potvrda brisanja",JOptionPane.YES_NO_OPTION);
					if(izbor == JOptionPane.YES_NO_OPTION) {
						TipClanarine c = biblioteka.getTipClanarine().get(id);
						c.setObrisan(true);
						System.out.println(biblioteka.getTipClanarine().toString());
						try {
							biblioteka.sacuvajTipClanarine();
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
				TipDodavanje da = new TipDodavanje(biblioteka);
				da.setVisible(true);
				TipClanarinePrikaz.this.dispose();
				TipClanarinePrikaz.this.setVisible(false);
			}
		});
		
		btnIzmeni.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = tipclanarineTabela.getSelectedRow();
				if(row == -1) {
					JOptionPane.showMessageDialog(null, "Morate da izaberete red koji zelite da promenite","Greska",JOptionPane.WARNING_MESSAGE);
				}
				else {
					String id = tableModel.getValueAt(row, 0).toString();
					TipClanarine tip = biblioteka.pronadjiTip(id);
					System.out.println(tip);
					TipIzmena edit = new TipIzmena(biblioteka,tip);
					edit.setVisible(true);
				}
			}
		});
		
	}
}
