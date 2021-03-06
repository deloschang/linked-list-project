package PS2;

/**
 * HeaderSLL.java
 * 
 * Implementation of singly linked list.
 * WARNING: This implementation is guaranteed to work only if always given
 * immutable objects (or at least ones that do not change).
 * 
 * @author THC
 * @author Scot Drysdale converted to Java
 * @author Scot Drysdale, THC have made a number of modifications.
 * @author Scot Drysdale most recently modified on 1/12/2011
 * @author Prasad Jayanti changed the interface to CS10ListADT
 * 
 * @author Delos Chang modified to add dummy header and currentPred
 */

public class HeaderSLL<T> implements CS10LinkedList<T> {
  // Instance variables.
  private Element<T> currentPred;    // current position in the list
  private Element<T> head;       // head of list
  
  /**
   * A private class inner representing the elements in the list.
   */
  private static class Element<T> {
    // Because this is a private inner class, these can't be seen from outside SLL.
    private T data;         	// reference to data stored in this element
    private Element<T> next;   // reference to next item in list
    
    /**
     * Constructor for a linked list element, given an object.
     * @param obj the data stored in the element.
     */
    public Element(T obj) {
      next = null;          // no element after this one, yet
      data = obj;           // OK to copy reference, since obj references an immutable object
    }

    /**
     * @return the String representation of a linked list element.
     */
    public String toString() {
      return data.toString();
    }
  }

  /**
   * Constructor to create an empty singly linked list.
   */
  public HeaderSLL() {
	head = new Element<T>(null); // create dummy header
    clear();
  }

  /**
   * @see CS10ListADT#clear()
   */
  public void clear() {
	// pointer to the dummy header 
    currentPred = head;
    head.next = null;
  }

  /**
   * @see CS10ListADT#add()
   */
  public void add(T obj) {
    Element<T> x = new Element<T>(obj);   // allocate a new element

    // There are two distinct cases, depending on whether the list is empty
    // list not empty
    if (hasCurrent()) {
	    x.next = currentPred.next.next;  // fix the next reference for the new element
	    currentPred.next.next = x;       // fix the next reference for current element
	    
	    currentPred = currentPred.next; // make x the new current
	    
    } else { 
    	// list empty
    	x.next = null; // fix next reference for new element
    	currentPred.next = x; // fix next reference for current element
    }
  }

  /**
   *   * @see CS10ListADT#remove()
   */
  public void remove() {
    if (!hasCurrent()) {          // check whether currentPred.next element exists
      System.err.println("No current item");
      return;
    }
    
    currentPred.next = currentPred.next.next; // change next reference 
  }

  /**
   * @return the String representation of this list.
   */
  public String toString() {
    String result = "";
    
    // start from head.next to skip dummy header
    for (Element<T> x = head.next; x != null; x = x.next) 
      result += x.toString() + "\n"; 
    
    return result;
  }

  /**
   * @see CS10ListADT#contains()
   */
  public boolean contains(T obj) {
	  for (currentPred = head; currentPred.next != null && 
			  !currentPred.next.data.equals(obj); currentPred = currentPred.next) 
		  ;

	  // We dropped out of the loop either because we ran off the end of the list
	  // (in which case x == null) or because we found s (and so x != null).

	  // if found, currentPred should be set before searched item
	  return currentPred.next != null;
  }

  /**
   * @see CS10ListADT#isEmpty()
   */
  public boolean isEmpty() {
    return head.next == null; // check after dummy list header
  }
  
  /**
   * @see CS10ListADT#hasCurrent()
   */
  public boolean hasCurrent() {
    return currentPred.next != null; // check if current element exists
  }
  
  /**
   * @see CS10ListADT#hasNext()
   */
  public boolean hasNext() {
    return hasCurrent() && currentPred.next.next != null;
  }
  
  /**
   * @see CS10ListADT#getFirst()
   */
  public T getFirst() { 
    if(isEmpty()) {
      System.err.println("The list is empty");
      return null;
    }
    
    currentPred = head;
    return get();
  }
  
  /**
   * @see CS10ListADT#getLast()
   */
  public T getLast() {
	  if (isEmpty()) {
		  System.err.println("The list is empty");
		  return null;
	  } else {
		  // O(n) without the tail pointer
		  for(currentPred = head; currentPred.next.next != null; currentPred = currentPred.next);
		  // found last element in the list

		  return get();
	  }
  }

  /**
   * @see CS10ListADT#addFirst()
   */
  public void addFirst(T obj) {
    Element<T> x = new Element<T>(obj);   // allocate a new element
	x.next = head.next;
	head.next = x;
	
    currentPred = head; // point to the object just added
  }

  /**
   * @see CS10ListADT#addLast()
   */
  public void addLast(T obj) {
    if(isEmpty())
      addFirst(obj);
    else {
      getLast();
      add(obj);
    }
  }
  
  /**
   * @see CS10ListADT#get()
   */
  public T get() {
    if (hasCurrent()) {
      return currentPred.next.data;
    }
    else {
      System.err.println("No currentPred.next item");
      return null;
    }

  }
  
  /**
   * @see CS10ListADT#set()  
   */
  public void set(T obj) {
    if (hasCurrent())
    	currentPred.next.data = obj;
    else
      System.err.println("No currentPred.next item");
  }
  
  /**
   * @see CS10ListADT#next()
   */
  public T next() {
    if (hasNext()) {
      currentPred = currentPred.next;
      return currentPred.next.data;
    }
    else {
      System.err.println("No next item");
      return null;
    }
  }
}