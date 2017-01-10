package jeuDames;

public interface GameJDD {

	/**
	 * Move a chip in a given direction.
	 * @param colour
	 * @param abs
	 * @param ord
	 * @throws GameException if it is not allowed to play in that cell.
	 */
	
    void play(int abs, int ord, Direction direction) throws GameException;
    /**
	 * Function all only if player can eat a chip
	 * @param colour
	 * @param abs
	 * @param ord
	 * @throws GameException if it is not allowed to play in that cell.
	 */
	
    void eatChip(int abs, int ord, Direction direction) throws GameException;

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
