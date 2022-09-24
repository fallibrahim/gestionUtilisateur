package com.idwc.utilisateur.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.idwc.utilisateur.dao.UtilisateurDAO;
import com.idwc.utilisateur.model.Utilisateur;

@WebServlet(name="ServletUtilisateur", urlPatterns="/")
public class ServletUtilisateur extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
     private UtilisateurDAO utilisateurDAO; 
     
         
    public ServletUtilisateur() {
      utilisateurDAO = new UtilisateurDAO();
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);      
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String action = request.getServletPath();
	    
	    switch(action) {
	    case "/nouveau" : 
	    	afficherNouveauFormulaire(request, response);
	    break;
	    case "/insert" : 
	    try
	    {
	      insertUtilisateur(request, response);	
	    }
	    catch(SQLException e)
	    {
	      e.printStackTrace();
	    }
	    break;
	    case "/delete" : 
	    	try {
				deleteUtilisateur(request, response);
			} catch (SQLException e) {
			
				e.printStackTrace();
			} 
	    break;
	    case "/edit" : 
	    	afficherModifierForm(request, response);
	    break;
	    case "/update" : 
	    	try {
				updateUtilisateur(request, response);
			} catch (SQLException e) {
	
				e.printStackTrace();
			}
	    break;
	    default :
	    	try {
				listUtilisateur(request, response);
			} catch (SQLException e) {
				
				e.printStackTrace();
			} 
	    	break;
	    }
	    
	}
	 private void listUtilisateur(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
	    	UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
	    	ArrayList<Utilisateur> listUtilisateur = utilisateurDAO.selectionnerToutLesUtilisateurs();
	 	    
	    	request.setAttribute("listUtilisateur", listUtilisateur);
	    	
	    	RequestDispatcher rd = request.getRequestDispatcher("user-list.jsp");
	    	
	    	rd.forward(request, response);
	    }
	  private void updateUtilisateur(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
	    	int id  = Integer.parseInt(request.getParameter("id"));
		    String email = request.getParameter("email");
	    	String motDePasse = request.getParameter("motDePasse");
	    	String nom = request.getParameter("nom");
	    	
	    	Utilisateur nouveauUtilisateur = new Utilisateur(id, email, motDePasse, nom);
	    	UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
			utilisateurDAO.updateUtilisateur(nouveauUtilisateur);
	    	response.sendRedirect("List");
	    }
    private void deleteUtilisateur(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
    	int id = Integer.parseInt(request.getParameter("id"));
    	UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
		utilisateurDAO.deleteUtilisateur(id);
    	response.sendRedirect("List");
    }
    private void afficherModifierForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    	
    	int id = Integer.parseInt(request.getParameter("id"));
    	UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
    	Utilisateur nouveauUtilisateur = utilisateurDAO.selectionnerUnUtilisateur(id); 
    	
    	RequestDispatcher rd = request.getRequestDispatcher("formulaireUtilisateur.jsp");
    	request.setAttribute("nouveauUtilisateur", nouveauUtilisateur);
    	rd.forward(request, response);
    }
    private void afficherNouveauFormulaire(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    	RequestDispatcher rd = request.getRequestDispatcher("formulaireUtilisateur.jsp");
    	rd.forward(request, response);
    }
    
    private void insertUtilisateur(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
    	String email = request.getParameter("email");
    	String motDePasse = request.getParameter("motDePasse");
    	String nom = request.getParameter("nom");
    	
    	Utilisateur nouveauUtilisateur = new Utilisateur(email, motDePasse, nom);
    	UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
		utilisateurDAO.insertUtilisateur(nouveauUtilisateur);
    	response.sendRedirect("List");
    }
    
} 
