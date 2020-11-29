package schedule_algorithm;

import java.awt.Color;
import java.beans.PropertyEditorManager;
import java.util.Vector;

public class AlgorithmKangmin {
	int ProcessCount;
	int TimeSlice;
	int[] ArrivalTime;
	int[] RunningTime;
	int[] Priority;
	String[] PID;
	Color[] color;
	
	double RRTotalRunningTime = 0;
	double RRTotalWaitingTime = 0;
	double RRTotalReturnTime = 0;
	double PPTotalWatingTime = 0;
	double PPTotalReturnTime = 0;
	
	// 반환할 것들 만들어줌
	 Vector<ArgumentVector> FCFSGantt = new Vector<ArgumentVector>();
	 Vector<ArgumentVector> RoundRobinGantt = new Vector<ArgumentVector>();
	 Vector<ArgumentVector> PreemptionGantt = new Vector<ArgumentVector>();
	 Vector<ArgumentVector> SRTGantt = new Vector<ArgumentVector>();
	 Vector<ArgumentVector> NPPGantt = new Vector<ArgumentVector>();
	

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
			FCFSReadyQueue[i].WaitingTime = totalWaitingTime - FCFSReadyQueue[i].ArrivalTime;
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
		double totalRunnigTime = 0;
		int tempLoop = 0;

		int RRLoop = 0;

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
				
				///////////// 평균 반환, 대기 시간 구하려는거///////////////////
				totalRunnigTime += RoundRobinGantt.get(tempLoop).RunningTime;
				if(RRReadyQueue[RRLoop].ReturnRunningTime()==0) {
					RRReadyQueue[RRLoop].SetReturnTime(totalRunnigTime);
				}
				
				
				tempLoop++;
				//////////////////////////////////////////////////////
			}
			// System.out.println(RRLoop);
			RRLoop++;
			if (RRLoop == ProcessCount) {
				RRLoop = 0;
			}
		}
		
		//////////// 평균 반환 대기시간 구하는거//////////////
		for(int j=0;j<RRReadyQueue.length;j++) {
			RRTotalReturnTime += RRReadyQueue[j].ReturnTime;
		}
		for(int j=0;j<RoundRobinGantt.size();j++) {
			for(int k=0;k<RRReadyQueue.length;k++) {
				if(RoundRobinGantt.get(j).PID==RRReadyQueue[k].PID) {
					RRReadyQueue[k].SetWaitingTime(RRTotalRunningTime-(double)RRReadyQueue[k].ArrivalTime);
					RRReadyQueue[k].ArrivalTime = (int) RRTotalRunningTime; // 여기 문제생길수도 있는 코드
					RRTotalRunningTime += (double)RoundRobinGantt.get(j).RunningTime;
				}
			}
		}
		for(int j=0;j<RRReadyQueue.length;j++) {
			RRTotalWaitingTime +=RRReadyQueue[j].WaitingTime;
		}
		//////////////////////////////////////////////
		
		// System.out.println(RoundRobinGantt.size());
		return RoundRobinGantt;
	}
	
	Vector<ArgumentVector> Preemption(){
		ArgumentVector[] PPReadyQueue = new ArgumentVector[ProcessCount];
		ArgumentVector PPTemp = new ArgumentVector(0, 0, "temp", 0, color[0]);
		int totalRunningTime = 0;
		
		for(int i=0;i<ProcessCount;i++) {
			PPReadyQueue[i] = new ArgumentVector(ArrivalTime[i], RunningTime[i], PID[i], Priority[i], color[i]);
			totalRunningTime += PPReadyQueue[i].ReturnTime;
		}
		
		for(int i = ProcessCount-1; i>0;i--) {
			for(int j=0;j<i;j++) {
				if(PPReadyQueue[j].ReturnArrivalTime()>PPReadyQueue[j+1].ReturnArrivalTime()) {
					PPTemp = PPReadyQueue[j].clone();
					PPReadyQueue[j] = PPReadyQueue[j+1].clone();
					PPReadyQueue[j+1] = PPTemp.clone();
				}
			}
		}
		
		boolean isComplete = false;
		
		
		int PPLoop = 0;
		int currentRunningTime = 0;
		int currentIndex;
		int tempRunningTime;
		
		while (true) {

			isComplete = false;
			currentIndex = 0;

			for (int i = 0; i < ProcessCount; i++) {
				if (PPReadyQueue[i].RunningTime > 0) {
					isComplete = true;
				}
			}

			if (!isComplete)
				break;

			PPTemp.ReSet(10000, 0, null, 10000);

			for (PPLoop = 0; PPLoop < ProcessCount; PPLoop++) {
				if (currentRunningTime >= PPReadyQueue[PPLoop].ArrivalTime && PPReadyQueue[PPLoop].RunningTime > 0) {
					if (PPTemp.Priority > PPReadyQueue[PPLoop].Priority) {
						PPTemp = PPReadyQueue[PPLoop].clone();
						currentIndex = PPLoop;
					}
				}
			}
			
			if (PPTemp.ArrivalTime <= currentRunningTime) {
				tempRunningTime = PPTemp.ReturnRunningTime();
				PPTemp.SetRunningTime(1);
				PreemptionGantt.add(PPTemp.clone());
				PPTemp.SetRunningTime(tempRunningTime - 1);
				PPReadyQueue[currentIndex] = PPTemp.clone();
				
				/////////////평균 대기, 반환 시간 구하는거//////////////
				if(PPReadyQueue[currentIndex].ReturnRunningTime()==0) {
					PPReadyQueue[currentIndex].SetReturnTime((double)PreemptionGantt.size());
				}
				/////////////////////////////////////////////////
				
			}
			currentRunningTime++;
		}
		
		///////////////평균 대기, 반환 시간 구하는거////////////
		for(int j=0;j<PPReadyQueue.length;j++) {
			PPTotalReturnTime += PPReadyQueue[j].ReturnTime;
		}
		/////////////////////////////////////////////////
		
		return PreemptionGantt;
	}
	
	Vector<ArgumentVector> SRT(){
		ArgumentVector[] SRTReadyQueue = new ArgumentVector[ProcessCount];
		ArgumentVector Temp = new ArgumentVector(0, 0, null, 0, null);
		
		
		for(int i=0;i<ProcessCount;i++) {
			SRTReadyQueue[i] = new ArgumentVector(ArrivalTime[i], RunningTime[i], PID[i], Priority[i], color[i]);
		}
		
		for(int i = ProcessCount - 1;i>=0;i--) {
			for(int j=0;j<i;j++) {
				if(SRTReadyQueue[j].ArrivalTime>SRTReadyQueue[j+1].ArrivalTime) {
					Temp = SRTReadyQueue[j+1].clone();
					SRTReadyQueue[j+1] = SRTReadyQueue[j].clone();
					SRTReadyQueue[j] = Temp.clone();
				}
			}
		}
		
		boolean isComplete = false;
		int TempRunningTime;
		double totalRunnigTime = 0;
		int tempLoop = 0;

		int SRTLoop = 0;

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
			
			for (SRTLoop = 0; SRTLoop < ProcessCount; SRTLoop++) {
				if (SRTReadyQueue[SRTLoop].ReturnRunningTime() > 0) {
					if (SRTReadyQueue[SRTLoop].ReturnRunningTime() < TimeSlice) {
						SRTGantt.add(SRTReadyQueue[SRTLoop].clone());
						SRTReadyQueue[SRTLoop].RunningTime = 0;
						for (int i = ProcessCount - 1; i >= 0; i--) {
							for (int j = 0; j < i; j++) {
								if (SRTReadyQueue[j].ReturnRunningTime() > SRTReadyQueue[j + 1].ReturnRunningTime()) {
									Temp = SRTReadyQueue[j + 1].clone();
									SRTReadyQueue[j + 1] = SRTReadyQueue[j].clone();
									SRTReadyQueue[j] = Temp.clone();
								}
							}
						}
						break;
					} else {
						TempRunningTime = SRTReadyQueue[SRTLoop].ReturnRunningTime();
						SRTReadyQueue[SRTLoop].SetRunningTime(TimeSlice);
						SRTGantt.add(SRTReadyQueue[SRTLoop].clone());
						SRTReadyQueue[SRTLoop].SetRunningTime(TempRunningTime - TimeSlice); // 여기서 수정하면서 위에 add한것도 같이
						for (int i = ProcessCount - 1; i >= 0; i--) {
							for (int j = 0; j < i; j++) {
								if (SRTReadyQueue[j].ReturnRunningTime() > SRTReadyQueue[j + 1].ReturnRunningTime()) {
									Temp = SRTReadyQueue[j + 1].clone();
									SRTReadyQueue[j + 1] = SRTReadyQueue[j].clone();
									SRTReadyQueue[j] = Temp.clone();
								}
							}
						}
						break;
					}
					
				}
			}
		}
		return SRTGantt;
	}
	
	Vector<ArgumentVector> NPP(){
		ArgumentVector[] NPPReadyQueue = new ArgumentVector[ProcessCount];
		ArgumentVector Temp = new ArgumentVector(0, 0, null, 0, null);
		
		int currentRunningTime = 0;
		boolean isComplete = false;
		boolean isAdd = false;
		
		for(int i=0;i<ProcessCount;i++) {
			NPPReadyQueue[i] = new ArgumentVector(ArrivalTime[i], RunningTime[i], PID[i], Priority[i], color[i]);
		}
		
		for(int i=ProcessCount-1;i>=0;i--) {
			for(int j=0;j<i;j++) {
				if(NPPReadyQueue[j].ReturnPriority()>NPPReadyQueue[j+1].ReturnPriority()) {
					Temp = NPPReadyQueue[j];
					NPPReadyQueue[j] = NPPReadyQueue[j+1];
					NPPReadyQueue[j+1] = Temp;
				}
			}
		}
		
		while(true) {
			isComplete = false;
			
			for(int i=0;i<ProcessCount;i++) {
				if(NPPReadyQueue[i].ReturnRunningTime()>0) {
					isComplete = true;
				}
			}//fdsa
			
			if(!isComplete) {
				break;
			}
			
			isAdd = false;
			
			for(int i=0;i<ProcessCount;i++) {
				if(NPPReadyQueue[i].ReturnRunningTime()>0 && NPPReadyQueue[i].ReturnArrivalTime()<currentRunningTime) {
					NPPGantt.add(NPPReadyQueue[i].clone());
					currentRunningTime+=NPPReadyQueue[i].ReturnRunningTime();
					NPPReadyQueue[i].SetRunningTime(0);
					isAdd = true;
				}
			}
			
			if(!isAdd) {
				currentRunningTime++;
			}
			
		}
		
		
		return NPPGantt;
	}
	
	double ReturnRRReturnTime() {
		return RRTotalReturnTime;
	}
	
	double ReturnRRWainttime() {
		return RRTotalWaitingTime;
	}
	
	double ReturnPPReturnTime() {
		return PPTotalReturnTime;
	}
}