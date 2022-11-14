package backgammon;

import java.util.ArrayList;
import java.util.List;

public class MoveSet {

	private List<Move> moves = new ArrayList<Move>();

	
	// Single move constructor
	public MoveSet(Move m) {
		moves.add(m);
	}
	
	// Two move constructor
	public MoveSet(Move m1, Move m2) {
		moves.add(m1);
		moves.add(m2);
	}

	public boolean usesRoll(Integer roll) {
		boolean usesRoll = false;
		for(Move m: moves) {
			if(m.getRoll()==roll)
				usesRoll = true;
		}
		return usesRoll;
	}

	public int size() {
		return moves.size();
	}

	public Move getFirstMove() {
		return moves.get(0);
	}

}
