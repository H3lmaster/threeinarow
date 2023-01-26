package threeinarow.services;

public interface IValidationService {

	public boolean currentPlayerWon(String[] positionsPlayed, String currentPlayer);
	public boolean isDraw(String[] positionsPlayed);
}
