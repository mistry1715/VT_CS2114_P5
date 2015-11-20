package prj5;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * LinkedList is a doubly linked node implementation of Iterable to be used for
 * data sorting.
 * 
 * @author Alec Alderman (ama2106)
 * @author Julian Davila (jaydee1)
 * @author Matthew Scanland (mks2752)
 * @version 2015.11.16
 * 
 * @param <T> The generic object used in the list.
 */
public class LinkedList<T> implements Iterable<T> {
    private Node<T> firstNode;
    private Node<T> lastNode;
    private int size;

    /**
     * Create a new LinkedList with inital null and zero values.
     */
    public LinkedList() {
        firstNode = null;
        lastNode = null;
        size = 0;
    }

    /**
     * Add an entry to the end of the list.
     * 
     * @param anEntry - entry to be added
     */
    public void add(T anEntry) {
        add(size, anEntry);
    }

    /**
     * Add an entry at a specified index. If added to the middle, shift node at
     * that position down 1.
     * 
     * @param index - index to be added at
     * @param anEntry - entry to be added
     */
    public void add(int index, T anEntry) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException(
                    "Cannot add to specified index");
        }
        Node<T> newNode = new Node<T>(anEntry);
        Node<T> previousNode = null;
        Node<T> nextNode = null;
        // list is empty
        if (isEmpty()) {
            firstNode = newNode;
            lastNode = firstNode;
        }
        // add to begining
        else
            if (index == 0) {
                newNode.setNextNode(firstNode);
                firstNode.setPreviousNode(newNode);
                firstNode = newNode;
            }
            // add to end
            else
                if (index == size) {
                    lastNode.setNextNode(newNode);
                    newNode.setPreviousNode(lastNode);
                    lastNode = newNode;
                }
                // add in the middle
                else {
                    previousNode = getNodeAt(index - 1);
                    nextNode = previousNode.getNextNode();
                    previousNode.setNextNode(newNode);
                    newNode.setPreviousNode(previousNode);
                    newNode.setNextNode(nextNode);
                    nextNode.setPreviousNode(newNode);
                }
        size++;
    }

    /**
     * Get the data at the given index.
     * 
     * @param index - index to return data
     * @return data from the node at specified index
     */
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(
                    "Cannot get element at this index. ");
        }
        return getNodeAt(index).getData();
    }

    /**
     * Check if the current list is empty.
     * 
     * @return true if empty, false if not empty
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Get the current size of the list.
     * 
     * @return the current size of the list
     */
    public int size() {
        return size;
    }

    /**
     * Check if the list contains the given object.
     *
     * @param anEntry - the object to check for
     * @return true if it contains the object, false if not contained
     */
    public boolean contains(T anEntry) {
        return findIndexOf(anEntry) != -1;
    }

    /**
     * Get the node at the given index.
     * 
     * @param index - index to get a node
     * @return node at the index
     */
    private Node<T> getNodeAt(int index) {
        Node<T> node = firstNode;
        for (int i = 0; i < index; i++) {
            node = node.getNextNode();
        }
        return node;
    }

    /**
     * Remove an entry at the given index.
     * 
     * @param index - index to be removed
     * @return data of removed node
     * @throws IndexOutOfBoundsException if index is less than 0 or greater than
     *         equal to size
     */
    public T remove(int index) {
        // if trying to remove above the size or below 0
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(
                    "Cannot remove element at this index.");
        }
        // get the previous and next nodes to link together, return data
        Node<T> removed = getNodeAt(index);
        T data = removed.getData();
        if (index == 0) {
            if (firstNode.getNextNode() == null) {
                firstNode = null;
                lastNode = null;
            }
            else {
                firstNode = firstNode.getNextNode();
                removed.setNextNode(null);
                firstNode.setPreviousNode(null);
            }
        }
        else
            if (index == size - 1) {
                lastNode = lastNode.getPreviousNode();
                removed.setPreviousNode(null);
                lastNode.setNextNode(null);
            }
            else {
                removed.getPreviousNode().setNextNode(removed.getNextNode());
                removed.getNextNode()
                        .setPreviousNode(removed.getPreviousNode());
            }
        size--;
        return data;
    }

    /**
     * Remove a specified entry by finding the index and calling the
     * remove(index) function.
     * 
     * @param anEntry - entry to be removed
     * @return null if not found, entry removed if found
     */
    public T remove(T anEntry) {
        T data = null;
        int index = findIndexOf(anEntry);
        // index is valid
        if (index >= 0 && index < size) {
            data = remove(index);
        }
        return data;
    }

    /**
     * Find the first index of an entry.
     * 
     * @param anEntry - entry to be found
     * @return -1 if non existent, index of entry if exists
     */
    private int findIndexOf(T anEntry) {
        Node<T> node = firstNode;
        for (int i = 0; i < size; i++) {
            if (node.getData().equals(anEntry)) {
                return i;
            }
            node = node.getNextNode();
        }
        return -1;
    }

    /**
     * Clear the current list.
     */
    public void clear() {
        firstNode = null;
        lastNode = null;
        size = 0;
    }

    /**
     * Create a new iterator object for the current list.
     * 
     * @return new LinkedListIterator()
     */
    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    /**
     * Inner node class to be used by the LinkedList.
     * 
     * @author Alec Alderman (ama2106)
     * @author Julian Davila (jaydee1)
     * @author Matthew Scanland (mks2752)
     * @version 2015.11.16
     *
     * @param <T> - generic data type
     */
    @SuppressWarnings("hiding")
    private class Node<T> {
        private T data;
        private Node<T> previousNode;
        private Node<T> nextNode;

        /**
         * Node constructor with with data as parameter.
         * 
         * @param anEntry - data to be added
         */
        private Node(T anEntry) {
            this(null, anEntry, null);
        }

        /**
         * Node constructor with previousNode, data, nextNode.
         * 
         * @param previous - previous node
         * @param anEntry - data to be added
         * @param next - next node
         */
        private Node(Node<T> previous, T anEntry, Node<T> next) {
            data = anEntry;
            previousNode = previous;
            nextNode = next;
        }

        /**
         * Return the data in the node.
         * 
         * @return data
         */
        private T getData() {
            return data;
        }

        /**
         * Return the next node.
         * 
         * @return nextNode
         */
        private Node<T> getNextNode() {
            return nextNode;
        }

        /**
         * Get the previous node.
         * 
         * @return previousNode
         */
        private Node<T> getPreviousNode() {
            return previousNode;
        }

        /**
         * Set the previous node.
         * 
         * @param newNode - new node to be previous node
         */
        private void setPreviousNode(Node<T> newNode) {
            previousNode = newNode;
        }

        /**
         * Set the next node.
         * 
         * @param newNode - new node to be next node
         */
        private void setNextNode(Node<T> newNode) {
            nextNode = newNode;
        }
    }

    /**
     * Inner iterator class to be used by the LinkedList.
     * 
     * @author Alec Alderman (ama2106)
     * @author Julian Davila (jaydee1)
     * @author Matthew Scanland (mks2752)
     * @version 2015.11.16
     */
    public class LinkedListIterator implements Iterator<T> {
        private boolean calledNext;
        private Node<T> currentNode;
        private int position;

        /**
         * Create a new LinkedListIterator object starting at the beginning of
         * the current LinkedList.
         */
        public LinkedListIterator() {
            currentNode = firstNode;
            calledNext = false;
            position = 0;
        }

        /**
         * Check if there is a next node.
         * 
         * @return true if there is a next node, false if not
         */
        @Override
        public boolean hasNext() {
            return (position < size);
        }

        /**
         * Return the data in the next node and makes current node the next
         * node.
         * 
         * @return data if there is a next
         * @throws NoSuchElementException if there is no next element in the
         *         current LinkedList
         */
        @Override
        public T next() {
            T data = null;
            if (hasNext()) {
                data = currentNode.getData();
                currentNode = currentNode.getNextNode();
                position++;
                calledNext = true;
            }
            else {
                throw new NoSuchElementException(
                        "Next in Iterator has no next element.");
            }
            return data;
        }

        /**
         * Remove the current node.
         * 
         * @throws IllegalStateException if there is no element to remove at the
         *         given position.
         */
        public void remove() {
            if (!calledNext) {
                throw new IllegalStateException(
                        "Unable to remove element in Iterator.");
            }
            else {
                calledNext = false;
                LinkedList.this.remove(position - 1);
                position--;
            }
        }
    }

}
