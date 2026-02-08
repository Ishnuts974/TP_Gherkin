Feature: TestSetTP

	@POEI2-644 @POEI2-699 @Tony_tag
	Scenario Outline: TestKO
		Given l'utilisateur est sur la page de connexion
		    When il saisit le login "<username>" et le mot de passe "<password>"
		    Then il est redirigé vers la page d'acceuil
		    And elle est affiche
		    Examples:
		      | username | password|
		      |locked_out_user| secret_sauce|
		      |standard_user| secret_sauce|
		
	@POEI2-641 @POEI2-699 @Tony_tag
	Scenario: TestOK
		Given l'utilisateur est sur la page de connexion
		    When il saisit le login "standard_user" et le mot de passe "secret_sauce"
		    Then il est redirigé vers la page d'acceuil
		    And elle est affiche
		
