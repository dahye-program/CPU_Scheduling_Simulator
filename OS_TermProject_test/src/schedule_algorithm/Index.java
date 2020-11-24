package schedule_algorithm;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import java.awt.FlowLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Index extends JFrame{
	
	private JTextField TextInputProcessCount;
	private JTextField TextInputTimeSlice;
	
	public Index() {
		setTitle("Index");
		setSize(320,300);
		setVisible(true);
		
		Container c = getContentPane(); // �����ο� layout
		c.setLayout(null);				// �����ο� layout
		
		
		// ���� �κ�
		
		JLabel LabelProcessCount = new JLabel("���μ��� ���� �Է� : "); 	// ���μ��� �Է� ��
		JLabel LabelTimeSlice = new JLabel("Ÿ�ӽ����̽� �Է�  : ");
		
		TextInputProcessCount = new JTextField();					// ���μ��� �Է� �ؽ�Ʈ�ʵ�
		TextInputTimeSlice = new JTextField();
		
		JButton ButtonInput = new JButton("�Է¿Ϸ�");
		
		ButtonInput.setFont(new Font("����", Font.BOLD, 20));
		
		
		// ��ġ ���� �κ�
		LabelProcessCount.setBounds(10, 100, 130, 30);
		LabelTimeSlice.setBounds(10, 150, 130, 30);
		
		TextInputProcessCount.setBounds(160, 100, 130, 30);
		TextInputTimeSlice.setBounds(160,150,130,30);
		
		
		//���� ���� �κ�
		getContentPane().add(LabelProcessCount);
		getContentPane().add(LabelTimeSlice);
		
		getContentPane().add(TextInputProcessCount);
		getContentPane().add(TextInputTimeSlice);
		
		getContentPane().add(ButtonInput);
		
		TextInputProcessCount.setColumns(10);
		TextInputTimeSlice.setColumns(10);
		
		
		ButtonInput.setBounds(10, 190, 280, 60);
		
		
		ButtonInput.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int ProcessCount = Integer.parseInt(TextInputProcessCount.getText());
				int TimeSlice = Integer.parseInt(TextInputTimeSlice.getText());
				dispose();
				new Input(ProcessCount, TimeSlice);
			}
		});
	}
	
	public static void main(String[] args) {
		Index index = new Index();
		// Hello
	}
}