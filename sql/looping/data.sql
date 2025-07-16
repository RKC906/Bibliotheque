INSERT INTO Profile(nom) VALUES
('Etudiant'),
('Enseignant'),
('Professionel');
-- Données pour la table Authentification
INSERT INTO Authentification (email, motDePasse) VALUES
('amine@example.com', 'password1'),
('sarah@example.com', 'password2'),
('youssef@example.com', 'password3'),
('nadia@example.com', 'password4'),
('karim@example.com', 'password5'),
('salima@example.com', 'password6'),
('rachid@example.com', 'password7'),
('amina@example.com', 'password8');

-- Données pour la table Adherant
INSERT INTO Auteur(nom, prenom, date_naissance, nationalite, date_deces, biographie, photo) VALUES
('Hugo', 'Victor', '1802-02-26', 'Française', '1885-05-22', 'Grand écrivain romantique français', 'hugo.jpg'),
('Camus', 'Albert', '1913-11-07', 'Française', '1960-01-04', 'Philosophe et écrivain existentialiste', 'camus.jpg'),
('Rowling', 'J.K.', '1965-07-31', 'Britannique', NULL, 'Auteur de la saga Harry Potter', 'rowling.jpg');

INSERT INTO CategorieLivre(nom) VALUES
('Philosophie'),
('Littérature classique'),
('Jeunesse / Fantastique');

INSERT INTO Livre(titre, date_publication, nb_pages, langue, tags, auteur_id_Auteur) VALUES
('Les Misérables', '1862-01-01', 1463, 'Français', '9782070409189', 1),
('L''Étranger', '1942-01-01', 185, 'Français', '9782070360022', 2),
('Harry Potter à l''école des sorciers', '1997-06-26', 320, 'Français', '9782070643026', 3);

INSERT INTO ExemplaireLivre(livre_id_Livre) VALUES
(1), (1), (1), -- 3 exemplaires des Misérables (MIS001, MIS002, MIS003)
(2), (2),      -- 2 exemplaires de L'Étranger (ETR001, ETR002)
(3);           -- 1 exemplaire de Harry Potter (HAR001)

INSERT INTO CategorieLivreAssociation(livre_id_Livre, categorieLivre_id_CategorieLivre) VALUES
(1, 2), -- Les Misérables: Littérature classique, Classique
(2, 1),         -- L'Étranger: Philosophie
(3, 3); -- Harry Potter: Jeunesse / Fantastique, Fantasy

INSERT INTO Admin (email, motDePasse) VALUES
('admin1@biblio.fr', 'admin123'),
('admin2@biblio.fr', 'motdepasse456');

INSERT INTO Status(nom) VALUES
('En Cours'),
('Accepter'),
('Refuser');

INSERT INTO TypePret(nom) VALUES
('Sur place'),
('A domicile');

INSERT INTO ReglePret(nbrPretLivre,tempsPretLivre,profile_id_Profile) VALUES
(2, 7, 1),  -- 2 livres pour 7 jours pour Etudiant
(3, 9, 2), -- 3 livres pour 9 jours pour Enseignant
(4, 12, 3);  -- 8 livres pour 30 jours pour Professionel

INSERT INTO StatusAdherant(nom) VALUES
('Actif'),
('Inactif');

INSERT INTO Adherant (dateNaissance, nom, prenom, id_Authentification, id_Profile, id_Status_Adherant) VALUES
('1995-03-15', 'Bensaïd Amine','ETU001', 1, 1, 1),
('1996-07-22', 'El Khattabi Sarah','ETU002', 2, 1, 2),
('1994-11-30', 'Moujahid Youssef','ETU003', 3, 1, 1),
('1980-05-10', 'Benali Nadia','ENS001', 4, 2, 1),
('1978-09-18', 'Haddadi Karim','ENS002', 5, 2, 2),
('1982-12-05', 'Touhami Salima','ENS003', 6, 2, 1),
('1975-04-25', 'El Mansouri Rachid','PROF001', 7, 3, 1),
('1976-08-12', 'Zerouali Amina','PROF002', 8, 3, 2);

INSERT INTO Abonnement (dateInscription, dateFinInscription, adherant_idAdherant, admin_id_Admin) VALUES
-- ETU001 - Amine Bensaïd (adhérent_id 1)
('2025-02-01 00:00:00', '2025-07-24 00:00:00', 1, 1),

-- ETU002 - Sarah El Khattabi (adhérent_id 2)
('2025-02-01 00:00:00', '2025-07-01 00:00:00', 2, 1),

-- ETU003 - Youssef Moujahid (adhérent_id 3)
('2025-04-01 00:00:00', '2025-12-01 00:00:00', 3, 1),

-- ENS001 - Nadia Benali (adhérent_id 4)
('2025-07-01 00:00:00', '2026-07-01 00:00:00', 4, 1),

-- ENS002 - Karim Haddadi (adhérent_id 5)
('2025-08-01 00:00:00', '2026-05-01 00:00:00', 5, 1),

-- ENS003 - Salima Touhami (adhérent_id 6)
('2025-07-01 00:00:00', '2026-06-01 00:00:00', 6, 1),

-- PROF001 - Rachid El Mansouri (adhérent_id 7)
('2025-06-01 00:00:00', '2025-12-01 00:00:00', 7, 1),

-- PROF002 - Amina Zerouali (adhérent_id 8)
('2024-10-01 00:00:00', '2025-06-01 00:00:00', 8, 1);

