#include <iostream>

using namespace std;

int main()
{
    /*unsigned int num;
    unsigned int low;
    unsigned int high;
    unsigned int maskBest = 0;
    unsigned int mask = 0;
    int readNum;*/
    
    long num;
    long low;
    long high;
    long maskBest = 0;
    long mask = 0;
    int readNum;
    
    while(true)
    {
        //Read the input
        readNum = scanf("%ld %ld %ld", &num, &low, &high);
        
        //Break if no more input
        if(readNum < 3)
        {
            break;
        }
        
        //Reset variables
        maskBest = 0;
        mask = 0;
        
        //printf("%ld %ld %ld \n", num, low, high);
        
        //i = potental bit to set
        for(long i = 1ll << 31; i > 0; i = i >> 1)
        {
            mask = maskBest | i;
            //printf("bit i = %ld \n", i);
            //Add the bit: Still less then the low, (less then high and the bit in num is not set)
            if((mask <= low) || ((mask <= high) && (~num & i)))
            {
                maskBest = mask;
                //printf("Seting bit, mask now is %ld \n", maskBest);
            }
        }
        
        printf("%ld\n", maskBest);
    }
   
    return 0;
}
