Feature: BuildxactActions.feature

  @CreateANewBuildxactUser
  Scenario Outline: Create a new user in buildxact application
    Given I launch "Chrome" browser session
    When I navigate to Buildxact application
    And I login to Buildxact application as per "<TestCaseName>"
    Then I verify if the login is successful or not
    Then I navigate to My Business and fill and submit the new user form as per "<TestCaseName>"
    And I logout from Buildxact application
    Then I navigate to the user's mailbox, open the Welcome mail, activate the user account as per "<TestCaseName>"
    And I verify if the login is successful or not
    Examples:
      |TestCaseName|
      |TestCase1   |
      |TestCase2   |

  @LoginAndLogoutFromBuildxact
  Scenario Outline: Login to buildxact using users
    Given I launch "Chrome" browser session
    When I navigate to Buildxact application
    And I login to Buildxact application as per "<TestCaseName>"
    Then I verify if the login is successful or not
    And I logout from Buildxact application
    Examples:
      |TestCaseName|
      |TestCase2   |

  @ResetBuildxactPassword
  Scenario Outline: Reset the password for buildxact users
    Given I launch "Chrome" browser session
    When I navigate to Buildxact application
    And I login to Buildxact application as per "<TestCaseName>"
    Then I verify if the login is successful or not
    Then I reset the user's password under My Profile
    And I logout from Buildxact application
    Then I navigate to the user's mailbox, open the Password Reset mail and reset the password as per "<TestCaseName>"
    And I logout from Buildxact application
    Then I login to the Buildxact application with the new password as per "<TestCaseName>"
    And I verify if the login is successful or not
    And I logout from Buildxact application
    Then I login to Buildxact application as per "<TestCaseName>"
    And I verify if the login is successful or not after password reset with old password
    Examples:
      |TestCaseName|
      |TestCase1   |