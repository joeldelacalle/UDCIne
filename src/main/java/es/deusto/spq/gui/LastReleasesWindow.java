package es.deusto.spq.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class LastReleasesWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;

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

		JLabel lblPeli1 = new JLabel("New label");
		lblPeli1.setBounds(24, 75, 203, 240);
		contentPane.add(lblPeli1);
		Image image = null;
		try {
			URL url = new URL("https://pics.filmaffinity.com/no_time_to_die-525355918-large.jpg");
			image = ImageIO.read(url);
			ImageIcon myImg = new ImageIcon(url);
			image = myImg.getImage();

			int width = myImg.getIconWidth() / 4;
			// System.out.println(width);
			int height = myImg.getIconHeight() / 5;
			// System.out.println(height);

			Image newImg = image.getScaledInstance(width, height, Image.SCALE_REPLICATE);
			ImageIcon resizeImg = new ImageIcon(newImg);
			lblPeli1.setIcon(resizeImg);

			JLabel lblNewLabel = new JLabel("Sin tiempo para morir");
			lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 13));
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setBounds(50, 326, 148, 14);
			contentPane.add(lblNewLabel);

		} catch (IOException e) {
		}

		JLabel lblPeli2 = new JLabel("New label");
		lblPeli2.setBounds(268, 75, 203, 240);
		contentPane.add(lblPeli2);

		try {
			URL url = new URL("https://pics.filmaffinity.com/a_quiet_place_part_ii-979721999-large.jpg");
			image = ImageIO.read(url);
			ImageIcon myImg = new ImageIcon(url);
			image = myImg.getImage();

			int width = myImg.getIconWidth() / 4;
			// System.out.println(width);
			int height = myImg.getIconHeight() / 5;
			// System.out.println(height);

			Image newImg = image.getScaledInstance(width, height, Image.SCALE_REPLICATE);
			ImageIcon resizeImg = new ImageIcon(newImg);
			lblPeli2.setIcon(resizeImg);

			JLabel lblNewLabel = new JLabel("Un lugar tranquilo 2");
			lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 13));
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setBounds(295, 326, 141, 14);
			contentPane.add(lblNewLabel);

		} catch (IOException e) {
		}

		JLabel lblPeli3 = new JLabel("New label");
		lblPeli3.setBounds(511, 75, 203, 240);
		contentPane.add(lblPeli3);

		try {
			URL url = new URL("https://pics.filmaffinity.com/black_widow-689701580-large.jpg");
			image = ImageIO.read(url);
			ImageIcon myImg = new ImageIcon(url);
			image = myImg.getImage();

			int width = myImg.getIconWidth() / 4;
			// System.out.println(width);
			int height = myImg.getIconHeight() / 5;
			// System.out.println(height);

			Image newImg = image.getScaledInstance(width, height, Image.SCALE_REPLICATE);
			ImageIcon resizeImg = new ImageIcon(newImg);
			lblPeli3.setIcon(resizeImg);

			JLabel lblNewLabel = new JLabel("Black Widow");
			lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 13));
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setBounds(541, 326, 141, 14);
			contentPane.add(lblNewLabel);

		} catch (IOException e) {
		}
	}
}
