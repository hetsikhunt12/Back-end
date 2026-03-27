package practice;

import java.util.Scanner;

public class switchcase_2 {
    public static void main(String[] args) {
		int a,b,ans,choice;
		boolean flag=true;
		Scanner sc=new Scanner(System.in);
		
		System.out.println("Enter A: ");
		a=sc.nextInt();
		
		System.out.println("Enter B: ");
		b=sc.nextInt();
		
		while(flag)
		{
			System.out.println("**********************");
			System.out.println("1.addition");
			System.out.println("2.subtraction");
			System.out.println("3.multiplication");
			System.out.println("4.division");
			System.out.println("5.exit");
			System.out.println("**********************");
			System.out.println("Enter choice: ");
			choice=sc.nextInt();
			
			switch(choice)
			{
			case 1:
				ans=a+b;
				System.out.println("addition: "+ans);
				break;
			case 2:
				ans=a-b;
				System.out.println("subtraction: "+ans);
				break;
			case 3:
				ans=a*b;
				System.out.println("multiplication: "+ans);
				break;
			case 4:
				ans=a/b;
				System.out.println("division: "+ans);
				break;
			case 5:
				System.out.println("thank you for serviceing");
				flag=false;
				break;
				
				default:
					System.out.println("invalid choice");
					break;
			}
		
		}
		sc.close();
	}
}
