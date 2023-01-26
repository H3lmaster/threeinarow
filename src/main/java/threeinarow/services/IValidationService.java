package threeinarow.services;

import threeinarow.models.ThreeInARow;

public interface IValidationService {

	public boolean currentPlayerWon(String[] positionsPlayed, String currentPlayer);
	public boolean isDraw(String[] positionsPlayed);
	public ThreeInARow checkResults(ThreeInARow input);
}
