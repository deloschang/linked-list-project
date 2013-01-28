package PS2;

/**
 * CatWorld.java
 * 
 * Modified Josephus problem with 9 lives cats
 *   
 * @author Delos Chang, modified for Josephus problem with cats
 */

import java.util.Scanner;


public class CatWorld {
  private Cat head;  // current position in the list
  
  /**
   * A private inner class representing the elements in the list.
   */
  private static class Cat {
    // Because this is a private inner class, these can be seen from SentinelDLL,
    // but not from outside SentinelDLL.
    private int data;                  // reference to data stored in this element
    private Cat next;            // reference to next item in list
    private Cat previous;        // reference to previous item in list
    private int lifeCount;		// number of lives the cats have
    
    /**
     * Constructor for a linked list element, given an object.
     * @param obj the data in the element.
     */
    public Cat(int number) {
      next = previous = null;   // no element yet
      data = number;            // identifier for cat   
    }

    /**
     *  @return the String representation of a Cat number
     */
    public String toString() {
      return String.valueOf(data);
    }
  }

  /**
   * Constructor for a circular, doubly linked list using cats without sentinel
   * @param n number of cats
   * @param k skip successive executions
   */
  
  public CatWorld(int n, int k) {
	// Initialize number of men 
    start(n);
    
    // Execute according to k
    remove(k, n);
  }

  /* Create number of men and links in doubly circular linked list
   * @param n number of cats
   */
  public void start(int n) {
	Cat temp_cat = null;
	
	if (n < 2){
		System.err.println("Parameter must be at least 2");
		return;
	}
	
	for (int i = 1; i < n + 1; i++){
		Cat cat = new Cat(n);
		
		// identify each cat
		cat.data = i;
		cat.lifeCount = i; // give cat i life
		
		// if first cat, there is no link
		if (i == 1){
			// store reference
			head = cat;
			temp_cat = cat;
			continue;
		}
		
		temp_cat.next = cat;
		cat.previous = temp_cat;
		
		// renew reference
		temp_cat = cat;
		
		// End case 
		if (i == n){
			cat.next = head;
			head.previous = cat;
			continue;
		}
	}
  }

  /** Remove every k man from the list
   * @param k skip successive executions
   * @param n number of cats
   */
  public void remove(int k, int n) {
	  // First check if k is between 1 and n to guarantee we won't fall off
	  if ((k > 1 && k < n)){
		  Cat x; 
		  int i = 0;
		  
		  // Stop when there is only 1 cat left (no next and no previous)
		  for (x = head; x.next != x && x.previous != x; x = x.next){
			  
			  // remove every k counting from Cat 1
			  if (i == k - 1){
				  
				  if (x.lifeCount == 0){
					  // remove cat from the list by adjusting references
					  x.previous.next = x.next;
					  x.next.previous = x.previous;
					  System.out.println("Cat "+x.data+" executed. RIP. ");
				  }

				  x.lifeCount--;
				  i = 0; // reset
				  continue;
			  }
			  i++;
		  }
		  System.out.println("Last remaining cat is "+x.data);
	  } else {
	      System.err.println("Parameter must be between 1 and n ");
	  }
  }
	  
  public static void main(String [] args) {
	  int parameter_n, parameter_k ;           
	  Scanner input = new Scanner(System.in); // ask for user inputs
	  
      System.out.print("Enter number n of cats, at least 2: ");
      parameter_n = input.nextInt();
      
      System.out.print("Enter spacing of victims, between 1 and n: ");
      parameter_k = input.nextInt();
      
      new CatWorld(parameter_n, parameter_k);
  }
}
