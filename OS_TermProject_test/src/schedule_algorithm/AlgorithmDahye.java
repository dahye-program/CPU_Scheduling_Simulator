package schedule_algorithm;

import java.util.Vector;

public class AlgorithmDahye {
	int ProcessCount;
	int TimeSlice;
	int[] ArrivalTime;
	int[] RunningTime;
	int[] Priority;
	String[] PID;
	
	// 반환할 것들 만들어줌
	 Vector<ArgumentVector> SJFGantt = new Vector<ArgumentVector>();
	 Vector<ArgumentVector> HRNGantt = new Vector<ArgumentVector>();
	 Vector<ArgumentVector> SRTGantt = new Vector<ArgumentVector>();
	 Vector<ArgumentVector> NonPreemptionGantt = new Vector<ArgumentVector>();
	
	 public AlgorithmDahye(int ProcessCount, int TimeSlice, String[] PID, int[] ArrivalTime, int[] RunningTime, int[] Priority) {
			// TODO Auto-generated constructor stub
			this.ProcessCount = ProcessCount;
			this.TimeSlice = TimeSlice;
			this.ArrivalTime = ArrivalTime;
			this.RunningTime = RunningTime;
			this.Priority = Priority;
			this.PID = PID;
		}
	 
	 Vector<ArgumentVector> SJF(){
		 ArgumentVector temp;
		 int totalWaitingTime = 0;
		 ArgumentVector[] SJFReadyQueue = new ArgumentVector[ProcessCount];	
		 
		 for(int i=0;i<ProcessCount;i++) {
				SJFReadyQueue[i] = new ArgumentVector(ArrivalTime[i], RunningTime[i], PID[i], Priority[i]);
			}
		 
		 // TODO: 처음으로 들어온 프로세스가 첫번째 실행 -> 도착 순서 최소값
		 for(int i=0;i<ProcessCount;i++) {
				//도착 시간이 0인걸 먼저 해야될거아냐 ㅅㅂ 조깥네
			
			}
		 
		 // 2번째 프로세스부터 작업 시간 짧은 순으로 정렬 -> 작업 시간 오름차순
		 for(int i=1;i<ProcessCount;i++) {
			 if(SJFReadyQueue[i].GetRunningTime() > SJFReadyQueue[i+1].GetRunningTime()) {
				 temp = SJFReadyQueue[i];
				 SJFReadyQueue[i] = SJFReadyQueue[i+1];
				 SJFReadyQueue[i+1] = temp;
				 //ㅅㅂ도착시간 고려안해?
			 }
		 }

		ArgumentVector SJFTemp = new ArgumentVector(0,0,"temp",0);
		
		for(int i=0;i<ProcessCount;i++) {
			SJFReadyQueue[i].WaitTime = totalWaitingTime - SJFReadyQueue[i].ArrivalTime;
			totalWaitingTime+=SJFReadyQueue[i].RunningTime;
			SJFReadyQueue[i].ReturnTime = totalWaitingTime;
			}
			
		for(int i=0;i<ProcessCount;i++) {
			SJFGantt.add(SJFReadyQueue[i]);
		}
		// 내 푸쉬 어디갔어 ㅅㅂ
		 
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