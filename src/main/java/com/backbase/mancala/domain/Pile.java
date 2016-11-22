/**
 * 
 */
package com.backbase.mancala.domain;

import java.util.Stack;

/**
 * @author ashraf
 *
 */
public class Pile {
    /**
       Description of instance variables
       pebbles: a stack of pebbles
       pebblecount: an int that keeps track of the number of pebbles
       in the stack
       isMancala: boolean that denotes whether the pile is a "mancala,"
       and whether pebbles should be deposited there during a turn
    */
    private Stack<Pebble> pebbles;
   // private Stack<String> int;
    private int pebbleCount;
    public boolean isMancala;//the mancalas can either be in the linked list
    //and have booleans or be separate instance variables/piles
 

    /**
       Constructs a Pile, given a Stack of Pebbles and an int, the
       number of pebbles.
       @param an int, the number of pebbles to add 
    */
    public Pile(Integer numPiles) {
     //int= new Stack<String>();
      pebbleCount=numPiles;
      pebbles = new Stack<Pebble>();
      if(numPiles>0){
        pebbles.push(new Pebble());
 pebbles.peek().toggleLastTrue();
 numPiles--;
 while(numPiles>0){
 pebbles.push(new Pebble());
 numPiles--;
        }
 //System.out.println(pebbles);
 //setNumPebbles(numPebbles);
 isMancala=false;
    }
    }

    //instance methods
    /**
       Changes pile to mancala
    */
    public void toggleMancala() {
 isMancala=true;
    }

    /**
       toString method that returns the contents of the pebbles stack
    */
    //amend this to also  return the state of the instance variables?
    public String toString() {
 return pebbles.toString();
    }

    /**
       Returns the number of pebbles in a given pile
       @return An int representing the number of pebbles in a pile
    */
    public int getNumPebbles() {
 return pebbleCount;
    }

   /**
       Clears the pile -tentative method.
       When someone chooses a pile, this could clear
       it before resetting the number of pebbles in the
       other piles, but because we are using stacks I think 
       it may make more sense to pop and push the pebbles
       onto the stacks as needed.
    */
    //clear pebbles in pile
    public void clearPile() {
 pebbles.clear();
 pebbleCount=0;
    }

    public void addPebble(){
      Pebble p1= new Pebble();
      pebbles.push(p1);
      pebbleCount++;
    }
    
    public void removePebble(){
      pebbles.pop();
      pebbleCount--;
    }
    
    public Pebble peek() {
      return pebbles.peek();
    }
    
    public boolean checkIfLastPebble() {
      return pebbles.peek().isLast();
       
    }
    /**
       Used in constructor to set the number of
       pebbles in a given pile
       @param An int the number of pebbles to add
    */
    //set number of pebbles in pile
    
    //this doesn't work, currently not being used anywhere
    public void setNumPebbles(int numPebbles) {
      System.out.println("test");
   /**   if(!pebbles.isEmpty()){
 pebbles.clear();
      } else{*/
 System.out.println("test2");
 pebbleCount = numPebbles;
 Pebble peb = new Pebble();
 //peb.toggleLastTrue();
 pebbles.push(peb);
 System.out.println("here");
 numPebbles--;
 while(numPebbles>0) {
     pebbles.push(new Pebble());
     numPebbles--;
 }
    }
  //  }
   
    /**
       Checks to see if a pile is empty-Will be used in the
       check to see if the pile across the board is empty.
    */
    public boolean isEmpty() {
 return pebbles.empty();
    }

    /**
       Main method to test Pile methods.
    */
    public static void main(String[] args) {
 Pile nah = new Pile(4);
 System.out.println(nah);
// nah.clearPile();
  System.out.println(nah.getNumPebbles());
 nah.removePebble();
 System.out.println(nah.getNumPebbles());
 System.out.println(nah.checkIfLastPebble());
 nah.removePebble();
 nah.removePebble();
 System.out.println(nah);
 System.out.println(nah.checkIfLastPebble());
 nah.addPebble();
 System.out.println(nah.getNumPebbles());
  System.out.println(nah.peek());
 
 Pile wow= new Pile(6);
 //nah.pebbles.push(new Pebble());
 //System.out.println(nah);
    }
} 
