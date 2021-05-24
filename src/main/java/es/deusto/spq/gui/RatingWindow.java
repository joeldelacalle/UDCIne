/** \file 
 * Descripción de la Ventana RatingWindow es.deusto.spq.gui RatingWindow.java. May 18, 2021
 */
package es.deusto.spq.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import es.deusto.spq.jdo.Assessment;
import es.deusto.spq.jdo.Cinema;
import es.deusto.spq.jdo.Film;
import es.deusto.spq.jdo.User;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
/**
 * Ventana de valoraciones.
 *
 */
public class RatingWindow extends JFrame {


	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	JLabel lblX1 = new JLabel("X");
	private JLabel labeluser = new JLabel("");

	Client client = ClientBuilder.newClient();

	final WebTarget appTarget = client.target("http://localhost:8080/myapp");
	final WebTarget FilmsTarget = appTarget.path("films");
	final WebTarget CinemasTarget = appTarget.path("cinemas");

	/**
	 * Construir la ventana de Valoraciones con sus atributos correspondientes
	 */
	public RatingWindow() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 754, 562);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(72, 209, 204));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("AREA PERSONAL Y VALORACIONES");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Cooper Black", Font.BOLD, 27));
		lblNewLabel.setBounds(88, 28, 539, 27);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Usuario conectado:");
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_1.setBounds(210, 82, 191, 27);
		contentPane.add(lblNewLabel_1);

		final JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(29, 206, 97, 22);
		contentPane.add(comboBox);

		GenericType<List<Film>> genericType = new GenericType<List<Film>>() {
		};
		List<Film> films = FilmsTarget.request(MediaType.APPLICATION_JSON).get(genericType);

		for (Film film : films) {
			System.out.println(film.getName());
			comboBox.addItem(film.getName());
		}

		final JComboBox<String> comboBox_1 = new JComboBox<String>();
		comboBox_1.setBounds(350, 206, 103, 22);
		contentPane.add(comboBox_1);

		GenericType<List<Cinema>> genericType2 = new GenericType<List<Cinema>>() {
		};
		List<Cinema> cinemas = CinemasTarget.request(MediaType.APPLICATION_JSON).get(genericType2);

		for (Cinema cinema : cinemas) {
			System.out.println(cinema.getName());
			comboBox_1.addItem(cinema.getName());
		}

		final JLabel lblNewLabel_3 = new JLabel("Peliculas");
		lblNewLabel_3.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_3.setBounds(29, 181, 70, 14);
		contentPane.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Cines");
		lblNewLabel_4.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_4.setBounds(350, 181, 46, 14);
		contentPane.add(lblNewLabel_4);

		final DefaultListModel<String> listmodelpelis = new DefaultListModel<String>();
		JList<String> list = new JList<String>(listmodelpelis);
		list.setBounds(29, 270, 275, 227);
		contentPane.add(list);

		final DefaultListModel<String> listmodelcines = new DefaultListModel<String>();
		JList<String> list_1 = new JList<String>(listmodelcines);
		list_1.setBounds(350, 270, 288, 227);
		contentPane.add(list_1);

		textField = new JTextField();
		textField.setBounds(29, 239, 181, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setBounds(350, 239, 191, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		JButton btnNewButton_1 = new JButton("A\u00F1adir");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AñadirValoracionPeli(comboBox, listmodelpelis, labeluser, textField);
			}

		});
		btnNewButton_1.setFont(new Font("Arial", Font.BOLD, 16));
		btnNewButton_1.setBounds(220, 236, 84, 23);
		contentPane.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("A\u00F1adir");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AñadirValoracionCine(comboBox_1, listmodelcines, labeluser, textField_1);
			}

		});
		btnNewButton_2.setFont(new Font("Arial", Font.BOLD, 16));
		btnNewButton_2.setBounds(551, 236, 87, 23);
		contentPane.add(btnNewButton_2);

		Border border = BorderFactory.createLineBorder(Color.black, 2);
		JLabel lblNewLabel_1_1 = new JLabel("Valorar pelicula");
		lblNewLabel_1_1.setBorder(border);
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("Cooper Black", Font.BOLD, 20));
		lblNewLabel_1_1.setBounds(67, 128, 191, 42);
		contentPane.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_2 = new JLabel("Valorar cine");
		lblNewLabel_1_2.setBorder(border);
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_2.setFont(new Font("Cooper Black", Font.BOLD, 20));
		lblNewLabel_1_2.setBounds(401, 128, 191, 42);
		contentPane.add(lblNewLabel_1_2);

		/*
		 * JButton btnNewButton = new JButton("Exit"); btnNewButton.setFont(new
		 * Font("Cooper Black", Font.PLAIN, 16)); btnNewButton.setBounds(658, 448, 70,
		 * 49); contentPane.add(btnNewButton);
		 */

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
		lblX.setBounds(707, 10, 19, 31);

		final JLabel lblFlecha = new JLabel("<-");
		lblFlecha.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MainWindow vp = new MainWindow();
				vp.setVisible(true);
				dispose();
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

		lblX1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblX1.setForeground(Color.WHITE);
		lblX1.setBounds(698, 35, 46, 14);
		contentPane.add(lblX1);
		lblX1.setVisible(true);

		lblX1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (JOptionPane.showConfirmDialog(null, "¿Seguro que quieres cerrar la aplicacion?", "Confirmacion",
						JOptionPane.YES_NO_OPTION) == 0) {
					dispose();
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				lblX1.setForeground(Color.RED);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lblX1.setForeground(Color.WHITE);
			}
		});

	}

	/**
	 * Establece el nickname de usuario en un JLabel
	 */
	public void SetUserName(User u) {
		this.labeluser.setBounds(401, 82, 151, 26);
		labeluser.setFont(new Font("Arial", Font.PLAIN, 20));
		this.labeluser.setText(u.getNickname());
		this.contentPane.add(this.labeluser);
	}

	/**
	 * Metodo para añadir una valoracion sobre una pelicula
	 */
	public void AñadirValoracionPeli(final JComboBox<String> comboBox, final DefaultListModel<String> listmodelpelis,
			JLabel labeluser, JTextField textField) {
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");

		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		String user = labeluser.getText();
		String name = comboBox.getSelectedItem().toString();
		String text = textField.getText();
		System.out.println("Añadiendo Valoracion en la BD");
		if (user.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Porfavor, escribe tu usuario", "ERROR", JOptionPane.ERROR_MESSAGE);
		} else if (text.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Porfavor, escribe tu valoracion", "ERROR", JOptionPane.ERROR_MESSAGE);
		} else {
			try {
				String valoracionpeli = textField.getText();
				listmodelpelis.addElement(valoracionpeli);
				tx.begin();
				Assessment assessment = new Assessment(user, name, text);
				pm.makePersistent(assessment);

				tx.commit();
				System.out.println("Añadido una nueva valoracion a la Base de Datos");

			} finally {
				if (tx.isActive()) {
					tx.rollback();
				}
				pm.close();
			}
		}
	}

	/**
	 * Metodo para añadir una valoracion sobre un cine
	 */
	public void AñadirValoracionCine(final JComboBox<String> comboBox_1, final DefaultListModel<String> listmodelcines,
			JLabel labeluser, JTextField textField_1) {
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");

		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		String user = labeluser.getText();
		String name = comboBox_1.getSelectedItem().toString();
		String text = textField_1.getText();
		System.out.println("Añadiendo Valoracion en la BD");
		if (user.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Porfavor, escribe tu usuario", "ERROR", JOptionPane.ERROR_MESSAGE);
		} else if (text.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Porfavor, escribe tu valoracion", "ERROR", JOptionPane.ERROR_MESSAGE);
		} else {
			try {
				String valoracioncine = textField_1.getText();
				listmodelcines.addElement(valoracioncine);
				tx.begin();
				Assessment assessment = new Assessment(user, name, text);
				pm.makePersistent(assessment);

				tx.commit();
				System.out.println("Añadido una nueva valoracion a la Base de Datos");

			} finally {
				if (tx.isActive()) {
					tx.rollback();
				}
				pm.close();
			}
		}
	}
}
