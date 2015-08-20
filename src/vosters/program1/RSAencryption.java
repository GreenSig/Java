package vosters.program1;


/** ********************************************************************* */
/* This program gets input from a user and generates a public and private */
/* key based on the unicode of the input. It then displays the encrypted  */
/* message, decrypts it, and prints it again.                             */
/* Created by Jacob Vosters                                               */
/* Date last modified:  7/31/2015                                         */
/************************************************************************ */

public class RSAencryption {

	public static void main(String[] args) {
		//*********************************************************************
		// Find P and Q
		//*********************************************************************
		// Generate 2 random numbers, P and Q with the following properties:
		// 1. P and Q can be at most 7 bits long
		// 2. P and Q cannot be 0 or 1
		// --> The above 2 constraints imply that P and Q are in the
		// range [2..128)
		// 3. P and Q must be primes
		// 4. P and Q cannot be the same number
		// 5. (PQ) must be at least 256

		int P = 0;
		int Q = 0;
		int PQ = 0;

		while(PQ < 255 || P == Q){ //make sure P*Q > 255 and P doesn't equal Q

			boolean isPprime = false;
			boolean isQprime = false;

			while(isPprime == false || isQprime == false){ //gets P and Q as both prime numbers

				P = randomRange(2, 128);
				Q = randomRange(2, 128);

				isPprime = isPrime(P);
				isQprime = isPrime(Q);
			}
			PQ = P * Q;
		}

		int phiPQ = (P-1) * (Q-1);

		System.out.println("P: " + P + ", Q: " + Q + ", P*Q=" + PQ + ", phiPQ=" + phiPQ);

		//*********************************************************************
		// Find E
		//*********************************************************************
		// Generate an E with the following properties:
		// 1. 1 < E < phiPQ
		// 2. E and phiPQ are relatively prime
		// --> the above constraint implies that gcd(E, phiPQ) == 1
		// 3. There may be several candidates for E, so pick the smallest one
		// (for consistency with the rest of the class -- there is no
		// theoretical reason for this constraint)
		int E = 1;
		boolean isRelPrime = false;
		while(isRelPrime == false){
			E++;
			isRelPrime = relativelyPrime(E, phiPQ);
		}
		System.out.println("E: " + E);

		//*********************************************************************
		// Find D
		//*********************************************************************
		// Generate D with the following properties:
		// 1. 0 < D <= PQ
		// 2. (DE-1) is evenly divisible by phiPQ
		int D = 1;
		while((D*E - 1) % phiPQ != 0){
			D++;
		}
		System.out.println("D: " + D);



		//Get the message from the user
		String message = "This is an encrypted message";
		int messagelen = message.length();
		int[] M = new int[messagelen];

		for(int i = 0; i < messagelen;i++){
			M[i] = message.charAt(i);
			System.out.println("M[" + i + "]=" + message.charAt(i));
		}

		int[] C = new int[messagelen];

		//Encrypting the message
		for(int i = 0; i < messagelen; i++){
			int Etemp = E;
			C[i] = 1;
			while(Etemp > 0){
				if(Etemp%2 == 1){ //if E is odd
					C[i] = (C[i] * M[i]) % PQ;
				}
				Etemp = Etemp / 2;
				M[i] = (int) Math.pow(M[i], 2) % PQ;

			}

		}
		message = "";
		for(int i = 0; i < messagelen;i++){
			message += M[i] + ",";			
		}

		System.out.println("encrypted: " + message);

		//Decrypting the message
		for(int i = 0; i < messagelen; i++){
			int Dtemp = D;
			M[i] = 1;
			while(Dtemp > 0){
				if(Dtemp%2 == 1){ //if E is odd
					M[i] = (M[i] * C[i]) % PQ;
				}
				Dtemp = Dtemp / 2;
				C[i] = (int) Math.pow(C[i], 2) % PQ;

			}

		}
		message = "";
		for(int i = 0; i < messagelen;i++){
			message += (char)M[i];
		}
		System.out.println("decrypted: " + message);
	}




	private static int randomRange(int min, int max)
	{
		int range = (max - min) + 1;     
		return (int)(Math.random() * range) + min;
	}


	private static boolean isPrime(int n) {
		//check if n is a multiple of 2
		if (n %  2== 0) return false;

		//if not, then just check the odds up to the square root of n
		for(int i = 3;i * i <= n;i += 2) {
			if(n % i == 0)
				return false;
		}
		return true;
	}

	private static int gcd(int a, int b) { //Euclid's Algorithm
		int t;
		while(b != 0){
			t = a;
			a = b;
			b = t%b;
		}
		return a;
	}

	private static boolean relativelyPrime(int a, int b) {
		return gcd(a,b) == 1;
	}
}
