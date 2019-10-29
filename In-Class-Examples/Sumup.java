/*
 * Yugin Yoshimura
 * 
 * This program will demonstrate IO and control
 * statements in Java.
 * 
 */

package sumup;
import java.util.Scanner;
import java.util.*;


public class Sumup {
	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Please enter a number.");
		int x = keyboard.nextInt();
		//int ans = (x * (x+1))/2;
		int ans = 0;
		for(int i = 1 ; i <= x; i++) {
			ans = ans + i; 
		}
		System.out.println("The sum of 1 through " + x + " is " + ans + ".");
		
		String sign;
		if(x > 0) {
			sign = "positive";
		}
		else if(x < 0) {
			sign = "negative";
		}
		else {
			sign = "zero";
		}
		
		String parity;
		if(x%2 == 0) {
			parity = "even";
		}
		else if(x%2 == 1) {
			parity = "odd";
		}
		else {
			parity = "neither";
		}
		int y;
		for(int i = 1;i <= x; i++) {
			y = (int) Math.pow(i, 2);
			System.out.println(i +" squared is "+y+".");
		}

		System.out.println(x + " is a " + sign + " " + parity + " number.");

		keyboard.close();
	}

}
