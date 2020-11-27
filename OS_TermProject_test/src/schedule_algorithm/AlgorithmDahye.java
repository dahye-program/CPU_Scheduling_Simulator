package schedule_algorithm;

import java.util.Vector;

public class AlgorithmDahye {
	int ProcessCount;
	int TimeSlice;
	int[] ArrivalTime;
	int[] RunningTime;
	int[] Priority;
	String[] PID;
	
	// ��ȯ�� �͵� �������
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
		 
		 // TODO: ó������ ���� ���μ����� ù��° ���� -> ���� ���� �ּҰ�
		 for(int i=0;i<ProcessCount;i++) {
				//���� �ð��� 0�ΰ� ���� �ؾߵɰžƳ� ���� ������
			
			}
		 
		 // 2��° ���μ������� �۾� �ð� ª�� ������ ���� -> �۾� �ð� ��������
		 for(int i=1;i<ProcessCount;i++) {
			 if(SJFReadyQueue[i].GetRunningTime() > SJFReadyQueue[i+1].GetRunningTime()) {
				 temp = SJFReadyQueue[i];
				 SJFReadyQueue[i] = SJFReadyQueue[i+1];
				 SJFReadyQueue[i+1] = temp;
				 //���������ð� �������?
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
		// �� Ǫ�� ��𰬾� ����
		 
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