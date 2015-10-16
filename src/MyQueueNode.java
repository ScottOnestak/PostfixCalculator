public class MyQueueNode<AnyType> {
	public AnyType data;
	public MyQueueNode<AnyType> next;
	public MyQueueNode<AnyType> prev;
	public MyQueueNode(AnyType x) {data=x;}
}
