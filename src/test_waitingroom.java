

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

//���� ���� - ä��â, player list,��ư
public class test_waitingroom extends JFrame implements ActionListener{
	//ä�� ������ array list�� �Է�  
	static JFrame f = new JFrame();
	static JTextField tf = new JTextField(20);
	static JTextArea player = new JTextArea("                    player list",7,20); //player list����
	static JTextArea chatting = new JTextArea(7,20); // ä���� �������� �κ�
	JScrollPane scrollPane = new JScrollPane(chatting); // ä���� ��ũ��
	
	static ImageIcon icon1 = new ImageIcon("image/game_rule.png"); //����� ����
	
	Button L1 = new Button("User update"); //���� ����
	Button manual = new Button("Game manual"); // ��ư ������ �̵�
	static Button exit = new Button("Exit Program"); // ������ ��ư ������ �α���â���� �̵�
	static Button send = new Button("send"); // ä���� ������ ��ư
	//static JList<String> info = new JList<String>(); // player�� ������ �˼��ִ� ��ư
	static JList<String> info; 
	static JScrollPane scrollPane2 = new JScrollPane(info); //player��ũ��
	static String message;
	static Button invite = new Button("�ʴ��ϱ�"); // ��ư ������ �̵�
	//static Button exit2` = new Button("���"); // ������ ��ư ������ �α���â���� �̵�
	static Font f1 = new Font("Aharoni ����",Font.BOLD,13);
	Socket socket = null;
	
	static Vector<String> Main_user = new Vector<String>();
	static DefaultListModel<String> l_model;
	
	static String chatting_msg = null;
	static String invite_msg = null;
	static String otherUser = null;
	static String mynickname = null;
	static String roomId = null;
	static String otherInviteNick = null;
	
	static JFrame inviteMsg  = new JFrame(); //�ʴ� �޾����� �޼��� â
	static JButton ok = new JButton("����"); // �ʴ� ����
	static JButton no = new JButton("����"); //�ʴ� ����

	//���� gui
	public test_waitingroom() throws UnknownHostException, IOException {	
		f.setTitle("waiting Room"); //â �̸�
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//f.getContentPane().setBackground(Color.GRAY); //��� ����
		
		//play ��ư ��ġ�� ��Ʈ
		manual.setBounds(90,50,200,35);
		manual.setFont(f1);
		
		//������ ��ư ��ġ�� ��Ʈ
		exit.setBounds(300,50,130,35);
		exit.setFont(f1);
		
		send.setBounds(390,420,50,20);
		
		//��ư ����
		manual.setBackground(new Color(234,234,234));
		exit.setBackground(new Color(234,234,234));
		send.setBackground(new Color(234,234,234));
		
		ok.setBackground(new Color(234,234,234));
		no.setBackground(new Color(234,234,234));
		
		
		L1.setBounds(440,50,130,35);
		L1.setBackground(new Color(234,234,234));
		tf.setBounds(90,420,300,20); 
		
		//��ư ������ ������ ���
		invite.addActionListener(this);	
		send.addActionListener(this);
		L1.addActionListener(this);	
		//tf.addActionListener(this);
		manual.addActionListener(this);
		exit.addActionListener(this);	
		
		//this.chatting();
		//test
		f.pack();
	    f.setVisible ( true ); // frame�� ���̰���
		f.setSize(750,550); //â ũ�� ����
		f.setLayout(null); //��ġ�� setbounds�� ����
		f.add(manual);
		f.add(exit);
		chatting.setEditable ( false ); //ä��â ���� �Ұ���
		//receiveMsg();
		//ä���Է�â�� ä��â ��ġ ����
		
		scrollPane.setBounds(90,100,350,320);
		//player list_test
		l_model = new DefaultListModel<String>();
		info = new JList<String>(l_model);
		
		//�޼��� ���� ��ư
		//test_waitingroom.player(); // ����� ���
		//list���� player�� �����ش�.	
		info.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getComponent()==info){
					if(arg0.getClickCount()==2){
						//info�� �� ��������
						int select = 0;
						//player�� ������ �۵� 
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
						P.setResizable(false);//â�� ũ�⸦ �������� ���ϰ�
						P.setLocationRelativeTo(null);//â�� ��� ������
						
							}		
						}
				}
		});	
		send.setFont(f1);		
	    f.add(send);
		f.add(scrollPane); //ä��â�� ��ũ�� �߰�
		scrollPane.setVisible(true);
		f.add(tf);f.add(L1);L1.setFont(f1);
		f.setResizable(false);//â�� ũ�⸦ �������� ���ϰ�
		f.setLocationRelativeTo(null);//â�� ��� ������
		
		//test
		info.setBounds(440,120,200,320);
		player.setEditable(false); 	//�����Ұ�
		
		//player�� �����ִ� â ��ġ����
		player.setBounds(440,100,200,20);
		scrollPane2.setBounds(440,120,200,320);
			
		f.add(info);
		f.add(scrollPane2);
		f.add(player);		
	}
	//��ư ����
	public void actionPerformed(ActionEvent e){
		// �޼��� ������
		if(e.getSource()==send) {
			chatting_msg = tf.getText();
			try {
				test_send.send_chatting();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			 tf.selectAll(); 
			 tf.setText("");	
			 // ���ι� ������
		}else if(e.getSource()==exit) {
			try {
				test_send.w_exit();
			} catch (IOException e1) {
				e1.printStackTrace();
			}	
		}
		// �ʴ��ϱ�
		else if(e.getSource()==invite) {
			try {
				test_send.send_invite();
			} catch (IOException e2) {
				e2.printStackTrace();
			}
		}
		//���� ����
		else if(e.getSource()==manual) {
			JFrame m = new JFrame();
			JTextArea m_info = new JTextArea(); //player list����
			m.setVisible(true);m.setSize(200,200);
			m.add(m_info);m.setResizable(false);
			m.setLocationRelativeTo(null);
	
		}
		//player list�� ������Ʈ �ȴ�.
		else if(e.getSource()==L1) {
			try {
				test_send.send_update();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		// �ʴ븦 ����
		else if(e.getSource()==ok) {
			try {
				test_send.recieve_invite();
				
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		//�ʴ븦 ���� 
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
	//�ʴ� �޽���(���� �޴� ��Ȳ)
	public static void setReceiveInvite(String nickname) {
		otherInviteNick = nickname;
	}
	public static String getOtherInvite() {
		return otherInviteNick;
	}
	
// chattingâ�� ������ �Է��Ѵ�.
 static void W_Chatting(String nickname,String msg) {
	chatting.append(nickname + ":" + msg+"\n");
}
 
//����� ������ update�ϴ� ��ư , �ڽ��� �̸��� �߰���//��ġ��
public static void player(String nickname) throws UnknownHostException, IOException {
	l_model.addElement(nickname);//�г��� ����ֱ�
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
//player��Ͽ��� �����ȴ�.
public static void exitUser(String nickName) {
	l_model.removeElement(nickName);//�г���
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
	inviteMsg.setResizable(false);//â�� ũ�⸦ �������� ���ϰ�
	
}
}