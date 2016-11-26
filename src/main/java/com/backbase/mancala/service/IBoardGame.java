/**
 * 
 */
package com.backbase.mancala.service;

import com.backbase.mancala.dto.GameBoard;

/**
 * The Interface IBoardGame.
 *
 * @author ashraf
 */
public interface IBoardGame {

	public void determineWinner();

	public void startNewGame();

	public boolean isGameOver();

	public GameBoard getCurrentGameBoard();

	public void makeSingleMove(Integer currentPile);

}
