package gestion_hotel;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JButton;
import com.toedter.calendar.JDateChooser;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;

public class Gestion_reservation extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldID;
	private JTextField textFieldClientID;
	private JTextField textFieldNChambre;
	private JTable tableR;
	Connection conn;
	ResultSet rs;
	PreparedStatement pst;


	/**
	 * Launch the application.
	 */	
	RESERVATION reservation = new RESERVATION();
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gestion_reservation frame = new Gestion_reservation();
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
	public Gestion_reservation() {	
		setBounds(100, 100, 638, 326);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		textFieldID = new JTextField();
		textFieldID.setBounds(132, 52, 96, 19);
		contentPane.add(textFieldID);
		textFieldID.setColumns(10);
		
		JTextPane textpnID = new JTextPane();
		textpnID.setText("ID Reservation");
		textpnID.setBounds(10, 52, 112, 19);
		contentPane.add(textpnID);
		
		JTextPane txtpnClientId = new JTextPane();
		txtpnClientId.setText("Client ID");
		txtpnClientId.setBounds(10, 81, 112, 19);
		contentPane.add(txtpnClientId);
		
		JTextPane txtpnNchambre = new JTextPane();
		txtpnNchambre.setText("N\u00B0Chambre");
		txtpnNchambre.setBounds(10, 110, 112, 19);
		contentPane.add(txtpnNchambre);
		
		JTextPane txtpnDebutReservation = new JTextPane();
		txtpnDebutReservation.setText("Debut reservation");
		txtpnDebutReservation.setBounds(10, 139, 112, 19);
		contentPane.add(txtpnDebutReservation);
		
		JTextPane txtpnFinReservation = new JTextPane();
		txtpnFinReservation.setText("Fin reservation");
		txtpnFinReservation.setBounds(10, 168, 112, 19);
		contentPane.add(txtpnFinReservation);
		
		textFieldClientID = new JTextField();
		textFieldClientID.setColumns(10);
		textFieldClientID.setBounds(132, 81, 96, 19);
		contentPane.add(textFieldClientID);
		
		textFieldNChambre = new JTextField();
		textFieldNChambre.setColumns(10);
		textFieldNChambre.setBounds(132, 110, 96, 19);
		contentPane.add(textFieldNChambre);
		
		tableR = new JTable();

		tableR.setModel(new DefaultTableModel(
			new Object[][] {
				{"ID", "ID Client", "Num\u00E9ro Chambre", "Date Debut", "Date Fin"},
			},
			new String[] {
				"ID", "ID Client", "Num\u00E9ro Chambre", "Date Debut", "Date Fin"
			}
		));
		tableR.getColumnModel().getColumn(2).setPreferredWidth(104);
		tableR.setBounds(238, 67, 376, 181);
		contentPane.add(tableR);
		reservation.fillReservationJTable(tableR);
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//clear le tableau
				tableR.setModel(new DefaultTableModel(null, new Object[] {"ID", "ID Client", "Num\u00E9ro Chambre", "Date Debut", "Date Fin"}));
				//puis on le rerempli
				reservation.fillReservationJTable(tableR);
			}
		});
		btnRefresh.setBounds(238, 258, 376, 21);
		contentPane.add(btnRefresh);
		
		
		
		
		JDateChooser dateChooserDebutReservation = new JDateChooser();
		dateChooserDebutReservation.setBounds(132, 139, 96, 19);
		contentPane.add(dateChooserDebutReservation);
		
		JDateChooser dateChooserFinReservation = new JDateChooser();
		dateChooserFinReservation.setBounds(132, 168, 96, 19);
		contentPane.add(dateChooserFinReservation);
		
		
		
		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int client_id = Integer.valueOf(textFieldClientID.getText());
					int NumeroChambres = Integer.valueOf(textFieldNChambre.getText());
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					String dateEntree = dateFormat.format(dateChooserDebutReservation.getDate());
					String dateSorti = dateFormat.format(dateChooserFinReservation.getDate());

					
					if(reservation.addReservation(NumeroChambres, client_id, dateEntree, dateSorti)) {
						JOptionPane.showMessageDialog(rootPane, "Nouvel réservation ajouté avec succes", "Ajouter reservation", JOptionPane.INFORMATION_MESSAGE);
					}else {
						JOptionPane.showMessageDialog(rootPane, "Echec de l'ajout de la reservation", "Echec Ajout reservation", JOptionPane.ERROR_MESSAGE);
					}
				}catch(NumberFormatException ex){
					JOptionPane.showMessageDialog(rootPane, ex.getMessage()+"-Renseigner le numéro de la reservation", "Erreur reservation", JOptionPane.ERROR_MESSAGE);	
				}
			}
		});
		
		tableR.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
					
					DefaultTableModel model = (DefaultTableModel)tableR.getModel();
					
					int rIndex = tableR.getSelectedRow();
					
					textFieldID.setText(model.getValueAt(rIndex, 0).toString());
					textFieldClientID.setText(model.getValueAt(rIndex, 1).toString());
					textFieldNChambre.setText(model.getValueAt(rIndex, 2).toString());
					
					try {
						Date dateEntree = new SimpleDateFormat("yyyy-MM-dd").parse(model.getValueAt(rIndex, 3).toString());
						dateChooserDebutReservation.setDate(dateEntree);
						
						Date dateSorti = new SimpleDateFormat("yyyy-MM-dd").parse(model.getValueAt(rIndex, 4).toString());
						dateChooserFinReservation.setDate(dateSorti);

					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}					
			}
		});
		
		JButton btnClear = new JButton("Vider champs");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				textFieldID.setText("");
				textFieldClientID.setText("");
				textFieldNChambre.setText("");
				dateChooserDebutReservation.setDate(null);
				dateChooserFinReservation.setDate(null);
			
			}
		});
		btnClear.setBounds(132, 258, 96, 21);
		contentPane.add(btnClear);
		btnAjouter.setBounds(10, 227, 112, 21);
		contentPane.add(btnAjouter);
		
		JButton btnModifier = new JButton("Modifier");
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int reservationId;
				int roomNumber;
				int clientId;
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				String dateEntre = dateFormat.format(dateChooserDebutReservation.getDate());
				String dateSorti = dateFormat.format(dateChooserFinReservation.getDate());

				try {
				reservationId =Integer.valueOf(textFieldID.getText());
				roomNumber = Integer.valueOf(textFieldNChambre.getText());
				clientId = Integer.valueOf(textFieldClientID.getText());
				
				if(reservation.editReservation(reservationId, clientId, roomNumber, dateEntre, dateSorti))
				{
					JOptionPane.showMessageDialog(rootPane, "Reservation modifié avec succès", "Modifier reservation", JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					JOptionPane.showMessageDialog(rootPane, "Echec de la mofication de la reservation", "Erreur modification reservation", JOptionPane.ERROR_MESSAGE);
					 }
				}catch(NumberFormatException ex) {
					
				}
		}}
		);
		btnModifier.setBounds(10, 258, 112, 21);
		contentPane.add(btnModifier);
		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//on supprime la
				int reservationId = Integer.valueOf(textFieldID.getText());
				
				if(reservation.removeReservation(reservationId))
				{
					JOptionPane.showMessageDialog(rootPane, "Reservation supprimé avec succès", "Supprimer reservation", JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					JOptionPane.showMessageDialog(rootPane, "Echec de la suppression de la reservation", "Erreur suppression reservation", JOptionPane.ERROR_MESSAGE);
				}
			}
				
				
				
			
		});
		btnSupprimer.setBounds(132, 227, 96, 21);
		contentPane.add(btnSupprimer);		
		
		JTextPane textPane = new JTextPane();
		textPane.setText("G\u00E9rer les chambres");
		textPane.setForeground(Color.WHITE);
		textPane.setFont(new Font("Tahoma", Font.BOLD, 35));
		textPane.setBackground(Color.DARK_GRAY);
		textPane.setBounds(238, 10, 376, 46);
		contentPane.add(textPane);
		
		JTextPane txtpnChampsRemplir = new JTextPane();
		txtpnChampsRemplir.setText("Champs \u00E0 remplir");
		txtpnChampsRemplir.setForeground(Color.WHITE);
		txtpnChampsRemplir.setFont(new Font("Tahoma", Font.BOLD, 20));
		txtpnChampsRemplir.setBackground(Color.DARK_GRAY);
		txtpnChampsRemplir.setBounds(10, 10, 218, 32);
		contentPane.add(txtpnChampsRemplir);
		
		JTextPane txtpnBoutons = new JTextPane();
		txtpnBoutons.setText("Boutons");
		txtpnBoutons.setForeground(Color.WHITE);
		txtpnBoutons.setFont(new Font("Tahoma", Font.BOLD, 18));
		txtpnBoutons.setBackground(Color.DARK_GRAY);
		txtpnBoutons.setBounds(10, 193, 218, 32);
		contentPane.add(txtpnBoutons);
		
	}
}
