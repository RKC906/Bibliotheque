<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="biblio.entities.Livre" %>
<%@ page import="biblio.entities.Auteur" %>
<%@ page import="biblio.entities.CategorieLivre" %>
<%
    List<Object[]> livres = (List<Object[]>) request.getAttribute("livres");
%>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <h1>Bienvenue Adherant</h1>

    <h2>Filtrer les livres</h2>
    <form method="get" action="${pageContext.request.contextPath}/adherant/livres">
        <%-- Préparation des listes pour le HTML --%>
        <% 
            List auteurs = (List) request.getAttribute("auteurs");
            List<Auteur> auteursList = new ArrayList<>();
            if (auteurs != null) {
                for (Object obj : auteurs) {
                    auteursList.add((Auteur) obj);
                }
            }
            List categories = (List) request.getAttribute("categories");
            List<CategorieLivre> categoriesList = new ArrayList<>();
            if (categories != null) {
                for (Object obj : categories) {
                    categoriesList.add((CategorieLivre) obj);
                }
            }
        %>
        Auteur :
        <select name="auteur">
            <option value="">-- Tous --</option>
            <% for (Auteur auteur : auteursList) { %>
                <option value="<%= auteur.getIdAuteur() %>"><%= auteur.getNom() %> <%= auteur.getPrenom() %></option>
            <% } %>
        </select>
        Langue :
        <input type="text" name="langue" placeholder="Langue">
        Date de publication :
        <input type="date" name="dateDebut"> à <input type="date" name="dateFin">
        Nombre de pages :
        <input type="number" name="nbPagesMin" placeholder="min" style="width:60px;"> à <input type="number" name="nbPagesMax" placeholder="max" style="width:60px;">
        Catégorie :
        <select name="categorie">
            <option value="">-- Toutes --</option>
            <% for (CategorieLivre cat : categoriesList) { %>
                <option value="<%= cat.getIdCategorieLivre() %>"><%= cat.getNom() %></option>
            <% } %>
        </select>
        Nombre d'exemplaires :
        <input type="number" name="nbExMin" placeholder="min" style="width:60px;"> à <input type="number" name="nbExMax" placeholder="max" style="width:60px;">
        <button type="submit">Filtrer</button>
    </form>

    <h2>Liste des livres</h2>
    <table border="1">
        <thead>
            <tr>
                <th>Titre</th>
                <th>Auteur</th>
                <th>Date de publication</th>
                <th>Nombre de pages</th>
                <th>Langue</th>
                <th>Catégories</th>
                <th>Nombre d'exemplaires</th>
                <th>Réserver</th>
            </tr>
        </thead>
        <tbody>
        <% 
            if (livres != null) {
                for (Object[] details : livres) {
                    Livre livre = (Livre) details[0];
                    List categoriesLivre = (List) details[1];
                    long nbEx = (Long) details[2];
        %>
            <tr>
                <td><%= livre.getTitre() %></td>
                <td><%= livre.getAuteur() != null ? livre.getAuteur().getNom() + " " + livre.getAuteur().getPrenom() : "" %></td>
                <td><%= livre.getDatePublication() %></td>
                <td><%= livre.getNbPages() %></td>
                <td><%= livre.getLangue() %></td>
                <td>
                    <%
                        for (int i = 0; i < categoriesLivre.size(); i++) {
                            out.print(categoriesLivre.get(i));
                            if (i < categoriesLivre.size() - 1) out.print(", ");
                        }
                    %>
                </td>
                <td>
                    <%= nbEx %>
                </td>
                <td>
                    <form action="${pageContext.request.contextPath}/adherant/reservation/nouveau" method="get" style="margin:0;">
                        <input type="hidden" name="livreId" value="<%= livre.getId_Livre() %>" />
                        <button type="submit" <%= nbEx == 0 ? "disabled" : "" %>>Réserver</button>
                    </form>
                </td>
            </tr>
        <%      }
            }
        %>
        </tbody>
    </table>
</body>
</html>