<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <div id="detailsContainer">
        <p>Chargement en cours...</p>
    </div>
    <a href="${pageContext.request.contextPath}/adherant/livres">Retour à la liste</a>

    <script>
        document.addEventListener("DOMContentLoaded", function() {
            const pathParts = window.location.pathname.split('/');
            const id = pathParts[pathParts.length - 1];
            
            if (!id) {
                document.getElementById('detailsContainer').innerHTML = 
                    '<p class="error">Aucun livre sélectionné</p>';
                return;
            }

            fetch(`${window.location.origin}${window.location.pathname.replace('details', 'api/details')}`)
                .then(response => {
                    if (!response.ok) throw new Error('Erreur ' + response.status);
                    return response.json();
                })
                .then(data => {
                    const livre = data.livre;
                    
                    // Formatage sécurisé de la date
                    let dateFormatee = 'Non disponible';
                    if (livre.date_publication) {
                        const dateObj = new Date(livre.date_publication);
                        if (!isNaN(dateObj.getTime())) {
                            dateFormatee = dateObj.toLocaleDateString('fr-FR');
                        }
                    }
                    
                    // Gestion sécurisée de l'auteur
                    let auteur = 'Inconnu';
                    if (livre.auteur) {
                        auteur = `${livre.auteur.nom} ${livre.auteur.prenom}`;
                    }
                    
                    let html = `
                        <div class="detail"><span class="label">Titre:</span> ${livre.titre || 'Non disponible'}</div>
                        <div class="detail"><span class="label">Auteur:</span> ${auteur}</div>
                        <div class="detail"><span class="label">Date:</span> ${dateFormatee}</div>
                        <div class="detail"><span class="label">Pages:</span> ${livre.nb_pages || 'Non disponible'}</div>
                        <div class="detail"><span class="label">Langue:</span> ${livre.langue || 'Non disponible'}</div>
                        <div class="detail"><span class="label">Catégories:</span> ${data.categories ? data.categories.join(', ') : 'Aucune'}</div>
                        <div class="detail"><span class="label">Exemplaires:</span> ${data.nbExemplaires || '0'}</div>
                    `;
                    document.getElementById('detailsContainer').innerHTML = html;
                })
                .catch(error => {
                    document.getElementById('detailsContainer').innerHTML = 
                        `<p class="error">Erreur: ${error.message}</p>`;
                });
        });
    </script>
</body>
</html>