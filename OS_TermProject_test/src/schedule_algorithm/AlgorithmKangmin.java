package schedule_algorithm;

import java.util.Vector;

public class AlgorithmKangmin {
	int ProcessCount;
	int TimeSlice;
	int[] ArrivalTime;
	int[] RunningTime;
	int[] Priority;
	String[] PID;
	
	// 반환할 것들 만들어줌
	 Vector<ArgumentVector> FCFSGhantt = new Vector<ArgumentVector>();
	 Vector<ArgumentVector> RoundRobinGhantt = new Vector<ArgumentVector>();
	 Vector<ArgumentVector> PreemptionGhantt = new Vector<ArgumentVector>();
	

	public AlgorithmKangmin(int ProcessCount, int TimeSlice, String[] PID, int[] ArrivalTime, int[] RunningTime, int[] Priority) {
		// TODO Auto-generated constructor stub
		this.ProcessCount = ProcessCount;
		this.TimeSlice = TimeSlice;
		this.ArrivalTime = ArrivalTime;
		this.RunningTime = RunningTime;
		this.Priority = Priority;
		this.PID = PID;
	}
	
	Vector<ArgumentVector> FCFS() {
		int TotalArrivalTime = 0;
		int TotalRunningTime = 0;
		
		Vector<ArgumentVector> Temp = new Vector<ArgumentVector>();
		
		for(int i=0;i<ProcessCount;i++) {
			Temp.add(new ArgumentVector(ArrivalTime[i], RunningTime[i], PID[i]));
		}
		
		for(int i=0;i<ProcessCount;i++) {
			TotalRunningTime = RunningTime[i];
			
			
			
		}
		
		FCFSGhantt = Temp;
		
		return FCFSGhantt;		
	}
	
	Vector<ArgumentVector> RoundRobin(){
		
		return RoundRobinGhantt;
	}
	
	Vector<ArgumentVector> Preemption(){
		
		return PreemptionGhantt;
	}
}