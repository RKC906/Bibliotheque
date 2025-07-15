<%@ page import="java.util.List" %>
<%@ page import="java.util.Set" %>
<%@ page import="biblio.entities.Pret" %>
<%@ page import="java.text.SimpleDateFormat" %>

<%
    List<Pret> prets = (List<Pret>) request.getAttribute("prets");
    Set<Integer> pretsRendus = (Set<Integer>) request.getAttribute("pretsRendus");
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Liste des prêts</title>
</head>
<body>
    <h1>Liste des prêts</h1>
    
    <table border="1">
        <thead>
            <tr>
                <th>ID Adhérent</th>
                <th>Nom</th>
                <th>Prénom</th>
                <th>Livres</th>
                <th>Date début</th>
                <th>Date fin</th>
                <th>Admin responsable</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <% for (Pret pret : prets) { 
                   boolean estRendu = pretsRendus != null && pretsRendus.contains(pret.getIdPret());
            %>
            <tr>
                <td><%= pret.getAdherant().getIdAdherant() %></td>
                <td><%= pret.getAdherant().getNom() %></td>
                <td><%= pret.getAdherant().getPrenom() %></td>
                <td><%= pret.getExemplaireLivre().getLivre() %></td>
                <td><%= dateFormat.format(pret.getDateDebut()) %></td>
                <td><%= dateFormat.format(pret.getDateFin()) %></td>
                <td>
                    <% if (pret.getAdmin() != null) { %>
                        <%= pret.getAdmin().getEmail() %>
                    <% } else { %>
                        Non attribué
                    <% } %>
                </td>
                <td>
                    <% if (estRendu) { %>
                        Rendu
                    <% } else { %>
                        <form action="${pageContext.request.contextPath}/admin/rendre" method="get" style="margin:0;">
                            <input type="hidden" name="idPret" value="<%= pret.getIdPret() %>" />
                            <input type="submit" value="Rendre" />
                        </form>
                    <% } %>
                </td>
            </tr>
            <% } %>
        </tbody>
    </table>
     <a href="${pageContext.request.contextPath}/admin/accueil">
</body>
</html>
