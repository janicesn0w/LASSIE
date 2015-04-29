package Main;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

public class MyListener implements ActionListener {

	private int count = 0;
	
	private JLabel label;
	
	public MyListener(JLabel label){
		this.label=label;
	}
	
	public void actionPerformed(ActionEvent e) {
		
		count++;
		
		label.setText("Count: " + count);
	}

	
	
}
