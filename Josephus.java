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
      next = previous = null;   // no element before or after this one, yet
      data = number;               // OK to copy reference, since obj references an immutable object
    }

    /**
     *  @return the String representation of a linked list element.
     */
    public String toString() {
      return String.valueOf(data);
    }
  }

  /**
   * Constructor for a circular, doubly linked list without sentinel
   * @param n number of soldiers
   * @param k skip successive executions
   */
  
  public Josephus(int n, int k) {
	// Initialize number of men 
    start(n);
    
    // Remove every k men
    Soldier remaining = remove(k);
    
    // Print result
    System.out.println("The last remaining soldier is "+remaining.data);
  }

  /* Create number of men and links in doubly circular linked list
   * @param n number of soldiers
   */
  public void start(int n) {
	Soldier temp_soldier = null;
	
	for (int i = 1; i < n + 1; i++){
		Soldier soldier = new Soldier(n);
		
		// identify each soldier
		soldier.data = i;
		
		System.out.println("Soldier is born: "+i+" data: "+soldier.data);
		
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
   */
  public Soldier remove(int k) {
	  // find Kth soldier
	  
	  // First check if k is between 1 and n to guarantee we won't fall off
	  
	  Soldier x; 
	  int i = 0;
	  for (x = head; x.next != x && x.previous != x; x = x.next){
		  // remove every k starting from 1
		  if (i == k-1){
			  // remove 
			  System.out.println("Soldier "+x);
			  x.previous.next = x.next;
			  x.next.previous = x.previous;
			  
			  System.out.println("Soldier "+x.data+" executed. RIP. ");
			  i = 0;
			  continue;
		  }
		  i++;
		  System.out.println(i);
		  System.out.println("Soldier "+x);
	  }
	  
	  return x;
  }
	  
  public static void main(String [] args) {
	  int parameter_n, parameter_k ;           // a command
	  Scanner input = new Scanner(System.in);
	  
      System.out.print("Enter number n of soldiers, at least 2: ");
      parameter_n = input.nextInt();
      
      System.out.print("Enter spacing of victims, between 1 and n: ");
      parameter_k = input.nextInt();
      
      new Josephus(parameter_n, parameter_k);
  }
}
