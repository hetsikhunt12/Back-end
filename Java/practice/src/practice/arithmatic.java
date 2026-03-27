package practice;

import java.util.Scanner;

public class arithmatic {
    public static void main(String[] args) {
    	Scanner sc=new Scanner(System.in);
		int a=10, b=5;
		
		System.out.println("Enter value a: ");
		a=sc.nextInt();
		
		System.out.println("Enter value b: ");
		b=sc.nextInt();
		
		System.out.println("addition: "+(a+b));
		System.out.println("subtraction: "+(a-b));
		System.out.println("Multiplication: "+(a*b));
		System.out.println("Divison: "+(a/b));
		
		sc.close();
	}
}

