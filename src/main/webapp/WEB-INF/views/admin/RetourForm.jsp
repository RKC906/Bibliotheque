<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="biblio.entities.Retour" %>
<%@ page import="biblio.repository.admin.RetourRepository" %>

<%
    String idPretStr = request.getParameter("idPret");
    int idPret = 0;
    if (idPretStr != null) {
        try {
            idPret = Integer.parseInt(idPretStr);
        } catch (NumberFormatException e) {
            // Gérer l'erreur si besoin
        }
    }

    // Date du jour au format yyyy-MM-dd pour input type="date"
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String today = sdf.format(new Date());

    // Messages éventuels (passés via request attributes)
    String error = (String) request.getAttribute("error");
    String message = (String) request.getAttribute("message");

    Integer idPret = (Integer) request.getAttribute("idPret");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Formulaire de retour</title>
    <style>
        .success { color: green; }
        .error { color: red; }
    </style>
</head>
<body>
    <h1>Retour du prêt n° <%= idPret %></h1>

    <% if (message != null) { %>
        <p class="success"><%= message %></p>
    <% } %>
    <% if (error != null) { %>
        <p class="error"><%= error %></p>
    <% } %>

    <form action="${pageContext.request.contextPath}/admin/traiterRetour" method="post">
        <input type="hidden" name="idPret" value="<%= idPret %>" />

        <label for="dateRetour">Date de retour :</label>
        <input type="date" id="dateRetour" name="dateRetour" value="<%= today %>" required />
        <br/><br/>

        <input type="submit" value="Enregistrer le retour" />
    </form>

    <br/>
<a href="${pageContext.request.contextPath}/admin/listPrets">Retour a la liste des prets</a>
</body>
</html>