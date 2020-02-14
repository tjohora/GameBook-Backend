#----------------------------------
# Example of Cucumber .feature file
#----------------------------------
    
 
Feature: User Login
As a user, I want to login into my account

   Scenario: User is logging in
      Given the user has already made an account
      When inputting their <username>
      And inputting their <password>
      Then user is given a message saying <message>

      Examples:
         |username|password|message|
   
