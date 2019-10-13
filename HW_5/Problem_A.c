#include <iostream>

using namespace std;

int main()
{
    int testNum;
    scanf("%d", &testNum);
    
    for(int test = 0; test < testNum; test++)
    {
        int n;
        scanf("%d", &n);
        
        int cur;
        int ans;
        
        for(int i = 0; i < n; i++)
        {
            scanf("%d", &cur);
            if(i == n/2)
            {
                ans = cur;
            }
        }
        
        printf("Case %d: %d\n", (test + 1), ans);
    }
   
    return 0;
}
