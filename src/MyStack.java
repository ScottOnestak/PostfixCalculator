public class MyStack<AnyType> implements Stack<AnyType> {
	//create the head node
	public MyStackNode<AnyType> head;
	
	public boolean isEmpty() {
		if(head == null)
			return true;
		return false;
	}
	
	//insert a node by pushing it onto the stack
	public void push(AnyType x) {
		MyStackNode <AnyType> NewNode = new MyStackNode <AnyType>(x);
		NewNode.data = x;
		NewNode.next = head;
		head = NewNode;
	}
	
	//remove a node by popping ot off the stack
	public AnyType pop() {
		if(isEmpty() == false){
			MyStackNode<AnyType> temp = head;
			head = head.next;
			return temp.data;	
		} else
			return null;
	}
	
	//peek at the top of the stack
	public AnyType peek() {
		if(isEmpty())
			return null;
		return head.data;
	}
	
}
