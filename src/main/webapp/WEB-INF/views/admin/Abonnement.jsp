<%@ page import="java.util.List" %>
<%@ page import="biblio.entities.Abonnement" %>
<%@ page import="biblio.entities.Adherant" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <h2>Liste des abonnés</h2>
    <a href="${pageContext.request.contextPath}/admin/abonnement/nouveau">Ajouter un abonnement</a>
    <table border="1">
        <thead>
            <tr>
                <th>Nom</th>
                <th>Prénom</th>
                <th>Date inscription</th>
                <th>Date fin inscription</th>
            </tr>
        </thead>
        <tbody>
            <% 
                List<Abonnement> abonnements = (List<Abonnement>) request.getAttribute("abonnements");
                if (abonnements != null) {
                    for (Abonnement abonnement : abonnements) {
                        Adherant adherant = abonnement.getAdherant();
            %>
                <tr>
                    <td><%= adherant != null ? adherant.getNom() : "" %></td>
                    <td><%= adherant != null ? adherant.getPrenom() : "" %></td>
                    <td><%= abonnement.getDateInscription() %></td>
                    <td><%= abonnement.getDateFinInscription() %></td>
                </tr>
            <%      }
                }
            %>
        </tbody>
    </table>
</body>
</html>