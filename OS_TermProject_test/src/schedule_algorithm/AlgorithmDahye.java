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
	boolean Completed;
	double SJFTotalWaitingTime=0;
	double SJFTotalReturnTime=0;

	// 반환할 것들 만들어줌
	Vector<ArgumentVector> SJFGantt = new Vector<ArgumentVector>();
	Vector<ArgumentVector_> HRNGantt = new Vector<ArgumentVector_>();
	Vector<ArgumentVector> SRTGantt = new Vector<ArgumentVector>();
	Vector<ArgumentVector> NonPreemptionGantt = new Vector<ArgumentVector>();

	public AlgorithmDahye(int ProcessCount, int TimeSlice, String[] PID, int[] ArrivalTime, int[] RunningTime,
		int[] Priority, Color[] color) {
		// TODO Auto-generated constructor stub
		this.ProcessCount = ProcessCount;
		this.TimeSlice = TimeSlice;
		this.ArrivalTime = ArrivalTime;
		this.RunningTime = RunningTime;
		this.Priority = Priority;
		this.PID = PID;
		this.color = color;
		//this.Completed = false;
	}

	Vector<ArgumentVector> SJF() {
		int totalWaitingTime = 0;
		int currentTime = 0;
		ArgumentVector[] SJFReadyQueue = new ArgumentVector[ProcessCount];
		ArgumentVector Temp = new ArgumentVector(0, 0, "temp", 0, color[0]);

		for (int i = 0; i < ProcessCount; i++) {
			SJFReadyQueue[i] = new ArgumentVector(ArrivalTime[i], RunningTime[i], PID[i], Priority[i], color[i]);
		}

		ArgumentVector SJFTemp = new ArgumentVector(0, 0, "temp", 0, color[0]);
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
		// 2번째 프로세스부터 작업 시간 짧은 순으로 정렬 -> 작업 시간 오름차순
		boolean isAdd = false;
		boolean isComplete = false;
		int currentRunningTime = 0;
		
		while(true) {
			
			isComplete = false;
			
			for(int i=0;i<ProcessCount;i++) {
				if(SJFReadyQueue[i].ReturnRunningTime()>0) {
					isComplete = true;
				}
			}
			
			if(!isComplete) {
				break;
			}
			
			for(int i=0;i<ProcessCount;i++) {
				if(SJFReadyQueue[i].ReturnRunningTime()>0 && SJFReadyQueue[i].ReturnArrivalTime()<=currentRunningTime) {
					SJFGantt.add(SJFReadyQueue[i].clone());
					currentRunningTime += SJFReadyQueue[i].ReturnRunningTime();
					SJFReadyQueue[i].SetRunningTime(0);
					for(int j=1;j<ProcessCount-1;j++) {
						if (SJFReadyQueue[j].GetRunningTime() > SJFReadyQueue[j + 1].GetRunningTime()) {
							SJFTemp = SJFReadyQueue[j];
							SJFReadyQueue[j] = SJFReadyQueue[j + 1];
							SJFReadyQueue[j + 1] = SJFTemp;
						}
					}
					isAdd = true;
				}
				if(!isAdd) {
					currentRunningTime++;
				}
				isAdd = false;
			}
		}
		for(int i=0; i<ProcessCount; i++) {
			currentTime += SJFReadyQueue[i].RunningTime;
			SJFReadyQueue[i].WaitingTime = currentTime - SJFReadyQueue[i].ReturnArrivalTime();
			SJFReadyQueue[i].ReturnTime = currentTime+SJFReadyQueue[i].ReturnRunningTime();
		}
		
		//평균 반환시간, 대기시간 
		for(int j=0;j<ProcessCount;j++) {
			SJFTotalReturnTime += SJFReadyQueue[j].ReturnTime;
			SJFTotalWaitingTime += SJFReadyQueue[j].WaitingTime;
		}

		for (int i = 0; i < ProcessCount; i++) {
			SJFGantt.add(SJFReadyQueue[i]);
		}
		return SJFGantt;
	}

	Vector<ArgumentVector_> HRN() {
		//할로하
		int totalWaitingTime = 0;
		int totalRunningTime = 0;
		int currentRunningTime = 0;
		
		ArgumentVector_[] HRNReadyQueue = new ArgumentVector_[ProcessCount];
		
		for (int i = 0; i < ProcessCount; i++) {
			HRNReadyQueue[i] = new ArgumentVector_(ArrivalTime[i], RunningTime[i], PID[i], Priority[i], color[i]);
			totalRunningTime += HRNReadyQueue[i].ReturnRunningTime(); //총 실행시간
		}
		ArgumentVector_ HRNTemp = new ArgumentVector_(0, 0, "temp", 0, color[0]);
		// 도착 시간 순서대로 정렬
		for (int i = ProcessCount - 1; i > 0; i--) {
			for (int j = 0; j < i; j++) {
				if (HRNReadyQueue[j].ReturnArrivalTime() > HRNReadyQueue[j + 1].ReturnArrivalTime()) {
					HRNTemp = HRNReadyQueue[j];
					HRNReadyQueue[j] = HRNReadyQueue[j + 1];
					HRNReadyQueue[j + 1] = HRNTemp;
				}
			}
		}
		
		while (true) {
	         int i = 0;
	         if (HRNReadyQueue[i].ReturnRunningTime() > 0) {
	            HRNGantt.add(HRNReadyQueue[i]);
	            currentRunningTime += HRNReadyQueue[i].ReturnRunningTime();
	            for (int j = 1; j < ProcessCount-1; j++) {
	               if (HRNReadyQueue[j].ReturnArrivalTime() <= currentRunningTime && HRNReadyQueue[j].ReturnRunningTime() > 0) {
	            	   //우선순위 계산해서 priority set
	                  HRNReadyQueue[j].setPriority((double)(currentRunningTime - HRNReadyQueue[j].ReturnArrivalTime()
		                        + (double)HRNReadyQueue[j].ReturnRunningTime()) / (double)HRNReadyQueue[j].ReturnRunningTime());
	               }
	            }
	         }
	         i++;
	         if (i == ProcessCount-1) {
	            break;
	         }
	      }


		// P1은 위에서 바로 Set함
		for (int i = 1; i < ProcessCount; i++) {
			HRNReadyQueue[i].WaitingTime = totalWaitingTime - HRNReadyQueue[i].ArrivalTime;
			totalWaitingTime += HRNReadyQueue[i].RunningTime;
			HRNReadyQueue[i].ReturnTime = totalWaitingTime;
		}

		for (int i = 0; i < ProcessCount; i++) {
			HRNGantt.add(HRNReadyQueue[i]);
		}

		return HRNGantt;
	}

	Vector<ArgumentVector> SRT() {
		int totalWaitingTime = 0;
		ArgumentVector[] SRTReadyQueue = new ArgumentVector[ProcessCount];

		for (int i = 0; i < ProcessCount; i++) {
			SRTReadyQueue[i] = new ArgumentVector(ArrivalTime[i], RunningTime[i], PID[i], Priority[i], color[i]);
		}

		ArgumentVector SRTTemp = new ArgumentVector(0, 0, "temp", 0, color[0]);
		// 도착 시간 순서대로 정렬
		for (int i = ProcessCount - 1; i > 0; i--) {
			for (int j = 0; j < i; j++) {
				if (SRTReadyQueue[j].ReturnArrivalTime() > SRTReadyQueue[j + 1].ReturnArrivalTime()) {
					SRTTemp = SRTReadyQueue[j];
					SRTReadyQueue[j] = SRTReadyQueue[j + 1];
					SRTReadyQueue[j + 1] = SRTTemp;
				}
			}
		}
		boolean isComplete = false;
		int TempRunningTime;

		int SRTLoop = 0;
		int j = 0;

		while (true) {
			isComplete = false;
			// System.out.println("hit");
			for (int i = 0; i < ProcessCount; i++) {
				if (SRTReadyQueue[i].RunningTime > 0) {
					isComplete = true;
				}
			}

			if (!isComplete)
				break;

			if (SRTReadyQueue[SRTLoop].ReturnRunningTime() > 0) {
				if (SRTReadyQueue[SRTLoop].ReturnRunningTime() < TimeSlice) {
					SRTGantt.add(SRTReadyQueue[SRTLoop].clone());
					SRTReadyQueue[SRTLoop].RunningTime = 0;
				} else {
					TempRunningTime = SRTReadyQueue[SRTLoop].ReturnRunningTime();
					// TempRunningTime = TempRunningTime - TimeSlice;
					// RRReadyQueue[RRLoop].RunningTime = TimeSlice;
					SRTReadyQueue[SRTLoop].SetRunningTime(TimeSlice);
					// RoundRobinGantt.add(RRReadyQueue[RRLoop]);
					// RoundRobinGantt.add(new ArgumentVector(RRReadyQueue[RRLoop].ArrivalTime,
					// RRReadyQueue[RRLoop].RunningTime, RRReadyQueue[RRLoop].PID,
					// RRReadyQueue[RRLoop].Priority));
					SRTGantt.add(SRTReadyQueue[SRTLoop].clone());
					// RRReadyQueue[RRLoop].RunningTime = TempRunningTime - TimeSlice;
					SRTReadyQueue[SRTLoop].SetRunningTime(TempRunningTime - TimeSlice); // 여기서 수정하면서 위에 add한것도 같이 수정됨
				}
			}
			// System.out.println(RRLoop);
			SRTLoop++;
			if (SRTLoop == ProcessCount) {
				SRTLoop = 0;
			}
		}
		return SRTGantt;
	}

	Vector<ArgumentVector> NonPreemption() {

		return NonPreemptionGantt;
	}

}