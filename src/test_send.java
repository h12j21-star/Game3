

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
			   
		        //���ӹ濡 ��������,���� ����(���� ���� ����): 700 1     
		  } finally {
			   try {
			    if (sock != null)
			     sock.close();
			    
			   } catch (IOException ex) {
			   }
			   
			  }//byte�� ������.	

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
	//�����̸�
	static String nickname;
	static String win;
	static String lose;
	static String total;
	//���ι濡
		public static void send_update() throws UnknownHostException, IOException {
		try {	
			  
			  OutputStream toServer = sock.getOutputStream();
			  InputStream user = sock.getInputStream();
			//���� ���� ������
			toServer.write(("300 1\n").getBytes()); 
			toServer.flush();	    
		}catch(Exception ee) {
			ee.printStackTrace();
		}	
	}	
		//game��
		public static void gameroomUserInfo(String roomId) throws UnknownHostException, IOException {
			try {	
			
				  OutputStream toServer = sock.getOutputStream();
				  InputStream user = sock.getInputStream();
				//���� ���� ������
				toServer.write(("300 "+ roomId +"\n").getBytes()); 
				toServer.flush();	    
			}catch(Exception ee) {
				ee.printStackTrace();
			}	
		}	
	//ä�� , gui���� �̷��� ���ڸ� ���´�.
		static String sendUser = null;
		static String receivemsg = null;
		static int count;
		public static void send_chatting() throws UnknownHostException, IOException {
			try {	
				 OutputStream toServer = sock.getOutputStream();
				  InputStream user = sock.getInputStream();
				  //Ȯ���ϱ�
				
				 
				//200 (���� �� ID) (���� ������)
					 toServer.write(("200 "+ "1" +" " + test_waitingroom.getMymsg()+"\n").getBytes()); 
					 toServer.flush();
				 
			}catch(Exception ee) {
				ee.printStackTrace();
			}
	}	
	//�ʴ��Ҷ� 
	public static void send_invite() throws UnknownHostException, IOException {
			   OutputStream toServer = sock.getOutputStream();
			toServer.write(("500" + " " + test_waitingroom.getMyNickname() + " " + test_waitingroom.getOther()).getBytes());
			toServer.flush();
			 
	}
	//���δ��濡���� ��밡��
	public static void in_waitingroom() throws UnknownHostException, IOException {
			OutputStream toServer = sock.getOutputStream();
			toServer.write(("300 1\n").getBytes());
			toServer.flush();		 
		        // ���ӹ�ȿ� ����
	}
	//���� ����(���� ���� ����): 710
	//+ byte[] Ÿ���̴�.
	public static void w_exit() throws UnknownHostException, IOException {
			 OutputStream toServer = sock.getOutputStream();
			toServer.write(("808 "+ test_waitingroom.getRoomId()+"\n").getBytes());
			toServer.flush();	
		    // ���ӹ�ȿ� ����	
	}
	//���� �ʴ��� ����̸�??
	public static void in_mainRoom() throws UnknownHostException, IOException { 
		OutputStream toServer = sock.getOutputStream();
		toServer.write(("400 1" +" "+"\n").getBytes());
		toServer.flush();	
	}	
	//505 (flag) (���� �ʴ��� ����� �г���) (�� �г���)
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
	//���� ���� �̸��� ���Ҷ�: 600 (���� �� ID)
	public static void getRoomname() throws UnknownHostException, IOException { 
		 OutputStream toServer = sock.getOutputStream();
		toServer.write(("600 2\n").getBytes());
		toServer.flush();	
	}
	//605 (���� �� ID) (�� �̸�)
	public static void change_r_name(String new_r_name) throws UnknownHostException, IOException { 
		 OutputStream toServer = sock.getOutputStream();
		toServer.write(("605 2 "+ new_r_name +"\n").getBytes());
		toServer.flush();	
	}
	//2�� ���濡�� ���� �������� �̵�: 400 (���� �� ID)
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
		   System.out.println(sock + ": �����");
		   new login_test2();
		   new Client(sock);
		}
		}



