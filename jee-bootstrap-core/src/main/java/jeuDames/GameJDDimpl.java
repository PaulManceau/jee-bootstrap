package jeuDames;

import java.util.ArrayList;
import java.util.List;

public class GameJDDimpl implements GameJDD {

	public static final int SQUARE_SIZE = 10;
    public static final String OUTSIDE_OF_BOARD_ERROR = "It is not possible move a chip outside of the board";
    public static final String NO_CHIP_ON_CELL_ERROR = "There is no chip on the board";
    
    //a jeter
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
    List<List<ChipColor>> board = new ArrayList<>(SQUARE_SIZE);
	
	private void initBoard(){
		for (int i = 0; i < SQUARE_SIZE; i++) {
            board.add(new ArrayList<ChipColor>(SQUARE_SIZE));
        }
	}
	
	
	//Le board est une liste de liste : la liste principale, verticale, contient des listes horizontales
	@Override
	public void play(int abs, int ord, boolean direction) throws GameException {
		if (abs > getSquareSize() - 1){
			throw new GameException(OUTSIDE_OF_BOARD_ERROR);
		}
		if (ord> getSquareSize() - 1){
			throw new GameException(OUTSIDE_OF_BOARD_ERROR);
		}
		List<ChipColor> absList = board.get(abs);
		if (absList.size() >= SQUARE_SIZE) {
            throw new GameException(OUTSIDE_OF_BOARD_ERROR);
        }
		
	}

	@Override
	public ChipColor getCell(int abs, int ord) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ChipColor getWinner() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getSquareSize() {
		return SQUARE_SIZE;
	}

}
