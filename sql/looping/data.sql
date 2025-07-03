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

INSERT INTO Auteur(nom, prenom, date_naissance, nationalite, date_deces, biographie, photo) VALUES
('Hugo', 'Victor', '1802-02-26', 'Française', '1885-05-22', 'Grand écrivain romantique français', 'hugo.jpg'),
('Rowling', 'J.K.', '1965-07-31', 'Britannique', NULL, 'Auteur de la saga Harry Potter', 'rowling.jpg'),
('Orwell', 'George', '1903-06-25', 'Britannique', '1950-01-21', 'Auteur de 1984 et La Ferme des animaux', 'orwell.jpg'),
('Christie', 'Agatha', '1890-09-15', 'Britannique', '1976-01-12', 'Reine du roman policier', 'christie.jpg'),
('Saint-Exupéry', 'Antoine', '1900-06-29', 'Française', '1944-07-31', 'Auteur du Petit Prince', 'saintexupery.jpg');

INSERT INTO CategorieLivre(nom) VALUES
('Roman'),
('Science-Fiction'),
('Policier'),
('Fantasy'),
('Classique'),
('Jeunesse'),
('Drame');

INSERT INTO Livre(titre, date_publication, nb_pages, langue, tags, auteur_id_Auteur) VALUES
('Les Misérables', '1862-01-01', 1463, 'Français', 'classique,drame,historique', 1),
('Harry Potter à l\'école des sorciers', '1997-06-26', 320, 'Anglais', 'fantasy,jeunesse,magie', 2),
('1984', '1949-06-08', 328, 'Anglais', 'dystopie,science-fiction,politique', 3),
('Le Meurtre de Roger Ackroyd', '1926-06-01', 256, 'Anglais', 'policier,enquête,mystère', 4),
('Le Petit Prince', '1943-04-06', 96, 'Français', 'philosophie,jeunesse,poétique', 5);

INSERT INTO ExemplaireLivre(livre_id_Livre 	) VALUES
(1), (1), (1), -- 3 exemplaires des Misérables
(2), (2),      -- 2 exemplaires de Harry Potter
(3), (3), (3), (3), -- 4 exemplaires de 1984
(4),            -- 1 exemplaire de Roger Ackroyd
(5), (5), (5);  -- 3 exemplaires du Petit Prince

INSERT INTO CategorieLivreAssociation(livre_id_Livre, categorieLivre_id_CategorieLivre) VALUES
(1, 1), (1, 5), (1, 7),   -- Les Misérables: Roman, Classique, Drame
(2, 4), (2, 6),            -- Harry Potter: Fantasy, Jeunesse
(3, 2), (3, 1), (3, 7),    -- 1984: Science-Fiction, Roman, Drame
(4, 3), (4, 1),            -- Roger Ackroyd: Policier, Roman
(5, 1), (5, 5), (5, 6);    -- Le Petit Prince: Roman, Classique, Jeunesse

INSERT INTO Admin (email, motDePasse) VALUES
('admin1@biblio.fr', 'admin123'),
('admin2@biblio.fr', 'motdepasse456');