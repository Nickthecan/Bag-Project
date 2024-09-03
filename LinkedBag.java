/** OUTLINE
    A class of bags whose entries are stored in a chain of linked nodes.
    The bag is never full.
    @author Frank M. Carrano, Timothy M. Henry
    @version 5.0
*/
public class LinkedBag<T> implements BagInterface<T> {
	private Node firstNode;       //reference to first node
	private int numberOfEntries;

	public LinkedBag() {
		firstNode = null;
      	numberOfEntries = 0;
	} //end default constructor

	public int getCurrentSize() {
		return numberOfEntries;
	} //end getCurrentSize

	public boolean isEmpty() {
		return numberOfEntries == 0;
	} //end isEmpty

	public boolean add(T newEntry) {
		//Add to beginning of chain:
		Node newNode = new Node(newEntry);
		//Make new node reference rest of chain
		newNode.next = firstNode; 
   		//(firstNode is null if chain is empty)        
		//New node is at beginning of chain
		firstNode = newNode;
		numberOfEntries++;
      	return true;
	} //end add
	
	public T remove() {
		T result = null;

    	if (firstNode != null) {
         	result = firstNode.getData();
        	firstNode = firstNode.getNextNode(); //Remove first node from chain
         	numberOfEntries--;
     	} //end if
      	return result;
	} //end remove
	
	public boolean remove(T anEntry) {
		boolean result = false;
		Node nodeN = getReferenceTo(anEntry);
		
		if (nodeN != null) {
  			//Replace located entry with entry in first node
		   	nodeN.setData(firstNode.getData()); 
  			//Remove first node
		   	firstNode = firstNode.getNextNode(); 
  			numberOfEntries--;
		   	result = true;
		} //end if
		return result;
	} //end remove

	public void clear() {
		while (!isEmpty())
			remove();
	} //end clear
	
	public int getFrequencyOf(T anEntry) {
		int frequency = 0;
		int counter = 0;
		Node currentNode = firstNode;

		while ((counter < numberOfEntries) && (currentNode != null)) {
			if (anEntry.equals(currentNode.getData()))
				frequency++;
			counter++;
			currentNode = currentNode.getNextNode();
		} //end while
		return frequency;
	} //end getFrequencyOf
	
	public boolean contains(T anEntry) {
		boolean found = false;
		Node currentNode = firstNode;

		while (!found && (currentNode != null)) {
			if (anEntry.equals(currentNode.getData()))
				found = true;
			else
				currentNode = currentNode.getNextNode();
		} //end while
		return found;
	} //end contains
	
	public T[] toArray() {
		//the cast is safe because the new aarray contains null entries
		@SuppressWarnings("unchecked")
		T[] results = (T[])new Object[numberOfEntries]; //Unchecked cast
		int index = 0;
		Node currentNode = firstNode;
		
		while ((index < numberOfEntries) && (currentNode != null)) {
			results[index] = currentNode.getData();
			index++;
			currentNode = currentNode.getNextNode();
		} //end while
		return results;
	} //end toArray

	/** The union of two collections consists of their contents combined into a new collection
	 * 
	 * @param insertedBag bag which will be compared to the one being called
	 * @return A new bag the union of the bag receiving the call to the method and the bag that is the method’s one argument
	 */
	public BagInterface<T> union(BagInterface<T> insertedBag) {
		//create a new node in order to traverse through the nodes
		Node bag = firstNode;
		//create an array for the inserted bag
		T[] Bag = insertedBag.toArray();
		//create a new BagInterface<T> result Bag
		BagInterface<T> resultBag = new LinkedBag<>();
		//while loop in order to traverse through the nodes
		while (bag != null) {
		   	resultBag.add(bag.data);
		   	bag = bag.next;
		}
		for (int i = 0; i < Bag.length; i++)
			resultBag.add(Bag[i]);
		//return resultBag
		return resultBag;
	} //end union 
	  
	/** The intersection of two collections consists of a new collection of entries that occur in both collections
	 * 
	 * @param insertedBag bag which will be compared to the one being called
	 * @return A new bag with collections that occur in both bags
	 */
	public BagInterface<T> intersection(BagInterface<T> insertedBag) {
		//create a new node in order to traverse through the nodes
		Node bag = firstNode;
		//create a new BagInterface<T> result Bag
		BagInterface<T> resultBag = new LinkedBag<>();
		//create a copy of the inserted bag so we don't mess with the bag when removing elements
		BagInterface<T> copyInsertedBag = new LinkedBag<>();
		T[] insert = insertedBag.toArray();
		for (int i = 0; i < insertedBag.getCurrentSize(); i++)
		   	copyInsertedBag.add(insert[i]);
		//new array Bag to deal with the comparison
		T[] Bag = copyInsertedBag.toArray();
		//for loop in order to run through bag and compare to see if the inserted bag contains each element
		while(bag != null) {
		   	//if the inserted bag contains the element at index i, add the element to the resultant bag, remove the element from the inserted bag
		   	//to prevent duplicates, and increment to the next number
		   	for (int i = 0; i < Bag.length; i++) {
				if (Bag[i] == firstNode) {
					resultBag.add(Bag[i]);
					bag = firstNode.next;
				}
		   	}
		}
		//return resultBag
		return resultBag;
	} //end intersection
	  
	  /** The difference of two collections is a new collection of the entires that would be left in one collection after removing those that also occur in the second
	   * 
	   * @param insertedBag bag which will be compared to the one being called
	   * @return A new bag with one bag's removed contents that appeared in the other bag
	   */
	  public BagInterface<T> difference(BagInterface<T> insertedBag) {
		//create a new node in order to traverse through the nodes
		Node node = firstNode;
		//create a new BagInterface<T> result Bag
		BagInterface<T> resultBag = new LinkedBag<>();
		//create a copy of the inserted bag so we don't mess with the bag when removing elements
		BagInterface<T> copyInsertedBag = new LinkedBag<>();
		T[] insert = insertedBag.toArray();
		for (int i = 0; i < insertedBag.getCurrentSize(); i++) {
		   copyInsertedBag.add(insert[i]);
		}
		
		//return resultBag
		return resultBag;
	 } //end difference

	//Locates a given entry within this bag.
	//Returns a reference to the node containing the //entry, if 
	//located, or null otherwise.
	private Node getReferenceTo(T anEntry) {
		boolean found = false;
		Node currentNode = firstNode;
		while (!found && (currentNode != null)) {
			if (anEntry.equals(currentNode.getData()))
				found = true;
			else
				currentNode = currentNode.getNextNode();
		} //end while
		return currentNode;
	} //end getReferenceTo

	private class Node {
	   	private T    data; //Entry in bag
	   	private Node next; //Link to next node
      
		private Node(T dataPortion) {
			this(dataPortion, null);
		} //end constructor
		
		private Node(T dataPortion, Node nextNode) {
			data = dataPortion;
			next = nextNode;
		} //end constructor

		private T getData() {
			return data;
		} //end getData
	
		private void setData(T newData) {
			data = newData;
		} //end setData
		
		private Node getNextNode() {
			return next;
		} //end getNextNode
	} //end Node
} //end LinkedBag