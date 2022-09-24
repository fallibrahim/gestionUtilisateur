package com.idwc.utilisateur.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.idwc.utilisateur.model.Utilisateur;

public class UtilisateurDAO {
	private String jdbcURL = "jdbc:mysql://localhost:3306/javaeecru";
	private String jdbcUtilisateur = "newUsers";
	private String jdbcPWD = "password";
	
	private static final String INSERT_USER_SQL = " INSERT INTO  donnee " + 
	"(email,motDePasse,nom) VALUES " + " (?,?,?); ";
	private static final String SELECT_USER_BY_ID = " SELECT id, email, motDePasse, nom from  donnee WHERE id = ?; ";
	private static final String SELECT_ALL_USER = " SELECT * FROM  donnee; ";
	private static final String DELETE_USER_SQL = " DELETE FROM  donnee WHERE id = ?; ";
	private static final String UPDATE_USER_SQL = " UPDATE  donnee SET email = ?, motDePasse = ?, nom = ?  where id = ?; ";
	
	public UtilisateurDAO() {}
	
	protected Connection getConnection()
	{
		Connection connection  = null;
		
	   try
	   {
		   Class.forName("com.mysql.jdbc.Driver");
		    connection = DriverManager.getConnection(jdbcURL, jdbcUtilisateur, jdbcPWD); 
	   }
	   
	   catch(SQLException e)
	   {
		   e.printStackTrace();
	   }
	   catch(ClassNotFoundException e)
	   {
		   e.printStackTrace();
	   }
		return connection;
	}
	 public void insertUtilisateur(Utilisateur utilisateur) throws SQLException
	 {
		    System.out.println(INSERT_USER_SQL);
		 try(Connection  connection = getConnection();
		 PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER_SQL);){
			preparedStatement.setString(1, utilisateur.getEmail()); 
			preparedStatement.setString(2, utilisateur.getMotDePasse());
			preparedStatement.setString(3, utilisateur.getNom());
			preparedStatement.executeUpdate();
		 }
	 }
	 public boolean updateUtilisateur(Utilisateur utilisateur) throws SQLException
	 {
		 boolean rowUpdate;
		 
		 try(Connection  connection = getConnection();
		 PreparedStatement statement = connection.prepareStatement(UPDATE_USER_SQL);){
			statement.setString(1, utilisateur.getEmail()); 
			statement.setString(2, utilisateur.getMotDePasse());
	        statement.setString(3, utilisateur.getNom());
	        rowUpdate = statement.executeUpdate() > 0; 
		 }
		 return rowUpdate;
	 }
	 public Utilisateur selectionnerUnUtilisateur(int id) 
	 {
		 Utilisateur utilisateur  = null;
		 
		 try(Connection  connection = getConnection();
		 PreparedStatement statement = connection.prepareStatement(SELECT_USER_BY_ID);){
			statement.setInt(1, id); 
			System.out.println(statement);
			ResultSet resultat = statement.executeQuery(); 
			
			while(resultat.next())
			{
				String email = resultat.getString("email");
				String motDePasse = resultat.getString("motDePasse");
				String nom = resultat.getString("nom");
				Utilisateur monUtilisateur = new Utilisateur(id, email, motDePasse, nom);
			}
		 }catch(SQLException e) {
			 e.printStackTrace();
		 }
		return utilisateur;
	   
	 }
	 
	 public List<Utilisateur> selectionnerToutLesUtilisateurs() 
	 {
		 List<Utilisateur> utilisateur = new ArrayList<>();
		 
		 try(Connection  connection = getConnection();
		 PreparedStatement statement = connection.prepareStatement(SELECT_ALL_USER);){
			
			System.out.println(statement);
			ResultSet resultat = statement.executeQuery(); 
			
			while(resultat.next())
			{
				int id = resultat.getInt("id");
				String email = resultat.getString("email");
				String motDePasse = resultat.getString("motDePasse");
				String nom = resultat.getString("nom");
				
			utilisateur.add(new Utilisateur(id, email, motDePasse, nom)); 
			}
		 }catch(SQLException e) {
			 e.printStackTrace();
		 }
		return utilisateur;
	   
	 }
	 public boolean deleteUtilisateur(int id) throws SQLException
	 {
		 boolean rowDeleted;
		 
		 try(Connection  connection = getConnection();
		 PreparedStatement statement = connection.prepareStatement(DELETE_USER_SQL);){
			statement.setInt(1, id); 
	        rowDeleted = statement.executeUpdate() > 0; 
		 }
		 return rowDeleted;
	 }
}
