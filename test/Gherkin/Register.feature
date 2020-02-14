#----------------------------------
# Empty Cucumber .feature file
#----------------------------------
    Feature: Registering a new account
   As a user, I want to register a new account

   Scenario outline:  
      Given the user hasnt already made an account
      When they input a <username>
      And they input an <email>
      And they input a <password>
      Then user is given a message saying <message>

      Examples:
         |username|email|password|message|
         |JohnJoe|JohnJoe@gmail.com|Pass|true|
         |BillyBob123|Billy@hotmail.com|HelloThere|true|
         |Lorem|Ipsum@live.ie|qwert|true|






   
