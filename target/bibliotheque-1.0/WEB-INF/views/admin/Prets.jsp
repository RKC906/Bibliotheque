<%@ page import="java.util.List" %>
<%@ page import="biblio.entities.Pret" %>
<%@ page import="biblio.entities.TypePret" %>
<%@ page import="biblio.entities.Admin" %>
<%@ page import="biblio.entities.Adherant" %>
<%@ page import="biblio.entities.ExemplaireLivre" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Liste des Prêts</title>
</head>
<body>
    <h2>Liste des Prêts</h2>
    <a href="${pageContext.request.contextPath}/admin/home">Retour admin</a>
    <table border="1">
        <thead>
            <tr>
                <th>Date début</th>
                <th>Date fin</th>
                <th>Type de prêt</th>
                <th>Admin</th>
                <th>Adhérant</th>
                <th>Exemplaire</th>
            </tr>
        </thead>
        <tbody>
            <% 
                List<Pret> prets = (List<Pret>) request.getAttribute("prets");
                if (prets != null) {
                    for (Pret pret : prets) {
            %>
            <tr>
                <td><%= pret.getDateDebut() %></td>
                <td><%= pret.getDateFin() %></td>
                <td><%= pret.getTypePret() != null ? pret.getTypePret().getNom() : "" %></td>
                <td><%= pret.getAdmin() != null ? pret.getAdmin().getEmail() : "" %></td>
                <td><%= pret.getAdherant() != null ? pret.getAdherant().getNom() + " " + pret.getAdherant().getPrenom() : "" %></td>
                <td><%= pret.getExemplaireLivre() != null ? pret.getExemplaireLivre().getIdExemplaireLivre() : "" %></td>
            </tr>
            <%      }
                }
            %>
        </tbody>
    </table>
</body>
</html>
