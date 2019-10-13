import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

public class Problem_D
{
	public static void main(String[] args) throws IOException
	{
		Scanner sc = new Scanner(System.in);
		BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out, "ASCII"), 4096);
		
		int sinario = 0;
		
		while(true)
		{	
			int teamNum = sc.nextInt();
			
			if(teamNum == 0)
			{
				break;
			}
			
			sinario++;
			output.write("Scenario #" + Integer.toString(sinario) + "\n");
			
			//Fill in the teams
			HashMap<Integer, Integer> peopId = new HashMap<>();		//Holds pepole and id
			
			for(int i = 0; i < teamNum; i++)
			{
				int teamSize = sc.nextInt();
				
				for(int c = 0; c < teamSize; c++)
				{
					int a = sc.nextInt();
					peopId.put(a, i);
				}
			}
			
			//Holds the team id and the people inside the line
			HashMap<Integer, LinkedList<Integer>> teamId = new HashMap<Integer, LinkedList<Integer>>();
			
			for(int i = 0; i < teamNum; i++)	//Initalizing
			{
				teamId.put(i, new LinkedList<Integer>());
			}
			
			LinkedList<Integer> que = new LinkedList<Integer>();
			
			//Run the queue useage
			while(true)
			{
				String line = sc.next();
				
				if(line.equals("STOP"))
				{
					break;
				}
				else if(line.equals("ENQUEUE"))
				{
					int in = sc.nextInt();		//The person's id
					int inId = peopId.get(in);	//The team id of the person
					
					//There is noone of the team on line
					if(teamId.get(inId).isEmpty())
					{
						que.add(inId);
					}
					//Add them to the team line
					teamId.get(inId).add(in);
				}
				else if(line.equals("DEQUEUE"))
				{
					//Que's first element is an empty team line
					while(que.size() != 0 && teamId.get(que.get(0)).size() == 0)
					{
						que.removeFirst();
					}
					
					//If not empty, return the first person of the first team in the que
					if(que.size() != 0)
					{
						output.write(Integer.toString(teamId.get(que.get(0)).removeFirst()) + "\n");
					}
				}
				else
				{
					System.out.println("ERROR - Non Reconized Input: " + line);
				}
			}
			
			output.write("\n");
			
		}
		
		output.close();
		sc.close();
	}
}
