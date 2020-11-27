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
		
		int TotalRunningTime = 0;
		
		for(int i=0;i<ProcessCount;i++) {
			TotalRunningTime += RunningTime[i];
		}
		
		AlgorithmKangmin kangmin = new AlgorithmKangmin(ProcessCount, TimeSlice, PID, ArrivalTime, RunningTime, Priority);
		FCFSGantt = kangmin.FCFS();
		//RoundRobinGhantt = kangmin.RoundRobin();
		//PreemptionGhantt = kangmin.Preemption();
		
				
		System.out.println(TotalRunningTime);
		
		// 여기서부터 GUI 영역
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("GhanttChart");
		setSize(600,1000);
		setVisible(true);
		
		
		JLabel LabelFCFS = new JLabel("FCFS간트차트");
		JLabel LabelRR = new JLabel("라운드로빈간트차트");
		JLabel LabelPP = new JLabel("선점간트차트");
		JLabel LabelSRT = new JLabel("SRT간트차트");
		JLabel LabelSJF = new JLabel("SJF간트차트");
		JLabel LabelHRN = new JLabel("HRN간트차트");
		JLabel LabelNPP = new JLabel("비선점간트차트");
		
		
		LabelFCFS.setBounds(10, 80, 100, 20);
		
		
		getContentPane().add(LabelFCFS);
		
		
		
		
		
		Container c = getContentPane();
		c.setLayout(null);
		
//		Canvas canvas = new Canvas();
//		canvas.setBackground(Color.RED);
//		canvas.setBounds(10, 100, STANDARDPX*TotalRunningTime, STANDARDPX);
//		canvas.setName("test");
//		getContentPane().add(canvas);
		
				
		Canvas[] FCFS = new Canvas[ProcessCount];
		
		int FCFSTemp = 0;
		
		for (int i = 0; i < ProcessCount; i++) {
			FCFS[i] = new Canvas();
			FCFS[i].setBackground(FCFSGantt.get(i).ReturnColor());
			FCFS[i].setBounds(10 + FCFSTemp, 100, FCFSGantt.get(i).ReturnRunningTime() * STANDARDPX, STANDARDPX);
			FCFSTemp += (FCFSGantt.get(i).ReturnRunningTime() * STANDARDPX);
			getContentPane().add(FCFS[i]);
		}
		
		
	}
}
