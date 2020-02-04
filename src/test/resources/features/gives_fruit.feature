Feature: Fruit is given when fruit is asked for, god damn it

  Scenario: Fruit is given
    Given the user wants any fruit
    When the user asks for 50 fruits
    Then the program returns a string with 50 fruits

  Scenario Outline: A random string of fruits, pulled from the requested pool, is given
    Given the user wants <fruit pool>
    When the user asks for <amount> fruits
    Then the program returns a string with <amount> fruits
    And the string contains <fruit pool>

    Examples:
      | fruit pool                       | amount |
      | only pineapples                  | 5      |
      | pineapples, coconuts, and lemons | 10     |

  Scenario Outline: Asking for an apple returns either a red or green apple
    Given the user wants <apple type> apples
    When the user asks for <amount> fruits
    Then the program returns a string with <amount> fruits
    And the string contains <apple type> apples

    Examples:
      | apple type          | amount |
      | only red            | 5      |
      | only green          | 6      |
      | either red or green | 3      |
