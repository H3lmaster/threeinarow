package threeinarow.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ValidationService {

	private static final String playerX = "X";
	private static final String playerO = "O";
	private static final Integer[][] winningPositions = { { 0, 1, 2 }, { 3, 4, 5 }, { 6, 7, 8 }, 
													  { 0, 3, 6 }, { 1, 4, 7 }, { 2, 5, 8 }, 
													  { 0, 4, 8 }, { 2, 4, 6 } }; 
	
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
		
		List<Integer> indexesPlayedByX = IntStream.range(0, positionsPlayed.length).filter(i -> playerX == positionsPlayed[i]).boxed().collect(Collectors.toList());
		List<Integer> indexesPlayedByO = IntStream.range(0, positionsPlayed.length).filter(i -> playerO == positionsPlayed[i]).boxed().collect(Collectors.toList());
		
		for (Integer[] match : winningPositions) {
			assertFalse(indexesPlayedByX.containsAll(Arrays.asList(match)));
			assertFalse(indexesPlayedByO.containsAll(Arrays.asList(match)));
		}
		
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
		
		boolean playerXWon = false, playerOWon = false;
		
		List<Integer> indexesPlayedByX = IntStream.range(0, positionsPlayed.length).filter(i -> playerX == positionsPlayed[i]).boxed().collect(Collectors.toList());
		List<Integer> indexesPlayedByO = IntStream.range(0, positionsPlayed.length).filter(i -> playerO == positionsPlayed[i]).boxed().collect(Collectors.toList());
		

		for (Integer[] match : winningPositions) {
			playerXWon = indexesPlayedByX.containsAll(Arrays.asList(match));
			playerOWon = indexesPlayedByO.containsAll(Arrays.asList(match));
			
			if ( playerOWon || playerXWon ) break;
		}
		
		// Confirms that player X won the game
		assertTrue(playerXWon);
		assertFalse(playerOWon);
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
		
		boolean playerXWon = false, playerOWon = false;
		
		List<Integer> indexesPlayedByX = IntStream.range(0, positionsPlayed.length).filter(i -> playerX == positionsPlayed[i]).boxed().collect(Collectors.toList());
		List<Integer> indexesPlayedByO = IntStream.range(0, positionsPlayed.length).filter(i -> playerO == positionsPlayed[i]).boxed().collect(Collectors.toList());
		

		for (Integer[] match : winningPositions) {
			playerXWon = indexesPlayedByX.containsAll(Arrays.asList(match));
			playerOWon = indexesPlayedByO.containsAll(Arrays.asList(match));
			
			if ( playerOWon || playerXWon ) break;
		}
		
		// Confirms that player O won the game
		assertFalse(playerXWon);
		assertTrue(playerOWon);
		
	}
	
	
}
