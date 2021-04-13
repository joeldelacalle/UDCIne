package es.deusto.spq;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class VentanaAlimentosCine extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;

	public VentanaAlimentosCine() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 501);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(64, 224, 208));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 139), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel pnlBtnAnyadir = new JPanel();
		pnlBtnAnyadir.addMouseListener(new MouseAdapter() {
		});
		pnlBtnAnyadir.setBackground(new Color(47, 79, 79));
		pnlBtnAnyadir.setBounds(455, 392, 271, 54);
		contentPane.add(pnlBtnAnyadir);
		pnlBtnAnyadir.setLayout(null);

		JLabel lblAnyadir = new JLabel("A\u00D1ADIR");
		lblAnyadir.setFont(new Font("Arial", Font.BOLD, 28));
		lblAnyadir.setForeground(new Color(245, 255, 250));
		lblAnyadir.setBounds(86, 10, 111, 34);
		pnlBtnAnyadir.add(lblAnyadir);

		final JLabel lblX = new JLabel("X");
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
		lblX.setBounds(707, 10, 19, 31);
		contentPane.add(lblX);
		
		final JLabel lblFlecha = new JLabel("<-");
		lblFlecha.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				VentanaPrincipal vp = new VentanaPrincipal();
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

		JLabel lblPopcornImage = new JLabel("New label");
		lblPopcornImage.setBounds(47, 76, 161, 179);
		contentPane.add(lblPopcornImage);
		Image image = null;
		try {
			URL url = new URL(
					"https://png.pngtree.com/png-vector/20201115/ourlarge/pngtree-striped-paper-box-for-popcorn-in-cinema-illustration-vector-png-image_2439043.jpg");
			image = ImageIO.read(url);
			ImageIcon myImg = new ImageIcon(url);
			image = myImg.getImage();

			int width = myImg.getIconWidth() / 4;
			//System.out.println(width);
			int height = myImg.getIconHeight() / 4;
			//System.out.println(height);

			Image newImg = image.getScaledInstance(width, height, Image.SCALE_REPLICATE);
			ImageIcon resizeImg = new ImageIcon(newImg);
			lblPopcornImage.setIcon(resizeImg);

		} catch (IOException e) {
		}
		JLabel lblPopcornCola = new JLabel("");
		lblPopcornCola.setBounds(480, 85, 166, 157);
		contentPane.add(lblPopcornCola);

		try {
			URL url = new URL(
					"https://previews.123rf.com/images/imagestore/imagestore1606/imagestore160601787/58756143-palomitas-en-rect%C3%A1ngulo-con-el-color-en-la-copa-para-llevar-aislado-en-el-fondo-blanco.jpg");
			image = ImageIO.read(url);
			ImageIcon myImg = new ImageIcon(url);
			image = myImg.getImage();

			int width = myImg.getIconWidth() / 7;
			//System.out.println(width);
			int height = myImg.getIconHeight() / 7;
			//System.out.println(height);

			Image newImg = image.getScaledInstance(width, height, Image.SCALE_REPLICATE);
			ImageIcon resizeImg = new ImageIcon(newImg);
			lblPopcornCola.setIcon(resizeImg);

		} catch (IOException e) {
		}

		JLabel lblPopcornBig = new JLabel("");
		lblPopcornBig.setBounds(245, 85, 192, 157);
		contentPane.add(lblPopcornBig);
		try {
			URL url = new URL("https://cdns3-2.primor.eu/90833-thickbox/cubo-palomitas-grande.jpg");
			image = ImageIO.read(url);
			ImageIcon myImg = new ImageIcon(url);
			image = myImg.getImage();

			int width = myImg.getIconWidth() / 3;
			//System.out.println(width);
			int height = myImg.getIconHeight() / 3;
			//System.out.println(height);

			Image newImg = image.getScaledInstance(width, height, Image.SCALE_REPLICATE);
			ImageIcon resizeImg = new ImageIcon(newImg);
			lblPopcornBig.setIcon(resizeImg);

			JLabel lblCompraDeAlimentos = new JLabel("COMPRA DE ALIMENTOS");
			lblCompraDeAlimentos.setFont(new Font("Cooper Black", Font.BOLD, 33));
			lblCompraDeAlimentos.setBounds(144, 10, 455, 54);
			contentPane.add(lblCompraDeAlimentos);

		} catch (IOException e) {
		}
		final DefaultListModel<String> listmodelAlimentos = new DefaultListModel<String>();
		JList<String> list = new JList<String>(listmodelAlimentos);
		list.setBounds(47, 405, 243, 41);
		contentPane.add(list);

		String Popcorn = "Palomitas Medianas";
		String PopcornBig = "Palomitas Grandes";
		String PopcornCola = "Palomitas Grandes + Coca Cola";

		final JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(194, 314, 155, 41);
		contentPane.add(comboBox);
		comboBox.addItem(Popcorn);
		comboBox.addItem(PopcornBig);
		comboBox.addItem(PopcornCola);

		textField = new JTextField();
		textField.setBounds(194, 427, 96, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		textField.setVisible(false);

		JButton btnCompraAlimentos = new JButton("SELECCIONAR");
		btnCompraAlimentos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String a;
				textField.setText((String) comboBox.getSelectedItem());
				a = textField.getText();
				listmodelAlimentos.addElement(a);

			}
		});
		btnCompraAlimentos.setBounds(383, 314, 101, 41);
		contentPane.add(btnCompraAlimentos);

		JLabel lblNewLabel = new JLabel("PALOMITAS MEDIANAS");
		lblNewLabel.setFont(new Font("Cooper Black", Font.PLAIN, 12));
		lblNewLabel.setBounds(47, 250, 173, 41);
		contentPane.add(lblNewLabel);

		JLabel lblPalomitasGrandes = new JLabel("PALOMITAS GRANDES");
		lblPalomitasGrandes.setFont(new Font("Cooper Black", Font.PLAIN, 12));
		lblPalomitasGrandes.setBounds(264, 252, 173, 41);
		contentPane.add(lblPalomitasGrandes);

		JLabel lblPalomitasGrandes_1 = new JLabel("PALOMITAS COCA COLA");
		lblPalomitasGrandes_1.setToolTipText("");
		lblPalomitasGrandes_1.setFont(new Font("Cooper Black", Font.PLAIN, 12));
		lblPalomitasGrandes_1.setBounds(490, 243, 156, 54);
		contentPane.add(lblPalomitasGrandes_1);

		JLabel lblListaDeCompra = new JLabel("LISTA DE COMPRA");
		lblListaDeCompra.setFont(new Font("Cooper Black", Font.PLAIN, 15));
		lblListaDeCompra.setBounds(47, 386, 173, 19);
		contentPane.add(lblListaDeCompra);

		
	}
}
