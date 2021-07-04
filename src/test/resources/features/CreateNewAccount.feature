Feature: create new account

  Scenario Outline: create new account

    Given I am logged in with user "<userName>" and password "<password>"
    When I create an account of type "<accountType>" from accountId "<fromAccountId>"
    Then account of type "<accountType>" must be created

    Examples:
      | userName | password | accountType | fromAccountId |
      | john     | demo     | CHECKING    | 13344         |
      | john     | demo     | SAVINGS     | 13344         |