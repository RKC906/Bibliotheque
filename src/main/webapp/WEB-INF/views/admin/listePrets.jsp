<%@ page import="java.util.List" %>
<%@ page import="biblio.entities.Pret" %>
<%@ page import="java.text.SimpleDateFormat" %>

<%
List<Pret> prets = (List<Pret>) request.getAttribute("prets");
SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Liste des prêts actifs</title>
</head>
<body>
    <h1>Liste des prêts actifs</h1>
    
    <table>
        <thead>
            <tr>
                <th>ID Adhérent</th>
                <th>Nom</th>
                <th>Prénom</th>
                <th>Date début</th>
                <th>Date fin</th>
                <th>Admin responsable</th>
            </tr>
        </thead>
        <tbody>
            <% for (Pret pret : prets) { %>
            <tr>
                <td><%= pret.getAdherant().getIdAdherant() %></td>
                <td><%= pret.getAdherant().getNom() %></td>
                <td><%= pret.getAdherant().getPrenom() %></td>
                <td><%= dateFormat.format(pret.getDateDebut()) %></td>
                <td><%= dateFormat.format(pret.getDateFin()) %></td>
                <td>
                    <% if (pret.getAdmin() != null) { %>
                        <%= pret.getAdmin().getEmail() %>
                    <% } else { %>
                        Non attribué
                    <% } %>
                </td>
            </tr>
            <% } %>
        </tbody>
    </table>
</body>
</html>