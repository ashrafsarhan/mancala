package com.backbase.mancala.service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.backbase.mancala.domain.Pile;
import com.backbase.mancala.dto.GameBoard;
import com.backbase.mancala.dto.GameStatus;
import com.backbase.mancala.dto.Winner;

/**
 * The Class MancalaGame.
 *
 * @author ashraf
 */
@Service
public class MancalaGame implements IBoardGame {
	/**
	 * Description of instance variables player1: boolean that denotes whether
	 * player1 or player2 is allowed to click on a certain pile on the board
	 * turn1: boolean denoting whose turn it is
	 */
	private boolean player1;
	public LinkedList<Pile> piles;
	private boolean isLandedMancala;
	private String gameStatus;
	private String winner;
	private final static int PILE_COUNT = 6;

	/**
	 * Determines whether the game has reached its end, by using isSideEmpty to
	 * check the state of each side of the board
	 * 
	 * @return boolean
	 */
	@Override
	public boolean isGameOver() {
		if (isSideEmpty()) {
			gameStatus = GameStatus.GAME_OVER.getGameStatus();
			return true;
		}else {
			return false;
		}
	}
	
	@Override
	public GameBoard getCurrentGameBoard() {
		List<Integer> pilesCounts = new ArrayList<>();
		piles.forEach(p -> pilesCounts.add(p.getNumPebbles()));
		return new GameBoard(pilesCounts, gameStatus,
				winner);
	}

	/**
	 * Determines whether a side of the board is empty.
	 *
	 * @return true, if is side empty
	 */
	public boolean isSideEmpty() {
		// one of two cases needs to be true:
		// either 0-5 are empty or 7-12 are empty
		if (isTopSideEmpty() || isBottomSideEmpty()) {
			moveAllPebblesToMancala();
			return true;
		} else {
			return false;
		}
	}
	
	public void moveAllPebblesToMancala() {
		int counter = 0;
		 if(isTopSideEmpty()){
			for (int i = 0; i < 6; i++) {
				counter += piles.get(i).getNumPebbles();
				piles.get(i).setNumPebbles(0);
			}
			piles.get(6).setNumPebbles(piles.get(6).getNumPebbles()+counter);
		}else if (isBottomSideEmpty()) {
			for (int i = 7; i < 13; i++) {
				counter += piles.get(i).getNumPebbles();
				piles.get(i).setNumPebbles(0);
			}
			piles.get(13).setNumPebbles(piles.get(13).getNumPebbles()+counter);
		}
	}

	/**
	 * checks if piles 0-5 are empty.
	 *
	 * @return true, if is top side empty
	 */
	public boolean isTopSideEmpty() {
		int counter = 0;
		for (int i = 0; i < 6; i++) {
			if (piles.get(i).isEmpty()) {
				counter++;
			}
		}
		return (counter == 6); // is empty if all 6 are empty
	}

	/**
	 * checks if piles 7-12 are empty.
	 *
	 * @return true, if is bottom side empty
	 */
	public boolean isBottomSideEmpty() {
		int counter = 0;
		for (int i = 7; i < 13; i++) {
			if (piles.get(i).isEmpty()) {
				counter++;
			}
		}
		return (counter == 6); // is empty if all 6 are empty
	}

	/**
	 * Takes a turn by distributing the contents of a chosen pile across the
	 * other piles on the board. Traverses the linked list, popping from the
	 * pile given as a parameter and pushing the popped pebble onto the current
	 * pile.
	 *
	 * @param currentPile the current pile
	 */
	@Override
	public void makeSingleMove(Integer currentPile) {
		// sets isLandedMancala to false just in case
		if (piles.get(currentPile).isEmpty()) {
			// do nothing
		} else if ((player1 == false && currentPile < 6) || (player1 == true && currentPile > 6)) {
			// do nothing
		} else {
			isLandedMancala = false;
			int i = currentPile;
			i++; // increment to get to next pile
			while (!piles.get(currentPile).isEmpty()) {
				if ((player1 == true) && (i == 6)) {
					piles.get(6).addPebble(); // adds pebble to player 1's
												// mancala
					if (piles.get(currentPile).checkIfLastPebble() == true) {
						isLandedMancala = true;
					}
					// we want player1 to go again
					piles.get(currentPile).removePebble(); // need to take into
															// consideration if
															// it's
					// the last one that landed in it
					i++;
				} else if ((player1 == false) && (i == 13)) {
					piles.get(13).addPebble(); // adds pebble to player 2's
												// mancala
					if (piles.get(currentPile).checkIfLastPebble() == true) {
						isLandedMancala = true;
					}
					piles.get(currentPile).removePebble();
					// if the pebble is dropped into an empty pile,
					i++;
				} else if ((player1 == true && i == 13) || (player1 == false && i == 6)) {
					// do nothing
					i++;
				} else if (piles.get(currentPile).checkIfLastPebble() == true && piles.get(i).isEmpty()
						&& isAcrossFull(i) && isOnYourSide(i)) {
					piles.get(currentPile).removePebble();
					if (player1 == true) {
						piles.get(6).addPebble();
					} else {
						piles.get(13).addPebble();
					}

					allotPebbles(i);
					// do not need to update i
				} else {
					piles.get(currentPile).removePebble();

					if (!piles.get(i).isEmpty()) {
						piles.get(i).addPebble();
					} else {
						piles.get(i).addPebble();
						piles.get(i).peek().toggleLastTrue();
					}
					i++;
				}
				if (i == 14) {
					i = 0;
				}

			}
			if (isLandedMancala == false) {
				if (player1 == true) {
					player1 = false;
					gameStatus = GameStatus.P2_Turn.getGameStatus();
				} else {
					player1 = true;
					gameStatus = GameStatus.P1_Turn.getGameStatus();
				}
			}
		}
	}

	/**
	 * Checks whether the pile that has been clicked on is actually on the
	 * current players side.
	 *
	 * @param nationWide the nation wide
	 * @return true, if is on your side
	 */
	public boolean isOnYourSide(Integer nationWide) {
		if (player1 == true && nationWide <= 5) {
			return true;
		} else if (player1 == false && nationWide > 5) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * During a move, checks whether the last pebble to be distributed landed in
	 * an empty pile with pebbles across from it. Will call findAcross() method
	 * in order to find the Pile to check.
	 *
	 * @param thisOne the this one
	 * @return true, if is across full
	 */
	public boolean isAcrossFull(Integer thisOne) {
		Integer pileAcross = 12 - thisOne;
		if (piles.get(pileAcross).isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Redistributes pebbles from a given pile to the mancala of the current
	 * player.
	 *
	 * @param i the i
	 */
	public void allotPebbles(Integer i) {
		while (!piles.get(12 - i).isEmpty()) {
			if (player1 == true) {
				piles.get(12 - i).removePebble();
				piles.get(6).addPebble();
			} else {
				piles.get(12 - i).removePebble();
				piles.get(13).addPebble();
			}
		}
	}

	/**
	 * Constructs a Board with a 12 piles and 2 mancalas in a linked list. The
	 * 12 piles are populated with 4 pebbles each.
	 */
	@Override
	public void startNewGame() {
		piles = new LinkedList<Pile>(); // creates a new list of piles
		// fills one side of piles LinkedList with 6 piles (4 pebbles each)
		for (int i = 0; i < 6; i++) {
			piles.add(new Pile(PILE_COUNT));
		}
		// creates mancala pile
		piles.add(new Pile(0));
		// fills remaining side of piles LinkedList with 6 piles (4 pebbles
		// each)
		for (int j = 0; j < 6; j++) {
			piles.add(new Pile(PILE_COUNT));
		}
		// creates remaining mancala pile
		piles.get(6).toggleMancala(); // this pile becomes a mancala
		piles.add(new Pile(0));
		piles.get(13).toggleMancala(); // this pile becomes a mancala
		isLandedMancala = false;
		player1 = true; // reset to player1's turn
		gameStatus = GameStatus.P1_Turn.getGameStatus();
		winner = "";
	}

	@Override
	public void determineWinner() {
		Integer player1Mancala = piles.get(6).getNumPebbles();
		Integer player2Mancala = piles.get(13).getNumPebbles();
		if (player1Mancala > player2Mancala) {
			winner = Winner.P1.getWinner();
		} else if (player1Mancala == player2Mancala) {
			winner = Winner.TIE.getWinner();
		} else {
			winner = Winner.P2.getWinner();
		}
	}

}
