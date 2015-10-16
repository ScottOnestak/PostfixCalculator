public class MyQueue<AnyType> implements Queue<AnyType>{
	//create nodes head an tail
	private MyQueueNode<AnyType> head;
	private MyQueueNode<AnyType> tail;

	//constructor
	public MyQueue(){
		//initialize head and tail to null and link them together
		head = new MyQueueNode <AnyType>(null);
		tail = new MyQueueNode <AnyType>(null);
		head.prev = null;
		head.next = tail;
		tail.prev = head;
		tail.next = null;
	}
	
	//isEmpty method to tell if the queue is empty
	public boolean isEmpty(){
		if(head.data == null && tail.data == null)
			return true;
		return false;
	}

	//enque method to insert a node into the queue
	public void enqueue(AnyType x){
		if(isEmpty()){
			head.data = x;
			tail = head;
		}else{	
			MyQueueNode <AnyType> temp = tail;
			tail = new MyQueueNode<AnyType>(x);
			tail.data = x;
			tail.next = null;
			tail.prev = temp;
			temp.next = tail;
		}
	}
	
	//dequeue method to remove a node from the queue
	public AnyType dequeue(){
		if(isEmpty() == false){
			AnyType data = head.data;
			if(head.next == null){
				head.data = null;
				tail = head;
			}else{
				head = head.next;
				head.prev = null;
			}
			return data;
		}else
			return null;
	}
	
	//peek at the front of the queue
	public AnyType peek(){
		if(isEmpty())
			return null;
		return head.data;}
}
