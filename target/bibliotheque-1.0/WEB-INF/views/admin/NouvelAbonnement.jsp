<%@ page import="java.util.List" %>
<%@ page import="biblio.entities.Adherant" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Nouvel Abonnement</title>
</head>
<body>
    <h2>Ajouter un abonnement</h2>
    <form action="${pageContext.request.contextPath}/admin/abonnement/nouveau" method="post">
        <label for="adherantId">Adhérant :</label>
        <select id="adherantId" name="adherantId" required>
            <% 
                List<Adherant> adherants = (List<Adherant>) request.getAttribute("adherants");
                if (adherants != null) {
                    for (Adherant adherant : adherants) {
            %>
                <option value="<%= adherant.getId_Adherant() %>"><%= adherant.getNom() %> <%= adherant.getPrenom() %></option>
            <%      }
                }
            %>
        </select><br><br>
        <label for="dateInscription">Date inscription :</label>
        <input type="date" id="dateInscription" name="dateInscription" required><br><br>
        <label for="dateFinInscription">Date fin inscription :</label>
        <input type="date" id="dateFinInscription" name="dateFinInscription" required><br><br>
        <button type="submit">Valider</button>
    </form>
    <a href="/admin/abonnement">Retour à la liste</a>
</body>
</html>
