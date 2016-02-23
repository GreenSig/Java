package vosters.BarcodeScanner;

/** ******************************************************************** */
/* Scan in a barcode, determine if it is upside down, convert it to      */
/* decimal, and check it against the checksum value                      */
/* Created by Jacob Vosters                                              */
/* Date last modified:  8/20/2015                                        */
/*********************************************************************** */

public class UPC {

	public static void main(String[] args) {
		
		//*********************************************************************
		// Get the Image
		//*********************************************************************
		UWECImage barCodeImage = new UWECImage("barcode1.png");
		int imageH = barCodeImage.getHeight();

		int[] scannedBarcode = new int[100]; //1 for black, 0 for white


		//*********************************************************************
		// Scan the barcode
		//*********************************************************************
		for(int i = 3; i < 100; i++){ //start at line 3 because there are 3 lines of white padding, same with at the end
			if(barCodeImage.getRed(i*2, imageH/2) == 0){
				scannedBarcode[i-3] = 1;
			} else {
				scannedBarcode[i-3] = 0;
			}			
		}

		//*********************************************************************
		// Print the barcode
		//*********************************************************************
		String barcode = "";
		for(int i = 0; i < 95; i++){
			barcode += scannedBarcode[i];
		}
		System.out.println(barcode);


		//*********************************************************************
		// Check to see if the barcode is upside down
		//*********************************************************************
		int[][] numPattern = {{0,0,0,1,1,0,1},
				{0,0,1,1,0,0,1},
				{0,0,1,0,0,1,1},
				{0,1,1,1,1,0,1},
				{0,1,0,0,0,1,1},
				{0,1,1,0,0,0,1},
				{0,1,0,1,1,1,1},
				{0,1,1,1,0,1,1},
				{0,1,1,0,1,1,1},
				{0,0,0,1,0,1,1}};

		final int NUM_PATTERNS = 10;
		final int NUM_PAT_BITS = 7;

		boolean isReversed = true; //to see if the barcode is flipped upside down

		for(int j = 0; j < NUM_PATTERNS; j++){ //loop thru the patterns
			for(int k = 0; k < NUM_PAT_BITS; k++){ //loop thru all the bits in the pattern
				if(!(scannedBarcode[k+3] == numPattern[j][k])){
					break;
				} else {
					if(k == NUM_PAT_BITS - 1){
						isReversed = false;
					}
				}
			}
		}

		System.out.println("Is the barcode upside down? " + isReversed);

		//*********************************************************************
		// Turn the scan into numbers
		//*********************************************************************

		int[] convertedBarcode = new int[12]; 
		for(int n = 0; n < 12; n++){ //which number you are converting
			for(int j = 0; j < NUM_PATTERNS; j++){ //loop thru the patterns
				for(int k = 0; k < NUM_PAT_BITS; k++){ //loop thru all the bits in the pattern

					if(n > 5){ //if we are on the second half of the barcode we need to add the 5 middle bits and use the other pattern
						if(!(scannedBarcode[n * NUM_PAT_BITS + 8 + k] != numPattern[j][k])){ //check the right hand pattern
							break;
						} else {
							if(k == NUM_PAT_BITS - 1){
								convertedBarcode[n] = j;
							}
						}

					} else {
						if(!(scannedBarcode[n * NUM_PAT_BITS + 3 + k] == numPattern[j][k])){ //check the left hand pattern
							break;
						} else {
							if(k == NUM_PAT_BITS - 1){
								convertedBarcode[n] = j;
							}
						}
					}
				}
			}
		}
		
		barcode = "";
		for(int n = 0; n < 12; n++){
			barcode += convertedBarcode[n] + " ";
		}
		
		System.out.println("The barcode converted is: " + barcode);
		
		
		//*********************************************************************
		// Find the CheckSum
		//*********************************************************************

		int checksum = (convertedBarcode[0] + convertedBarcode[2] + convertedBarcode[4] + convertedBarcode[6] + convertedBarcode[8] + 
				convertedBarcode[10]) * 3 + (convertedBarcode[1] + convertedBarcode[3] + convertedBarcode[5] + convertedBarcode[7] + convertedBarcode[9]);
		checksum = 10 - (checksum % 10);

		if(checksum == convertedBarcode[11]){
			System.out.println("The barcode passes the checksum with a value of: " + checksum);
		} else {
			System.out.println("The barcode fails the checksum with a value of: " + checksum);
		}


	}
}
