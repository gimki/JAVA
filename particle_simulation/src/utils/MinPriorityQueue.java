package utils;

import java.util.ArrayList;

public class MinPriorityQueue<T extends Comparable<T>>{
    private ArrayList<T> queue;
    private int qSize = 0;

    /**
     * Creates an empty queue.
     */
    @SuppressWarnings("unchecked")
    public MinPriorityQueue() {
        queue = new ArrayList<T>();
        queue.add(null);
        qSize = 0;
    }

    /**
     * Returns the number of elements currently in the queue.
     */
    public int size() {
        return qSize;
    }
    
    /**
     * Adds elem to the queue.
     */
    public void add(T elem) {
        queue.add(elem);
    	qSize++;
        switchNodesUp(qSize);
    }
    
    /**
     * Removes, and returns, the element at the front of the queue.
     */
    
    public T remove() {
        if (isEmpty())
        	return null;
    	T toBeRemoved = queue.get(1);
        swap(1, qSize);
        queue.remove(qSize);
        qSize--;
        switchNodesDown(1);
        return toBeRemoved;
    }
    
    /**
     * Returns true if the queue is empty, false otherwise.
     */
    public boolean isEmpty() {
        return (qSize == 0);
    }

    /***** HELPER FUNCTIONS *****/
    
    /**
     * Goes up through the binary heap. While the k-th element 
     * in the queue is greater than its parent element, swap.
     */
    private void switchNodesUp(int k) {
        while (k > 1 && less(k/2, k)) {
            swap(k/2, k);
            k = k/2;
        }
    }
    
    /**
     * Returns true if the i-th element in the queue is less
     * than the j-th element in the queue, false otherwise.
     */
    private boolean less(int i, int j) {
        return (queue.get(i).compareTo(queue.get(j)) > 0);
    }

    /**
     * Swaps the i-th element with the j-th element in the queue.
     */
    private void swap(int i, int j) {
        T temp = queue.get(i);
        queue.set(i, queue.get(j));
        queue.set(j, temp);
    }

    /**
     * Goes down through the binary heap. While the k-th element 
     * in the queue is less than a child element, swap.
     */
    private void switchNodesDown(int k) {
           while (2*k <= qSize) {
               int j = 2*k;

               if (j < qSize && less(j, j+1))
            	   j++;
               
               if (!less(k, j))
            	   break;

               swap(k, j);
               k = j;
           }
    }
}
