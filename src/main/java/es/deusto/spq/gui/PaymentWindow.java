/** \file 
 * Descripción de la Ventana PaymentWindow es.deusto.spq.gui PaymentWindow.java. May 18, 2021
 */
package es.deusto.spq.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import es.deusto.spq.jdo.Order;
import es.deusto.spq.jdo.PayPal;
import es.deusto.spq.jdo.Receipt;
import es.deusto.spq.jdo.User;
import es.deusto.spq.resources.PagoResource;
import es.deusto.spq.resources.UserResource;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;

/**
 * Ventana de Pago.
 *
 */
public class PaymentWindow extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JTextField textField;
	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private JPasswordField passwordField;
	Client cliente = ClientBuilder.newClient();
	final WebTarget appTarget = cliente.target("http://localhost:8080/myapp");
	final WebTarget pagoTarget = appTarget.path("paypal");

	private JLabel lblUserName = new JLabel("");
	public final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	/**
	 * Construir la ventana de Pago con sus atributos correspondientes
	 */
	public PaymentWindow(final Order o) {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 689, 480);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(64, 224, 208));
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		final DefaultListModel<Order> listapedido = new DefaultListModel<Order>();
		JList<Order> list = new JList<Order>(listapedido);
		list.setBounds(45, 68, 275, 317);
		listapedido.addElement(o);
		contentPane.add(list);

		final JLabel lblNewLabel_1 = new JLabel("X");
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (JOptionPane.showConfirmDialog(null, "¿Seguro que quieres cerrar la aplicacion?", "Confirmacion",
						JOptionPane.YES_NO_OPTION) == 0) {
					dispose();
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				lblNewLabel_1.setForeground(Color.RED);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lblNewLabel_1.setForeground(Color.WHITE);
			}
		});
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1.setBounds(641, 24, 22, 14);
		contentPane.add(lblNewLabel_1);

		JButton btnNewButton = new JButton("Saltar pago");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PagarCaja(o);
			}

		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton.setBounds(440, 322, 136, 23);
		contentPane.add(btnNewButton);

		JLabel lblNewLabel = new JLabel("Paypal");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(475, 61, 78, 23);
		contentPane.add(lblNewLabel);

		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.BOLD, 12));
		textField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (textField.getText().equals("Correo electronico")) {
					textField.setText("");
				} else {
					textField.selectAll();
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (textField.getText().equals(""))
					textField.setText("Correo electronico");
			}
		});
		textField.setBounds(440, 115, 196, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.BOLD, 12));
		passwordField.setBounds(440, 155, 196, 20);
		contentPane.add(passwordField);

		JLabel lblNewLabel_2 = new JLabel("Contraseña:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2.setBounds(352, 158, 78, 14);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Correo:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_3.setBounds(352, 118, 46, 14);
		contentPane.add(lblNewLabel_3);

		JButton btnNewButton_1 = new JButton("Pagar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				PagarPaypal(o, textField, passwordField);
			}

		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton_1.setBounds(464, 201, 89, 23);
		contentPane.add(btnNewButton_1);

		JLabel lblNewLabel_4 = new JLabel("Pago en caja");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_4.setBounds(440, 272, 136, 23);
		contentPane.add(lblNewLabel_4);
	}

	/**
	 * Metodo para pagar con PayPal
	 */
	public void PagarPaypal(Order o, JTextField textField, JPasswordField passwordField) {
		String email = textField.getText();
		String contraseña = String.valueOf(passwordField.getPassword());

		if (!email.matches(EMAIL_PATTERN)) {
			JOptionPane.showMessageDialog(null, "Email no valido", "ERROR", JOptionPane.ERROR_MESSAGE);
		} else {

			PagoResource pr = new PagoResource();
			PayPal paypal = pr.getPaypal(email);

			if (paypal.getPassword().equals(contraseña)) {

				o.setPaymentMethod("Pagado con Paypal");
				String Email = o.getMail();
				String Paymentmethod = o.getPaymentMethod();
				String Products = o.getProducts();
				String Tickets = o.getTickets();
				long Price = o.getPrice();
				Date Fecha = o.getDate();
				Receipt r = new Receipt(Email, Fecha, o, Price);
				try {
					FileWriter archivo = new FileWriter("Facturas/" + Email + ".txt", true);

					PrintWriter escribir = new PrintWriter(archivo);

					String cadena = " -----------------" + "\r\n" + "|-Fecha de compra: " + Fecha + "\r\n" + "|-Email: "
							+ Email + "\r\n" + "|-Tickets: " + Tickets + "\r\n" + "|-Products: " + Products + "\r\n"
							+ "|-Paymentmethod: " + Paymentmethod + "\r\n" + "|-Price: " + Price + "\r\n"
							+ " -----------------";
					escribir.print(cadena);

					archivo.close();
				} catch (IOException e6) {
					logger.log(Level.WARNING, "ERROR", e6);
				}

				PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");

				PersistenceManager pm = pmf.getPersistenceManager();
				Transaction tx = pm.currentTransaction();

				System.out.println("Añadiendo pedido en la BD");

				try {
					tx.begin();

					mandarMensaje(Email, r);

					pm.makePersistent(r);
					pm.makePersistent(o);

					tx.commit();
					System.out.println("Añadido una nuevo pedido a la Base de Datos");

				} finally {
					if (tx.isActive()) {
						tx.rollback();
					}
					pm.close();
				}
				dispose();
				MainWindow mw = new MainWindow();
				mw.setVisible(true);
			} else {
				JOptionPane.showMessageDialog(null, "Usuario incorrecto");
			}
		}

	}

	/**
	 * Metodo para pagar En caja
	 */
	public void PagarCaja(Order o) {

		o.setPaymentMethod("Pendiente de pago");
		String Email = o.getMail();
		String Paymentmethod = o.getPaymentMethod();
		String Products = o.getProducts();
		String Tickets = o.getTickets();
		long Price = o.getPrice();
		Date Fecha = o.getDate();
		Receipt r = new Receipt(Email, Fecha, o, Price);

		try {
			FileWriter archivo = new FileWriter("Facturas/" + Email + ".txt", true);

			PrintWriter escribir = new PrintWriter(archivo);

			String cadena = " -----------------" + "\r\n" + "|-Fecha de compra: " + Fecha + "\r\n" + "|-Email: " + Email
					+ "\r\n" + "|-Tickets: " + Tickets + "\r\n" + "|-Products: " + Products + "\r\n"
					+ "|-Paymentmethod: " + Paymentmethod + "\r\n" + "|-Price: " + Price + "\r\n"
					+ " -----------------";
			escribir.print(cadena);

			archivo.close();
		} catch (IOException e6) {
			logger.log(Level.WARNING, "ERROR", e6);

		}
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");

		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();

		System.out.println("Añadiendo pedido en la BD");

		try {
			tx.begin();

			mandarMensaje(Email, r);

			pm.makePersistent(o);
			pm.makePersistent(r);

			tx.commit();
			System.out.println("Añadido una nuevo pedido a la Base de Datos");

		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
		dispose();
		initMainWindow();
	}

	/**
	 * Metodo para enviar factura por email
	 */
	public void mandarMensaje(String correo, Receipt recibo) {

		final String from = "pruebasjaimedeusto@gmail.com";
		final String contra = "deustocine";

		String host = "smtp.gmail.com";

		Properties properties = System.getProperties();

		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");

		Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

			protected PasswordAuthentication getPasswordAuthentication() {

				return new PasswordAuthentication(from, contra);

			}

		});
		session.setDebug(false);

		try {

			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(correo));
			message.setSubject("Confirmación de Reserva");
			message.setText("El código de tu reserva es: " + recibo.getOrder());

			System.out.println("Enviando mensaje...");
			Transport.send(message);
			System.out.println("El mensaje se ha enviado correctamente");

		} catch (MessagingException mex) {
			logger.log(Level.WARNING, "ERROR", mex);
		}
	}

	public void SetUserName(User u) {
		this.lblUserName.setBounds(525, 28, 46, 14);
		this.lblUserName.setText(u.getNickname());
		this.contentPane.add(this.lblUserName);
	}

	public void initMainWindow() {
		MainWindow mw = new MainWindow();
		UserResource ur = new UserResource();
		mw.SetUserName(ur.getUser(lblUserName.getText()));
		mw.setVisible(true);
		dispose();
	}

}
