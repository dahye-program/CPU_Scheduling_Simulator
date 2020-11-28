package schedule_algorithm;

import java.awt.Color;
import java.util.Vector;

public class AlgorithmKangmin {
	int ProcessCount;
	int TimeSlice;
	int[] ArrivalTime;
	int[] RunningTime;
	int[] Priority;
	String[] PID;
	Color[] color;
	
	int FCFSAWT;
	int FCFSATT;
	
	// 반환할 것들 만들어줌
	 Vector<ArgumentVector> FCFSGantt = new Vector<ArgumentVector>();
	 Vector<ArgumentVector> RoundRobinGantt = new Vector<ArgumentVector>();
	 Vector<ArgumentVector> PreemptionGantt = new Vector<ArgumentVector>();
	

	public AlgorithmKangmin(int ProcessCount, int TimeSlice, String[] PID, int[] ArrivalTime, int[] RunningTime, int[] Priority, Color[] color) {
		// TODO Auto-generated constructor stub
		this.ProcessCount = ProcessCount;
		this.TimeSlice = TimeSlice;
		this.ArrivalTime = ArrivalTime;
		this.RunningTime = RunningTime;
		this.Priority = Priority;
		this.PID = PID;
		this.color = color;
	}
	
	Vector<ArgumentVector> FCFS() {
		
		int totalWaitingTime = 0;
		
		
		ArgumentVector[] FCFSReadyQueue = new ArgumentVector[ProcessCount];

		for (int i = 0; i < ProcessCount; i++) {
			FCFSReadyQueue[i] = new ArgumentVector(ArrivalTime[i], RunningTime[i], PID[i], Priority[i], color[i]);
		}

		ArgumentVector FCFSTemp = new ArgumentVector(0, 0, "temp", 0, color[0]);
		
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
	
	Vector<ArgumentVector> RoundRobin() {

		ArgumentVector[] RRReadyQueue = new ArgumentVector[ProcessCount];

		for (int i = 0; i < ProcessCount; i++) {
			RRReadyQueue[i] = new ArgumentVector(ArrivalTime[i], RunningTime[i], PID[i], Priority[i], color[i]);
		}

		ArgumentVector RRTemp = new ArgumentVector(0, 0, "temp", 0, color[0]);

		for (int i = ProcessCount - 1; i > 0; i--) {
			for (int j = 0; j < i; j++) {
				if (RRReadyQueue[j].ReturnArrivalTime() > RRReadyQueue[j + 1].ReturnArrivalTime()) {
					RRTemp = RRReadyQueue[j];
					RRReadyQueue[j] = RRReadyQueue[j + 1];
					RRReadyQueue[j + 1] = RRTemp;
				}
			}
		}

		boolean isComplete = false;
		int TempRunningTime;

		int RRLoop = 0;
		int j=0;

		while (true) {
			isComplete = false;
			// System.out.println("hit");
			for (int i = 0; i < ProcessCount; i++) {
				if (RRReadyQueue[i].RunningTime > 0) {
					isComplete = true;
				}
			}

			if (!isComplete)
				break;

			if (RRReadyQueue[RRLoop].ReturnRunningTime() > 0) {
				if (RRReadyQueue[RRLoop].ReturnRunningTime() < TimeSlice) {
					RoundRobinGantt.add(RRReadyQueue[RRLoop].clone());
					RRReadyQueue[RRLoop].RunningTime = 0;
				} else {
					TempRunningTime = RRReadyQueue[RRLoop].ReturnRunningTime();
					// TempRunningTime = TempRunningTime - TimeSlice;
					// RRReadyQueue[RRLoop].RunningTime = TimeSlice;
					RRReadyQueue[RRLoop].SetRunningTime(TimeSlice);
					//RoundRobinGantt.add(RRReadyQueue[RRLoop]);
					//RoundRobinGantt.add(new ArgumentVector(RRReadyQueue[RRLoop].ArrivalTime, RRReadyQueue[RRLoop].RunningTime, RRReadyQueue[RRLoop].PID, RRReadyQueue[RRLoop].Priority));
					RoundRobinGantt.add(RRReadyQueue[RRLoop].clone());
					// RRReadyQueue[RRLoop].RunningTime = TempRunningTime - TimeSlice;
					RRReadyQueue[RRLoop].SetRunningTime(TempRunningTime - TimeSlice);	//여기서 수정하면서 위에 add한것도 같이 수정됨
				}
			}
			// System.out.println(RRLoop);
			RRLoop++;
			if (RRLoop == ProcessCount) {
				RRLoop = 0;
			}
		}
		// System.out.println(RoundRobinGantt.size());
		return RoundRobinGantt;
	}
	
	Vector<ArgumentVector> Preemption(){
		
		return PreemptionGantt;
	}
}