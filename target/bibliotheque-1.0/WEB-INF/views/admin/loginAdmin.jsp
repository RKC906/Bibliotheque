 <%@ page import="java.lang.String" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <h2>Connexion Admin</h2>
   
    <form action="${pageContext.request.contextPath}/admin/login" method="post">
        <label for="email">Email :</label>
        <input type="email" id="email" name="email" required value="admin1@biblio.fr"><br><br>
        <label for="motDePasse">Mot de passe :</label>
        <input type="password" id="motDePasse" name="motDePasse" required value="admin123"><br><br>
        <button type="submit">Se connecter</button>
    </form>
    <% String error = (String) request.getAttribute("error");
       if (error != null && !error.isEmpty()) { %>
        <p style="color:red;"><%= error %></p>
    <% } %>
</body>
</html>