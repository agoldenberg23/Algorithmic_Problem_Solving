import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Problem_B
{
	//Disjoint set implementation from class resitiation - edited
	private static class UnionFind
	{
		//Variables for the set
	    public int[] parent;
	    public int[] depth;
	        
	    //Initialize the set
	    public UnionFind(int n)
	    {
	        this.parent = new int[n];
	        this.depth = new int[n];
	            
	        for(int i = 0; i < n; i++)
	        {
	            parent[i] = i;
	            depth[i] = 0;
	        }
	    }
	        
	    //Find the set a variable is a part of
	    public int findSet(int i)
	    {
	        if(parent[i] == i)
	        {
	            return i;
	        } 
	        else
	        {
	            int p = findSet(parent[i]);
	            depth[i] = depth[i] + depth[parent[i]];
	            parent[i] = p;
	            return p;
	        }
	    }
	     
	    //Get the depth
	    public int getDepth(int a)
	    {
	    	findSet(a);
	    	return depth[a];
	    }
	    
	    //Union two variables' sets
	    public void union(int i, int j)
	    {
	    	parent[i] = j;
	    	depth[i] = Math.abs(i - j) % 1000;
	    }
	}
	//End the disjoint set implamentation
		
	    
	public static void main(String[] args) throws IOException
	{
		BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out, "ASCII"), 4096);
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		
		int tests = 0;
		try{
			tests = Integer.parseInt(sc.readLine());
		}
		catch (Exception e){
			System.out.println("ERROR");
		}
		
		for(int test = 0; test < tests; test++)
		{
			int n = 0;
			try{
				n = Integer.parseInt(sc.readLine());
			}
			catch (Exception e){
				System.out.println("ERROR");
			}
			
			UnionFind uf = new UnionFind(n+10);
			
			while(true)
			{
				String line = sc.readLine().trim();
				if(line.equals("O"))
				{
					break;
				}
				
				String[] split = line.split(" ");
				
				if(split[0].equals("I"))
				{
					try{
					uf.union(Integer.parseInt(split[1]), Integer.parseInt(split[2]));
					}
					catch (Exception e){
						System.out.println("ERROR");
					}
				}
				else if(split[0].equals("E"))
				{
					try{
					output.write(Integer.toString(uf.getDepth(Integer.parseInt(split[1]))) + "\n");
					}
					catch (Exception e){
						System.out.println("ERROR");
					}
				}
				else
				{
					System.out.println("ERROR");
				}
			}
		}
			
		sc.close();
		output.close();
	}
}
