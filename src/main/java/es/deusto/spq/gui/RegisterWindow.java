/** \file 
 * Descripción de la Ventana RegisterWindow es.deusto.spq.gui RegisterWindow.java. May 18, 2021
 */
package es.deusto.spq.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import es.deusto.spq.jdo.User;

/**
 * Ventana de registro.
 *
 */
public class RegisterWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldPhoneNumber = new JTextField();
	private JTextField textFieldName = new JTextField();
	private JTextField textFieldNickname = new JTextField();
	private JTextField textFieldEmail = new JTextField();
	private JPasswordField passwordField = new JPasswordField();
	private int phone;
	public final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	/**
	 * Crea la ventana de registro
	 */
	public RegisterWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBackground(new Color(64, 224, 208));
		setBounds(100, 100, 638, 519);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(64, 224, 208));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textFieldName.setBounds(214, 139, 276, 19);
		getContentPane().add(textFieldName);
		textFieldName.setColumns(10);

		textFieldNickname.setColumns(10);
		textFieldNickname.setBounds(214, 183, 276, 19);
		getContentPane().add(textFieldNickname);

		textFieldEmail.setColumns(10);
		textFieldEmail.setBounds(214, 231, 276, 19);
		getContentPane().add(textFieldEmail);

		textFieldPhoneNumber.setColumns(10);
		textFieldPhoneNumber.setBounds(214, 324, 276, 19);
		getContentPane().add(textFieldPhoneNumber);

		passwordField.setBounds(214, 278, 276, 19);
		getContentPane().add(passwordField);

		JLabel lblNewLabel = new JLabel("Nombre y apellido:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(47, 139, 138, 19);
		getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Apodo:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(47, 183, 138, 19);
		getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Correo electrónico:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(47, 231, 138, 19);
		getContentPane().add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Contraseña:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(47, 278, 138, 19);
		getContentPane().add(lblNewLabel_3);

		JLabel lblNmeroDeTelfono = new JLabel("Número de teléfono:");
		lblNmeroDeTelfono.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNmeroDeTelfono.setBounds(47, 324, 138, 19);
		getContentPane().add(lblNmeroDeTelfono);

		JButton btnNewButton = new JButton("SALIR");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CinemaLoginWindow cl = new CinemaLoginWindow();
				cl.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton.setBounds(121, 411, 138, 33);
		getContentPane().add(btnNewButton);

		JButton btnRegistrarse = new JButton("REGISTRARSE");
		btnRegistrarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Registro(textFieldPhoneNumber, textFieldName, textFieldNickname, textFieldEmail, passwordField);

			}

		});
		btnRegistrarse.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnRegistrarse.setBounds(325, 411, 164, 33);
		getContentPane().add(btnRegistrarse);

	}

	/**
	 * Crea en la base de datos un usuario
	 */
	public void Registro(JTextField textFieldPhoneNumber, JTextField textFieldName, JTextField textFieldNickname,
			JTextField textFieldEmail, JPasswordField passwordField) {
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");

		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		phone = Integer.parseInt(textFieldPhoneNumber.getText().toString());
		System.out.println("Añadiendo usuario en la BD");
		try {
			tx.begin();
			User user = new User(textFieldName.getText().toString(), textFieldNickname.getText().toString(),
					textFieldEmail.getText().toString(), passwordField.getText().toString(), phone);
			pm.makePersistent(user);

			tx.commit();
			System.out.println("Añadido un nuevo usuario a la Base de Datos");

		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
	}

}
