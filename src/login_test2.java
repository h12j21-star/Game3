

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
	
	public static String id; //입력받은 id pw를 저장
	public  static String pw;
	static JTextField tf1 = new JTextField(); // id 입력창
	JLabel L1 = new JLabel("<LOGIN>"); 
	JLabel L2 = new JLabel("ID : "); 
	JLabel L3 = new JLabel("PW : "); 
	
	static JPasswordField pw1 = new JPasswordField(); //pw입력창
	static Button login = new Button("LOGIN"); // login버튼
	static Button join = new Button("JOIN"); //회원가입 버튼
	Socket sock = null;
	Font f1,f2,f3;
	Background panel = new Background(); //배경 설정 panel

	public login_test2() throws UnknownHostException, IOException {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(panel);	
		//글씨체 
		f1 = new Font("Aharoni 굵게",Font.PLAIN,10);
		f2 = new Font("Serif",Font.BOLD,35);
		f3 = new Font("Aharoni 굵게",Font.BOLD,10);
		
		//<LOGIN>글씨 위치
		L1.setBounds(45,130,300,50);
		L1.setFont(f2);
		
		//ID
		L2.setBounds(20,200,80,30);
		L2.setFont(f1);
		
		//ID입력창 위치
		tf1.setBounds(80,200,100,30);
		tf1.setFont(f1);
		
		//PW 위치
		L3.setBounds(20,250,80,30);
		L3.setFont(f1);
		
		//PW입력창 위치
		pw1.setBounds(80,250,100,30);
		pw1.setFont(f1);
		
		//login버튼 위치
		login.setBounds(20,300,90,30);
		login.setFont(f3);
		
		//Join버튼 위치
		join.setBounds(140,300,90,30);
		join.setFont(f3);
		
		//번튼 색깔과  action
		login.setBackground(new Color(234,234,234));
		join.setBackground(new Color(234,234,234));
		//test
		// 다른 클래스에서 사용하기 위해
		
		join.addActionListener(this);
		login.addActionListener(this);
		
		//화면에 추가
		add(L1);add(pw1);add(L2);
		add(tf1);add(L3);add(login);add(join);
		setTitle("TIC-TAC-TOE login");setSize(750,550);
		
		setLayout(null); setVisible(true);
		setResizable(false);//창의 크기를 변경하지 못하게
		setLocationRelativeTo(null);//창이 가운데 나오게	
		
	}	
	//test
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==join) {
			new test_join();
		}else if(e.getSource()==login) {
			id = tf1.getText();
			pw = pw1.getText();
			
			try {
				test_send.send_login(); // 로그인 버튼 누르면 정보 전달
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