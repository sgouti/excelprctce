package test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Test_class {

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
/*System.out.println("Hi sirsi");
int i=10;
int j=i++;
System.out.println(i=i);

int j1,k;
demo:
for(j1=0; j1<5; j1++)
{
	for(k=2;k<5;k++)
	{
		if(j1==3)
		{
		break demo;
		}
	System.out.print(j1 + ""+k +" ");
	}
	System.out.println("");
}

for(int i=1;i<=12;i++)
{
	if(fibonacci(i)>100)
		break;
System.out.print(fibonacci(i) + " ");
}
*/
		
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMMyyyy-hh_mm a");
		 
		 Date date = new Date();
		 String date1= dateFormat.format(date);
		// date1=date1.trim();
		 System.out.println(date1);
		 String path="E:\\\\sid"+ date1 + ".png";
		 System.out.println(path);
		
		
		
		
		
		
		System.out.println("Javatpoint" + 10 * 20);  
if(Isprime(18)==true)
System.out.println("prime");
else

{
	System.out.println(" not prime");}
if(amstrng(153)==true)
System.out.println("smastng");
else

{
	System.out.println(" not smastng");}


	}
	
	
	public static int fibonacci(int number){
        if(number == 1 || number == 2){
          // System.out.println(1+ " ");
        	return 1;
        }
      System.out.println();
       // System.out.println(fibonacci(number-1) + fibonacci(number -2));
        return fibonacci(number-1) + fibonacci(number -2); //tail recursion
        
    }

	public static boolean Isprime(int a)
	{
		if(a<=3 && a>0)
			return true;
		if(a>3)
		{
			for(int i=2;i<a;i++)
			{
				if(a%i==0)
					return true;
				
			}
		}
		return false;
	}
	static int asmtrn=0;
	public static boolean amstrng(int a)
	{
		int c=a;
		while(c>0)
		{
		int b=c%10;
		asmtrn=b*b*b+asmtrn;
		c=c/10;
		}
		if(a==asmtrn)
			return true;
		
		return false;
		
	}
}
