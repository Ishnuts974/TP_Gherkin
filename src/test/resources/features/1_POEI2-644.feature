Feature: TestKO

	@POEI2-644 @Tony_tag
	Scenario Outline: TestKO
		Given l'utilisateur est sur la page de connexion
		    When il saisit le login "<username>" et le mot de passe "<password>"
		    Then l'utilisateur est sur la page d'acceuil "urlHome"
		    Examples:
		      | username | password|
		      |locked_out_user| secret_sauce|
		      |standard_user| secret_sauce|
		
