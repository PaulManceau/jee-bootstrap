package jeuDames;

public interface GameJDD {

	/**
	 * Move a chip in a given direction.
	 * @param colour
	 * @param abs
	 * @param ord
	 * @throws GameException if it is not allowed to play in that cell.
	 */
    void play(int abs, int ord, String direction) throws GameException;
    
    /**
   	 * can the player play again ?
   	 * @param abs
   	 * @param ord
   	 * @param color
   	 * @return boolean
   	 */
    boolean canYouPlayAgain(int abs, int ord, ChipColor color);
    
    /**
	 * Function call only if player can eat a chip
	 * @param abs
	 * @param ord
	 * @param color
	 * @throws GameException if it is not allowed to play in that cell.
	 */
    void eatChip(int abs, int ord, Direction direction, ChipColor color) throws GameException;

    /**
	 * Move using direction
	 * @param direction
	 * @param ord
	 * @return integer
	 */
    int newOrd(int ord, Direction direction);
    
    /**
	 * Move using direction
	 * @param direction
	 * @param abs
	 * @return integer
	 */
    int newAbs(int abs, Direction direction);
    
    void showCurrentBoardStatus();
    
    
    /**
     * Returns the color of the chip in a given cell, null if no
     * chip is present.
     * @param abs
     * @param ord
     * @return
     */
    ChipColor getCell(int abs, int ord);

    /**
     * Returns the number of rows = column.
     * @return
     */
    int getSquareSize();

    /**
     * Returns the color of the winner, null if no winner.
     * @return
     */
    ChipColor getWinner();
	
    /**
     * Returns the color of the next player.
     * @return
     */
    ChipColor changePlayer(ChipColor player) throws GameException;
}
