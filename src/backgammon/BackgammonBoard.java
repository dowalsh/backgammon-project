package backgammon;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
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

		this.availableRolls.addAll(board.availableRolls);
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

	public String legalMovesToString(Player activePlayer) {
		final StringBuilder moveString = new StringBuilder("");

		moveString.append("Remaining Rolls: " + availableRolls + "\n");
		moveString.append("MOVE OPTIONS: \n");
		for (Map.Entry<Character, Move> m : legalMoves.entrySet()) {
			char key = m.getKey();
			Move move = m.getValue();
			moveString.append(key + ") " + move.toString(activePlayer) + "\n");
		}
		return moveString.toString();
	}

	private Collection<Integer> getUniqueAvailableRolls() {
		Collection<Integer> uniqueRolls = new HashSet<Integer>(availableRolls);

		return uniqueRolls;
	}

	private List<Move> getPossibleNextMoves(Player activePlayer) {

		Collection<Integer> uniqueRolls = getUniqueAvailableRolls();

		List<Move> nextPossibleMoves = new ArrayList<Move>();
		Bar activePlayersBar = getBarByColour(activePlayer.getColour());
		BoardSpace destSpace;
		for (int roll : uniqueRolls) {
			if (activePlayersBar.canTake(activePlayer)) {
				destSpace = getDestinationBoardSpace(activePlayer, activePlayersBar, roll);
				if (destSpace.canPlace(activePlayersBar.getTopChecker())) {
					Move legalMove = new Move(roll, activePlayersBar, destSpace, activePlayer);
					nextPossibleMoves.add(legalMove);
				}
			} else {
				for (int i = 0; i < NUMBER_OF_POINTS; i++) {
					if (boardSpaces[i].canTake(activePlayer)) {
						destSpace = getDestinationBoardSpace(activePlayer, boardSpaces[i], roll);
						// TODO remove test of != null ; not groot practice according to notes I think
						if (destSpace != null && destSpace.canPlace(boardSpaces[i].getTopChecker())) {
							Move legalMove = new Move(roll, boardSpaces[i], destSpace, activePlayer);
							nextPossibleMoves.add(legalMove);
						}
					}
				}
			}
		}
		return nextPossibleMoves;
	}

	public BoardSpace getDestinationBoardSpace(Player activePlayer, BoardSpace source, int roll) {
		int start = source.getPipValue(activePlayer);
		int dest = start - (roll);
		BoardSpace destination;
		if (dest < 0) {
			if (canBearOff(activePlayer)) {
				// Check if start is highest pip count of activePlayer's points
				boolean highestPip = true;
				for (int i = 6; i > start; i--) {
					if (((Point) boardSpaces[activePlayer.getAlternateIndex(i) - 1]).hasColour(activePlayer.getColour())) {
						highestPip = false;
					}
				}
				if (highestPip) {
					destination = getBearedOffSpaceByColour(activePlayer.getColour());
				} else {
					destination = null;
				}
			} else {
				destination = null;
			}
		} else if (dest == 0) {
			if (canBearOff(activePlayer)) {
				destination = getBearedOffSpaceByColour(activePlayer.getColour());
			} else {
				destination = null;
			}
		} else {
			destination = boardSpaces[activePlayer.getAlternateIndex(dest) - 1];
		}
		return destination;
	}

	public void resetAvailableRolls() {
		this.availableRolls.clear();
	}

	public boolean canBearOff(Player activePlayer) {
		boolean bearOff = true;
		for (int i = 24; i > 6; i--) {
			if (((Point) boardSpaces[activePlayer.getAlternateIndex(i) - 1]).hasColour(activePlayer.getColour())) {
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
	public void rollDice(Player activePlayer) {
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

		updateLegalMoves(activePlayer);
	}

	private void endTurn() {
		this.latestDiceRoll[0] = 0;
		this.latestDiceRoll[1] = 0;
		isDiceRolled = false;
		isTurnOver = true;
		resetAvailableRolls();
		this.legalMoves.clear();
	}

	public void beginTurn() {
		isTurnOver = false;
	}

	public boolean isDiceRolled() {
		return isDiceRolled;
	}

	/**
	 * Gets the pip count for a activePlayer
	 * 
	 * @param activePlayer the activePlayer to calculate the pip count for
	 */
	public int getPipCount(Player activePlayer) {
		int pipCount = 0;

		// For Every BoardSpace
		for (BoardSpace b : boardSpaces) {
			int numCheckers = b.getNumCheckers();
			// Get number of activePlayer's checkers on that space
			if (numCheckers > 0) {
				if (b.getTopChecker().getColour() == activePlayer.getColour()) {
					// multiply this number this space's pip value
					// and add to running total
					pipCount += numCheckers * b.getPipValue(activePlayer);
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

	public void updateLegalMoves(Player activePlayer) {

		List<Move> possibleNextMoves = getPossibleNextMoves(activePlayer);
		if (possibleNextMoves.isEmpty()) {
			this.endTurn();
		} else {
			if (getUniqueAvailableRolls().size() == 1) {
				// Either doubles have been rolled, or there is only one roll available left
				addMovesToDictionary(possibleNextMoves);

			} else {
				List<MoveSet> legalMoveSets = createLegalMoveSets(possibleNextMoves, activePlayer);

				List<Move> legalMoves = new ArrayList<Move>();
				for (MoveSet mp : legalMoveSets) {
					legalMoves.add(mp.getFirstMove());
				}
				Collection<Move> uniqueMoves = new HashSet<Move>(legalMoves);

				addMovesToDictionary(new ArrayList<Move>(uniqueMoves));
			}
		}
	}

	private List<MoveSet> createLegalMoveSets(List<Move> possibleNextMoves, Player activePlayer) {
		List<MoveSet> legalMoveSets = new ArrayList<MoveSet>();

		for (Move m1 : possibleNextMoves) {
			BackgammonBoard newBoard = new BackgammonBoard(this);

			newBoard.applyMove(m1, activePlayer);
			List<Move> subsequentMoves = newBoard.getPossibleNextMoves(activePlayer);
			if (subsequentMoves.isEmpty()) {
				// Add a singular move to the movepairs
				legalMoveSets.add(new MoveSet(m1));
			} else {
				// Add a moveset for every combination
				for (Move m2 : subsequentMoves) {
					legalMoveSets.add(new MoveSet(m1, m2));
				}
			}
		}

		// If any movesets use both rolls, delete others
		boolean canUseBothRolls = false;
		for (MoveSet moveSet : legalMoveSets) {
			if (moveSet.size() == 2) {
				canUseBothRolls = true;
				break;
			}
		}
		if (canUseBothRolls) {
			for (MoveSet moveSet : legalMoveSets) {
				if (moveSet.size() == 1) {
					legalMoveSets.remove(moveSet);
				}
			}
		} else {

			boolean canUseLargerRoll = false;
			for (MoveSet moveSet : legalMoveSets) {
				if (moveSet.usesRoll(Collections.max(availableRolls))) {
					canUseLargerRoll = true;
					break;
				}
			}
			if (canUseLargerRoll) {
				for (MoveSet moveSet : legalMoveSets) {
					if (!moveSet.usesRoll(Collections.max(availableRolls))) {
						legalMoveSets.remove(moveSet);
					}
				}
			}
		}

		return legalMoveSets;
	}

	private void addMovesToDictionary(List<Move> uniqueMoves) {
		Collections.sort(uniqueMoves);
		legalMoves.clear();
		char key = 'A';
		for (Move m : uniqueMoves) {
			legalMoves.put(key, m);
			key++;
		}
	}

	private void applyMove(Move m, Player activePlayer) {
		BoardSpace source = m.getSource(this, activePlayer);
		BoardSpace destination = m.getDestination(this, activePlayer);

		Checker checker = source.removeChecker();

		if (destination instanceof Point) {
			if (((Point) destination).isAHit(checker)) {
				Checker removed = destination.removeChecker();
				Bar bar = getBarByColour(removed.getColour());
				bar.addChecker(removed);
			}
		}

		destination.addChecker(checker);

		// other updates
		availableRolls.remove(m.getRoll());
	}

	public void selectMove(char c, Player activePlayer) {
		Move m = legalMoves.get(c);
		applyMove(m, activePlayer);

		updateLegalMoves(activePlayer);

		if (availableRolls.isEmpty())
			this.endTurn();
	}

	public boolean isTurnOver() {
		return isTurnOver;
	}

	public BoardSpace getBoardSpaceByPipValue(int p, Player activePlayer) {
		BoardSpace bs;
		if (p == 0) {
			bs = getBearedOffSpaceByColour(activePlayer.getColour());
		} else if (p == 25) {
			bs = getBarByColour(activePlayer.getColour());
		} else {
			bs = boardSpaces[activePlayer.getAlternateIndex(p) - 1];
		}
		return bs;
	}

	public boolean noMoveAvailable(Player activePlayer) {
		// TODO try think of a more elegant solution
		return getPossibleNextMoves(activePlayer).isEmpty();
	}

}