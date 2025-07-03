<%@ page import="java.util.List" %>
<%@ page import="biblio.entities.ExemplaireLivre" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Nouvelle Réservation</title>
</head>
<body>
    <h2>Nouvelle réservation</h2>
    <form action="${pageContext.request.contextPath}/adherant/reservation/nouveau" method="post">
        <input type="hidden" name="livreId" value="<%= request.getAttribute("livreId") %>" />
        <label for="exemplaireId">Exemplaire :</label>
        <select id="exemplaireId" name="exemplaireId" required>
            <% 
                List<ExemplaireLivre> exemplaires = (List<ExemplaireLivre>) request.getAttribute("exemplaires");
                if (exemplaires != null) {
                    for (ExemplaireLivre ex : exemplaires) {
            %>
                <option value="<%= ex.getId_ExemplaireLivre() %>">Exemplaire #<%= ex.getId_ExemplaireLivre() %></option>
            <%      }
                }
            %>
        </select><br><br>
        <label for="dateReservation">Date de réservation :</label>
        <input type="date" id="dateReservation" name="dateReservation" required><br><br>
        <button type="submit">Valider</button>
    </form>
    <a href="/adherant/livres">Retour à la liste</a>
</body>
</html>
