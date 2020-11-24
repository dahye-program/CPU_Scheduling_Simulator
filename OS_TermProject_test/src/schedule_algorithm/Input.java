package schedule_algorithm;

import java.awt.Container;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Input extends JFrame{
	
	
	
	public Input(int ProcessCount, int TimeSlice) {
		
		setTitle("Input");
		setSize(450,500);
		setVisible(true);
		
		Container c = getContentPane(); // 자유로운 layout
		c.setLayout(null);				// 자유로운 layout
		
		
		JLabel LabelPID = new JLabel("프로세스아이디");
		JLabel LabelArrivalTime = new JLabel("도착시간");
		JLabel LabelRunningTime = new JLabel("실행시간");
		JLabel LabelPriority = new JLabel("우선순위");
		
		JButton InputComplete = new JButton("입력완료");
		
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
		
		int a[] = new int[5];
		
		String PID[] = new String[ProcessCount];
		int ArrivalTime[] = new int[ProcessCount];
		int RunningTime[] = new int[ProcessCount];
		int Priority[] = new int[ProcessCount];
		
		
		
		InputComplete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(int i=0;i<ProcessCount;i++) {
					PID[i] = TextPID[i].getText();
					ArrivalTime[i] = Integer.parseInt(TextArrivalTime[i].getText());
					RunningTime[i] = Integer.parseInt(TextRunningTime[i].getText());
					Priority[i] = Integer.parseInt(TextPriority[i].getText());
				}
				new Ghantt(ProcessCount, TimeSlice, PID, ArrivalTime, RunningTime, Priority);				
			}
		});
	}
}