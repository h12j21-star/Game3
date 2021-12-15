import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.JTextComponent;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import java.net.*;
import java.io.*;

//게임방 start버튼, text area두개 채팅 하나 보내기 버튼
public class test_mainroom extends JFrame implements ActionListener{
	//채팅 내용을 array list로 입력  
	static JFrame f = new JFrame();

	static JTextArea player1 = new JTextArea(7,20); //player list글자
	static JTextArea player2 = new JTextArea(7,20); //player list글자
	static JTextArea chatting = new JTextArea(7,20); // 채팅이 보여지는 부분
	
	static JTextField tf = new JTextField(20);
	static JTextArea tf_name = new JTextArea(7,20);
	static JTextArea r_name = new JTextArea("   <  room name  >",7,20);
	Button Start = new Button("Start"); //사진 설정	
	static Button exit = new Button("Exit Program"); // 나가기 버튼 누르면 로그인창으로 이동
	static Button send = new Button("send"); // 채팅을 보내는 버튼
	static Button setname = new Button("SET ROOM NAME"); // 채팅을 보내는 버튼
	static Button change_name = new Button("change room name"); // 채팅을 보내는 버튼
	static Button change = new Button("Change"); // 채팅을 보내는 버튼
	static String message;
	static Font f1 = new Font("Aharoni 굵게",Font.BOLD,13);
	JScrollPane scrollPane = new JScrollPane(chatting); // 채팅의 스크린
	
	static ImageIcon icon1 = new ImageIcon("image/images2.png"); //사용자 사진
	static ImageIcon icon2 = new ImageIcon("image/images2.png"); //사용자 사진
	static JButton img1 = new JButton(icon1); //player list글자
	static JButton img2 = new JButton(icon2); // 채팅이 보여지는 부분
	static Vector<String> game_user = new Vector<String>();
	static String chatting_msg = null;
	static String otherUser = null;
	static String mynickname = null;
	static String roomId = null;
	static String roomName= null;
	String c_name = null;
	
	//대기방 gui
	public test_mainroom() throws UnknownHostException, IOException {	
		//게임방 이름
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setTitle(c_name); 
		//play 버튼 위치와 폰트
		Start.setBounds(245,430,250,60);
		Start.setFont(f1);
		scrollPane.setBounds(229,100,280,300);
		//chatting.setBounds(520,100,200,400);
		player1.setBounds(520,100,200,400);
		player2.setBounds(15,100,200,400);
		tf.setBounds(229,400,280,25); 
		exit.setBounds(520,40,200,50);
		change_name.setBounds(20,40,200,50);
		send.setBounds(460,400,50,23);
		 r_name.setBounds(245,50,250,30);
		 img1.setBounds(41,40,120,120);
		 img2.setBounds(41,40,120,120);	 
		//버튼 배경색
		Start.setBackground(new Color(234,234,234));
		send.setBackground(new Color(234,234,234));
		exit.setBackground(new Color(234,234,234));
		
		//버튼 누르면 나오는 결과	
		send.addActionListener(this);
		Start.addActionListener(this);
		exit.addActionListener(this);	
		change_name.addActionListener(this);	
		change.addActionListener(this);
		img1.addActionListener(this);	
		img2.addActionListener(this);
		
		f.pack();	
	    f.setVisible ( true ); 
		f.setSize(750,550); 
		f.setLayout(null); 
		f.add(exit);
		f.add(send);
		f.add(r_name);
		f.add(change_name);
		chatting.setEditable ( false ); 
		send.setFont(f1);		
	    f.add(send);
		player1.setEditable(false); 	
		player2.setEditable(false); 
		f.add(scrollPane); //채팅창에 스크롤 추가
		scrollPane.setVisible(true);
		f.add(player1);
		f.add(player2);
		f.add(Start);
		f.add(tf);
		player1.add(img1);
		player2.add(img2);
		info1_gui();
		info2_gui();
		
		f.setResizable(false);//창의 크기를 변경하지 못하게
		f.setLocationRelativeTo(null);//창이 가운데 나오게	
	}
	//버튼 동작
	public void actionPerformed(ActionEvent e){	
		if(e.getSource()==Start) {
			new Game2();
		}
		//버튼을 누르면 현재 방 이름을 알 수있다. 
		else if(e.getSource()== setname) {
			try {
				test_send.getRoomname();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		//이름변경하는 
		else if(e.getSource()==exit) {
		 	try {
		 	test_send.passWaitingRoom();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		else if(e.getSource()== change_name) {
		
			JFrame c = new JFrame();
			tf_name.setBounds(50,40,90,40);
			change.setBounds(70,100,50,30);
			c.pack();c.add(change);c.setLayout(null);
			c.setVisible(true);c.setSize(200,200);
			c.add(tf_name);c.setResizable(false);
			c.setLocationRelativeTo(null);
		}
		//채팅(메인, 2인방 공통): 200 (현재 방 ID) (보낼 데이터)
		else if(e.getSource()== send) {
			 chatting_msg  = tf.getText();
			 try {
				test_send.game_chatting();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		else if(e.getSource()== img1) {
			
			try {
				test_send.gameroomUserInfo();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		else if(e.getSource()== img2) {
			
			try {
				test_send.gameroomUserInfo();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		else if(e.getSource() == change) {
			try {
				test_send.change_r_name(); // 이름 변경 내용
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			c_name =tf_name.getText();
			r_name.append(getR_name()); //서버로부터 받은 이름을 게임방에 나타낸다.
		}
	}
public void info1_gui() {
	JFrame P = new JFrame();
	JLabel N = new JLabel("NickName");
	JTextArea tf_N = new JTextArea(info[0]);
	JLabel T = new JLabel("Total Game");
	JTextArea tf_T = new JTextArea(info[1]);
	JLabel W = new JLabel("win"); 
	JTextArea tf_W = new JTextArea(info[2]);
	JLabel l = new JLabel("lose"); 
	JTextArea tf_l = new JTextArea(info[3]);
		
	N.setBounds(10,170,80,20);tf_N.setBounds(10,190,80,20);
	T.setBounds(10,210,80,20);tf_T.setBounds(10,230,80,20);
	W.setBounds(10,250,80,20);tf_W.setBounds(10,270,80,20);
	l.setBounds(10,290,80,20);tf_l.setBounds(10,310,80,20);
	
	player1.setLayout(null);
	player1.add(N);player1.add(tf_N);player1.add(T);player1.add(tf_T);player1.add(W);
	player1.add(tf_W);player1.add(l);player1.add(tf_l);//P.add(exit2);
	
	
}
public void info2_gui() {
	JFrame P2 = new JFrame();
	JLabel N2 = new JLabel("NickName");
	JTextArea tf_N2 = new JTextArea(info[0]);
	JLabel T2 = new JLabel("Total Game");
	JTextArea tf_T2 = new JTextArea(info[1]);
	JLabel W2 = new JLabel("win"); 
	JTextArea tf_W2 = new JTextArea(info[2]);
	JLabel l2 = new JLabel("lose"); 
	JTextArea tf_l2 = new JTextArea(info[3]);
		
	N2.setBounds(10,170,80,20);tf_N2.setBounds(10,190,80,20);
	T2.setBounds(10,210,80,20);tf_T2.setBounds(10,230,80,20);
	W2.setBounds(10,250,80,20);tf_W2.setBounds(10,270,80,20);
	l2.setBounds(10,290,80,20);tf_l2.setBounds(10,310,80,20);

	player2.setLayout(null);
	player2.add(N2);player2.add(tf_N2);player2.add(T2);player2.add(tf_T2);player2.add(W2);
	player2.add(tf_W2);player2.add(l2);player2.add(tf_l2);//P.add(exit2);
	
}
public static void roomname(String r_name) {
	 roomName = r_name;
}
public String getR_name() {
	return roomName;
}
//게임방에서의 채팅 
public static void send_chatting(String nickname,String msg) {
	chatting.append(nickname +" : " + msg);
}

static String[] info = new String[4];
public static void in_gameRoom(String nickname,String t_win,String t_lose,String t_tot) {
	info[0] = nickname;
	info[1] =  t_win;	
	info[2] =  t_lose;
	info[3] =  t_tot;	
}

static String room_id;

// 방번호 저장
public static void roomId(String id) {
	roomId = id;
}
public String getroomID() {
	return roomId;
}
}

