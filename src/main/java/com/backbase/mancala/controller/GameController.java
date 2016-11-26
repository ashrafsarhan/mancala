package com.backbase.mancala.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.backbase.mancala.dto.GameBoard;
import com.backbase.mancala.service.IBoardGame;

/**
 * The Class GameController.
 *
 * @author ashraf
 */
@Controller
public class GameController {

	@Autowired
	private IBoardGame mancalaGame;

	/**
	 * Start.
	 *
	 * @return the game board
	 * @throws Exception the exception
	 */
	@MessageMapping("/start")
	@SendTo("/topic/game")
	public GameBoard start() throws Exception {
		mancalaGame.startNewGame();
		GameBoard gameBoard = mancalaGame.getCurrentGameBoard();
		return gameBoard;
	}
	
	/**
	 * Move.
	 *
	 * @param currentPile the current pile
	 * @return the game board
	 * @throws Exception the exception
	 */
	@MessageMapping("/move")
	@SendTo("/topic/game")
	public GameBoard move(Integer currentPile) throws Exception {
		mancalaGame.makeSingleMove(currentPile);
		if(mancalaGame.isGameOver()){
			mancalaGame.determineWinner();
		}
		GameBoard gameBoard = mancalaGame.getCurrentGameBoard();
		return gameBoard;
	}

}