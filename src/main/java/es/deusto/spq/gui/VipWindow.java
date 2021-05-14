package es.deusto.spq.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import es.deusto.spq.Film;
import es.deusto.spq.Order;
import es.deusto.spq.User;
import es.deusto.spq.jdo.OrderResource;
import es.deusto.spq.jdo.UserResource;

import javax.swing.JList;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VipWindow extends JFrame {

	private JPanel contentPane;
	private JLabel lblUserName = new JLabel("");
	private DefaultListModel<Order> listModelOrders = new DefaultListModel<Order>();
	private JList<Order> listOrders;

	public VipWindow() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 501);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(64, 224, 208));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 139), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		final JLabel lblNewLabel_1 = new JLabel("X");
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (JOptionPane.showConfirmDialog(null, "¿Seguro que quieres cerrar la aplicacion?", "Confirmacion",
						JOptionPane.YES_NO_OPTION) == 0) {
					dispose();
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				lblNewLabel_1.setForeground(Color.RED);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lblNewLabel_1.setForeground(Color.WHITE);
			}
		});
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1.setBounds(641, 24, 22, 14);
		contentPane.add(lblNewLabel_1);

		JLabel lblName = new JLabel("USER:");
		lblName.setBounds(36, 21, 85, 29);
		contentPane.add(lblName);

		JButton btnViewOrders = new JButton("Ver Mis Pedidos");
		btnViewOrders.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				List<Order> oList = SetOrderRecord(lblUserName.getText());
				for (int i = 0; i < oList.size(); i++) {
					listModelOrders.addElement(oList.get(i));
				}

			}
		});
		btnViewOrders.setBounds(36, 77, 142, 58);
		contentPane.add(btnViewOrders);

		listOrders = new JList<Order>(listModelOrders);
		listOrders.setBounds(409, 52, 276, 389);
		contentPane.add(listOrders);

	}

	public void SetUserName(User u) {
		this.lblUserName.setBounds(131, 24, 202, 26);
		this.lblUserName.setText(u.getNickname());
		this.contentPane.add(lblUserName);
	}

	public List<Order> SetOrderRecord(String userName) {

		UserResource ur = new UserResource();

		User u = ur.getUser(userName);

		OrderResource or = new OrderResource();

		List<Order> oList = or.getOrders(u.getEmail());

		return oList;

	}
}
