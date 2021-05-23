/** \file 
 * Descripción de la Ventana AdminFilmsWindow es.deusto.spq.gui AdminFilmsWindow.java. May 18, 2021
 */
package es.deusto.spq.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import es.deusto.spq.Film;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;

/**
 * Ventana Administrador para Películas.
 *
 */
public class AdminFilmsWindow extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JList<Film> listBillboard;
	private JLabel lblMessage = new JLabel("");

	Client client = ClientBuilder.newClient();
	final WebTarget appTarget = client.target("http://localhost:8080/myapp");
	final WebTarget FilmsTarget = appTarget.path("films");
	private JTextField textFieldTrailer = new JTextField();

	// private FilmResources fr;
	// private List<Film> films = fr.getFilms();

	/**
	 * Crea la ventana de Administración de películas
	 */

	public AdminFilmsWindow() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 501);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(64, 224, 208));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 139), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		final DefaultListModel<Film> billboard = new DefaultListModel<>();
		GenericType<List<Film>> genericType = new GenericType<List<Film>>() {
		};
		List<Film> films = FilmsTarget.request(MediaType.APPLICATION_JSON).get(genericType);

		billboard.clear();
		for (Film film : films) {
			// System.out.println(film.getName());
			billboard.addElement(film);
		}

		listBillboard = new JList<Film>(billboard);
		listBillboard.setBounds(50, 50, 380, 350);
		listBillboard.setFont(new Font("Tahoma", Font.BOLD, 20));
		contentPane.add(listBillboard);

		final JLabel lblX = new JLabel("X");
		lblX.setBounds(707, 10, 19, 31);
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
		contentPane.add(lblX);

		final JLabel lblFlecha = new JLabel("<-");
		lblFlecha.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AdminWindow aw = new AdminWindow();
				aw.setVisible(true);
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
		lblFlecha.setBounds(50, 10, 25, 31);
		contentPane.add(lblFlecha);

		final JTextField txtName = new JTextField();
		txtName.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (txtName.getText().equals("Título")) {
					txtName.setText("");
				} else {
					txtName.selectAll();
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (txtName.getText().equals(""))
					txtName.setText("Título");
			}
		});
		txtName.setBorder(null);
		txtName.setFont(new Font("Arial", Font.BOLD, 14));
		txtName.setText("Título");
		txtName.setBounds(500, 50, 170, 20);
		txtName.setColumns(10);
		contentPane.add(txtName);

		final JTextField txtDirector = new JTextField();
		txtDirector.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (txtDirector.getText().equals("Director")) {
					txtDirector.setText("");
				} else {
					txtDirector.selectAll();
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (txtDirector.getText().equals(""))
					txtDirector.setText("Director");
			}
		});
		txtDirector.setBorder(null);
		txtDirector.setFont(new Font("Arial", Font.BOLD, 14));
		txtDirector.setText("Director");
		txtDirector.setBounds(500, 90, 170, 20);
		txtDirector.setColumns(10);
		contentPane.add(txtDirector);

		final JTextField txtFoto = new JTextField();
		txtFoto.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (txtFoto.getText().equals("Url Cartel")) {
					txtFoto.setText("");
				} else {
					txtFoto.selectAll();
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (txtFoto.getText().equals(""))
					txtFoto.setText("Url Cartel");
			}
		});
		txtFoto.setBorder(null);
		txtFoto.setFont(new Font("Arial", Font.BOLD, 14));
		txtFoto.setText("Url Cartel");
		txtFoto.setBounds(500, 170, 170, 20);
		txtFoto.setColumns(10);
		contentPane.add(txtFoto);

		final JComboBox<Integer> cbAge = new JComboBox<>();
		cbAge.addItem(0);
		cbAge.addItem(7);
		cbAge.addItem(13);
		cbAge.addItem(16);
		cbAge.addItem(18);
		cbAge.setBounds(500, 130, 170, 20);
		cbAge.setFont(new Font("Arial", Font.BOLD, 14));
		contentPane.add(cbAge);

		/**
		 * La text area contiene un método para que no se pueda escribir más de 255
		 * caracteres, incluso si se pega un texto más largo
		 */
		final JTextArea txtDescription = new JTextArea();
		txtDescription.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				maxLengthdesc( txtDescription);
				e.consume();
			}

			@Override
			public void keyPressed(KeyEvent e) {

			}

			@Override
			public void keyReleased(KeyEvent e) {

			}
		});

		txtDescription.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (txtDescription.getText().equals("Descripción (max 255)")) {
					txtDescription.setText("");
				} else {
					txtDescription.selectAll();
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (txtDescription.getText().equals(""))
					txtDescription.setText("Descripción (max 255)");
			}
		});
		txtDescription.setLineWrap(true);
		txtDescription.setWrapStyleWord(true);
		txtDescription.setBorder(null);
		txtDescription.setFont(new Font("Arial", Font.BOLD, 14));
		txtDescription.setText("Descripción (max 255)");
		txtDescription.setColumns(10);

		JScrollPane areaScrollPane = new JScrollPane(txtDescription);
		areaScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		areaScrollPane.setBounds(500, 247, 170, 190);
		contentPane.add(areaScrollPane);

		JButton btnAdd = new JButton("Añadir");
		btnAdd.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {

			}

			@Override
			public void mousePressed(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {

			}

			@Override
			public void mouseEntered(MouseEvent e) {

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				añadirPeliculaBd(txtName, txtDirector, txtFoto, cbAge, txtDescription,
						textFieldTrailer.getText().toString());

			}
		});
		btnAdd.setBounds(481, 447, 200, 30);
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 20));
		contentPane.add(btnAdd);

		JButton btnDelete = new JButton("Eliminar");
		btnDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				eliminarPeliculaBd(listBillboard.getModel(), listBillboard.getSelectedValue().getName());
			}
		});
		btnDelete.setBounds(115, 430, 200, 30);
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 20));
		contentPane.add(btnDelete);

		lblMessage.setForeground(new Color(128, 0, 0));
		lblMessage.setFont(new Font("Arial", Font.PLAIN, 12));
		lblMessage.setBounds(500, 410, 250, 19);
		contentPane.add(lblMessage);

		textFieldTrailer.setText("Url Trailer");
		textFieldTrailer.setFont(new Font("Arial", Font.BOLD, 14));
		textFieldTrailer.setColumns(10);
		textFieldTrailer.setBorder(null);
		textFieldTrailer.setBounds(500, 210, 170, 20);
		contentPane.add(textFieldTrailer);

	}

	/**
	 * Añade una nueva película a la BD
	 */
	public void añadirPeliculaBd(JTextField txtName, JTextField txtDirector, JTextField txtFoto,
			JComboBox<Integer> cbAge, JTextArea txtDescription, String trailer) {
		if (txtName.getText().equals("") || txtName.getText().equals("Título") || txtDirector.getText().equals("")
				|| txtDirector.getText().equals("Director") || txtDescription.getText().equals("")
				|| txtDescription.getText().equals("Descripción (max 255)") || txtFoto.getText().equals("")
				|| txtFoto.getText().equals("Url Cartel")) {

			lblMessage.setText("Por favor rellena los campos!");

		} else {
			PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");

			PersistenceManager pm = pmf.getPersistenceManager();
			Transaction tx = pm.currentTransaction();
			int age = Integer.parseInt(cbAge.getSelectedItem().toString());
			System.out.println("Añadiendo película en la BD");

			try {
				tx.begin();
				Film film = new Film(txtDirector.getText().toString(), txtName.getText().toString(),
						txtDescription.getText().toString(), age, txtFoto.getText().toString(), trailer);
				pm.makePersistent(film);

				tx.commit();
				System.out.println("Añadido una nueva película a la Base de Datos");

			} finally {
				if (tx.isActive()) {
					tx.rollback();
				}
				pm.close();

				AdminFilmsWindow afw = new AdminFilmsWindow();
				afw.setVisible(true);
				dispose();

			}
		}
	}

	/**
	 * Elimina la película seleccionada de la lista de la BD
	 */
	public void eliminarPeliculaBd(ListModel<Film> listModel, String selectedFilm) {
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
		PersistenceManager pm = pmf.getPersistenceManager();
		System.out.println("Eliminando película de la BD");

		try {
			// Film f = pm.getObjectById(Film.class, filmList.get(0).getId());
			Query<Film> q = pm.newQuery("SELECT FROM " + Film.class.getName() + " WHERE name== '" + selectedFilm + "'");
			List<Film> filmList = q.executeList();
			q.deletePersistentAll(filmList);
			// pm.deletePersistent(f);

			System.out.println("Eliminada película de la Base de Datos");

		} finally {
			pm.close();

			AdminFilmsWindow afw = new AdminFilmsWindow();
			afw.setVisible(true);
			dispose();
		}
	}
	/**
	 * metodo para el tamaño de la imagen de la edad en un ultimo estreno
	 *
	 */

	public void maxLengthdesc( JTextArea txtDescription) {

		int max = 255;
		if (txtDescription.getText().length() > max + 1) {
			String shortened = txtDescription.getText().substring(0, max);
			txtDescription.setText(shortened);
		} else if (txtDescription.getText().length() > max) {
			
		}

	}
}