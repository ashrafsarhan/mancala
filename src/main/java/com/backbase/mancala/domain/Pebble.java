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
        Description of instance variables:
 last: boolean used to mark the last pebble in the stack
       This will help to determine when a turn ends.

    */

    //private String color;
    private boolean last;

    /**
       Pebble Constructor
    */
    public Pebble() {
 last=false;
    }

    /**
       Checks the status of last boolean, and switches it to true
       if false and false if true
    */
    public void toggleLastTrue() {
      last=true;
    }
    
    public void toggleLastFalse() {
      last=false;      
    }

    /**
       Checks whether the last pebble landed in a mancala
    */
    public boolean isLandedMancala() {
return false;
    }

    public boolean isLast(){
      return last;
    }
    /**
       toString method that returns the state of the last boolean
    */
    public String toString() {
 return "0 "+last;
    }

    /**
       Main method, contains code to test the Pebble methods
    */
    public static void main(String[] args) {
 Pebble bluh = new Pebble();
 System.out.println(bluh);
    }

}
