package August;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.LayoutManager;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Random;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.DropMode;
import javax.swing.JTextArea;

public class MainFrame extends JFrame {
	public static Image im = Lottery.makeColorTransparent(new ImageIcon("./l.jpg").getImage(), Color.white);
	private JButton b_start;
	private JTextField textField1;
	private JTextField textField2;
	private JTextField textField3;
	private ImagePanel ip;
	public static boolean isLottery = false;
	Lottery l = new Lottery("./Num.txt");

	public MainFrame() {
		Init();
	}

	private void Init() {
		this.setTitle("计算机工程学院抽奖系统");
		this.setSize(553, 370);
		this.setBounds(400, 300, 450, 370);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		JPanel jp1=new JPanel(new GridLayout(2,1));
		JPanel jp2=new JPanel(new GridLayout(1,4));
		jp1.setBounds(0, 0, 400, this.getHeight()-30);
		setLayout(new GridLayout(1,3));
		b_start = new JButton("开始");
		b_start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (((JButton) e.getSource()).getText().equals("开始")) {
					isLottery = true;
					startLottery();

					b_start.setText("结束");
				} else if (((JButton) e.getSource()).getText().equals("结束")) {
					isLottery = false;
					b_start.setEnabled(false);
				}
			}
		});

		textField1 = new JTextField();
		textField2 = new JTextField();
		textField3 = new JTextField();
		textField1.setFont(new Font("微软雅黑", Font.BOLD, 150));
		textField2.setFont(new Font("微软雅黑", Font.BOLD, 150));
		textField3.setFont(new Font("微软雅黑", Font.BOLD, 150));
		textField1.setEditable(false);
		textField2.setEditable(false);
		textField3.setEditable(false);
		ip=new ImagePanel();
		jp2.add(textField1);
		jp2.add(textField2);
		jp2.add(textField3);
		jp2.add(b_start);
		jp1.add(ip);
		jp1.add(jp2);
		//add(jp3);
		getContentPane().add(jp1);
		//add(jp4);
		textField1.setBackground(Color.red);
		textField2.setBackground(Color.red);
		textField3.setBackground(Color.red);
		
		setResizable(false);
		setVisible(true);
		setBackground(Color.red);
		Lottery.saveData("./Num.txt");
		System.out.println(textField1.getWidth()+" "+textField1.getHeight());
	}

	private void startLottery() {
		new Thread(new Runnable() {
			int sleep = 100;

			@Override
			public void run() {
				try {
					while (isLottery == true) {
						textField1
								.setText(String.valueOf(Math.abs(new Random().nextInt() % (l.Amount / 100 % 10 + 1))));
						textField2.setText(String.valueOf(Math.abs(new Random().nextInt() % 10)));
						textField3.setText(String.valueOf(Math.abs(new Random().nextInt() % 10)));
						Thread.sleep(sleep);
					}
					int a = 0;
					sleep = 100;
					while (isLottery == false) {
						if (a < 30) {
							textField1.setText(
									String.valueOf(Math.abs(new Random().nextInt() % (l.Amount / 100 % 10 + 1))));
						}
						if (a < 60) {
							if (a >= 30 && Integer.valueOf(textField1.getText()) == (l.Amount / 100 % 10)) {
								textField2.setText(
										String.valueOf(Math.abs(new Random().nextInt() % (l.Amount / 10 % 10 + 1))));
							} else {
								textField2.setText(String.valueOf(Math.abs(new Random().nextInt() % 10)));
							}

						}
						if (a < 90) {
							if (a >= 60 && Integer.valueOf(textField1.getText()) == (l.Amount / 100 % 10)
									&& Integer.valueOf(textField2.getText()) == (l.Amount / 10 % 10)) {
								textField3.setText(
										String.valueOf(Math.abs(new Random().nextInt() % (l.Amount % 10 + 1))));
							} else {
								textField3.setText(String.valueOf(Math.abs(new Random().nextInt() % 10)));
							}
						}
						if (a > 90)
							break;
						a++;
						Thread.sleep(sleep);
					}
					b_start.setEnabled(true);
					b_start.setText("开始");
				} catch (Exception e) {
				}
			}
		}).start();
	}
}
