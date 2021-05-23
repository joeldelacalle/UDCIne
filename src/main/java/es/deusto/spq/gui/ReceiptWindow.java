/** \file 
 * Descripción de la Ventana ReceiptWindow es.deusto.spq.gui ReceiptWindow.java. May 18, 2021
 */
package es.deusto.spq.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import es.deusto.spq.Receipt;
import es.deusto.spq.User;
import es.deusto.spq.jdo.UserResource;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;

/**
 * Ventana de recibos.
 *
 */
public class ReceiptWindow extends JFrame {

	private JPanel contentPane;
	private JLabel labeluser = new JLabel("");
	private JLabel labelmail = new JLabel("");
	Client cliente = ClientBuilder.newClient();
	final WebTarget appTarget = cliente.target("http://localhost:8080/myapp");
	final WebTarget receiptTarget = appTarget.path("receipts");
	public final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	/**
	 * Construir la ventana de Recibos con sus atributos correspondientes
	 */
	public ReceiptWindow() {
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 426, 521);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(72, 209, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Facturas");
		lblNewLabel.setFont(new Font("Cooper Black", Font.BOLD, 27));
		lblNewLabel.setBounds(131, 27, 140, 41);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Usuario conectado:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1.setBounds(20, 88, 218, 25);
		contentPane.add(lblNewLabel_1);

		final JLabel lblFlecha = new JLabel("<-");
		lblFlecha.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				initMainWindow(labeluser);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				lblFlecha.setForeground(Color.RED);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lblFlecha.setForeground(Color.WHITE);
			}
		});
		lblFlecha.setHorizontalAlignment(SwingConstants.CENTER);
		lblFlecha.setHorizontalTextPosition(SwingConstants.CENTER);
		lblFlecha.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblFlecha.setForeground(new Color(255, 255, 255));
		lblFlecha.setBounds(29, 27, 25, 31);
		contentPane.add(lblFlecha);

		final JLabel lblX = new JLabel("X");
		lblX.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (JOptionPane.showConfirmDialog(null, "¿Seguro que quieres cerrar la aplicacion?", "Confirmacion",
						JOptionPane.YES_NO_OPTION) == 0) {
					dispose();
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				lblX.setForeground(Color.RED);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lblX.setForeground(Color.WHITE);
			}
		});
		lblX.setHorizontalAlignment(SwingConstants.CENTER);
		lblX.setHorizontalTextPosition(SwingConstants.CENTER);
		lblX.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblX.setForeground(new Color(255, 255, 255));
		lblX.setBounds(367, 27, 19, 31);
		contentPane.add(lblX);

	}

	/**
	 * Establece el nickname de usuario en un JLabel
	 */
	public void SetUserName(User u) {
		this.labeluser.setBounds(250, 88, 151, 26);
		labeluser.setFont(new Font("Arial", Font.PLAIN, 20));
		this.labeluser.setText(u.getNickname());
		this.contentPane.add(this.labeluser);
	}

	/**
	 * Establece el mail de usuario en un JLabel
	 */
	public void SetEmail(User u) {
		this.labelmail.setBounds(200, 82, 151, 26);
		labelmail.setFont(new Font("Arial", Font.PLAIN, 20));
		this.labelmail.setText(u.getEmail());
		this.contentPane.add(this.labelmail);
		this.labelmail.setVisible(false);
		final DefaultListModel<Receipt> listamodelreceipt = new DefaultListModel<Receipt>();
		JList<Receipt> list = new JList<Receipt>(listamodelreceipt);
		list.setBounds(42, 141, 326, 299);
		contentPane.add(list);
		WebTarget ReceiptTarget = receiptTarget.path("getreceipt").queryParam("mail", labelmail.getText());
		GenericType<List<Receipt>> genericType = new GenericType<List<Receipt>>() {
		};
		List<Receipt> r = ReceiptTarget.request(MediaType.APPLICATION_JSON).get(genericType);
		for (Receipt receipt : r) {
			listamodelreceipt.addElement(receipt);
		}
	}

	public void initMainWindow(JLabel labeluser) {
		MainWindow vp = new MainWindow();
		UserResource ur = new UserResource();
		vp.SetUserName(ur.getUser(labeluser.getText()));
		vp.setVisible(true);
		dispose();
	}
}
