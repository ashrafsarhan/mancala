package com.backbase.mancala.domain;

import java.util.Stack;

/**
 * The Class Pile.
 *
 * @author ashraf
 */
public class Pile {
	/**
	 * Description of instance variables pebbles: a stack of pebbles
	 * pebblecount: an int that keeps track of the number of pebbles in the
	 * stack isMancala: boolean that denotes whether the pile is a "mancala,"
	 * and whether pebbles should be deposited there during a turn
	 */
	private Stack<Pebble> pebbles;
	private int pebbleCount;
	public boolean isMancala;// the mancalas can either be in the linked list
	// and have booleans or be separate instance variables/piles

	/**
	 * Constructs a Pile, given a Stack of Pebbles and an int, the number of
	 * pebbles.
	 *
	 * @param numPiles the num piles
	 */
	public Pile(Integer numPiles) {
		pebbleCount = numPiles;
		pebbles = new Stack<Pebble>();
		if (numPiles > 0) {
			pebbles.push(new Pebble());
			pebbles.peek().toggleLastTrue();
			numPiles--;
			while (numPiles > 0) {
				pebbles.push(new Pebble());
				numPiles--;
			}
			isMancala = false;
		}
	}

	/**
	 * Changes pile to mancala.
	 */
	public void toggleMancala() {
		isMancala = true;
	}

	/**
	 * Returns the number of pebbles in a given pile
	 * 
	 * @return An int representing the number of pebbles in a pile
	 */
	public int getNumPebbles() {
		return pebbleCount;
	}

	/**
	 * Clears the pile -tentative method. When someone chooses a pile, this
	 * could clear it before resetting the number of pebbles in the other piles,
	 * but because we are using stacks I think it may make more sense to pop and
	 * push the pebbles onto the stacks as needed.
	 */
	public void clearPile() {
		pebbles.clear();
		pebbleCount = 0;
	}

	/**
	 * Adds the pebble.
	 */
	public void addPebble() {
		Pebble p1 = new Pebble();
		pebbles.push(p1);
		pebbleCount++;
	}

	/**
	 * Removes the pebble.
	 */
	public void removePebble() {
		pebbles.pop();
		pebbleCount--;
	}

	/**
	 * Peek.
	 *
	 * @return the pebble
	 */
	public Pebble peek() {
		return pebbles.peek();
	}

	/**
	 * Check if last pebble.
	 *
	 * @return true, if successful
	 */
	public boolean checkIfLastPebble() {
		return pebbles.peek().isLast();

	}

	/**
	 * Set the number of pebbles in a given pile.
	 *
	 * @param numPebbles the new num pebbles
	 */
	public void setNumPebbles(int numPebbles) {
		pebbleCount = numPebbles;
		Pebble peb = new Pebble();
		pebbles.push(peb);
		numPebbles--;
		while (numPebbles > 0) {
			pebbles.push(new Pebble());
			numPebbles--;
		}
	}

	/**
	 * Checks to see if a pile is empty-Will be used in the check to see if the
	 * pile across the board is empty.
	 */
	public boolean isEmpty() {
		return pebbles.empty();
	}

}
