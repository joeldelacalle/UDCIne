/** \file 
 * Descripción de la Ventana LastReleasesWindow es.deusto.spq.gui LastReleasesWindow.java. May 20, 2021
 */
package es.deusto.spq.gui;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URISyntaxException;
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
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import es.deusto.spq.jdo.Release;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;

/**
 * Ventana ultimos estrenos
 */
public class LastReleasesWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	Client client = ClientBuilder.newClient();

	final WebTarget appTarget = client.target("http://localhost:8080/myapp");
	final WebTarget ReleaseTarget = appTarget.path("release");

	private JPanel contentPane;
	private JLabel lblFilmImage = new JLabel("");
	private JTextPane textDescription = new JTextPane();
	private JLabel lblAgeRestriction = new JLabel("");
	private JTextField textFieldReleaseTitle = new JTextField();
	private JList<Release> listReleases;

	private int ageAll = 0; // apta para todos los publicos
	private int age7 = 7;
	private int age13 = 13;
	private int age16 = 16;
	private String trailer = null;
	private JButton btnNewButton;

	public final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	/**
	 * Crea la ventana de ultimos estrenos
	 */
	public LastReleasesWindow() {
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
					LastReleasesWindow.this.dispose();
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
				MainWindow vp = new MainWindow();
				vp.setVisible(true);
				dispose();
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

		JLabel lblNuevosEstrenos = new JLabel("NUEVOS ESTRENOS");
		lblNuevosEstrenos.setHorizontalAlignment(SwingConstants.CENTER);
		lblNuevosEstrenos.setFont(new Font("Cooper Black", Font.BOLD, 33));
		lblNuevosEstrenos.setBounds(144, 10, 455, 54);
		contentPane.add(lblNuevosEstrenos);

		final DefaultListModel<Release> releases = new DefaultListModel<>();

		GenericType<List<Release>> genericType = new GenericType<List<Release>>() {
		};
		List<Release> lastReleases = ReleaseTarget.request(MediaType.APPLICATION_JSON).get(genericType);

		listReleases = new JList<Release>(releases);
		listReleases.setBounds(20, 75, 280, 410);
		listReleases.setFont(new Font("Tahoma", Font.BOLD, 20));
		contentPane.add(listReleases);

		releases.clear();
		for (Release release : lastReleases) {
			releases.addElement(release);
		}

		listReleases.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {

				try {
					nuevosLanzamientos(listReleases, listReleases.getSelectedIndex(), textFieldReleaseTitle,
							lblFilmImage, textDescription, trailer, btnNewButton);
					
					
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}

		});

		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBounds(283, 88, 17, 37);
		contentPane.add(scrollBar);

		lblFilmImage.setBounds(320, 121, 281, 298);
		contentPane.add(lblFilmImage);

		textDescription.setBackground(new Color(64, 224, 208));
		textDescription.setBounds(310, 429, 395, 62);
		contentPane.add(textDescription);

		lblAgeRestriction.setBounds(647, 57, 60, 54);
		contentPane.add(lblAgeRestriction);

		textFieldReleaseTitle.setFont(new Font("Tahoma", Font.BOLD, 15));
		textFieldReleaseTitle.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldReleaseTitle.setEditable(false);
		textFieldReleaseTitle.setBackground(new Color(64, 224, 208));
		textFieldReleaseTitle.setBounds(331, 74, 281, 37);
		contentPane.add(textFieldReleaseTitle);
		textFieldReleaseTitle.setColumns(10);

		btnNewButton = new JButton("TRAILER");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Desktop.getDesktop().browse(new URL(trailer).toURI());
				} catch (IOException | URISyntaxException e1) {
					// TODO Auto-generated catch block
					logger.log(Level.WARNING, "IOException", e1);
					// e1.printStackTrace();
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnNewButton.setBounds(611, 353, 115, 31);
		btnNewButton.setVisible(false);
		contentPane.add(btnNewButton);

	}

	/**
	 * Metodo para modificar la imagen asociada a cada edad
	 */
	public void ageReleaseIconResize(URL url) throws IOException {
		Image image;
		image = ImageIO.read(url);
		ImageIcon myImg = new ImageIcon(url);
		image = myImg.getImage();

		int width = myImg.getIconWidth() / 7;
		int height = myImg.getIconHeight() / 7;

		Image newImg = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		ImageIcon resizeImg = new ImageIcon(newImg);
		lblAgeRestriction.setIcon(resizeImg);
	}

	/**
	 * Metodo para asignar una imagen para cada edad
	 */
	public void releaseAgeRestImage(int ageRelease) {
		if (ageAll == ageRelease) {
			try {
				URL url = new URL("https://upload.wikimedia.org/wikipedia/commons/6/68/Edad_TP.png");
				ageReleaseIconResize(url);
			} catch (IOException e7) {
			}
		} else if (age7 == ageRelease) {
			try {
				URL url = new URL("https://upload.wikimedia.org/wikipedia/commons/5/55/Edad_7.png");
				ageReleaseIconResize(url);
			} catch (IOException e7) {
			}

		} else if (age13 == ageRelease) {
			try {
				URL url = new URL("https://upload.wikimedia.org/wikipedia/commons/b/bd/Edad_13.png");
				ageReleaseIconResize(url);
			} catch (IOException e7) {
			}

		} else if (age16 == ageRelease) {
			try {
				URL url = new URL("https://upload.wikimedia.org/wikipedia/commons/4/4b/Edad_16.png");
				ageReleaseIconResize(url);
			} catch (IOException e7) {
			}

		} else {
			try {
				URL url = new URL("https://upload.wikimedia.org/wikipedia/commons/c/ca/Edad_18.png");
				ageReleaseIconResize(url);
			} catch (IOException e7) {
			}
		}
	}

	/**
	 * Metodo para los nuevos lanzamientos
	 * 
	 */

	public void nuevosLanzamientos(JList<Release> lReleases, int selected, JTextField tRelease, JLabel lblFilmImage,
			JTextPane textDescription, String trailer, JButton btnNewButton) throws IOException {

		tRelease.setText(lReleases.getModel().getElementAt(selected).getName());

		textDescription.setText(lReleases.getModel().getElementAt(selected).getDescription());

		setRelease(lReleases.getSelectedValue().getUrl(), lblFilmImage);

		releaseAgeRestImage(lReleases.getModel().getElementAt(selected).getAgeRestriction());
		
		setTrailer(lReleases.getModel().getElementAt(selected).getTrailer());
		btnNewButton.setVisible(true);

	}

	/**
	 * Metodo para poner la imagen de un tamaño estandar en el label
	 */

	public void setRelease(String urlS, JLabel jb) throws IOException {
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

	public String getTrailer() {
		return trailer;
	}

	public void setTrailer(String trailer) {
		this.trailer = trailer;
	}

}
