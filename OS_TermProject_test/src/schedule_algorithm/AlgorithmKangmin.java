package schedule_algorithm;

import java.util.Vector;

public class AlgorithmKangmin {
	static int processCount;
	private int[] arrivaltime;
	
		
	static Vector<ArgumentVector> test = new Vector<ArgumentVector>();
	
	
	public int[] getArrivaltime() {
		return arrivaltime;
	}

	public void setArrivaltime(int[] arrivaltime) {
		this.arrivaltime = arrivaltime;
	}

	public AlgorithmKangmin(int processCount, int timeSlice, String[] pID, int[] arrivalTime, int[] runningTime, int[] priority) {
		// TODO Auto-generated constructor stub
		System.out.println(processCount);
		this.arrivaltime = arrivalTime;
		this.processCount = processCount;
		
	}
	
	Vector<ArgumentVector> FCFS() {
		
		ArgumentVector ar = new ArgumentVector(1,1,"22");
		processCount = processCount+1;
		test.add(ar);
		
		ar.FixArgument(0, 0, "test");
		
		
		return test;
	}
}