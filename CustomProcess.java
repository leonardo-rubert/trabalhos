
/** This class represents the data type for the processes used in our application
 * 
 * @author galoi
 *
 */
public class CustomProcess implements java.lang.Comparable<CustomProcess> {

	private static int nextProcessId = 1; // stores the id to be assigned to the next process to be created
	private final int PROCESS_ID; // unique identifier for this process
	private int burstTime; // time required by this process for CPU extension

	// initializes a new instance of CustomProcess
	public CustomProcess(int burstTime) throws java.lang.IllegalArgumentException {
		if (burstTime <= 0)
			throw new java.lang.IllegalArgumentException("Warning: BurstTime must be set to be positive.");
		this.burstTime = burstTime;
		PROCESS_ID = nextProcessId;
		nextProcessId ++;

	}

	/**
	 * returns a String representation of this CustomProcess
	 * 
	 * @return a string representation of this CustomProcess
	 */
	@Override
	public String toString() {
		return "p" + this.PROCESS_ID + "(" + this.burstTime + ")";
	}

	/** 
	 * customized comparison between two instance fields of this class 
	 * @param other, another instance field to be compare to
	 * @return +/- values or 0 with respect to the comparison criteria
	 */
	public int compareTo(CustomProcess other) {
		if (this.burstTime != other.burstTime)
			return this.burstTime - other.burstTime; // the one with larger burstTime is straight larger
		else
			return this.PROCESS_ID - other.PROCESS_ID; // otherwise the one with larger ID number is larger

	}
	
	/**
	 * the getter method to access the private PROCESS_ID
	 * @return
	 */
	public int getProcessedId() {
		return PROCESS_ID;
	}
	
	/**
	 * the getter method to access the private burstTime
	 * @return
	 */
	public int getBurstTime() {
		return burstTime;
	}

}
