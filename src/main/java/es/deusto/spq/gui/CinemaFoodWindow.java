/** \file 
 * Descripción de la Ventana CinemaFoodWindow es.deusto.spq.gui CinemaFoodWindow.java. May 20, 2021
 */
package es.deusto.spq.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ListModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import es.deusto.spq.jdo.Film;
import es.deusto.spq.jdo.Product;
import es.deusto.spq.jdo.User;
import es.deusto.spq.resources.ProductResource;
import es.deusto.spq.resources.UserResource;

/**
 * Ventana de alimentos
 *
 */
public class CinemaFoodWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	private JList<Product> list;
	private DefaultListModel<Product> listmodelAlimentos;
	private ProductResource pr = new ProductResource();
	private List<Product> products = pr.getProducts();
	private JLabel lblUserName = new JLabel("");

	/**
	 * Crea la ventana de alimentos
	 *
	 */
	public CinemaFoodWindow(final Film selectedFilm, final ListModel<Film> listModelShoppingCart) {

		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 501);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(64, 224, 208));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 139), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);

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

		final JLabel lblFlecha = new JLabel("<-");
		lblFlecha.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				initMainWindow();
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

		JLabel lblPopcornImage = new JLabel("");
		lblPopcornImage.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				listmodelAlimentos.addElement(products.get(0));
			}
		});
		lblPopcornImage.setBounds(47, 76, 161, 179);
		contentPane.add(lblPopcornImage);
		try {
			btnSetImageIcon(products.get(0).getUrl(), lblPopcornImage);
		} catch (IOException e) {
		}

		JLabel lblPopcornCola = new JLabel("");
		lblPopcornCola.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				listmodelAlimentos.addElement(products.get(1));
			}
		});
		lblPopcornCola.setBounds(480, 85, 166, 157);
		contentPane.add(lblPopcornCola);

		try {
			btnSetImageIcon(products.get(1).getUrl(), lblPopcornCola);
		} catch (IOException e) {
		}

		JLabel lblPopcornBig = new JLabel("");
		lblPopcornBig.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				listmodelAlimentos.addElement(products.get(1));
			}
		});
		lblPopcornBig.setBounds(245, 85, 192, 157);
		contentPane.add(lblPopcornBig);
		try {
			btnSetImageIcon(products.get(2).getUrl(), lblPopcornBig);
		} catch (IOException e) {
		}
		listmodelAlimentos = new DefaultListModel<Product>();
		list = new JList<Product>(listmodelAlimentos);
		list.setBounds(47, 332, 390, 158);
		contentPane.add(list);

		JLabel lblNewLabel = new JLabel("PALOMITAS MEDIANAS");
		lblNewLabel.setFont(new Font("Cooper Black", Font.PLAIN, 12));
		lblNewLabel.setBounds(47, 250, 173, 41);
		contentPane.add(lblNewLabel);

		JLabel lblPalomitasGrandes = new JLabel("PALOMITAS GRANDES");
		lblPalomitasGrandes.setFont(new Font("Cooper Black", Font.PLAIN, 12));
		lblPalomitasGrandes.setBounds(264, 252, 173, 41);
		contentPane.add(lblPalomitasGrandes);

		JLabel lblPalomitasGrandes_1 = new JLabel("PALOMITAS COCA COLA");
		lblPalomitasGrandes_1.setToolTipText("");
		lblPalomitasGrandes_1.setFont(new Font("Cooper Black", Font.PLAIN, 12));
		lblPalomitasGrandes_1.setBounds(490, 243, 156, 54);
		contentPane.add(lblPalomitasGrandes_1);

		JLabel lblListaDeCompra = new JLabel("LISTA DE COMPRA");
		lblListaDeCompra.setFont(new Font("Cooper Black", Font.PLAIN, 15));
		lblListaDeCompra.setBounds(50, 302, 173, 19);
		contentPane.add(lblListaDeCompra);

		JButton btnAadir = new JButton("AÑADIR AL CARRITO");
		btnAadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				initOrderWindow(selectedFilm, listModelShoppingCart, listmodelAlimentos);
			}
		});
		btnAadir.setBackground(new Color(0, 153, 153));
		btnAadir.setForeground(new Color(0, 0, 0));
		btnAadir.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnAadir.setBounds(468, 386, 234, 60);
		contentPane.add(btnAadir);

	}

	/**
	 * Metodo para añadir productos
	 *
	 */
	public void addProducts(OrderWindow ow, DefaultListModel<Product> listmodelAlimentos) {
		List<Product> listProducts = new ArrayList<Product>();

		for (int i = 0; i < listmodelAlimentos.size(); i++) {
			listProducts.add(listmodelAlimentos.get(i));
		}

		ow.setProducts(listProducts);
	}

	/**
	 * Metodo para establecer la imagen. Ajusta la altura y anchura de la imagen.
	 * 
	 */
	public void btnSetImageIcon(String urlS, JLabel jb) throws IOException {
		URL url = new URL(urlS);

		Image image;
		image = ImageIO.read(url);
		ImageIcon myImg = new ImageIcon(url);
		image = myImg.getImage();

		int width = myImg.getIconWidth() / 3;
		int height = myImg.getIconHeight() / 3;

		Image newImg = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		ImageIcon resizeImg = new ImageIcon(newImg);
		jb.setIcon(resizeImg);
	}

	/**
	 * metodo para pasar el usuario de ventna en ventana
	 *
	 */
	public void SetUserName(User u) {
		this.lblUserName.setBounds(131, 24, 202, 26);
		this.lblUserName.setText(u.getNickname());
		this.contentPane.add(this.lblUserName);
	}

	/**
	 * Metodo inicio de la ventana pedido
	 *
	 */
	public void initOrderWindow(Film selectedFilm, ListModel<Film> listModelShoppingCart,
			DefaultListModel<Product> listmodelAlimentos) {
		OrderWindow ow = new OrderWindow(selectedFilm);
		addProducts(ow, listmodelAlimentos);
		UserResource ur = new UserResource();
		ow.SetUserName(ur.getUser(lblUserName.getText()));
		ow.setVisible(true);
		ow.setListModelShoppingCart(listModelShoppingCart, selectedFilm);
		dispose();
	}

	/**
	 * Metodo inicio de la ventana principal
	 *
	 */
	public void initMainWindow() {
		MainWindow mw = new MainWindow();
		UserResource ur = new UserResource();
		mw.SetUserName(ur.getUser(lblUserName.getText()));
		mw.setVisible(true);
		dispose();
	}
}
