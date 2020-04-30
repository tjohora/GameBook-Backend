  
Feature: Login Feature
As a user, I want to login into my account
    Scenario Outline: Log in with correct username and password
        Given the user has already registered
        When inputting their <username>
        And inputting their <password>
        Then user is recieves theyre user details

        Examples:
         |username |password |
         |Thomas   |password |
    

   
