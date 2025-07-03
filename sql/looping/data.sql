INSERT INTO Profile(nom) VALUES
('Etudiant'),
('Professionnel'),
('Professeur'),
('Anonyme');
-- Données pour la table Authentification
INSERT INTO Authentification (email, motDePasse) VALUES
  ('alice@example.com', 'azerty'),
  ('bob@example.com', 'password123');

-- Données pour la table Adherant
INSERT INTO Adherant (date_naissance, nom, prenom, authentification_id_Authentification, profile_id_Profile) VALUES
  ('1990-01-01', 'Dupont', 'Alice', 1, 1),
  ('1985-05-15', 'Martin', 'Bob', 2, 2);

