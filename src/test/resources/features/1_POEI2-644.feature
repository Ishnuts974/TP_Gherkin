Feature: TestKO

	@POEI2-644 @Tony_tag
	Scenario Outline: TestKO
		Given l'utilisateur est sur la page de connexion
		    When il saisit le login "<username>" et le mot de passe "<password>"
		    Then il est redirigé vers la page d'acceuil
		    And elle est affiche
		    Examples:
		      | username | password|
		      |locked_out_user| secret_sauce|
		      |standard_user| secret_sauce|
		
