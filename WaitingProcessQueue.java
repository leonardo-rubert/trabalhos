
public class WaitingProcessQueue implements WaitingQueueADT<CustomProcess> {

	private static final int INITIAL_CAPACITY = 20; // the inital capacity of this waiting process queue
	private CustomProcess[] data; // min heap-array storing the CustomProcesses inserted in this
									// WaitingProcessQueue
	private int size; // number of CustomProcess stored in this WaitingProcessQueue

	/**
	 * initializes an empty waiting process queue whose initial capacity is
	 * INITIAL_capcity
	 */
	public WaitingProcessQueue() {
		data = new CustomProcess[INITIAL_CAPACITY];
		size = 0;
	}

	/**
	 * a helper method to swap the position of two nodes of the min-heap
	 * 
	 * @param pos1, position of the first element to be swapped
	 * @param pos2, positiom of the second element to be swapped
	 */
	private void swap(int pos1, int pos2) {
		CustomProcess temp = data[pos1];
		data[pos1] = data[pos2];
		data[pos2] = temp;
	}

	/**
	 * a helper method to find the position of the parent for the current node
	 * 
	 * @param pos, the current node position
	 * @return the index of the parent of this node
	 */
	private int parent(int pos) {
		return (pos - 1) / 2;
	}

	/**
	 * a helper method to find the position of the left child of the current node,
	 * suppose there is one
	 * 
	 * @param pos, the current position of the node
	 * @return the index of the leftchild of this node
	 */
	private int leftChild(int pos) {
		return 2 * pos + 1;
	}

	/**
	 * a helper method to return the position of the right child of the current node
	 * 
	 * @param pos, position of the current node
	 * @return the index of its rightchild
	 */
	private int rightChild(int pos) {
		return 2 * pos + 2;
	}

	/**
	 * determines whether the node with the current index is a leaf node
	 * 
	 * @param pos, the index of the current node
	 * @return true if this node has no left nor right child, false otherwise
	 */
	private boolean isLeaf(int pos) {
		
		return (2 * pos + 1 >= size);
	}

	/**
	 * a helper method to percolate up the min-heap at the given index in the data
	 * min-heap array
	 * 
	 * @param index, the index of the element to be percolated upwards
	 */
	private void minHeapPercolateUp(int index) {
		
		while (index > 0) {
			if(data[index] == null)
				return;
			if (data[index].compareTo(data[parent(index)]) >= 0)
				return;
			else {
				swap(index, parent(index));
				index = parent(index);
			}
		}
	}

	/**
	 * a helper method to percolate down the min-heap at the given index in the data
	 * min-heap array
	 * 
	 * @param index, the index of the element to be percolated downwards
	 */
	private void minHeapPercolateDown(int index) {
		// needs to descend the node all the way down to the leaf or otherwise
		// terminates when sorted
		while (!isLeaf(index)) {
			// case 1: the node has only left child
			if (2 * index + 1 == size - 1) {
				if (data[index].compareTo(data[leftChild(index)]) > 0)
					swap(index, leftChild(index));
				return;
			}

			// case 2: the node has both children
			else {
				CustomProcess min = data[index];
				int minIndex = -1;
				
				// finds out the min value among the node and its children, records that index
				for (int i = 0; i < 2; i++) {
					
					if (data[leftChild(index) + i].compareTo(min) < 0) {
						min = data[leftChild(index) + i];
						minIndex = leftChild(index) + i;
					}
				}

				if (min.compareTo(data[index]) == 0)
					return;

				else { // continue to trace down and alter the heap
					swap(index, minIndex);
					index = minIndex;
				}

			}

		}
	}

	/**
	 * inserts a newObject in this min-heap.
	 * 
	 * @param newObject to be inserted in this min-heap.
	 */
	public void insert(CustomProcess newObject) {
		// exceptional case: the data array has no more extra space to the new element
		if (data.length == size) {
			CustomProcess[] copy = new CustomProcess[size];
			for (int i = 0; i < size; i++)
				copy[i] = data[i]; // stores all the previous data
			data = new CustomProcess[size * 2]; // doubles the size of data array and restalls the data
			for (int i = 0; i < size; i++)
				data[i] = copy[i];
		}

		// inserts the element into the right-most empty spot of the lowest row, and
		// percolate upwards from there to heapify the data array again
		data[size++] = newObject;
		
		minHeapPercolateUp(size-1);
		

	}

	/**
	 * removes and returns the element with the highest priority.
	 * 
	 * @return the removed element
	 * @throws java.util.NoSuchElementException with a descriptive error message if
	 *         this waiting queue is empty
	 */
	public CustomProcess removeBest() throws java.util.NoSuchElementException {
		if (size == 0)
			throw new java.util.NoSuchElementException("Warning: the Queue is empty.");
		CustomProcess min = data[0];

		// swap the root with the right-most element on the lowest row and heapify the data array
		swap(0, size - 1);
		data[size - 1] = null;
		size --;
		minHeapPercolateDown(0);
		

		return min;

	}
	
	/**
	   * returns without removing the element with the highest priority.
	   * 
	   * @return the element with the highest priority
	   * @throws java.util.NoSuchElementException with a descriptive error message if this waiting queue
	   *                                          is empty
	   */
	  public CustomProcess peekBest() throws java.util.NoSuchElementException {
		  if(size == 0)
			  throw new java.util.NoSuchElementException("Warning: the Queue is empty.");
		  return data[0];
	  }
	  
	  /**
	   * returns size of priority queue
	   * 
	   * @return the size of priority queue
	   */
	  public int size() {
		  return size;
	  }
	  
	  /**
	   * checks whether this WaitingProcess queue is empty or not.
	   * 
	   * @return true if this waiting queue is empty, false otherwise
	   */
	  public boolean isEmpty() {
		  return size==0;
	  }
	
	  /** returns a String representation of all the custom processes stored in the data array
	   * 
	   * @return a String representation of all the custom processes stored in the data array
	   */
	  @Override
	  public String toString() {
		  if(size == 0) 
			  return " ";
		  else {
			  String str = "";
			  for(int i = 0; i < size; i++) 
				  str += data[i] + " ";
			  return str.trim();
		  }
		  
	  }

}
