package backgammon;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;


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

	// Bars and BearedOffSpaces
	private Map<Colour, Bar> bars_dict = new HashMap<Colour, Bar>();
	private Map<Colour, BearedOffSpace> bearedOffSpaces_dict = new HashMap<Colour, BearedOffSpace>();

	// Dice Roll
	private int[] latestDiceRoll = new int[2];

	private boolean isDiceRolled = false;
	private List<Integer> availableRolls = new ArrayList<Integer>();

	private Map<Character, Move> legalMoves = new HashMap<Character, Move>();

	private boolean isTurnOver;
	
	private int doublingCubeMultiplier = 1;

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
		Point[] points = createEmptyPoints();
		for (Point p : points) {
			// add checkers to points
			p.addInitialCheckers();
		}
		return points;
	}

	private static Point[] createEmptyPoints() {
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
			points[index] = new Point(board.getPoint(index));
			index++;
		}
		return points;
	}

	/**
	 * Testing Constructor for the class Sets up the board in a designated state
	 */
	protected static BackgammonBoard createTestBoard(String testScenarioString) {

		// Create empty points and empty bars and empty beared off

		// to add checkers to the points - they are indexed by whites perspective
		// (from 0 to 23)
		Point[] points = createEmptyPoints();
		Bar whiteBar = new Bar(Colour.WHITE);
		Bar blackBar = new Bar(Colour.BLACK);
		BearedOffSpace whiteBearedOffSpace = new BearedOffSpace(Colour.WHITE);
		BearedOffSpace blackBearedOffSpace = new BearedOffSpace(Colour.BLACK);
		BackgammonBoard testBoard = new BackgammonBoard(points, whiteBar, blackBar, whiteBearedOffSpace, blackBearedOffSpace);

		switch (testScenarioString) {
		case "SINGLE WIN":
			testBoard.getBoardSpaceByPipValue(0, Colour.WHITE).addNewCheckers(14, Colour.WHITE);
			testBoard.getBoardSpaceByPipValue(0, Colour.BLACK).addNewCheckers(14, Colour.BLACK);
			testBoard.getBoardSpaceByPipValue(1, Colour.WHITE).addNewCheckers(1, Colour.WHITE);
			testBoard.getBoardSpaceByPipValue(1, Colour.BLACK).addNewCheckers(1, Colour.BLACK);
			break;

			
		case "GAMMON WIN":
			testBoard.getBoardSpaceByPipValue(0, Colour.WHITE).addNewCheckers(14, Colour.WHITE);
			testBoard.getBoardSpaceByPipValue(1, Colour.WHITE).addNewCheckers(1, Colour.WHITE);
			testBoard.getBoardSpaceByPipValue(15, Colour.BLACK).addNewCheckers(15, Colour.BLACK);
			break;
			
		case "BACKGAMMON WIN":
			testBoard.getBoardSpaceByPipValue(0, Colour.WHITE).addNewCheckers(14, Colour.WHITE);
			testBoard.getBoardSpaceByPipValue(1, Colour.WHITE).addNewCheckers(1, Colour.WHITE);
			testBoard.getBoardSpaceByPipValue(22, Colour.BLACK).addNewCheckers(15, Colour.BLACK);
			break;
		case "BEAR OFF":
			// can implement any number of specific test scenarios here
			testBoard.getBoardSpaceByPipValue(1, Colour.BLACK).addNewCheckers(3, Colour.BLACK);
			testBoard.getBoardSpaceByPipValue(2, Colour.BLACK).addNewCheckers(4, Colour.BLACK);
			testBoard.getBoardSpaceByPipValue(3, Colour.BLACK).addNewCheckers(5, Colour.BLACK);
			testBoard.getBoardSpaceByPipValue(4, Colour.BLACK).addNewCheckers(3, Colour.BLACK);
			testBoard.getBoardSpaceByPipValue(25, Colour.WHITE).addNewCheckers(1, Colour.WHITE);
			testBoard.getBoardSpaceByPipValue(19, Colour.WHITE).addNewCheckers(4, Colour.WHITE);
			testBoard.getBoardSpaceByPipValue(16, Colour.WHITE).addNewCheckers(10, Colour.WHITE);
			break;
		
		case "ONLY LARGER ROLL":
			testBoard.getBoardSpaceByPipValue(15, Colour.BLACK).addNewCheckers(1, Colour.BLACK);
			testBoard.getBoardSpaceByPipValue(18, Colour.WHITE).addNewCheckers(2, Colour.WHITE);
			break;
			
		case "ONLY DOUBLE ROLL MOVES":
			testBoard.getBoardSpaceByPipValue(15, Colour.BLACK).addNewCheckers(1, Colour.BLACK);
			testBoard.getBoardSpaceByPipValue(10, Colour.BLACK).addNewCheckers(1, Colour.BLACK);
			testBoard.getBoardSpaceByPipValue(18, Colour.WHITE).addNewCheckers(2, Colour.WHITE);
			break;
			

		case "HIT":
			testBoard.getBoardSpaceByPipValue(14, Colour.BLACK).addNewCheckers(1, Colour.BLACK);
			testBoard.getBoardSpaceByPipValue(13, Colour.BLACK).addNewCheckers(1, Colour.WHITE);
			break;
			
		case "CHECKER ON BAR":
			testBoard.getBoardSpaceByPipValue(25, Colour.WHITE).addNewCheckers(1, Colour.WHITE);
			testBoard.getBoardSpaceByPipValue(13, Colour.WHITE).addNewCheckers(2, Colour.WHITE);
			testBoard.getBoardSpaceByPipValue(11, Colour.WHITE).addNewCheckers(6, Colour.WHITE);
			testBoard.getBoardSpaceByPipValue(19, Colour.WHITE).addNewCheckers(3, Colour.WHITE);
			testBoard.getBoardSpaceByPipValue(16, Colour.WHITE).addNewCheckers(2, Colour.WHITE);
			testBoard.getBoardSpaceByPipValue(22, Colour.BLACK).addNewCheckers(3, Colour.BLACK);
			testBoard.getBoardSpaceByPipValue(21, Colour.BLACK).addNewCheckers(4, Colour.BLACK);
			testBoard.getBoardSpaceByPipValue(23, Colour.BLACK).addNewCheckers(5, Colour.BLACK);
			testBoard.getBoardSpaceByPipValue(24, Colour.BLACK).addNewCheckers(3, Colour.BLACK);
			break;
		}
		// call constructor
		return testBoard;
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
	
	public void applyDouble() {
		this.doublingCubeMultiplier = 2*this.doublingCubeMultiplier;
	}
	
	public int getDoublingCubeMultiplier() {
		return this.doublingCubeMultiplier;
	}

	public void setRolls(int roll1, int roll2, Player activePlayer) {
		this.latestDiceRoll[0] = roll1;
		this.latestDiceRoll[1] = roll2;
		isDiceRolled = true;

		if (this.latestDiceRoll[0] == this.latestDiceRoll[1]) {
			for (int i = 0; i < 4; i++) {
				this.availableRolls.add(this.latestDiceRoll[0]);
			}
		} else {
			this.availableRolls.add(this.latestDiceRoll[0]);
			this.availableRolls.add(this.latestDiceRoll[1]);
		}
		updateLegalMoves(activePlayer);
	}

	private void resetAvailableRolls() {
		this.availableRolls.clear();
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
		this.setRolls(Dice.roll(),Dice.roll(),activePlayer);
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
			moveString.append(key + ") " + move.toString() + "\n");
		}
		return moveString.toString();
	}
	
	protected Collection<Move> getLegalMoves(){
		return this.legalMoves.values();
	}

	private Collection<Integer> getUniqueAvailableRolls() {
		Collection<Integer> uniqueRolls = new HashSet<Integer>(availableRolls);
		return uniqueRolls;
	}

	private List<Move> getPossibleNextMoves(Player activePlayer) {
		
		Colour colour = activePlayer.getColour();
		Collection<Integer> uniqueRolls = getUniqueAvailableRolls();

		List<Move> nextPossibleMoves = new ArrayList<Move>();
		Bar activePlayersBar = getBarByColour(colour);
		BoardSpace destSpace;
		for (int roll : uniqueRolls) {
			if (activePlayersBar.canTake(activePlayer.getColour())) {
				destSpace = getDestinationBoardSpace(activePlayer, activePlayersBar, roll);
				if (destSpace.canPlace(activePlayersBar.getTopChecker())) {
					Move legalMove = new Move(roll, activePlayersBar.getPipValue(colour), destSpace.getPipValue(colour) );
					nextPossibleMoves.add(legalMove);
				}
			} else {
				for (int i = 0; i < NUMBER_OF_POINTS; i++) {
					if (boardSpaces[i].canTake(activePlayer.getColour())) {
						destSpace = getDestinationBoardSpace(activePlayer, boardSpaces[i], roll);
						if (destSpace != null && destSpace.canPlace(boardSpaces[i].getTopChecker())) {
							Move legalMove = new Move(roll, boardSpaces[i].getPipValue(colour), destSpace.getPipValue(colour));
							nextPossibleMoves.add(legalMove);
						}
					}
				}
			}
		}
		return nextPossibleMoves;
	}
	
	private BoardSpace getDestinationBoardSpace(Player activePlayer, BoardSpace source, int roll) {
		int start = source.getPipValue(activePlayer.getColour());
		int dest = start - (roll);
		BoardSpace destination;
		if (dest < 0) {
			if (canBearOff(activePlayer)) {
				// Check if start is highest pip count of activePlayer's points
				boolean highestPip = true;
				for (int i = 6; i > start; i--) {
					if (((Point) boardSpaces[activePlayer.getAlternateIndex(i) - 1])
							.hasCheckerOfColour(activePlayer.getColour())) {
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

	private boolean canBearOff(Player activePlayer) {
		boolean bearOff = true;
		for (int i = 24; i > 6; i--) {
			if (((Point) boardSpaces[activePlayer.getAlternateIndex(i) - 1]).hasCheckerOfColour(activePlayer.getColour())) {
				bearOff = false;
			}
		}
		return bearOff;
	}

	public boolean isWon(Colour playerColour) {
		boolean gameWon = false;
		if (getBearedOffSpaceByColour(playerColour).isFull()) {
			gameWon = true;
		}
		return gameWon;
	}

	public boolean isBackgammon(Colour playerColour) {
		boolean backgammon = false;
		if (!this.getBarByColour(playerColour).isEmpty()) {
			backgammon = true;
		} else {
			for(int i = 24; i > 18;i--) {
				if (((Point) boardSpaces[playerColour.getAlternateIndex(i) - 1]).hasCheckerOfColour(playerColour)) {
					backgammon = true;
				}
			}
		}
		return backgammon;
	}
	
	public boolean isGammon(Colour playerColour) {
		boolean gammon = false;
		if (this.getBearedOffSpaceByColour(playerColour).isEmpty()) {
			gammon = true;
		}
		return gammon;
	}

	protected void endTurn() {
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

	public int getPipCount(Colour playerColour) {
		int pipCount = 0;

		// For Every BoardSpace
		for (BoardSpace b : boardSpaces) {
			int numCheckers = b.getNumCheckers();
			// Get number of activePlayer's checkers on that space
			if (numCheckers > 0) {
				if (b.getTopChecker().getColour() == playerColour) {
					// multiply this number this space's pip value
					// and add to running total
					pipCount += numCheckers * b.getPipValue(playerColour);
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

	public Set<Character> getMoveKeys() {
		return legalMoves.keySet();
	}

	private void updateLegalMoves(Player activePlayer) {

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
			// Remove any moveSets where only 1 roll is used
			List<MoveSet> trimmedLegalMoveSets = new ArrayList<MoveSet>();
			for (MoveSet moveSet : legalMoveSets) {
				if (moveSet.size() != 1) {
					trimmedLegalMoveSets.add(moveSet);
				}
			}
			legalMoveSets = trimmedLegalMoveSets;
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
		BoardSpace source = m.getSource(this, activePlayer.getColour());
		BoardSpace destination = m.getDestination(this, activePlayer.getColour());

		Checker checker = source.removeChecker();

		if (destination instanceof Point) {
			if (((Point) destination).isHittable(checker)) {
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

	public BoardSpace getBoardSpaceByPipValue(int p, Colour playerColour) {
		BoardSpace bs;
		if (p == 0) {
			bs = getBearedOffSpaceByColour(playerColour);
		} else if (p == 25) {
			bs = getBarByColour(playerColour);
		} else {
			bs = boardSpaces[playerColour.getAlternateIndex(p) - 1];
		}
		return bs;
	}

	public boolean noMoveAvailable(Player activePlayer) {
		return getPossibleNextMoves(activePlayer).isEmpty();
	}


}