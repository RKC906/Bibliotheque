<%@ page import="java.util.List" %>
<%@ page import="biblio.entities.Penalite" %>
<%@ page import="biblio.entities.Adherant" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Liste des pénalités</title>
</head>
<body>
    <h2>Liste des pénalités</h2>
    <a href="${pageContext.request.contextPath}/admin/penalite/nouveau">Ajouter une pénalité</a>
    <table border="1">
        <thead>
            <tr>
                <th>Nom</th>
                <th>Prénom</th>
                <th>Date début</th>
                <th>Date fin</th>
            </tr>
        </thead>
        <tbody>
            <% 
                List<Penalite> penalites = (List<Penalite>) request.getAttribute("penalites");
                if (penalites != null) {
                    for (Penalite penalite : penalites) {
                        Adherant adherant = penalite.getAdherant();
            %>
            <tr>
                <td><%= adherant != null ? adherant.getNom() : "" %></td>
                <td><%= adherant != null ? adherant.getPrenom() : "" %></td>
                <td><%= penalite.getDateDebut() %></td>
                <td><%= penalite.getDateFin() %></td>
            </tr>
            <%      }
                }
            %>
        </tbody>
    </table>
</body>
</html>
