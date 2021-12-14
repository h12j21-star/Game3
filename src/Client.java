
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

public class Client {
	public Client(Socket sock) throws UnknownHostException, IOException {
		OutputStream toServer = sock.getOutputStream();
		InputStream inputStream = sock.getInputStream();	
		int i=2;
		byte[] buf = new byte[1024];			int count;
	while((count = inputStream.read(buf))!=-1) {
			 String temp = new String(buf);
		    String temp_new = temp.substring(0,temp.indexOf("\n"));
		     String[] temp_arr = temp_new.split(" ");
			     //로그인 성공
			     if(temp_arr[0].equals("100")) {
			    	 new test_waitingroom();
			     }
			     //로그인실패
			     else if(temp_arr[0].equals("105")) {
			    	 JOptionPane.showMessageDialog(null,"로그인 실패","Message",JOptionPane.ERROR_MESSAGE);
			     }
			     //회원가입성공
			     else if(temp_arr[0].equals("200")) {
			    	 JOptionPane.showMessageDialog(null,"회원가입 성공 로그인 해주세요","Message",JOptionPane.INFORMATION_MESSAGE);
		    		 new login_test2();
			     }
			     //정보가 이미존재
			     else if(temp_arr[0].equals("205")) {
			    	 JOptionPane.showMessageDialog(null,"정보가 이미 존재합니다.","Message",JOptionPane.ERROR_MESSAGE);
			     }
			     //id중복입력 잘못됨
			     else if(temp_arr[0].equals("210")) {
			    	 JOptionPane.showMessageDialog(null,"아이디 중복","Message",JOptionPane.ERROR_MESSAGE);
			     }
			     else if(temp_arr[0].equals("300")) {
			    	 //String ID;
			    	 JOptionPane.showMessageDialog(null,"초대성공","Message",JOptionPane.INFORMATION_MESSAGE);
			    	 test_mainroom.roomId(Integer.toString(i));
			    	 i++;
			    	 new test_mainroom();
			     }
			     else if(temp_arr[0].equals("305")) {
			    	 JOptionPane.showMessageDialog(null,"초대실패","Message",JOptionPane.ERROR_MESSAGE);
			     }
			     //초대메세지,400 (나를 초대한 사람의 닉네임)
			     else if(temp_arr[0].equals("400")) {
			    	 test_waitingroom.receiveMsg(); 
			    	 String[] user_info = temp_new.split(" ",2);
			    	 test_waitingroom.setReceiveInvite(user_info[1]);
			    	
			    	 //user_info[1] = 나를 초대한 사람의 닉네임
			     }
			     //자신의 선택이 받영
			     else if(temp_arr[0].equals("600")) {
			    	
			     }
			     // 자신의 선택이 반영되지않음
			     else if(temp_arr[0].equals("605")) {
			    	 
			     }
			     //상대방의 선택이 반영
			     else if(temp_arr[0].equals("610")) {
			    	 
			     }
			     //초기화 완료
			     else if(temp_arr[0].equals("650")) {
			    	 
			     }
			     //게임입장,메인대기방
			     else if(temp_arr[0].equals("700")) {
			    	 String[] user_info = temp_new.split(" ",3);
			    	 test_waitingroom.setRoomID(user_info[2]);
			    	//user_info[2] = 방번호
			     }
			     //게임입장
			     else if(temp_arr[0].equals("705")) {
			    	 //게임 방 띄우기
			    	 new test_mainroom();
			     }
			     //게임퇴장_메인
			     else if(temp_arr[0].equals("710")) {
			    	 test_waitingroom.exitUser(test_waitingroom.getMyNickname());
			    	 new login_test2();
			    	 
			     }
			     //게임 퇴장_2인
			     else if(temp_arr[0].equals("715")) {
			    	 //게임방 나가는 함수만들기
			    	 //test_mainroom();-> vector.remove();
			    	 
			    	 new test_waitingroom();
			     }
			     //게임승리
			     else if(temp_arr[0].equals("800")) {
			    	 
			     }
			     //게임패배
			     else if(temp_arr[0].equals("801")) {
			    	 
			     }
			     //게임무승부
			     else if(temp_arr[0].equals("802")) {
			    	 
			     }
			     //db에 반영
			     else if(temp_arr[0].equals("810")) {
			    	 
			     } 
			     //채팅으로 보냄
			     //채팅: 850 (채팅 보내는 유저의 닉네임) (내용)
			     else if(temp_arr[0].equals("850")) {
			    	 String[] user_info = temp_new.split(" ",3);
			    	 test_waitingroom.W_Chatting(user_info[1],user_info[2]); //메인방 채팅
			    	test_mainroom.send_chatting(user_info[1],user_info[2]); // 게임방 채팅
			     }
			     else if(temp_arr[0].equals("900")) {
			    	 String[] user_info = temp_new.split(" ",9);
			    	 test_waitingroom.playerList(user_info[1],user_info[5],user_info[6],user_info[7]);
			    	 //test_mainroom.in_gameRoom(user_info[1],user_info[5],user_info[6],user_info[7]);
			     }
			     //방장이 된 경우: 1000
			     else if(temp_arr[0].equals("1000")) {
			    	 
			     }
			     //방의 이름이 변경된 경우: 1005 (새로운 방 이름)
			     else if(temp_arr[0].equals("1005")) {
			    	 String[] user_info = temp_new.split(" ",2);
			    	 test_mainroom.roomname(user_info[1]);
			    	 
			     }
			     // 방의 이름을 반환하는 경우: 1010 (기존의 방 이름)
			     else if(temp_arr[0].equals("1010")) {
			    	 String[] user_info = temp_new.split(" ",2);
			    	 test_mainroom.roomname(user_info[1]);
			    	
			     }
			     else if(temp_arr[0].equals("2000")) {
			    	 System.out.println("잘못된 프로토콜사용");
			     }     
				
			}
		}
}

