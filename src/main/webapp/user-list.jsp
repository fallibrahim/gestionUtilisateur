<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        <html>

        <head>
            <title>User Management Application</title>
            <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        </head>

        <body>

            <header>
                <nav class="navbar navbar-expand-md navbar-dark" style="background-color: tomato">
                    <div>
                        <a href="https://www.javaguides.net" class="navbar-brand">Systeme de gestion des utilisateurs </a>
                    </div>

                    <ul class="navbar-nav">
                        <li><a href="<%=request.getContextPath()%>/list" class="nav-link">Utilisateurs</a></li>
                    </ul>
                </nav>
            </header>
            <br>

            <div class="row">
                <!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

                <div class="container">
                    <h3 class="text-center">Liste des utilisateurs</h3>
                    <hr>
                    <div class="container text-left">

                        <a href="<%=request.getContextPath()%>/nouveau" class="btn btn-success">Ajouter un nouveau utilisateur</a>
                    </div>
                    <br>
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Email</th>
                                  <th>Mot de Passe</th>
                                <th>Nom</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <!--   for (Todo todo: todos) {  -->
                            <c:forEach var="utilisateur" items="${listUtilisateur}">

                                <tr>
                                    <td>
                                        <c:out value="${utilisateur.id}" />
                                    </td>
                                    <td>
                                        <c:out value="${utilisateur.email}" />
                                    </td>
                                    <td>
                                        <c:out value="${utilisateur.motDePasse}" />
                                    </td>
                                    <td>
                                        <c:out value="${utilisateur.nom}" />
                                    </td>
                                   
                                    <td><a href="edit?id=<c:out value='${utilisateur.id}' />">Edit</a> &nbsp;&nbsp;&nbsp;&nbsp; <a href="delete?id=<c:out value='${utilisateur.id}' />">Delete</a></td>
                                </tr>
                            </c:forEach>
                            <!-- } -->
                        </tbody>

                    </table>
                </div>
            </div>
        </body>

        </html>
