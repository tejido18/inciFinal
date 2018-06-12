# language: en
Feature: Create an incident

  Scenario: An incident is created
    Given I am a correct agent
    When I login in the Incidence Manager
    Then I click on create an incident
    Then I fill the incident description
    Then I create the incident
