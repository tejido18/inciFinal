# language: en
Feature: Create an empty incident

  Scenario: An incident is not created
    Given I am a correct agent
    When I login in the Incidence Manager
    Then I click on create an incident
    Then I dont fill the incident description
    Then I get an error in the form
