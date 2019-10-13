#include<iostream>

using namespace std;

int main()
{
    int testNum;
    scanf("%d", &testNum);
    int rungHight[100010];
    int diff[100010];

    for(int test = 0; test < testNum; test++)
    {
        int rungNum;
        scanf("%d", &rungNum);
        int maxDiff = -1;
        
        //Enter the first rung maualy
        scanf("%d", &rungHight[0]);
        diff[0] = rungHight[0];
        //printf("%d,0 ", rungHight[0]);
        
        //Read in the latter rungs
        for(int i = 1; i < rungNum; i++)
        {
            scanf("%d", &rungHight[i]);
            diff[i] = rungHight[i] - rungHight[i-1];
            
            if(diff[i] > maxDiff)
            {
                maxDiff = diff[i];
            }
            
            //printf("%d,%d ", rungHight[i], diff[i]);
        }
        //printf("maxDiff=%d\n", maxDiff);
        
        int maxCoppy = maxDiff;
        bool failedJump = false;
        for(int i = 0; i < rungNum; i++)
        {
            if(maxDiff == diff[i])
            {
                maxDiff--;
            }
            else if(diff[i] > maxDiff)
            {
                failedJump = true;
                break;
            }
        }
        
        if(failedJump == true)
        {
            printf("Case %d: %d\n", (test+1), (maxCoppy+1));
        }
        else
        {
            printf("Case %d: %d\n", (test+1), (maxCoppy));
        }
    }
}
