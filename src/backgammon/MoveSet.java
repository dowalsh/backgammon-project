package backgammon;

import java.util.ArrayList;
import java.util.List;

public class MoveSet {

	private List<Move> moves = new ArrayList<Move>();

	/**
	 * Single move constructor
	 * 
	 * @param m the single move to put in the move set
	 */
	public MoveSet(Move m) {
		moves.add(m);
	}

	/**
	 * Two-move constructor
	 * 
	 * @param m1 the first move to put in the move set
	 * @param m2 the second move to put in the move set
	 */
	public MoveSet(Move m1, Move m2) {
		moves.add(m1);
		moves.add(m2);
	}

	/**
	 * Check if any of the moves in this moveSet contain a move that uses a
	 * particular dice roll
	 * 
	 * @param roll the roll to check is in this moveSet
	 * @return true if the roll is used by one of the moves in this moveSet
	 */
	public boolean usesRoll(Integer roll) {
		boolean usesRoll = false;
		for (Move m : moves) {
			if (m.getRoll() == roll)
				usesRoll = true;
		}
		return usesRoll;
	}
	
	/**
	 * get the number of moves in this moveSet
	 * 
	 * @return the number of moves in this moveSet
	 */
	public int size() {
		return moves.size();
	}

	/**
	 * get the first move in this moveSet
	 * 
	 * @return the first move in this moveSet
	 */
	public Move getFirstMove() {
		return moves.get(0);
	}

}
