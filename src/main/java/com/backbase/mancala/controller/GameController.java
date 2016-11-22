/**
 * 
 */
package com.backbase.mancala.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.backbase.mancala.domain.Pile;
import com.backbase.mancala.dto.GameBoard;
import com.backbase.mancala.service.MancalaGame;

/**
 * @author ashraf
 *
 */
@Controller
public class GameController {

	@Autowired
	private MancalaGame mancalaGame;

	@MessageMapping("/start")
	@SendTo("/topic/game")
	public GameBoard start() throws Exception {
		mancalaGame.startNewGame();
		List<Integer> piles = new ArrayList<>();
		mancalaGame.getPiles().forEach(p -> piles.add(p.getNumPebbles()));
		GameBoard gameBoard = new GameBoard(piles, mancalaGame.getGameStatus(),
				mancalaGame.getWinner());
		return gameBoard;
	}
	
	@MessageMapping("/move")
	@SendTo("/topic/game")
	public GameBoard move(Integer currentPile) throws Exception {
		mancalaGame.makeSingleMove(currentPile);
		if(mancalaGame.isGameOver()){
			mancalaGame.determineWinner();
		}
		List<Integer> piles = new ArrayList<>();
		mancalaGame.getPiles().forEach(p -> piles.add(p.getNumPebbles()));
		GameBoard gameBoard = new GameBoard(piles, mancalaGame.getGameStatus(),
				mancalaGame.getWinner());
		return gameBoard;
	}

}