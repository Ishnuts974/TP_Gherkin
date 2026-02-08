Feature: Ajout produit au panier

  Background: Authentification
    Given l'utilisateur est sur la page de connexion
    When il saisit le login "standard_user" et le mot de passe "secret_sauce"
    Then il est redirigé vers la page d'acceuil
    And elle est affiche

  Scenario: Achat d'un produit et vérifier son ajout au panier
    Given l'utilisateur est sur la page d'acceuil "urlHome"
    When l'utilisateur choisi d'ajouter le "t-shirt" à son panier
    Then