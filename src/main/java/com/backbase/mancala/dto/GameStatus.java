package com.backbase.mancala.dto;

/**
 * @author ashraf
 *
 */
public enum GameStatus {
	
	P1_Turn("Player 1's Turn!"),  
	P2_Turn("Player 2's Turn!"),
	GAME_OVER("Game is Over!");

    private final String gameStatus;

    private GameStatus(String gameStatus) {
        this.gameStatus = gameStatus;
    }

	public String getGameStatus() {
		return gameStatus;
	}

}
