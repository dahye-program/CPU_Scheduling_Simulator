package schedule_algorithm;

import java.awt.Container;

import javax.swing.*;

public class Input extends JFrame{
	public Input(int ProcessCount, int TimeSlice) {
		setTitle("Input");
		setSize(400,500);
		setVisible(true);
		
		Container c = getContentPane(); // �����ο� layout
		c.setLayout(null);				// �����ο� layout
		
		JLabel LabelPID = new JLabel("PID �Է�");
		
		
		LabelPID.setBounds(10, 10, 60, 15);
		
		
		getContentPane().add(LabelPID);
		
		
	}
}