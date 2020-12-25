Scenario: CreateNewUser

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