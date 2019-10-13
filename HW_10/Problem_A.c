#include<iostream>

using namespace std;

int main()
{
    //Fill in the posible combo list
    long long combos[30010];
    for(int i = 1; i < 30010; i++)
    {
        combos[i] = 0;
    }
    combos[0] = 1;
    
    //5c
    for(int i = 5; i <= 30000; i++)
    {
        combos[i] = combos[i] + combos[i - 5];
    }
    
    //10c
    for(int i = 10; i <= 30000; i++)
    {
        combos[i] = combos[i] + combos[i - 10];
    }
    
    //20c
    for(int i = 20; i <= 30000; i++)
    {
        combos[i] = combos[i] + combos[i - 20];
    }
    
    //50c
    for(int i = 50; i <= 30000; i++)
    {
        combos[i] = combos[i] + combos[i - 50];
    }
    
    //100c
    for(int i = 100; i <= 30000; i++)
    {
        combos[i] = combos[i] + combos[i - 100];
    }
    
    //200c
    for(int i = 200; i <= 30000; i++)
    {
        combos[i] = combos[i] + combos[i - 200];
    }
    
    //500c
    for(int i = 500; i <= 30000; i++)
    {
        combos[i] = combos[i] + combos[i - 500];
    }
    
    //1000c
    for(int i = 1000; i <= 30000; i++)
    {
        combos[i] = combos[i] + combos[i - 1000];
    }
    
    //2000c
    for(int i = 2000; i <= 30000; i++)
    {
        combos[i] = combos[i] + combos[i - 2000];
    }
    
    //5000c
    for(int i = 5000; i <= 30000; i++)
    {
        combos[i] = combos[i] + combos[i - 5000];
    }
    
    //10000c
    for(int i = 10000; i <= 30000; i++)
    {
        combos[i] = combos[i] + combos[i - 10000];
    }
    
    //Get the test cases
    while(true)
    {
        double input;
        scanf("%lf", &input);
        
        int test = (int) (input * 100 + 0.5);
        
        if(test == 0)
        {
            break;
        }
        
        //output formating taken from stack overfllow (looked up how to do so with the widths)
        printf("%6.2f%17lld\n", input, combos[test]);
    }
}
