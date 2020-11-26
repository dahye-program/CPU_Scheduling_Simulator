package schedule_algorithm;

import java.awt.Container;
import java.util.Vector;

import javax.swing.*;
import java.awt.Canvas;
import java.awt.Color;

public class DrawGhanttChart extends JFrame{
	final int STANDARDPX = 30;
	public DrawGhanttChart(int ProcessCount, int TimeSlice, String[] PID, int[] ArrivalTime, int[] RunningTime, int[] Priority) {
		
		
		Vector<ArgumentVector> FCFSGhantt = new Vector<ArgumentVector>();
		Vector<ArgumentVector> RoundRobinGhantt = new Vector<ArgumentVector>();
		Vector<ArgumentVector> PreemptionGhantt = new Vector<ArgumentVector>();
		
		int TotalRunningTime = 0;
		
		for(int i=0;i<ProcessCount;i++) {
			TotalRunningTime += RunningTime[i];
		}
		
		AlgorithmKangmin kangmin = new AlgorithmKangmin(ProcessCount, TimeSlice, PID, ArrivalTime, RunningTime, Priority);
		FCFSGhantt = kangmin.FCFS();
		//RoundRobinGhantt = kangmin.RoundRobin();
		//PreemptionGhantt = kangmin.Preemption();
		
				
		System.out.println(TotalRunningTime);
		
		// 여기서부터 GUI 영역
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("GhanttChart");
		setSize(600,600);
		setVisible(true);
		
		Container c = getContentPane();
		c.setLayout(null);
		
//		Canvas canvas = new Canvas();
//		canvas.setBackground(Color.RED);
//		canvas.setBounds(10, 100, StandardPX*TotalRunningTime, StandardPX);
//		canvas.setName("test");
//		getContentPane().add(canvas);
		
				
		Canvas[] FCFS = new Canvas[ProcessCount];
		
		int FCFSTemp = 0;
		
		for(int i=0;i<ProcessCount;i++) {
			FCFS[i].setBackground(Color.RED);
			FCFS[i].setBounds(10+FCFSTemp, 100, FCFSGhantt.get(0).ReturnRunningTime()*STANDARDPX, 10);
			FCFSTemp+=FCFSGhantt.get(0).ReturnRunningTime()*STANDARDPX;
			getContentPane().add(FCFS[i]);
		}
		
		
	}
}
