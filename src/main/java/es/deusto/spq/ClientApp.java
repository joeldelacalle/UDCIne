package es.deusto.spq;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;

public class ClientApp extends JFrame {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	Client client = ClientBuilder.newClient();

	final WebTarget appTarget = client.target("http://localhost:8080/myapp");
	final WebTarget FilmsTarget = appTarget.path("films");

	public ClientApp() {
		setSize(620, 480);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JButton getFilmsButton = new JButton("Get Films");
		JPanel buttonsPanel = new JPanel();

		JButton deleteFilmButton = new JButton("Delete Films");

		buttonsPanel.add(getFilmsButton);
		buttonsPanel.add(deleteFilmButton);
		add(buttonsPanel, BorderLayout.SOUTH);

		final DefaultListModel<FilmWindow> filmListModel = new DefaultListModel<>();
		JList<FilmWindow> filmList = new JList<>(filmListModel);

		JScrollPane listScrollPane = new JScrollPane(filmList);
		add(listScrollPane, BorderLayout.WEST);

		getFilmsButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				GenericType<List<FilmWindow>> genericType = new GenericType<List<FilmWindow>>() {};
				List<FilmWindow> films = FilmsTarget.request(MediaType.APPLICATION_JSON).get(genericType);

				filmListModel.clear();
				for (FilmWindow film : films) {
					filmListModel.addElement(film);
				}

			}

		});

		JPanel rightPanel = new JPanel();
		add(rightPanel, BorderLayout.EAST);

		JButton addFilmButton = new JButton("Add Film");
		rightPanel.add(addFilmButton);

		final JTextField codeTextField = new JTextField("", 2);
		final JTextField nameTextField = new JTextField("", 10);
		final JTextField surnameTextField = new JTextField("", 10);

		rightPanel.add(codeTextField);
		rightPanel.add(nameTextField);
		rightPanel.add(surnameTextField);

		addFilmButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});

		deleteFilmButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

			}

		});

		setVisible(true);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new ClientApp();
			}
		});
	}
}