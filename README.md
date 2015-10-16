# PostfixCalculator
This is a postfix calculator that converts an equation to postfix, solves the equation, and then outputs the results to a file.

I had to read in a file and then output the result of my calculations to a file.  I did this by asking the 
user for the file name and then, instantiating a BufferedReader and BufferedWriter.

Reading in the code line by line, this was passed to toPostfix method, which converted the string to an 
ArrayList in postfix order.  This was done by traversing the string character by character, ignoring spaces,
and grouping numbers together using my isOperator method to create cases for each whether it is an operator or
not.  From there, these were added to an arraylist.  I then traverse the arraylist, using an if...else to tell
if it is an operator or a number and a switch statement for the operators.  If it is a number, it is
automatically enqueued.  Otherwise, it goes through the switch statements.  If it is an "(", it is automatically
pushed onto the stack.  If it is an ")", the method pops everything in the stack and enqueues it until it
reaches the "(" character.  Then, it pops the "(" without enqueueing it.  For the default case, as long as 
the stack is not empty and the top of the stack is not a "(", then we continue to compare the top of the stack's
operator to the current operator.  If the stack's operator has higher precedence, then it is popped and enqueued.
From there, the current operator is pushed onto the stack.  From here, if the stack is not empty, pop and queue
everything in the stack, put the queue into an arraylist, and pass back the arraylist.

Back in the main method, I traverse this arraylist and create a string, which is the postfix.  I then add this
string to another arraylist to be stored for later use of the BufferedWriter.  Then, I pass this string to the
postfixEval method.  It splits the string passed in on spaces, and then traverses this array.  For each case,
it calls the correct method to evaluate it and pushes this onto the stack, or it goes to the default method, 
which means it must be a number, so it makes it a double and pushes it onto the stack to be used.  At the end
of this process, the stack is popped one time, and this double is returned as the answer.

This answer is then put into a different arraylist to be stored for use in the BufferedWriter.  After the
BufferedReader has finished, I then make a BufferedWriter outside the while loop.  Then, I traverse the 
arraylist and add the values stored in them to the arraylist.  I created two BufferedWriters because I wanted
to see the postfix expressions and evaluations, but I knew only the answers were necessary for grading.
Therefore, "PostfixAndEvaluations.txt" will contain the postfix and evaluations and "Evaluations.txt" will
contain only the evaluations.
