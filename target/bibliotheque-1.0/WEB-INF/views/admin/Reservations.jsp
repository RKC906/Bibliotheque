<%@ page import="java.util.List, biblio.entities.Reservation, biblio.entities.Adherant" %>

<%
// Récupération des données
List<Reservation> reservations = (List<Reservation>) request.getAttribute("reservations");
String success = (String) request.getAttribute("success");
String error = (String) request.getAttribute("error");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Gestion des Réservations</title>
</head>
<body>
    <h1>Liste des Réservations</h1>
    
    <%-- Affichage des messages --%>
    <% if (success != null) { %>
        <p style="color:green;"><%= success %></p>
    <% } %>
    
    <% if (error != null) { %>
        <p style="color:red;"><%= error %></p>
    <% } %>
    
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Livre</th>
            <th>Adhérent</th>
            <th>Date</th>
            <th>Statut</th>
            <th>Actions</th>
        </tr>
        
        <% for (Reservation reservation : reservations) { 
            Adherant adherant = (Adherant) request.getAttribute("adherant_" + reservation.getIdReservation());
            String statusText = "";
            switch(reservation.getStatusEntity().getId_Status()) {
                case 1: statusText = "En attente"; break;
                case 2: statusText = "Acceptée"; break;
                case 3: statusText = "Refusée"; break;
            }
        %>
            <tr>
                <td><%= reservation.getIdReservation() %></td>
                <td>
                    <% if (reservation.getExemplaireLivre() != null) { %>
                        <%= reservation.getExemplaireLivre().getLivre().getTitre() %>
                    <% } %>
                </td>
                <td>
                    <% if (adherant != null) { %>
                        <%= adherant.getNom() %> <%= adherant.getPrenom() %>
                    <% } %>
                </td>
                <td><%= reservation.getDateReservation() %></td>
                <td><%= statusText %></td>
                <td>
                    <% if (reservation.getStatusEntity().getId_Status() == 1) { %>
                        <form action="${pageContext.request.contextPath}/admin/reservations/accepter" method="post">
                            <input type="hidden" name="reservationId" value="<%= reservation.getIdReservation() %>">
                            <input type="submit" value="Accepter">
                        </form>
                        
                        <form action="${pageContext.request.contextPath}/admin/reservations/refuser" method="post">
                            <input type="hidden" name="reservationId" value="<%= reservation.getIdReservation() %>">
                            <input type="submit" value="Refuser">
                        </form>
                    <% } else { %>
                        Action terminée
                    <% } %>
                </td>
            </tr>
        <% } %>
    </table>
<a href="${pageContext.request.contextPath}/admin/accueil">Retour à l'accueil</a>
</body>
</html>