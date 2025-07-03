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
    <table border="1">
        <thead>
            <tr>
                <th>Livre</th>
                <th>Exemplaire</th>
                <th>Adhérant</th>
                <th>Date de réservation</th>
                <th>Status</th>
            </tr>
        </thead>
        <tbody>
            <% 
                List<Reservation> reservations = (List<Reservation>) request.getAttribute("reservations");
                if (reservations != null) {
                    for (Reservation reservation : reservations) {
                        ExemplaireLivre ex = reservation.getExemplaireLivre();
                        Livre livre = ex != null ? ex.getLivre() : null;
                        Adherant adherant = (Adherant) request.getAttribute("adherant_" + reservation.getId_Reservation());
            %>
            <tr>
                <td><%= livre != null ? livre.getTitre() : "" %></td>
                <td><%= ex != null ? ex.getId_ExemplaireLivre() : "" %></td>
                <td><%= adherant != null ? adherant.getNom() + " " + adherant.getPrenom() : "" %></td>
                <td><%= reservation.getDate_reservation() %></td>
                <td><%= reservation.getStatusEntity() != null ? reservation.getStatusEntity().getNom() : "" %></td>
            </tr>
            <%      }
                }
            %>
        </tbody>
    </table>
</body>
</html>
