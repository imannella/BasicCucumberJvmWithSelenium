@minor-disruption @moderate-disruption @severe-disruption
Feature: Live traffic release regression test

Scenario Outline: Live traffic information events related to Unplanned Events

Given A valid list of Traffic information from "http://m.highways.gov.uk/feeds/rss/UnplannedEvents.xml"

When I want to validate events for category "<category>"

Then I validate I have "<numItems>" elements 
And I proof evidence creating the relative CSV file

Examples:
    | category            | numItems  | 
    | Minor Disruption    | 32        |
	| Moderate Disruption | 7         |
	| Severe Disruption   | 3         |
