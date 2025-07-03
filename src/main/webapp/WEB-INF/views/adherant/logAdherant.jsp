<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Connexion Adhérant</title>
</head>
<body>
    <h2>Connexion Adhérant</h2>
    <form action="${pageContext.request.contextPath}/adherant/login" method="post">        
        <label for="email">Email :</label>
        <input type="email" id="email" name="email" required value="alice@example.com"><br><br>
        <label for="motDePasse">Mot de passe :</label>
        <input type="password" id="motDePasse" name="motDePasse" required value="azerty"><br><br>
        <button type="submit">Se connecter</button>
    </form>
    <% if (request.getAttribute("error") != null) { %>
        <p style="color:red;"><%= request.getAttribute("error") %></p>
    <% } %>
</body>
</html>