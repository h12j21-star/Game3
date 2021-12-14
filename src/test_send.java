

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Vector;

import javax.swing.JOptionPane;

public class test_send {
	 static byte[] buf = new byte[1500];
	 private static Socket sock;
	public static void send_login() throws UnknownHostException, IOException  {
		 String id = login_test2.getid();
	      String pw = login_test2.getpw();
		  byte[] buf = new byte[1500];
		  int count;
		  
		try {
			   OutputStream toServer = sock.getOutputStream();
			  InputStream user = sock.getInputStream(); 
			   String message = "100"+ " " + id + " " + pw + "\n";
			  
		         toServer.write(message.getBytes());
		        toServer.flush();
			   
		        //게임방에 들어왔을때,게임 입장(메인 대기방 입장): 700 1     
		  } finally {
			   try {
			    if (sock != null)
			     sock.close();
			    
			   } catch (IOException ex) {
			   }
			   
			  }//byte로 보낸다.	

				}
	public static void send_join() throws UnknownHostException, IOException {
		String id = test_join.getid();
		String pw = test_join.getpw();
		String name = test_join.getname();
		String nickname = test_waitingroom.getMyNickname()  ;
		String email = test_join.getemail();
		String Pw2 =  test_join.getpw2();
		try { 
			   OutputStream toServer = sock.getOutputStream();
			   InputStream user = sock.getInputStream();		
				toServer.write(("105"+ " " + name + " " + nickname + " " + id + " " + pw + " " +Pw2 + " " + email +"\n").getBytes()); 
				toServer.flush();			
		  } finally {
			   try {
			    if (sock != null)
			     sock.close();
			   } catch (IOException ex) {
			   }
		  }
	}	
	//게임이름
	static String nickname;
	static String win;
	static String lose;
	static String total;
	//메인방에
		public static void send_update() throws UnknownHostException, IOException {
		try {	
			  
			  OutputStream toServer = sock.getOutputStream();
			  InputStream user = sock.getInputStream();
			//유저 정보 받을때
			toServer.write(("300 1\n").getBytes()); 
			toServer.flush();	    
		}catch(Exception ee) {
			ee.printStackTrace();
		}	
	}	
		//game방
		public static void gameroomUserInfo(String roomId) throws UnknownHostException, IOException {
			try {	
			
				  OutputStream toServer = sock.getOutputStream();
				  InputStream user = sock.getInputStream();
				//유저 정보 받을때
				toServer.write(("300 "+ roomId +"\n").getBytes()); 
				toServer.flush();	    
			}catch(Exception ee) {
				ee.printStackTrace();
			}	
		}	
	//채팅 , gui에서 이력한 문자를 얻어온다.
		static String sendUser = null;
		static String receivemsg = null;
		static int count;
		public static void send_chatting() throws UnknownHostException, IOException {
			try {	
				 OutputStream toServer = sock.getOutputStream();
				  InputStream user = sock.getInputStream();
				  //확인하기
				
				 
				//200 (현재 방 ID) (보낼 데이터)
					 toServer.write(("200 "+ "1" +" " + test_waitingroom.getMymsg()+"\n").getBytes()); 
					 toServer.flush();
				 
			}catch(Exception ee) {
				ee.printStackTrace();
			}
	}	
	//초대할때 
	public static void send_invite() throws UnknownHostException, IOException {
			   OutputStream toServer = sock.getOutputStream();
			toServer.write(("500" + " " + test_waitingroom.getMyNickname() + " " + test_waitingroom.getOther()).getBytes());
			toServer.flush();
			 
	}
	//메인대기방에서만 사용가능
	public static void in_waitingroom() throws UnknownHostException, IOException {
			OutputStream toServer = sock.getOutputStream();
			toServer.write(("300 1\n").getBytes());
			toServer.flush();		 
		        // 게임방안에 들어옴
	}
	//게임 퇴장(메인 대기방 퇴장): 710
	//+ byte[] 타입이다.
	public static void w_exit() throws UnknownHostException, IOException {
			 OutputStream toServer = sock.getOutputStream();
			toServer.write(("808 "+ test_waitingroom.getRoomId()+"\n").getBytes());
			toServer.flush();	
		    // 게임방안에 들어옴	
	}
	//나를 초대한 사람이름??
	public static void in_mainRoom() throws UnknownHostException, IOException { 
		OutputStream toServer = sock.getOutputStream();
		toServer.write(("400 1" +" "+"\n").getBytes());
		toServer.flush();	
	}	
	//505 (flag) (나를 초대한 사람의 닉네임) (내 닉네임)
	public static void recieve_invite() throws IOException {
		OutputStream toServer = sock.getOutputStream();
		toServer.write(("505 0 " +test_waitingroom.getOtherInvite() + " " + test_waitingroom.getMyNickname()+"\n").getBytes());
		toServer.flush();	
	}
	public static void reject_invite() throws UnknownHostException, IOException {
		 
		 OutputStream toServer = sock.getOutputStream();
		toServer.write(("505 1 " +test_waitingroom.getOtherInvite()+ " "+ test_waitingroom.getMyNickname()+"\n").getBytes());
		toServer.flush();	
	}
	//현재 방의 이름을 원할때: 600 (현재 방 ID)
	public static void getRoomname() throws UnknownHostException, IOException { 
		 OutputStream toServer = sock.getOutputStream();
		toServer.write(("600 2\n").getBytes());
		toServer.flush();	
	}
	//605 (현재 방 ID) (새 이름)
	public static void change_r_name(String new_r_name) throws UnknownHostException, IOException { 
		 OutputStream toServer = sock.getOutputStream();
		toServer.write(("605 2 "+ new_r_name +"\n").getBytes());
		toServer.flush();	
	}
	//2인 대기방에서 메인 대기방으로 이동: 400 (현재 방 ID)
	public static void passWaitingRoom(String new_r_name) throws UnknownHostException, IOException { 
		 OutputStream toServer = sock.getOutputStream();
		 toServer.write(("400 2\n").getBytes());
		 toServer.flush();	
	}
	public static void game_chatting(String msg,String roomId) throws UnknownHostException, IOException { 
		 OutputStream toServer = sock.getOutputStream();
		 toServer.write(("200 "+roomId + " "+msg+"\n").getBytes());
		 toServer.flush();	
	}
	
	public static void main(String[] args) throws IOException {
		   sock = new Socket("localhost", 9999);
		   System.out.println(sock + ": 연결됨");
		   new login_test2();
		   new test_mainroom();
		   new test_waitingroom();
		   new Client(sock);
		}
		}



