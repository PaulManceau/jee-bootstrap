package jeuDames;

import java.util.Scanner;

public class Test {

	private static Scanner scanner;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		GameJDDimpl Partie = new GameJDDimpl();		
		Partie.initBoard();
		int x = 0 , y = 0;
		String move= null;
		boolean moveExists= false;
		System.out.println("Player BLANC begin");
		//Test de méthode showCurrentBoardStatus
		while(Partie.getWinner() == null){

			scanner = new Scanner(System.in);
			Partie.showCurrentBoardStatus();
			System.out.println("Coordinate of your Chip :");
			
			System.out.print("x = ");
			while(!scanner.hasNextInt()) scanner.next();
			x= scanner.nextInt();
			
			System.out.print("y = ");
			while(!scanner.hasNextInt()) scanner.next();
			y= scanner.nextInt();
			
			System.out.print("Move(AvD, AvG, ArD or ArG = ");
			while(!moveExists){
				move = scanner.next();
				for(Direction dir : Direction.values()){
					if(dir.name().equalsIgnoreCase(move)) moveExists=true;
				}	
			}
			Partie.play(x, y, move);
		}
		
		System.out.println("Player :" + Partie.getWinner().toString() + ", YOU WIN !");

	}

}
