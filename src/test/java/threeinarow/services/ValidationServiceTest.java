package threeinarow.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ValidationServiceTest {

	private static final String playerX = "X";
	private static final String playerO = "O";
	
	@Autowired
	private IValidationService validationService;
	
	/**
	 * Checks if there is only one position played and that the 
	 * player is the X.
	 */
	@Test
	public void checkFirstPlayIsX() {
		String[] positionsPlayed = new String[9];
		positionsPlayed[5] = playerX;
		int playCount = 0, nullCount = 0;
		
		for (String play : positionsPlayed) {
			if ( play != null && playerX.equalsIgnoreCase(play) ) {
				playCount++;
			}
		}
		
		for (String play : positionsPlayed) {
			if ( play != null && playerO.equalsIgnoreCase(play) ) {
				playCount++;
			}
			if ( play == null ) {
				nullCount++;
			}
		}
		
		// Confirm that there is only one played position in the game
		assertEquals(playCount, 1);
		assertEquals(nullCount, 8);
	}
	
	/**
	 * Checks if there is two positions played and that both 
	 * players played.
	 */
	@Test
	public void checkBothPlayersPlayedOnce() {
		String[] positionsPlayed = new String[9];
		positionsPlayed[1] = playerO;
		positionsPlayed[5] = playerX;
		int playCount = 0;
		
		for (String play : positionsPlayed) {
			if ( play != null && playerX.equalsIgnoreCase(play) ) {
				playCount++;
			}
		}
		
		for (String play : positionsPlayed) {
			if ( play != null && playerO.equalsIgnoreCase(play) ) {
				playCount++;
			}
		}
		
		// Confirm that there is only one played position in the game
		assertEquals(playCount, 2);
	}
	
	/**
	 * The game is a draw if all the positions are filled and no player 
	 * get a sequence that matches the possible winning positions.
	 */
	@Test
	public void drawGame() {
		String[] positionsPlayed = new String[9];
		positionsPlayed[0] = playerX;
		positionsPlayed[4] = playerO;
		positionsPlayed[1] = playerX;
		positionsPlayed[2] = playerO;
		positionsPlayed[6] = playerX;
		positionsPlayed[3] = playerO;
		positionsPlayed[5] = playerX;
		positionsPlayed[7] = playerO;
		positionsPlayed[8] = playerX;
		
		assertTrue(validationService.isDraw(positionsPlayed));
		assertFalse(validationService.currentPlayerWon(positionsPlayed, playerX));
		assertFalse(validationService.currentPlayerWon(positionsPlayed, playerO));
		
	}
	
	/**
	 * The player X wins if he gets a sequence that matches
	 * any of the possible winning positions.
	 */
	@Test
	public void playerXWon() {
		String[] positionsPlayed = new String[9];
		positionsPlayed[0] = playerX;
		positionsPlayed[1] = playerO;
		positionsPlayed[6] = playerX;
		positionsPlayed[3] = playerO;
		positionsPlayed[8] = playerX;
		positionsPlayed[7] = playerO;
		positionsPlayed[4] = playerX;
		
		// Confirms that player X won the game
		assertTrue(validationService.currentPlayerWon(positionsPlayed, playerX));
		assertFalse(validationService.currentPlayerWon(positionsPlayed, playerO));
	
	}
	
	/**
	 * The player O wins if he gets a sequence that matches
	 * any of the possible winning positions.
	 */
	@Test
	public void playerOWon() {
		String[] positionsPlayed = new String[9];
		positionsPlayed[1] = playerX;
		positionsPlayed[0] = playerO;
		positionsPlayed[2] = playerX;
		positionsPlayed[6] = playerO;
		positionsPlayed[5] = playerX;
		positionsPlayed[3] = playerO;
		
		// Confirms that player O won the game
		assertTrue(validationService.currentPlayerWon(positionsPlayed, playerO));
		assertFalse(validationService.currentPlayerWon(positionsPlayed, playerX));
		
	}
	
	
}
