package es.deusto.spq.gui;


import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import es.deusto.spq.Film;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;

import javax.swing.JComboBox;
import javax.swing.JTextField;

public class FilmWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Client client = ClientBuilder.newClient();

	final WebTarget appTarget = client.target("http://localhost:8080/myapp");
	final WebTarget FilmsTarget = appTarget.path("films");
	
	private GenericType<List<Film>> genericType = new GenericType<List<Film>>() {};
	private List<Film> films = FilmsTarget.request(MediaType.APPLICATION_JSON).get(genericType);
	
	private JPanel contentPane;
	private JTextField textFieldFilmName;
	private JLabel lblRecommendedAge = new JLabel();
	private JLabel lblFilmImage = new JLabel();
	private int ageFilm = -1;
	private String filmName = "-1";
	private String urlFilm = "-1";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FilmWindow frame = new FilmWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FilmWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 648, 588);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(64, 224, 208));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        
        lblFilmImage.setHorizontalAlignment(SwingConstants.CENTER);
       /* Image image = null;
        try {
            URL url = new URL("https://i.blogs.es/7ccbec/iron-man/1366_2000.jpg");
            image = ImageIO.read(url);
            ImageIcon myImg = new ImageIcon(url);
            image = myImg.getImage();
            
            int width = myImg.getIconWidth() / 3;
            //System.out.println(width);
            int height = myImg.getIconHeight() / 3;
            //System.out.println(height);
            
            Image newImg = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            ImageIcon resizeImg = new ImageIcon(newImg);
            lblFilmImage.setIcon(resizeImg);
        } 
        catch (IOException e) {
        }
        */
      
        
        JButton btnExit = new JButton("Exit");
        btnExit.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		VentanaPrincipal vp = new VentanaPrincipal();
        		vp.setVisible(true);
        		dispose();
        	}
        });
        
        JButton btnShowCinemas = new JButton("Show Cinemas");
        
        JLabel lblDescription = new JLabel("Description:");
        lblDescription.setHorizontalAlignment(SwingConstants.CENTER);
        
      /*  try {
            URL url = new URL("https://www.kindpng.com/picc/m/27-275692_pg-13-png-rated-pg-transparent-png.png");
            image = ImageIO.read(url);
            ImageIcon myImg = new ImageIcon(url);
            image = myImg.getImage();
            
            int width = myImg.getIconWidth() / 6;
            //System.out.println(width);
            int height = myImg.getIconHeight() / 6;
            //System.out.println(height);
            
            Image newImg = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            ImageIcon resizeImg = new ImageIcon(newImg);
            lblRecommendedAge.setIcon(resizeImg);
        } 
        catch (IOException e) {
        }
        */
        final JComboBox<String> comboBoxFilm = new JComboBox<String>();

		
		
		for (Film film : films) {
			filmName = film.getName();
			comboBoxFilm.addItem(filmName);
			
		}
        comboBoxFilm.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		textFieldFilmName.setText(comboBoxFilm.getSelectedItem().toString());
        		String selectedFilm = textFieldFilmName.getText();
        		Image image = null;
        		for (Film film : films) {
        			if(film.getName().equals(selectedFilm)) {
        				ageFilm = film.getAgeRestriction();
        				filmName =film.getName();
        				urlFilm = film.getUrl();
        			}
        		}
        		System.out.println(filmName);
        			if(filmName.equals(selectedFilm)) {
        				try {
        		            URL url = new URL(urlFilm);
        		            image = ImageIO.read(url);
        		            ImageIcon myImg = new ImageIcon(url);
        		            image = myImg.getImage();
        		            
        		            int width = myImg.getIconWidth() / 3;
        		            //System.out.println(width);
        		            int height = myImg.getIconHeight() / 3;
        		            //System.out.println(height);
        		            
        		            Image newImg = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        		            ImageIcon resizeImg = new ImageIcon(newImg);
        		            lblFilmImage.setIcon(resizeImg);
        		        } 
        		        catch (IOException e7) {
        		        }
        			}
        			System.out.println(ageFilm);
        			
        			int ageAll = 0; //apta para todos los publicos
        			int age7 = 7;
        			int age13 = 13;
        			int age16 = 16;
        			
        			if(ageAll == ageFilm) {
        				try {
        		            URL url = new URL("https://upload.wikimedia.org/wikipedia/commons/6/68/Edad_TP.png");
        		            image = ImageIO.read(url);
        		            ImageIcon myImg = new ImageIcon(url);
        		            image = myImg.getImage();
        		            
        		            int width = myImg.getIconWidth() / 5;
        		            //System.out.println(width);
        		            int height = myImg.getIconHeight() / 5;
        		            //System.out.println(height);
        		            
        		            Image newImg = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        		            ImageIcon resizeImg = new ImageIcon(newImg);
        		            lblRecommendedAge.setIcon(resizeImg);
        		        } 
        		        catch (IOException e7) {
        		        }
        			}else if(age7 == ageFilm) {
        				try {
        		            URL url = new URL("https://upload.wikimedia.org/wikipedia/commons/5/55/Edad_7.png");
        		            image = ImageIO.read(url);
        		            ImageIcon myImg = new ImageIcon(url);
        		            image = myImg.getImage();
        		            
        		            int width = myImg.getIconWidth() / 5;
        		            //System.out.println(width);
        		            int height = myImg.getIconHeight() / 5;
        		            //System.out.println(height);
        		            
        		            Image newImg = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        		            ImageIcon resizeImg = new ImageIcon(newImg);
        		            lblRecommendedAge.setIcon(resizeImg);
        		        } 
        		        catch (IOException e7) {
        		        }
        	        
        			}else if(age13 == ageFilm) {
        				try {
        		            URL url = new URL("https://upload.wikimedia.org/wikipedia/commons/b/bd/Edad_13.png");
        		            image = ImageIO.read(url);
        		            ImageIcon myImg = new ImageIcon(url);
        		            image = myImg.getImage();
        		            
        		            int width = myImg.getIconWidth() / 5;
        		            //System.out.println(width);
        		            int height = myImg.getIconHeight() / 5;
        		            //System.out.println(height);
        		            
        		            Image newImg = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        		            ImageIcon resizeImg = new ImageIcon(newImg);
        		            lblRecommendedAge.setIcon(resizeImg);
        		        } 
        		        catch (IOException e7) {
        		        }
        				
        			}else if(age16 == ageFilm) {
        				try {
        		            URL url = new URL("https://upload.wikimedia.org/wikipedia/commons/4/4b/Edad_16.png");
        		            image = ImageIO.read(url);
        		            ImageIcon myImg = new ImageIcon(url);
        		            image = myImg.getImage();
        		            
        		            int width = myImg.getIconWidth() / 5;
        		            //System.out.println(width);
        		            int height = myImg.getIconHeight() / 5;
        		            //System.out.println(height);
        		            
        		            Image newImg = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        		            ImageIcon resizeImg = new ImageIcon(newImg);
        		            lblRecommendedAge.setIcon(resizeImg);
        		        } 
        		        catch (IOException e7) {
        		        }
        	        
        			}else{
        				try {
        		            URL url = new URL("https://upload.wikimedia.org/wikipedia/commons/c/ca/Edad_18.png");
        		            image = ImageIO.read(url);
        		            ImageIcon myImg = new ImageIcon(url);
        		            image = myImg.getImage();
        		            
        		            int width = myImg.getIconWidth() / 5;
        		            //System.out.println(width);
        		            int height = myImg.getIconHeight() / 5;
        		            //System.out.println(height);
        		            
        		            Image newImg = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        		            ImageIcon resizeImg = new ImageIcon(newImg);
        		            lblRecommendedAge.setIcon(resizeImg);
        		        } 
        		        catch (IOException e7) {
        		        }
        			}    				
        		}
        });
        
        textFieldFilmName = new JTextField();
        textFieldFilmName.setEditable(false);
        textFieldFilmName.setBackground(new Color(64, 224, 208));
        textFieldFilmName.setHorizontalAlignment(SwingConstants.CENTER);
        textFieldFilmName.setFont(new Font("Imprint MT Shadow", Font.PLAIN, 52));
        textFieldFilmName.setColumns(10);
        
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
        	gl_contentPane.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_contentPane.createSequentialGroup()
        			.addGap(175)
        			.addComponent(btnExit, GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
        			.addGap(53)
        			.addComponent(btnShowCinemas)
        			.addGap(196))
        		.addGroup(gl_contentPane.createSequentialGroup()
        			.addGap(55)
        			.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
        				.addComponent(comboBoxFilm, GroupLayout.PREFERRED_SIZE, 269, GroupLayout.PREFERRED_SIZE)
        				.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
        					.addComponent(lblRecommendedAge, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE)
        					.addComponent(lblDescription, GroupLayout.PREFERRED_SIZE, 508, GroupLayout.PREFERRED_SIZE)))
        			.addContainerGap(61, Short.MAX_VALUE))
        		.addGroup(gl_contentPane.createSequentialGroup()
        			.addGap(65)
        			.addComponent(textFieldFilmName, GroupLayout.PREFERRED_SIZE, 302, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(257, Short.MAX_VALUE))
        		.addGroup(gl_contentPane.createSequentialGroup()
        			.addGap(168)
        			.addComponent(lblFilmImage)
        			.addContainerGap(456, Short.MAX_VALUE))
        );
        gl_contentPane.setVerticalGroup(
        	gl_contentPane.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_contentPane.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
        				.addGroup(gl_contentPane.createSequentialGroup()
        					.addComponent(comboBoxFilm, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        					.addComponent(textFieldFilmName, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE))
        				.addComponent(lblRecommendedAge, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(lblFilmImage, GroupLayout.PREFERRED_SIZE, 325, GroupLayout.PREFERRED_SIZE)
        			.addGap(26)
        			.addComponent(lblDescription, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        			.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
        				.addComponent(btnShowCinemas)
        				.addComponent(btnExit))
        			.addGap(21))
        );
        contentPane.setLayout(gl_contentPane);
	}
}