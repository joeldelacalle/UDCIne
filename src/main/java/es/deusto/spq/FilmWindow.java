package es.deusto.spq;


import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;

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

public class FilmWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

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
        
        JLabel lblFilmTitle = new JLabel("Iron Man");
        lblFilmTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblFilmTitle.setFont(new Font("Imprint MT Shadow", Font.PLAIN, 52));
        
        JLabel lblFilmImage = new JLabel();
        lblFilmImage.setHorizontalAlignment(SwingConstants.CENTER);
        Image image = null;
        try {
            URL url = new URL("https://i.blogs.es/7ccbec/iron-man/1366_2000.jpg");
            image = ImageIO.read(url);
            ImageIcon myImg = new ImageIcon(url);
            image = myImg.getImage();
            
            int width = myImg.getIconWidth() / 3;
            System.out.println(width);
            int height = myImg.getIconHeight() / 3;
            System.out.println(height);
            
            Image newImg = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            ImageIcon resizeImg = new ImageIcon(newImg);
            lblFilmImage.setIcon(resizeImg);
        } 
        catch (IOException e) {
        }
        
      
        
        JButton btnExit = new JButton("Exit");
        btnExit.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		setVisible(false); //you can't see me!
        		dispose(); //Destroy the JFrame object
        	}
        });
        
        JButton btnShowCinemas = new JButton("Show Cinemas");
        
        JLabel lblDescription = new JLabel("Description:");
        lblDescription.setHorizontalAlignment(SwingConstants.CENTER);
        
        JLabel lblRecommendedAge = new JLabel("");
        try {
            URL url = new URL("https://www.kindpng.com/picc/m/27-275692_pg-13-png-rated-pg-transparent-png.png");
            image = ImageIO.read(url);
            ImageIcon myImg = new ImageIcon(url);
            image = myImg.getImage();
            
            int width = myImg.getIconWidth() / 6;
            System.out.println(width);
            int height = myImg.getIconHeight() / 6;
            System.out.println(height);
            
            Image newImg = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            ImageIcon resizeImg = new ImageIcon(newImg);
            lblRecommendedAge.setIcon(resizeImg);
        } 
        catch (IOException e) {
        }
        
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
        	gl_contentPane.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_contentPane.createSequentialGroup()
        			.addGap(175)
        			.addComponent(btnExit, GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
        			.addGap(53)
        			.addComponent(btnShowCinemas)
        			.addGap(196))
        		.addGroup(gl_contentPane.createSequentialGroup()
        			.addGap(72)
        			.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
        				.addGroup(gl_contentPane.createSequentialGroup()
        					.addComponent(lblFilmTitle, GroupLayout.PREFERRED_SIZE, 302, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addComponent(lblRecommendedAge, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.RELATED))
        				.addComponent(lblFilmImage))
        			.addContainerGap(60, Short.MAX_VALUE))
        		.addGroup(gl_contentPane.createSequentialGroup()
        			.addGap(55)
        			.addComponent(lblDescription, GroupLayout.PREFERRED_SIZE, 508, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(61, Short.MAX_VALUE))
        );
        gl_contentPane.setVerticalGroup(
        	gl_contentPane.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_contentPane.createSequentialGroup()
        			.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
        				.addGroup(gl_contentPane.createSequentialGroup()
        					.addGap(57)
        					.addComponent(lblRecommendedAge, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
        					.addGap(18))
        				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
        					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        					.addComponent(lblFilmTitle, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
        					.addGap(27)))
        			.addComponent(lblFilmImage, GroupLayout.PREFERRED_SIZE, 325, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(lblDescription, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
        			.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
        				.addComponent(btnShowCinemas)
        				.addComponent(btnExit))
        			.addGap(21))
        );
        contentPane.setLayout(gl_contentPane);
	}

}