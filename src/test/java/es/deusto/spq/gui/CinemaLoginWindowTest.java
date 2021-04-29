package es.deusto.spq.gui;

import static org.junit.Assert.*;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.text.StyledEditorKit.ForegroundAction;

import org.junit.Before;
import org.junit.Test;

public class CinemaLoginWindowTest {

	private JFrame f;
	private JPanel cp;
	private JTextField txtUsername1;
	private JPasswordField txtPassword1;
	private JLabel lblLoginMessage1;
	private JLabel lblGest;
	private JPanel panel;
	private JPanel lblIconPassword1;
	private JPanel pnlBtnLogin1;
	private JLabel lblLogIn;
	private JLabel lblX;
	private JLabel lblCineDeusto;
	private JButton btnRegister;

	@Before
	public void setUp() throws Exception {
		f = new JFrame();
		f.setVisible(true);
		f.setBounds(100, 100, 600, 400);
		
		 cp = new JPanel();
		 cp.setBounds(173, 146, 250, 40);
		 cp.setBackground(new Color(72, 209, 204));
	     		 
		 txtUsername1 = new JTextField();
		 txtUsername1.setBounds(10, 10, 170, 20);
		 txtUsername1.setText("Usuario");
		 txtUsername1.setColumns(10);
		 txtUsername1.setFont(new Font("Arial", Font.BOLD, 14));
		 
		 txtPassword1 = new JPasswordField();
		 txtPassword1.setFont(new Font("Arial", Font.BOLD, 14));
	        txtPassword1.setText("Contraseña");
	        txtPassword1.setBounds(10, 10, 170, 20);
	        
		 lblLoginMessage1 = new JLabel();
		 
		 lblGest = new JLabel();
		 
		 panel = new JPanel();
		 panel.setBackground(Color.WHITE);
	     panel.setBounds(173, 146, 250, 40);
		 
		 lblIconPassword1 = new JPanel();
		 lblIconPassword1.setBackground(Color.WHITE);
		 lblIconPassword1.add(txtPassword1);
		 
		 pnlBtnLogin1 = new JPanel();
		 pnlBtnLogin1.setBackground(new Color(47, 79, 79));
	        pnlBtnLogin1.setBounds(327, 292, 198, 51);
		 
		 lblLogIn = new JLabel();
		 lblLogIn.setForeground(Color.WHITE);
		 lblLogIn.setText("INICIAR SESIÓN");
	        lblLogIn.setFont(new Font("Arial", Font.BOLD, 14));
	        lblLogIn.setBounds(47, 10, 125, 31);
		 
		 lblX = new JLabel();
		 
		 lblCineDeusto = new JLabel();
		 
		 btnRegister = new JButton();
		 
	}

	@Test
	public void testFrame() {
		int xframe = f.getX();
		assertEquals(100, xframe);
		
	}
	@Test
	public void testContentPane() {
		int xcp = cp.getX();
		assertEquals(173, xcp);
		int ycp   = cp.getY();
		assertEquals(146, ycp);
		int widthcp   = cp.getWidth();
		assertEquals(250, widthcp);
		int heightcp   = cp.getHeight();
		assertEquals(40, heightcp);
		
		Color cCp = cp.getBackground();
		assertEquals(new Color(72, 209, 204), cCp);		
	}
	@Test
	public void testTextUser() {
		int xTxtUser  = txtUsername1.getX();
		assertEquals(10, xTxtUser);
		int yTxtUser  = txtUsername1.getY();
		assertEquals(10, yTxtUser);
		int widthTxtUser  = txtUsername1.getWidth();
		assertEquals(170, widthTxtUser);
		int heightTxtUser  = txtUsername1.getHeight();
		assertEquals(20, heightTxtUser);
		int columnsTxtUser = txtUsername1.getColumns();
		assertEquals(10, columnsTxtUser);
		
		String textUser = txtUsername1.getText().toString();
		assertEquals("Usuario", textUser);
		Font fUser = txtUsername1.getFont();
		assertEquals(new Font("Arial",Font.BOLD,14), fUser);
	}
		
	@Test
	public void testTextPassword() {
		int xTxtPassword  = txtPassword1.getX();
		assertEquals(10, xTxtPassword);
		int yTxtPassword = txtPassword1.getY();
		assertEquals(10, yTxtPassword);
		int widthTxtPassword  = txtPassword1.getWidth();
		assertEquals(170, widthTxtPassword);
		int heightTxtPassword  = txtPassword1.getHeight();
		assertEquals(20, heightTxtPassword);
		
		String textPassword = txtPassword1.getText().toString();
		assertEquals("Contraseña", textPassword);
		Font fPassword = txtPassword1.getFont();
		assertEquals(new Font("Arial",Font.BOLD,14), fPassword);
	}
	
	@Test
	public void testPanel(){
		int xPanel  = panel.getX();
		assertEquals(173, xPanel);
		int yPanel = panel.getY();
		assertEquals(146, yPanel);
		int widthPanel  = panel.getWidth();
		assertEquals(250, widthPanel);
		int heightPanel  = panel.getHeight();
		assertEquals(40, heightPanel);
		
		Color cPanel = panel.getBackground();
		assertEquals(Color.WHITE, cPanel);	
	}
	@Test
	public void testPnlBtnLogin(){
		int xPnlBtnLogin  = pnlBtnLogin1.getX();
		assertEquals(327, xPnlBtnLogin);
		int yPnlBtnLogin = pnlBtnLogin1.getY();
		assertEquals(292, yPnlBtnLogin);
		int widthPnlBtnLogin  = pnlBtnLogin1.getWidth();
		assertEquals(198, widthPnlBtnLogin);
		int heightPnlBtnLogin  = pnlBtnLogin1.getHeight();
		assertEquals(51, heightPnlBtnLogin);
		
		Color cBtnLogin1 = pnlBtnLogin1.getBackground();
		assertEquals(new Color(47, 79, 79), cBtnLogin1);		
	}
	
	@Test
	public void testLblLogIn() {
		int xlblLogIn  = lblLogIn.getX();
		assertEquals(47, xlblLogIn);
		int ylblLogIn  = lblLogIn.getY();
		assertEquals(10, ylblLogIn);
		int widthlblLogIn  = lblLogIn.getWidth();
		assertEquals(125, widthlblLogIn);
		int heightlblLogIn  = lblLogIn.getHeight();
		assertEquals(31, heightlblLogIn);
		
		String textlblLogIn = lblLogIn.getText().toString();
		assertEquals("INICIAR SESIÓN", textlblLogIn);
		Font fUser = lblLogIn.getFont();
		assertEquals(new Font("Arial",Font.BOLD,14), fUser);
		
		Color clblLogIn = lblLogIn.getForeground();
		assertEquals(Color.WHITE, clblLogIn);
	}
	

}
