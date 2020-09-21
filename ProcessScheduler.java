import java.util.*;
/**
 * this class represents the data type for the main scheduler for our processes.
 * @author galoi
 *
 */
public class ProcessScheduler {

	private int currentTime; // stores the current time after the last run
	private int numProcessesRun;  // stores the number of processes run so far
	private WaitingProcessQueue queue;  // this processing unit's queue
	
	/**
	 * initializes an scheduler that sets up an empty queue
	 */
	public ProcessScheduler() {
		queue = new WaitingProcessQueue();
		currentTime = 0;
		numProcessesRun = 0;
	}
	
	/**
	 * inserts the given process in the WaitingProcessQueue queue
	 * @param process, the process to be inserted
	 */
	public void scheduleProcess(CustomProcess process) {
		queue.insert(process);
		
	}
	
	/**
	 * runs the ready processes already scheduled in this processScheduler's queue
	 * @return a String that represents the log of one run operation
	 */
	public String run() {
		String log = "Starting " + queue.size() + " processes\n\n";
		while(!queue.isEmpty()) {
			CustomProcess high = queue.removeBest();
			log+= "Time "+ currentTime + " : Process ID " + high.getProcessedId() + " Starting.\n";

			// updates data after the burstTime processed
			currentTime+= high.getBurstTime();
			log+= "Time "+ currentTime + " : Process ID " + high.getProcessedId() + " Completed.\n";
			numProcessesRun++;
		}
		
		log+= "\nTime " + currentTime + ": All scheduled processes completed.\n";
		return log;
	}
	
	
	/**
	 * 
	 * @return
	 */
	public int getNumProcessesRun() {
		return numProcessesRun;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getCurrentTime() {
		return currentTime;
	}
	
	/**
	 * a helper method to determine if a string represents an integer
	 * @param input the string to be tested
	 * @return true if input represents an integer, false otherwise
	 */
	public static boolean isInteger(String input) {
		try {
			Integer.parseInt(input); // turns the string into the integer it represents, if valid
			return true;
		}
		catch(Exception e) {
			return false;
		}
	}
	
	
	
	public static void main(String[] args) {
		System.out.println("==========    Welcome to the SJF Process Scheduler App    ==========");
		Scanner sc = new Scanner(System.in);
		boolean end = false;
		ProcessScheduler sch = new ProcessScheduler();
		
		do {
			System.out.println("Enter command:");
			System.out.println("[schedule <burstTime>] or [s <burstTime>]");
			System.out.println("[run] or [r]");
			System.out.println("[quit] or [q]\n");
			String input = sc.nextLine();
			String line[] = input.split("\\s+");
			String check = line[0];
			
			if(check.equals("schedule") || check.equals("s")) {
				if(isInteger(line[1])) {
					
					CustomProcess append = new CustomProcess(Integer.parseInt(line[1]));
					sch.scheduleProcess(append);
					
					System.out.println("Process ID " + append.getProcessedId() + " scheduled. Burst Time = " + append.getBurstTime() +"\n");
				}
				
				else
					System.out.println("WARNING: burst time must be an integer!\n");
			}
			
			
			
			else if (check.equals("quit") || check.equals("q")) {
				end = true;
				sc.close();
				System.out.println(sch.numProcessesRun + " processes run in " + sch.getCurrentTime() + " unit of time!");
				System.out.println("Thank you for using our scheduler!");
				System.out.println("Goodbye!");
			}
			
			
			else if(check.equals("run") || check.equals("r")) {
				System.out.println(sch.run());
			}
			
			else 
				System.out.println("WARNING: Please enter a valid command!\n");
			
		}
		
		while(!end);
		
		
		
		
		
		
		
	}
	
	
}
