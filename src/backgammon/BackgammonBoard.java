package backgammon;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

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

	// TODO update bars and bearedOff storage to be simpler data structure than
	// dictionary
	// Bars and BearedOffSpaces
	private Map<Colour, Bar> bars_dict = new HashMap<Colour, Bar>();
	private Map<Colour, BearedOffSpace> bearedOffSpaces_dict = new HashMap<Colour, BearedOffSpace>();

	// Dice Roll
	private int[] latestDiceRoll = new int[2];
	private boolean isDoubles = false;
	private boolean isDiceRolled = false;

	private List<Integer> availableRolls = new ArrayList<Integer>();

	private Map<Character, Move> legalMoves = new HashMap<Character, Move>();

	private boolean isGameOver = false;
	private boolean isTurnOver;

	public BoardSpace[] getBoardSpaces() {
		return boardSpaces;
	}

	/**
	 * Default Constructor for the class
	 */
	public BackgammonBoard() {
		this(createInitialPoints(), new Bar(Colour.WHITE), new Bar(Colour.BLACK), new BearedOffSpace(Colour.WHITE),
				new BearedOffSpace(Colour.BLACK));
	}

	private static Point[] createInitialPoints() {
		int index = 0;
		Point[] points = new Point[NUMBER_OF_POINTS];
		// create and add points
		while (index < NUMBER_OF_POINTS) {
			points[index] = new Point(index + 1);
			index++;
		}
		return points;
	}

	/**
	 * Copy Constructor for the class
	 */
	private BackgammonBoard(BackgammonBoard board) {
		this(copyPoints(board), new Bar(board.getBarByColour(Colour.WHITE)),
				new Bar(board.getBarByColour(Colour.BLACK)),
				new BearedOffSpace(board.getBearedOffSpaceByColour(Colour.WHITE)),
				new BearedOffSpace(board.getBearedOffSpaceByColour(Colour.BLACK)));
	}

	private static Point[] copyPoints(BackgammonBoard board) {
		int index = 0;
		Point[] points = new Point[NUMBER_OF_POINTS];
		// create and add points
		while (index < NUMBER_OF_POINTS) {
			points[index] = new Point(index + 1, board.getPoint(index));
			index++;
		}
		return points;
	}

	/*
	 * Largest generic constructor for class
	 */
	private BackgammonBoard(Point[] points, Bar whiteBar, Bar blackBar, BearedOffSpace whiteBearedOffSpace,
			BearedOffSpace blackBearedOffSpace) {
		int index = 0;

		// Add Points
		while (index < NUMBER_OF_POINTS) {
			boardSpaces[index] = points[index];
			index++;
		}

		// add bars
		boardSpaces[index] = blackBar;
		bars_dict.put(Colour.BLACK, blackBar);
		index++;
		boardSpaces[index] = whiteBar;
		bars_dict.put(Colour.WHITE, whiteBar);
		index++;

		// add BearedOffSpaces
		boardSpaces[index] = blackBearedOffSpace;
		bearedOffSpaces_dict.put(Colour.BLACK, blackBearedOffSpace);
		index++;
		boardSpaces[index] = whiteBearedOffSpace;
		bearedOffSpaces_dict.put(Colour.WHITE, whiteBearedOffSpace);
		index++;

	}

	private Point getPoint(int index) {
		return (Point) boardSpaces[index];
	}

	public Collection <Integer> getUniqueAvailableRolls() {
        Collection<Integer> uniqueRolls = new HashSet<Integer>(availableRolls);

		return uniqueRolls;
	}

	private List<Move> getPossibleNextMoves(Player player) {
		Collection<Integer> uniqueRolls = getUniqueAvailableRolls();
		List<Move> nextPossibleMoves = new ArrayList<Move>();
		Bar playersBar = getBarByColour(player.getColour());
		BoardSpace destSpace;
		for (int roll : uniqueRolls) {
			if (playersBar.canTake(player)) {
				destSpace = getDestinationBoardSpace(player, playersBar, roll);
				if (destSpace.canPlace(playersBar.getTopChecker())) {
					Move legalMove = new Move(roll, playersBar, destSpace);
					nextPossibleMoves.add(legalMove);
				}
			} else {
				for (int i = 0; i < NUMBER_OF_POINTS; i++) {
					if (boardSpaces[i].canTake(player)) {
						destSpace = getDestinationBoardSpace(player, boardSpaces[i], roll);
						// TODO remove test of != null ; not groot practice according to notes I think
						if (destSpace != null && destSpace.canPlace(boardSpaces[i].getTopChecker())) {
							Move legalMove = new Move(roll, boardSpaces[i], destSpace);
							nextPossibleMoves.add(legalMove);
						}
					}
				}
			}
		}
		return nextPossibleMoves;
	}

	public String legalMovesToString(Player player) {
		final StringBuilder moveString = new StringBuilder("");
		
		
		moveString.append("Remaining Rolls: "+availableRolls+"\n");
		moveString.append("MOVE OPTIONS: \n");
		for (Map.Entry<Character, Move> m : legalMoves.entrySet()) {
			char key = m.getKey();
			Move move = m.getValue();
			moveString.append(key + ") " + move.toString(player) + "\n");
		}
		return moveString.toString();
	}

	public BoardSpace getDestinationBoardSpace(Player player, BoardSpace source, int roll) {
		int start = source.getPipValue(player);
		int dest = start - (roll);
		BoardSpace destination;
		if (dest < 0) {
			if (canBearOff(player)) {
				// Check if start is highest pip count of player's points
				boolean highestPip = true;
				for (int i = 6; i > start; i--) {
					if (!boardSpaces[player.getAlternateIndex(i) - 1].isEmpty()) {
						highestPip = false;
					}
				}
				if (highestPip) {
					destination = getBearedOffSpaceByColour(player.getColour());
				} else {
					destination = null;
				}
			} else {
				destination = null;
			}
		} else if (dest == 0) {
			if (canBearOff(player)) {
				destination = getBearedOffSpaceByColour(player.getColour());
			} else {
				destination = null;
			}
		} else {
			destination = boardSpaces[player.getAlternateIndex(dest) - 1];
		}
		return destination;
	}

	public void resetAvailableRolls() {
		this.availableRolls.clear();
	}

	public boolean canBearOff(Player player) {
		boolean bearOff = true;
		for (int i = 24; i > 6; i--) {
			if (!boardSpaces[player.getAlternateIndex(i) - 1].isEmpty()) {
				bearOff = false;
			}
		}
		return bearOff;
	}

	public boolean isWon(Player player) {
		boolean gameWon = false;
		if(getBearedOffSpaceByColour(player.getColour()).isFull()) {
			gameWon = true;
		}
		return gameWon;
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

		if (this.latestDiceRoll[0] == this.latestDiceRoll[1]) {
			isDoubles = true;
			for (int i = 0; i < 4; i++) {
				this.availableRolls.add(this.latestDiceRoll[0]);
			}
		} else {
			isDoubles = false;
			this.availableRolls.add(this.latestDiceRoll[0]);
			this.availableRolls.add(this.latestDiceRoll[1]);
		}
	}

	private void endTurn() {
		this.latestDiceRoll[0] = 0;
		this.latestDiceRoll[1] = 0;
		isDiceRolled = false;
		isTurnOver = true;
		resetLegalMoves();
		resetAvailableRolls();
	}

	public boolean isDiceRolled() {
		return isDiceRolled;
	}

	/**
	 * Gets the pip count for a player
	 * 
	 * @param player the player to calculate the pip count for
	 */
	public int getPipCount(Player player) {
		int pipCount = 0;

		// For Every BoardSpace
		for (BoardSpace b : boardSpaces) {
			int numCheckers = b.getNumCheckers();
			// Get number of player's checkers on that space
			if (numCheckers > 0) {
				if (b.getTopChecker().getColour() == player.getColour()) {
					// multiply this number this space's pip value
					// and add to running total
					pipCount += numCheckers * b.getPipValue(player);
				}
			}

		}

		return pipCount;
	}

	private Bar getBarByColour(Colour c) {
		return bars_dict.get(c);
	}

	private BearedOffSpace getBearedOffSpaceByColour(Colour c) {
		return bearedOffSpaces_dict.get(c);
	}

	public boolean isGameOver() {
		return isGameOver;
	}

	public void endGame() {
		isGameOver = true;
	}

	public Set<Character> getMoveKeys() {
		return legalMoves.keySet();
	}

	public void createLegalMoves(Player activePlayer) {
		char key = 'A';
		if (isDoubles) {
			for (Move m : getPossibleNextMoves(activePlayer)) {
				legalMoves.put(key, m);
				key++;
			}
		} else {
			for (Move m : getPossibleNextMoves(activePlayer)) {
				legalMoves.put(key, m);
				key++;
			}
		}

	}

	private void updateLegalMoves(Player activePlayer) {
		resetLegalMoves();
		char key = 'A';
		if (isDoubles) {
			for (Move m : getPossibleNextMoves(activePlayer)) {
				legalMoves.put(key, m);
				key++;
			}
		} else {
			for (Move m : getPossibleNextMoves(activePlayer)) {
				legalMoves.put(key, m);
				key++;
			}
		}	
	}
	
	public void resetLegalMoves() {
		this.legalMoves.clear();
	}

	public void applyMove(char c, Player activePlayer) {
		Move m = legalMoves.get(c);
		BoardSpace source = m.getSource();
		BoardSpace destination = m.getDestination();

		Checker checker = source.removeChecker();

		if (destination instanceof Point) {
			if (((Point) destination).isAHit(checker)) {
				Checker removed = destination.removeChecker();
				Bar bar = getBarByColour(removed.getColour());
				bar.addChecker(removed);
			}
		}

		destination.addChecker(checker);
		
		//other updates
		availableRolls.remove(m.getRoll());
		
		updateLegalMoves(activePlayer);

		if (availableRolls.isEmpty())
			this.endTurn();
	}


	public boolean isTurnOver() {
		return isTurnOver;
	}

	public void beginTurn() {
		isTurnOver = false;
		isDiceRolled = false;
	}

}