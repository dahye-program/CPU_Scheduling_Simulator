package schedule_algorithm;

import java.awt.Container;

import javax.swing.*;

public class Input extends JFrame{
	
	
	
	public Input(int ProcessCount, int TimeSlice) {
		
		setTitle("Input");
		setSize(450,500);
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
		
		JTextField[] textField = new JTextField[1];
		textField[1].setBounds(10, 51, 116, 21);
		getContentPane().add(textField[1]);
		textField[1].setColumns(10);
				
	}
}