package es.deusto.spq.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import es.deusto.spq.Film;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.MouseWheelListener;
import java.awt.event.MouseWheelEvent;

public class MainWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6673510127789501132L;
	
	Client client = ClientBuilder.newClient();

	final WebTarget appTarget = client.target("http://localhost:8080/myapp");
	final WebTarget FilmsTarget = appTarget.path("films");
	
	private JPanel contentPane;
	private JList<Film> listBillboard;
	private JList<String> listShoppingCart;
	private DefaultListModel<String> shoppingCart;
	private JSpinner spNumberTickets;
	private JComboBox<String> cbCinema;

	public MainWindow() {
		
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 501);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(64, 224, 208));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 139), 2));
		setContentPane(contentPane);

		cbCinema = new JComboBox<String>();
		cbCinema.setBounds(555, 54, 164, 22);
		cbCinema.addItem("");
		cbCinema.addItem("Cine 1");
		contentPane.setLayout(null);
		cbCinema.setFont(new Font("Tahoma", Font.BOLD, 20));
		contentPane.add(cbCinema);

		JLabel lblCinema = new JLabel("Cine:");
		lblCinema.setBounds(405, 56, 92, 18);
		lblCinema.setFont(new Font("Tahoma", Font.BOLD, 20));
		contentPane.add(lblCinema);

		
		
		
		final DefaultListModel<Film> billboard = new DefaultListModel<>();

		GenericType<List<Film>> genericType = new GenericType<List<Film>>() {};
		List<Film> films = FilmsTarget.request(MediaType.APPLICATION_JSON).get(genericType);

		billboard.clear();
		for (Film film : films) {
			System.out.println(film.getName());
			billboard.addElement(film);
		}
		
		
		/*
		 * habra que cambiar de string a pelicula y hacer un metodo que recorra y copie
		 * los parametros de la lista de peliculas al DefaultListModel
		 */

		
		listBillboard = new JList<Film>(billboard);
		listBillboard.addMouseWheelListener(new MouseWheelListener() {
			public void mouseWheelMoved(MouseWheelEvent e) {
				FilmWindow fw = new FilmWindow();
        		fw.setVisible(true);
        		dispose();
			}
		});
		listBillboard.setBounds(1, 1, 383, 365);
		listBillboard.setFont(new Font("Tahoma", Font.BOLD, 20));
		contentPane.add(listBillboard);

		JLabel lblBillboard = new JLabel("CARTELERA");
		lblBillboard.setBounds(10, 10, 337, 39);
		lblBillboard.setFont(new Font("Tahoma", Font.BOLD, 20));
		contentPane.add(lblBillboard);

		JButton btnAdd = new JButton("añadir al carrito");
		btnAdd.setBounds(184, 441, 195, 49);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Film a;
				int cinema = cbCinema.getSelectedIndex();
				if (cinema == 0) {
					JOptionPane.showMessageDialog(null, "Seleccione el cine");
				} else {
					int numberTickets = (int) spNumberTickets.getValue();
					if (numberTickets == 0) {
						JOptionPane.showMessageDialog(null, "Seleccione número de entradas");
					} else {
						for (int j = 0; j < numberTickets; j++) {
							int i = 0;
							a = listBillboard.getSelectedValue();
							shoppingCart.add(i, a.getName());
							i++;
						}

					}
				}

			}
		});
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 20));
		contentPane.add(btnAdd);

		JLabel lblNumberTickets = new JLabel("Número de entradas:");
		lblNumberTickets.setBounds(404, 101, 184, 31);
		contentPane.add(lblNumberTickets);
		lblNumberTickets.setFont(new Font("Tahoma", Font.BOLD, 15));

		JButton btnFutureFilms = new JButton("Futuros estrenos");
		btnFutureFilms.setBounds(389, 441, 205, 49);
		btnFutureFilms.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LastReleasesWindow vne = new LastReleasesWindow();
				vne.setVisible(true);
				dispose();
			}
		});
		btnFutureFilms.setFont(new Font("Tahoma", Font.BOLD, 20));
		contentPane.add(btnFutureFilms);

		JButton btnFood = new JButton("Comida");
		btnFood.setBounds(10, 441, 164, 49);
		btnFood.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CinemaFoodWindow vac = new CinemaFoodWindow();
				vac.setVisible(true);
				dispose();
			}
		});
		btnFood.setFont(new Font("Tahoma", Font.BOLD, 20));
		contentPane.add(btnFood);

		spNumberTickets = new JSpinner();
		spNumberTickets.setBounds(598, 99, 121, 31);

		spNumberTickets.setModel(new SpinnerNumberModel(0, 0, 15, 1));
		spNumberTickets.setFont(new Font("Tahoma", Font.BOLD, 20));
		contentPane.add(spNumberTickets);

		shoppingCart = new DefaultListModel<String>();

		listShoppingCart = new JList<String>(shoppingCart);
		listShoppingCart.setBounds(404, 143, 315, 248);
		listShoppingCart.setFont(new Font("Tahoma", Font.BOLD, 20));

		contentPane.add(listShoppingCart);

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

		JButton btnAsessment = new JButton("Valorar");
		btnAsessment.setBounds(604, 441, 136, 49);
		btnAsessment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RatingWindow vv = new RatingWindow();
				vv.setVisible(true);
				dispose();
			}
		});
		btnAsessment.setFont(new Font("Tahoma", Font.BOLD, 20));
		contentPane.add(btnAsessment);
	
		
		JScrollPane scrollPane = new JScrollPane(listBillboard);
		scrollPane.setBounds(10, 60, 385, 367);
		contentPane.add(scrollPane);
		
		
		
		

	}
}
