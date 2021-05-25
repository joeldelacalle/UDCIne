/** \file 
 * Descripción de la Ventana CinemaLoginWindow es.deusto.spq.gui CinemaLoginWindow.java. May 20, 2021
 */
package es.deusto.spq.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import es.deusto.spq.resources.UserResource;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;

/**
 * Ventana de login
 *
 */
public class CinemaLoginWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	Client client = ClientBuilder.newClient();

	final WebTarget appTarget = client.target("http://localhost:8080/myapp");
	final WebTarget FilmsTarget = appTarget.path("films");
	private JPanel contentPane;
	private JTextField txtUsername1;
	private JPasswordField txtPassword1;
	private JLabel lblLoginMessage1 = new JLabel("");
	private JLabel lblGest;

	public final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	/**
	 * Metodo main. Crea la ventana de login
	 *
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					CinemaLoginWindow frame = new CinemaLoginWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					logger.log(Level.WARNING, "ERROR", e);
				}
			}
		});
	}

	/**
	 * Crea la ventana de login.
	 *
	 */
	public CinemaLoginWindow() {

		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();

		contentPane.setBackground(new Color(72, 209, 204));
		contentPane.setBorder(new LineBorder(new Color(85, 107, 47), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(173, 146, 250, 40);
		contentPane.add(panel);
		panel.setLayout(null);

		txtUsername1 = new JTextField();
		txtUsername1.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (txtUsername1.getText().equals("Username")) {
					txtUsername1.setText("");
				} else {
					txtUsername1.selectAll();
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (txtUsername1.getText().equals(""))
					txtUsername1.setText("Username");
			}
		});
		txtUsername1.setBorder(null);
		txtUsername1.setFont(new Font("Arial", Font.BOLD, 14));
		txtUsername1.setText("Usuario");
		txtUsername1.setBounds(10, 10, 170, 20);
		panel.add(txtUsername1);
		txtUsername1.setColumns(10);

		JPanel lblIconPassword1 = new JPanel();
		lblIconPassword1.setBackground(Color.WHITE);
		lblIconPassword1.setBounds(173, 213, 250, 40);
		contentPane.add(lblIconPassword1);
		lblIconPassword1.setLayout(null);

		txtPassword1 = new JPasswordField();
		txtPassword1.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (txtPassword1.getText().equals("Contraseña")) {
					txtPassword1.setText("");
					txtPassword1.setEchoChar('\u25CF');
					
				} else {
					txtPassword1.selectAll();
					txtPassword1.setEchoChar('\u25CF');
				}

			}

			@Override
			public void focusLost(FocusEvent e) {
				if (txtPassword1.getText().equals("")) {
					txtPassword1.setText("Password");
					txtPassword1.setEchoChar((char) 0);
				}
			}
		});
		txtPassword1.setBorder(null);
		txtPassword1.setEchoChar((char) 0);
		txtPassword1.setFont(new Font("Arial", Font.BOLD, 14));
		txtPassword1.setText("Contraseña");
		txtPassword1.setBounds(10, 10, 170, 20);
		lblIconPassword1.add(txtPassword1);

		final JPanel pnlBtnLogin1 = new JPanel();
		pnlBtnLogin1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Login(txtPassword1, txtUsername1);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				pnlBtnLogin1.setBackground(new Color(20, 30, 40));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				pnlBtnLogin1.setBackground(new Color(47, 79, 79));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				pnlBtnLogin1.setBackground(new Color(60, 80, 90));
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				pnlBtnLogin1.setBackground(new Color(20, 30, 40));
			}
		});
		pnlBtnLogin1.setBackground(new Color(47, 79, 79));
		pnlBtnLogin1.setBounds(327, 292, 198, 51);
		contentPane.add(pnlBtnLogin1);
		pnlBtnLogin1.setLayout(null);

		final JLabel lblLogIn = new JLabel("INICIAR SESIÓN");
		lblLogIn.setForeground(Color.WHITE);
		lblLogIn.setFont(new Font("Arial", Font.BOLD, 14));
		lblLogIn.setBounds(47, 10, 125, 31);
		pnlBtnLogin1.add(lblLogIn);

		final JLabel lblX = new JLabel("X");
		lblX.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (JOptionPane.showConfirmDialog(null, "Seguro que quieres salir de la aplicacion?", "Confirmar",
						JOptionPane.YES_NO_OPTION) == 0) {
					CinemaLoginWindow.this.dispose();
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				lblX.setForeground(Color.red);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lblX.setForeground(Color.WHITE);
			}
		});
		lblX.setForeground(Color.WHITE);
		lblX.setFont(new Font("Arial", Font.BOLD, 25));
		lblX.setHorizontalAlignment(SwingConstants.CENTER);
		lblX.setBounds(563, 0, 37, 34);
		contentPane.add(lblX);

		lblLoginMessage1.setForeground(new Color(128, 0, 0));
		lblLoginMessage1.setFont(new Font("Arial", Font.PLAIN, 12));
		lblLoginMessage1.setBounds(173, 263, 250, 19);
		contentPane.add(lblLoginMessage1);

		JLabel lblCineDeusto = new JLabel("CINE DEUSTO");
		lblCineDeusto.setFont(new Font("Cooper Black", Font.BOLD, 33));
		lblCineDeusto.setToolTipText("");
		lblCineDeusto.setBounds(175, 68, 299, 34);
		contentPane.add(lblCineDeusto);

		lblGest = new JLabel("Entrar como invitado");
		lblGest.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblGest.setForeground(Color.RED);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lblGest.setForeground(Color.BLUE);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				MainWindow vp = new MainWindow();
				vp.setVisible(true);
				dispose();
			}
		});
		lblGest.setForeground(Color.BLUE);
		lblGest.setBounds(237, 362, 151, 14);
		contentPane.add(lblGest);

		JButton btnRegister = new JButton("REGISTRARSE");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterWindow rw = new RegisterWindow();
				rw.setVisible(true);
				dispose();
			}
		});
		btnRegister.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnRegister.setForeground(Color.WHITE);
		btnRegister.setBackground(new Color(47, 79, 79));
		btnRegister.setBounds(59, 292, 176, 51);
		contentPane.add(btnRegister);

	}

	/**
	 * Metodo que para hacer login. Hay varias comprobaciones para que no se permita
	 * añadir un campo vacio y para que el usuario y contraseña sean correctos
	 *
	 */
	public void Login(JPasswordField txtPassword1, JTextField txtUsername1) {
		UserResource ur = new UserResource();
		if (txtUsername1.getText().equals("admin") && txtPassword1.getText().equals("admin")) {
			lblLoginMessage1.setText("");
			AdminWindow aw = new AdminWindow();
			aw.setVisible(true);
			dispose();
		} else if (ur.CheckUser(txtUsername1.getText(), txtPassword1.getText().toString())) {
			lblLoginMessage1.setText("te has loggeado correctamente!");
			MainWindow vp = new MainWindow();
			vp.SetUserName(ur.getUser(txtUsername1.getText()));
			vp.setVisible(true);
			dispose();

		} else if (txtUsername1.getText().equals("") || txtUsername1.getText().equals("Username")
				|| txtPassword1.getText().equals("") || txtPassword1.getText().equals("Password")) {
			lblLoginMessage1.setText("Por favor rellena los campos!");
		} else {
			lblLoginMessage1.setText("Usuario y contraseña no coinciden");
		}
	}

}
