Feature: ResetPassword

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