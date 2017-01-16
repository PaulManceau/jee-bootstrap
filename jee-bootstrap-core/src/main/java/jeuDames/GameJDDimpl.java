package jeuDames;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameJDDimpl implements GameJDD {

	public static final int SQUARE_SIZE = 10;
	public static final String CANT_FIND_PLAYER_ERROR = "Next player unfoundable";
    public static final String OUTSIDE_OF_BOARD_ERROR = "It is not possible move a chip outside of the board";
    public String ANSI_BLACK = "NOIRE"; //"\u001B[30m";
    public String ANSI_WHITE = "BLANC"; //"\u001B[37m";
    public String ANSI_YELLOW = "EMPTY"; //"\u001B[33m";
    
    CaseColor player = CaseColor.BLANC;
    List<List<CaseColor>> board = new ArrayList<>(SQUARE_SIZE);
	private Scanner scanner;
	
	public void initBoard(){
		for (int i = 0; i < SQUARE_SIZE; i++) {
            board.add(new ArrayList<CaseColor>(SQUARE_SIZE)); 
            for (int j = 0; j < SQUARE_SIZE; j++){
            	if(i % 2 == j % 2 && i<4)board.get(i).add(CaseColor.BLANC);
            	else if(i % 2 == j % 2 && i>5)board.get(i).add(CaseColor.NOIRE);
            	else board.get(i).add(CaseColor.EMPTY);
            }
        }
	}
	
	
	//Le board est une liste de liste : la liste principale, verticale, contient des listes horizontales
	@Override
	public void play(int abs, int ord, String direction) throws GameException {
		if(getCell(abs,ord) == CaseColor.EMPTY) System.out.println("There is no chip on the board") ;
		else if(getCell(abs,ord) != player) System.out.println("That is not your chip, select a Chip "+ player) ;
		else{
			Direction dir = Direction.valueOf(direction);
			int newAbs = newAbs(abs, dir);
			int newOrd = newOrd(ord, dir, player);
			if (newAbs > getSquareSize() - 1 || newAbs < 0){
				throw new GameException(OUTSIDE_OF_BOARD_ERROR);
			}
			if (newOrd> getSquareSize() - 1 || newOrd < 0){
				throw new GameException(OUTSIDE_OF_BOARD_ERROR);
			}
			//Test move
			if(newAbs != abs && newOrd != ord){
				if(getCell(newAbs,newOrd)== CaseColor.EMPTY){
					if (dir == Direction.AvD || dir == Direction.AvG){
						board.get(ord).set(abs, CaseColor.EMPTY);
						board.get(newOrd).set(newAbs, player);
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
			List<CaseColor> ordList = board.get(ord);
			List<CaseColor> newOrdList = board.get(newOrd);
			if (ordList.size() > SQUARE_SIZE || newOrdList.size() > SQUARE_SIZE ) {
	            throw new GameException(OUTSIDE_OF_BOARD_ERROR);
	        }
		}
		
	}

	@Override
	public ChipColor getCell(int abs, int ord) {	
		return board.get(ord).get(abs);
	}

	@Override
	public CaseColor getWinner() {
		int nbWhiteChip = 0;
		int nbBlackChip = 0;
		
		for (int i = 0; i< SQUARE_SIZE; i++){
			for (int j = 0; j< SQUARE_SIZE; j++){
				if (board.get(i).get(j) == CaseColor.BLANC){
					nbWhiteChip++;
				}
				if (board.get(i).get(j) == CaseColor.NOIRE){
					nbBlackChip++;
				}
			}
		}
		
		if (nbWhiteChip == 0 && nbBlackChip != 0){
			return CaseColor.NOIRE;
		} else if ( nbBlackChip == 0 && nbWhiteChip != 0){
			return CaseColor.BLANC;
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
				/*switch (getCell(i, j)) {
				case NOIRE:
					System.out.print(" "+ ANSI_BLACK);
					break;
				case BLANC:
					System.out.print(" "+ ANSI_WHITE);
					break;
				default:
					System.out.print(" "+ ANSI_YELLOW);
					break;
				}*/
			}
		System.out.print("\n");
		}		
	}


	@Override
	public CaseColor changePlayer(CaseColor player) throws GameException{
		switch (player) {
		case BLANC:
			player = CaseColor.NOIRE;
			break;
		case NOIRE:
			player = CaseColor.BLANC;
			break;
		default:
			throw new GameException(CANT_FIND_PLAYER_ERROR);
		}
		return player;
	}


	@Override
	public void eatChip(int abs, int ord, Direction direction, CaseColor color) throws GameException {
		
		int ordEatenChip = newOrd(ord,direction, player);
		int absEatenChip = newAbs(abs,direction);
		int newOrd = newOrd(ordEatenChip,direction, player);
		int newAbs = newAbs(absEatenChip,direction);
		
		scanner = new Scanner(System.in);
		String command;
		System.out.println("ord : "+ newOrd +", abs :"+ newAbs);
		CaseColor ChipOnNewPosition = getCell(newAbs,newOrd);
		
		if( ChipOnNewPosition == color || ChipOnNewPosition != CaseColor.EMPTY){
			System.out.println("You can't eat this chip, play again");
		}else{
			board.get(ord).set(abs, CaseColor.EMPTY);
			board.get(ordEatenChip).set(absEatenChip, CaseColor.EMPTY);
			board.get(newOrd).set(newAbs, player);
			System.out.println(player + " : you eat a chip");
			
			if(canYouPlayAgain(newAbs,newOrd, player) == true ){
				System.out.println("Player " + player + " you can eat again, enter a direction (Avd, AvG, ArD or ArG) : ");
				command = scanner.next();
				showCurrentBoardStatus();
				play(newAbs,newOrd,command);
			}else{
				player = changePlayer(player);
				System.out.println("Player " + player + " that's your turn");
			}
		}
	}


	@Override
	public int newOrd(int ord, Direction direction, CaseColor player) {
		int newOrd = 0;
		if(player == CaseColor.BLANC){
			switch (direction) {
			case AvD:
				newOrd = ord + 1;
				break;
			case AvG:
				newOrd = ord + 1;
				break;
			case ArD:
				newOrd = ord - 1;
				break;
			case ArG:
				newOrd = ord - 1;
				break;

			default:
				System.out.println(direction + " is not a direction. Directions are : AvD, AvG, ArD and ArG");
				newOrd = ord;
			}
		}else{
			switch (direction) {
			case AvD:
				newOrd = ord - 1;
				break;
			case AvG:
				newOrd = ord - 1;
				break;
			case ArD:
				newOrd = ord + 1;
				break;
			case ArG:
				newOrd = ord + 1;
				break;
	
			default:
				System.out.println(direction + " is not a direction. Directions are : AvD, AvG, ArD and ArG");
				newOrd = ord;
			}
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
			newAbs = abs - 1;
			break;
		case ArD:
			newAbs = abs + 1;
			break;
		case ArG:
			newAbs = abs + 1;
			break;

		default:
			System.out.println(direction + " is not a direction. Directions are : AvD, AvG, ArD and ArG");
			newAbs = abs;
		}
		return newAbs;
	}


	@Override
	public boolean canYouPlayAgain(int abs, int ord, CaseColor color) {
		
		int nextCaseOrd, nextCaseAbs, followingCaseOrd, followingCaseAbs;
		CaseColor NextCase;
		CaseColor FollowingCase;
		
		for(Direction dir : Direction.values()){
			nextCaseAbs = newAbs(abs,dir);
			nextCaseOrd = newOrd(ord, dir, color);
			followingCaseAbs = newAbs(nextCaseAbs,dir);
			followingCaseOrd = newOrd(nextCaseOrd, dir, color);
			NextCase = getCell(nextCaseAbs, nextCaseOrd);
			FollowingCase = getCell(followingCaseAbs,followingCaseOrd);
			
			if(NextCase != CaseColor.EMPTY && NextCase != color){
				if(FollowingCase == CaseColor.EMPTY){
					return true;
				}
			}
		}
		return false;
	}

}
