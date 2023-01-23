package gestion_hotel;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.sql.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;

public class Gestion_clients extends JFrame {

	private JPanel contentPane;
	Connection conn;
	ResultSet rs;
	PreparedStatement pst;
	private JTextField txtId;
	private JTextField textFieldNom;
	private JTextField textFieldPrenom;
	private JTextField textFieldCIN;
	private JTextField textFieldTel;
	private JTable table;
	/**
	 * Launch the application.
	 */
	
	public static void GestiondesClients() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gestion_clients frame = new Gestion_clients();
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
	public Gestion_clients() {
		
		setBounds(100, 100, 629, 463);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtId = new JTextField();
		txtId.setBounds(72, 65, 165, 19);
		contentPane.add(txtId);
		txtId.setColumns(10);
		
		JTextPane txtpnId = new JTextPane();
		txtpnId.setText("ID");
		txtpnId.setBounds(10, 65, 52, 19);
		contentPane.add(txtpnId);
		
		JTextPane txtpnNom = new JTextPane();
		txtpnNom.setText("Nom");
		txtpnNom.setBounds(10, 94, 52, 19);
		contentPane.add(txtpnNom);
		
		textFieldNom = new JTextField();
		textFieldNom.setColumns(10);
		textFieldNom.setBounds(72, 94, 165, 19);
		contentPane.add(textFieldNom);
		
		JTextPane txtpnPrnom = new JTextPane();
		txtpnPrnom.setText("Pr\u00E9nom");
		txtpnPrnom.setBounds(10, 123, 52, 19);
		contentPane.add(txtpnPrnom);
		
		JTextPane txtpnCin = new JTextPane();
		txtpnCin.setText("CIN");
		txtpnCin.setBounds(10, 152, 52, 19);
		contentPane.add(txtpnCin);
		
		JTextPane txtpnTel = new JTextPane();
		txtpnTel.setText("Tel");
		txtpnTel.setBounds(10, 181, 52, 19);
		contentPane.add(txtpnTel);
		
		textFieldPrenom = new JTextField();
		textFieldPrenom.setColumns(10);
		textFieldPrenom.setBounds(72, 123, 165, 19);
		contentPane.add(textFieldPrenom);
		
		textFieldCIN = new JTextField();
		textFieldCIN.setColumns(10);
		textFieldCIN.setBounds(72, 152, 165, 19);
		contentPane.add(textFieldCIN);
		
		textFieldTel = new JTextField();
		textFieldTel.setColumns(10);
		textFieldTel.setBounds(72, 181, 165, 19);
		contentPane.add(textFieldTel);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				
				DefaultTableModel model = (DefaultTableModel)table.getModel();
				
				int rIndex = table.getSelectedRow();
				
				txtId.setText(model.getValueAt(rIndex, 0).toString());
				textFieldNom.setText(model.getValueAt(rIndex, 1).toString());
				textFieldPrenom.setText(model.getValueAt(rIndex, 2).toString());
				textFieldCIN.setText(model.getValueAt(rIndex, 3).toString());
				textFieldTel.setText(model.getValueAt(rIndex, 4).toString());

			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"ID", "Nom", "Pr\u00E9nom", "CIN", "Tel"},
			},
			new String[] {
				"ID", "Nom", "Pr\u00E9nom", "CIN", "Tel"
			}
		)
		//permet de rendre les cellules du tableau non editable
		{public boolean isCellEditable(int row, int column) {return false;}}
		);
		table.setBounds(251, 76, 354, 306);
		contentPane.add(table);
		
		CLIENT client = new CLIENT();
		
		client.fillClientJTable(table);
		JButton btnAjouterClient = new JButton("Ajouter Client");
		btnAjouterClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//ajouter un nouveau client
				
				//obtenir les informations des fields
				String fnom = textFieldNom.getText();
				String fprenom = textFieldPrenom.getText();				
				String CIN = textFieldCIN.getText();
				String tel = textFieldTel.getText();
				if(fnom.trim().equals("") ||fprenom.trim().equals("") ||CIN.trim().equals("") ||tel.trim().equals(""))
				{
					JOptionPane.showMessageDialog(rootPane, "Champ requis -> Nom, Prenomn, CIN, Telephone", "Champ Vide", JOptionPane.WARNING_MESSAGE);
				}
				else {
					if(client.addClient(fnom, fprenom, CIN, tel))
				{
					JOptionPane.showMessageDialog(rootPane, "Nouveau Client ajouté avec succès", "Ajouter Client", JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					JOptionPane.showMessageDialog(rootPane, "Echec de l'ajout du client", "Erreur Ajouter Client", JOptionPane.ERROR_MESSAGE);
				}
					
				}

			}
		});
		btnAjouterClient.setBounds(10, 268, 112, 21);
		contentPane.add(btnAjouterClient);
		
		JButton buttonSupprimer = new JButton("Supprimer");
		buttonSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//on supprime le client sélectionné
				int id_client = Integer.valueOf(txtId.getText());
				
				if(client.removeClient(id_client))
				{
					JOptionPane.showMessageDialog(rootPane, "Client supprimé avec succès", "Supprimer Client", JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					JOptionPane.showMessageDialog(rootPane, "Echec de la suppression du client", "Erreur Supprimer Client", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		buttonSupprimer.setBounds(128, 268, 109, 21);
		contentPane.add(buttonSupprimer);
		
		JButton buttonModifier = new JButton("Modifier");
		buttonModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//modifier l'user selectionne
				
				//obtenir les info depuis les champs
				int id_client = Integer.valueOf(txtId.getText());
				String fnom = textFieldNom.getText();
				String fprenom = textFieldPrenom.getText();				
				String CIN = textFieldCIN.getText();
				String tel = textFieldTel.getText();
				
				
				if(fnom.trim().equals("") ||fprenom.trim().equals("") ||CIN.trim().equals("") ||tel.trim().equals(""))
				{
					JOptionPane.showMessageDialog(rootPane, "Champ requis -> Nom, Prenomn, CIN, Telephone", "Champ Vide", JOptionPane.WARNING_MESSAGE);
				}
				else {
					if(client.editClient(id_client, fnom, fprenom, CIN, tel))
				{
					JOptionPane.showMessageDialog(rootPane, "Client modifié avec succès", "Modifier Client", JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					JOptionPane.showMessageDialog(rootPane, "Echec de la mofication du client", "Erreur modifier Client", JOptionPane.ERROR_MESSAGE);
				}
					
				}
			}
		});
		buttonModifier.setBounds(10, 310, 112, 21);
		contentPane.add(buttonModifier);
		
		JButton btnClear = new JButton("Vider champs");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//supprime le text des jtextfields
				txtId.setText("");
				textFieldNom.setText("");
				textFieldPrenom.setText("");
				textFieldCIN.setText("");
				textFieldTel.setText("");
			}
		});
		btnClear.setBounds(128, 310, 112, 21);
		contentPane.add(btnClear);
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//clear le tableau
				table.setModel(new DefaultTableModel(null, new Object[] {"ID", "Nom", "Prenom", "CIN", "Tel"}));
				//puis on le rerempli
				client.fillClientJTable(table);
			}
		});
		btnRefresh.setBounds(251, 395, 354, 21);
		contentPane.add(btnRefresh);
		
		JTextPane txtpnGrerLesClients = new JTextPane();
		txtpnGrerLesClients.setText("G\u00E9rer les clients");
		txtpnGrerLesClients.setForeground(Color.WHITE);
		txtpnGrerLesClients.setFont(new Font("Tahoma", Font.BOLD, 35));
		txtpnGrerLesClients.setBackground(Color.DARK_GRAY);
		txtpnGrerLesClients.setBounds(257, 11, 348, 49);
		contentPane.add(txtpnGrerLesClients);
		
		JTextPane textPane = new JTextPane();
		textPane.setText("Champs \u00E0 remplir");
		textPane.setForeground(Color.WHITE);
		textPane.setFont(new Font("Tahoma", Font.BOLD, 20));
		textPane.setBackground(Color.DARK_GRAY);
		textPane.setBounds(10, 28, 218, 32);
		contentPane.add(textPane);
		
		JTextPane textPane_1 = new JTextPane();
		textPane_1.setText("Champs \u00E0 remplir");
		textPane_1.setForeground(Color.WHITE);
		textPane_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		textPane_1.setBackground(Color.DARK_GRAY);
		textPane_1.setBounds(10, 226, 218, 32);
		contentPane.add(textPane_1);
		
	}
}
