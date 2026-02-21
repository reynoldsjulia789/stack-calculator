package src;

/**
 * Custom stack class
 * @param <Type> the type of object the stack will hold
 * @author Julia Reynolds
 */
public class Stack<Type>
{
    Node head;
    int  size;

    /**
     * Inner node class for Stack
     */
    private class Node
    {
        Type data;
        Node next;

        /**
         * Node constructor
         * @param data the data to store
         * @param next the next node in the Stack
         */
        private Node(Type data, Node next)
        {
            this.data = data;
            this.next = next;
        }
    }

    /**
     * Constructs an empty stack with a dummy head node
     */
    public Stack()
    {
        this.head = new Node(null, null);
        this.size = 0;
    }

    /**
     * Returns the size of the stack
     * @return number of elements in the stack
     */
    public int size()
    {
        return this.size;
    }

    /**
     * Adds data to the top of the stack
     * @param data the data to add to the stack, can be null
     */
    public void push(Type data)
    {
        this.head.next = new Node(data, this.head.next);

        this.size++;
    }

    /**
     * Removes the data from the top of the stack and returns it to the caller
     * @return data at the top of the stack
     */
    public Type pop()
    {
        Node curr, prev;

        if (size == 0)
        {
            throw new IllegalArgumentException("nothing in the stack to pop");
        }

        curr = this.head.next;
        prev = this.head;

        prev.next = curr.next;

        this.size--;

        return curr.data;
    }

    /**
     * Allows the caller to look at what is at the top of the stack without removing it from
     *      the stack.
     * @return the data at the top of the stack without removing it
     */
    public Type peek()
    {
        if (size == 0)
        {
            throw new IllegalArgumentException("stack is empty");
        }

        return this.head.next.data;
    }

    /**
     * Emptys the stack of all contents
     */
    public void clear()
    {
        this.head.next = null;
        this.size      = 0;
    }
}
