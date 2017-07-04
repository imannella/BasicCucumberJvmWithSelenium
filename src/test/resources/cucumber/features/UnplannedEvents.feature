@minor-disruption @moderate-disruption @severe-disruption
Feature: Live traffic release regression test

Scenario Outline: Live traffic information events related to Unplanned Events

Given A valid list of Traffic information from "<url>"

When I want to validate events for category "<category>"

Then I validate I have "<numItems>" elements 
And I store data into CSV file

Examples:
    | url                 | category            | numItems  | 
    | UnplannedEvents.xml | Minor Disruption    | 32        |
	| UnplannedEvents.xml | Moderate Disruption | 7         |
	| UnplannedEvents.xml | Severe Disruption   | 3         |
