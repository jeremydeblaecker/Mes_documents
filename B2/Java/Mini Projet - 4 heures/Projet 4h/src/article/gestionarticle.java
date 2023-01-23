package article;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class gestionarticle {

	private JFrame frame;
	private JTextField textFieldNomArticle;
	private JTable table;
	private JTextField textFieldPrix;
	private JTextField textFieldReference;
	private JTextField textFieldNbrArticleStock;
	private JTextField textFieldNombreArticleVendu;
	private JTextField textFieldCodeBarre;
	
	

	/**
	 * Launch the application.
	 */
	
	ARTICLES articles = new ARTICLES();
	protected Component rootPane;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					gestionarticle window = new gestionarticle();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public gestionarticle() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		
		frame.getContentPane().setBackground(Color.DARK_GRAY);
		frame.setBounds(100, 100, 801, 508);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTextPane txtpnAjouer = new JTextPane();
		txtpnAjouer.setText("Champs \u00E0 remplir");
		txtpnAjouer.setForeground(Color.WHITE);
		txtpnAjouer.setFont(new Font("Tahoma", Font.BOLD, 20));
		txtpnAjouer.setBackground(Color.DARK_GRAY);
		txtpnAjouer.setBounds(10, 50, 312, 31);
		frame.getContentPane().add(txtpnAjouer);
		
		JTextPane txtpnNomDarticle = new JTextPane();
		txtpnNomDarticle.setText("Nom d'article");
		txtpnNomDarticle.setBounds(10, 91, 82, 19);
		frame.getContentPane().add(txtpnNomDarticle);
		
		textFieldNomArticle = new JTextField();
		textFieldNomArticle.setColumns(10);
		textFieldNomArticle.setBounds(102, 91, 220, 19);
		frame.getContentPane().add(textFieldNomArticle);
		
		table = new JTable();
		
		

		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"Nom Article", "Prix", "Reference", "TVA", "Nbr Article Stock", "Nbr Article Vendu", "Code Barre"},
			},
			new String[] {
				"Nom Article", "Prix", "Reference", "TVA", "Nbr Article Stock", "Nbr Article Vendu", "Code Barre"
			}
			
		));
		table.getColumnModel().getColumn(4).setPreferredWidth(122);
		table.getColumnModel().getColumn(5).setPreferredWidth(93);

		table.setBounds(332, 10, 445, 388);
		articles.fillArticleTable(table);
		frame.getContentPane().add(table);
		
		JTextPane txtpnPrix = new JTextPane();
		txtpnPrix.setText("Prix");
		txtpnPrix.setBounds(10, 120, 82, 19);
		frame.getContentPane().add(txtpnPrix);
		
		textFieldPrix = new JTextField();
		textFieldPrix.setColumns(10);
		textFieldPrix.setBounds(102, 120, 220, 19);
		frame.getContentPane().add(textFieldPrix);
		
		JTextPane txtpnRfrences = new JTextPane();
		txtpnRfrences.setText("R\u00E9f\u00E9rences");
		txtpnRfrences.setBounds(10, 149, 82, 19);
		frame.getContentPane().add(txtpnRfrences);
		
		JTextPane txtpnTva = new JTextPane();
		txtpnTva.setText("TVA");
		txtpnTva.setBounds(10, 178, 82, 19);
		frame.getContentPane().add(txtpnTva);
		
		JTextPane txtpnNombreDarticleEn = new JTextPane();
		txtpnNombreDarticleEn.setText("Nombre d\u2019article en stock");
		txtpnNombreDarticleEn.setBounds(10, 207, 82, 31);
		frame.getContentPane().add(txtpnNombreDarticleEn);
		
		JTextPane txtpnNombreDarticleVendu = new JTextPane();
		txtpnNombreDarticleVendu.setText("Nombre d\u2019article vendu");
		txtpnNombreDarticleVendu.setBounds(10, 248, 82, 37);
		frame.getContentPane().add(txtpnNombreDarticleVendu);
		
		JTextPane txtpnCodeBarrenumro = new JTextPane();
		txtpnCodeBarrenumro.setText("Code barre (num\u00E9ro)");
		txtpnCodeBarrenumro.setBounds(10, 295, 82, 37);
		frame.getContentPane().add(txtpnCodeBarrenumro);
		
		textFieldReference = new JTextField();
		textFieldReference.setColumns(10);
		textFieldReference.setBounds(102, 149, 220, 19);
		frame.getContentPane().add(textFieldReference);
		
		textFieldNbrArticleStock = new JTextField();
		textFieldNbrArticleStock.setColumns(10);
		textFieldNbrArticleStock.setBounds(102, 207, 220, 31);
		frame.getContentPane().add(textFieldNbrArticleStock);
		
		textFieldNombreArticleVendu = new JTextField();
		textFieldNombreArticleVendu.setColumns(10);
		textFieldNombreArticleVendu.setBounds(102, 248, 220, 37);
		frame.getContentPane().add(textFieldNombreArticleVendu);
		
		textFieldCodeBarre = new JTextField();
		textFieldCodeBarre.setColumns(10);
		textFieldCodeBarre.setBounds(102, 295, 220, 37);
		frame.getContentPane().add(textFieldCodeBarre);
		


		JComboBox comboBoxTVA = new JComboBox();		
		articles.fill_TVA_JCombobox(comboBoxTVA);
		

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				DefaultTableModel model = (DefaultTableModel)table.getModel();
				
				int rIndex = table.getSelectedRow();
				
				textFieldNomArticle.setText(model.getValueAt(rIndex, 0).toString());
				textFieldPrix.setText(model.getValueAt(rIndex, 1).toString());
				comboBoxTVA.setSelectedItem(model.getValueAt(rIndex, 3));
				textFieldReference.setText(model.getValueAt(rIndex, 2).toString());
				textFieldNbrArticleStock.setText(model.getValueAt(rIndex, 4).toString());
				textFieldNombreArticleVendu.setText(model.getValueAt(rIndex, 5).toString());
				textFieldCodeBarre.setText(model.getValueAt(rIndex, 6).toString());

			}
		});
		comboBoxTVA.setBounds(102, 176, 220, 21);
		frame.getContentPane().add(comboBoxTVA);
		
		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.addActionListener(new ActionListener() {
			private Component rootPane;

			public void actionPerformed(ActionEvent e) {
				
			try {
				String NomArticles = textFieldNomArticle.getText();				
				int Prix = Integer.valueOf(textFieldPrix.getText());
				String TVA = comboBoxTVA.getSelectedItem().toString();
				int NbrArticlesStock = Integer.valueOf(textFieldNbrArticleStock.getText().toString());
				int NbrArticleVendu = Integer.valueOf(textFieldNombreArticleVendu.getText().toString());
				int CodeBarre = Integer.valueOf(textFieldCodeBarre.getText().toString());
				
				
				if(articles.addArticle(NomArticles, Prix, TVA, NbrArticlesStock, NbrArticleVendu, CodeBarre)) {
					JOptionPane.showMessageDialog(rootPane, "Article ajouté avec succès", "Ajouter article", JOptionPane.INFORMATION_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(rootPane, "Echec de l'ajout de l'article", "Echec ajout de l'article", JOptionPane.ERROR_MESSAGE);
				}
			}catch(NumberFormatException ex){
				JOptionPane.showMessageDialog(rootPane, ex.getMessage()+"-Renseigner les champs", "Erreur article", JOptionPane.ERROR_MESSAGE);	
			}
			}
		});
		btnAjouter.setBounds(10, 384, 112, 21);
		frame.getContentPane().add(btnAjouter);
		
		JTextPane txtpnBoutons = new JTextPane();
		txtpnBoutons.setText("Boutons");
		txtpnBoutons.setForeground(Color.WHITE);
		txtpnBoutons.setFont(new Font("Tahoma", Font.BOLD, 18));
		txtpnBoutons.setBackground(Color.DARK_GRAY);
		txtpnBoutons.setBounds(10, 342, 218, 32);
		frame.getContentPane().add(txtpnBoutons);
		
		JTextPane txtpnArticles = new JTextPane();
		txtpnArticles.setText("ARTICLES");
		txtpnArticles.setForeground(Color.WHITE);
		txtpnArticles.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		txtpnArticles.setBackground(Color.DARK_GRAY);
		txtpnArticles.setBounds(10, 10, 312, 31);
		frame.getContentPane().add(txtpnArticles);
		
		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//on supprime le client sélectionné
				int reference = Integer.valueOf(textFieldReference.getText());
				
				if(articles.removeArticles(reference))
				{
					JOptionPane.showMessageDialog(rootPane, "Article supprimé avec succès", "Supprimer Article", JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					JOptionPane.showMessageDialog(rootPane, "Echec de la suppression de la Article", "Erreur Supprimer Article", JOptionPane.ERROR_MESSAGE);
				}
			}
				
			
		});
		btnSupprimer.setBounds(132, 384, 112, 54);
		frame.getContentPane().add(btnSupprimer);
		
		JButton btnModifier = new JButton("Modifier");
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nom_article = textFieldNomArticle.getText();
				int prix = Integer.valueOf(textFieldPrix.getText());
				int reference = Integer.valueOf(textFieldReference.getText());
				String tva =String.valueOf(comboBoxTVA.getSelectedItem().toString());				
				int nbr_article_stock = Integer.valueOf(textFieldNbrArticleStock.getText());
				int nbr_article_vendu = Integer.valueOf(textFieldNombreArticleVendu.getText());
				int code_barre = Integer.valueOf(textFieldCodeBarre.getText());

				
				
				if(nom_article.trim().equals(""))
				{
					JOptionPane.showMessageDialog(rootPane, "Champ requis -> Nom, Prenomn, CIN, Telephone", "Champ Vide", JOptionPane.WARNING_MESSAGE);
				}
				else {
					if(articles.editArticles(prix, nom_article, reference, tva, nbr_article_stock, nbr_article_vendu, code_barre))
				{
					JOptionPane.showMessageDialog(rootPane, "Client modifié avec succès", "Modifier Client", JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					JOptionPane.showMessageDialog(rootPane, "Echec de la mofication du client", "Erreur modifier Client", JOptionPane.ERROR_MESSAGE);
				}
					
				}
			}
		});
		btnModifier.setBounds(10, 417, 112, 21);
		frame.getContentPane().add(btnModifier);
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//clear le tableau
				table.setModel(new DefaultTableModel(null, new Object[] {"Nom Article", "Prix", "Reference", "TVA", "Nbr Article Stock", "Nbr Article Vendu", "Code Barre"}));
				//puis on le rerempli
				articles.fillArticleTable(table);
			}
			
		});
		btnRefresh.setBounds(332, 417, 445, 21);
		frame.getContentPane().add(btnRefresh);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldNomArticle.setText("");
				textFieldPrix.setText("");
				comboBoxTVA.setSelectedItem(false);
				textFieldReference.setText("");
				textFieldNbrArticleStock.setText("");
				textFieldNombreArticleVendu.setText("");
				textFieldCodeBarre.setText("");

			}
		});
		btnClear.setBounds(254, 384, 68, 54);
		frame.getContentPane().add(btnClear);
	}
}
