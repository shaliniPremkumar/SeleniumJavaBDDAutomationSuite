Feature: LoginLogout

  @LoginAndLogoutFromBuildxact
  Scenario Outline: Login to buildxact using users
    Given I launch "Chrome" browser session
    When I navigate to Buildxact application
    And I login to Buildxact application as per "<TestCaseName>"
    Then I verify if the login is successful or not
    And I logout from Buildxact application
    Examples:
      |TestCaseName|
      |TestCase1   |
      |TestCase2   |