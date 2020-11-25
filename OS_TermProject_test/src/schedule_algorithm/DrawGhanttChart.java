package schedule_algorithm;

import java.awt.Container;

import javax.swing.*;
import java.awt.Canvas;
import java.awt.Color;

public class DrawGhanttChart extends JFrame{
	final int StandardPX = 30;
	public DrawGhanttChart(int ProcessCount, int TimeSlice, String[] PID, int[] ArrivalTime, int[] RunningTime) {
		
		int TotalRunningTime = 0;
		
		for(int i=0;i<ProcessCount;i++) {
			TotalRunningTime += RunningTime[i];
		}
		
		System.out.println(TotalRunningTime);
		
		// 여기서부터 GUI 영역
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("GhanttChart");
		setSize(600,600);
		setVisible(true);
		
		Container c = getContentPane();
		c.setLayout(null);
		
		Canvas canvas = new Canvas();
		canvas.setBackground(Color.RED);
		canvas.setBounds(10, 100, StandardPX*TotalRunningTime, StandardPX);
		canvas.setName("test");
		getContentPane().add(canvas);
		
		
	}
}
