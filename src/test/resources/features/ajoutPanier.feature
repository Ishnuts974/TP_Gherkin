@ajoutPanier
Feature: Ajout produit au panier

  Background: Authentification
    Given l'utilisateur est sur la page de connexion
    When il saisit le login "standard_user" et le mot de passe "secret_sauce"
    Then l'utilisateur est sur la page d'acceuil "urlHome"

  Scenario: Achat d'un produit et vérifier son ajout au panier
    Given l'utilisateur est sur la page d'acceuil "urlHome"
    When l'utilisateur choisi d'ajouter le "backpack" à son panier
    Then il clique sur le panier en haut a droite
    And il est redirigé sur la page "urlPanier"
    And il vérifie que le "backpack" est présent dans le panier