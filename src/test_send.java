

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Vector;

import javax.swing.JOptionPane;
public class test_send {
	static Socket sock = null;
	
	 static byte[] buf = new byte[1500];
	
	 public static void connect(String msg) throws IOException {
		 OutputStream toServer = sock.getOutputStream();
		 InputStream user = sock.getInputStream();	
		 Client chandler = new Client(sock);
		 chandler.start();
		 try{
			 while ((count = System.in.read(buf)) != -1) {
			        toServer.write((msg+"/n").getBytes());
			        toServer.flush();
			     }	
			 }catch(IOException ex) {
				 System.out.println("연결종료 (" +ex + ")");
	 }
	 }
	public static void send_login() throws UnknownHostException, IOException  {
		 String id = login_test2.getid();
	      String pw = login_test2.getpw();
		  byte[] buf = new byte[1500];
		  int count;	
		  test_send connect = new test_send();
			   String message = "100"+ " " + id + " " + pw;
		      connect.connect(message);
			   
		        //게임방에 들어왔을때,게임 입장(메인 대기방 입장): 700 1     
		  
				}
	public static void send_join() throws UnknownHostException, IOException {
		String id = test_join.getid();
		String pw = test_join.getpw();
		String name = test_join.getname();
		String nickname = test_waitingroom.getMyNickname()  ;
		String email = test_join.getemail();
		String Pw2 =  test_join.getpw2();
		String msg 	= "105"+ " " + name + " " + nickname + " " + id + " " + pw + " " +Pw2 + " " + email;
		connect(msg);			
	}	
	//게임이름
	static String nickname;
	static String win;
	static String lose;
	static String total;
	//메인방에
		public static void send_update() throws UnknownHostException, IOException {
			String msg 	= "300 1";
			connect(msg);	 
	}	
		//game방
		public static void gameroomUserInfo() throws UnknownHostException, IOException {
			//유저 정보 받을때
			String msg = "300 "+test_waitingroom.getRoomId();	 
			connect(msg);
		}	
	//채팅 , gui에서 이력한 문자를 얻어온다.
		static String sendUser = null;
		static String receivemsg = null;
		static int count;
		public static void send_chatting() throws UnknownHostException, IOException {
			String msg ="200 "+ "1" +" " + test_waitingroom.getMymsg();	 
			connect(msg);
	}	
	//초대할때 
	public static void send_invite() throws UnknownHostException, IOException {
		String msg ="500" + " " + test_waitingroom.getMyNickname() + " " + test_waitingroom.getOther(); 
		connect(msg);
			
			 
	}
	//메인대기방에서만 사용가능
	public static void in_waitingroom() throws UnknownHostException, IOException {
		
		String msg ="300 1"; 
		connect(msg);
	}
	//게임 퇴장(메인 대기방 퇴장): 710
	//+ byte[] 타입이다.
	public static void w_exit() throws UnknownHostException, IOException {
		String msg ="808"+ test_waitingroom.getRoomId(); 
		connect(msg);
	}
	//나를 초대한 사람이름??
	public static void in_mainRoom() throws UnknownHostException, IOException { 
		
		String msg ="400 1"; 
		connect(msg);

	}	
	//505 (flag) (나를 초대한 사람의 닉네임) (내 닉네임)
	public static void recieve_invite() throws IOException {
		String msg ="505 0" +test_waitingroom.getOtherInvite() + " " + test_waitingroom.getMyNickname();
		connect(msg);

	
	}
	public static void reject_invite() throws UnknownHostException, IOException {
		String msg ="505 1" +test_waitingroom.getOtherInvite() + " " + test_waitingroom.getMyNickname();
		connect(msg);

	
	}
	//현재 방의 이름을 원할때: 600 (현재 방 ID)
	public static void getRoomname() throws UnknownHostException, IOException { 
		String msg ="600 0";
		connect(msg);
	}
	//605 (현재 방 ID) (새 이름)
	public static void change_r_name() throws UnknownHostException, IOException { 
		String msg ="605 2";
		connect(msg);
		
	}
	//2인 대기방에서 메인 대기방으로 이동: 400 (현재 방 ID)
	public static void passWaitingRoom() throws UnknownHostException, IOException { 
		String msg ="400 2";
		connect(msg);
	
	}
	public static void game_chatting() throws UnknownHostException, IOException { 
		String msg ="200 "+ test_waitingroom.getRoomId() + test_waitingroom.getMymsg();
		connect(msg); 

	}
	public static void main(String[] args) throws UnknownHostException, IOException{
		//Socket sock = null;
	   sock = new Socket("localhost", 9999);
	   System.out.println(sock + ": 연결됨");
	   new login_test2();
	  new Client(sock);
	  
	}
}




