/**
 * 
 */
package com.backbase.mancala.domain;

/**
 * The Class Pebble.
 *
 * @author ashraf
 */
public class Pebble {
	/**
	 * Description of instance variables: last: boolean used to mark the last
	 * pebble in the stack This will help to determine when a turn ends.
	 * 
	 */
	private boolean last;

	/**
	 * Pebble Constructor.
	 */
	public Pebble() {
		last = false;
	}

	/**
	 * Checks the status of last boolean, and switches it to true if false and
	 * false if true.
	 */
	public void toggleLastTrue() {
		last = true;
	}

	/**
	 * Toggle last false.
	 */
	public void toggleLastFalse() {
		last = false;
	}

	/**
	 * Checks whether the last pebble landed in a mancala.
	 *
	 * @return true, if is landed mancala
	 */
	public boolean isLandedMancala() {
		return false;
	}

	/**
	 * Checks if is last.
	 *
	 * @return true, if is last
	 */
	public boolean isLast() {
		return last;
	}

}
