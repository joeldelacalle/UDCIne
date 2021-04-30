package es.deusto.spq.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import es.deusto.spq.Cinema;
import es.deusto.spq.Film;
import es.deusto.spq.Order;
import es.deusto.spq.Ticket;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class OrderWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JSpinner spinner;
	Client client = ClientBuilder.newClient();

	final WebTarget appTarget = client.target("http://localhost:8080/myapp");
	final WebTarget CinemasTarget = appTarget.path("cinemas");
	private GenericType<List<Cinema>> genericTypeC = new GenericType<List<Cinema>>() {
	};
	private List<Cinema> cinemas = CinemasTarget.request(MediaType.APPLICATION_JSON).get(genericTypeC);
	private JComboBox<Cinema> cbCinema;

	public OrderWindow(final Film selectedFilm) {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 501);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(64, 224, 208));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 139), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);
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

		JLabel lblFilmName = new JLabel(selectedFilm.getName());
		lblFilmName.setBounds(10, 22, 396, 62);
		contentPane.add(lblFilmName);

		spinner = new JSpinner();
		spinner.setBounds(164, 95, 64, 51);
		contentPane.add(spinner);

		JLabel lblNumTickets = new JLabel("Número de Entradas");
		lblNumTickets.setBounds(10, 95, 139, 51);
		contentPane.add(lblNumTickets);

		JComboBox cbSession = new JComboBox();
		cbSession.setBounds(164, 235, 190, 62);
		contentPane.add(cbSession);

		JLabel lblSession = new JLabel("Sesión");
		lblSession.setBounds(10, 235, 139, 62);
		contentPane.add(lblSession);

		final DefaultListModel<Film> listModelShoppingCart = new DefaultListModel<Film>();
		JList<Film> list = new JList<Film>(listModelShoppingCart);
		list.setBounds(409, 52, 276, 389);
		contentPane.add(list);

		JButton btnAdd = new JButton("Añadir al carrito");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				for (int i = 0; i < (Integer) spinner.getValue(); i++)
					listModelShoppingCart.addElement(selectedFilm);
			}
		});
		btnAdd.setBounds(38, 452, 139, 38);
		contentPane.add(btnAdd);

		JButton btnBuy = new JButton("Comprar");
		btnBuy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				ComprarPedido(listModelShoppingCart);

			}

			private void ComprarPedido(final DefaultListModel<Film> listModelShoppingCart) {
				List<Ticket> tickets = new ArrayList<Ticket>();

				for (int i = 0; i < listModelShoppingCart.size(); i++) {
					Ticket ticketf = new Ticket((Cinema) cbCinema.getSelectedItem(),
							listModelShoppingCart.getElementAt(i), 1, 1, 1, 0, null);
					tickets.add(ticketf);
				}

				Order o = new Order("ejemplo", Calendar.getInstance().getTime(), tickets, null, "En caja", 0);
				PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");

				PersistenceManager pm = pmf.getPersistenceManager();
				Transaction tx = pm.currentTransaction();

				System.out.println("Añadiendo pedido en la BD");

				try {
					tx.begin();

					pm.makePersistent(o);

					tx.commit();
					System.out.println("Añadido una nueva pedido a la Base de Datos");

				} finally {
					if (tx.isActive()) {
						tx.rollback();
					}
					pm.close();
				}
			}
		});
		btnBuy.setBounds(409, 452, 276, 38);
		contentPane.add(btnBuy);

		JLabel lblCinema = new JLabel("Cine");
		lblCinema.setBounds(10, 157, 139, 62);
		contentPane.add(lblCinema);

		cbCinema = new JComboBox<Cinema>();
		cbCinema.setBounds(164, 157, 190, 62);

		for (Cinema cinema : cinemas) {
			cbCinema.addItem(cinema);
		}

		contentPane.add(cbCinema);

	}
}
