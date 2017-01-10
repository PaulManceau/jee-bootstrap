package testpackage;

//import static org.assertj.core.api.Assertions.assertThat;
//import static org.assertj.core.api.Fail.fail;
//import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import jeuDames.GameJDD;
import jeuDames.GameJDDimpl;


public class GameJDDTest {

	private GameJDD game;
	
	@Before
    public void doBefore() throws Exception {
		game = new GameJDDimpl();
    }
	
	
	@Test
	public void aPlayerHasSelecthisChip() throws Exception {
		//joueur blanc indique piont Blanc
	}
	@Test
	public void aPlayerCanMoveChip() throws Exception {
		//test direction sélectionnée
	}
	@Test
	public void aPlayerCantPlayOutsideTheBoard() throws Exception {
		
	}
	@Test
	public void aPlayerCanEatAChip() throws Exception {
		//test couleur et position
	}
	
	
	
}
