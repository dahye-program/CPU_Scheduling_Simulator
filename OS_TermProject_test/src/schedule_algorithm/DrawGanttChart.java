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
      Vector<ArgumentVector> SRTGantt = new Vector<ArgumentVector>();
      Vector<ArgumentVector> NonPreemptionGantt = new Vector<ArgumentVector>();
      
      Vector<ArgumentVector> SJFGantt = new Vector<ArgumentVector>();
<<<<<<< HEAD
      Vector<ArgumentVector_> HRNGantt = new Vector<ArgumentVector_>();
      Vector<ArgumentVector> NonPreemptionGantt = new Vector<ArgumentVector>();
=======
      Vector<ArgumentVector> HRNGantt = new Vector<ArgumentVector>();
      
>>>>>>> branch 'main' of https://github.com/dahye-program/CPU_Scheduling_Simulator.git
      
      int TotalRunningTime = 0;
      double FCFSTotalWaitingTime = 0;
      double FCFSTotalReturnTime = 0;
      double RRAWT = 0;
      double RRATT = 0;
      double PPAWT = 0;
      double PPATT = 0;
      
      for(int i=0;i<ProcessCount;i++) {
         TotalRunningTime += RunningTime[i];
      }
      
      AlgorithmKangmin kangmin = new AlgorithmKangmin(ProcessCount, TimeSlice, PID, ArrivalTime, RunningTime, Priority, color);
      AlgorithmDahye dahye = new AlgorithmDahye(ProcessCount, TimeSlice, PID, ArrivalTime, RunningTime, Priority, color);
      
      FCFSGantt = kangmin.FCFS();
      RoundRobinGantt = kangmin.RoundRobin();
      PreemptionGantt = kangmin.Preemption();
      SRTGantt = kangmin.SRT();
      
      // Dahye Code
      SJFGantt = dahye.SJF();
      HRNGantt = dahye.HRN();
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
      Canvas[] SRT = new Canvas[SRTGantt.size()];
      
      Canvas[] SJF = new Canvas[SJFGantt.size()];
      Canvas[] HRN = new Canvas[HRNGantt.size()];
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
         FCFSTotalReturnTime += FCFSTemp/STANDARDPX + FCFSGantt.get(i).ReturnRunningTime(); // GUI아님         
         FCFSTemp += (FCFSGantt.get(i).ReturnRunningTime() * STANDARDPX);
         getContentPane().add(FCFS[i]);
         FCFSTotalWaitingTime+= FCFSTemp/STANDARDPX - FCFSGantt.get(i).ArrivalTime;			// GUI아님
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
      
      int SRTTemp = 0;
      for(int i=0;i<SRTGantt.size();i++) {
    	  SRT[i] = new Canvas();
    	  SRT[i].setBackground(SRTGantt.get(i).ReturnColor());
    	  SRT[i].setBounds(10+SRTTemp, 500, SRTGantt.get(i).ReturnRunningTime()*STANDARDPX, STANDARDPX);
    	  SRTTemp += (SRTGantt.get(i).ReturnRunningTime()*STANDARDPX);
    	  getContentPane().add(SRT[i]);
      }
      
      
      int SJFTemp = 0;
      for(int i=0;i<SJFGantt.size();i++) {
    	  SJF[i] = new Canvas();
    	  SJF[i].setBackground(SJFGantt.get(i).ReturnColor());
    	  SJF[i].setBounds(10+SJFTemp, 600, SJFGantt.get(i).ReturnRunningTime()*STANDARDPX, STANDARDPX);
    	  SJFTemp += (SJFGantt.get(i).ReturnRunningTime()*STANDARDPX);
    	  getContentPane().add(SJF[i]);
      }
      
      int HRNTemp = 0;
      for(int i=0;i<HRNGantt.size();i++) {
    	  HRN[i] = new Canvas();
    	  HRN[i].setBackground(HRNGantt.get(i).ReturnColor());
    	  HRN[i].setBounds(10+HRNTemp, 700, HRNGantt.get(i).ReturnRunningTime()*STANDARDPX, STANDARDPX);
    	  HRNTemp += (HRNGantt.get(i).ReturnRunningTime()*STANDARDPX);
    	  getContentPane().add(HRN[i]);
      }
      
      //평균대기시간 평균반환시간 계산
      FCFSTotalWaitingTime = FCFSTotalWaitingTime / (double)ProcessCount;
      FCFSTotalReturnTime = FCFSTotalReturnTime / (double)ProcessCount;
      JLabel LabelFCFSAWT = new JLabel("평균 대기 시간 : " + FCFSTotalWaitingTime);
      JLabel LabelFCFSATT = new JLabel("평균 반환 시간 : " + FCFSTotalReturnTime);
      LabelFCFSAWT.setBounds(10, 240, 300, 20);
      LabelFCFSATT.setBounds(310, 240, 300, 20);
      getContentPane().add(LabelFCFSAWT);
      getContentPane().add(LabelFCFSATT);
      
      RRATT = kangmin.ReturnRRReturnTime() / (double)ProcessCount;
      RRAWT = kangmin.ReturnRRWainttime() / (double)ProcessCount;
      JLabel LabelRRAWT = new JLabel("평균 대기 시간 : " + RRAWT);
      JLabel LabelRRATT = new JLabel("평균 반환 시간 : " + RRATT);
      LabelRRAWT.setBounds(10, 340, 300, 20);
      LabelRRATT.setBounds(310, 340, 300, 20);
      getContentPane().add(LabelRRAWT);
      getContentPane().add(LabelRRATT);
      
      PPATT = kangmin.ReturnPPReturnTime() / (double)ProcessCount;
      
      JLabel LabelPPAWT = new JLabel("평균 대기 시간 : " + PPAWT);
      JLabel LabelPPATT = new JLabel("평균 반환 시간 : " + PPATT);
      LabelPPAWT.setBounds(10, 440, 300, 20);
      LabelPPATT.setBounds(310, 440, 300, 20);
      getContentPane().add(LabelPPAWT);
      getContentPane().add(LabelPPATT);
   }
}