Feature: test feature

  @woyou
  Scenario Outline: get list
    Given [woyou get name] has config
    When [woyou get name] get <path> success
    Then [woyou get name] response status is <status> and code is <code> success
    And [woyou get name] response body contains <body> success

    Examples:
      | path     | status | code | body  |
      | /getName | OK     | 200  | body  |
      | /getName | OK     | 200  | body2 |

  @disable
  Scenario Outline: get list disable
    Given [woyou get name] has config
    When [woyou get name] get <path> success
    Then [woyou get name] response status is <status> and code is <code> success
    And [woyou get name] response body contains <body> success

    Examples:
      | path     | status | code | body  |
      | /getName | OK     | 200  | body  |
      | /getName | OK     | 200  | body2 |