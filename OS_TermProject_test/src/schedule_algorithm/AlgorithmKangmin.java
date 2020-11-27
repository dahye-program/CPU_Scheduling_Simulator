package schedule_algorithm;

import java.util.Vector;

public class AlgorithmKangmin {
	int ProcessCount;
	int TimeSlice;
	int[] ArrivalTime;
	int[] RunningTime;
	int[] Priority;
	String[] PID;
	
	int FCFSAWT;
	int FCFSATT;
	
	// 반환할 것들 만들어줌
	 Vector<ArgumentVector> FCFSGantt = new Vector<ArgumentVector>();
	 Vector<ArgumentVector> RoundRobinGantt = new Vector<ArgumentVector>();
	 Vector<ArgumentVector> PreemptionGantt = new Vector<ArgumentVector>();
	

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
		
		int totalWaitingTime = 0;
		
		
		ArgumentVector[] FCFSReadyQueue = new ArgumentVector[ProcessCount];
		
		for(int i=0;i<ProcessCount;i++) {
			FCFSReadyQueue[i] = new ArgumentVector(ArrivalTime[i], RunningTime[i], PID[i], Priority[i]);
		}
		
		ArgumentVector FCFSTemp = new ArgumentVector(0,0,"temp",0);
		
		for (int i = ProcessCount - 1; i > 0; i--) {
			for (int j = 0; j < i; j++) {
				if (FCFSReadyQueue[j].ReturnArrivalTime() > FCFSReadyQueue[j + 1].ReturnArrivalTime()) {
					FCFSTemp = FCFSReadyQueue[j];
					FCFSReadyQueue[j] = FCFSReadyQueue[j + 1];
					FCFSReadyQueue[j + 1] = FCFSTemp;
				}
			}
		}
		
		for(int i=0;i<ProcessCount;i++) {
			FCFSReadyQueue[i].WaitTime = totalWaitingTime - FCFSReadyQueue[i].ArrivalTime;
			totalWaitingTime+=FCFSReadyQueue[i].RunningTime;
			FCFSReadyQueue[i].ReturnTime = totalWaitingTime;
			}
		
		for(int i=0;i<ProcessCount;i++) {
			FCFSGantt.add(FCFSReadyQueue[i]);
		}
		
		return FCFSGantt;		
	}
	
	Vector<ArgumentVector> RoundRobin(){
		
		return RoundRobinGantt;
	}
	
	Vector<ArgumentVector> Preemption(){
		
		return PreemptionGantt;
	}
}