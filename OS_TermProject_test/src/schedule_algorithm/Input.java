package schedule_algorithm;

import java.awt.Color;
import java.awt.Container;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.util.Random;

public class Input extends JFrame{
	
	
	
	public Input(int ProcessCount, int TimeSlice) {
		
		setTitle("Input");
		setSize(450,70+20*ProcessCount);
		setVisible(true);
		
		Container c = getContentPane(); // �����ο� layout
		c.setLayout(null);				// �����ο� layout
		
		
		JLabel LabelPID = new JLabel("���μ������̵�");
		JLabel LabelArrivalTime = new JLabel("�����ð�");
		JLabel LabelRunningTime = new JLabel("����ð�");
		JLabel LabelPriority = new JLabel("�켱����");
		
		JButton InputComplete = new JButton("�Է¿Ϸ�");
		
		LabelPID.setBounds(10, 10, 100, 20);
		LabelArrivalTime.setBounds(120, 10, 60, 20);
		LabelRunningTime.setBounds(190, 10, 60, 20);
		LabelPriority.setBounds(260, 10, 60, 20);
		
		InputComplete.setBounds(340, 10, 90, 20);
		
		
		getContentPane().add(LabelPID);	
		getContentPane().add(LabelArrivalTime);
		getContentPane().add(LabelRunningTime);
		getContentPane().add(LabelPriority);
		
		getContentPane().add(InputComplete);
		
		
		JTextField[] TextPID = new JTextField[ProcessCount];
		JTextField[] TextArrivalTime = new JTextField[ProcessCount];
		JTextField[] TextRunningTime = new JTextField[ProcessCount];
		JTextField[] TextPriority = new JTextField[ProcessCount];
		
		for(int i=0;i<ProcessCount;i++) {
			TextPID[i] = new JTextField();
			TextArrivalTime[i] = new JTextField();
			TextRunningTime[i] = new JTextField();
			TextPriority[i] = new JTextField();
			
			TextPID[i].setBounds(10, 30+i*20, 100, 20);
			TextArrivalTime[i].setBounds(120, 30+i*20, 60, 20);
			TextRunningTime[i].setBounds(190, 30+i*20, 60, 20);
			TextPriority[i].setBounds(260, 30+i*20, 60, 20);
			
			getContentPane().add(TextPID[i]);
			getContentPane().add(TextArrivalTime[i]);
			getContentPane().add(TextRunningTime[i]);
			getContentPane().add(TextPriority[i]);
			
			TextPID[i].setColumns(10);
			TextArrivalTime[i].setColumns(10);
			TextRunningTime[i].setColumns(10);
			TextPriority[i].setColumns(10);
		}
		
		String PID[] = new String[ProcessCount];
		int ArrivalTime[] = new int[ProcessCount];
		int RunningTime[] = new int[ProcessCount];
		int Priority[] = new int[ProcessCount];
		Color color[] = new Color[ProcessCount];
		
		Random rand = new Random();
		
		
		InputComplete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(int i=0;i<ProcessCount;i++) {
					PID[i] = TextPID[i].getText();
					ArrivalTime[i] = Integer.parseInt(TextArrivalTime[i].getText());
					RunningTime[i] = Integer.parseInt(TextRunningTime[i].getText());
					Priority[i] = Integer.parseInt(TextPriority[i].getText());
					float r = rand.nextFloat() / 2f + (float)0.5;
					float g = rand.nextFloat() / 2f + (float)0.5;
					float b = rand.nextFloat() / 2f + (float)0.5;
					color[i] = new Color(r,g,b);
				}
				dispose();
				new DrawGanttChart(ProcessCount, TimeSlice, PID, ArrivalTime, RunningTime, Priority, color);
				//new AlgorithmKangmin(ProcessCount, TimeSlice, PID, ArrivalTime, RunningTime, Priority);
			}
		});
	}
}