package jeuDames;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		GameJDDimpl Partie = new GameJDDimpl();		
		Partie.initBoard();
		
		Partie.board.get(0).set(0, ChipColor.BLANC);
		
		for (int i = 0; i<10; i++){
			for (int j = 0; j< 10; j++){
				System.out.println(Partie.board.get(i).get(j));
			}
		}

	}

}
