import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileReader;

public class Test {
	public static void main(String[] args) throws Exception{
		
		//create the ArrayList, the file names, create empty string, and set the BufferedWriter and BufferedWriter to null
		ArrayList<String> thePostfix = new ArrayList<String>();
		ArrayList<Double> evaluations = new ArrayList<Double>();			
		String writer = "PostfixAndEvaluations.txt";
		String writer2 = "Evaluations.txt";
		String postfix = "";
		BufferedReader br = null;
		BufferedWriter bw = null;
		BufferedWriter bw2 = null;
				
		//create a scanner to take in the file name
		System.out.print("Please enter the file name:");
		Scanner scan = new Scanner(System.in);
		String input = scan.next();

		//create try statement to read in the file with the BufferedReader
		try {
			//create empty string
			String sCurrentLine;
			
			//create BufferedReader
			br = new BufferedReader(new FileReader(new File(input)));

			//while there is another line, read it in, split it at the space
			while ((sCurrentLine = br.readLine()) != null) {
				String theString = sCurrentLine;
				theString = theString.replaceAll("\\s+", "");
				
				//set postfixValues equal to the ArrayList sent back by the toPostfix method
				ArrayList<String> postfixValues = toPostfix(theString);
				
				//traverse through the arraylist to create the postfix string
				for(int i = 0; i < postfixValues.size(); i++){
					postfix += (postfixValues.get(i) + " ");
				}
				
				//add the postfix to an arraylist
				thePostfix.add(postfix);
				
				//add the evaluation to another arraylist
				evaluations.add(postFixEval(postfix));
				
				//set postfix back to an empty string
				postfix = "";
			}
			//close the BufferedReader
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		} 

		//create the BufferedWriter
		bw = new BufferedWriter(new FileWriter(new File(writer)));

		//write each item in the ArrayList to the file - "PostfixAndEvaluations.txt"
		for(int i = 0; i < thePostfix.size(); i++){
			bw.write("Postfix:    " + thePostfix.get(i) + "\nEvaluation: " + evaluations.get(i) + "\n\n");
		}

		//close the BufferedWriter
		bw.close();
		
		//create second BufferedWriter
		bw2 = new BufferedWriter(new FileWriter(new File(writer2)));
		
		//write each item in the Arraylist to the file - "Evaluations.txt"
		for(int k = 0; k < evaluations.size(); k++){
			bw2.write(evaluations.get(k) + "\n");
		}
		
		//close the second BufferedWriter and the scanner
		bw2.close();
		scan.close();
	}
	
	//takes the string and puts it into an ArrayList in postfix form
	public static ArrayList<String> toPostfix(String infixExp) throws Exception {
		try{
			//create stack, queue, empty string, and arraylists
			MyStack<String> stack=new MyStack<String>();
			MyQueue<String> queue =new MyQueue<String>();
			String string = "";
			ArrayList<String> theCharacters = new ArrayList<String>(); 
			ArrayList<String> theAnswers = new ArrayList<String>();
			
			//traverse through the string, ignoring spaces, grouping characters such as "2.0" together and attaching the negative
			//to its appropriate double
			for(int k = 0; k < infixExp.length(); k++){
				if(infixExp.charAt(k) != ' '){
					if(!isOperator(infixExp.charAt(k)))
						string += infixExp.charAt(k);
					else if (infixExp.charAt(k) == '-' && (k == 0 || isOperator(infixExp.charAt(k-1)) || infixExp.charAt(k-1) == '('))
						string += infixExp.charAt(k);
					else if(isOperator(infixExp.charAt(k))){
						if(string.length()>0)
							theCharacters.add(string);
						theCharacters.add(""+ infixExp.charAt(k));
						string = "";
					} 
				}
			}

			//if there is something in the string, also add that to the arraylist
			if(string.length()>0)
				theCharacters.add(string);
			
			//now, traverse through the arraylist and perform the appropriate task based on the operator
			for(int i=0;i<theCharacters.size();i++){
				//if it is not an operator (a number), send it to the queue
				if(!isOperator(theCharacters.get(i))){
					queue.enqueue(theCharacters.get(i));
				} else {
						//create a switch case for the operators
						switch(theCharacters.get(i)){
							//if there is a space (which there shouldn't be), just do nothing with it
							case (" "):
								break;
							//if it is a "(", push it on the stack
							case ("("):
								stack.push(theCharacters.get(i));
								break;
							//if a ")", pop everything in the stack up to "(" and enqueue it
							//then pop the "(" off the stack, but do nothing with it
							case(")"):
								while(!(stack.isEmpty()) && !(stack.peek()).equals("(")){
									queue.enqueue(stack.pop());
								}	
								stack.pop();
								break;
							//while the stack isn't empty and the top of the stack is not a "(", compare the value on top of the stack
							//to the current operator.  if the stack operator is greater in precedence pop and enqueue it.
							//then, push the current character onto the stack
							default:
								while (!stack.isEmpty() && !(stack.peek().equals("(")) && 
										operatorPrecedence(stack.peek()) > operatorPrecedence(theCharacters.get(i))){
									queue.enqueue(stack.pop());
								}
								stack.push(theCharacters.get(i));
								break;
						}
					}
				}
			
			//enqueue everything in the stack until the stack is empty
			while(!stack.isEmpty()){
				queue.enqueue(stack.pop());
			}
			
			//then add everything from the queue to an arraylist
			while(!queue.isEmpty()){
				theAnswers.add(queue.dequeue());
			}
			
			//return that arraylist
			return theAnswers;
			
		} catch (EmptyStackException ese) {
			throw new EmptyStackException();
		} catch (NumberFormatException nfe) {
			throw new NumberFormatException();
		}
	}

	//evaluate the postfix and return a double
	private static double postFixEval(String string) throws Exception{
		try{
			//create a stack, spit the string by spaces and put it into an array
			MyStack<Double> stack = new MyStack<Double>();
			String[] postfix = string.split(" ");
			double evaluation;
			
			//traverse the string array 
			for(int i = 0; i < postfix.length; i++){
				//send to appropriate case, which calls its operator method
				switch(postfix[i]){
				case("+"):
					stack.push(applyOperatorBinary(postfix[i] , stack));
					break;
				case("-"):
					stack.push(applyOperatorBinary(postfix[i] , stack));
					break;
				case("/"):
					stack.push(applyOperatorBinary(postfix[i] , stack));
					break;
				case("*"):
					stack.push(applyOperatorBinary(postfix[i] , stack));
					break;
				case("<"):
					stack.push(applyOperatorBinary(postfix[i] , stack));
					break;
				case(">"):
					stack.push(applyOperatorBinary(postfix[i] , stack));
					break;
				case("="):
					stack.push(applyOperatorBinary(postfix[i] , stack));
					break;
				case("&"):
					stack.push(applyOperatorBinary(postfix[i] , stack));
					break;
				case("|"):
					stack.push(applyOperatorBinary(postfix[i] , stack));
					break;
				case("%"):
					stack.push(applyOperatorBinary(postfix[i] , stack));
					break;
				case("^"):
					stack.push(applyOperatorBinary(postfix[i] , stack));
					break;
				case("!"):
					stack.push(applyOperatorUnary(postfix[i] , stack));
					break;
				//if not an operator, it must be a number
				//therefore, make it a double and push it onto the stack
				default:
					stack.push(new Double(postfix[i]));
				}
			}
			//pop the top value, which, if a valid entry, is the answer
			evaluation = stack.pop();
			
			//if there are more numbers in the stack, then there was an invalid entry
			if(!stack.isEmpty())
				throw new Exception("Invalid Entry");
			
			//return the evaluation
			return evaluation;
			
			}

		catch (EmptyStackException ese) {
			throw new EmptyStackException();
		} 
		catch (NumberFormatException nfe) {
			throw new NumberFormatException();

		}
		
	}

	//evaluate the binary operators
	private static double applyOperatorBinary(String operator, Stack<Double> stack) throws Exception {
		//if the stack is empty, then there was an invalid entry.  otherwise, pop the double
		if(stack.isEmpty())
			throw new Exception("Invalid Entry");
		double op1 = stack.pop();
		
		//same as above
		if(stack.isEmpty())
			throw new Exception("Invalid Entry");
		double op2 = stack.pop();
		
		switch(operator){
			case("+"):
				return op2 + op1;
			case("-"):
				return op2 - op1;
			case("*"):
				return op2 * op1;
			case("/"):
				if(op1 == 0)
					throw new IllegalArgumentException("Divide by zero error for: " + op2 + " / " + op1);
				else
					return op2 / op1;
			case("%"):
				return op2 % op1;
			case("^"):
				return exponent(op2,op1);
			case("&"):
				if (op1 == 1 && op2 == 1){
					return 1.0;
				}
				else
					return 0.0;
			case("|"):
				if (op1 == 1 || op2 == 1)
					return 1.0;
				else
					return 0.0;
			case("="):
				if (op1 == op2)
					return 1.0;
				else
					return 0.0;
			case(">"):
				if(op2 > op1)
					return 1.0;
				else
					return 0.0;
			case("<"):
				if (op2 < op1)
					return 1.0;
				else
					return 0.0; 
			default:
				throw new IllegalArgumentException("Operator " + operator + " not found");
		}
	}
	
	private static double applyOperatorUnary(String operator, Stack<Double> stack) throws Exception {
		//if the stack is empty, invalid entry.  else pop the double.
		if(stack.isEmpty())
			throw new Exception("Invalid Entry");
		double op1 = stack.pop();
			
			//if operator is "!", perform it.  otherwise, illegal argument.
			if (operator.equals("!")) {
				if(op1 == 1.0) 
					return 0.0;
				else
					return 1.0;
			}
			else {
				throw new IllegalArgumentException("Operator" + operator + " not found");
			}
	}
	
	//evaluate the exponent using recursion
	private static double exponent(double base , double power){
		if(power == 0)
			return 1;
		else if (power < 0)
			return (1.0 / base) * exponent(base, power + 1);
		else
			return base * exponent(base, power - 1);
	}
	
	//returns true if it is an operator for a string, otherwise false
	private static boolean isOperator(String character){
		if(character == null)
			return false;
		switch(character){
			case ("+"):
				return true;
			case ("-"):
				return true;
			case("*"):
				return true;
			case("/"):
				return true;
			case(">"):
				return true;
			case("<"):
				return true;
			case("|"):
				return true;
			case("&"):
				return true;
			case("!"):
				return true;
			case("="):
				return true;
			case("("):
				return true;
			case(")"):
				return true;
			case("%"):
				return true;
			case("^"):
				return true;
			default:
				return false;
		}
			
	}
	
	//returns true if the character is true for a boolean, else returns false
	private static boolean isOperator(char character){
		switch(character){
			case ('+'):
				return true;
			case ('-'):
				return true;
			case('*'):
				return true;
			case('/'):
				return true;
			case('>'):
				return true;
			case('<'):
				return true;
			case('|'):
				return true;
			case('&'):
				return true;
			case('!'):
				return true;
			case('='):
				return true;
			case('('):
				return true;
			case(')'):
				return true;
			case('%'):
				return true;
			case('^'):
				return true;
			default:
				return false;
		}
	}
	
	//stores the precedence of the operators for the comparison
	private static double operatorPrecedence(String operator) throws Exception {
		switch(operator){
			case("("):
				return 8;
			case(")"):
				return 8;
			case("^"):
				return 7;
			case("!"):
				return 6;
			case("-u"):
				return 6;
			case("*"):
				return 5;
			case("/"):
				return 5;
			case("%"):
				return 5;
			case("+"):
				return 4;
			case("-"):
				return 4;
			case(">"):
				return 3;
			case("<"):
				return 3;
			case("&"):
				return 2;
			case("|"):
				return 1;
			case("="):
				return 0;
			default:
				throw new Exception();
		}
	}
}
