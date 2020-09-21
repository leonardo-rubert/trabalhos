/**
 * This generic interface models a waiting queue abstract data type
 * 
 * @param <T> type parameter extends the Comparable interface. It represents the type of elements
 *            stored in this waiting queue
 */
public interface WaitingQueueADT<T extends Comparable<T>> {

  /**
   * inserts a newObject in this waiting queue.
   * 
   * @param newObject to insert in this waiting queue
   */
  public void insert(T newObject);

  /**
   * removes and returns the element with the highest priority.
   * 
   * @return the removed element
   * @throws java.util.NoSuchElementException with a descriptive error message if this waiting queue
   *                                          is empty
   */
  public T removeBest();


  /**
   * returns without removing the element with the highest priority.
   * 
   * @return the element with the highest priority
   * @throws java.util.NoSuchElementException with a descriptive error message if this waiting queue
   *                                          is empty
   */
  public T peekBest();

  /**
   * returns size of priority queue
   * 
   * @return the size of priority queue
   */
  public int size();

  /**
   * checks whether this waiting queue is empty or not.
   * 
   * @return true if this waiting queue is empty, false otherwise
   */
  public boolean isEmpty();
}