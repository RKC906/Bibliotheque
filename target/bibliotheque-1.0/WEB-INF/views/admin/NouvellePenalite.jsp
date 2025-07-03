<%@ page import="java.util.List" %>
<%@ page import="biblio.entities.Adherant" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Nouvelle Pénalité</title>
</head>
<body>
    <h2>Ajouter une pénalité</h2>
    <form action="${pageContext.request.contextPath}/admin/penalite/nouveau" method="post">
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
        <label for="dateDebut">Date début :</label>
        <input type="date" id="dateDebut" name="dateDebut" required><br><br>
        <label for="dateFin">Date fin :</label>
        <input type="date" id="dateFin" name="dateFin" required><br><br>
        <button type="submit">Valider</button>
    </form>
    <a href="/admin/penalite">Retour à la liste</a>
</body>
</html>
