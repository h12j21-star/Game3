

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

//게임 대기방 - 채팅창, player list,버튼
public class test_waitingroom extends JFrame implements ActionListener{
	//채팅 내용을 array list로 입력  
	static JFrame f = new JFrame();
	static JTextField tf = new JTextField(20);
	static JTextArea player = new JTextArea("                    player list",7,20); //player list글자
	static JTextArea chatting = new JTextArea(7,20); // 채팅이 보여지는 부분
	JScrollPane scrollPane = new JScrollPane(chatting); // 채팅의 스크린
	
	static ImageIcon icon1 = new ImageIcon("image/game_rule.png"); //사용자 사진
	
	Button L1 = new Button("User update"); //사진 설정
	Button manual = new Button("Game manual"); // 버튼 누르면 이동
	static Button exit = new Button("Exit Program"); // 나가기 버튼 누르면 로그인창으로 이동
	static Button send = new Button("send"); // 채팅을 보내는 버튼
	//static JList<String> info = new JList<String>(); // player의 정보를 알수있는 버튼
	static JList<String> info; 
	static JScrollPane scrollPane2 = new JScrollPane(info); //player스크롤
	static String message;
	static Button invite = new Button("초대하기"); // 버튼 누르면 이동
	//static Button exit2` = new Button("취소"); // 나가기 버튼 누르면 로그인창으로 이동
	static Font f1 = new Font("Aharoni 굵게",Font.BOLD,13);
	Socket socket = null;
	
	static Vector<String> Main_user = new Vector<String>();
	static DefaultListModel<String> l_model;
	
	static String chatting_msg = null;
	static String invite_msg = null;
	static String otherUser = null;
	static String mynickname = null;
	static String roomId = null;
	static String otherInviteNick = null;
	
	static JFrame inviteMsg  = new JFrame(); //초대 받았을때 메세지 창
	static JButton ok = new JButton("수락"); // 초대 수락
	static JButton no = new JButton("거절"); //초대 거절

	//대기방 gui
	public test_waitingroom() throws UnknownHostException, IOException {	
		f.setTitle("waiting Room"); //창 이름
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//f.getContentPane().setBackground(Color.GRAY); //배경 설정
		
		//play 버튼 위치와 폰트
		manual.setBounds(90,50,200,35);
		manual.setFont(f1);
		
		//나가기 버튼 위치와 폰트
		exit.setBounds(300,50,130,35);
		exit.setFont(f1);
		
		send.setBounds(390,420,50,20);
		
		//버튼 배경색
		manual.setBackground(new Color(234,234,234));
		exit.setBackground(new Color(234,234,234));
		send.setBackground(new Color(234,234,234));
		
		ok.setBackground(new Color(234,234,234));
		no.setBackground(new Color(234,234,234));
		
		
		L1.setBounds(440,50,130,35);
		L1.setBackground(new Color(234,234,234));
		tf.setBounds(90,420,300,20); 
		
		//버튼 누르면 나오는 결과
		invite.addActionListener(this);	
		send.addActionListener(this);
		L1.addActionListener(this);	
		//tf.addActionListener(this);
		manual.addActionListener(this);
		exit.addActionListener(this);	
		
		//this.chatting();
		//test
		f.pack();
	    f.setVisible ( true ); // frame을 보이게함
		f.setSize(750,550); //창 크기 설정
		f.setLayout(null); //위치를 setbounds로 설정
		f.add(manual);
		f.add(exit);
		chatting.setEditable ( false ); //채팅창 편집 불가능
		//receiveMsg();
		//채팅입력창과 채팅창 위치 설정
		
		scrollPane.setBounds(90,100,350,320);
		//player list_test
		l_model = new DefaultListModel<String>();
		info = new JList<String>(l_model);
		
		//메세지 전송 버튼
		//test_waitingroom.player(); // 사용자 목록
		//list에서 player를 보여준다.	
		info.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent()==info){
					if(arg0.getClickCount()==2){
						//info의 값 가져오기
						int select = 0;
						//player를 누르면 작동 
						for(int i=0;i<n_name.size();i++) {
							if((info.getSelectedValue()).equals(l_model.get(i))){
								select = i;
							}
						}
						otherUser = info.getSelectedValue();
						JFrame P = new JFrame();
						JLabel N = new JLabel("NickName");
						JTextArea tf_N = new JTextArea(n_name.get(select));
						JLabel T = new JLabel("Total Game");
						JTextArea tf_T = new JTextArea(T_win.get(select));
						JLabel W = new JLabel("win"); 
						JTextArea tf_W = new JTextArea(T_lose.get(select));
						JLabel l = new JLabel("lose"); 
						JTextArea tf_l = new JTextArea(T_tot.get(select));
							
						N.setBounds(110,30,100,20);tf_N.setBounds(100,60,90,20);
						T.setBounds(110,85,100,20);tf_T.setBounds(100,110,90,20);
						W.setBounds(130,135,100,20);tf_W.setBounds(100,160,90,20);
						l.setBounds(130,185,100,20);tf_l.setBounds(100,210,90,20);
						invite.setBounds(107,260,70,30);
						//exit2.setBounds(170,260,70,30);
						invite.setBackground(new Color(234,234,234));invite.setFont(f1);
						//exit2.setBackground(new Color(234,234,234));exit2.setFont(f1);
						P.setTitle("user information");
						P.setSize(300,350);
						P.setVisible(true);
						P.setLayout(null);
						P.add(N);P.add(tf_N);P.add(T);P.add(tf_T);P.add(W);
						P.add(tf_W);P.add(l);P.add(tf_l);//P.add(exit2);
						P.add(invite);
						P.setResizable(false);//창의 크기를 변경하지 못하게
						P.setLocationRelativeTo(null);//창이 가운데 나오게
						
							}		
						}
				}
		});	
		send.setFont(f1);		
	    f.add(send);
		f.add(scrollPane); //채팅창에 스크롤 추가
		scrollPane.setVisible(true);
		f.add(tf);f.add(L1);L1.setFont(f1);
		f.setResizable(false);//창의 크기를 변경하지 못하게
		f.setLocationRelativeTo(null);//창이 가운데 나오게
		
		//test
		info.setBounds(440,120,200,320);
		player.setEditable(false); 	//편집불가
		
		//player을 보여주는 창 위치설정
		player.setBounds(440,100,200,20);
		scrollPane2.setBounds(440,120,200,320);
			
		f.add(info);
		f.add(scrollPane2);
		f.add(player);		
	}
	//버튼 동작
	public void actionPerformed(ActionEvent e){
		// 메세지 보내기
		if(e.getSource()==send) {
			chatting_msg = tf.getText();
			try {
				test_send.send_chatting();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			 tf.selectAll(); 
			 tf.setText("");	
			 // 메인방 나가기
		}else if(e.getSource()==exit) {
			try {
				test_send.w_exit();
			} catch (IOException e1) {
				e1.printStackTrace();
			}	
		}
		// 초대하기
		else if(e.getSource()==invite) {
			try {
				test_send.send_invite();
			} catch (IOException e2) {
				e2.printStackTrace();
			}
		}
		//설명서 보기
		else if(e.getSource()==manual) {
			JFrame m = new JFrame();
			JTextArea m_info = new JTextArea(); //player list글자
			m.setVisible(true);m.setSize(200,200);
			m.add(m_info);m.setResizable(false);
			m.setLocationRelativeTo(null);
	
		}
		//player list가 업데이트 된다.
		else if(e.getSource()==L1) {
			try {
				test_send.send_update();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		// 초대를 수락
		else if(e.getSource()==ok) {
			try {
				test_send.recieve_invite();
				
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		//초대를 거절 
		else if(e.getSource()==no) {
			try {
				test_send.reject_invite();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	// get set
	public static String getMymsg() {
		return chatting_msg;
	}

	public static String getinvite() {
		return invite_msg;
	}
	public static void setMyNickname(String nickname) {
		mynickname = nickname;
	}
	public static String getMyNickname() {
		return mynickname;
	}
	public static void setRoomID(String roomid) {
		roomId = roomid;
	}
	public static String getRoomId() {
		return roomId;
	}
	static String getOther() {
		return otherUser;
	}
	//초대 메시지(내가 받는 상황)
	public static void setReceiveInvite(String nickname) {
		otherInviteNick = nickname;
	}
	public static String getOtherInvite() {
		return otherInviteNick;
	}
	
// chatting창에 내용을 입력한다.
 static void W_Chatting(String nickname,String msg) {
	chatting.append(nickname + ":" + msg+"\n");
}
 
//사용자 정보를 update하는 버튼 , 자신의 이름이 추가됨//고치기
public static void player(String nickname) throws UnknownHostException, IOException {
	l_model.addElement(nickname);//닉네임 집어넣기
}

static Vector<String> n_name = new Vector<String>();
static Vector<String> T_win = new Vector<String>();
static Vector<String> T_lose = new Vector<String>();
static Vector<String> T_tot = new Vector<String>();

static String nickname = null;
static String t_win = null;
static String t_lose = null;
static String t_tot = null;

public static void playerList(String nickName,String t_Win,String t_Lose,String t_Tot) {
	n_name.add(nickName);
	T_win.add(t_Win);
	T_lose.add(t_Lose);
	T_tot.add(t_Tot);
	nickname = nickName;
	t_win = t_Win;
	t_lose = t_Lose;
	t_tot = t_Tot;
	
}
public static String getNicName() {
	return nickname;
}
public static String getWin() {
	return t_win;
}
public static String getLose() {
	return t_lose;
}
public static String getTot() {
	return t_tot;
}
//player목록에서 삭제된다.
public static void exitUser(String nickName) {
	l_model.removeElement(nickName);//닉네임
}
public static void receiveMsg() {
	//invite.setBounds(400,300,200,300);
	ok.setBounds(40,40,80,60);
	no.setBounds(160,40,80,60);
	inviteMsg.setTitle("invite");
	inviteMsg.setLayout(null);
	inviteMsg.pack();
	inviteMsg.setVisible(true);	
	inviteMsg.setSize(300,180);
	inviteMsg.add(ok);
	inviteMsg.add(no);
	inviteMsg.setResizable(false);//창의 크기를 변경하지 못하게
	
}
}