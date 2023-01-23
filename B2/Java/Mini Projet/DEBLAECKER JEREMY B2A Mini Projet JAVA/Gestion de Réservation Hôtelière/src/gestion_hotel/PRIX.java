package gestion_hotel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class PRIX {
	MY_CONNECTION my_connection = new MY_CONNECTION();
	public Object fillTable;
	//creer une fonction qui permet d'ajouter un type de chambre
	public boolean addPrix(String label, int prix)
	 
	 {
		 PreparedStatement st;
		 String addQuery = "INSERT INTO `type`(`label`, `price`) VALUES (?,?)";
		 
		 try {
			 
		 st = my_connection.createConnection().prepareStatement(addQuery);
		 
		 st.setString(1, label);
		 st.setInt(2, prix);
	
		 return (st.executeUpdate()>0);
	

		 }catch (SQLException ex) {
			 Logger.getLogger(PRIX.class.getName()).log(Level.SEVERE, null, ex);
			 return false;
		 }
		 
	 }
	//creer une fonction qui modifie le type de chambre
	 public boolean editType(int id_type, String label, int prix)
	 {
		 PreparedStatement st;
		 String editQuery = "UPDATE `type` SET `label`=?,`price`=? WHERE `id`=?";
		 
		 try {
			 
		 st = my_connection.createConnection().prepareStatement(editQuery);
		 
		 st.setString(1, label);
		 st.setInt(2, prix);	 
		 st.setInt(3, id_type);



		 return (st.executeUpdate()>0);


		 }catch (SQLException ex) {
			 Logger.getLogger(PRIX.class.getName()).log(Level.SEVERE, null, ex);
			 return false;
		 }
		 
	 }
	//creer une fonction qui supprime le type selectionne
	 public boolean removeType(int id) 
	 {
		 PreparedStatement st;
		 String deleteQuery = "DELETE FROM `type` WHERE `id`=?";
		 
		 try {
			 
		 st = my_connection.createConnection().prepareStatement(deleteQuery);
		 
		 st.setInt(1, id);

		 return (st.executeUpdate()>0);


		 }catch (SQLException ex) {
			 Logger.getLogger(PRIX.class.getName()).log(Level.SEVERE, null, ex);
			 return false;
		 }
	 }
	 //creer une fonction qui remplit le tableau des reservation grace a la bdd
	 public void fillTable(JTable table)
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
			 row[2] = rs.getInt(3);

			 
			 tableModel.addRow(row);
		 }
		 
		 }catch(SQLException ex){
			 Logger.getLogger(PRIX.class.getName()).log(Level.SEVERE, null, ex);
		 }
		 
	}
}
