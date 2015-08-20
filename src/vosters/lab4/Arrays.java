package vosters.lab4;

import java.util.Scanner;

public class Arrays {

	public static void main(String[] args) {

		int[] input = new int[10];
		int i = 0, j = 0;

		Scanner scan = new Scanner(System.in);

		//get the numbers from the console windows (up to 10)
		while(i != -1 && j < 10){
			i = scan.nextInt();
			if(i != -1){
				input[j] = i;
				j++;
			}
		}
		scan.close();

		//display the numbers entered
		i = 0;
		while(i < j){
			System.out.println("Number " + (i + 1) + ": " + input[i]);
			i++;
		}

		//make a copy of the array
		int[] copy = new int[j];		
		for(i=0; i < j; i++){
			copy[i] = input[i];
		}

		//Sort the numbers from smallest to largest
		for(i = 0; i < j; i++){
			int small = copy[i];
			int smallpos = i;
			for(int ii = i; ii < j; ii++){
				if(copy[ii] < small){
					smallpos = ii;
					small = copy[ii];
				}
			}
			int temp = copy[i];
			copy[i] = copy[smallpos];
			copy[smallpos] = temp;
		}

		//display the sorted list
		i = 0;
		while(i < j){
			System.out.println("Sorted " + (i + 1) + ": " + copy[i]);
			i++;
		}

	}

}
