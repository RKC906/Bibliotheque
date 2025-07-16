<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="biblio.entities.Livre" %>
<%@ page import="biblio.entities.Auteur" %>
<%@ page import="biblio.entities.CategorieLivre" %>
<%
    List<Object[]> livres = (List<Object[]>) request.getAttribute("livres");
    List<Auteur> auteurs = (List<Auteur>) request.getAttribute("auteurs");
    List<CategorieLivre> categories = (List<CategorieLivre>) request.getAttribute("categories");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Liste des Livres</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        table { width: 100%; border-collapse: collapse; margin-top: 20px; }
        th, td { padding: 8px; text-align: left; border-bottom: 1px solid #ddd; }
        th { background-color: #f2f2f2; }
        a { text-decoration: none; color: #0066cc; }
        button { 
            background: none;
            border: none;
            color: #0066cc;
            cursor: pointer;
            padding: 0;
            font: inherit;
            text-decoration: underline;
        }
        button:disabled {
            color: #999;
            cursor: not-allowed;
            text-decoration: none;
        }
        .filter-form { 
            background: #f5f5f5; 
            padding: 15px; 
            border-radius: 5px; 
            margin-bottom: 20px;
        }
        .filter-form select, 
        .filter-form input {
            padding: 5px;
            margin-right: 10px;
        }
        .filter-form button[type="submit"] {
            background: #4CAF50;
            color: white;
            padding: 5px 15px;
            border-radius: 3px;
            text-decoration: none;
        }
        .filter-row { margin-bottom: 10px; }
    </style>
</head>
<body>
    <h1>Liste des Livres</h1>
    
    <div class="filter-form">
        <h2>Filtrer les livres</h2>
        <form method="get" action="${pageContext.request.contextPath}/adherant/livres">
            <div class="filter-row">
                Auteur :
                <select name="auteur">
                    <option value="">-- Tous --</option>
                    <% for (Auteur auteur : auteurs) { %>
                        <option value="<%= auteur.getId_Auteur() %>">
                            <%= auteur.getNom() %> <%= auteur.getPrenom() %>
                        </option>
                    <% } %>
                </select>
                
                Langue :
                <input type="text" name="langue" placeholder="Langue">
            </div>
            
            <div class="filter-row">
                Date de publication :
                <input type="date" name="dateDebut"> à 
                <input type="date" name="dateFin">
            </div>
            
            <div class="filter-row">
                Nombre de pages :
                <input type="number" name="nbPagesMin" placeholder="min" style="width:60px;"> à
                <input type="number" name="nbPagesMax" placeholder="max" style="width:60px;">
            </div>
            
            <div class="filter-row">
                Catégorie :
                <select name="categorie">
                    <option value="">-- Toutes --</option>
                    <% for (CategorieLivre cat : categories) { %>
                        <option value="<%= cat.getId_CategorieLivre() %>">
                            <%= cat.getNom() %>
                        </option>
                    <% } %>
                </select>
                
                Nombre d'exemplaires :
                <input type="number" name="nbExMin" placeholder="min" style="width:60px;"> à
                <input type="number" name="nbExMax" placeholder="max" style="width:60px;">
            </div>
            
            <button type="submit">Filtrer</button>
        </form>
    </div>
    
    <table>
        <thead>
            <tr>
                <th>Titre</th>
                <th>Auteur</th>
                <th>Actions</th>
                <th>Réserver</th>
            </tr>
        </thead>
        <tbody>
            <%
                if (livres != null) {
                    for (Object[] livreDetails : livres) {
                        Livre livre = (Livre) livreDetails[0];
                        Auteur auteur = livre.getAuteur();
                        long nbExemplaires = (Long) livreDetails[2];
            %>
                <tr>
                    <td><%= livre.getTitre() %></td>
                    <td>
                        <% if (auteur != null) { %>
                            <%= auteur.getNom() %> <%= auteur.getPrenom() %>
                        <% } else { %>
                            Auteur inconnu
                        <% } %>
                    </td>
                    <td>
                        <a href="${pageContext.request.contextPath}/adherant/livres/details/<%= livre.getId_Livre() %>">
                            Voir détails
                        </a>
                    </td>
                    <td>
                        <form action="${pageContext.request.contextPath}/adherant/reservation/nouveau" method="get" style="display: inline;">
                            <input type="hidden" name="livreId" value="<%= livre.getId_Livre() %>">
                            <button type="submit" <%= nbExemplaires == 0 ? "disabled" : "" %>>Réserver</button>
                        </form>
                    </td>
                </tr>
            <%
                    }
                }
            %>
        </tbody>
    </table>
</body>
</html>