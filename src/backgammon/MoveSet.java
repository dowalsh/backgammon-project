package backgammon;

import java.util.ArrayList;
import java.util.List;

public class MoveSet {

	private List<Move> moves = new ArrayList<Move>();
	
	
	// Single move constructor
	public MoveSet(Move m) {
		moves.add(m);
	}
	
	//Two move constructor
	public void addToStart(Move m) {
		moves.add(0, m);
	}
	
	public String toString() {
		
	String s = "";
		 for (Move m : moves) {
			 s = s + m +"\n";
		 }
			 
	return s;
	}
	
}
