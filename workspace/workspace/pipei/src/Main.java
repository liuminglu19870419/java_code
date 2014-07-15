import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.apache.poi.hssf.record.ExtSSTRecord;

public class Main extends JFrame {
	private JTextField textField;
	private JButton button1;
	private JButton button2;
	private JButton button3;
	private JFileChooser fDialog;
	private JFrame frame;
	private String file1;
	private String file1pathString;
	private String file2;
	private String file2pathString;

	public Main() {
		button1 = new JButton();
		button1.setText("null");
		button1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				fDialog = new JFileChooser(); // 文件选择器
				int result = fDialog.showOpenDialog(frame);
				if (result == JFileChooser.APPROVE_OPTION) {
					file1 = fDialog.getName(fDialog.getSelectedFile());
					file1pathString = fDialog.getSelectedFile().getPath();
					button1.setText(file1);
				} else {
				}
			}
		});
		button2 = new JButton();
		button2.setText("null");
		button2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				fDialog = new JFileChooser(); // 文件选择器
				int result = fDialog.showOpenDialog(frame);
				if (result == JFileChooser.APPROVE_OPTION) {
					file2 = fDialog.getName(fDialog.getSelectedFile());
					file2pathString = fDialog.getSelectedFile().getPath();
					button2.setText(file2);
				} else {
				}
			}
		});
		button3 = new JButton("save");
		button3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
					Mainfun mainfun = new Mainfun();
					List<String[]> result1 = mainfun.getData(file1pathString, 0);
					List<String[]> result2 = mainfun.getData(file2pathString, 0);
					System.out.println("read finish");
					List<String[]> result = mainfun.match(result1, result2);
					mainfun.writeData("temp.xls", result);
					System.out.println("write finish");
					System.exit(0);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}

			}
		});
		frame = new JFrame("matcher");
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		FlowLayout flowLayout = new FlowLayout();
		frame.setLayout(flowLayout);
		frame.setSize(150, 150);
		frame.add(button1);
		frame.add(button2);
		frame.add(button3);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		new Main();
	}
}