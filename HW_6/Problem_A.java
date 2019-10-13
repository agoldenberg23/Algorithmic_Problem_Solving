import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Problem_A
{
	//Disjoint set implementation from class resitiation (formatting edited)
    private static class UnionFind
    {
    	//Variables for the set
        public int[] parent;
        public int[] rank;
        public int[] size;
        
        //Initialize the set
        public UnionFind(int n)
        {
            this.parent = new int[n];
            this.rank = new int[n];
            this.size = new int[n];
            
            for(int i = 0; i < n; i++)
            {
                parent[i] = i;
                rank[i] = 1;
                size[i] = 1;
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
                parent[i] = p;
                return p;
            }
        }
        
        //Check if two variables are part of the same set
        public boolean isSameSet(int i, int j)
        {
            return findSet(i) == findSet(j);
        }
        
        //Union two variables' sets
        public void union(int i, int j)
        {
            int p_i = findSet(i);
            int p_j = findSet(j);
            
            if(p_i != p_j)
            {
                if(rank[p_i] > rank[p_j])
                {
                    parent[p_j] = p_i;
                    size[p_i] += size[p_j];
                }
                else if(rank[p_i] < rank[p_j])
                {
                    parent[p_i] = p_j;
                    size[p_j] += size[p_i];
                }
                else
                {
                    parent[p_i] = p_j;
                    size[p_j] += size[p_i];
                    rank[p_j] += 1;
                }
            }
            //else do nothing
        }
    }
	//End the disjoint set implamentation
	
    
	public static void main(String[] args) throws IOException
	{
		BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out, "ASCII"), 4096);
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		
		int testCases = Integer.parseInt(sc.readLine());
		
		for(int tests = 0; tests < testCases; tests++)
		{
			//Read in the input variables
			String[] split = sc.readLine().split(" ");
			int pepol = Integer.parseInt(split[0]);
			int frends = Integer.parseInt(split[1]);
			
	        UnionFind uf = new UnionFind(pepol+1);
	        
	        //Go through all of the friends
	        for(int fren = 0; fren < frends; fren++)
	        {
	        	split = sc.readLine().split(" ");
	        	//The two friends, a and b
	        	int a = Integer.parseInt(split[0]);
	        	int b = Integer.parseInt(split[1]);
	        	
	        	uf.union(a, b);
	        }
	        
	        //find the largest
	        int largest = 0;
	        for(int fren = 1; fren < pepol+1; fren++)
	        {
	        	int size = uf.size[fren];
	        	
	        	//System.out.println("Size = " + size + " for person " + fren + " part of " + uf.findSet(fren));
	        	
	        	if(size > largest)
	        	{
	        		largest = size;
	        	}
	        }
	        
	        //output
	        //System.out.println(largest);
	        output.write(Integer.toString(largest) + "\n");
		}
		
		sc.close();
		output.close();
	}
}
