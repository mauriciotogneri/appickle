@super_tag @awesome_tag
Feature: Serve coffee
	# This is the first comment
	Coffee should not be served until paid for
	Coffee should not be served until the button has been pressed
	# This is another comment
	If there is no coffee left then money should be refunded

	@behind
	Background:
		Given a global administrator named "Greg"
		And a blog named "Greg's anti-tax rants"
		And a customer named "Wilson"
		And a blog named "Expensive Therapy" owned by "Wilson"

	@scenario1
	Scenario: Buy last coffee
	In order to buy the last coffee
	As a costumer of the shop
	I want to order a coffee
		Given there is 1 coffee left in the machine
		And I have deposited 1$
		When I press the "strong coffee" button
		And I wait for a few seconds
		Then I should be served a coffee
		But in less than 10 seconds

	@scenario2
	Scenario Outline: eating
	In order to eat
	As a hungry person
	I want to order a pizza
		Given there are <start> cucumbers
		When I eat <eat> cucumbers
		Then I should have <left> cucumbers

		Examples:
			| start | eat | left |
			| 12    | 5   | 7    |
			| 20    | 5   | 15   |