package threeinarow.services;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.stereotype.Service;

@Service
public class ValidationService implements IValidationService {
	
	static final String playerX = "X";
	static final String playerO = "O";
	private static final Integer[][] winningPositions = { { 0, 1, 2 }, { 3, 4, 5 }, { 6, 7, 8 }, 
													  { 0, 3, 6 }, { 1, 4, 7 }, { 2, 5, 8 }, 
													  { 0, 4, 8 }, { 2, 4, 6 } }; 
	
	/**
	 * Checks if the current player won the game. 
	 * @param positionsPlayed
	 * @param currentPlayer
	 * @return
	 */
	public boolean currentPlayerWon(String[] positionsPlayed, String currentPlayer) {
		
		if ( positionsPlayed == null || currentPlayer == null ) return false;
		
		boolean currentPlayerWon = false;
		
		List<Integer> indexesPlayedByCurrentPlayer = IntStream.range(0, positionsPlayed.length).filter(i -> currentPlayer == positionsPlayed[i]).boxed().collect(Collectors.toList());
		

		for (Integer[] match : winningPositions) {
			currentPlayerWon = indexesPlayedByCurrentPlayer.containsAll(Arrays.asList(match));
			
			if ( currentPlayerWon ) break;
		}
		
		return currentPlayerWon;
	}
	
	/**
	 * Checks if all the positions are played and no player won.
	 * @param positionsPlayed
	 * @return
	 */
	public boolean isDraw(String[] positionsPlayed) {
		
		if ( positionsPlayed == null ) return false;
		
		boolean playerXWon = false, playerOWon = false;
		
		List<Integer> indexesPlayedByX = IntStream.range(0, positionsPlayed.length).filter(i -> playerX == positionsPlayed[i]).boxed().collect(Collectors.toList());
		List<Integer> indexesPlayedByO = IntStream.range(0, positionsPlayed.length).filter(i -> playerO == positionsPlayed[i]).boxed().collect(Collectors.toList());
		

		for (Integer[] match : winningPositions) {
			playerXWon = indexesPlayedByX.containsAll(Arrays.asList(match));
			playerOWon = indexesPlayedByO.containsAll(Arrays.asList(match));
			
			if ( playerOWon || playerXWon ) break;
		}
		
		return !playerOWon && !playerXWon && (indexesPlayedByO.size() + indexesPlayedByX.size()) == positionsPlayed.length;
	}
}
