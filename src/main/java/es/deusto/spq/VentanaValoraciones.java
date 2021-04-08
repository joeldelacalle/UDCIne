package es.deusto.spq;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JList;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;

public class VentanaValoraciones extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaValoraciones frame = new VentanaValoraciones();
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
	public VentanaValoraciones() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 754, 562);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(72, 209, 204));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("AREA PERSONAL Y VALORACIONES");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Cooper Black", Font.BOLD, 27));
		lblNewLabel.setBounds(88, 28, 539, 27);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Usuario conectado:");
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_1.setBounds(210, 82, 191, 27);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("...");
		lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_2.setBounds(419, 82, 97, 27);
		contentPane.add(lblNewLabel_2);
		
		String pelicula1 = "2 dias en Paris";
		String pelicula2 = "76 days";
		String pelicula3 = "A balance";
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(29, 206, 97, 22);
		contentPane.add(comboBox);
		comboBox.addItem(pelicula1);
		comboBox.addItem(pelicula2);
		comboBox.addItem(pelicula3);
		
		String cine1 = "Max ocio";
		String cine2 = "Zubiarte";
		String cine3 = "Urbil";
		
		JComboBox<String> comboBox_1 = new JComboBox<String>();
		comboBox_1.setBounds(395, 206, 103, 22);
		contentPane.add(comboBox_1);
		comboBox_1.addItem(cine1);
		comboBox_1.addItem(cine2);
		comboBox_1.addItem(cine3);
		
		final JLabel lblNewLabel_3 = new JLabel("Peliculas");
		lblNewLabel_3.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_3.setBounds(29, 181, 70, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Cines");
		lblNewLabel_4.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel_4.setBounds(395, 181, 46, 14);
		contentPane.add(lblNewLabel_4);
		
		final DefaultListModel<String> listmodelpelis = new DefaultListModel<String>();
		JList<String> list = new JList<String>(listmodelpelis);
		list.setBounds(29, 270, 305, 227);
		contentPane.add(list);
		
		final DefaultListModel<String> listmodelcines = new DefaultListModel<String>();
		JList<String> list_1 = new JList<String>(listmodelcines);
		list_1.setBounds(395, 270, 317, 227);
		contentPane.add(list_1);
		
		textField = new JTextField();
		textField.setBounds(29, 239, 211, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(395, 239, 218, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		
		JButton btnNewButton_1 = new JButton("A\u00F1adir");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String valoracionpeli = textField.getText();
				listmodelpelis.addElement(valoracionpeli);
			}
		});
		btnNewButton_1.setFont(new Font("Arial", Font.BOLD, 16));
		btnNewButton_1.setBounds(250, 236, 84, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("A\u00F1adir");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String valoracioncine = textField_1.getText();
				listmodelcines.addElement(valoracioncine);
			}
		});
		btnNewButton_2.setFont(new Font("Arial", Font.BOLD, 16));
		btnNewButton_2.setBounds(623, 236, 89, 23);
		contentPane.add(btnNewButton_2);
		
		Border border = BorderFactory.createLineBorder(Color.black, 2);
		JLabel lblNewLabel_1_1 = new JLabel("Valorar pelicula");
		lblNewLabel_1_1.setBorder(border);
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("Cooper Black", Font.BOLD, 20));
		lblNewLabel_1_1.setBounds(88, 128, 191, 42);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Valorar cine");
		lblNewLabel_1_2.setBorder(border);
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_2.setFont(new Font("Cooper Black", Font.BOLD, 20));
		lblNewLabel_1_2.setBounds(451, 128, 191, 42);
		contentPane.add(lblNewLabel_1_2);
	}
}
