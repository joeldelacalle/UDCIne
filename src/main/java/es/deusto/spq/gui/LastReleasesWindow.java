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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import es.deusto.spq.Film;
import es.deusto.spq.Release;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;

import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JScrollBar;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class LastReleasesWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Client client = ClientBuilder.newClient();

	final WebTarget appTarget = client.target("http://localhost:8080/myapp");
	final WebTarget ReleaseTarget = appTarget.path("release");

	private JPanel contentPane;
	private JLabel lblFilmImage = new JLabel("");
	private GenericType<List<Release>> genericType = new GenericType<List<Release>>() {};	
	private List<Release> releases = ReleaseTarget.request(MediaType.APPLICATION_JSON).get(genericType);
	private JTextPane textDescription = new JTextPane();
	private JLabel lblAgeRestriction = new JLabel("");
	private JTextField textFieldReleaseTitle = new JTextField();
	
	private int ageRelease = -1;
	private String urlRelease = "-1";
	private String descRelease= "-1";
	private String releaseName ="-1";
	
	private void ageReleaseIconResize(URL url) throws IOException {
		Image image;
		image = ImageIO.read(url);
		ImageIcon myImg = new ImageIcon(url);
		image = myImg.getImage();
		
		int width = myImg.getIconWidth() / 7;
		//System.out.println(width);
		int height = myImg.getIconHeight() / 7;
		//System.out.println(height);
		
		Image newImg = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		ImageIcon resizeImg = new ImageIcon(newImg);
		lblAgeRestriction.setIcon(resizeImg);
	}
	
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

		final  JComboBox<String> comboBoxRelease = new JComboBox<String>();

		for (Release release : releases) {
			//System.out.println(release.getName());
			releaseName = release.getName();
			comboBoxRelease.addItem(releaseName);
		}
		
		
		/*
		 * habra que cambiar de string a pelicula y hacer un metodo que recorra y copie
		 * los parametros de la lista de peliculas al DefaultListModel
		 */

		comboBoxRelease.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				textFieldReleaseTitle.setText(comboBoxRelease.getSelectedItem().toString());
				String selectedRelease = textFieldReleaseTitle.getText();
				//System.out.println(selectedRelease);
        		Image image = null;
        		for (Release release : releases) {
        			if(release.getName().equals(selectedRelease)) {
        				ageRelease = release.getAgeRestriction();
        				urlRelease = release.getUrl();
        				releaseName = release.getName();
        				descRelease= release.getDescription();
        			}
        		}
        		System.out.println(releaseName);
        			if(releaseName.equals(selectedRelease)) {
        				try {
        		            URL url = new URL(urlRelease);
        		            image = ImageIO.read(url);
        		            ImageIcon myImg = new ImageIcon(url);
        		            image = myImg.getImage();
        		            
        		            int width = myImg.getIconWidth() / 9 * 2;
        		            //System.out.println(width);
        		            int height = myImg.getIconHeight() / 9 * 2;
        		            //System.out.println(height);
        		            
        		            Image newImg = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        		            ImageIcon resizeImg = new ImageIcon(newImg);
        		            lblFilmImage.setIcon(resizeImg);
        		            textDescription.setText(descRelease);
        		        } 
        		        catch (IOException e7) {
        		        }
        		}
        			int ageAll = 0; //apta para todos los publicos
        			int age7 = 7;
        			int age13 = 13;
        			int age16 = 16;
        			
        			if(ageAll == ageRelease) {
        				try {
        		            URL url = new URL("https://upload.wikimedia.org/wikipedia/commons/6/68/Edad_TP.png");
        		            ageReleaseIconResize(url);
        		        } 
        		        catch (IOException e7) {
        		        }
        			}else if(age7 == ageRelease) {
        				try {
        		            URL url = new URL("https://upload.wikimedia.org/wikipedia/commons/5/55/Edad_7.png");
        		            ageReleaseIconResize(url);
        		        } 
        		        catch (IOException e7) {
        		        }
        	        
        			}else if(age13 == ageRelease) {
        				try {
        		            URL url = new URL("https://upload.wikimedia.org/wikipedia/commons/b/bd/Edad_13.png");
        		            ageReleaseIconResize(url);
        		        } 
        		        catch (IOException e7) {
        		        }
        				
        			}else if(age16 == ageRelease) {
        				try {
        		            URL url = new URL("https://upload.wikimedia.org/wikipedia/commons/4/4b/Edad_16.png");
        		            ageReleaseIconResize(url);
        		        } 
        		        catch (IOException e7) {
        		        }
        	        
        			}else{
        				try {
        		            URL url = new URL("https://upload.wikimedia.org/wikipedia/commons/c/ca/Edad_18.png");
        		            ageReleaseIconResize(url);
        		        } 
        		        catch (IOException e7) {
        		        }
        			} 
				
			}
		});
		comboBoxRelease.setBounds(23, 74, 302, 37);
		contentPane.add(comboBoxRelease);
		
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBounds(308, 74, 17, 37);
		contentPane.add(scrollBar);
		
		lblFilmImage.setBounds(345, 121, 281, 298);
		contentPane.add(lblFilmImage);
		
		textDescription.setBackground(new Color(64, 224, 208));
		textDescription.setBounds(345, 429, 395, 62);
		contentPane.add(textDescription);
		
		lblAgeRestriction.setBounds(647, 57, 60, 54);
		contentPane.add(lblAgeRestriction);
		
		textFieldReleaseTitle.setFont(new Font("Tahoma", Font.BOLD, 15));
		textFieldReleaseTitle.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldReleaseTitle.setEditable(false);
		textFieldReleaseTitle.setBackground(new Color(64, 224, 208));
		textFieldReleaseTitle.setBounds(345, 72, 281, 37);
		contentPane.add(textFieldReleaseTitle);
		textFieldReleaseTitle.setColumns(10);
	/*	Image image = null;
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

		} catch (IOException e) {
		}

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

		} catch (IOException e) {
		}

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

		} catch (IOException e) {
		}
		*/
	}
}
