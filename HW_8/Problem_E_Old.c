#include<iostream>

using namespace std;

//Reverses the bits in a unsigned long long within bitNum
unsigned long long reverseBits(unsigned long long num, int bitNum) 
{ 
    unsigned long long ret = 0; 
    for (int i = 0; i < bitNum; i++) 
    { 
        if((num & (1 << i)))
        {
           ret |= 1 << ((bitNum - 1) - i);   
        }
    }
    return ret; 
} 

int main()
{
    int testNum;
    scanf("%d", &testNum);

    for(int test = 0; test < testNum; test++)
    {
        int ones;
        int zeros;
        scanf("%d %d", &ones, &zeros);
        int bitNum = ones + zeros;
        
        //printf("%d %d %d\n", ones, zeros, bitNum);
        
        unsigned long long bits = 0; //Bits to set
        int ind = 0;    //The indice to set next in bits
        
        //General structure
        while(ones >= 2 && zeros >= 1)
        {
            bits |= 5UL << ind;
            ind = ind + 3;
            ones = ones - 2;
            zeros = zeros - 1;
        }
        
        //Case where more zeros then ones or not enogh to make full 101
        if(ones == 1 && zeros >= 1)
        {
            bits |= 1UL << ind;
            ind = ind + 2;
            ones--;
            zeros--;
        }
        
        //Case of ones left only
        for(int i = 0; i < ones; i++)
        {
            bits |= 1UL << ind;
            ind++;
        }
        
        //Zeros left only handled by trailing bits (no effect)
        
        //printf("%llu\n", bits);
        unsigned long long bitsRev = reverseBits(bits, bitNum);
        bits = min(bits, bitsRev-1);
        
        printf("%llu\n", bits);
    }
}
