package gestion_hotel;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;

public class TOUS_TYPE_CHAMBRES extends JFrame {

	private JPanel contentPane;
	private JTable tableType;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TOUS_TYPE_CHAMBRES frame = new TOUS_TYPE_CHAMBRES();
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
	public TOUS_TYPE_CHAMBRES() {
		setBackground(Color.DARK_GRAY);
		setBounds(100, 100, 563, 425);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tableType = new JTable();
		tableType.setBackground(Color.WHITE);
		tableType.setModel(new DefaultTableModel(
			new Object[][] {
				{"TypeID", "Label", "Prix"},
			},
			new String[] {
				"TypeID", "Label", "Prix"
			}
		));
		tableType.getColumnModel().getColumn(0).setPreferredWidth(101);
		
		CHAMBRES chambre = new CHAMBRES();
		chambre.fillType_Chambre_JTable(tableType);
		tableType.setBounds(36, 36, 477, 308);
		contentPane.add(tableType);
	}

}
