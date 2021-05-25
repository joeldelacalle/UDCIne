/** \file 
 * Descripción de la Ventana AdminUsersWindow es.deusto.spq.gui AdminUsersWindow.java. May 20, 2021
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
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import es.deusto.spq.jdo.User;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import javax.swing.JScrollPane;

/**
 * Ventana Administrador para usuarios
 */
public class AdminUsersWindow extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JList<User> lista;

	Client client = ClientBuilder.newClient();
	final WebTarget appTarget = client.target("http://localhost:8080/myapp");
	final WebTarget UsersTarget = appTarget.path("users");
	final WebTarget UsersallTarget = UsersTarget.path("allusers");

	/**
	 * Crea la ventana de Administracion de usuarios
	 */
	public AdminUsersWindow() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 613, 501);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(64, 224, 208));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 139), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		final DefaultListModel<User> listausuarios = new DefaultListModel<User>();
		GenericType<List<User>> genericType = new GenericType<List<User>>() {
		};
		List<User> users = UsersallTarget.request(MediaType.APPLICATION_JSON).get(genericType);

		listausuarios.clear();
		for (User user : users) {
			listausuarios.addElement(user);
		}

		final JLabel lblX = new JLabel("X");
		lblX.setBounds(584, 10, 19, 31);
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
		lista = new JList<User>(listausuarios);
		lista.setBounds(50, 126, 512, 281);
		
		JScrollPane scrollPane = new JScrollPane(lista);
		scrollPane.setBounds(45, 126, 517, 281);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		contentPane.add(scrollPane);
		lblX.setHorizontalAlignment(SwingConstants.CENTER);
		lblX.setHorizontalTextPosition(SwingConstants.CENTER);
		lblX.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblX.setForeground(new Color(255, 255, 255));
		contentPane.add(lblX);

		final JLabel lblFlecha = new JLabel("<-");
		lblFlecha.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				initAdminWindow();
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

		JButton btnNewButton = new JButton("Eliminar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				eliminarUsuarioBd(lista, lista.getSelectedIndex());
				rerunAdminUsersWindow();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnNewButton.setBounds(217, 432, 183, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblUsuarios = new JLabel("USUARIOS");
		lblUsuarios.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblUsuarios.setBounds(256, 85, 160, 31);
		contentPane.add(lblUsuarios);
	}

	/**
	 * Metodo que elimina usuarios de la Base de datos
	 */
	public void eliminarUsuarioBd(JList<User> lUser, int selectedUser) {
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		System.out.println("Eliminando usuario de la BD");

		try {
			tx.begin();
			User user = lUser.getModel().getElementAt(selectedUser);
			User u = pm.getObjectById(User.class, user.getId());
			pm.deletePersistent(u);

			tx.commit();
			System.out.println("Eliminada usuario de la Base de Datos");

		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Metodo para volver a ejecutar la adminUsersWindow
	 */
	public void rerunAdminUsersWindow() {

		AdminUsersWindow auw = new AdminUsersWindow();
		auw.setVisible(true);
		dispose();

	}

	/**
	 * Metodo para iniciar la AdminWindow
	 */
	public void initAdminWindow() {
		AdminWindow aw = new AdminWindow();
		aw.setVisible(true);
		dispose();
	}
}
