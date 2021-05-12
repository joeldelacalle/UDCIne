package es.deusto.spq.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import es.deusto.spq.Order;
import es.deusto.spq.PayPal;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.logging.Level;

import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PaymentWindow extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private JPasswordField passwordField;
	Client cliente = ClientBuilder.newClient();
	final WebTarget appTarget = cliente.target("http://localhost:8080/myapp");
	final WebTarget pagoTarget = appTarget.path("paypal");

	
	public PaymentWindow(final Order o) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 689, 480);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(64, 224, 208));
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		final DefaultListModel<String> listapedido = new DefaultListModel<String>();
		JList<String> list = new JList<String>(listapedido);
		list.setBounds(45, 68, 275, 317);
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
				PagarCaja();
			}

			private void PagarCaja() {
				String[] opciones = { "Aceptar", "Cancelar" };
				String confirmacion = "¿Estas seguro de que quieres pagar en la caja?";
				int respuesta = JOptionPane.showOptionDialog(null, confirmacion, "¿Estas seguro?",
						JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
				switch (respuesta) {
				case 0:
					JOptionPane.showMessageDialog(null, "Se ha llevado a cabo la reserva, pendiente de pago");
					o.setPaymentMethod("Pendiente de pago");
					String Email = o.getMail();
					String Paymentmethod = o.getPaymentMethod();
					String Products = o.getProducts();
					String Tickets = o.getTickets();
					long Price = o.getPrice();
					Date Fecha = o.getDate();
					
					try{
						FileWriter archivo = new FileWriter("Facturas/"+Email+".txt", true);
			
						PrintWriter escribir = new PrintWriter(archivo);
			
						String cadena = " -----------------" + "\r\n" + "|-Fecha de compra: " + Fecha + "\r\n" +"|-Email: " + Email + "\r\n" +"|-Tickets: " + Tickets + "\r\n" + "|-Products: " + Products +"\r\n" + "|-Paymentmethod: " + Paymentmethod + "\r\n" + "|-Price: " + Price + "\r\n" + " -----------------";
						escribir.print(cadena);
			
						archivo.close();
					}catch(IOException e6) {
						e6.printStackTrace();
					}
					PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");

					PersistenceManager pm = pmf.getPersistenceManager();
					Transaction tx = pm.currentTransaction();

					System.out.println("Añadiendo pedido en la BD");

					try {
						tx.begin();

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
					break;
				case 1:
					JOptionPane.showMessageDialog(null, "Se ha cancelado la operacion");
					break;
				default:
					break;
				}
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

				PagarPaypal(o);
			}

			private void PagarPaypal(Order o) {
				String email = textField.getText();
				String contraseña = String.valueOf(passwordField.getPassword());
				String[] opciones = { "Aceptar", "Cancelar" };
				String confirmacion = "¿Estas seguro de que quieres pagar con PayPal?";
				int respuesta = JOptionPane.showOptionDialog(null, confirmacion, "¿Estas seguro?",
						JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
				switch (respuesta) {
				case 0:
					if (!email.matches(EMAIL_PATTERN)) {
						JOptionPane.showMessageDialog(null, "Email no valido", "ERROR", JOptionPane.ERROR_MESSAGE);
					}else{
						WebTarget PaypalTarget = pagoTarget.path("getemail").queryParam("email", email);
						GenericType<PayPal> genericType = new GenericType<PayPal>() {};
						PayPal paypal = PaypalTarget.request(MediaType.APPLICATION_JSON).get(genericType);
						if(paypal.getPassword().equals(contraseña)) {
							JOptionPane.showMessageDialog(null, "Usuario correcto, se ha llevado a cabo la reserva, pagada");
							o.setPaymentMethod("Pagado con Paypal");
							String Email = o.getMail();
							String Paymentmethod = o.getPaymentMethod();
							String Products = o.getProducts();
							String Tickets = o.getTickets();
							long Price = o.getPrice();
							Date Fecha = o.getDate();
							
							try{
								FileWriter archivo = new FileWriter("Facturas/"+Email+".txt", true);
					
								PrintWriter escribir = new PrintWriter(archivo);
					
								String cadena = " -----------------" + "\r\n" + "|-Fecha de compra: " + Fecha + "\r\n" +"|-Email: " + Email + "\r\n" +"|-Tickets: " + Tickets + "\r\n" + "|-Products: " + Products +"\r\n" + "|-Paymentmethod: " + Paymentmethod + "\r\n" + "|-Price: " + Price + "\r\n" + " -----------------";
								escribir.print(cadena);
					
								archivo.close();
							}catch(IOException e6) {
								e6.printStackTrace();
							}
							
							PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");

							PersistenceManager pm = pmf.getPersistenceManager();
							Transaction tx = pm.currentTransaction();

							System.out.println("Añadiendo pedido en la BD");

							try {
								tx.begin();

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
						}else {
							JOptionPane.showMessageDialog(null, "Usuario incorrecto");
						}
					}
					break;
				case 1:
					JOptionPane.showMessageDialog(null, "Se ha cancelado la operacion");
					break;
				default:
					break;
				}
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
}
