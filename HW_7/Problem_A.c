#include<iostream>

using namespace std;

bool found = false;
int used[20];

void DFS(int sum, int total, int numbers, int num[], int pos, int usedPos)
{
    //Base went over the sum
    if(sum > total)
    {
        return;
    }
    
    //Base went past the numbers array length
    if(pos > numbers)
    {
        return;
    }
    
    //Found a sum that works
    if(sum == total)
    {
        found = true;
        //Print the summation found
        printf("%d", used[0]);
        for(int i = 1; i < usedPos; i++)
        {
            printf("+%d", used[i]);
        }
        printf("\n");
        return;
    }
    
    //Try adding the number to the sum
    used[usedPos] = num[pos];
    DFS(sum + used[usedPos], total, numbers, num, pos+1, usedPos+1);
    
    while(pos+1 <= numbers && num[pos] == num[pos+1])
    {
        pos++;
    }
    
    //Try going to the next number without adding
    DFS(sum, total, numbers, num, pos+1, usedPos);
}

int main()
{
    int total;
    int numbers;
    while(scanf("%d %d", &total, &numbers))
    {
        //printf("Total = %d, Numbers = %d\n", total, numbers);
        
        if(numbers == 0)
        {
            break;
        }
        
        int num[20];
        
        for(int i = 0; i < numbers; i++)
        {
            scanf("%d", &num[i]);
            //printf("%d ", num[i]);
        }
        //printf("\n");
        
        printf("Sums of %d:\n", total);
        
        found = false;
        
        DFS(0, total, numbers, num, 0, 0);
        
        if(found == false)
        {
            printf("NONE\n");
        }
    }
}
