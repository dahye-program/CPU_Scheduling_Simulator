package schedule_algorithm;

import java.awt.Container;
import java.util.Vector;

import javax.swing.*;
import java.awt.Canvas;
import java.awt.Color;

public class DrawGanttChart extends JFrame{
   final int STANDARDPX = 30;
   public DrawGanttChart(int ProcessCount, int TimeSlice, String[] PID, int[] ArrivalTime, int[] RunningTime, int[] Priority, Color[] color) {
      
      
      Vector<ArgumentVector> FCFSGantt = new Vector<ArgumentVector>();
      Vector<ArgumentVector> RoundRobinGantt = new Vector<ArgumentVector>();
      Vector<ArgumentVector> PreemptionGantt = new Vector<ArgumentVector>();
      
      Vector<ArgumentVector> SJFGantt = new Vector<ArgumentVector>();
      Vector<ArgumentVector> HRNGantt = new Vector<ArgumentVector>();
      Vector<ArgumentVector> SRTGantt = new Vector<ArgumentVector>();
      Vector<ArgumentVector> NonPreemptionGantt = new Vector<ArgumentVector>();
      
      int TotalRunningTime = 0;
      double FCFSTotalWaitingTime = 0;
      double FCFSTotalReturnTime = 0;
      
      for(int i=0;i<ProcessCount;i++) {
         TotalRunningTime += RunningTime[i];
      }
      
      AlgorithmKangmin kangmin = new AlgorithmKangmin(ProcessCount, TimeSlice, PID, ArrivalTime, RunningTime, Priority, color);
     // AlgorithmDahye dahye = new AlgorithmDahye(ProcessCount, TimeSlice, PID, ArrivalTime, RunningTime, Priority, color);
      
      FCFSGantt = kangmin.FCFS();
      RoundRobinGantt = kangmin.RoundRobin();
      PreemptionGantt = kangmin.Preemption();
      
      // Dahye Code
     // SJFGantt = dahye.SJF();
     // HRNGantt = dahye.HRN();
     // SRTGantt = dahye.SRT();
     // NonPreemptionGantt = dahye.NonPreemption();
            
      //System.out.println(RoundRobinGantt.size());
      
      // 여기서부터 GUI 영역
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setTitle("GanttChart");
      setSize(1000,1000);
      setVisible(true);
      
      Container c = getContentPane();
      c.setLayout(null);
      
      
      JLabel LabelFCFS = new JLabel("FCFS간트차트");
      JLabel LabelRR = new JLabel("라운드로빈간트차트");
      JLabel LabelPP = new JLabel("선점간트차트");
      JLabel LabelSRT = new JLabel("SRT간트차트");
      JLabel LabelSJF = new JLabel("SJF간트차트");
      JLabel LabelHRN = new JLabel("HRN간트차트");
      JLabel LabelNPP = new JLabel("비선점간트차트");
      
      JLabel[] LabelPID = new JLabel[ProcessCount];
      JLabel[] LabelRunningAndArrivalTime = new JLabel[ProcessCount];
      JLabel[] LabelFCFSSecond = new JLabel[PreemptionGantt.size()];
      JLabel[] LabelRRSecond = new JLabel[PreemptionGantt.size()];
      JLabel[] LabelPPSecond = new JLabel[PreemptionGantt.size()];
      JLabel[] LabelSRTSecond = new JLabel[PreemptionGantt.size()];
      JLabel[] LabelSJFSecond = new JLabel[PreemptionGantt.size()];
      JLabel[] LabelHRNSecond = new JLabel[PreemptionGantt.size()];
      JLabel[] LabelNPPSecond = new JLabel[PreemptionGantt.size()];
      
      
      LabelFCFS.setBounds(10, 180, 150, 20);
      LabelRR.setBounds(10,280,150,20);
      LabelPP.setBounds(10,380,150,20);
      LabelSRT.setBounds(10,480,150,20);
      LabelSJF.setBounds(10,580,150,20);
      LabelHRN.setBounds(10,680,150,20);
      LabelNPP.setBounds(10,780,150,20);
      
      
      getContentPane().add(LabelFCFS);
      getContentPane().add(LabelRR);
      getContentPane().add(LabelPP);
      getContentPane().add(LabelSRT);
      getContentPane().add(LabelSJF);
      getContentPane().add(LabelHRN);
      getContentPane().add(LabelNPP);
      
      Canvas[] PrcoessColor = new Canvas[ProcessCount];      
      Canvas[] FCFS = new Canvas[ProcessCount];
      Canvas[] RoundRobin = new Canvas[RoundRobinGantt.size()];
      Canvas[] Preemption = new Canvas[PreemptionGantt.size()];
      
      //Canvas[] SRT = new Canvas[SRTGantt.size()];
      //Canvas[] SJT = new Canvas[SJTGantt.size()];
      //Canvas[] HRN = new Canvas[HRNGantt.size()];
      //Canvas[] NPP = new Canvas[NPPGantt.size()];
      
      int secondTemp = 0;
      int second = 1;
      for(int i=0;i<PreemptionGantt.size();i++) {
    	  LabelFCFSSecond[i] = new JLabel(second+"");
    	  LabelRRSecond[i] = new JLabel(second+"");
    	  LabelPPSecond[i] = new JLabel(second+"");
    	  LabelSRTSecond[i] = new JLabel(second+"");
    	  LabelSJFSecond[i] = new JLabel(second+"");
    	  LabelHRNSecond[i] = new JLabel(second+"");
    	  LabelNPPSecond[i] = new JLabel(second+"");
    	  
    	  LabelFCFSSecond[i].setBounds(35+secondTemp,230, 20, 10);
    	  LabelRRSecond[i].setBounds(35+secondTemp,330, 20, 10);
    	  LabelPPSecond[i].setBounds(35+secondTemp,430, 20, 10);
    	  LabelSRTSecond[i].setBounds(35+secondTemp,530, 20, 10);
    	  LabelSJFSecond[i].setBounds(35+secondTemp,630, 20, 10);
    	  LabelHRNSecond[i].setBounds(35+secondTemp,730, 20, 10);
    	  LabelNPPSecond[i].setBounds(35+secondTemp,830, 20, 10);
    	  
    	  getContentPane().add(LabelFCFSSecond[i]);
    	  getContentPane().add(LabelRRSecond[i]);
    	  getContentPane().add(LabelPPSecond[i]);
    	  getContentPane().add(LabelSRTSecond[i]);
    	  getContentPane().add(LabelSJFSecond[i]);
    	  getContentPane().add(LabelHRNSecond[i]);
    	  getContentPane().add(LabelNPPSecond[i]);
    	  
    	  second++;
    	  
    	  secondTemp+=STANDARDPX;
      }
      
      
      int notifyTemp = 0;
      for(int i=0;i<ProcessCount;i++) {
    	  LabelPID[i] = new JLabel("PID = " + FCFSGantt.get(i).PID + " / Priority = " + FCFSGantt.get(i).Priority);
    	  LabelRunningAndArrivalTime[i] = new JLabel("RT = "+FCFSGantt.get(i).RunningTime + " / AT = " + FCFSGantt.get(i).ArrivalTime);
    	  
    	  LabelPID[i].setBounds(10+notifyTemp, 50, STANDARDPX*4, STANDARDPX);
    	  LabelRunningAndArrivalTime[i].setBounds(10+notifyTemp, 80, STANDARDPX*4, STANDARDPX);
    	  
    	  getContentPane().add(LabelPID[i]);
    	  getContentPane().add(LabelRunningAndArrivalTime[i]);
    	  
    	  
    	  PrcoessColor[i] = new Canvas();
    	  PrcoessColor[i].setBackground(FCFSGantt.get(i).ReturnColor());
    	  PrcoessColor[i].setBounds(10+notifyTemp, 20, STANDARDPX*4, STANDARDPX);
    	  notifyTemp += STANDARDPX*4 + 10;
    	  getContentPane().add(PrcoessColor[i]);
      }
      
      int FCFSTemp = 0;
      for (int i = 0; i < ProcessCount; i++) {
         FCFS[i] = new Canvas();
         FCFS[i].setBackground(FCFSGantt.get(i).ReturnColor());
         FCFS[i].setBounds(10 + FCFSTemp, 200, FCFSGantt.get(i).ReturnRunningTime() * STANDARDPX, STANDARDPX);
         FCFSTemp += (FCFSGantt.get(i).ReturnRunningTime() * STANDARDPX);
         
         /////////////////////////GUI말고 다른부분////////////////////////////
         FCFSGantt.get(i).SetWaitingTime(FCFSTemp-FCFSGantt.get(i).ArrivalTime);
         FCFSTotalWaitingTime+=FCFSGantt.get(i).WaitingTime;
         FCFSTotalReturnTime+=FCFSTemp;
         //////////////////////////////////////////////////////////////////
         
         getContentPane().add(FCFS[i]);
      }
      
      int RoundRobinTemp = 0;
      for(int i=0;i<RoundRobinGantt.size();i++) {
         RoundRobin[i] = new Canvas();
         RoundRobin[i].setBackground(RoundRobinGantt.get(i).ReturnColor());
         RoundRobin[i].setBounds(10+RoundRobinTemp, 300, RoundRobinGantt.get(i).ReturnRunningTime()*STANDARDPX, STANDARDPX);
         RoundRobinTemp += (RoundRobinGantt.get(i).ReturnRunningTime()*STANDARDPX);
         getContentPane().add(RoundRobin[i]);
      }
      
      int PreemptionTemp = 0;
      for(int i=0;i<PreemptionGantt.size();i++) {
    	  Preemption[i] = new Canvas();
    	  Preemption[i].setBackground(PreemptionGantt.get(i).ReturnColor());
    	  Preemption[i].setBounds(10+PreemptionTemp, 400, PreemptionGantt.get(i).ReturnRunningTime()*STANDARDPX, STANDARDPX);
    	  PreemptionTemp += (PreemptionGantt.get(i).ReturnRunningTime()*STANDARDPX);
    	  getContentPane().add(Preemption[i]);
      }
      
      
      int SJFTemp = 0;
      
      //평균대기시간 평균반환시간 계산
      FCFSTotalWaitingTime = FCFSTotalWaitingTime / (double)ProcessCount;
      FCFSTotalReturnTime = FCFSTotalReturnTime / (double)ProcessCount;
   }
}