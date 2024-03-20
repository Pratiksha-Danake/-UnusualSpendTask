# UnusualSpendTask
## Problem Statement 1 : Unusual Spends

You work at a credit card company. As a value-add, they want to start providing alerts to users when their spending in any particular category is higher than usual.
 - Compare the total amount paid for the current month, grouped by category with the previous month.
 - Filter down to the categories for which the user spent at least 50% more this month than last.
 - Compose an e-mail message to the user that lists the categories for which spending was unusually high

### State and Behaviour

#### class CreditCardUser:
- State:
  - Private Credit Card Number: Represents the credit card number of the respective customer.
  - Private Name: Name of the credit card customer.
  - Private Email: Email address of the credit card customer.
  - private double totalSpentOfTheMonth
- Behaviour:
  - public getName(): Return the name of the credit card customer.
  - public getEmail(): Return the email address of the credit card customer.

#### class EmailAlert:
- Behaviour:
  - sendEmail(recepientEmail,userName,subject,emailContent,List<category,spent>): Send email to the customer.
    
#### class ManageUserSpent:
- State:
    - TransactionHistory transactionHistory
    - double currentMonthSpent
    - double lastMonthSpent
    - private List<Str,spent>
- Behaviour:
    - checkMonthlyTotalSpent
    - checkMonthlySpentByCategory(creditCardNo)
    - compareSpentByCategory(currentMonth,lastMonth)
         ##### If the current month spent for at least one category is high then it will add it to the list and call sendEmail method.

#### class TransactionHistory:
- State:
  - List of transactions: List of transactions made by the customer.
- Behaviour:
  - saveTransaction(): save a new transaction to the transaction history.

 #### class Transaction
 - State:
  -  customerId
  -  category
  -  Date transactionDate
  -  amountSpend
- Behaviour
  - public createTransaction()
