package PS2;

/**
 * Josephus.java
 * 
 * Program to aid Josephus in figuring out where to stand in the circle
 *   
 * @author Delos Chang, modified for Josephus problem
 */

import java.util.Scanner;


public class Josephus {
  private Soldier head;  // current position in the list
  
  /**
   * A private inner class representing the elements in the list.
   */
  private static class Soldier {
    // Because this is a private inner class, these can be seen from SentinelDLL,
    // but not from outside SentinelDLL.
    private int data;                  // reference to data stored in this element
    private Soldier next;            // reference to next item in list
    private Soldier previous;        // reference to previous item in list
    
    /**
     * Constructor for a linked list element, given an object.
     * @param obj the data in the element.
     */
    public Soldier(int number) {
      next = previous = null;   // no element yet
      data = number;            // identifier for soldier   
    }

    /**
     *  @return the String representation of a Soldier number
     */
    public String toString() {
      return String.valueOf(data);
    }
  }

  /**
   * Constructor for a circular, doubly linked list using soldiers without sentinel
   * @param n number of soldiers
   * @param k skip successive executions
   */
  
  public Josephus(int n, int k) {
	// Initialize number of men 
    start(n);
    
    // Execute according to k
    remove(k, n);
  }

  /* Create number of men and links in doubly circular linked list
   * @param n number of soldiers
   */
  public void start(int n) {
	Soldier temp_soldier = null;
	
	if (n < 2){
		System.err.println("Parameter must be at least 2");
		return;
	}
	
	for (int i = 1; i < n + 1; i++){
		Soldier soldier = new Soldier(n);
		
		// identify each soldier
		soldier.data = i;
		
		//System.out.println("Soldier is born: "+i+" data: "+soldier.data);
		
		// if first soldier, there is no link
		if (i == 1){
			// store reference
			head = soldier;
			temp_soldier = soldier;
			continue;
		}
		
		temp_soldier.next = soldier;
		soldier.previous = temp_soldier;
		
		// renew reference
		temp_soldier = soldier;
		
		// End case 
		if (i == n){
			soldier.next = head;
			head.previous = soldier;
			continue;
		}
	}
  }

  /** Remove every k man from the list
   * @param k skip successive executions
   * @param n number of soldiers
   */
  public void remove(int k, int n) {
	  // First check if k is between 1 and n to guarantee we won't fall off
	  if ((k > 1 && k < n)){
		  Soldier x; 
		  int i = 0;
		  
		  // Stop when there is only 1 soldier left (no next and no previous)
		  for (x = head; x.next != x && x.previous != x; x = x.next){
			  
			  // remove every k counting from Soldier 1
			  if (i == k - 1){
				  
				  // remove soldier from the list by adjusting references
				  x.previous.next = x.next;
				  x.next.previous = x.previous;

				  System.out.println("Soldier "+x.data+" executed. RIP. ");
				  i = 0; // reset
				  continue;
			  }
			  i++;
		  }
		  System.out.println("Last remaining soldier is "+x.data);
	  } else {
	      System.err.println("Parameter must be between 1 and n ");
	  }
  }
	  
  public static void main(String [] args) {
	  int parameter_n, parameter_k ;           
	  Scanner input = new Scanner(System.in); // ask for user inputs
	  
      System.out.print("Enter number n of soldiers, at least 2: ");
      parameter_n = input.nextInt();
      
      System.out.print("Enter spacing of victims, between 1 and n: ");
      parameter_k = input.nextInt();
      
      new Josephus(parameter_n, parameter_k);
  }
}
