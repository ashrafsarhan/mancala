package com.backbase.mancala;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.backbase.mancala.domain.Pile;
import com.backbase.mancala.service.MancalaGame;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MancalaApplicationTests {
	
	@Autowired
	private MancalaGame mancalaGame;

	@Test
	public void testStartNewGame() {
		
		mancalaGame.startNewGame();
		
		List<Pile> piles = mancalaGame.getPiles();
		
		Assert.assertEquals(14, piles.size());
		
		Assert.assertEquals("Error in piles size!", 14, piles.size());
		
		for(int i=0; i<6; i++)
			Assert.assertEquals("Error in the first player pebbles size!", 6, piles.get(i).getNumPebbles());
		
		for(int i=7; i<13; i++)
			Assert.assertEquals("Error in the second player pebbles size!", 6, piles.get(i).getNumPebbles());
		
		Assert.assertEquals("Error in the first player mancala!", 0, piles.get(6).getNumPebbles());
		
		Assert.assertEquals("Error in the second player mancala!", 0, piles.get(13).getNumPebbles());
	}
	
	@Test
	public void testMakeSingleMove() {
		
		mancalaGame.startNewGame();
		
		List<Pile> piles = mancalaGame.getPiles();
		
		mancalaGame.makeSingleMove(0);
		
		for(int i=1; i<6; i++)
			Assert.assertEquals("Error in the first player pebbles size!", 7, piles.get(i).getNumPebbles());
		
		Assert.assertEquals("Error in the first player mancala!", 1, piles.get(6).getNumPebbles());
		
	}
	
	@Test
	public void testIsGameOver() {
		
		mancalaGame.startNewGame();
		
		List<Pile> piles = mancalaGame.getPiles();
		
		for(int i=0; i<6; i++)
			piles.get(i).clearPile();
		
		Assert.assertEquals("Error in isGameOver!", true, mancalaGame.isGameOver());
		
	}
	
	@Test
	public void testDetermineWinner() {
		
		mancalaGame.startNewGame();
		
		//Tie case
		mancalaGame.determineWinner();
		Assert.assertEquals("Error in determineWinner!", "It is a tie!", mancalaGame.getWinner());
		
		//Player 1 won
		List<Pile> piles = mancalaGame.getPiles();
		piles.get(6).setNumPebbles(6);
		mancalaGame.determineWinner();
		Assert.assertEquals("Error in determineWinner!", "Player 1 won!", mancalaGame.getWinner());
	}
}
