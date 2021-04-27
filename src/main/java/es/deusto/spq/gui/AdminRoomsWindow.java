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

	private final WebTarget appTarget = client.target("http://localhost:8080/myapp");
	private final WebTarget CinemasTarget = appTarget.path("cinemas");

	 GenericType<List<Cinema >> genericType0 = new GenericType<List<Cinema>>() {};
	 List<Cinema > cinemas = CinemasTarget.request(MediaType.APPLICATION_JSON).get(genericType0);
	 
	private final WebTarget RoomsTarget = appTarget.path("rooms");

	private GenericType<List<Room>> genericType1 = new GenericType<List<Room>>() {};
	private List<Room> rooms = RoomsTarget.request(MediaType.APPLICATION_JSON).get(genericType1);

	

	private final WebTarget FilmsTarget = appTarget.path("films");

	private GenericType<List<Film>> genericType2 = new GenericType<List<Film>>() {};
	private List<Film> films = FilmsTarget.request(MediaType.APPLICATION_JSON).get(genericType2);

	private JPanel contentPane;
	private Date date = null;
	private final JComboBox<Film> comboFilm = new JComboBox<Film>();
	private final JComboBox<Cinema> comboCinema = new JComboBox<Cinema>();
	private final JComboBox<String> comboRoom = new JComboBox<String>();
	
	private JDateChooser calendar = new JDateChooser("yyyy/MM/dd","####/##/##",'_');
	private Cinema cinemaSelected;
	
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
			cinemaSelected = cinema;
			comboCinema.addItem(cinema);
		}
		for (Film film : films) {
			comboFilm.addItem(film);
		}
		
		
		
		for (Room room : rooms) {
			//if(room.getCinema().equals(cinemaSelected)) {
				comboRoom.addItem(room.getName());	
			/*}else {
				JOptionPane.showMessageDialog(contentPane, "Seleccione un cine para que se le muestren las salas.");
			}*/
			
		}
		
		
		
		comboFilm.setBounds(51, 68, 265, 57);
		contentPane.add(comboFilm);
		
		
		comboCinema.setBounds(50, 161, 265, 57);
		contentPane.add(comboCinema);
		
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
