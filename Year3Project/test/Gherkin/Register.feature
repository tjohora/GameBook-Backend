Feature: Register Feature 
   As a user, I want to create a new account
        Scenario Outline: Create a new account
            Given we are on the register page
            When inputting a <username>
            And <password> 
            And <email> into text box
            Then i recieve a response from server
            Examples:
         |username |password | email|
         |Thomas   |password | sdfsdfs@gmail.com| 