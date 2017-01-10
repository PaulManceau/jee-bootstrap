package jeuDames;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		GameJDDimpl Partie = new GameJDDimpl();		
		Partie.initBoard();
		
		Partie.board.get(0).set(0, ChipColor.BLANC);
		Partie.showCurrentBoardStatus();		

	}

}
