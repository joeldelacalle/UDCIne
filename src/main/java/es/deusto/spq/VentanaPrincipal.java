package es.deusto.spq;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

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
import java.awt.event.ActionEvent;

public class VentanaPrincipal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6673510127789501132L;
	private JPanel contentPane;
	private JList<String> listBillboard;
	private JList<String> listShoppingCart;
	private DefaultListModel<String> shoppingCart;
	private JSpinner spNumberTickets;
	private JComboBox<String> cbCinema;

	public VentanaPrincipal() {
		
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 501);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(64, 224, 208));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 139), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		cbCinema = new JComboBox<String>();
		cbCinema.setBounds(555, 54, 164, 22);
		cbCinema.addItem("");
		cbCinema.addItem("Cine 1");
		cbCinema.setFont(new Font("Tahoma", Font.BOLD, 20));
		contentPane.add(cbCinema);

		JLabel lblCinema = new JLabel("Cine:");
		lblCinema.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblCinema.setBounds(405, 56, 92, 18);
		contentPane.add(lblCinema);

		final DefaultListModel<String> billboard = new DefaultListModel<String>();

		/*
		 * habra que cambiar de string a pelicula y hacer un metodo que recorra y copie
		 * los parametros de la lista de peliculas al DefaultListModel
		 */

		billboard.add(0, "Pelicula1");
		billboard.add(1, "Pelicula2");
		billboard.add(2, "Pelicula3");
		billboard.add(3, "Pelicula4");
		billboard.add(4, "Pelicula5");

		listBillboard = new JList<String>(billboard);
		listBillboard.setBounds(10, 60, 385, 367);
		listBillboard.setFont(new Font("Tahoma", Font.BOLD, 20));
		contentPane.add(listBillboard);

		JLabel lblBillboard = new JLabel("CARTELERA");
		lblBillboard.setBounds(10, 10, 337, 39);
		lblBillboard.setFont(new Font("Tahoma", Font.BOLD, 20));
		contentPane.add(lblBillboard);

		JButton btnAdd = new JButton("añadir al carrito");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String a;
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
							shoppingCart.add(i, a);
							i++;
						}

					}
				}

			}
		});
		btnAdd.setBounds(184, 441, 195, 49);
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 20));
		contentPane.add(btnAdd);

		JLabel lblNumberTickets = new JLabel("Número de entradas:");
		lblNumberTickets.setBounds(404, 101, 184, 31);
		contentPane.add(lblNumberTickets);
		lblNumberTickets.setFont(new Font("Tahoma", Font.BOLD, 15));

		JButton btnFutureFilms = new JButton("Futuros estrenos");
		btnFutureFilms.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VentanaNuevosEstrenos vne = new VentanaNuevosEstrenos();
				vne.setVisible(true);
				dispose();
			}
		});
		btnFutureFilms.setBounds(389, 441, 205, 49);
		btnFutureFilms.setFont(new Font("Tahoma", Font.BOLD, 20));
		contentPane.add(btnFutureFilms);

		JButton btnFood = new JButton("Comida");
		btnFood.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VentanaAlimentosCine vac = new VentanaAlimentosCine();
				vac.setVisible(true);
				dispose();
			}
		});
		btnFood.setBounds(10, 441, 164, 49);
		btnFood.setFont(new Font("Tahoma", Font.BOLD, 20));
		contentPane.add(btnFood);

		spNumberTickets = new JSpinner();

		spNumberTickets.setModel(new SpinnerNumberModel(0, 0, 15, 1));
		spNumberTickets.setBounds(598, 99, 121, 31);
		spNumberTickets.setFont(new Font("Tahoma", Font.BOLD, 20));
		contentPane.add(spNumberTickets);

		shoppingCart = new DefaultListModel<String>();

		listShoppingCart = new JList<String>(shoppingCart);
		listShoppingCart.setFont(new Font("Tahoma", Font.BOLD, 20));
		listShoppingCart.setBounds(404, 143, 315, 248);

		contentPane.add(listShoppingCart);

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
		contentPane.add(lblX);

		JButton btnAsessment = new JButton("Valorar");
		btnAsessment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VentanaValoraciones vv = new VentanaValoraciones();
				vv.setVisible(true);
				dispose();
			}
		});
		btnAsessment.setBounds(604, 441, 136, 49);
		btnAsessment.setFont(new Font("Tahoma", Font.BOLD, 20));
		contentPane.add(btnAsessment);
		
		
		
		

	}
}
