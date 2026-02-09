Feature: TestOK

	@POEI2-641 @Tony_tag
	Scenario: TestOK
		Given l'utilisateur est sur la page de connexion
		    When il saisit le login "standard_user" et le mot de passe "secret_sauce"
		    Then l'utilisateur est sur la page d'acceuil "urlHome"
		
