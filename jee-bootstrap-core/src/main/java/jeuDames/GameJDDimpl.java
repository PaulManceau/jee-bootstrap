package jeuDames;

import java.util.ArrayList;
import java.util.List;

public class GameJDDimpl implements GameJDD {

	public static final int SQUARE_SIZE = 10;
	public static final String CANT_FIND_PLAYER_ERROR = "Next player unfoundable";
    public static final String OUTSIDE_OF_BOARD_ERROR = "It is not possible move a chip outside of the board";
    public static final String NO_CHIP_ON_CELL_ERROR = "There is no chip on the board"; 
    public static final String NOT_PLAYERS_CHIP_ERROR = "That is not your chip"; 
    public static final String WRONG_DIRECTION_NAME_ERROR = "Directions are : AvD, AvG, ArD and ArG";
    
    ChipColor player = ChipColor.BLANC;
    List<List<ChipColor>> board = new ArrayList<>(SQUARE_SIZE);
	
	public void initBoard(){
		for (int i = 0; i < SQUARE_SIZE; i++) {
            board.add(new ArrayList<ChipColor>(SQUARE_SIZE));
            for (int j = 0; j < SQUARE_SIZE; j++){
            	if(i % 2 == j % 2 && i<4)board.get(i).add(ChipColor.BLANC);
            	else if(i % 2 == j % 2 && i>5)board.get(i).add(ChipColor.NOIR);
            	else board.get(i).add(ChipColor.EMPTY);
            } 
        }
	}
	
	
	//Le board est une liste de liste : la liste principale, verticale, contient des listes horizontales
	@Override
	public void play(int abs, int ord, Direction dir) throws GameException {
		if(board.get(abs).get(ord) == ChipColor.EMPTY) throw new GameException(NO_CHIP_ON_CELL_ERROR) ;
		if(board.get(abs).get(ord) != player) throw new GameException(NOT_PLAYERS_CHIP_ERROR) ;
		
		int newAbs, newOrd;
		
		switch (dir) {
		case AvD:
			newAbs = abs + 1;
			newOrd = ord + 1;
			break;
		case AvG:
			newAbs = abs + 1;
			newOrd = ord - 1;
			break;
		case ArD:
			newAbs = abs - 1;
			newOrd = ord + 1;
			break;
		case ArG:
			newAbs = abs - 1;
			newOrd = ord - 1;
			break;

		default:
			throw new GameException(WRONG_DIRECTION_NAME_ERROR);
		}
		if (newAbs > getSquareSize() - 1 || newAbs < 0){
			throw new GameException(OUTSIDE_OF_BOARD_ERROR);
		}
		if (newOrd> getSquareSize() - 1 || newOrd < 0){
			throw new GameException(OUTSIDE_OF_BOARD_ERROR);
		}
		
		//Test move
		if(board.get(newAbs).get(newOrd)== ChipColor.EMPTY){
			if (dir == Direction.AvD || dir == Direction.AvG){
				board.get(abs).set(ord, ChipColor.EMPTY);
				board.get(newOrd).set(newOrd, player);
				player = changePlayer(player);
			}else{
				System.out.println("Player : " + player.toString() +", you can't move backward");
			}
		}
		else if(board.get(newAbs).get(newOrd)== player){
			System.out.println("Player : " + player.toString() +", one of your chips blocks your move" );
		}
		else
		{
			eatChip(abs,ord,dir);
		}
		
		//après modifications des listes
		List<ChipColor> absList = board.get(abs);
		List<ChipColor> newAbsList = board.get(newAbs);
		if (absList.size() >= SQUARE_SIZE || newAbsList.size() >= SQUARE_SIZE ) {
            throw new GameException(OUTSIDE_OF_BOARD_ERROR);
        }
		
	}

	@Override
	public ChipColor getCell(int abs, int ord) {
		return board.get(ord).get(abs);
	}

	@Override
	public ChipColor getWinner() {
		int nbWhiteChip = 0;
		int nbBlackChip = 0;
		
		for (int i = 0; i< SQUARE_SIZE; i++){
			for (int j = 0; j< SQUARE_SIZE; j++){
				if (board.get(i).get(j) == ChipColor.BLANC){
					nbWhiteChip++;
				}
				if (board.get(i).get(j) == ChipColor.NOIR){
					nbBlackChip++;
				}
			}
		}
		
		if (nbWhiteChip == 0 && nbBlackChip != 0){
			return ChipColor.NOIR;
		} else if ( nbBlackChip == 0 && nbWhiteChip != 0){
			return ChipColor.BLANC;
		} else if (nbWhiteChip == 0 && nbBlackChip == 0) {
			//implemeter message d erreur 
			return null;
		} else {
			return null;
		}
	}

	@Override
	public int getSquareSize() {
		return SQUARE_SIZE;
	}


	@Override
	public void showCurrentBoardStatus() {
		for (int i = 9; i >= 0; i--){
			for (int j = 0; j< SQUARE_SIZE; j++){
				System.out.print(board.get(i).get(j) + " ");
			}
		System.out.print("\n");
		}		
	}


	@Override
	public ChipColor changePlayer(ChipColor player) throws GameException{
		switch (player) {
		case BLANC:
			player = ChipColor.NOIR;
			break;
		case NOIR:
			player = ChipColor.BLANC;
			break;
		default:
			throw new GameException(CANT_FIND_PLAYER_ERROR);
		}
		return player;
	}


	@Override
	public void eatChip(int abs, int ord, Direction direction) throws GameException {
		// TODO Auto-generated method stub
		
	}

}
