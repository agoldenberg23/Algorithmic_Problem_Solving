import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Problem_5
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
	
		int testNum = sc.nextInt();
		
		for(int tests = 0; tests < testNum; tests++)
		{
			int jobNum = sc.nextInt();
			int ourPos = sc.nextInt();
			
			Queue<Integer> q = new LinkedList<>();
			int[] priNum = new int[10];
			int time = 0;
			
			//Read in the jobs and put them in the que
			for(int i = 0; i < jobNum; i++)
			{
				int a = sc.nextInt();
				priNum[a]++;
				q.add(a);
			}
			
			//Handle each priority in order
			runTest: for(int pri = 9; pri > 0; pri--)
			{
				while(priNum[pri] != 0)
				{
					int a = q.poll();
					ourPos--;
					
					if(a != pri)
					{
						q.add(a);
						
						if(ourPos == -1)
						{
							ourPos = jobNum-1;
						}
					}
					else
					{
						jobNum--;
						priNum[pri]--;
						time++;
						
						if(ourPos == -1)
						{
							break runTest;
						}
					}
				}
			}
			
			System.out.println(time);
		}
		
		sc.close();
	}
}
