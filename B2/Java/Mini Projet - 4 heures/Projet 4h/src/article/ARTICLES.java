package article;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class ARTICLES {
MY_CONNECTION my_connection = new MY_CONNECTION();
//creer une function qui ajoute l'article
	 public boolean addArticle(String NomArticles, int Prix, String TVA, int NbrArticlesStock, int NbrArticleVendu, int CodeBarre)
	 
	 {
		 PreparedStatement st;
		 String addQuery = "INSERT INTO `articles`(`NOM_ARTICLES`, `PRIX`, `TVA`, `NBR_ARTICLE_STOCK`, `NBR_ARTICLE_VENDU`, `CODE_BARRE`) VALUES (?,?,?,?,?,?)";
		 
		 try {
			 
		 st = my_connection.createConnection().prepareStatement(addQuery);
		 
		 st.setString(1, NomArticles);
		 st.setInt(2, Prix);
		 st.setString(3, TVA);
		 st.setInt(4, NbrArticlesStock);
		 st.setInt(5, NbrArticleVendu);
		 st.setInt(6, CodeBarre);

		 
		 return (st.executeUpdate()>0);
	

		 }catch (SQLException ex) {
			 Logger.getLogger(ARTICLES.class.getName()).log(Level.SEVERE, null, ex);
			 return false;
		 }
		 
	 }
	 
	 public void fill_TVA_JCombobox(JComboBox comboBox)
	 {
		 PreparedStatement ps;
		 ResultSet rs;
		 String selectQuery = "SELECT * FROM `tva`";
		 
		 try {
			 
		 ps = my_connection.createConnection().prepareStatement(selectQuery);
		 
		 rs = ps.executeQuery();
		 
		 Object[] row;
		 
		 while(rs.next())
		 {
			 comboBox.addItem(rs.getString(1));
		 }
		 
		 }catch(SQLException ex){
			 Logger.getLogger(ARTICLES.class.getName()).log(Level.SEVERE, null, ex);
		 }
		 
	}
	//creer une fonction qui modifie l'article  selectionne
	 //je sais pas pourquoi ca marche pas
		 public boolean editArticles(int reference, String NomArticles, int Prix, String TVA, int NbrArticlesStock, int NbrArticleVendu, int CodeBarre)
		 {
			 PreparedStatement st;
			 String editQuery = "UPDATE `articles` SET `NOM_ARTICLES`=?,`PRIX`=?,`TVA`=?,`NBR_ARTICLE_STOCK`=?, 'NBR_ARTICLE_VENDU'=?, 'CODE_BARRE'=? WHERE`REFERENCE`=?";
			 
			 try {
				 
			 st = my_connection.createConnection().prepareStatement(editQuery);
			 
			 
			 st.setString(1, NomArticles);
			 st.setInt(2, Prix);
			 st.setString(4, TVA);
			 st.setInt(5, NbrArticlesStock);
			 st.setInt(6, NbrArticleVendu);
			 st.setInt(7, CodeBarre);
			 st.setInt(3, reference);



			 return (st.executeUpdate()>0);


			 }catch (SQLException ex) {
				 Logger.getLogger(ARTICLES.class.getName()).log(Level.SEVERE, null, ex);
				 return false;
			 }
			 
		 }
		 
		 //creer une fonction qui remplit le tableau de client grace a la bdd
		 public void fillArticleTable(JTable table)
		 {
			 PreparedStatement ps;
			 ResultSet rs;
			 String selectQuery = "SELECT * FROM `articles`";
			 
			 try {
				 
			 ps = my_connection.createConnection().prepareStatement(selectQuery);
			 
			 rs = ps.executeQuery();
			 
			 DefaultTableModel tableModel = (DefaultTableModel)table.getModel();
			 
			 Object[] row;
			 
			 while(rs.next())
			 {
				 row = new Object[7];
				 row[0] = rs.getString(1);
				 row[1] = rs.getInt(2);
				 row[2] = rs.getString(3);
				 row[3] = rs.getInt(4);
				 row[4] = rs.getInt(5);
				 row[5] = rs.getInt(6);
				 row[6] = rs.getInt(7);

				 tableModel.addRow(row);

				 
			 }
			 
			 }catch(SQLException ex){
				 Logger.getLogger(ARTICLES.class.getName()).log(Level.SEVERE, null, ex);
			 }
			 
		}
		 //creer une fonction qui supprime la chambre selectionne
		 public boolean removeArticles(int number) 
		 {
			 PreparedStatement st;
			 String deleteQuery = "DELETE FROM `articles` WHERE `reference`=?";
			 
			 try {
				 
			 st = my_connection.createConnection().prepareStatement(deleteQuery);
			 
			 st.setInt(1, number);

			 return (st.executeUpdate()>0);


			 }catch (SQLException ex) {
				 Logger.getLogger(ARTICLES.class.getName()).log(Level.SEVERE, null, ex);
				 return false;
			 }
		 }
}
