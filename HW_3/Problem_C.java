import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class Problem_C
{
	public static class CLinkedList
	{
		  public static class Node
		  {
			  	int element;            // reference to the element stored at this node
			  	int count = 1;			//The number of instances with the same number
		    	Node nextG;   			// reference to the subsequent node in the list
		    	Node nextL;
		    	Node prev;				// reference to the previous node in the list

		    	public Node(int ele, Node nG, Node nL,  Node p)
		    	{
		    		this.element = ele;
		      		this.nextG = nG;
		      		this.nextL = nL;
		      		this.prev = p;
		    	}

		    	//Geters
		    	public int getElement()
		    	{ 
		    		return this.element; 
		    	}

		    	public Node getNextG() 
		    	{ 
		    		return this.nextG; 
		    	}
		    	
		    	public Node getNextL() 
		    	{ 
		    		return this.nextL; 
		    	}
		    	
		    	public Node getPrev()
		    	{
		    		return this.prev;
		    	}

		    	//Modifier methods
		    	public void setNextL(Node n) 
		    	{ 
		    		this.nextL = n; 
		    	}
		    	
		    	public void setNextG(Node n) 
		    	{ 
		    		this.nextG = n; 
		    	}
		    	
		    	public void setPrev(Node p)
		    	{
		    		this.prev = p;
		    	}
		  }

		  // instance variables of the SinglyLinkedList
		  public Node head = null;               //head node of the list (or null if empty)
		  //public Node tail = null;               // last node of the list (or null if empty)
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
		  public int head() 
		  {             
			  if(isEmpty())
			  {
				  return -1;
			  }
			  return this.head.getElement();
		  }

		  // update methods
		  // adds element e to the front of the list
		  public void add(int ele) 
		  {                
			  Node newest = new Node(ele, null, null, null);         // create and link a new node
			  
			  if(isEmpty())
			  {
				  this.head = newest;
			  }
			  else
			  {
				  Node walk = this.head;
				  while(true)
				  {
					  //New element is equal to the current
					  if(ele == walk.getElement())
					  {
						  walk.count++;
						  break;
					  }
					  
					  //Case where the new element is larger then the current
					  else if(ele > walk.getElement())
					  {
						  //No larger child
						  if(walk.getNextG() == null)
						  {
							  walk.setNextG(newest);
							  newest.setPrev(walk);
							  break;
						  }
						  else	//Go to next node
						  {
							  walk = walk.getNextG();
							  continue;
						  }
					  }
					  
					  //New element is smaller or equal
					  else
					  {
						  //No smaller child
						  if(walk.getNextL() == null)
						  {
							  walk.setNextL(newest);
							  newest.setPrev(walk);
							  break;
						  }
						  else	//Go to next node
						  {
							  walk = walk.getNextL();
							  continue;
						  }
					  }
				  }
			  }

			  size++;
		  }

		  // removes and returns the first element
		  public int removeG() 
		  {                   
			  if(isEmpty()) 
			  {
				  return -1;              // nothing to remove
			  }
			  
			  Node walk = this.head;
			  while(true)
			  {
				  if(walk.getNextG() == null)	//Found the largest
				  {
					  break;
				  }
				  else
				  {
					  walk = walk.getNextG();
					  continue;
				  }
			  }
			  
			  //Remove the largest
			  int ret = walk.getElement();
			  
			  if(walk.count > 1)	//More then one so lower count
			  {
				  walk.count--;
			  }
			  else		//Need to remove the node
			  {
				if(walk == this.head)
				{
					if(walk.getNextL() != null)
					{
						this.head = walk.getNextL();
						walk.getNextL().setPrev(null);
					}
					else
					{
						this.head = null;
					}
				}
				else if(walk.getNextL() != null)
			  	{
				  	walk.getPrev().setNextG(walk.getNextL());
				  	walk.getNextL().setPrev(walk.getPrev());
			  	}
			  	else
			  	{
				  	walk.getPrev().setNextG(null);
			  	}
			  }
			  
			  this.size--;
			  return ret;
		  }
		  
		// removes and returns the first element
		  public int removeL() 
		  {                   
			  if(isEmpty()) 
			  {
				  return -1;              // nothing to remove
			  }
			  
			  Node walk = this.head;
			  while(true)
			  {
				  if(walk.getNextL() == null)	//Found the smallest
				  {
					  break;
				  }
				  else
				  {
					  walk = walk.getNextL();
					  continue;
				  }
			  }
			  
			  //Remove the largest
			  int ret = walk.getElement();
			  
			  if(walk.count > 1)	//More then one so lower count
			  {
				  walk.count--;
			  }
			  else		//Need to remove the node
			  {
				if(walk == this.head)
				{
					if(walk.getNextG() != null)
					{
						this.head = walk.getNextG();
						walk.getNextG().setPrev(null);
					}
					else
					{
						this.head = null;
					}
				}
			    else if(walk.getNextG() != null)
			  	{
				  	walk.getPrev().setNextL(walk.getNextG());
				  	walk.getNextG().setPrev(walk.getPrev());
			  	}
			  	else
			  	{
				  	walk.getPrev().setNextL(null);
			  	}
			  }
			  
			  this.size--;
			  
			  return ret;
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
					//System.out.println("Inserting bill " + bill + "     ");
					urn.add(bill);
					//System.out.println(urn.toString());
					//System.out.println("The head is now " + urn.first());
				}
				
				//Get the highest and lowest
				int max = urn.removeG();
				//System.out.print("Max = " + max);
				int min = urn.removeL();
				
				//System.out.println("MinMax getten: " + urn.toString());
				//System.out.println("The head is now " + urn.first());
				//System.out.println("The tale is now " + urn.last());
				
				//System.out.println(" min = " + min + " Diff = " + (max - min));
				spent = spent + (max - min);
			}
			
			//System.out.println("Spent = " + spent);
			output.write(Integer.toString(spent));
			first = false;
		}
		
		sc.close();
		output.close();
	}
}
