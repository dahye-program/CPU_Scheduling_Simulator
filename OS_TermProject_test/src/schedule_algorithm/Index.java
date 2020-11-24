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
		
		Container c = getContentPane(); // 자유로운 layout
		c.setLayout(null);				// 자유로운 layout
		
		
		// 선언 부분
		
		JLabel LabelProcessCount = new JLabel("프로세스 개수 입력 : "); 	// 프로세스 입력 라벨
		JLabel LabelTimeSlice = new JLabel("타임슬라이스 입력  : ");
		
		TextInputProcessCount = new JTextField();					// 프로세스 입력 텍스트필드
		TextInputTimeSlice = new JTextField();
		
		JButton ButtonInput = new JButton("입력완료");
		
		ButtonInput.setFont(new Font("굴림", Font.BOLD, 20));
		
		
		// 위치 지정 부분
		LabelProcessCount.setBounds(10, 100, 130, 30);
		LabelTimeSlice.setBounds(10, 150, 130, 30);
		
		TextInputProcessCount.setBounds(160, 100, 130, 30);
		TextInputTimeSlice.setBounds(160,150,130,30);
		
		
		//실제 생성 부분
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