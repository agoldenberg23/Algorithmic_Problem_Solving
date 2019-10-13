import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Problem_D
{
	/*Deravation:
		(L/2)^2 + (r - wanted)^2 = rad^2 	-> 		rad = (L^2 /8 wanted + wanted/2)
		sin(ang) = o/h and arc = ang*rad	->		arc = 2 * r * sin^-1(o/h)*/
	
	//Rounds a double
	public static double round(double value, int places)
	{
	    BigDecimal bd = new BigDecimal(value);
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}
	
	public static void main(String[] args) throws IOException
	{
		BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out, "ASCII"), 4096);
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
	
		while(true)
		{
			String[] split = sc.readLine().split(" ");
			double initLen = Double.parseDouble(split[0]);
			double tempDif = Double.parseDouble(split[1]);
			double coef = Double.parseDouble(split[2]);
			
			if(initLen < 0 && tempDif < 0 && coef < 0)
			{
				break;
			}
			
			double newLen = (1 + (tempDif * coef)) * initLen;
			//System.out.println("NewLen=" + newLen);
			
			//run bisection
			double low = 0.0;
			double high = 0.5 * initLen;
			double mid = 0.0;
			double error = 1e-6;	//Error is arbitrary
			int iter = 0;
			
			while(high - low > error && iter < 1000000)
			{
				iter++;
				mid = (high + low)/2;
				double rad = (((initLen * initLen)/(mid * 8)) + ((mid)/(2)));
				
				if(2 * rad * Math.asin(initLen / (2 * rad)) < newLen)
				{
					low = mid;
				}
				else
				{
					high = mid;
				}
			}
			
			//Round to the 3rd decimal
			mid = round(mid, 3);
			
			output.write(String.format("%.3f", mid) + "\n");
		}
		
		sc.close();
		output.close();
	}
}
