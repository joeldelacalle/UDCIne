package es.deusto.spq.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import es.deusto.spq.Cinema;
import es.deusto.spq.Film;
import es.deusto.spq.Room;
import es.deusto.spq.jdo.CinemaResource;
import es.deusto.spq.jdo.FilmResources;
import es.deusto.spq.jdo.RoomResource;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;

import javax.swing.JComboBox;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import com.toedter.calendar.JDateChooser;
import java.awt.event.MouseMotionAdapter;

public class AdminRoomsWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Client client = ClientBuilder.newClient();

	private CinemaResource cr;
	private List<Cinema> cinemas = cr.getReleases();

	private RoomResource rr;
	private List<Room> rooms = rr.getReleases();

	private FilmResources fr;
	private List<Film> films = fr.getFilms();
	

	private JPanel contentPane;
	private Date date = null;
	private final JComboBox<Film> comboFilm = new JComboBox<Film>();
	private final JComboBox<Cinema> comboCinema = new JComboBox<Cinema>();
	private final JComboBox<String> comboRoom = new JComboBox<String>();
	
	private JDateChooser calendar = new JDateChooser("yyyy/MM/dd","####/##/##",'_');
	private long cinemaId;
	private long roomCinemaId;
	private Room r;
	
	private void addFilmtoRoom() {
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
		
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		date = calendar.getDate();
		System.out.println("Añadiendo pelicula a la sala");
		try {
			tx.begin();
			Room room = new Room((Cinema)comboCinema.getSelectedItem(),(Film)comboFilm.getSelectedItem(),comboRoom.getSelectedItem().toString(),date,100);
			pm.makePersistent(room);
			
			tx.commit();
			System.out.println("Añadida pelicula a la sala");
			
		}finally {
			if (tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
	}
	
	public AdminRoomsWindow() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 501);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(64, 224, 208));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 139), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
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
		
		final JLabel lblFlecha = new JLabel("<-");
		lblFlecha.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AdminWindow aw = new AdminWindow();
				aw.setVisible(true);
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
		
		for (Cinema cinema : cinemas) {
			comboCinema.addItem(cinema);
		}
		for (Film film : films) {
			comboFilm.addItem(film);
		}
		for (Room room : rooms) {
			r = room;
			//roomCinema = room.getCinema();
			comboRoom.addItem(room.getName());	
		}
		
		
		
		comboFilm.setBounds(51, 68, 265, 57);
		contentPane.add(comboFilm);
		
		
		comboCinema.setBounds(50, 161, 265, 57);
		contentPane.add(comboCinema);
		/*comboRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				for (int i=0;i<cinemas.size();i++) {
					 cinemaId = cinemas.get(i).getId();			
				 }
				 for (int i=0;i<rooms.size();i++) {
					 roomCinemaId = rooms.get(i).getId();			
				 }
				 if(cinemaId == roomCinemaId) {
						System.out.println(cinemaId);
						System.out.println(roomCinemaId);
						JOptionPane.showMessageDialog(contentPane, "Sala y cine coinciden.");
					}else {
						System.out.println("No coincide");
					}
					 /*else if(!comboCinema.getSelectedItem().equals(roomCinema)) {
						System.out.println(roomCinema);
						comboRoom.removeItem(r);
						JOptionPane.showMessageDialog(contentPane, "Sala y cine no coinciden. Borrar sala");
				}
			}
		});
		*/
		comboRoom.setBounds(50, 247, 265, 57);
		contentPane.add(comboRoom);
		
		JButton btnAddFilmtoRoom = new JButton("ASIGNAR");
		btnAddFilmtoRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addFilmtoRoom();
			}
		});
		btnAddFilmtoRoom.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnAddFilmtoRoom.setBounds(50, 387, 266, 41);
		contentPane.add(btnAddFilmtoRoom);
		
        calendar.setBounds(123,329,110,31);
        getContentPane().add(calendar);
		

	}
}
