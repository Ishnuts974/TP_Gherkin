Feature: AjoutPanierTest

	Background:
		#@POEI2-891
		Given l'utilisateur est sur la page de connexion
		    When il saisit le login "standard_user" et le mot de passe "secret_sauce"
		    Then l'utilisateur est sur la page d'acceuil "urlHome"

	@POEI2-890
	Scenario: AjoutPanierTest
		Given l'utilisateur est sur la page d'acceuil "urlHome"
		    When l'utilisateur choisi d'ajouter le "backpack" à son panier
		    Then il clique sur le panier en haut a droite
		    And il est redirigé sur la page "urlPanier"
		    And il vérifie que le "backpack" est présent dans le panier
		
