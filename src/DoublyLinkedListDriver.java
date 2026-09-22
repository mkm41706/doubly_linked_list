import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * Driver program for the doubly-linked-list.
 * Reads the input file, bilds the list based on chosen type,
 * and then runs the user command-loop.
 */
public class DoublyLinkedListDriver {

    // Command options used in the menu
    public static final char INSERT = 'i';
    public static final char DELETE = 'd';
    public static final char PRINT = 'p';
    public static final char LENGTH = 'l';
    public static final char PRINT_REV = 't';
    public static final char REVERSE = 'r';
    public static final char DELETE_SUB = 'b';
    public static final char SWAP_ALT = 's';
    public static final char QUIT = 'q';
    
    /**
     * Entry point of the program. Reads the file name from the
     * command line, asks the user for the list type, loads the data
     * into the list, and then starts the user command loop.
     *
     * @param args the command line arguments
     */
    public static void main (String args[]) {
	// Ensure file name was provided
	if (args.length == 0) {
            System.out.println("Usage: java -cp DoublyLinkedListDriver <input.txt>");
            return;
        }//if

	String filename = args[0];
	Scanner input = new Scanner(System.in);

	// Ask user what type of list to build
	System.out.print("Enter list type (i - int, d - double, s - string): ");
	char type = input.next().charAt(0);
	
	DoublyLinkedList<Integer> listInt = null;
	DoublyLinkedList<Double> listDouble = null;
	DoublyLinkedList<String> listString = null;

	// Create correct list based on the user's choice
	if (type == 'i') {
	    listInt = new DoublyLinkedList<>();
	} else if (type == 'd') {
	    listDouble = new DoublyLinkedList<>();
	} else if (type == 's') {
	    listString = new DoublyLinkedList<>();
	} else {
	    System.out.println("Invalid type. Exiting");
	    return;
	} //else-if
	
        Scanner fileScanner = null;
	
	// Try to open the input file
        try{
            fileScanner = new Scanner(new File(filename));
        } catch (FileNotFoundException e) {
            System.out.println("Error: Unable to open file " + 
                                filename);
            return;
        } //try-catch

	// Read values from the file and insert them into the list
	while (fileScanner.hasNext()) {
	    if (type == 'i') {
		listInt.insertItem(fileScanner.nextInt());
	    } else if (type == 'd') {
		listDouble.insertItem(fileScanner.nextDouble());
	    } else {
		listString.insertItem(fileScanner.next());
	    } //else-if
	} //while

	// Start the user command loop
	if (type == 'i') {
	    userIO(listInt, type);
	} else if (type == 'd') {
	    userIO(listDouble, type);
	} else {
	    userIO(listString, type);
	} //else-if
    } //main

    /**
     * Prints the list of available commands for the user.
     */
    public static void printMenu () {
	System.out.println("Commands: ");
	System.out.println();
	System.out.println("(i) - Insert value");
	System.out.println("(d) - Delete value");
	System.out.println("(p) - Print list");
	System.out.println("(l) - Length");
	System.out.println("(t) - Print reverse");
	System.out.println("(r) - Reverse list");
	System.out.println("(b) - Delete Subsection");
	System.out.println("(s) - Swap Alternate");
	System.out.println("(q) - Quit program");
	System.out.println();
    } //printMenu

    /**
     * Reads a value from the user based on the chosen list type.
     * Returns the value as the correct generic type.
     *
     * @param type the list type
     * @param input the scanner used to read the user's input
     * @return the value entered by the user, converted to the correct type
     */
    @SuppressWarnings("unchecked")
    private static <T> T readValue (char type, Scanner input) {
	// Read an int
	if (type == 'i') {
	    return (T)(Integer)input.nextInt();
	}
	// Read a double
	else if (type == 'd') {
	    return (T)(Double)input.nextDouble();
	}
	// Read a string
	else {
	    return (T)input.next();
	} //else-if
    } //readValue

    /**
     * Runs the main command loop for the list. Reads user commands,
     * performs the requested operation, and prints the list when
     * needed.
     *
     * @param list the list the user wil interact with
     * @param type the type of list used for reading values
     */
    public static<T extends Comparable<T>> void userIO(DoublyLinkedList<T> list, char type) {
	Scanner input = new Scanner(System.in);
	printMenu();

	// Used for prompts (number or string)
	String outType = (type == 'i' || type == 'd') ? "number" : "string";
	
	while (true) {
	    System.out.print("Enter a command: ");
	    char cmd = input.next().charAt(0);

	    switch (cmd) {
	    case INSERT:
		// Show current list before inserting
		System.out.print("The list is: ");
		list.print();
		
		System.out.print("Enter a " + outType + " to insert: ");
		list.insertItem(readValue(type, input));

		// Show updated list
		System.out.print("The list is: ");
		list.print();
		list.printReverse();
		break;
		
	    case DELETE:
		// Only prints if list has items
		if (list.length() != 0) {
		    System.out.print("The list is: ");
		    list.print();
		} //if
		
		System.out.print("Enter a " + outType + " to delete: ");
		list.deleteItem(readValue(type, input));

		// Print updated list if not empty
                if (list.length() != 0) {
		    System.out.print("The list is: ");
		    list.print();
                    list.printReverse();
		} //if
		break;
		
	    case PRINT:
		System.out.print("The list is: ");
		list.print();
		break;
		
	    case LENGTH:
		System.out.println("The length of the list is " + list.length());
		break;
		
	    case PRINT_REV:
		list.printReverse();
		break;
		
	    case REVERSE:
		// Print original list of not empty
		if (list.length() != 0) {
		  System.out.print("The original list: ");
		  list.print();
		} else {
		    System.out.println("The original list: ");
		} //if-else
		
		list.reverseList();

		// Print reversed list if not empty
		if (list.length() != 0) {
		System.out.print("The reversed list: ");
		list.print();
		} else {
		    System.out.println("The reversed list: ");
		} //if-else
		break;
		
	    case DELETE_SUB:
		System.out.print("Enter lower bound: ");
		T lower = readValue(type, input);

		System.out.print("Enter upper bound: ");
		T upper = readValue(type, input);

		// Print original list if not empty
		if (list.length() != 0) {
		    System.out.print("The original list: ");
		    list.print();
		} else {
		    System.out.println("The original list: ");
		}
		
		list.deleteSubsection(lower, upper);

		// Print modified list if not empty
		if (list.length() != 0) {
		    System.out.print("The modified list: ");
		    list.print();
		    list.printReverse();
		} else {
		    System.out.println("The modified list: ");
		} //if-else
	        break;
		
	    case SWAP_ALT:
		// Print original list if not empty
		if (list.length() != 0) {
		    System.out.print("The original list: ");
		    list.print();
		} else {
		    System.out.println("The original list: ");
		} //if-else
		
		list.swapAlternate();

		// Print modified list if not empty
		if (list.length() != 0) {
		    System.out.print("The modified list: " );
		    list.print();
		    list.printReverse();
		} else {
		    System.out.println("The modified list: ");
		} //if-else
		break;
		
	    case QUIT:
		System.out.println("Exiting the program...");
		return;

	    default:
		System.out.println("Invalid command, try again.");
	    } //switch
	} //while
    } //userIO
} //DoublyLinkedListDriver
