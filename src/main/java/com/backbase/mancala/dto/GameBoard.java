package com.backbase.mancala.dto;

import java.util.List;

/**
 * @author ashraf
 *
 */
public class GameBoard {
	
	public List<Integer> piles;
	private String gameStatus;
	private String winner;
	
	public GameBoard(List<Integer> piles, String gameStatus, String winner) {
		this.piles = piles;
		this.gameStatus = gameStatus;
		this.winner = winner;
	}
	
	public List<Integer> getPiles() {
		return piles;
	}
	public void setPiles(List<Integer> piles) {
		this.piles = piles;
	}
	public String getGameStatus() {
		return gameStatus;
	}
	public void setGameStatus(String gameStatus) {
		this.gameStatus = gameStatus;
	}
	public String getWinner() {
		return winner;
	}
	public void setWinner(String winner) {
		this.winner = winner;
	}

}
