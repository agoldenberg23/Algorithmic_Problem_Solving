import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class Problem_c_old
{
	public static class CLinkedList
	{
		  public static class Node
		  {
			  	int element;            // reference to the element stored at this node
		    	Node next;   			// reference to the subsequent node in the list
		    	Node prev;				// reference to the previous node in the list

		    	public Node(int ele, Node n, Node p)
		    	{
		    		this.element = ele;
		      		this.next = n;
		      		this.prev = p;
		    	}

		    	//Geters
		    	public int getElement()
		    	{ 
		    		return this.element; 
		    	}

		    	public Node getNext() 
		    	{ 
		    		return this.next; 
		    	}
		    	
		    	public Node getPrev()
		    	{
		    		return this.prev;
		    	}

		    	//Modifier methods
		    	public void setNext(Node n) 
		    	{ 
		    		this.next = n; 
		    	}
		    	
		    	public void setPrev(Node p)
		    	{
		    		this.prev = p;
		    	}
		  }

		  // instance variables of the SinglyLinkedList
		  public Node head = null;               //head node of the list (or null if empty)
		  public Node tail = null;               // last node of the list (or null if empty)
		  public int size = 0;                   // number of nodes in the list

		  public CLinkedList() { }              // constructs an initially empty list

		  // access methods
		  public int size()
		  { 
			  return this.size; 
		  }

		  public boolean isEmpty()
		  { 
			  return this.size == 0; 
		  }

		  // returns (but does not remove) the first element
		  public int first() 
		  {             
			  if(isEmpty())
			  {
				  return -1;
			  }
			  return this.head.getElement();
		  }

		  // returns (but does not remove) the last element
		  public int last() 
		  {              
			  if(isEmpty()) 
			  {
				  return -1;
			  }
			  return this.tail.getElement();
		  }

		  // update methods
		  // adds element e to the front of the list
		  public void addFirst(int ele) 
		  {                
			  Node newest = new Node(ele, this.head, null);         // create and link a new node
			  
			  if(isEmpty() == false)
			  {
				  this.head.setPrev(newest);
			  }
			  else
			  {
				  this.tail = newest;                  // special case: new node becomes tail also
			  }
			  
			  this.head = newest;
			  size++;
		  }

		  // adds element e to the end of the list
		  public void addLast(int ele) 
		  {                 
			  Node newest = new Node(ele, null, this.tail);    // node will eventually be the tail
			  // special case: previously empty list
			  if(isEmpty())
			  {
				  this.head = newest;
			  }
			  else
			  {
				  this.tail.setNext(newest);     // new node after existing tail
			  }
			  
			  this.tail = newest;               // new node becomes the tail
			  size++;
		  }

		  // removes and returns the first element
		  public int removeFirst() 
		  {                   
			  if(isEmpty()) 
			  {
				  return -1;              // nothing to remove
			  }
			  
			  int answer = this.head.getElement();
			  this.head = this.head.getNext();                   // will become null if list had only one node
			  size--;
			  
			  if(size == 0)
			  {
				  this.tail = null;                           // special case as list is now empty
			  }
			  else
			  {
				  //System.out.println(this.head);
				  this.head.setPrev(null);
			  }
				  
			  return answer;
		  }
		  
		  //Removes the last element of the list
		  //return the removed element (or null if empty)
		  public int removeLast()
		  {
			  if(isEmpty()) 
			  {
				  return -1;			//Nothing to remove
			  }
			  
			  int answer = this.tail.getElement();
			  this.tail = this.tail.getPrev();
			  size--;
			  
			  if(size == 0)
			  {
				  this.head = null;
			  }
			  else
			  {
				  this.tail.setNext(null);
			  }
			  
			  return answer;
		  }

		  public int[] GetMinMax()
		  {
			  int[] minMax = new int[2];
			  //Special cases
			  if(isEmpty())
			  {
				  return null;
			  }
			  else if(this.size == 1)
			  {
				  minMax[0] = this.head.getElement();
				  minMax[1] = this.head.getElement();
				  removeFirst();
				  return minMax;
			  }
			  else if(this.size == 2)
			  {
				  if(this.head.getElement() > this.tail.getElement())
				  {
					  minMax[1] = this.head.getElement();
					  minMax[0] = this.tail.getElement();
				  }
				  else
				  {
					  minMax[0] = this.head.getElement();
					  minMax[1] = this.tail.getElement();
				  }
				  
				  removeFirst();
				  removeFirst();
				  return minMax;
			  }
			  
			  minMax[0] = this.head.getElement();
			  minMax[1] = this.head.getElement();
			  Node minInd = this.head;
			  Node maxInd = this.head;
			  
			  //Find the min and max elements and there nodes
			  Node walk = head;
			  while(walk != null)
			  {
				  int ele = walk.getElement();
				  if(ele < minMax[0])
				  {
					  minMax[0] = ele;
					  minInd = walk;
				  }
				  else if(ele > minMax[1])
				  {
					  minMax[1] = ele;
					  maxInd = walk;
				  }
				  
				  walk = walk.getNext();
			  }
			  
			  //Remove the corresponding nodes
			  //Remove the min node
			  if(minInd == this.head)
			  {
				  removeFirst();
			  }
			  else if(minInd == this.tail)
			  {
				  removeLast();
			  }
			  else
			  {
				  minInd.getNext().setPrev(minInd.getPrev());
				  minInd.getPrev().setNext(minInd.getNext());
				  this.size--;
			  }
			  
			  //Remove the max node
			  if(maxInd == this.head)
			  {
				  removeFirst();
			  }
			  else if(maxInd == this.tail)
			  {
				  removeLast();
			  }
			  else
			  {
				  maxInd.getNext().setPrev(maxInd.getPrev());
				  maxInd.getPrev().setNext(maxInd.getNext());
				  this.size--;
			  }
			  
			  return minMax;
		  }
		  
		  public String toString()
		  {
			  StringBuilder sb = new StringBuilder("(");
			  Node walk = head;
			  while (walk != null) 
			  {
				  sb.append(walk.getElement());
				  if (walk != tail)
				  {
					  sb.append(", ");
				  }
				  walk = walk.getNext();
			  }
			  sb.append(")");
			  return sb.toString();
		  }
	}

	public static void main(String[] args) throws IOException
	{
		Scanner sc = new Scanner(System.in);
		BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out, "ASCII"), 4096);
		
		boolean first = true;
		while(true)
		{	
			int dayNum = sc.nextInt();
		
			if(dayNum == 0)
			{
				break;
			}
		
			if(first == false)
			{
				output.write("\n");
			}
			
			//Make a priority que to use
			CLinkedList urn = new CLinkedList();
			
			//Count the amount spent
			int spent = 0;
			
			//For each day
			for(int day = 0; day < dayNum; day++)
			{
				//Insert the bills into the urn
				int billNum = sc.nextInt();
				for(int i = 0; i < billNum; i++)
				{
					int bill = sc.nextInt();
					//System.out.print("Inserting bill " + bill + "     ");
					urn.addFirst(bill);
					//System.out.println(urn.toString());
					//System.out.println("The head is now " + urn.first());
				}
				
				//Get the highest and lowest
				int[] minMax = urn.GetMinMax();
				
				//System.out.println("MinMax getten: " + urn.toString());
				//System.out.println("The head is now " + urn.first());
				//System.out.println("The tale is now " + urn.last());
				
				//System.out.println("Diff = " + (minMax[1] - minMax[0]));
				spent = spent + (minMax[1] - minMax[0]);
			}
			
			
			output.write(Integer.toString(spent));
			first = false;
		}
		
		sc.close();
		output.close();
	}
}
