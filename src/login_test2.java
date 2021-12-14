

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
public class login_test2 extends JFrame implements ActionListener{
	
	public static String id; //�Է¹��� id pw�� ����
	public  static String pw;
	static JTextField tf1 = new JTextField(); // id �Է�â
	JLabel L1 = new JLabel("<LOGIN>"); 
	JLabel L2 = new JLabel("ID : "); 
	JLabel L3 = new JLabel("PW : "); 
	
	static JPasswordField pw1 = new JPasswordField(); //pw�Է�â
	static Button login = new Button("LOGIN"); // login��ư
	static Button join = new Button("JOIN"); //ȸ������ ��ư
	Socket sock = null;
	Font f1,f2,f3;
	Background panel = new Background(); //��� ���� panel

	public login_test2() throws UnknownHostException, IOException {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(panel);	
		//�۾�ü 
		f1 = new Font("Aharoni ����",Font.PLAIN,10);
		f2 = new Font("Serif",Font.BOLD,35);
		f3 = new Font("Aharoni ����",Font.BOLD,10);
		
		//<LOGIN>�۾� ��ġ
		L1.setBounds(45,130,300,50);
		L1.setFont(f2);
		
		//ID
		L2.setBounds(20,200,80,30);
		L2.setFont(f1);
		
		//ID�Է�â ��ġ
		tf1.setBounds(80,200,100,30);
		tf1.setFont(f1);
		
		//PW ��ġ
		L3.setBounds(20,250,80,30);
		L3.setFont(f1);
		
		//PW�Է�â ��ġ
		pw1.setBounds(80,250,100,30);
		pw1.setFont(f1);
		
		//login��ư ��ġ
		login.setBounds(20,300,90,30);
		login.setFont(f3);
		
		//Join��ư ��ġ
		join.setBounds(140,300,90,30);
		join.setFont(f3);
		
		//��ư �����  action
		login.setBackground(new Color(234,234,234));
		join.setBackground(new Color(234,234,234));
		//test
		// �ٸ� Ŭ�������� ����ϱ� ����
		
		join.addActionListener(this);
		login.addActionListener(this);
		
		//ȭ�鿡 �߰�
		add(L1);add(pw1);add(L2);
		add(tf1);add(L3);add(login);add(join);
		setTitle("TIC-TAC-TOE login");setSize(750,550);
		
		setLayout(null); setVisible(true);
		setResizable(false);//â�� ũ�⸦ �������� ���ϰ�
		setLocationRelativeTo(null);//â�� ��� ������	
		
	}	
	//test
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==join) {
			new test_join();
		}else if(e.getSource()==login) {
			id = tf1.getText();
			pw = pw1.getText();
			
			try {
				test_send.send_login(); // �α��� ��ư ������ ���� ����
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	public static String getid() {
		return id;
	}
	public static String getpw() {
		return pw;
	}
	
}