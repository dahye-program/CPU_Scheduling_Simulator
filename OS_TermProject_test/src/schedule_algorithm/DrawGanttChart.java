package schedule_algorithm;

import java.awt.Container;
import java.util.Vector;

import javax.swing.*;
import java.awt.Canvas;
import java.awt.Color;

public class DrawGanttChart extends JFrame{
   final int STANDARDPX = 30;
   public DrawGanttChart(int ProcessCount, int TimeSlice, String[] PID, int[] ArrivalTime, int[] RunningTime, int[] Priority) {
      
      
      Vector<ArgumentVector> FCFSGantt = new Vector<ArgumentVector>();
      Vector<ArgumentVector> RoundRobinGantt = new Vector<ArgumentVector>();
      Vector<ArgumentVector> PreemptionGantt = new Vector<ArgumentVector>();
      
      Vector<ArgumentVector> SJFGantt = new Vector<ArgumentVector>();
      Vector<ArgumentVector> HRNGantt = new Vector<ArgumentVector>();
      Vector<ArgumentVector> SRTGantt = new Vector<ArgumentVector>();
      Vector<ArgumentVector> NonPreemptionGantt = new Vector<ArgumentVector>();
      
      int TotalRunningTime = 0;
      
      for(int i=0;i<ProcessCount;i++) {
         TotalRunningTime += RunningTime[i];
      }
      
      AlgorithmKangmin kangmin = new AlgorithmKangmin(ProcessCount, TimeSlice, PID, ArrivalTime, RunningTime, Priority);
      AlgorithmDahye dahye = new AlgorithmDahye(ProcessCount, TimeSlice, PID, ArrivalTime, RunningTime, Priority);
      
      FCFSGantt = kangmin.FCFS();
      RoundRobinGantt = kangmin.RoundRobin();
      //PreemptionGantt = kangmin.Preemption();
      
      // Dahye Code
      SJFGantt = dahye.SJF();
      HRNGantt = dahye.HRN();
      SRTGantt = dahye.SRT();
      NonPreemptionGantt = dahye.NonPreemption();
            
      //System.out.println(RoundRobinGantt.size());
      
      // ���⼭���� GUI ����
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setTitle("GhanttChart");
      setSize(600,1000);
      setVisible(true);
      
      
      JLabel LabelFCFS = new JLabel("FCFS��Ʈ��Ʈ");
      JLabel LabelRR = new JLabel("����κ�Ʈ��Ʈ");
      JLabel LabelPP = new JLabel("������Ʈ��Ʈ");
      JLabel LabelSRT = new JLabel("SRT��Ʈ��Ʈ");
      JLabel LabelSJF = new JLabel("SJF��Ʈ��Ʈ");
      JLabel LabelHRN = new JLabel("HRN��Ʈ��Ʈ");
      JLabel LabelNPP = new JLabel("������Ʈ��Ʈ");
      
      
      LabelFCFS.setBounds(10, 80, 100, 20);
   
      
      getContentPane().add(LabelFCFS);
      
      
      
      
      
      Container c = getContentPane();
      c.setLayout(null);
      
//      Canvas canvas = new Canvas();
//      canvas.setBackground(Color.RED);
//      canvas.setBounds(10, 100, STANDARDPX*TotalRunningTime, STANDARDPX);
//      canvas.setName("test");
//      getContentPane().add(canvas);
      
            
      Canvas[] FCFS = new Canvas[ProcessCount];
      Canvas[] RoundRobin = new Canvas[RoundRobinGantt.size()];
      
      //System.out.println(RoundRobinGantt.size());
      
      int FCFSTemp = 0;
      for (int i = 0; i < ProcessCount; i++) {
         FCFS[i] = new Canvas();
         FCFS[i].setBackground(FCFSGantt.get(i).ReturnColor());
         FCFS[i].setBounds(10 + FCFSTemp, 100, FCFSGantt.get(i).ReturnRunningTime() * STANDARDPX, STANDARDPX);
         FCFSTemp += (FCFSGantt.get(i).ReturnRunningTime() * STANDARDPX);
         getContentPane().add(FCFS[i]);
      }
      
      int RoundRobinTemp = 0;
      //getContentPane().repaint();
      for(int i=0;i<RoundRobinGantt.size();i++) {
         //System.out.println(RoundRobinGantt.get(i).ReturnRunningTime());
         RoundRobin[i] = new Canvas();
         RoundRobin[i].setBackground(RoundRobinGantt.get(i).ReturnColor());
         RoundRobin[i].setBounds(10+RoundRobinTemp, 250, RoundRobinGantt.get(i).ReturnRunningTime()*STANDARDPX, STANDARDPX);
         RoundRobinTemp += (RoundRobinGantt.get(i).ReturnRunningTime()*STANDARDPX);
         getContentPane().add(RoundRobin[i]);
      }
      
      int SJFTemp = 0;
   }
}