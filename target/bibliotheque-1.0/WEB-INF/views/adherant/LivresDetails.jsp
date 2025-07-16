<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="biblio.entities.Livre" %>
<%
    Livre livre = (Livre) request.getAttribute("livre");
    List<String> categories = (List<String>) request.getAttribute("categories");
    Long nbExemplaires = (Long) request.getAttribute("nbExemplaires");
    
    // Formatage de la date
    String dateFormatee = "";
    if (livre.getDate_publication() != null) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        dateFormatee = sdf.format(livre.getDate_publication());
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Détails du Livre</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        .detail { margin-bottom: 10px; }
        .label { font-weight: bold; display: inline-block; width: 150px; }
        .error { color: red; }
    </style>
</head>
<body>
    <h1>Détails du Livre</h1>
    
    <% if (livre != null) { %>
        <div class="detail"><span class="label">Titre:</span> <%= livre.getTitre() %></div>
        <div class="detail"><span class="label">Auteur:</span> 
            <% if (livre.getAuteur() != null) { %>
                <%= livre.getAuteur().getNom() %> <%= livre.getAuteur().getPrenom() %>
            <% } else { %>
                Inconnu
            <% } %>
        </div>
        <div class="detail"><span class="label">Date de publication:</span> <%= dateFormatee %></div>
        <div class="detail"><span class="label">Nombre de pages:</span> <%= livre.getNb_pages() %></div>
        <div class="detail"><span class="label">Langue:</span> <%= livre.getLangue() %></div>
        <div class="detail"><span class="label">Catégories:</span> 
            <%= String.join(", ", categories) %>
        </div>
        <div class="detail"><span class="label">Exemplaires disponibles:</span> <%= nbExemplaires %></div>
    <% } else { %>
        <p class="error">Livre non trouvé</p>
    <% } %>
    
    <a href="${pageContext.request.contextPath}/adherant/livres">Retour à la liste</a>
</body>
</html>