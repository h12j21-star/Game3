

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;

public class test_join extends JFrame implements ActionListener{
	public static String name;
	public static String id;
	public static String pw;
	public static String Pw2;
	public static String email;
	public static String nickname;
	static JTextField tf1 = new JTextField(); // name
	static JTextField tf2 = new JTextField(); //id
	static JTextField tf3 = new JTextField(); // nickname
	static JTextField tf4 = new JTextField(); //email
	static JPasswordField pw1 = new JPasswordField();//pw
	static JPasswordField pw2 = new JPasswordField();//pw
	JLabel J1 = new JLabel();
	JLabel J2 = new JLabel("NAME ");
	JLabel J3 = new JLabel("ID ");
	JLabel J4 = new JLabel("PW ");
	JLabel J7 = new JLabel("check PW ");
	JLabel J5 = new JLabel("Nickname ");
	JLabel J6 = new JLabel("E-mail ");
	//버튼
	static JButton create = new JButton("Create");
	static JButton check_id = new JButton("Check");//id 중복
	static JButton check_pw = new JButton("Check");//닉네임 중복
	Font f1;
	
	static OutputStream out =null; 
	DataInputStream in = null; 
	
	
	public test_join() {
		f1 = new Font("Aharoni 굵게",Font.BOLD,10);
		
		
		J1.setBounds(20,200,300,50);
		J1.setFont(f1);
		
		//이름
		J2.setBounds(210,50,80,30);
		J2.setFont(f1);
		
		//이름 입력창
		tf1.setBounds(210,80,300,30);
		tf1.setFont(f1);
		
		//id
		J3.setBounds(210,110,80,30);
		J3.setFont(f1);
		
		//id 입력창
		tf2.setBounds(210,140,300,30);
		tf2.setFont(f1);
		
		//pw
		J4.setBounds(210,170,80,30);
		J4.setFont(f1);
		
		//pw입력창
		pw1.setBounds(210,200,300,30);
		pw1.setFont(f1);
		
		J7.setBounds(210,230,80,30);
		J7.setFont(f1);
		
		
		pw2.setBounds(210,260,300,30);
		pw2.setFont(f1);
		
		//nickname
		J5.setBounds(210,290,80,30);
		J5.setFont(f1);
		
		//nickname입력창
		tf3.setBounds(210,320,300,30);
		tf3.setFont(f1);	
		
		//email
		J6.setBounds(210,350,80,30);
		J6.setFont(f1);
		
		//email입력창
		tf4.setBounds(210,380,300,30);
		tf4.setFont(f1);
		
		//버튼
		create.setBounds(310,430,90,30);
		create.setFont(f1);
		
		check_id.setBounds(530,140,90,30);
		check_id.setFont(f1);
		
		check_pw.setBounds(530,260,90,30);
		check_pw.setFont(f1);
		
	
		//버튼 색상,action
		create.setBackground(new Color(234,234,234));
		check_id.setBackground(new Color(234,234,234));
		check_pw.setBackground(new Color(234,234,234));

		create.addActionListener(this);
		check_id.addActionListener(this);
		check_pw.addActionListener(this);
	
		
		
		add(J1);
		add(pw1);add(J2);add(tf1);
		add(tf2);add(tf3);add(J3);add(create);add(J4);add(J5);add(J6);add(tf4);add(check_id);
		setTitle("TIC-TAC-TOE join");setSize(750,550); add(J7);add(pw2);add(check_pw);
		setLayout(null); setVisible(true);
		setResizable(false);//창의 크기를 변경하지 못하게
		setLocationRelativeTo(null);//창이 가운데 나오게
		
	}	
	
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==create) {
			name = tf1.getText();
			id = tf2.getText();
			pw = pw1.getText();
			Pw2 = pw2.getText();
			email = tf3.getText();
			nickname = tf4.getText();
			try {
				test_send.send_join(); // 로그인 버튼 누르면 정보 전달
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			 tf1.selectAll();  tf1.setText(""); tf2.selectAll();  tf2.setText(""); tf3.selectAll();  tf3.setText(""); 
			 tf4.selectAll();  tf4.setText("");pw1.selectAll();pw1.setText("");pw2.selectAll();  pw2.setText(""); 
		}else if((e.getSource()==check_id) || (e.getSource()==check_pw)) {
			try {
				test_send.send_join();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	
	}

//test
public static String getid() {
	return id;
}
public static String getpw() {
	return pw;
}

public static String getpw2() {
	return Pw2;
}

public static String getemail() {
	return email;
}
public static String getenickname() {
	return nickname;
}
public static String getname() {
	return name;
}


}