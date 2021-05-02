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
import es.deusto.spq.Room;
import es.deusto.spq.Ticket;
import es.deusto.spq.User;
import es.deusto.spq.jdo.CinemaResource;
import es.deusto.spq.jdo.RoomResource;
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

	
	private CinemaResource cr = new CinemaResource();
	private List<Cinema> cinemas = cr.getReleases();
	private JComboBox<Cinema> cbCinema;
	private RoomResource rr = new RoomResource();
	private List<Room> rooms = rr.getReleases();
	private JComboBox<Room> cbSession;

	private long totalPrice;

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

		cbSession = new JComboBox<Room>();
		cbSession.setBounds(164, 235, 190, 62);
		for (Room room : rooms) {
			cbSession.addItem(room);
		}
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

				ComprarPedido(listModelShoppingCart, selectedFilm);

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

	private void ComprarPedido(final DefaultListModel<Film> listModelShoppingCart, Film selectedFilm) {
		List<Ticket> tickets = new ArrayList<Ticket>();

		Order o = new Order("ejemplo", Calendar.getInstance().getTime(), 0, "", null, "En caja", 0);
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < listModelShoppingCart.size(); i++) {

			Ticket ticketf = new Ticket((Cinema) cbCinema.getSelectedItem(), listModelShoppingCart.getElementAt(i),
					i + 1, i + 1, i + 1, 8, null);
			tickets.add(ticketf);

			int ticketNum = i + 1;
			sb.append(" Entrada:" + ticketNum);
			sb.append(" fila:" + tickets.get(i).getRow());
			sb.append(" asiento:" + tickets.get(i).getSeat());

			o.setNumberTickets(i);

			totalPrice = totalPrice + ticketf.getPrice();

		}
		o.setTickets("pelicula:" + selectedFilm.getName() + sb.toString());
		o.setPrice(totalPrice);

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
	}
}
