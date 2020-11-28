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
      
      // ���⼭���� GUI ����
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setTitle("GanttChart");
      setSize(1000,1000);
      setVisible(true);
      
      Container c = getContentPane();
      c.setLayout(null);
      
      
      JLabel LabelFCFS = new JLabel("FCFS��Ʈ��Ʈ");
      JLabel LabelRR = new JLabel("����κ�Ʈ��Ʈ");
      JLabel LabelPP = new JLabel("������Ʈ��Ʈ");
      JLabel LabelSRT = new JLabel("SRT��Ʈ��Ʈ");
      JLabel LabelSJF = new JLabel("SJF��Ʈ��Ʈ");
      JLabel LabelHRN = new JLabel("HRN��Ʈ��Ʈ");
      JLabel LabelNPP = new JLabel("������Ʈ��Ʈ");
      
      JLabel[] LabelPID = new JLabel[ProcessCount];
      
      
      LabelFCFS.setBounds(10, 80, 150, 20);
      LabelRR.setBounds(10,180,150,20);
      LabelPP.setBounds(10,280,150,20);
      LabelSRT.setBounds(10,380,150,20);
      LabelSJF.setBounds(10,480,150,20);
      LabelHRN.setBounds(10,580,150,20);
      LabelNPP.setBounds(10,680,150,20);
   
      
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
      
      int ProcessColorTemp = 0;
      for(int i=0;i<ProcessCount;i++) {
    	  LabelPID[i] = new JLabel(FCFSGantt.get(i).PID);
    	  LabelPID[i].setBounds(10+ProcessColorTemp, 50, STANDARDPX, STANDARDPX);
    	  getContentPane().add(LabelPID[i]);
    	  
    	  
    	  PrcoessColor[i] = new Canvas();
    	  PrcoessColor[i].setBackground(FCFSGantt.get(i).ReturnColor());
    	  PrcoessColor[i].setBounds(10+ProcessColorTemp, 20, STANDARDPX, STANDARDPX);
    	  ProcessColorTemp += STANDARDPX + 10;
    	  getContentPane().add(PrcoessColor[i]);
      }
      
      int FCFSTemp = 0;
      for (int i = 0; i < ProcessCount; i++) {
         FCFS[i] = new Canvas();
         FCFS[i].setBackground(FCFSGantt.get(i).ReturnColor());
         FCFS[i].setBounds(10 + FCFSTemp, 100, FCFSGantt.get(i).ReturnRunningTime() * STANDARDPX, STANDARDPX);
         FCFSTemp += (FCFSGantt.get(i).ReturnRunningTime() * STANDARDPX);
         
         /////////////////////////GUI���� �ٸ��κ�////////////////////////////
         //FCFSGantt.get(i).SetWaitingTime(FCFSTemp-FCFSGantt.get(i).ArrivalTime);
         //FCFSTotalWaitingTime+=FCFSGantt.get(i).WaitingTime;
         //FCFSTotalReturnTime+=FCFSTemp;
         //////////////////////////////////////////////////////////////////
         
         getContentPane().add(FCFS[i]);
      }
      
      int RoundRobinTemp = 0;
      for(int i=0;i<RoundRobinGantt.size();i++) {
         RoundRobin[i] = new Canvas();
         RoundRobin[i].setBackground(RoundRobinGantt.get(i).ReturnColor());
         RoundRobin[i].setBounds(10+RoundRobinTemp, 200, RoundRobinGantt.get(i).ReturnRunningTime()*STANDARDPX, STANDARDPX);
         RoundRobinTemp += (RoundRobinGantt.get(i).ReturnRunningTime()*STANDARDPX);
         getContentPane().add(RoundRobin[i]);
      }
      
      int PreemptionTemp = 0;
      for(int i=0;i<PreemptionGantt.size();i++) {
    	  Preemption[i] = new Canvas();
    	  Preemption[i].setBackground(PreemptionGantt.get(i).ReturnColor());
    	  Preemption[i].setBounds(10+PreemptionTemp, 300, PreemptionGantt.get(i).ReturnRunningTime()*STANDARDPX, STANDARDPX);
    	  PreemptionTemp += (PreemptionGantt.get(i).ReturnRunningTime()*STANDARDPX);
    	  getContentPane().add(Preemption[i]);
      }
      
      
      int SJFTemp = 0;
   }
}