package gestion_hotel;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class gestion_prix extends JFrame {

	private JPanel contentPane;
	private JTable tableTitle;
	private JTextField textFieldID;
	private JTextField textFieldType;
	private JTextField textFieldPrix;

	/**
	 * Launch the application.
	 */
	
	PRIX prix = new PRIX();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					gestion_prix frame = new gestion_prix();
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
	public gestion_prix() {
		setBounds(100, 100, 587, 355);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextPane txtpnIdTypeDe = new JTextPane();
		txtpnIdTypeDe.setText("ID Type de chambre");
		txtpnIdTypeDe.setBounds(10, 74, 122, 19);
		contentPane.add(txtpnIdTypeDe);
		
		JTextPane txtpnTypeDeChambre = new JTextPane();
		txtpnTypeDeChambre.setText("Type de chambre");
		txtpnTypeDeChambre.setBounds(10, 103, 122, 19);
		contentPane.add(txtpnTypeDeChambre);
		
		JTextPane txtpnPrixChambre = new JTextPane();
		txtpnPrixChambre.setText("Prix chambre");
		txtpnPrixChambre.setBounds(10, 132, 122, 19);
		contentPane.add(txtpnPrixChambre);
		
		tableTitle = new JTable();
		tableTitle.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				DefaultTableModel model = (DefaultTableModel)tableTitle.getModel();
				
				int rIndex = tableTitle.getSelectedRow();
				
				textFieldID.setText(model.getValueAt(rIndex, 0).toString());
				textFieldType.setText(model.getValueAt(rIndex, 1).toString());
				textFieldPrix.setText(model.getValueAt(rIndex, 2).toString());
						
			}
		});
		tableTitle.setModel(new DefaultTableModel(
			new Object[][] {
				{"ID Type de chambres", "Types de chambres", "Prix Chambres"},
			},
			new String[] {
				"ID Type de chambres", "Types de chambres", "Prix chambres"
			}
		));
		
		
		tableTitle.getColumnModel().getColumn(0).setPreferredWidth(124);
		tableTitle.getColumnModel().getColumn(1).setPreferredWidth(123);
		tableTitle.getColumnModel().getColumn(2).setPreferredWidth(92);
		tableTitle.setBounds(248, 74, 315, 229);
		contentPane.add(tableTitle);
		prix.fillTable(tableTitle);

		
		textFieldID = new JTextField();
		textFieldID.setColumns(10);
		textFieldID.setBounds(142, 74, 96, 19);
		contentPane.add(textFieldID);
		
		textFieldType = new JTextField();
		textFieldType.setColumns(10);
		textFieldType.setBounds(142, 103, 96, 19);
		contentPane.add(textFieldType);
		
		textFieldPrix = new JTextField();
		textFieldPrix.setColumns(10);
		textFieldPrix.setBounds(142, 132, 96, 19);
		contentPane.add(textFieldPrix);
		//int id_type = Integer.valueOf(textFieldID.getText());

		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String label = textFieldType.getText();
					int prixchambre = Integer.valueOf(textFieldPrix.getText());
		
					if(prix.addPrix(label, prixchambre)) {
						JOptionPane.showMessageDialog(rootPane, "Nouvel réservation ajouté avec succes", "Ajouter reservation", JOptionPane.INFORMATION_MESSAGE);
					}else {
						JOptionPane.showMessageDialog(rootPane, "Echec de l'ajout de la reservation", "Echec Ajout reservation", JOptionPane.ERROR_MESSAGE);
					}
				}catch(NumberFormatException ex){
					JOptionPane.showMessageDialog(rootPane, ex.getMessage()+"-Renseigner le numéro de la reservation", "Erreur reservation", JOptionPane.ERROR_MESSAGE);	
				}
				
			}
		});
		btnAjouter.setBounds(10, 203, 112, 21);
		contentPane.add(btnAjouter);
		
		JTextPane textPane = new JTextPane();
		textPane.setText("Champs \u00E0 remplir");
		textPane.setForeground(Color.WHITE);
		textPane.setFont(new Font("Tahoma", Font.BOLD, 20));
		textPane.setBackground(Color.DARK_GRAY);
		textPane.setBounds(10, 28, 218, 32);
		contentPane.add(textPane);
		
		JTextPane textPane_1 = new JTextPane();
		textPane_1.setText("Boutons");
		textPane_1.setForeground(Color.WHITE);
		textPane_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		textPane_1.setBackground(Color.DARK_GRAY);
		textPane_1.setBounds(10, 161, 218, 32);
		contentPane.add(textPane_1);
		
		JButton button_1 = new JButton("Supprimer");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//on supprime le client sélectionné
				int id = Integer.valueOf(textFieldID.getText());
				
				if(prix.removeType(id))
				{
					JOptionPane.showMessageDialog(rootPane, "Client supprimé avec succès", "Supprimer Client", JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					JOptionPane.showMessageDialog(rootPane, "Echec de la suppression du client", "Erreur Supprimer Client", JOptionPane.ERROR_MESSAGE);
				}
			}
				
			}
		);
		button_1.setBounds(142, 203, 96, 21);
		contentPane.add(button_1);
		
		JButton button = new JButton("Modifier");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = Integer.valueOf(textFieldID.getText());
				String label = textFieldType.getText();
				int prixchambre = Integer.valueOf(textFieldPrix.getText());
				
				
				if(label.trim().equals(""))
				{
					JOptionPane.showMessageDialog(rootPane, "Champ requis -> Type et Prix", "Champ Vide", JOptionPane.WARNING_MESSAGE);
				}
				else {
					if(prix.editType(id, label, prixchambre))
				{
					JOptionPane.showMessageDialog(rootPane, "Client modifié avec succès", "Modifier Client", JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					JOptionPane.showMessageDialog(rootPane, "Echec de la mofication du client", "Erreur modifier Client", JOptionPane.ERROR_MESSAGE);
				}
					
				}
			}
		});
		button.setBounds(10, 234, 112, 21);
		contentPane.add(button);
		
		JTextPane txtpnGrerLesPrix = new JTextPane();
		txtpnGrerLesPrix.setText("G\u00E9rer les prix");
		txtpnGrerLesPrix.setForeground(Color.WHITE);
		txtpnGrerLesPrix.setFont(new Font("Tahoma", Font.BOLD, 35));
		txtpnGrerLesPrix.setBackground(Color.DARK_GRAY);
		txtpnGrerLesPrix.setBounds(248, 18, 315, 46);
		contentPane.add(txtpnGrerLesPrix);
		
		JButton button_2 = new JButton("Vider champs");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//supprime le text des jtextfields
				textFieldID.setText("");
				textFieldType.setText("");
				textFieldPrix.setText("");

			}
		});
		button_2.setBounds(142, 234, 96, 21);
		contentPane.add(button_2);
		
		JButton button_3 = new JButton("Refresh");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//clear le tableau
				tableTitle.setModel(new DefaultTableModel(null, new Object[] {"ID Type de chambres", "Types de chambres", "Prix Chambres"}));
				//puis on le rerempli
				prix.fillTable(tableTitle);
			}
		});
		button_3.setBounds(10, 282, 228, 21);
		contentPane.add(button_3);
	}
}
