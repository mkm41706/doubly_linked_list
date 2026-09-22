/**
 * A node used in the doubly linked list.
 * Stores the data along with links to the next and back nodes.
 */
public class NodeType<T extends Comparable<T>> {
    // Value stored in this node
    public T info;

    // Pointer to the next node in the list
    public NodeType<T> next;

    // Pointer to the previous node in the list
    public NodeType<T> back;
} //NodeType
