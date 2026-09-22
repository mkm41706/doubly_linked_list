/**
 * A generic doubly-linked-list that keeps items in sorted order.
 * Each node stores a value along with next and back pointers,
 * allowing the list to be traversed through in either direction.
 * Supports inserting, deleting, printing, reversing, deleting a
 * range of values, and swapping every pair of nodes.
 */
public class DoublyLinkedList<T extends Comparable<T>> {

    // Points to the first node in the list
    private NodeType<T> head;

    /**
     * Creates an empty doubly linked list with head set to null. 
     */
    public DoublyLinkedList () {
	head = null;
    } //DoublyLinkedList

    /**
     * Inserts the given item into the list while keeping
     * sorted order. If the item already exists nothing
     * is inserted.
     *
     * @param item the item to be inserted
     */
    public void insertItem (T item) {
	// Create new node for item
	NodeType<T> node = new NodeType<T>();
	node.info = item;

	// Empty list insert
	if (head == null) {
	    head = node;
	    return;
	} //if

	// Insert at beginning
	if (item.compareTo(head.info) < 0) {
	    node.next = head;
	    head.back = node;
	    head = node;
	    return;
	} //if

	// Traverse the list to find where the item belongs
	NodeType<T> predloc = head;
	NodeType<T> location = head.next;

	// Traverse forward while current item is smaller than new item
	while (location != null && item.compareTo(location.info) > 0) {
	    predloc = location;
	    location = location.next;
	} //while

	// Check if item already exists
	if (location != null && item.compareTo(location.info) == 0) {
	    System.out.println("Item already exists");
	    return;
	} //if

	// Link new node between predloc and location
	node.next = location;
	node.back = predloc;
	predloc.next = node;

	// Fix backpointer of the node after new node
	if (location != null) {
	    location.back = node;
	} //if
    } //InsertItem

    /**
     * Deletes the node that holds the given item.
     * If the list is empty or the item given is not present
     * in the list prints message to stdOut.
     *
     * @param item the item to delete from the list
     */
    public void deleteItem (T item) {
	if (head == null) {
	    System.out.println("You cannot delete from an empty list");
	    return;
	  // Deleting the head  
	} else if (item.compareTo(head.info) == 0) {
	    if (head.next != null) {
		head = head.next;
		head.back = null; // New head has no back link
	    } else {
		head = null;      // List becomes empty
	    } //else
	    return;
        } //else-if

	// Start searching from second node
        NodeType<T> location = head.next;
        NodeType<T> predloc = head;

	// Traverse the list to search for item
        while (location != null) {
	    // Found node to delete
	    if (item.compareTo(location.info) == 0) {
	        predloc.next = location.next; // Unlink forward

		// Unlink backward if not in the last node
	        if (location.next != null) {
                    location.next.back = predloc;
	        } //if
	        return;
	    } //if

	    // Move forward
	    predloc = location;
	    location = location.next;
        } //while

        System.out.println("The item is not present in the list");
    } //DeleteItem

    /**
     * Returns the number of nodes in the list.
     */
    public int length () {
	int length = 0;
	NodeType<T> location = head;

	// Traverse list and count each node
	while (location != null) {
	    location = location.next;
	    length++;
	} //while
	return length;
    } //length

    /**
     * Prints all items in the list from head to last node.
     * If the list is empty, prints message to stdOut.
     */
    public void print () {
	if(head == null) {
	    System.out.println("The list is empty");
	    return;
	} //if

	NodeType<T> location = head;
	// Traverse list from head and print each value
	while (location != null) {
	    System.out.print(location.info + " ");
	    location = location.next;
	} //while
	System.out.println();
    } //print

    /**
     * Prints the items in the list from last node to head.
     * If the list is empty, prints message to stdOut.
     */
    public void printReverse () {
	if (head == null) {
	    System.out.println("The list is empty");
	    return;
	} //if
	
	NodeType<T> location = head;
	// Move location ptr to last node
	while (location.next != null) {
	    location = location.next;
	} //while

	System.out.print("The reverse list: ");
	// Traverse list from location ptr back to head
	while (location != null) {
	    System.out.print(location.info + " ");
	    location = location.back;
	} //while
	System.out.println();
    } //prinReverse

    /**
     * Delets all nodes within values lower and upper (inclusive).
     * If no values fall within the range, the list is unchanged.
     *
     * @param lower the lower bound to start deletion
     * @param upper the upper bound to end deletion
     */
    public void deleteSubsection (T lower, T upper) {
	if(head == null) {
	    return;
	} //if

        NodeType<T> location = head;
	// Traverse list to find first node >= lower
	while (location != null && lower.compareTo(location.info) > 0) {
	    location = location.next;
	} //while
	
	// Nothing to delete
	if (location == null) {
	    return;
	} //if

	// Start of deletion block
	NodeType<T> start = location;
	// Traverse list until upper bound is passed
	while (location != null && upper.compareTo(location.info) >= 0) {
	    location = location.next;
	} //while

	
	NodeType<T> endNext = location;     // Node after deletion block
	NodeType<T> startPrev = start.back; // Node before deletion block

	// Reconnect the list before and after the block
	if (startPrev != null) {
	    startPrev.next = endNext;
	} else {
	    head = endNext; // Deleting from head
	} //if-else

	// Fix the backpointer of the node after the block
	if (endNext != null) {
	    endNext.back = startPrev;
	} //if
    } //deleteSubsection

    /**
     * Reverses the entire list by flipping all of the next and
     * back pointers.
     */
    public void reverseList () {
	if (head == null) {
	    return;
	} //if

	NodeType<T> location = head;
	NodeType<T> prev = null;
	NodeType<T> next = null;

	// Traverse the list and flip pointer one node at a time
	while (location != null) {
	    next = location.next; // Save the next node
	    location.next = prev; // Flip the foward link
	    location.back = next; // Flip backward link
	    prev = location;      // Move prev forward
	    location = next;      // Move location forward
	} //while
	
	head = prev; // Prev ends at the new head
    } //reverseList

    /**
     * Swaps every pair of nodes in the list (1<->2, 3<->4, ...).
     * If the list has an odd number of nodes, the last node stays
     * in place.
     */
    public void swapAlternate () {
	// Nothing to swap if list has 0 or 1 nodes
	if (head == null || head.next == null) {
	    return;
	} //if

	NodeType<T> prevTail = null;   // End of last swapped pair
	NodeType<T> first = head;      // First node in current pair
	NodeType<T> second = head.next;// Second node in current pair

	head = second; // After first swap, second becomes new head

	while (first != null && second != null) {
	    // Save start of next pair
	    NodeType<T> nextPair = second.next; 

	    // Swap the two nodes
	    second.next = first;
	    first.back = second;

	    // Connect previous swapped pair to this one
	    if (prevTail != null) {
		prevTail.next = second;
		second.back = prevTail;
	    } else {
		second.back = null; // New head has no back link
	    } //if-else

	    // Connect the swapped pair to next pair
	    if (nextPair != null) {
		first.next = nextPair;
		nextPair.back = first;
	    } else {
		first.next = null;  // Reached the end
	    } //if-else

	    // Update prevTail to the end of this swapped pair
	    prevTail = first;

	    // Move to next pair
	    first = nextPair;
	    if (first != null) {
		second = first.next;
	    } //if
	} //while
    } //swapAlternate
} //DoublyLinkedList
