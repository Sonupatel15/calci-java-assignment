Overview
This Java application is a command-line calculator that evaluates arithmetic expressions. It supports basic mathematical operations while handling operator precedence and parentheses correctly. The calculator implements the Shunting Yard algorithm to convert infix expressions to postfix notation before evaluation.
Features

Basic arithmetic operations (+, -, *, /)
Parentheses support for expression grouping
Decimal number support
Operator precedence handling
Input validation
Error handling for invalid expressions and division by zero

Input Rules

Supported operators: +, -, *, /
Parentheses must be properly matched
Only round brackets () are allowed (no square [] or curly {} brackets)
No consecutive operators are allowed
Numbers can be integers or decimals
Spaces in the expression are optional

Error Handling
The calculator handles various error cases:

Invalid operator combinations
Mismatched parentheses
Division by zero
Invalid characters in the expression
Malformed expressions

Implementation Details
The calculator uses a three-step process:

Validation: Checks for valid parentheses and operator combinations
Infix to Postfix Conversion: Uses the Shunting Yard algorithm
Evaluation: Processes the postfix expression to calculate the final result

Technical Components
isValidOperators(): Validates operator placement
isValidParenthisis(): Checks parentheses matching
infixToPostfix(): Converts infix to postfix notation
evaluatePostfix(): Calculates the final result
performOperation(): Executes individual arithmetic operations


Output : 
Enter the valid arithmetic expression: 3+5*2
Expression: 3+5*2
Result: 13.00

Enter the valid arithmetic expression: 2-3/3-3
Expression: 2-3/3-3
Result: -2.00

Enter the valid arithmetic expression: (2*2+1)/7+3-1
Expression: (2*2+1)/7+3-1
Result: 2.71

Enter the valid arithmetic expression: B
Expression: B
Error evaluating expression: empty String

Enter the valid arithmetic expression: -(-)
Expression: -(-)
Error evaluating expression: null

Enter the valid arithmetic expression: 7/3-2+1
Expression: 7/3-2+1
Result: 1.33

Enter the valid arithmetic expression: (5+3*2)+(2*4/2)
Expression: (5+3*2)+(2*4/2)
Result: 15.00

