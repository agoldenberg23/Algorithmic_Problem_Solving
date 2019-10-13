#include <iostream>
#include <vector>
#include <map>

using namespace std;

//Modified disjoint set
class disSet
{
    public:
    //Variables used
    vector<int> parent;
    vector<int> rank;
    vector<int> setSize;
    vector<int> setMoney;
    
    //Initalize the set
    disSet(int size, int *mon)
    {
        //Init variables
        parent.assign(size, 0);
        rank.assign(size, 1);
        setSize.assign(size, 1);
        setMoney.assign(size, 0);

        for (int i = 0; i < size; i++)
        {
            parent[i] = i;
            setMoney[i] = mon[i];
        }
    }

    //find the parent set
    int findSet(int a)
    {
        //Is its own parent
        if(parent[a] == a)
        {
            return a;
        }
        
        int p = findSet(parent[a]);
        parent[a] = p;
        return  p;
    }

    //Check if two elements are part of the same set
    bool isSameSet(int a, int b)
    {
        return findSet(a) == findSet(b);
    }

    //Union two sets
    void unionSet(int a, int b)
    {
        int pA = findSet(a);
        int pB = findSet(b);
        
        //Not the same set
        if(pA != pB)
        {
            if(rank[pA] > rank[pB])
            {
                parent[pB] = pA;
                setSize[pA] += setSize[pB];
                setMoney[pA] += setMoney[pB];
            }
            else if(rank[pA] < rank[pB])
            {
                parent[pA] = pB;
                setSize[pB] += setSize[pA];
                setMoney[pB] += setMoney[pA];
            }
            else
            {
                parent[pA] = pB;
                setSize[pB] += setSize[pA];
                rank[pB] += 1;
                setMoney[pB] += setMoney[pA];
            }
        }
        //else do nothing
    }

    //Get the size of the set
    int sizeOfSet(int a)
    {
        return setSize[findSet(a)];
    }

    //Get the money total in the set
    int getMoney(int a)
    {
        return setMoney[findSet(a)];
    }
};  //End the disjoint set implamentation

int main()
{
    int tests;
    cin >> tests;
    int mon[10001];

    for(int test = 0; test < tests; test++)
    {
        int n;
        int m;
        cin >> n;
        cin >> m;

        //Read in the values
        for(int i = 0; i < n; i++)
        {
            cin >> mon[i];
        }
        
        //Create the set and fill with values
        disSet set(n, mon);

        //Union the friends
        for(int i = 0; i < m; i++)
        {
            int a;
            int b;
            cin >> a;
            cin >> b;
            
            set.unionSet(a, b);
        }

        //Check if the money is good
        bool moneyBal = true;
        for(int i = 0; i < n; i++)
        {
            if(set.getMoney(i) != 0)
            {
                moneyBal = false;
                break;
            }
        }

        if(moneyBal == true)
        {
            cout << "POSSIBLE" << endl;
        }
        else
        {    
            cout << "IMPOSSIBLE" << endl;
        }
            
    }
   
    return 0;
}
