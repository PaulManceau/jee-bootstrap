package jeuDames;

public interface GameJDD {

	/**
	 * Move a chip in a given direction (true = right, false = left).
	 * @param colour
	 * @param absHorizontal
	 * @param absVertical
	 * @throws GameException if it is not allowed to play in that cell.
	 */
	
    void play(int abs, int ord, boolean direction) throws GameException;

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
     * Returns the colour of the winner, null if no winner.
     * @return
     */
    ChipColor getWinner();
	
}
