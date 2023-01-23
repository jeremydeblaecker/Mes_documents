package gestion_hotel;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JTable;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.JRadioButton;

public class GererChambres extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldNumero;
	private JTable tableChambres;

	/**
	 * Launch the application.
	 */
	
	CHAMBRES chambres = new CHAMBRES();
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GererChambres frame = new GererChambres();
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
	public GererChambres() {
		setBounds(100, 100, 496, 379);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setForeground(Color.DARK_GRAY);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		textFieldNumero = new JTextField();
		textFieldNumero.setBounds(72, 54, 165, 19);
		textFieldNumero.setColumns(10);
		panel.add(textFieldNumero);
		
		JTextPane txtpnNumro = new JTextPane();
		txtpnNumro.setBounds(10, 54, 52, 19);
		txtpnNumro.setText("Num\u00E9ro");
		panel.add(txtpnNumro);
		
		JTextPane txtpnType = new JTextPane();
		txtpnType.setBounds(10, 83, 52, 19);
		txtpnType.setText("Type");
		panel.add(txtpnType);
		
		tableChambres = new JTable();
		tableChambres.setModel(new DefaultTableModel(
			new Object[][] {
				{"Num\u00E9ro", "Type", "Reserv\u00E9"},
			},
			new String[] {
				"Num\u00E9ro", "Type", "Reserv\u00E9"
			}
		));

		tableChambres.setBounds(259, 13, 203, 239);
		panel.add(tableChambres);
		JComboBox jcomboBoxType = new JComboBox();

		JButton btnAjouterChambre = new JButton("Ajouter Chambre");
		btnAjouterChambre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			try {
				int NumeroChambres = Integer.valueOf(textFieldNumero.getText());
				int TypeChambres = Integer.valueOf(jcomboBoxType.getSelectedItem().toString());
				
				if(chambres.addChambre(NumeroChambres, TypeChambres)) {
					JOptionPane.showMessageDialog(rootPane, "Nouvel Chambre ajouter avec succes", "Ajouter Chambre", JOptionPane.INFORMATION_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(rootPane, "Echec de l'ajout de la chambre", "Echec Ajout Chambre", JOptionPane.ERROR_MESSAGE);
				}
			}catch(NumberFormatException ex){
				JOptionPane.showMessageDialog(rootPane, ex.getMessage()+"-Renseigner le numéro de la chambre", "Erreur Id", JOptionPane.ERROR_MESSAGE);	
			}
				
			}
		});
		btnAjouterChambre.setBounds(10, 191, 112, 21);
		panel.add(btnAjouterChambre);
		
		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//on supprime le client sélectionné
				int id_client = Integer.valueOf(textFieldNumero.getText());
				
				if(chambres.removeRoom(id_client))
				{
					JOptionPane.showMessageDialog(rootPane, "Chambre supprimé avec succès", "Supprimer Chambre", JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					JOptionPane.showMessageDialog(rootPane, "Echec de la suppression de la chambre", "Erreur Supprimer Chambre", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnSupprimer.setBounds(132, 191, 105, 21);
		panel.add(btnSupprimer);

		
		JButton button_4 = new JButton("Refresh");
		button_4.setBounds(251, 395, 354, 21);
		panel.add(button_4);
		
		JTextPane txtpnGrerLesChambres = new JTextPane();
		txtpnGrerLesChambres.setFont(new Font("Tahoma", Font.BOLD, 20));
		txtpnGrerLesChambres.setBackground(Color.DARK_GRAY);
		txtpnGrerLesChambres.setBounds(10, 13, 227, 31);
		txtpnGrerLesChambres.setForeground(Color.WHITE);
		txtpnGrerLesChambres.setText("G\u00E9rer les chambres");
		panel.add(txtpnGrerLesChambres);
		
		jcomboBoxType.setBounds(72, 83, 165, 21);
		panel.add(jcomboBoxType);
		
		
		
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				//clear le tableau
				tableChambres.setModel(new DefaultTableModel(null, new Object[] {"Numéro Chambre", "Type", "Reserve"}));
				//puis on le rerempli
				chambres.fillChambresJTable(tableChambres);
			}
		});
		btnRefresh.setBounds(259, 275, 203, 29);
		panel.add(btnRefresh);
		
		
		
		
		chambres.fillType_Chambre_JCombobox(jcomboBoxType);
		chambres.fillChambresJTable(tableChambres);
		
		JButton btnAfficherTypes = new JButton("Afficher Types Chambres");
		btnAfficherTypes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			TOUS_TYPE_CHAMBRES TypeChambre = new TOUS_TYPE_CHAMBRES(); 
			TypeChambre.setVisible(true);
			TypeChambre.pack();
			TypeChambre.setLocationRelativeTo(null);
			
			TypeChambre.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});
		
		
		
		
		btnAfficherTypes.setBounds(10, 275, 227, 29);
		panel.add(btnAfficherTypes);
		
		JTextPane txtpnReserv = new JTextPane();
		txtpnReserv.setText("Reserv\u00E9");
		txtpnReserv.setBounds(10, 112, 52, 19);
		panel.add(txtpnReserv);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.DARK_GRAY);
		panel_1.setBounds(72, 114, 165, 52);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JRadioButton rdbtnOui = new JRadioButton("OUI");
		rdbtnOui.setForeground(Color.WHITE);
		rdbtnOui.setBackground(Color.DARK_GRAY);
		rdbtnOui.setBounds(6, 6, 105, 21);
		panel_1.add(rdbtnOui);
		
		JRadioButton rdbtnNon = new JRadioButton("NON");
		rdbtnNon.setForeground(Color.WHITE);
		rdbtnNon.setBackground(Color.DARK_GRAY);
		rdbtnNon.setBounds(6, 25, 105, 21);
		panel_1.add(rdbtnNon);
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(rdbtnOui);
		bg.add(rdbtnNon);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldNumero.setText("");
				jcomboBoxType.setSelectedIndex(0);
				rdbtnOui.setSelected(false);
			}
		});
		btnClear.setBounds(132, 231, 105, 21);
		panel.add(btnClear);
		tableChambres.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				DefaultTableModel model = (DefaultTableModel)tableChambres.getModel();
				
				int rIndex = tableChambres.getSelectedRow();
				
				textFieldNumero.setText(model.getValueAt(rIndex, 0).toString());
				jcomboBoxType.setSelectedItem(model.getValueAt(rIndex, 1));
				String isReeserved = model.getValueAt(rIndex, 2).toString();
				
				if(isReeserved.equals("OUI"))
				{
					rdbtnOui.setSelected(true);
				}
				else if(isReeserved.equals("NON"))
				{
					rdbtnNon.setSelected(true);
				}
			}
		});
		
		JButton btnModifier = new JButton("Modifier");
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//modifier la chambre selectionne
				//obtenir les info depuis les champs
				int NumeroChambres;
				int type = Integer.valueOf(jcomboBoxType.getSelectedItem().toString());
				String isReeserved = "NO";				
				
				if(rdbtnOui.isSelected())
				{
					isReeserved="OUI";
				}
				
				if(isReeserved.trim().equals(""))
				{
					JOptionPane.showMessageDialog(rootPane, "Entrer les champs", "Champs Vides", JOptionPane.WARNING_MESSAGE);
				}
				else {
					try {
						NumeroChambres = Integer.valueOf(textFieldNumero.getText());
						if(chambres.editChambre(NumeroChambres, type, isReeserved)){
							JOptionPane.showMessageDialog(rootPane, "Chambre modifié avec succès", "Modifier Chambre", JOptionPane.INFORMATION_MESSAGE);

						}else {
							JOptionPane.showMessageDialog(rootPane, "Echec de la mofication de la Chambre", "Erreur mofication Chambre", JOptionPane.ERROR_MESSAGE);
						}
					}
					catch(NumberFormatException ex)
					{
						
					}
				}
			}
		});
		btnModifier.setBounds(10, 231, 112, 21);
		panel.add(btnModifier);
	}
}
