/** \file 
 * Descripción de la Ventana MainWindow es.deusto.spq.gui MainWindow.java. May 18, 2021
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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import es.deusto.spq.jdo.Billboard;
import es.deusto.spq.jdo.Film;
import es.deusto.spq.jdo.User;
import es.deusto.spq.resources.BillboardResource;
import es.deusto.spq.resources.FilmResources;
import es.deusto.spq.resources.UserResource;

/**
 * Esta es la ventana principal de nuestra aplicacion
 */
public class MainWindow extends JFrame {

	private static final long serialVersionUID = 6673510127789501132L;
	private JPanel contentPane;
	private List<Film> films;
	FilmResources fr = new FilmResources();
	private JLabel lblUserName = new JLabel("");
	private JLabel lblEmail = new JLabel("");
	public final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	/**
	 * Construir la ventana main con sus atributos correspondientes
	 */
	public MainWindow() {

		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 501);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(64, 224, 208));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 139), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		final DefaultListModel<Film> billboard = new DefaultListModel<>();
		films = fr.getFilms();

		billboard.clear();
		for (Film film : films) {

			billboard.addElement(film);
		}

		JLabel lblBillboard = new JLabel("CARTELERA");
		lblBillboard.setBounds(10, 10, 337, 39);
		lblBillboard.setFont(new Font("Tahoma", Font.BOLD, 20));
		contentPane.add(lblBillboard);

		/**
		 * Botón que ejecuta la ventana de futuros estrenos pasandole los datos
		 * necesarios
		 */

		JButton btnFutureFilms = new JButton("Futuros estrenos");
		btnFutureFilms.setBounds(10, 429, 224, 61);
		btnFutureFilms.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				initLastReleasesWindow();
			}
		});
		btnFutureFilms.setFont(new Font("Tahoma", Font.BOLD, 20));
		contentPane.add(btnFutureFilms);

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

		/**
		 * Botón que ejecuta la ventana de valoraciones pasandole los datos necesarios
		 */
		JButton btnAsessment = new JButton("Valorar");
		btnAsessment.setBounds(587, 429, 153, 61);
		btnAsessment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				initRatingWindow(lblUserName);
			}
		});
		btnAsessment.setFont(new Font("Tahoma", Font.BOLD, 20));
		contentPane.add(btnAsessment);

		JButton btnFilm1 = new JButton();
		btnFilm1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				initFilmWindow(lblUserName, 0);
			}
		});
		btnFilm1.setBounds(36, 105, 130, 180);
		try {
			btnSetImageIcon(getBillboardFilm(0).getUrl(), btnFilm1);
		} catch (IOException e1) {
			logger.log(Level.WARNING, "ERROR", e1);
			
		}
		contentPane.add(btnFilm1);

		JButton btnFilm2 = new JButton("");
		btnFilm2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				initFilmWindow(lblUserName, 1);
			}
		});
		btnFilm2.setBounds(176, 105, 130, 180);
		try {
			btnSetImageIcon(getBillboardFilm(1).getUrl(), btnFilm2);
		} catch (IOException e1) {
			logger.log(Level.WARNING, "ERROR", e1);
			
		}
		contentPane.add(btnFilm2);

		JButton btnFilm3 = new JButton("");
		btnFilm3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				initFilmWindow(lblUserName, 2);
			}
		});
		btnFilm3.setBounds(316, 105, 130, 180);
		try {
			btnSetImageIcon(getBillboardFilm(2).getUrl(), btnFilm3);
		} catch (IOException e1) {
			logger.log(Level.WARNING, "ERROR", e1);
			
		}
		contentPane.add(btnFilm3);

		JButton btnFilm4 = new JButton("");
		btnFilm4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				initFilmWindow(lblUserName, 3);
			}
		});
		btnFilm4.setBounds(456, 105, 130, 180);

		try {
			btnSetImageIcon(getBillboardFilm(3).getUrl(), btnFilm4);

		} catch (IOException e1) {

		}

		contentPane.add(btnFilm4);

		/**
		 * Botón que ejecuta la ventana Vip pasandole los datos necesarios
		 */

		JButton btnVip = new JButton("VIP");
		btnVip.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnVip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				initVipWindow(lblUserName);
			}
		});
		btnVip.setBounds(412, 429, 165, 61);
		contentPane.add(btnVip);

		/**
		 * Botón que ejecuta la ventana de facturas pasandole los datos necesarios
		 */
		JButton btnNewButton = new JButton("Facturas");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				initReceiptWindow(lblUserName);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnNewButton.setBounds(244, 429, 158, 61);
		contentPane.add(btnNewButton);

	}

	/**
	 * obtiene una pelicula de la cartelera en base a la posicion que le des
	 */

	public Billboard getBillboardFilm(int i) {

		BillboardResource br = new BillboardResource();
		List<Billboard> bl = br.getBillboardFilms();
		Billboard billboardFilm = bl.get(i);
		return billboardFilm;

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
	 * Establece el mail en un JLabel
	 */

	public void SetEmail(User u) {
		this.lblEmail.setBounds(131, 24, 202, 26);
		this.lblEmail.setText(u.getEmail());
		this.contentPane.add(this.lblEmail);
	}

	/**
	 * Establece el icono de cada boton
	 */

	public void btnSetImageIcon(String urlS, JButton jb) throws IOException {
		URL url = new URL(urlS);

		Image image;
		image = ImageIO.read(url);
		ImageIcon myImg = new ImageIcon(url);
		image = myImg.getImage();

		int width = myImg.getIconWidth() / 5;
		int height = myImg.getIconHeight() / 5;

		Image newImg = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		ImageIcon resizeImg = new ImageIcon(newImg);
		jb.setIcon(resizeImg);
	}

	/**
	 * metodo para iniciar la ventana ReceiptWindow desde la MainWindow
	 *
	 */
	public void initReceiptWindow(JLabel lblUserName) {
		ReceiptWindow rw = new ReceiptWindow();
		UserResource ur = new UserResource();
		rw.SetUserName(ur.getUser(lblUserName.getText()));
		rw.SetEmail(ur.getUser(lblUserName.getText()));
		rw.setVisible(true);
	}

	/**
	 * metodo para iniciar la ventana VipWindow desde la MainWindow
	 *
	 */
	public void initVipWindow(JLabel lblUserName) {
		VipWindow vw = new VipWindow();
		UserResource ur = new UserResource();
		vw.SetUserName(ur.getUser(lblUserName.getText()));
		vw.setVisible(true);
	}

	/**
	 * metodo para iniciar la ventana FilmWindow desde la MainWindow
	 *
	 */
	public void initFilmWindow(JLabel lblUserName, int i) {
		FilmWindow fw = new FilmWindow(i);
		UserResource ur = new UserResource();
		fw.SetUserName(ur.getUser(lblUserName.getText()));
		fw.setVisible(true);
	}

	/**
	 * metodo para iniciar la ventana RatingWindow desde la MainWindow
	 *
	 */

	public void initRatingWindow(JLabel lblUserName) {
		RatingWindow vv = new RatingWindow();
		UserResource ur = new UserResource();
		vv.SetUserName(ur.getUser(lblUserName.getText()));
		vv.setVisible(true);
		dispose();
	}

	/**
	 * metodo para iniciar la ventana ReleasesWindow desde la MainWindow
	 *
	 */
	public void initLastReleasesWindow() {
		LastReleasesWindow vne = new LastReleasesWindow();
		vne.setVisible(true);
		dispose();
	}
}
