package swingPrikaz;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDate;
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
import biblioteka.PrimerakKnjige;
import ljudi.Zaposleni;
import swingDodavanje.PrimerakDodavanje;
import swingIzmena.PrimerakIzmena;


public class PrimerakKnjigePrikaz extends JFrame {
	private JToolBar mainToolbar = new JToolBar();
	private final JButton btnDodaj = new JButton("Dodaj Primerak");
	private final JButton btnIzmeni = new JButton("Izmeni Primerak");
	private final JButton btnIzbrisi = new JButton("Izbrisi Primerak");
	ImageIcon ikonica = new ImageIcon("src/slike/knjiga.png");
	private Zaposleni zaposleni;
	
	private DefaultTableModel tableModel;
	private JTable primerciKnjigaTabela;
	
	private Biblioteka biblioteka;
	private PrimerakKnjige primerak;

	public PrimerakKnjigePrikaz (Biblioteka biblioteka, Zaposleni zaposleni) throws IOException {
		this.biblioteka = biblioteka;
		this.zaposleni = zaposleni;
		this.biblioteka.citajZanr();
		this.biblioteka.citajKnjige();
		setTitle("Kompozicije");
		setSize(600,400);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		initGUI();
		initActions();
	}

	private void initGUI() throws IOException {
		setIconImage(ikonica.getImage());
		getContentPane().add(mainToolbar, BorderLayout.SOUTH);		
		mainToolbar.setBackground(Color.LIGHT_GRAY);
		btnDodaj.setBackground(Color.LIGHT_GRAY);
		btnIzmeni.setBackground(Color.LIGHT_GRAY);
		btnIzbrisi.setBackground(Color.LIGHT_GRAY);
		mainToolbar.add(btnDodaj);
		mainToolbar.add(btnIzmeni);
		mainToolbar.add(btnIzbrisi);
		
		biblioteka.citajKnjige();
		String[] zaglavlja = new String[] {"Id", "Br.Strana", "TipPoveza", "GodinaStampanja", "Iznajmljena","Knjiga"};
		Object[][] sadrzaj = new Object[biblioteka.sviNeobrisaniPrimerciKnjige().size()][zaglavlja.length];
		System.out.println(biblioteka.sviNeobrisaniPrimerciKnjige());
		for(int i=0; i<biblioteka.sviNeobrisaniPrimerciKnjige().size(); i++) {
			PrimerakKnjige primerak = biblioteka.sviNeobrisaniPrimerciKnjige().get(i);
			System.out.println(biblioteka.getKnjige());
			sadrzaj[i][0] = primerak.getId();
			sadrzaj[i][1] = primerak.getBrStrana();
			sadrzaj[i][2] = primerak.isTipPoveza();
			sadrzaj[i][3] = primerak.getGodinaStampanja();
			sadrzaj[i][4] = primerak.isIzdata();
			sadrzaj[i][5] = primerak.getKnjiga().getId();
		}
		
		tableModel = new DefaultTableModel(sadrzaj, zaglavlja);
		primerciKnjigaTabela = new JTable(tableModel);
		
		primerciKnjigaTabela.setRowSelectionAllowed(true);
		primerciKnjigaTabela.setColumnSelectionAllowed(false);
		primerciKnjigaTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		primerciKnjigaTabela.setDefaultEditor(Object.class, null);
		primerciKnjigaTabela.getTableHeader().setReorderingAllowed(false);
		
		JScrollPane scrollPane = new JScrollPane(primerciKnjigaTabela);
		add(scrollPane, BorderLayout.CENTER);
		
	}

	private void initActions() {
		btnIzbrisi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = primerciKnjigaTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.","Greska",JOptionPane.WARNING_MESSAGE);
				}
				else {
					int id = Integer.parseInt(tableModel.getValueAt(red, 0).toString());
					String naziv = tableModel.getValueAt(red, 1).toString();
					
					int izbor = JOptionPane.showConfirmDialog(null, "Da li ste sigurni da zelite da obrisete clana?",naziv + "- Potvrda brisanja",JOptionPane.YES_NO_OPTION);
					if(izbor == JOptionPane.YES_NO_OPTION) {
						PrimerakKnjige c = biblioteka.getPrimerakKnjige().get(id);
						c.setObrisan(true);
						System.out.println(biblioteka.getPrimerakKnjige().toString());
						try {
							biblioteka.sacuvajPrimerke();
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
				PrimerakDodavanje da = new PrimerakDodavanje(biblioteka);
				da.setVisible(true);
				PrimerakKnjigePrikaz.this.dispose();
				PrimerakKnjigePrikaz.this.setVisible(false);
			}
		});
		btnIzmeni.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = primerciKnjigaTabela.getSelectedRow();
				if(row == -1) {
					JOptionPane.showMessageDialog(null, "Morate da izaberete red koji zelite da promenite","Greska",JOptionPane.WARNING_MESSAGE);
				}
				else {
					String id = tableModel.getValueAt(row, 0).toString();
					PrimerakKnjige primerak = biblioteka.pronadjiPrimerak(id);
					PrimerakIzmena edit = new PrimerakIzmena(biblioteka,primerak);
					edit.setVisible(true);
				}
			}
		});
	}
}
