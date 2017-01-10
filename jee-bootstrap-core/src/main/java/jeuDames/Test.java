package jeuDames;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		GameJDDimpl Partie = new GameJDDimpl();		
		Partie.initBoard();
		
		//Test de méthode showCurrentBoardStatus
		Partie.board.get(0).set(0, ChipColor.BLANC);
		Partie.showCurrentBoardStatus();
		
		System.out.println(Partie.getCell(0, 0));
		
		//Test de méthode getWinner
		System.out.println(Partie.getWinner().toString());

	}

}
