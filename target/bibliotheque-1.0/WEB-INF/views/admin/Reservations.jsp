<%@ page import="java.util.List" %>
<%@ page import="biblio.entities.Reservation" %>
<%@ page import="biblio.entities.ExemplaireLivre" %>
<%@ page import="biblio.entities.Livre" %>
<%@ page import="biblio.entities.Adherant" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Réservations des livres</title>
</head>
<body>
    <h2>Liste des réservations de livres</h2>
    <a href="${pageContext.request.contextPath}/admin/home">Retour admin</a>
    <% if (request.getAttribute("error") != null) { %>
        <div style="color:red;"><%= request.getAttribute("error") %></div>
    <% } %>
    <% if (request.getAttribute("success") != null) { %>
        <div style="color:green;"><%= request.getAttribute("success") %></div>
    <% } %>
    <table border="1">
        <thead>
            <tr>
                <th>Livre</th>
                <th>Exemplaire</th>
                <th>Adhérant</th>
                <th>Date de réservation</th>
                <th>Status</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <% 
                List<Reservation> reservations = (List<Reservation>) request.getAttribute("reservations");
                if (reservations != null) {
                    for (Reservation reservation : reservations) {
                        ExemplaireLivre ex = reservation.getExemplaireLivre();
                        Livre livre = ex != null ? ex.getLivre() : null;
                        Adherant adherant = (Adherant) request.getAttribute("adherant_" + reservation.getIdReservation());
            %>
            <tr>
                <td><%= livre != null ? livre.getTitre() : "" %></td>
                <td><%= ex != null ? ex.getId_ExemplaireLivre() : "" %></td>
                <td><%= adherant != null ? adherant.getNom() + " " + adherant.getPrenom() : "" %></td>
                <td><%= reservation.getDateReservation() %></td>
                <td><%= reservation.getStatusEntity() != null ? reservation.getStatusEntity().getNom() : "" %></td>
                <td>
                    <% if (reservation.getStatusEntity() != null && "En Cours".equals(reservation.getStatusEntity().getNom())) { %>
                        <form action="${pageContext.request.contextPath}/admin/reservations/accepter" method="post" style="display:inline;">
                            <input type="hidden" name="reservationId" value="<%= reservation.getIdReservation() %>" />
                            <button type="submit">Accepter</button>
                        </form>
                        <form action="${pageContext.request.contextPath}/admin/reservations/refuser" method="post" style="display:inline;">
                            <input type="hidden" name="reservationId" value="<%= reservation.getIdReservation() %>" />
                            <button type="submit">Refuser</button>
                        </form>
                    <% } else { %>
                        <em>Action terminée</em>
                    <% } %>
                </td>
            </tr>
            <%      }
                }
            %>
        </tbody>
    </table>
</body>
</html>
