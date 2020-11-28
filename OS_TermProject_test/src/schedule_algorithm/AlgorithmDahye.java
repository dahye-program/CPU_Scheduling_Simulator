package schedule_algorithm;

import java.awt.Color;
import java.util.Vector;

public class AlgorithmDahye {
	int ProcessCount;
	int TimeSlice;
	int[] ArrivalTime;
	int[] RunningTime;
	int[] Priority;
	String[] PID;
	Color[] color;
	
	// 반환할 것들 만들어줌
	 Vector<ArgumentVector> SJFGantt = new Vector<ArgumentVector>();
	 Vector<ArgumentVector> HRNGantt = new Vector<ArgumentVector>();
	 Vector<ArgumentVector> SRTGantt = new Vector<ArgumentVector>();
	 Vector<ArgumentVector> NonPreemptionGantt = new Vector<ArgumentVector>();
	
	 public AlgorithmDahye(int ProcessCount, int TimeSlice, String[] PID, int[] ArrivalTime, int[] RunningTime, int[] Priority, Color[] color) {
			// TODO Auto-generated constructor stub
			this.ProcessCount = ProcessCount;
			this.TimeSlice = TimeSlice;
			this.ArrivalTime = ArrivalTime;
			this.RunningTime = RunningTime;
			this.Priority = Priority;
			this.PID = PID;
			this.color = color;
		}
	 
	 Vector<ArgumentVector> SJF(){
		 ArgumentVector temp;
		 int totalWaitingTime = 0;
		 ArgumentVector[] SJFReadyQueue = new ArgumentVector[ProcessCount];	
		 
		 for(int i=0;i<ProcessCount;i++) {
				SJFReadyQueue[i] = new ArgumentVector(ArrivalTime[i], RunningTime[i], PID[i], Priority[i],color[i]);
			}
		 
		 ArgumentVector SJFTemp = new ArgumentVector(0,0,"temp",0,color[0]);
		 // 도착 시간 순서대로 정렬
		 for (int i = ProcessCount - 1; i > 0; i--) {
				for (int j = 0; j < i; j++) {
					if (SJFReadyQueue[j].ReturnArrivalTime() > SJFReadyQueue[j + 1].ReturnArrivalTime()) {
						SJFTemp = SJFReadyQueue[j];
						SJFReadyQueue[j] = SJFReadyQueue[j + 1];
						SJFReadyQueue[j + 1] = SJFTemp;
					}
				}
			}
		 //첫번째로 도착한 프로세스 제외하고 
		 // 2번째 프로세스부터 작업 시간 짧은 순으로 정렬 -> 작업 시간 오름차순
		 for (int i = 1; i < ProcessCount-1 ; i++) {
					if (SJFReadyQueue[i].GetRunningTime() > SJFReadyQueue[i + 1].GetRunningTime()) {
						SJFTemp = SJFReadyQueue[i];
						SJFReadyQueue[i] = SJFReadyQueue[i + 1];
						SJFReadyQueue[i + 1] = SJFTemp;
					}
			}
		
		for(int i=0;i<ProcessCount;i++) {
			SJFReadyQueue[i].WaitingTime = totalWaitingTime - SJFReadyQueue[i].ArrivalTime;
			totalWaitingTime+=SJFReadyQueue[i].RunningTime;
			SJFReadyQueue[i].ReturnTime = totalWaitingTime;
			}
		
			
		for(int i=0;i<ProcessCount;i++) {
			SJFGantt.add(SJFReadyQueue[i]);
		}
		 
		return SJFGantt;
	}
	 
	 Vector<ArgumentVector> HRN(){
			
			return HRNGantt;
		}
	 
	 Vector<ArgumentVector> SRT(){
			
			return SRTGantt;
		}
	 Vector<ArgumentVector> NonPreemption(){
			
			return NonPreemptionGantt;
		}

}