
public class ProcessSchedulerTester {

	/**
	 * checks the correctness of the insert operation implemented in the
	 * WaitingProcessQueue class
	 * 
	 * @return true if the insert method works, false otherwise
	 */
	public static boolean testInsertWaitingProcessQueue() {
		WaitingProcessQueue queue = new WaitingProcessQueue();
		CustomProcess process1 = new CustomProcess(10);
		CustomProcess process2 = new CustomProcess(2);
		CustomProcess process3 = new CustomProcess(5);
		CustomProcess process4 = new CustomProcess(3);
		CustomProcess process5 = new CustomProcess(1);
		queue.insert(process1);
		queue.insert(process2);
		queue.insert(process3);
		queue.insert(process4);
		queue.insert(process5);

		if (queue.size() != 5)
			return false;
		if (queue.peekBest() != process5)
			return false;

		return true;
	}

	/**
	 * checks the correctness of the removeBest operation implemented in the
	 * WaitingProcessQueue class
	 * 
	 * @return true if removeBest works, false otherwise
	 */
	public static boolean testRemoveBestWaitingProcessQueue() {
		WaitingProcessQueue queue = new WaitingProcessQueue();
		CustomProcess process1 = new CustomProcess(10);
		CustomProcess process2 = new CustomProcess(2);
		CustomProcess process3 = new CustomProcess(5);
		queue.insert(process3);
		queue.insert(process2);
		queue.insert(process1);
		queue.removeBest();

		return queue.size() == 2;
	}

//	public static void main(String[] args) {
//		System.out.println("We passed testInsertWaitingProcessQueue(): " + testInsertWaitingProcessQueue());
//		System.out.println("We passed testRemoveBestWaitingProcessQueue(): " + testRemoveBestWaitingProcessQueue());
//	}
}
