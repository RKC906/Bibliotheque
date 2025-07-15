<%@ page import="java.util.List" %>
<%@ page import="biblio.entities.Abonnement" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%
List<Abonnement> abonnements = (List<Abonnement>) request.getAttribute("abonnements");
SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
%>
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
        <tr>
            <th>Nom</th>
            <th>Prénom</th>
            <th>Date Début</th>
            <th>Date Fin</th>
        </tr>
        
        <% for (Abonnement abonnement : abonnements) { %>
        <tr>
            <td><%= abonnement.getAdherant().getNom() %></td>
            <td><%= abonnement.getAdherant().getPrenom() %></td>
            <td><%= dateFormat.format(abonnement.getDateInscription()) %></td>
            <td><%= dateFormat.format(abonnement.getDateFinInscription()) %></td>
        </tr>
        <% } %>
    </table>
<a href="${pageContext.request.contextPath}/admin/accueil">Retour à l'accueil</a>
</body>
</html>