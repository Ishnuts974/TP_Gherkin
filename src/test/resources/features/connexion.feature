Feature: Authentification

  #@SuccessLogin
  Scenario: Connexion réussi avec des identifiants valides
    Given l'utilisateur est sur la page de connexion
    When il saisit le login "standard_user" et le mot de passe "secret_sauce"
    Then il est redirigé vers la page d'acceuil
    And elle est affiché


  #@FailLogin
  Scenario: Connexion réussi avec un mot de passe éroné
    Given l'utilisateur est sur la page de connexion
    When il saisit le login "standard_user" et le mot de passe "wrong_password"
    Then un message d'erreur s'affiche

  Scenario Outline: Connexion réussi avec différents username
    Given l'utilisateur est sur la page de connexion
    When il saisit le login "<username>" et le mot de passe "<password>"
    Then il est redirigé vers la page d'acceuil
    And elle est affiché
    Examples:
      | username | password|
      |locked_out_user| secret_sauce|
      |standard_user| secret_sauce|



