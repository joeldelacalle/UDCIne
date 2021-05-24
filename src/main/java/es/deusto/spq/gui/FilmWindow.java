/** \file 
 * Descripción de la Ventana FilmWindow es.deusto.spq.gui FilmWindow.java. May 20, 2021
 */
package es.deusto.spq.gui;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import es.deusto.spq.jdo.Billboard;
import es.deusto.spq.jdo.Film;
import es.deusto.spq.jdo.User;
import es.deusto.spq.resources.BillboardResource;
import es.deusto.spq.resources.UserResource;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericType;

/**
 * Ventana Pelicula
 */
public class FilmWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	Client client = ClientBuilder.newClient();

	final WebTarget appTarget = client.target("http://localhost:8080/myapp");
	final WebTarget FilmsTarget = appTarget.path("films");

	private GenericType<List<Film>> genericType = new GenericType<List<Film>>() {
	};
	private BillboardResource br = new BillboardResource();
	private List<Billboard> billFilms = br.getBillboardFilms();
	private List<Film> films = new ArrayList<Film>();

	private JPanel contentPane;
	private JTextField textFieldFilmName;
	private JLabel lblRecommendedAge = new JLabel();
	private JLabel lblFilmImage = new JLabel();
	private int ageFilm = -1;
	private String filmName = "-1";
	private String urlFilm = "-1";
	private String descFilm = "-1";
	private String trailerFilm = "-1";
	private JTextPane textPaneDescription = new JTextPane();
	private Film film;
	private int ageAll = 0; // apta para todos los publicos
	private int age7 = 7;
	private int age13 = 13;
	private int age16 = 16;
	private JLabel lblUserName = new JLabel("");

	public final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	/**
	 * Metodo para crear nuevas Peliculas
	 */
	public void nuevasPeliculas(final JComboBox<String> comboBoxFilm) {
		textFieldFilmName.setText(comboBoxFilm.getSelectedItem().toString());
		String selectedFilm = textFieldFilmName.getText();
		Image image = null;
		transformFilms(billFilms, films);

		for (Film film : films) {
			if (film.getName().equals(selectedFilm)) {
				ageFilm = film.getAgeRestriction();
				filmName = film.getName();
				urlFilm = film.getUrl();
				descFilm = film.getDescription();
				trailerFilm = film.getTrailer();
			}
		}
		
		if (filmName.equals(selectedFilm)) {
			try {
				URL url = new URL(urlFilm);
				image = ImageIO.read(url);
				ImageIcon myImg = new ImageIcon(url);
				image = myImg.getImage();

				int width = myImg.getIconWidth() / 7 * 2;
				int height = myImg.getIconHeight() / 7 * 2;

				Image newImg = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
				ImageIcon resizeImg = new ImageIcon(newImg);
				lblFilmImage.setIcon(resizeImg);
				textPaneDescription.setText(descFilm);
			} catch (IOException e7) {
			}
		}
		
		filmAgeRestImage();
	}

	/**
	 * Metodo para asignar una imagen para cada edad
	 */
	public void filmAgeRestImage() {
		if (ageAll == ageFilm) {
			try {
				URL url = new URL("https://upload.wikimedia.org/wikipedia/commons/6/68/Edad_TP.png");
				ageFilmIconResize(url);
			} catch (IOException e7) {
			}
		} else if (age7 == ageFilm) {
			try {
				URL url = new URL("https://upload.wikimedia.org/wikipedia/commons/5/55/Edad_7.png");
				ageFilmIconResize(url);
			} catch (IOException e7) {
			}

		} else if (age13 == ageFilm) {
			try {
				URL url = new URL("https://upload.wikimedia.org/wikipedia/commons/b/bd/Edad_13.png");
				ageFilmIconResize(url);
			} catch (IOException e7) {
			}

		} else if (age16 == ageFilm) {
			try {
				URL url = new URL("https://upload.wikimedia.org/wikipedia/commons/4/4b/Edad_16.png");
				ageFilmIconResize(url);
			} catch (IOException e7) {
			}

		} else {
			try {
				URL url = new URL("https://upload.wikimedia.org/wikipedia/commons/c/ca/Edad_18.png");
				ageFilmIconResize(url);
			} catch (IOException e7) {
			}
		}
	}

	/**
	 * Metodo para modificar la imagen asociada a cada edad
	 */
	public void ageFilmIconResize(URL url) throws IOException {
		Image image;
		image = ImageIO.read(url);
		ImageIcon myImg = new ImageIcon(url);
		image = myImg.getImage();

		int width = myImg.getIconWidth() / 5;
		int height = myImg.getIconHeight() / 5;

		Image newImg = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		ImageIcon resizeImg = new ImageIcon(newImg);
		lblRecommendedAge.setIcon(resizeImg);

	}

	/**
	 * Crea la ventana Pelicula
	 */
	public FilmWindow(int billboard) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 648, 588);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(64, 224, 208));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		lblFilmImage.setHorizontalAlignment(SwingConstants.CENTER);

		JButton btnExit = new JButton("SALIR");
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		JButton btnShowCinemas = new JButton("MOSTRAR CINES");
		btnShowCinemas.setFont(new Font("Tahoma", Font.BOLD, 10));

		JLabel lblDsc = new JLabel("Descripción:");
		lblDsc.setHorizontalAlignment(SwingConstants.CENTER);

		final JComboBox<String> comboBoxFilm = new JComboBox<String>();

		transformFilms(billFilms, films);
		for (Film film : films) {
			filmName = film.getName();
			comboBoxFilm.addItem(filmName);
		}

		// ESTO ESTA PARA QUE LA PRIMERA PELI QUE SALGA SEA LA SELECCIONADA EN LA
		// ANTERIOR VENTANA
		comboBoxFilm.setSelectedItem(films.get(billboard));

		film = films.get(billboard);
		ageFilm = film.getAgeRestriction();
		filmName = film.getName();
		urlFilm = film.getUrl();
		descFilm = film.getDescription();
		trailerFilm = film.getTrailer();
		Image image = null;
		try {
			URL url = new URL(urlFilm);
			image = ImageIO.read(url);
			ImageIcon myImg = new ImageIcon(url);
			image = myImg.getImage();

			int width = myImg.getIconWidth() / 7 * 2;
			int height = myImg.getIconHeight() / 7 * 2;

			Image newImg = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
			ImageIcon resizeImg = new ImageIcon(newImg);
			lblFilmImage.setIcon(resizeImg);
			textPaneDescription.setText(descFilm);
			// textFieldFilmName.setText(filmName); //NOSE PORQUE NO FUNCIONA
		} catch (IOException e7) {
		}

		filmAgeRestImage();
		// AQUI TERMINA EL METODO Y EMPIEZA EL DEL COMBOBOX

		comboBoxFilm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				nuevasPeliculas(comboBoxFilm);
			}
		});

		textFieldFilmName = new JTextField();
		textFieldFilmName.setEditable(false);
		textFieldFilmName.setBackground(new Color(64, 224, 208));
		textFieldFilmName.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldFilmName.setFont(new Font("Imprint MT Shadow", Font.PLAIN, 52));
		textFieldFilmName.setColumns(10);

		textPaneDescription.setBackground(new Color(64, 224, 208));

		JButton btnBuyTickets = new JButton("COMPRAR ENTRADAS");
		btnBuyTickets.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				OrderWindow ow = new OrderWindow(film);
				UserResource ur = new UserResource();
				ow.SetUserName(ur.getUser(lblUserName.getText()));
				ow.setVisible(true);
			}
		});

		JButton btnTrailer = new JButton("TRAILER");
		btnTrailer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					Desktop.getDesktop().browse(new URL(trailerFilm).toURI());
				} catch (IOException | URISyntaxException e1) {
					logger.log(Level.WARNING, "ERROR", e1);
					
				}
			}
		});

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING).addGroup(gl_contentPane
				.createSequentialGroup()
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup().addGap(65).addComponent(textFieldFilmName,
								GroupLayout.PREFERRED_SIZE, 302, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup().addGap(168).addComponent(lblFilmImage))
						.addGroup(gl_contentPane.createSequentialGroup().addGap(55).addGroup(gl_contentPane
								.createParallelGroup(Alignment.LEADING)
								.addComponent(comboBoxFilm, GroupLayout.PREFERRED_SIZE, 269, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane.createSequentialGroup().addGap(360).addComponent(
										lblRecommendedAge, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(
												lblDsc, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_contentPane.createSequentialGroup()
														.addComponent(btnExit, GroupLayout.DEFAULT_SIZE,
																GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addGap(42).addComponent(btnShowCinemas).addGap(18)
														.addComponent(btnBuyTickets)
														.addPreferredGap(ComponentPlacement.UNRELATED)
														.addComponent(btnTrailer, GroupLayout.PREFERRED_SIZE, 71,
																GroupLayout.PREFERRED_SIZE))
												.addComponent(textPaneDescription, GroupLayout.PREFERRED_SIZE, 392,
														GroupLayout.PREFERRED_SIZE))))))
				.addGap(11)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING).addGroup(gl_contentPane
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(comboBoxFilm, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED, 8, Short.MAX_VALUE).addComponent(
										textFieldFilmName, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblRecommendedAge, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(lblFilmImage, GroupLayout.PREFERRED_SIZE, 325, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblDsc, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
						.addComponent(textPaneDescription, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE))
				.addGap(2)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnShowCinemas, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)
						.addComponent(btnExit, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnBuyTickets)
						.addComponent(btnTrailer, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
				.addContainerGap()));
		contentPane.setLayout(gl_contentPane);
	}

	/**
	 * Metodo para transformar Peliculas
	 *
	 */
	public void SetUserName(User u) {
		this.lblUserName.setBounds(525, 28, 46, 14);
		this.lblUserName.setText(u.getNickname());
		this.contentPane.add(this.lblUserName);
	}

	public List<Film> transformFilms(List<Billboard> billFilms, List<Film> films) {

		for (int i = 0; i < billFilms.size(); i++) {

			films.add(new Film(billFilms.get(i)));

		}

		return films;

	}

}