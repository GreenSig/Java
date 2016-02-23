package vosters.Dnaalignment;

import java.util.Map;

public class DNAMatcher {
	public Map<String, Integer> matchCost;
	public int numspaces1 = 0;
	public int numspaces2 = 0;

	public DNAMatcher(Map<String, Integer> matchCost) {
		this.matchCost = matchCost;
	}

	public LocalAlignment findLocalAlignment(String string2, String string1) {
		// makes sure the longest string is always the Y axis
		if (string2.length() < string1.length()) {
			String temp = string2;
			string2 = string1;
			string1 = temp;
		}
		int[][] table = new int[string1.length() + 1][string2.length() + 1];
		table[0][0] = 0;
		// sets the first row to 0
		for (int i = 1; i < string1.length(); i++) {
			table[i][0] = 0;
		}
		for (int i = 1; i < string2.length(); i++) {
			table[0][i] = 0;
		}
		table = computeOptimalAlignmentTable(table, string1, string2);
		LocalAlignment newL = generateAlignmentFromTable(table, string1,
				string2);
		System.out.println();
		return newL;
	}

	private int[][] computeOptimalAlignmentTable(int[][] table, String stringX,
			String stringY) {
		stringX = " " + stringX;
		stringY = " " + stringY;
		// loop thru all the rows
		for (int x = 1; x < stringX.length(); x++) {
			// loop thru all the columns
			for (int y = 1; y < stringY.length(); y++) {
				// check to see if the char's are equal
				if (stringX.charAt(x) == stringY.charAt(y)) {
					// if they are, add the weight of the character
					// to the top-left value
					// first find the match cost by finding the letter
					int matchCostNew = matchCost.get(stringX.charAt(x) + ""
							+ stringY.charAt(y));
					table[x][y] = table[x - 1][y - 1] + matchCostNew;
				} else {
					// if the are not the same, find the max
					System.out.println(x + " " + y);
					System.out.println((stringX.charAt(x - 1) + "" + stringY
							.charAt(y - 1)));
					// if on the first iteration
					int maxvalue = 0;
					if (x == 1 && y == 1) {
						maxvalue = Math.max(table[x - 1][y], table[x][y - 1]) - 1;
					} else {
						maxvalue = Math.max(table[x - 1][y - 1]
								+ matchCost.get(stringX.charAt(x) + ""
										+ stringY.charAt(y)), Math.max(
								table[x - 1][y], table[x][y - 1]) - 1);
					}
					maxvalue = Math.max(maxvalue, 0);
					table[x][y] = maxvalue;
				}
			}
		}
		return table;
	}

	// This method will take in the table, along with the strings,
	// and will loop thru the table backwards, finding the best cost
	private LocalAlignment generateAlignmentFromTable(int[][] table,
			String stringX, String stringY) {
		// find the max value
		int maxStart = 0;
		int[] maxIndex = new int[2];
		// loop thru the x's and y's
		for (int i = 1; i < stringX.length() + 1; i++) {
			if (table[i][stringY.length()] > maxStart) {
				maxIndex[0] = i;
				maxIndex[1] = stringY.length();
				maxStart = table[maxIndex[0]][maxIndex[1]];
			}

		}
		for (int i = 1; i < stringY.length() + 1; i++) {
			if (table[stringX.length()][i] > maxStart) {
				maxStart = table[stringX.length()][i];
				maxIndex[0] = stringX.length();
				maxIndex[1] = i;
			}
		}
		// sets first characters
		// stringX.charAt(maxIndex[0] - 1) + ""
		// stringY.charAt(maxIndex[1] - 1) + ""
		String newInputX = "";
		String newInputY = "";

		String[] newStrings = OptimalCost(stringX, stringY, table, maxIndex,
				newInputX, newInputY);
		// get the length of the strings
		int matchLength = newStrings[0].length();
		// adds the rest of the end to the strings
		int numAddedEndX = 0;
		int numAddedEndY = 0;
		int s1s = 0;
		int s2s = 0;
		if (maxIndex[0] > 1
				&& newStrings[0].charAt(newStrings[0].length() - 1) != stringX
						.charAt(stringX.length() - 1)) {
			numAddedEndX = newStrings[0].length();
			
			newStrings[0] = stringX.substring(maxIndex[0]
					+ Integer.parseInt(newStrings[2]) - 1);
			numAddedEndX = newStrings[0].length() - numAddedEndX;
			
		}
		if (maxIndex[1] > 1
				&& newStrings[1].charAt(newStrings[1].length() - 1) != stringY
						.charAt(stringY.length() - 1)) {
			numAddedEndY  = newStrings[1].length();
			newStrings[1] = stringY.substring(maxIndex[1]
					+ Integer.parseInt(newStrings[3]) - 1);
			numAddedEndY = newStrings[1].length() - numAddedEndY;
		}
		// adds the rest to the front of the strings
		if (newStrings[0].length() < stringX.length() - numAddedEndX
				+ Integer.parseInt(newStrings[2])) {
			s1s = stringX.length()+ Integer.parseInt(newStrings[2]) - newStrings[0].length();
			newStrings[0] = stringX
					.substring(0, (stringX.length()
							+ Integer.parseInt(newStrings[2]) - newStrings[0]
							.length())) + newStrings[0];
			
			System.out.println(stringX.length()+ Integer.parseInt(newStrings[2]) - newStrings[0].length());

		}
		if (newStrings[1].length() < stringY.length()  - numAddedEndY
				+ Integer.parseInt(newStrings[3])) {
			s2s = stringY.length()+ Integer.parseInt(newStrings[3]) - newStrings[1].length();
			newStrings[1] = stringY
					.substring(0, (stringY.length()
							+ Integer.parseInt(newStrings[3]) - newStrings[1]
							.length())) + newStrings[1];
			
		}
		// adds the rest of the spaces to the front
		if (newStrings[0].length() < newStrings[1].length()) {
			
			
			while (newStrings[0].length() - numAddedEndX < newStrings[1].length() - numAddedEndY) {
				newStrings[0] = " " + newStrings[0];
				s1s++;
			}
		} else {
		
		
			while (newStrings[1].length() - numAddedEndY < newStrings[0].length() - numAddedEndX) {
				newStrings[1] = " " + newStrings[1];
				s2s++;
			}
		}

		LocalAlignment alignment = new LocalAlignment(newStrings[0], newStrings[1],
				s1s, s2s, matchLength);
		return alignment;

	}

	public String[] OptimalCost(String stringX, String stringY, int[][] table,
			int[] indexes, String newInputX, String newInputY) {
		String[] newString = new String[5];
		
		
		// if the loop should continue
		if (indexes[0] > 0 && indexes[1] > 0) {
			// find the max of the left, top, or top-left
			int max = Math.max(table[indexes[0] - 1][indexes[1] - 1], Math.max(
					table[indexes[0] - 1][indexes[1]],
					table[indexes[0]][indexes[1] - 1]));
			// if the top left is 0
			if (table[indexes[0] - 1][indexes[1] - 1] == 0) {
				newInputX = stringX.charAt(indexes[0] - 1) + newInputX;
				newInputY = stringY.charAt(indexes[1] - 1) + newInputY;
				newString[0] = newInputX;
				newString[1] = newInputY;
				newString[2] = Integer.toString(numspaces1);
				newString[3] = Integer.toString(numspaces2);
			} else {

				System.out.println(table[indexes[0] - 1][indexes[1] - 1]);
				// if max is Top-left
				if (max == table[indexes[0] - 1][indexes[1] - 1]) {
					indexes[0] = indexes[0] - 1;
					indexes[1] = indexes[1] - 1;
					newInputX = stringX.charAt(indexes[0]) + newInputX;
					newInputY = stringY.charAt(indexes[1]) + newInputY;
					newString = OptimalCost(stringX, stringY, table, indexes,
							newInputX, newInputY);
					// if max is top
				} else if (max == table[indexes[0]][indexes[1] - 1]) {
					indexes[1] = indexes[1] - 1;
					newInputY = stringY.charAt(indexes[1]) + "" + newInputY;
					newInputX = "-" + newInputX;
					numspaces1++;
					newString = OptimalCost(stringX, stringY, table, indexes,
							newInputX, newInputY);
					// if max is left
				} else if (max == table[indexes[0] - 1][indexes[1]]) {
					indexes[0] = indexes[0] - 1;
					newInputY = "-" + newInputY;
					numspaces2++;
					newString = OptimalCost(stringX, stringY, table, indexes,
							newInputX, newInputY);
					// if max is top
				}
			}
		}

		// then pass back the new strings
		return newString;
	}
}
