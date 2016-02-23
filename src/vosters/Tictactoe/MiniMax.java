package vosters.Tictactoe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class MiniMax {
	int maxLevel = 2;
	double alpha;
	double beta;

	public MiniMax(int maxLevel){
		this.maxLevel = maxLevel;
	}
	public TwoPlayerGameBoard generateNextMove(TwoPlayerGameBoard currentProblem) {
		// make two arraylists with the children and correseponding costs
		ArrayList<Double> costs = new ArrayList<Double>();
		ArrayList<TwoPlayerGameBoard> children = new ArrayList<TwoPlayerGameBoard>();
		// loop thru, filling up the children arraylist
		while (currentProblem.hasMoreChildren()) {
			TwoPlayerGameBoard child = currentProblem.nextChild();
			children.add(child);
			alpha = Integer.MIN_VALUE;
			beta = Integer.MAX_VALUE;
			costs.add(this.recursiveMiniMaxAlphaBeta(child, 1, alpha, beta));
		}
		// get the min cost (-1, 0, 1)
		double maxCost = Collections.max(costs);
		
		ArrayList<Integer> maxs = new ArrayList<Integer>();
				
		
		for(int i = 0; i < costs.size(); i++){
			if(costs.get(i) == maxCost){
				maxs.add(i);
			}
		}
		Random rng = new Random();
		int rand = rng.nextInt(maxs.size());
		
		
		
		// return the child at the index of the minCost
		return children.get(maxs.get(rand));

	}
	
	
	private double recursiveMiniMaxAlphaBeta(TwoPlayerGameBoard currentProblem,
			int currentLevel, double alpha, double beta) {

		if (currentProblem.isComputerWinner() || currentProblem.isDraw()
				|| currentProblem.isUserWinner() || currentLevel == maxLevel) {
			if (currentProblem.isComputerWinner()) {
				return 1;
			}
			if (currentProblem.isDraw() || currentLevel == maxLevel) {
				return 0;
			}
			if (currentProblem.isUserWinner()) {
				return -1;
			}
		}
		// maximizing level
		if (currentLevel % 2 == 0) {
	
			while (currentProblem.hasMoreChildren() && alpha < beta) {
				double temp = recursiveMiniMaxAlphaBeta(currentProblem
						.nextChild(), currentLevel + 1, alpha, beta);
				if (temp > alpha) {
					alpha = temp;
				}
			}
			return alpha;
		}
		if (currentLevel % 2 == 1) {
			
			while (currentProblem.hasMoreChildren() && alpha < beta) {
				double temp = recursiveMiniMaxAlphaBeta(currentProblem
						.nextChild(), currentLevel + 1, alpha, beta);
				if (temp < beta) {
					beta = temp;
				}
				
			}
			return beta;
		}
		return -20;
	}
}
