package swingMain;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import biblioteka.Biblioteka;
import ljudi.Admin;
import ljudi.Zaposleni;
import net.miginfocom.swing.MigLayout;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	protected JPanel contentPane;
	protected JLabel tekst1 = new JLabel("Prijavite se na sistem biblioteke.");
	protected JLabel korisnickoImeNatpis = new JLabel("Korisničko ime");
	protected JTextField korisnickoImePolje = new JTextField(20);
	protected JLabel lozinkaNatpis = new JLabel("Lozinka");
	protected JPasswordField lozinkaPolje = new JPasswordField(20);
	protected JButton btnOk = new JButton("Prijava");
	protected JButton btnCancel = new JButton("Otkaži");
	protected Biblioteka biblioteka;
	
	ImageIcon ikonica = new ImageIcon("src/slike/ikonicaKnjige.png");
	
	public Login(Biblioteka biblioteka) {
		this.biblioteka = biblioteka;
		setIconImage(ikonica.getImage());
		setTitle("Biblioteka Prijava");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(getMaximumSize());
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		setSize(800,600);
		Toolkit toolkit = getToolkit();
		Dimension size = toolkit.getScreenSize();
		setLocation(size.width/2 - getWidth()/2, size.height/2-getHeight()/2);
		
		initMig();
		initOkCancel();
		pack();
		
	}

	public void initMig() {
		MigLayout mig = new MigLayout("wrap 2", "[][]", "[]10[][]10[]");
		setLayout(mig);
		
		add(tekst1, "span 2");
		add(korisnickoImeNatpis);
		add(korisnickoImePolje);
		add(lozinkaNatpis);
		add(lozinkaPolje);
		add(new JLabel());
		add(btnOk, "split 2");
		add(btnCancel);
		
		korisnickoImePolje.setText("akorisnicko");
		lozinkaPolje.setText("alozinka");
		getRootPane().setDefaultButton(btnOk);
	}
	
	public void initOkCancel() {
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Login.this.dispose();
				Login.this.setVisible(false);
			}
		});
	
		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String korisnikoIme = korisnickoImePolje.getText().trim();
				String lozinka = new String(lozinkaPolje.getPassword()).trim();
				if(korisnikoIme.equals("") || lozinka.equals("")) {
					JOptionPane.showMessageDialog(null, "Unesite podatke za prijavu.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					Zaposleni prijavljeni = biblioteka.login(korisnikoIme, lozinka);
					if(prijavljeni == null) {
						System.out.println(prijavljeni);
						JOptionPane.showMessageDialog(null, "Pogrešno korisničko ime ili lozinka", "Greška", JOptionPane.WARNING_MESSAGE);
					}else {
						Login.this.dispose();
						Login.this.setVisible(false);
						boolean isAdmin = prijavljeni instanceof Admin;
						MainWindow mw = new MainWindow(biblioteka, prijavljeni,isAdmin);
						mw.setVisible(true);
						}
					}
				}
		});
	}
}

