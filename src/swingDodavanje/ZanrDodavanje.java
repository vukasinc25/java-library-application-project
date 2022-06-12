package swingDodavanje;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import biblioteka.Biblioteka;
import biblioteka.ZanrKnjige;
import net.miginfocom.swing.MigLayout;

public class ZanrDodavanje extends JDialog{
	private Biblioteka biblioteka;
	private ZanrKnjige zanrKnjige;

	
//	String id,String oznaka, String opis,boolean jeObrisan
	 private JLabel lblID = new JLabel("ID");
	 private JTextField txtID = new JTextField(20);
	 private JLabel lblOznaka = new JLabel("Oznaka");
	 private JTextField txtOznaka = new JTextField(20);
	 private JLabel lblOpis = new JLabel("Opis");
	 private JTextField txtOpis = new JTextField(20);
	 private JButton btnSave = new JButton("Save");
	 private JButton btnCancel = new JButton("Cancel");
	 
//	 public DijalogDodajZanr(Biblioteka biblioteka,ZanrKnjige zanrKnjige) {
//		 this.biblioteka = biblioteka;
//		 this.zanrKnjige= zanrKnjige;
//		 setTitle("Dodavanje novi zanr");
//		 setSize(500,500);
//		 setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//		 setLocationRelativeTo(null);
////		 cmbxEmnumPol.setModel(new DefaultComboBoxModel<EmnumPol>(EmnumPol.values()));
//		 initGUI();
//		 initActions();
//		 pack();
//	 }
	 
	 public ZanrDodavanje(Biblioteka biblioteka) {
		 this.biblioteka = biblioteka;
//		 this.zanrKnjige= zanrKnjige;
		 setTitle("Dodavanje novi zanr");
		 setSize(300,200);
		 setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		 setLocationRelativeTo(null);
		 initGUI();
		 initActions();
		 pack();
	 }

	private void initActions() {	
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ZanrDodavanje.this.dispose();
				ZanrDodavanje.this.setVisible(false);
			}
		});
		btnSave.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String id = txtID.getText().trim();
				String oznaka = txtOznaka.getText().trim();
				String opis = txtOpis.getText().trim();
				
				if(id.equals("")||oznaka.equals("")||opis.equals("")) {
					JOptionPane.showMessageDialog(null, "Moraju sva polja da budu popunjena","Greska",JOptionPane.WARNING_MESSAGE);
				}
				else {
					if(zanrKnjige == null) {
						ZanrKnjige zanr = new ZanrKnjige(id,oznaka,opis,false);
						biblioteka.getZanrovi().add(zanr);
					}/*else {*/
//						zanrKnjige.setId(id);
//						zanrKnjige.setOpis(opis);
//						zanrKnjige.setOznaka(oznaka);
//					}
					try {
						biblioteka.sacuvajZanrKnjige();
						ZanrDodavanje.this.setVisible(false);
					}catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
	}

	private void initGUI() {
		MigLayout mig = new MigLayout("wrap 2","[][]","[]10[]10[]");
		setLayout(mig);
		
		add(lblID);
		add(txtID);
		add(lblOpis);
		add(txtOpis);
		add(lblOznaka);
		add(txtOznaka);
		add(btnSave);
		add(btnCancel);
		
	}
}
