import java.util.Scanner;
import java.util.Stack;

public class Problem_2
{
	public static boolean isNum(String ch)
	{
	    switch (ch)
	    {
	    	case "1":
	    	case "2": 
	    	case "3":
	    	case "4":
	    	case "5":
	    	case "6": 
	    	case "7":
	    	case "8":
	    	case "9":
	    	case "0":return true;
	    }
	    return false;
	}
	
	public static int checkPres(String a, String b)
	{
		//Higher pressidence
		if((a.equals("*") || a.equals("/")) && (b.equals("+") || b.equals("-")))
		{
			return 1;
		}
		
		//Lower pressidence
		if((a.equals("+") || a.equals("-")) && (b.equals("*") || b.equals("/")))
		{
			return -1;
		}
		
		//Equal pressidence
		return 0;
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		
		int testCases = Integer.parseInt(sc.nextLine());
		sc.nextLine();
		
		runLoop: for(int run = 0; run < testCases; run++)
		{
			if(sc.hasNextLine() == false)
			{
				break;
			}
			//System.out.println("Running case: " + run);
			
			Stack<String> stack = new Stack<String>();
			String output = "";
			
			while(sc.hasNextLine())
			{
				String line = sc.nextLine();
				if(line.equals(""))
				{
					while(stack.isEmpty() == false)
					{
						output = output + stack.pop();
						//System.out.println(output);
					}
					System.out.println(output);
					if(run != testCases-1)
					{
						System.out.println();
					}
					continue runLoop;
				}
				
				line = line.substring(0, 1);
				
				if(isNum(line) == true)
				{
					output = output + line;
					//System.out.println(output);
				}
				else	//Is an operator
				{
					if(stack.isEmpty() || stack.peek().equals("("))
					{
						stack.push(line);
					}
					else if(line.equals("(")) //Open ( special
					{
						stack.push("(");
					}
					else if(line.equals(")"))	//The ) case
					{
						String ele = stack.pop();
						//System.out.println(ele);
						while(ele.equals("(") == false)
						{
							output = output + ele;
							//System.out.println(output);
							ele = stack.pop();
							//System.out.println(ele);
						}
					}
					
					else //Standard operators cases
					{
						int check = -1;
						while(check == -1) 
						{
							if(stack.isEmpty())
							{
								break;
							}
							
							check = checkPres(line, stack.peek());
							//Incoming has higher precedence
							if(check == 1 || stack.peek().equals("("))
							{
								stack.push(line);
							}
						
							//Incoming has equal
							else if(check == 0)
							{
								output = output + stack.pop();
								//System.out.println(output);
								stack.push(line);
							}
						
							//Incoming has lower (then repeat)
							else
							{
								output = output + stack.pop();
								//System.out.println(output);
							}
						}
						if(stack.isEmpty())
						{
							stack.push(line);
						}
					}
				}
			}
			
			while(stack.isEmpty() == false)
			{
				output = output + stack.pop();
				//System.out.println(output);
			}
			System.out.println(output);
			
		}
		
		sc.close();
	}
}
