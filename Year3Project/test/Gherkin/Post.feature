Feature: Posr Feature
    As a user, I want to create a new post
        Scenario: Create a post on the DB
            Given we are on the Post page
            When inputting a post 
            Then i recieve a response from the server