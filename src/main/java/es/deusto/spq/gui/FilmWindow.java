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
import javax.swing.JTextPane;

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
	private String descFilm = "-1";
	private JTextPane textPaneDescription = new JTextPane();

	
	private void ageFilmIconResize(URL url) throws IOException {
		Image image;
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
      
        
        JButton btnExit = new JButton("SALIR");
        btnExit.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		VentanaPrincipal vp = new VentanaPrincipal();
        		vp.setVisible(true);
        		dispose();
        	}
        });
        
        JButton btnShowCinemas = new JButton("MOSTRAR CINES");
        
        JLabel lblDsc = new JLabel("Descripción:");
        lblDsc.setHorizontalAlignment(SwingConstants.CENTER);
        
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
        				descFilm = film.getDescription();
        			}
        		}
        		//System.out.println(filmName);
        			if(filmName.equals(selectedFilm)) {
        				try {
        		            URL url = new URL(urlFilm);
        		            image = ImageIO.read(url);
        		            ImageIcon myImg = new ImageIcon(url);
        		            image = myImg.getImage();
        		            
        		            int width = myImg.getIconWidth() / 7 * 2;
        		            //System.out.println(width);
        		            int height = myImg.getIconHeight() / 7 * 2;
        		            //System.out.println(height);
        		            
        		            Image newImg = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        		            ImageIcon resizeImg = new ImageIcon(newImg);
        		            lblFilmImage.setIcon(resizeImg);
        		            textPaneDescription.setText(descFilm);
        		        } 
        		        catch (IOException e7) {
        		        }
        			}
        			//System.out.println(ageFilm);
        			
        			int ageAll = 0; //apta para todos los publicos
        			int age7 = 7;
        			int age13 = 13;
        			int age16 = 16;
        			
        			if(ageAll == ageFilm) {
        				try {
        		            URL url = new URL("https://upload.wikimedia.org/wikipedia/commons/6/68/Edad_TP.png");
        		            ageFilmIconResize(url);
        		        } 
        		        catch (IOException e7) {
        		        }
        			}else if(age7 == ageFilm) {
        				try {
        		            URL url = new URL("https://upload.wikimedia.org/wikipedia/commons/5/55/Edad_7.png");
        		            ageFilmIconResize(url);
        		        } 
        		        catch (IOException e7) {
        		        }
        	        
        			}else if(age13 == ageFilm) {
        				try {
        		            URL url = new URL("https://upload.wikimedia.org/wikipedia/commons/b/bd/Edad_13.png");
        		            ageFilmIconResize(url);
        		        } 
        		        catch (IOException e7) {
        		        }
        				
        			}else if(age16 == ageFilm) {
        				try {
        		            URL url = new URL("https://upload.wikimedia.org/wikipedia/commons/4/4b/Edad_16.png");
        		            ageFilmIconResize(url);
        		        } 
        		        catch (IOException e7) {
        		        }
        	        
        			}else{
        				try {
        		            URL url = new URL("https://upload.wikimedia.org/wikipedia/commons/c/ca/Edad_18.png");
        		            ageFilmIconResize(url);
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
        
       
        textPaneDescription.setBackground(new Color(64, 224, 208));
        
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
        	gl_contentPane.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_contentPane.createSequentialGroup()
        			.addGap(65)
        			.addComponent(textFieldFilmName, GroupLayout.PREFERRED_SIZE, 302, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(257, Short.MAX_VALUE))
        		.addGroup(gl_contentPane.createSequentialGroup()
        			.addGap(168)
        			.addComponent(lblFilmImage)
        			.addContainerGap(456, Short.MAX_VALUE))
        		.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
        			.addGap(187)
        			.addComponent(btnExit, GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
        			.addGap(42)
        			.addComponent(btnShowCinemas)
        			.addGap(195))
        		.addGroup(gl_contentPane.createSequentialGroup()
        			.addGap(55)
        			.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
        				.addComponent(comboBoxFilm, GroupLayout.PREFERRED_SIZE, 269, GroupLayout.PREFERRED_SIZE)
        				.addGroup(gl_contentPane.createSequentialGroup()
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(lblDsc, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(textPaneDescription, GroupLayout.PREFERRED_SIZE, 392, GroupLayout.PREFERRED_SIZE))
        				.addGroup(gl_contentPane.createSequentialGroup()
        					.addGap(360)
        					.addComponent(lblRecommendedAge, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE)))
        			.addContainerGap(61, Short.MAX_VALUE))
        );
        gl_contentPane.setVerticalGroup(
        	gl_contentPane.createParallelGroup(Alignment.TRAILING)
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
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
        				.addComponent(lblDsc, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
        				.addComponent(textPaneDescription, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE))
        			.addContainerGap(35, Short.MAX_VALUE))
        		.addGroup(gl_contentPane.createSequentialGroup()
        			.addGap(510)
        			.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
        				.addComponent(btnShowCinemas, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        				.addComponent(btnExit, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        			.addContainerGap())
        );
        contentPane.setLayout(gl_contentPane);
	}
}