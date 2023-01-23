package gestion_hotel;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class RESERVATION {
MY_CONNECTION my_connection = new MY_CONNECTION();
			//creer une fonction qui permet d'jouter une reservation
			public boolean addReservation(int client_id, int room_number, String dateEntree, String dateSorti)
			 
			 {
				 PreparedStatement st;
				 String addQuery = "INSERT INTO `reservation`(`id_client`, `numero_chambre`, `date_debut`, `date_fin`) VALUES (?,?,?,?)";
				 
				 try {
					 
				 st = my_connection.createConnection().prepareStatement(addQuery);
				 
				 st.setInt(1, client_id);
				 st.setInt(2, room_number);
				 st.setString(3, dateEntree);
				 st.setString(4, dateSorti);

			
				 return (st.executeUpdate()>0);
			

				 }catch (SQLException ex) {
					 Logger.getLogger(CLIENT.class.getName()).log(Level.SEVERE, null, ex);
					 return false;
				 }
				 
			 }
			//creer une fonction qui modifie la reservation
			 public boolean editReservation(int reservation_id, int client_id, int room_number, String dateEntree, String dateSorti)
			 {
				 PreparedStatement st;
				 String editQuery = "UPDATE `reservation` SET `id_client`=?,`numero_chambre`=?,`date_debut`=?,`date_fin`=? WHERE `id_reservation`=?";
				 
				 try {
					 
				 st = my_connection.createConnection().prepareStatement(editQuery);
				 
				 st.setInt(1, client_id);
				 st.setInt(2, room_number);
				 st.setString(3, dateEntree);
				 st.setString(4, dateSorti);
				 st.setInt(5, reservation_id);


				 return (st.executeUpdate()>0);


				 }catch (SQLException ex) {
					 Logger.getLogger(CLIENT.class.getName()).log(Level.SEVERE, null, ex);
					 return false;
				 }
				 
			 }
			//creer une fonction qui supprime la reservation selectionne
			 public boolean removeReservation(int room_number) 
			 {
				 PreparedStatement st;
				 String deleteQuery = "DELETE FROM `reservation` WHERE `id_reservation`=?";
				 
				 try {
					 
				 st = my_connection.createConnection().prepareStatement(deleteQuery);
				 
				 st.setInt(1, room_number);

				 return (st.executeUpdate()>0);


				 }catch (SQLException ex) {
					 Logger.getLogger(CLIENT.class.getName()).log(Level.SEVERE, null, ex);
					 return false;
				 }
			 }
			 //creer une fonction qui remplit le tableau des reservation grace a la bdd
			 public void fillReservationJTable(JTable table)
			 {
				 PreparedStatement ps;
				 ResultSet rs;
				 String selectQuery = "SELECT * FROM `reservation`";
				 
				 try {
					 
				 ps = my_connection.createConnection().prepareStatement(selectQuery);
				 
				 rs = ps.executeQuery();
				 
				 DefaultTableModel tableModel = (DefaultTableModel)table.getModel();
				 
				 Object[] row;
				 
				 while(rs.next())
				 {
					 row = new Object[5];
					 row[0] = rs.getInt(1);
					 row[1] = rs.getInt(2);
					 row[2] = rs.getInt(3);
					 row[3] = rs.getString(4);
					 row[4] = rs.getString(5);

					 
					 tableModel.addRow(row);
				 }
				 
				 }catch(SQLException ex){
					 Logger.getLogger(CLIENT.class.getName()).log(Level.SEVERE, null, ex);
				 }
				 
			}
}
