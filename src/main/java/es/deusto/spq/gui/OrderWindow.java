/** \file 
 * Descripción de la Ventana OrderWindow es.deusto.spq.gui OrderWindow.java. May 18, 2021
 */

package es.deusto.spq.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.ListModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import es.deusto.spq.jdo.Cinema;
import es.deusto.spq.jdo.Film;
import es.deusto.spq.jdo.Order;
import es.deusto.spq.jdo.Product;
import es.deusto.spq.jdo.Room;
import es.deusto.spq.jdo.Ticket;
import es.deusto.spq.jdo.User;
import es.deusto.spq.resources.CinemaResource;
import es.deusto.spq.resources.RoomResource;
import es.deusto.spq.resources.UserResource;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;

/**
 * Ventana de pedidos
 */
public class OrderWindow extends JFrame {

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
	private List<Product> products = null;
	private int numberTickets = 0;
	private JLabel lblUserName = new JLabel("");
	private final DefaultListModel<Film> listModelShoppingCart;

	/**
	 * Obtener los productos Para la posterior generación del pedido
	 */
	public List<Product> getProducts() {
		return products;
	}

	/**
	 * Establecer los productos Para la posterior generación del pedido
	 */

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	private long totalPrice;

	/**
	 * Construir la ventana Order con sus atributos correspondientes
	 */
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

		listModelShoppingCart = new DefaultListModel<Film>();
		JList<Film> list = new JList<Film>(listModelShoppingCart);
		list.setBounds(409, 52, 276, 389);
		contentPane.add(list);

		JButton btnAdd = new JButton("Añadir al carrito");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				for (int i = 0; i < (Integer) spinner.getValue(); i++) {
					setListModelShoppingCart(listModelShoppingCart, selectedFilm);
				}
				numberTickets = numberTickets + (int) spinner.getValue();

			}

		});
		btnAdd.setBounds(38, 439, 158, 51);
		contentPane.add(btnAdd);

		JButton btnBuy = new JButton("Ir al pago");
		btnBuy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				CrearPedido(listModelShoppingCart, selectedFilm, lblUserName.getText());
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

		JButton btnComida = new JButton("Comida");
		btnComida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				initCinemaFoodWindow(selectedFilm, listModelShoppingCart, lblUserName);

			}
		});
		btnComida.setBounds(222, 439, 158, 51);
		contentPane.add(btnComida);

	}

	/**
	 * Establece el nickname de usuario en un JLabel
	 */
	public void SetUserName(User u) {
		this.lblUserName.setBounds(131, 24, 202, 26);
		this.lblUserName.setText(u.getNickname());
		this.contentPane.add(this.lblUserName);
	}

	/**
	 * Obtener el nickname de usuario en un JLabel
	 */

	public JLabel getLblUserName() {
		return lblUserName;
	}

	/**
	 * Creación de un pedido con los parámetros obtenidos dentro de esta ventana
	 */
	public void CrearPedido(final DefaultListModel<Film> listModelShoppingCart, Film selectedFilm, String nickName) {
		List<Ticket> tickets = new ArrayList<Ticket>();

		UserResource ur = new UserResource();

		User u = ur.getUser(nickName);

		Order o = new Order(u.getEmail(), Calendar.getInstance().getTime(), numberTickets, "", null, "En caja", 0);
		StringBuilder sb = new StringBuilder();
		StringBuilder sb2 = new StringBuilder();

		for (int i = 0; i < listModelShoppingCart.size(); i++) {

			Ticket ticketf = new Ticket((Cinema) cbCinema.getSelectedItem(), listModelShoppingCart.getElementAt(i),
					i + 1, i + 1, i + 1, 8, null);
			tickets.add(ticketf);

			int ticketNum = i + 1;
			sb.append(" Entrada:" + ticketNum);
			sb.append(" fila:" + tickets.get(i).getRow());
			sb.append(" asiento:" + tickets.get(i).getSeat());

			totalPrice = totalPrice + ticketf.getPrice();

		}

		if (products == null) {
			o.setProducts("Vacio");
		} else {

			for (int i = 0; i < products.size(); i++) {

				int productNum = i + 1;
				sb2.append(" Producto:" + productNum);
				sb2.append(" Nombre:" + products.get(i).getName());

				o.setNumberTickets(i);

				totalPrice = totalPrice + products.get(i).getPrice();
				o.setProducts("Productos" + sb2.toString());
			}
		}

		o.setTickets("pelicula:" + selectedFilm.getName() + sb.toString());

		o.setPrice(totalPrice);

		initPaymentWindow(o, ur);

	}

	public void setListModelShoppingCart(ListModel<Film> listModelShoppingCart, Film film) {
		this.listModelShoppingCart.addElement(film);
	}

	public void initCinemaFoodWindow(Film selectedFilm, ListModel<Film> listModelShoppingCart, JLabel lblUserName) {
		CinemaFoodWindow vac = new CinemaFoodWindow(selectedFilm, listModelShoppingCart);
		UserResource ur = new UserResource();
		vac.SetUserName(ur.getUser(lblUserName.getText()));
		vac.setVisible(true);
		dispose();
	}

	public void initPaymentWindow(Order o, UserResource ur) {
		PaymentWindow pw = new PaymentWindow(o);
		pw.SetUserName(ur.getUser(lblUserName.getText()));
		pw.setVisible(true);
		dispose();
	}
}
