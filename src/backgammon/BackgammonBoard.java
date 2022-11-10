package backgammon;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

/**
 * This program is the BackgammonBoard class
 * @author dylan
 *
 */

/**
 * A {@code BackgammonBoard} object represents a backgammon board
 */
public class BackgammonBoard {

	private static final int NUMBER_OF_POINTS = 24;
	private BoardSpace[] boardSpaces = new BoardSpace[NUMBER_OF_POINTS + 2 + 2];
	private Map<Colour, Bar> bars_dict = new HashMap<Colour, Bar>();
	private Map<Colour, BearedOffSpace> bearedOffSpaces_dict = new HashMap<Colour, BearedOffSpace>();
	
	private int[] latestDiceRoll = new int[2];
	private List<Integer> availableRolls = new ArrayList<Integer>();
	
	private Map<Character, Move> legalMoves = new HashMap<Character, Move>();
	
	private boolean isDiceRolled = false;

	

	public BoardSpace[] getBoardSpaces() {
		return boardSpaces;
	}

	/**
	 * Constructor for the class
	 */
	public BackgammonBoard() {

		int index = 0;

		// create and add points
		while (index < NUMBER_OF_POINTS) {
			boardSpaces[index] = new Point(index + 1);
			index++;
		}

		// create and add bars
		Bar blackBar = new Bar(Colour.BLACK);
		boardSpaces[index] = blackBar;
		bars_dict.put(Colour.BLACK, blackBar);
		index++;
		Bar whiteBar = new Bar(Colour.WHITE);
		boardSpaces[index] = whiteBar;
		bars_dict.put(Colour.WHITE, whiteBar);
		index++;

		// Create and add bearedOffSpaces
		BearedOffSpace blackBearedOffSpace = new BearedOffSpace(Colour.BLACK);
		boardSpaces[index] = blackBearedOffSpace;
		bearedOffSpaces_dict.put(Colour.BLACK, blackBearedOffSpace);
		index++;
		BearedOffSpace whiteBearedOffSpace = new BearedOffSpace(Colour.WHITE);
		boardSpaces[index] = whiteBearedOffSpace;
		bearedOffSpaces_dict.put(Colour.WHITE, whiteBearedOffSpace);
		index++;
	}
	
	public List<Integer> getUniqueAvailableRolls(){
		List<Integer> uniqueRolls = new ArrayList<Integer>();
		boolean doubleRoll = this.availableRolls.stream().allMatch(i -> i.equals(this.availableRolls.get(0)));
		if(doubleRoll) {
			uniqueRolls.add(this.availableRolls.get(0));
		} else {
			uniqueRolls.add(this.availableRolls.get(0));
			uniqueRolls.add(this.availableRolls.get(1));
		}
		return uniqueRolls;
	}
	
	public void updateLegalMoves(Player player) {
		List<Integer> uniqueRolls = getUniqueAvailableRolls();
		char  charIndex = 'a';
		Bar playersBar = getBarForPlayer(player);
		BoardSpace destSpace;
		for(int roll: uniqueRolls) {
			if(playersBar.canTake(player)) {
				destSpace = getDestinationBoardSpace(player, playersBar, roll);
				if(destSpace.canPlace(playersBar.getTopChecker())) {
					Move legalMove = new Move(roll, playersBar, destSpace);
					legalMoves.put(charIndex, legalMove);
					charIndex++;
				}
			} else {
				for(int i=0;i<NUMBER_OF_POINTS;i++) {
					if(boardSpaces[i].canTake(player)) {
						destSpace = getDestinationBoardSpace(player, boardSpaces[i], roll);
						//TODO remove test of != null ; not goot practice according to notes I think
						if(destSpace != null && destSpace.canPlace(boardSpaces[i].getTopChecker())) {
							Move legalMove = new Move(roll, boardSpaces[i], destSpace);
							legalMoves.put(charIndex, legalMove);
							charIndex++;
						}
					}
				}
			}
		}
		
	}
	
	public int getNumPossibleMoves() {
		return legalMoves.size();
	}
	
	public String[] legalMovesToString(Player player) {
		int numMoves = legalMoves.size();
		String[] moveString = new String[numMoves];
		int i=0;
		for(Map.Entry<Character, Move> m : legalMoves.entrySet()) {
			char key = m.getKey();
			Move move = m.getValue();
			moveString[i] = key+": "+move.toString(player);
			i++;
		}
		return moveString;
	}
	
	public BoardSpace getDestinationBoardSpace(Player player, BoardSpace source, int roll) {
		int start = source.getPipValue(player);
		int dest = start - (roll);
		BoardSpace destination;
		
		//TODO Bear off functionality
		if(dest<=0) {
			destination  = null;
		} else {
			destination = boardSpaces[player.getAlternateIndex(dest)-1];
		}
		return destination;
	}
	
	public void resetLegalMoves(){
		this.legalMoves.clear();
	}
	
	public void resetAvailableRolls() {
		this.availableRolls.clear();
	}
	
	public boolean canBearOff(Player player) {
		//TODO Bear off logic
		return false;
	}

	public boolean isWon() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * @return the latest Dice Roll
	 */
	public int[] getLatestDiceRoll() {
		return latestDiceRoll;
	}

	/**
	 * rolls two dice and saves the rolls as the latest roll
	 */
	public void rollDice() {
		this.latestDiceRoll[0] = Dice.roll();
		this.latestDiceRoll[1] = Dice.roll();
		isDiceRolled = true;

		// TODO set available Dice Roll List here
		if(this.latestDiceRoll[0] == this.latestDiceRoll[1]) {
			for(int i=0;i<4;i++) {
				this.availableRolls.add(this.latestDiceRoll[0]);
			}
		} else {
			this.availableRolls.add(this.latestDiceRoll[0]);
			this.availableRolls.add(this.latestDiceRoll[1]);
		}
	}

	public void endTurn() {
		this.latestDiceRoll[0] = 0;
		this.latestDiceRoll[1] = 0;
		isDiceRolled = false;
		resetLegalMoves();
		resetAvailableRolls();
	}

	public boolean getIsDiceRolled() {
		return isDiceRolled;
	}

	/**
	 * Gets the pip count for a player
	 * 
	 * @param player the player to calculate the pip count for
	 */
	public int getPipCount(Player player) {
		int pipCount = 0;

		// For Every Boardspace
		for (BoardSpace b : boardSpaces) {
			int numCheckers = b.getNumCheckers();
			// Get number of player's checkers on that space
			if (numCheckers > 0) {
				if(b.getTopChecker().getColour()== player.getColour()) {
					// multiply this number this space's pip value
					// and add to running total
					pipCount += numCheckers * b.getPipValue(player);
				}
			}

		}

		return pipCount;
	}
	
	private Bar getBarForPlayer(Player player) {
		return bars_dict.get(player.getColour());
	}
	
	private BearedOffSpace getBearedOffSpaceForPlayer(Player player) {
		return bearedOffSpaces_dict.get(player.getColour());
	}
	
//	public static void main(String args[]) {
//		char boo = 'Z';
//		boo++;
//		System.out.println(boo);
//		boo++;
//		System.out.println(boo);
//		boo++;
//		System.out.println(boo);
//		boo++;
//		System.out.println(boo);
//		boo++;
//		System.out.println(boo);
//	}
}