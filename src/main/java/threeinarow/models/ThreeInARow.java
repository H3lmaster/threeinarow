package threeinarow.models;

public class ThreeInARow {
	
	private String[] positionsPlayed;
	private String currentPlayer;
	private boolean currentPlayerWon;
	private boolean isDraw;
	
	public String[] getPositionsPlayed() {
		return positionsPlayed;
	}
	
	public String getCurrentPlayer() {
		return currentPlayer;
	}
	
	public boolean isCurrentPlayerWon() {
		return currentPlayerWon;
	}
	public void setCurrentPlayerWon(boolean currentPlayerWon) {
		this.currentPlayerWon = currentPlayerWon;
	}
	public boolean isDraw() {
		return isDraw;
	}
	public void setDraw(boolean isDraw) {
		this.isDraw = isDraw;
	}
	
}
