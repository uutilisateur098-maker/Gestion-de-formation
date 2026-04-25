-- Create the database
CREATE DATABASE IF NOT EXISTS gestion_formation;
USE gestion_formation;

-- Table for users (Admin, Trainer)
CREATE TABLE IF NOT EXISTS utilisateurs (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role ENUM('ADMIN', 'FORMATEUR') NOT NULL
);

-- Table for trainers
CREATE TABLE IF NOT EXISTS formateurs (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    prenom VARCHAR(100) NOT NULL,
    specialite VARCHAR(100),
    email VARCHAR(100) UNIQUE,
    user_id INT,
    FOREIGN KEY (user_id) REFERENCES utilisateurs(id) ON DELETE CASCADE
);

-- Table for formations (courses)
CREATE TABLE IF NOT EXISTS formations (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titre VARCHAR(200) NOT NULL,
    description TEXT,
    date_debut DATE,
    date_fin DATE,
    formateur_id INT,
    FOREIGN KEY (formateur_id) REFERENCES formateurs(id) ON DELETE SET NULL
);

-- Table for apprenants (learners)
CREATE TABLE IF NOT EXISTS apprenants (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    prenom VARCHAR(100) NOT NULL,
    date_naissance DATE,
    email VARCHAR(100) UNIQUE,
    photo LONGBLOB
);

-- Table for inscriptions
CREATE TABLE IF NOT EXISTS inscriptions (
    id INT AUTO_INCREMENT PRIMARY KEY,
    apprenant_id INT NOT NULL,
    formation_id INT NOT NULL,
    date_inscription DATETIME DEFAULT CURRENT_TIMESTAMP,
    statut ENUM('EN_COURS', 'TERMINE', 'ANNULE') DEFAULT 'EN_COURS',
    FOREIGN KEY (apprenant_id) REFERENCES apprenants(id) ON DELETE CASCADE,
    FOREIGN KEY (formation_id) REFERENCES formations(id) ON DELETE CASCADE
);

-- Initial Admin account: admin / admin123
INSERT INTO utilisateurs (username, password, role) VALUES ('admin', 'admin123', 'ADMIN');
