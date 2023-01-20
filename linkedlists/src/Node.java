public class Node 
{
	protected Node next;
	protected Node previous;
	protected String value;

	public Node(String value, Node previous, Node next)
	{
		this.value = value;
		this.previous = previous;
		this.next = next;
	}
	
	public String getValue()
	{
		return this.value;
	}
		
}
