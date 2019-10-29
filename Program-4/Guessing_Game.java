/* ====================================================================
 * @author: Yujin Yoshimura
 * CMPS 4143 Contemporary Programming Language: Java-Python
 * Dr. Tina Johnson
 * Program 4
 * 
 * This program allows the user to play the number "guessing game".
 * The program randomly generates a number between 1 - 100 inclusive.
 * The user is asked to guess the number until they get it right.
 * Each time an incorrect answer is submitted, a count is incremented
 * and the user is instructed to guess "higher" or "lower".
 * When the correct number is entered, the program displays the number
 * of tries it took to guess the right answer.
 * ================================================================= */

package guessing_game;

import java.util.Scanner;

public class Guessing_Game {
	
	static int min = 1;
	static int max = 100;

	/* main
	 * @param: String[]
	 * @return: void
	 * Main function.
	 */
	public static void main (String[] args) {
		Scanner keyboard = new Scanner(System.in);
		int hidden = 0;
		int guess = 0;
		int count = 0;
		boolean found = false;

		header();
		hidden = initiate();
		while (!found) {
			guess = submit(keyboard);
			found = is_found(hidden, guess);
			count++;
		};
		result(hidden, count);
		
		keyboard.close();
	}
	
	/* header
	 * @param: void
	 * @return: void
	 * Prints program description.
	 */
	public static void header () {
		System.out.println("====================================================================");
		System.out.println("This program allows the user to play the number \"guessing game\".");
		System.out.format("The program randomly generates a number between %d - %d inclusive.\n", min, max);
		System.out.println("The user is asked to guess the number until they get it right.");
		System.out.println("Each time an incorrect answer is submitted, a count is incremented");
		System.out.println("and the user is instructed to guess \"higher\" or \"lower\".");
		System.out.println("When the correct number is entered, the program displays the number");
		System.out.println("of tries it took to guess the right answer.");
		System.out.println("====================================================================");
	}
	
	/* initiate
	 * @param: void
	 * @return: int
	 * Generates a random number between min and max.
	 * Then prints message for user to guess..
	 */
	public static int initiate() {
		int hidden = (int)(Math.random() * (max - min + 1) + min);
		System.out.format("Guess an integer between %d - %d:\n", min, max);		
		return hidden;
	}

	/* submit
	 * @param: Scanner
	 * @return: int
	 * Asks user to enter an integer between min and max.
	 * When user inputs invalid entry, prints error message.
	 */
	public static int submit(Scanner keyboard) {
		int guess = 0;
		boolean valid = false;

		while (!valid) {
			try {
				guess = keyboard.nextInt();
				if (guess < min || guess > max) {
					// Error message
					System.out.println("Out of range: Please enter a valid input!");
				}
				else {
					valid = true;
				}
			}
			catch (Exception e) {
				// Error message
				System.out.println("Not an integer: Please enter a valid input!");
				keyboard.next();
			}
		};
		
		return guess;
	}

	/* is_found
	 * @param: int, int
	 * @return: boolean
	 * Compares user's guess with program-generated number.
	 * When it is incorrect, instructs user to guess higher or lower.
	 * When it is correct, returns true.
	 */
	public static boolean is_found (int hidden, int guess) {
		if (guess < hidden) {
			System.out.println("Higher");
			return false;
		}
		else if (guess > hidden) {
			System.out.println("Lower");
			return false;
		}
		else {
			return true;
		}		
	}
		
	/* result
	 * @param: int, int
	 * @return: void
	 * Prints result.
	 */
	public static void result(int hidden, int count) {
		String plural = (count > 1)? "s" : "";
		System.out.format("Yay! You made it! The correct number was %d!\n", hidden);
		System.out.format("You have guessed %d time%s.", count, plural);
	}
	
}

