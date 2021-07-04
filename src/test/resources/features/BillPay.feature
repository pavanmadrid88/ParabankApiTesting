Feature: Bill Pay

  @billPay
  Scenario Outline: Bill Pay

    Given I am logged in with user "<userName>" and password "<password>"
    When I create an account of following types
      | accountType | fromAccountId |
      | CHECKING    | 13344         |
      | SAVINGS     | 13344         |

    And I pay a bill with amount "<amount>",payee name "<payeeName>",payee address street "<payeeAddressStreet>",payee address city "<payeeAddressCity>",payee address state "<payeeAddressState>",payee address zipcode "<payeeAddressZipcode>",payee phoneNumber "<payeeAddressPhoneNumber>"
    Then bill pay should be successful for amount "<amount>",payee name "<payeeName>"

    Examples:
      | userName | password |  |  |  | amount | payeeName | payeeAddressStreet | payeeAddressCity | payeeAddressState | payeeAddressZipcode | payeeAddressPhoneNumber |
      | john     | demo     |  |  |  | 200    | Sam       | NYC                | 1234568945       | NYC               | M1h3                | 123458                  |
