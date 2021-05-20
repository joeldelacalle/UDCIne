package es.deusto.spq.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class AdminWindow extends JFrame{

	/**
	 * Ventana Administrador
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	/**
	 * Crea la ventana de Administracion
	 */
	public AdminWindow() {
		
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
					AdminWindow.this.dispose();
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
				CinemaLoginWindow lw = new CinemaLoginWindow();
				lw.setVisible(true);
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
		
		JLabel lblAdmin = new JLabel("ADMINISTRACIÓN");
		lblAdmin.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdmin.setFont(new Font("Cooper Black", Font.BOLD, 33));
		lblAdmin.setBounds(199, 20, 338, 54);
		contentPane.add(lblAdmin);
		/**
    	 * Boton que contiene un metodo para acceder a la ventana administrador para peliculas
    	 */
		JButton btnPelis = new JButton("Administrar Películas");
		btnPelis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AdminFilmsWindow afw = new AdminFilmsWindow();
				afw.setVisible(true);
				dispose();
			}
		});
		btnPelis.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnPelis.setForeground(Color.WHITE);
		btnPelis.setBackground(new Color(47, 79, 79));
		btnPelis.setBounds(240, 170, 250, 50);
        contentPane.add(btnPelis);
        /**
    	 * Boton que contiene un metodo para acceder a la ventana administrador para salas
    	 */
        JButton btnSalas = new JButton("Administrar Salas");
        btnSalas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AdminRoomsWindow arw = new AdminRoomsWindow();
				arw.setVisible(true);
				dispose();
			}
		});
        btnSalas.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnSalas.setForeground(Color.WHITE);
        btnSalas.setBackground(new Color(47, 79, 79));
        btnSalas.setBounds(240, 250, 250, 50);
        contentPane.add(btnSalas);
        /**
    	 * Boton que contiene un metodo para acceder a la ventana administrador para usuarios
    	 */
        JButton btnUsuarios = new JButton("Administrar Usuarios");
        btnUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AdminUsersWindow auw = new AdminUsersWindow();
				auw.setVisible(true);
				dispose();
			}
		});
        btnUsuarios.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnUsuarios.setForeground(Color.WHITE);
        btnUsuarios.setBackground(new Color(47, 79, 79));
        btnUsuarios.setBounds(240, 330, 250, 50);
        contentPane.add(btnUsuarios);
        
	}
	
}
