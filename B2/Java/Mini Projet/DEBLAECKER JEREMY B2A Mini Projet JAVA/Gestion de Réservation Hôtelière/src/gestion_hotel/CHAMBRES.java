package gestion_hotel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class CHAMBRES {
	
	MY_CONNECTION my_connection = new MY_CONNECTION();

	public void fillType_Chambre_JTable(JTable table)
	 {
		 PreparedStatement ps;
		 ResultSet rs;
		 String selectQuery = "SELECT * FROM `type`";
		 
		 try {
			 
		 ps = my_connection.createConnection().prepareStatement(selectQuery);
		 
		 rs = ps.executeQuery();
		 
		 DefaultTableModel tableModel = (DefaultTableModel)table.getModel();
		 
		 Object[] row;
		 
		 while(rs.next())
		 {
			 row = new Object[3];
			 row[0] = rs.getInt(1);
			 row[1] = rs.getString(2);
			 row[2] = rs.getString(3);
			 
			 tableModel.addRow(row);
		 }
		 
		 }catch(SQLException ex){
			 Logger.getLogger(CLIENT.class.getName()).log(Level.SEVERE, null, ex);
		 }
		 
	}
	
	public void fillType_Chambre_JCombobox(JComboBox comboBoxType)
	 {
		 PreparedStatement ps;
		 ResultSet rs;
		 String selectQuery = "SELECT * FROM `type`";
		 
		 try {
			 
		 ps = my_connection.createConnection().prepareStatement(selectQuery);
		 
		 rs = ps.executeQuery();
		 
		 Object[] row;
		 
		 while(rs.next())
		 {
			 comboBoxType.addItem(rs.getInt(1));
		 }
		 
		 }catch(SQLException ex){
			 Logger.getLogger(CLIENT.class.getName()).log(Level.SEVERE, null, ex);
		 }
		 
	}
	//creer une fonction qui permet d'jouter une chambre
	public boolean addChambre(int number, int type)
	 
	 {
		 PreparedStatement st;
		 String addQuery = "INSERT INTO `chambre`(`id_chambre`, `type_chambre`, `reservé`) VALUES (?,?,?)";
		 
		 try {
			 
		 st = my_connection.createConnection().prepareStatement(addQuery);
		 
		 st.setInt(1, number);
		 st.setInt(2, type);
		 
		 st.setString(3, "NON");
	
		 return (st.executeUpdate()>0);
	

		 }catch (SQLException ex) {
			 Logger.getLogger(CLIENT.class.getName()).log(Level.SEVERE, null, ex);
			 return false;
		 }
		 
	 }
	//creer une fonction qui modifie la chambre selectionne
		 public boolean editChambre(int NumeroChambres, int type, String isReeserved)
		 {
			 PreparedStatement st;
			 String editQuery = "UPDATE `chambre` SET `type_chambre`=?,`reservé`=? WHERE `id_chambre`=?";
			 
			 try {
				 
			 st = my_connection.createConnection().prepareStatement(editQuery);
			 
			 st.setInt(1, type);
			 st.setString(2, isReeserved);
			 st.setInt(3, NumeroChambres);			 



			 return (st.executeUpdate()>0);


			 }catch (SQLException ex) {
				 Logger.getLogger(CLIENT.class.getName()).log(Level.SEVERE, null, ex);
				 return false;
			 }
			 
		 }
		 
		//creer une fonction qui supprime la chambre selectionne
		 public boolean removeRoom(int number) 
		 {
			 PreparedStatement st;
			 String deleteQuery = "DELETE FROM `chambre` WHERE `id_chambre`=?";
			 
			 try {
				 
			 st = my_connection.createConnection().prepareStatement(deleteQuery);
			 
			 st.setInt(1, number);

			 return (st.executeUpdate()>0);


			 }catch (SQLException ex) {
				 Logger.getLogger(CLIENT.class.getName()).log(Level.SEVERE, null, ex);
				 return false;
			 }
		 }
		 
		//creer une fonction qui remplit le tableau de chambres grace a la bdd
		 public void fillChambresJTable(JTable table)
		 {
			 PreparedStatement ps;
			 ResultSet rs;
			 String selectQuery = "SELECT * FROM `chambre`";
			 
			 try {
				 
			 ps = my_connection.createConnection().prepareStatement(selectQuery);
			 
			 rs = ps.executeQuery();
			 
			 DefaultTableModel tableModel = (DefaultTableModel)table.getModel();
			 
			 Object[] row;
			 
			 while(rs.next())
			 {
				 row = new Object[3];
				 row[0] = rs.getInt(1);
				 row[1] = rs.getInt(2);
				 row[2] = rs.getString(3);
				 
				 tableModel.addRow(row);
			 }
			 
			 }catch(SQLException ex){
				 Logger.getLogger(CLIENT.class.getName()).log(Level.SEVERE, null, ex);
			 }
			 
		}
}
