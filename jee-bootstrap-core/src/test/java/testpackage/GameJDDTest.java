package testpackage;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Fail.fail;
import static org.junit.Assert.*;

import java.util.WeakHashMap;

import org.assertj.core.api.Fail;
import org.junit.Before;
import org.junit.Test;

import jeuDames.CaseColor;
import jeuDames.Direction;
import jeuDames.GameException;
import jeuDames.GameJDD;
import jeuDames.GameJDDimpl;


public class GameJDDTest {

	private GameJDD game;
	
	@Before
    public void doBefore() throws Exception {
		game = new GameJDDimpl();
    }
	
	@Test
	public void aBoardCanBeCreated(){
		game.initBoard();
		assertThat(game.getCell(0, 0)).isEqualTo(CaseColor.BLANC);
		assertThat(game.getCell(1, 0)).isEqualTo(CaseColor.EMPTY);
		assertThat(game.getCell(2, 0)).isEqualTo(CaseColor.BLANC);
		assertThat(game.getCell(9, 9)).isEqualTo(CaseColor.NOIRE);
		assertThat(game.getCell(8, 9)).isEqualTo(CaseColor.EMPTY);
	}
	
	@Test
	public void aPlayerHasSelectHisChip() throws Exception {
		game.initBoard();
		//On joue en tant que blanc
		game.play(1, 3, "AvD");
		assertThat(game.getCell(1, 3)).isEqualTo(CaseColor.EMPTY);
		assertThat(game.getCell(2, 4)).isEqualTo(CaseColor.BLANC);
		//On veut jouer une deuxième fois avec un pion blanc, ça ne devrait pas être possible
		game.play(2, 4, "AvD");
		assertThat(game.getCell(2, 4)).isEqualTo(CaseColor.BLANC);
		assertThat(game.getCell(3, 5)).isEqualTo(CaseColor.EMPTY);
		//On veut pouvoir jouer du coup avec un pion noir
		game.play(8, 6, "AvD");
		assertThat(game.getCell(8, 6)).isEqualTo(CaseColor.EMPTY);
		assertThat(game.getCell(9, 5)).isEqualTo(CaseColor.NOIRE);
	}
	@Test
	public void aPlayerCanMoveChip() throws Exception {
		game.initBoard();
		//Un pion blanc peut bouger s'il n'y a personne sur la case qu'il vise
		game.play(1, 3, "AvD");
		assertThat(game.getCell(1, 3)).isEqualTo(CaseColor.EMPTY);
		assertThat(game.getCell(2, 4)).isEqualTo(CaseColor.BLANC);
		//Un pion noir ne peut pas bouger sur une case déjà occupée par un pion de même couleur
		game.play(1, 9, "AvD");
		assertThat(game.getCell(1, 9)).isEqualTo(CaseColor.NOIRE);
	}
	@Test
	public void aPlayerCantPlayOutsideTheBoard() throws Exception {
		game.initBoard();
		try {
			game.play(0, 0, "AvG");
			fail("Should throw exception");	
		} catch (GameException e) {
			assert (e.getMessage().contains("outside of the board"));
		}	
	}
	@Test
	public void aPlayerCanEatAChip() throws Exception {
		//on tente de manger un piont de la même couleur
		game.initBoard();
		game.play(1, 3, "AvD");
		game.play(4, 6, "AvG");
		game.play(3,3, "AvG");
		assertThat(game.getCell(3, 3)).isEqualTo(CaseColor.BLANC);
		assertThat(game.getCell(1, 5)).isEqualTo(CaseColor.EMPTY);
		//On retente en mangeant un piont d'une autre couleur sans autre piont derrière
		game.play(2,4, "AvD");
		assertThat(game.getCell(2, 4)).isEqualTo(CaseColor.EMPTY);
		assertThat(game.getCell(3, 5)).isEqualTo(CaseColor.EMPTY);
		assertThat(game.getCell(4, 6)).isEqualTo(CaseColor.BLANC);
		//Cette fois ci le piont d'autre couleur est "couvert" par d'autres pionts
		game.play(6,6, "AvD");
		game.play(4,6, "AvD");
		assertThat(game.getCell(4, 6)).isEqualTo(CaseColor.BLANC);
		
	}
	
	@Test
	public void aBoardCanBeCleard(){
		game.initBoard();
		game.clearBord();
		assertThat(game.getCell(0, 0)).isEqualTo(CaseColor.EMPTY);
		assertThat(game.getCell(9, 9)).isEqualTo(CaseColor.EMPTY);
	}
	
	@Test
	public void aChipCanBeSet() {
		game.initBoard();
		game.clearBord();
		game.setChip(1, 0, CaseColor.BLANC);
		game.setChip(2, 0, CaseColor.NOIRE);
		assertThat(game.getCell(1,0)).isEqualTo(CaseColor.BLANC);
		assertThat(game.getCell(2,0)).isEqualTo(CaseColor.NOIRE);
	}
	
	@Test
	public void aPlayerCanWin(){
		game.initBoard();
		game.clearBord();
		game.setChip(2, 2, CaseColor.BLANC);
		game.setChip(3, 3, CaseColor.NOIRE);
		game.play(2, 2, "AvD");
		assertThat(game.getCell(2, 2)).isEqualTo(CaseColor.EMPTY);
		assertThat(game.getCell(4, 4)).isEqualTo(CaseColor.BLANC);
		assertThat(game.getWinner()).isEqualTo(CaseColor.BLANC);
		
	}
	
}
