
import java.awt.HeadlessException;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

class Client extends Thread {
	Socket sock = null;

	
	public Client(Socket sock) {
		this.sock = sock;
	}
public void run() {
	InputStream fromServer = null;
	try {
		fromServer = sock.getInputStream();
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	
	byte[] buf = new byte[1024];
	int count;
	try {
	while((count = fromServer.read(buf))!=-1) {
			 String temp = new String(buf);
		    String temp_new = temp.substring(0,temp.indexOf("\n"));
		     String[] temp_arr = temp_new.split(" ");
			     //�α��� ����
			     if(temp_arr[0].equals("100")) {
			    	 try {
						new test_waitingroom();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			     }
			     //�α��ν���
			     else if(temp_arr[0].equals("105")) {
			    	 JOptionPane.showMessageDialog(null,"�α��� ����","Message",JOptionPane.ERROR_MESSAGE);
			     }
			     //ȸ�����Լ���
			     else if(temp_arr[0].equals("200")) {
			    	 JOptionPane.showMessageDialog(null,"ȸ������ ���� �α��� ���ּ���","Message",JOptionPane.INFORMATION_MESSAGE);
		    		 try {
						new login_test2();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			     }
			     //������ �̹�����
			     else if(temp_arr[0].equals("205")) {
			    	 JOptionPane.showMessageDialog(null,"������ �̹� �����մϴ�.","Message",JOptionPane.ERROR_MESSAGE);
			     }
			     //id�ߺ��Է� �߸���
			     else if(temp_arr[0].equals("210")) {
			    	 JOptionPane.showMessageDialog(null,"���̵� �ߺ�","Message",JOptionPane.ERROR_MESSAGE);
			     }
			     else if(temp_arr[0].equals("300")) {
			    	 //String ID;
			    	 JOptionPane.showMessageDialog(null,"�ʴ뼺��","Message",JOptionPane.INFORMATION_MESSAGE);
			    	// test_mainroom.roomId(Integer.toString(i));
			    	 //i++;
			    	 try {
						new test_mainroom();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			     }
			     else if(temp_arr[0].equals("305")) {
			    	 JOptionPane.showMessageDialog(null,"�ʴ����","Message",JOptionPane.ERROR_MESSAGE);
			     }
			     //�ʴ�޼���,400 (���� �ʴ��� ����� �г���)
			     else if(temp_arr[0].equals("400")) {
			    	 test_waitingroom.receiveMsg(); 
			    	 String[] user_info = temp_new.split(" ",2);
			    	 test_waitingroom.setReceiveInvite(user_info[1]);
			    	
			    	 //user_info[1] = ���� �ʴ��� ����� �г���
			     }
			     //�ڽ��� ������ �޿�
			     else if(temp_arr[0].equals("600")) {
			    	
			     }
			     // �ڽ��� ������ �ݿ���������
			     else if(temp_arr[0].equals("605")) {
			    	 
			     }
			     //������ ������ �ݿ�
			     else if(temp_arr[0].equals("610")) {
			    	 
			     }
			     //�ʱ�ȭ �Ϸ�
			     else if(temp_arr[0].equals("650")) {
			    	 
			     }
			     //��������,���δ���
			     else if(temp_arr[0].equals("700")) {
			    	 String[] user_info = temp_new.split(" ",3);
			    	 test_waitingroom.setRoomID(user_info[2]);
			    	//user_info[2] = ���ȣ
			     }
			     //��������
			     else if(temp_arr[0].equals("705")) {
			    	 //���� �� ����
			    	 try {
						new test_mainroom();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			     }
			     //��������_����
			     else if(temp_arr[0].equals("710")) {
			    	 test_waitingroom.exitUser(test_waitingroom.getMyNickname());
			    	 try {
						new login_test2();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			    	 
			     }
			     //���� ����_2��
			     else if(temp_arr[0].equals("715")) {
			    	 //���ӹ� ������ �Լ������
			    	 //test_mainroom();-> vector.remove();
			    	 
			    	 try {
						new test_waitingroom();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			     }
			     //���ӽ¸�
			     else if(temp_arr[0].equals("800")) {
			    	 
			     }
			     //�����й�
			     else if(temp_arr[0].equals("801")) {
			    	 
			     }
			     //���ӹ��º�
			     else if(temp_arr[0].equals("802")) {
			    	 
			     }
			     //db�� �ݿ�
			     else if(temp_arr[0].equals("810")) {
			    	 
			     } 
			     //ä������ ����
			     //ä��: 850 (ä�� ������ ������ �г���) (����)
			     else if(temp_arr[0].equals("850")) {
			    	 String[] user_info = temp_new.split(" ",3);
			    	 test_waitingroom.W_Chatting(user_info[1],user_info[2]); //���ι� ä��
			    	test_mainroom.send_chatting(user_info[1],user_info[2]); // ���ӹ� ä��
			     }
			     else if(temp_arr[0].equals("900")) {
			    	 String[] user_info = temp_new.split(" ",9);
			    	 test_waitingroom.playerList(user_info[1],user_info[5],user_info[6],user_info[7]);
			    	 //test_mainroom.in_gameRoom(user_info[1],user_info[5],user_info[6],user_info[7]);
			     }
			     //������ �� ���: 1000
			     else if(temp_arr[0].equals("1000")) {
			    	 
			     }
			     //���� �̸��� ����� ���: 1005 (���ο� �� �̸�)
			     else if(temp_arr[0].equals("1005")) {
			    	 String[] user_info = temp_new.split(" ",2);
			    	 test_mainroom.roomname(user_info[1]);
			    	 
			     }
			     // ���� �̸��� ��ȯ�ϴ� ���: 1010 (������ �� �̸�)
			     else if(temp_arr[0].equals("1010")) {
			    	 String[] user_info = temp_new.split(" ",2);
			    	 test_mainroom.roomname(user_info[1]);
			    	
			     }
			     else if(temp_arr[0].equals("2000")) {
			    	 System.out.println("�߸��� �������ݻ��");
			     }
	}
	} catch (HeadlessException | IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally {
			    	 try {
			    		 if(fromServer !=null) {
			    			 fromServer.close();
			    		 }
			    		 if(sock != null) {
			    			 sock.close();
			    		 }
			    	 }catch(IOException ex) {
			    			 
			    	 }
	}
	}
}
