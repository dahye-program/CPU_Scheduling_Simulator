package schedule_algorithm;

import java.awt.Container;

import javax.swing.*;

public class Input extends JFrame{
	private JTextField[] TextInputPID;
	private JTextField[] TextInputArrivalTime;
	private JTextField[] TextInputRunningTime;
	
	private int ProcessCount, TimeSlice;
	
	public Input(int ProcessCount, int TimeSlice) {
		this.ProcessCount = ProcessCount;
		this.TimeSlice = TimeSlice;
		
		setTitle("Input");
		setSize(420,500);
		setVisible(true);
		
		Container c = getContentPane(); // 자유로운 layout
		c.setLayout(null);				// 자유로운 layout
		
		JLabel LabelPID = new JLabel("PID 입력");
		JLabel LabelArrivalTime = new JLabel("도착시간 입력");
		JLabel LabelRunningTime = new JLabel("실행시간 입력");
		
		JButton ButtonInputComplete = new JButton("입력 완료");
		
		TextInputPID = new JTextField[this.ProcessCount];
		//TextInputArrivalTime = new JTextField[this.ProcessCount];
		//TextInputRunningTime = new JTextField[this.ProcessCount];
		
		
		for(int i=0;i<this.ProcessCount;i++) {
			TextInputPID[i].setBounds(10, 20+10*i, 60, 60);
			getContentPane().add(TextInputPID[i]);
			TextInputPID[i].setColumns(10);
			
		}
		
		
		LabelPID.setBounds(10, 10, 60, 15);				//왼쪽 시작 px, 위에 시작 px, 길이, 높이
		LabelArrivalTime.setBounds(90, 10, 100, 15);
		LabelRunningTime.setBounds(200, 10, 100, 15);
		
		ButtonInputComplete.setBounds(300, 10, 100, 15);
		
		
		getContentPane().add(LabelPID);
		getContentPane().add(LabelArrivalTime);
		getContentPane().add(LabelRunningTime);
				
		getContentPane().add(ButtonInputComplete);
		//Hello
		//gfyhgfy
	}
}