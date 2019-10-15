#include<iostream>
#include <bits/stdc++.h>
#include <algorithm>

using namespace std;

int main()
{
    int testNum;
    scanf("%d", &testNum);

    for(int test = 0; test < testNum; test++)
    {
        //Get the input
        int price;
        int coinNum;
        scanf("%d  %d", &price, &coinNum);
        
        //printf("price= %d\n", price);
        
        int coin[coinNum + 10];
        for(int i = 0; i < coinNum; i++)
        {
            scanf("%d", &coin[i]);
        }
        sort(coin, coin + coinNum);
        
        //Print the coins for debuggin
        /*printf("Coins= ");
        for(int i = 0; i < coinNum; i++)
        {
            printf("%d ", coin[i]);
        }
        printf("\n");*/
        
        //Cons needed for each posible money array
        int monMax = 0;
        int mon[10010];
        mon[0] = 0;
        for(int i = 1; i < 10010; i++)
        {
            mon[i] = 10010;
        }
        
        //Fill the mon array
        //For each coin
        for(int i = 0; i < coinNum; i++)
        {
            //For each posible money value
            for(int c = monMax; c >= 0; c--)
            {
                //Dont keep filling foredver and keep in bounds
                if((c + coin[i]) < 10000)
                {
                    if(c + coin[i] > monMax)
                    {
                        monMax = c + coin[i];
                    }
                    mon[c + coin[i]] = std::min(mon[c + coin[i]], mon[c]+1);
                }
            }
        }
        
        //printf("At 500: %d, at 1500: %d, at 2000 %d\n", mon[500], mon[1500], mon[2000]);
        
        int monSolInd = -1;
        for(int i = price; i < 10000; i++)
        {
            if(mon[i] != 10010)
            {
                monSolInd = i;
                break;
            }
        }
        
        printf("%d %d\n", monSolInd, mon[monSolInd]);
    }
}
