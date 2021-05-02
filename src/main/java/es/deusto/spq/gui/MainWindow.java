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

import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import es.deusto.spq.Film;
import es.deusto.spq.jdo.FilmResources;

public class MainWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6673510127789501132L;
	private JPanel contentPane;
	private List<Film> films;
	FilmResources fr = new FilmResources();

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
			System.out.println(film.getName());
			billboard.addElement(film);
		}

		JLabel lblBillboard = new JLabel("CARTELERA");
		lblBillboard.setBounds(10, 10, 337, 39);
		lblBillboard.setFont(new Font("Tahoma", Font.BOLD, 20));
		contentPane.add(lblBillboard);

		JButton btnFutureFilms = new JButton("Futuros estrenos");
		btnFutureFilms.setBounds(184, 441, 205, 49);
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

		JButton btnFilm1 = new JButton();
		btnFilm1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FilmWindow fw = new FilmWindow(0);
				fw.setVisible(true);
			}
		});
		btnFilm1.setBounds(36, 105, 130, 180);
		try {
			btnSetImageIcon(films.get(0).getUrl(), btnFilm1);
		} catch (IOException e1) {

			e1.printStackTrace();
		}
		contentPane.add(btnFilm1);

		JButton btnFilm2 = new JButton("");
		btnFilm2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FilmWindow fw = new FilmWindow(1);
				fw.setVisible(true);
			}
		});
		btnFilm2.setBounds(176, 105, 130, 180);
		try {
			btnSetImageIcon(films.get(1).getUrl(), btnFilm2);
		} catch (IOException e1) {

			e1.printStackTrace();
		}
		contentPane.add(btnFilm2);

		JButton btnFilm3 = new JButton("");
		btnFilm3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FilmWindow fw = new FilmWindow(2);
				fw.setVisible(true);
			}
		});
		btnFilm3.setBounds(316, 105, 130, 180);
		try {
			btnSetImageIcon(films.get(2).getUrl(), btnFilm3);
		} catch (IOException e1) {

			e1.printStackTrace();
		}
		contentPane.add(btnFilm3);

		JButton btnFilm4 = new JButton("");
		btnFilm4.setBounds(456, 105, 130, 180);

		// todavia no hay suficientes peliculas
		/*
		 * try { btnSetImageIcon(films.get(0).getUrl(),btnFilm1); } catch (IOException
		 * e1) {
		 */
		contentPane.add(btnFilm4);

	}

	public void btnSetImageIcon(String urlS, JButton jb) throws IOException {
		URL url = new URL(urlS);

		Image image;
		image = ImageIO.read(url);
		ImageIcon myImg = new ImageIcon(url);
		image = myImg.getImage();

		int width = myImg.getIconWidth() / 5;
		// System.out.println(width);
		int height = myImg.getIconHeight() / 5;
		// System.out.println(height);

		Image newImg = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		ImageIcon resizeImg = new ImageIcon(newImg);
		jb.setIcon(resizeImg);
	}
}
