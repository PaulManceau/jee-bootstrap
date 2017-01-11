package jeuDames;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameJDDimpl implements GameJDD {

	public static final int SQUARE_SIZE = 10;
	public static final String CANT_FIND_PLAYER_ERROR = "Next player unfoundable";
    public static final String OUTSIDE_OF_BOARD_ERROR = "It is not possible move a chip outside of the board";
    public static final String NO_CHIP_ON_CELL_ERROR = "There is no chip on the board"; 
    public static final String NOT_PLAYERS_CHIP_ERROR = "That is not your chip";
    
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
	public void play(int abs, int ord, String direction) throws GameException {
		if(getCell(abs,ord) == ChipColor.EMPTY) throw new GameException(NO_CHIP_ON_CELL_ERROR) ;
		if(getCell(abs,ord) != player) throw new GameException(NOT_PLAYERS_CHIP_ERROR) ;
		
		Direction dir = Direction.valueOf(direction);
		int newAbs = newAbs(abs, dir);
		int newOrd = newOrd(ord, dir);
		
		if (newAbs > getSquareSize() - 1 || newAbs < 0){
			throw new GameException(OUTSIDE_OF_BOARD_ERROR);
		}
		if (newOrd> getSquareSize() - 1 || newOrd < 0){
			throw new GameException(OUTSIDE_OF_BOARD_ERROR);
		}
		
		//Test move
		if(newAbs != abs && newOrd != ord){
			if(getCell(newAbs,newOrd)== ChipColor.EMPTY){
				if (dir == Direction.AvD || dir == Direction.AvG){
					board.get(abs).set(ord, ChipColor.EMPTY);
					board.get(newOrd).set(newOrd, player);
					player = changePlayer(player);
					System.out.println("Player " + player + " that's your turn");
				}else{
					System.out.println("Player : " + player.toString() +", you can't move backward");
				}
			}
			else if(getCell(newAbs,newOrd)== player){
				System.out.println("Player : " + player.toString() +", one of your chips blocks your move" );
			}
			else
			{
				eatChip(abs,ord,dir,player);
			}
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
	public void eatChip(int abs, int ord, Direction direction, ChipColor color) throws GameException {
		
		int ordEatenChip = newOrd(ord,direction);
		int absEatenCHip = newAbs(abs,direction);
		int newOrd = 2*ordEatenChip;
		int newAbs = 2*absEatenCHip;
		
		Scanner scanner = new Scanner(System.in);
		String command;
		
		ChipColor ChipOnNewPosition = getCell(newOrd,newAbs);
		
		if( ChipOnNewPosition != color || ChipOnNewPosition != ChipColor.EMPTY){
			System.out.println("You can't eat this chip, play again");
		}else{
			board.get(abs).set(ord, ChipColor.EMPTY);
			board.get(absEatenCHip).set(ordEatenChip, ChipColor.EMPTY);
			board.get(newAbs).set(newOrd, player);
			System.out.println(player + " : you eat a chip");
			
			if(canYouPlayAgain(newAbs,newOrd, player) == true ){
				System.out.println("Player " + player + " you can eat again, enter a direction (Avd, AvG, ArD or ArG) : ");
				command = scanner.nextLine();
				scanner.close();
				play(newAbs,newOrd,command);
			}else{
				player = changePlayer(player);
				System.out.println("Player " + player + " that's your turn");
			}
		}
	}


	@Override
	public int newOrd(int ord, Direction direction) {
		int newOrd = 0;
		switch (direction) {
		case AvD:
			newOrd = ord + 1;
			break;
		case AvG:
			newOrd = ord - 1;
			break;
		case ArD:
			newOrd = ord + 1;
			break;
		case ArG:
			newOrd = ord - 1;
			break;

		default:
			System.out.println(direction + " is not a direction. Directions are : AvD, AvG, ArD and ArG");
			newOrd = ord;
		}
		return newOrd;
	}


	@Override
	public int newAbs(int abs, Direction direction) {
		int newAbs=0;
		switch (direction) {
		case AvD:
			newAbs = abs + 1;
			break;
		case AvG:
			newAbs = abs + 1;
			break;
		case ArD:
			newAbs = abs - 1;
			break;
		case ArG:
			newAbs = abs - 1;
			break;

		default:
			System.out.println(direction + " is not a direction. Directions are : AvD, AvG, ArD and ArG");
			newAbs = abs;
		}
		return newAbs;
	}


	@Override
	public boolean canYouPlayAgain(int abs, int ord, ChipColor color) {
		
		
		ChipColor ChipAvD = getCell(newAbs(abs,Direction.AvD), newOrd(ord,Direction.AvD));
		ChipColor ChipArD = getCell(newAbs(abs,Direction.ArD), newOrd(ord,Direction.ArD));
		ChipColor ChipAvG = getCell(newAbs(abs,Direction.AvG), newOrd(ord,Direction.AvG));
		ChipColor ChipArG = getCell(newAbs(abs,Direction.ArG), newOrd(ord,Direction.ArG));
		
		if( ChipAvD != ChipColor.EMPTY && ChipAvD != color){
			if(getCell(2*newAbs(abs,Direction.AvD),2*newOrd(ord,Direction.AvD)) == ChipColor.EMPTY){
				return true;
			}
		}
		if( ChipAvG != ChipColor.EMPTY && ChipAvG != color){
			if(getCell(2*newAbs(abs,Direction.AvG),2*newOrd(ord,Direction.AvG)) == ChipColor.EMPTY){
				return true;
			}
		}
		if( ChipArD != ChipColor.EMPTY && ChipArD != color){
			if(getCell(2*newAbs(abs,Direction.ArD),2*newOrd(ord,Direction.ArD)) == ChipColor.EMPTY){
				return true;
			}
		}
		if( ChipArG != ChipColor.EMPTY && ChipArG != color){
			if(getCell(2*newAbs(abs,Direction.ArG),2*newOrd(ord,Direction.ArG)) == ChipColor.EMPTY){
				return true;
			}
		}
		
		return false;
	}

}
