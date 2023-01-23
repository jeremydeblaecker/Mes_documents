package gestion_hotel;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import java.awt.Font;
import java.awt.Color;

public class Main {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.DARK_GRAY);
		frame.getContentPane().setForeground(Color.DARK_GRAY);
		frame.setBounds(100, 100, 570, 411);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btnGererChambre = new JButton("Gerer Chambre");
		btnGererChambre.setBounds(56, 51, 154, 45);
		btnGererChambre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GererChambres nw = new GererChambres();
				nw.setVisible(true);
			}
		});
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(btnGererChambre);
		
		JButton btnGererReservation = new JButton("Gerer Reservation");
		btnGererReservation.setBounds(340, 51, 154, 45);
		btnGererReservation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Gestion_reservation nw = new Gestion_reservation();
				nw.setVisible(true);
			}
		});
		frame.getContentPane().add(btnGererReservation);
		
		JButton btnGererClient = new JButton("Gerer Clients");
		btnGererClient.setBounds(56, 267, 154, 45);
		btnGererClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Gestion_clients nw = new Gestion_clients();
				nw.setVisible(true);
			}
		});
		frame.getContentPane().add(btnGererClient);
		
		JButton btnGererPrix = new JButton("Gerer les Prix");
		btnGererPrix.setBounds(340, 264, 154, 50);
		btnGererPrix.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gestion_prix nw = new gestion_prix();
				nw.setVisible(true);
			}
		});
		frame.getContentPane().add(btnGererPrix);
		
		JTextPane txtpnGestionDeRservations = new JTextPane();
		txtpnGestionDeRservations.setForeground(Color.WHITE);
		txtpnGestionDeRservations.setCaretColor(Color.WHITE);
		txtpnGestionDeRservations.setBackground(Color.DARK_GRAY);
		txtpnGestionDeRservations.setFont(new Font("Tahoma", Font.BOLD, 20));
		txtpnGestionDeRservations.setText("Gestion de R\u00E9servation H\u00F4teli\u00E8re");
		txtpnGestionDeRservations.setBounds(121, 173, 341, 31);
		frame.getContentPane().add(txtpnGestionDeRservations);
	}
}
