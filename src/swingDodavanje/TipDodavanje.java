package swingDodavanje;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import biblioteka.Biblioteka;
import ljudi.TipClanarine;
import net.miginfocom.swing.MigLayout;

public class TipDodavanje extends JFrame {
	
	private JLabel lblOpis=new JLabel("Opis: ");
	private JTextField txtOpis=new JTextField(20);
	private JLabel lblCena=new JLabel("Cena: ");
	private JTextField txtCena=new JTextField(20);
	private JButton btnOk = new JButton("OK");
	private JButton btnCancel = new JButton("Cancel");
	
	private Biblioteka biblioteka;
	private TipClanarine tip;
	
	public TipDodavanje(Biblioteka biblioteka) {
		this.biblioteka=biblioteka;
		setSize(500,1000);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		initGUI();
		initActions();
		pack();
	}
	
	public TipDodavanje(Biblioteka biblioteka, TipClanarine tip) {
		this.biblioteka=biblioteka;	
		this.tip=tip;
		setTitle("Dodavanje");
		setSize(500,1000);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		initGUI();
		initActions();
		pack();
	}
	
	public void initGUI() {
		MigLayout mig = new MigLayout("wrap 2", "[][]", "[]10[][]10[]");
		setLayout(mig);
		
		add(lblOpis);
		add(txtOpis);
		add(lblCena);
		add(txtCena);
		
		add(btnOk);
		add(btnCancel);
	
		if(tip!=null) {
			txtOpis.setText(tip.getOpis());
			txtCena.setText(tip.getCena());
		}
	}
	public void initActions() {
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TipDodavanje.this.dispose();
				TipDodavanje.this.setVisible(false);
			}
		});
		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String opis=txtOpis.getText().trim();
				String cena=txtCena.getText().trim();
				
				if(opis.equals("") || cena.equals("")) {
					JOptionPane.showMessageDialog(null, "Niste uneli sve podatke za dodavanje.", "Greska", JOptionPane.WARNING_MESSAGE);
				}
				else {
					if(tip==null) {
						String id= Integer.toString(biblioteka.getTipovi().size());	
						TipClanarine noviTip= new TipClanarine(id,opis,cena,false);
						biblioteka.getTipovi().add(noviTip);
					}
					else {
						tip.setOpis(opis);
						tip.setCena(cena);
					}
					try {
						biblioteka.sacuvajTipoveClanarine();
						TipDodavanje.this.setVisible(false);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
	}

	
	
}
