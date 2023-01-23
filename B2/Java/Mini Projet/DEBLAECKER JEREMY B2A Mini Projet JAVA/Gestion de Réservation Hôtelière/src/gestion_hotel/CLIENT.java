package gestion_hotel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.util.logging.Level;


public class CLIENT { //la classe client
	 MY_CONNECTION my_connection = new MY_CONNECTION();

	 //creer une function qui ajoute le client
	 public boolean addClient(String fnom, String fprenom, String tel, String CIN)
	 
	 {
		 PreparedStatement st;
		 String addQuery = "INSERT INTO `clients`(`nom`, `prenom`, `CIN`, `tel`) VALUES (?,?,?,?)";
		 
		 try {
			 
		 st = my_connection.createConnection().prepareStatement(addQuery);
		 
		 st.setString(1, fnom);
		 st.setString(2, fprenom);
		 st.setString(3, tel);
		 st.setString(4, CIN);
		 
		 return (st.executeUpdate()>0);
	

		 }catch (SQLException ex) {
			 Logger.getLogger(CLIENT.class.getName()).log(Level.SEVERE, null, ex);
			 return false;
		 }
		 
	 }
	 //creer une fonction qui modifie le client selectionne
	 public boolean editClient(int id_client, String fnom, String fprenom, String tel, String CIN)
	 {
		 PreparedStatement st;
		 String editQuery = "UPDATE `clients` SET `nom`=?,`prenom`=?,`CIN`=?,`tel`=? WHERE`id_clients`=?";
		 
		 try {
			 
		 st = my_connection.createConnection().prepareStatement(editQuery);
		 
		 st.setString(1, fnom);
		 st.setString(2, fprenom);
		 st.setString(3, tel);
		 st.setString(4, CIN);
		 st.setInt(5, id_client);

		 return (st.executeUpdate()>0);


		 }catch (SQLException ex) {
			 Logger.getLogger(CLIENT.class.getName()).log(Level.SEVERE, null, ex);
			 return false;
		 }
		 
	 }
	 //creer une fonction qui supprime le client selectionne
	 public boolean removeClient(int id) 
	 {
		 PreparedStatement st;
		 ResultSet rs;
		 String deleteQuery = "DELETE FROM `clients` WHERE `id_clients`=?";
		 
		 try {
			 
		 st = my_connection.createConnection().prepareStatement(deleteQuery);
		 
		 st.setInt(1, id);

		 return (st.executeUpdate()>0);


		 }catch (SQLException ex) {
			 Logger.getLogger(CLIENT.class.getName()).log(Level.SEVERE, null, ex);
			 return false;
		 }
	 }
	 //creer une fonction qui remplit le tableau de client grace a la bdd
	 public void fillClientJTable(JTable table)
	 {
		 PreparedStatement ps;
		 ResultSet rs;
		 String selectQuery = "SELECT * FROM `clients`";
		 
		 try {
			 
		 ps = my_connection.createConnection().prepareStatement(selectQuery);
		 
		 rs = ps.executeQuery();
		 
		 DefaultTableModel tableModel = (DefaultTableModel)table.getModel();
		 
		 Object[] row;
		 
		 while(rs.next())
		 {
			 row = new Object[5];
			 row[0] = rs.getInt(1);
			 row[1] = rs.getString(2);
			 row[2] = rs.getString(3);
			 row[3] = rs.getString(4);
			 row[4] = rs.getString(5);
			 
			 tableModel.addRow(row);
		 }
		 
		 }catch(SQLException ex){
			 Logger.getLogger(CLIENT.class.getName()).log(Level.SEVERE, null, ex);
		 }
		 
	}
	 
}
