

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Game extends JFrame implements ActionListener{
JFrame game = new JFrame();
JButton[] bts;	  
ImageIcon[] imgs;
JLabel lb,winlb;
JPanel panel1,panel2;
JButton re;
int set; 
int count,win,idx,didx,ran; // win이 0이면 아무 일 x, 1이면 이김, -1이면 짐, 2면 비김 
int[] dec; //전체 버튼 이미지에 따라 번호 설정 (번호는 배열에 따로 저장) 0->원, 1->엑스, 2->빈 거
int winnumber=0;

 public Game() { // 객체 생성을 담당함
  setSize(400, 400);
  setLocation(800, 200);
  setResizable(false);
  setTitle("Tic-Tac-Toe");
  setIconImage(new ImageIcon("tic-tac-toe/tic.png").getImage());
  
  dec=new int[9];
  
  panel1=new JPanel();
  panel1.setLayout(new GridLayout(3,3));
  panel2=new JPanel();
  
  bts=new JButton[9];
  for(int i=0;i<bts.length;i++) {
	  bts[i]=new JButton();
	  bts[i].addActionListener(this);
	  panel1.add(bts[i]);
}

  lb=new JLabel();
  re=new JButton("ReStart");
  re.addActionListener(this);
  
  winlb=new JLabel();

  imgs=new ImageIcon[3];
  imgs[0]=new ImageIcon("tic-tac-toe/circle.JPG");
  imgs[1]=new ImageIcon("tic-tac-toe/x.JPG");
  imgs[2]=new ImageIcon("tic-tac-toe/no.JPG");
  
  
  for(int i=0;i<imgs.length;i++) {
	//ImageIcon에서 Image를 추출
	Image originImg = imgs[i].getImage(); 
	//추출된 Image의 크기를 조절하여 새로운 Image객체 생성
	Image changedImg= originImg.getScaledInstance(133, 133, Image.SCALE_SMOOTH );
	//새로운 Image로 ImageIcon객체를 생성
	imgs[i] = new ImageIcon(changedImg);
	}
  
  add(panel1);
  add(panel2,BorderLayout.SOUTH);
 
 }
 
 public void GameStart() { // 게임 세팅을 담당함
	 win=count=0; // 카운트 및 win 초기화
	 panel2.removeAll(); // restart버튼 지우기 위해 위젯 다 지우기
	 
	  for(int i=0;i<bts.length;i++) 
		  bts[i].setIcon(imgs[2]);
	  
	  panel2.setBackground(Color.WHITE);
	  lb.setForeground(Color.black); 
	  lb.setText("Copyrightⓒ2019 Ahn Sung Hyun All rights reserved.");
	  panel2.add(lb);
	  
	  if(set==1) {
		  ran=(int)(Math.random()*9);
		  bts[ran].setIcon(imgs[1]);
		  count++;}
	  setVisible(true);
 }
 public void change_and_check(int c) {
	 
	// 전체 버튼 이미지에 따라 번호 설정 (번호는 배열에 따로 저장) 0->원, 1->엑스, 2->빈 거
	didx=0;			
	for(int i=0;i<bts.length;i++) {
		if(bts[i].getIcon()==imgs[0]) 
			dec[didx]=0;
		else if(bts[i].getIcon()==imgs[1])
			dec[didx]=1;
		else
			dec[didx]=2;
		didx++;
	}
	
	if(c==0) {
		// 이겼는지 조건 검사 
		for(int i=0; i+2<dec.length;i+=3) { 		// 가로 part
			if(dec[i]==0 &&dec[i+1]==0 &&dec[i+2]==0 ) // -> 이김
				win=1;
			if(win!=0)
				break;
			}
		
		if(win==0) {
		for(int i=0; i+6<dec.length;i++) { 		// 세로 part
			if(dec[i]==0 &&dec[i+3]==0 &&dec[i+6]==0) // -> 이김
				win=1;
			if(win!=0)
				break;
			}
		}
		
		if(win==0 && dec[0]==0 &&dec[4]==0 &&dec[8]==0) 		// 대각선 part -> 이김
			win=1;
		else if(win==0 && dec[2]==0 &&dec[4]==0 &&dec[6]==0) 
			win=1;
		else if(win==0 && count==9) // 비김
			win=2;
	}
	
	else if(c==1) {
		// 졌는지 조건 검사 
		for(int i=0; i+2<dec.length;i+=3) { 		// 가로 part
			if(dec[i]==1 &&dec[i+1]==1 &&dec[i+2]==1) // -> 짐
				win=-1;
			if(win!=0)
				break;
			}
		
		if(win==0) {
		for(int i=0; i+6<dec.length;i++) { 		// 세로 part
			if(dec[i]==1 &&dec[i+3]==1 &&dec[i+6]==1) // -> 짐
				win=-1;
			if(win!=0)
				break;
			}
		}
		if(win==0 && dec[0]==1 &&dec[4]==1 &&dec[8]==1) 		// 대각선 part -> 짐
			win=-1;
		else if(win==0 && dec[2]==1 &&dec[4]==1 &&dec[6]==1) 
			win=-1;
		else if(win==0 && count==9) // 비김
			win=2;
	}
 

	// 이기거나 지거나 비겼을 때 
	if(win==1 || win==-1 || win==2) {
		if(win==1) {
		lb.setText("게임 승리");
		lb.setForeground(Color.blue);
		winlb.setText("( 승리 횟수:"+(++winnumber)+" )");
		}
		
		else if(win==-1) {
			lb.setText("게임 패배");
			lb.setForeground(Color.red);
			winnumber=0;
			winlb.setText("( 승리 횟수:"+(winnumber)+" )");
		}
		
		else {
			lb.setText("게임 비김");
			lb.setForeground(Color.yellow);
			winnumber=0;
			winlb.setText("( 승리 횟수:"+(winnumber)+" )");
		}
		
		lb.setBackground(Color.WHITE);
		lb.setOpaque(true);
		
		for(int i=0;i<bts.length;i++) 
			bts[i].setEnabled(false);  // 버튼 비활성화
		
		panel2.add(winlb);
		panel2.add(re);
	}
 }
		

 
 @Override
 public void actionPerformed(ActionEvent e) { // 게임을 담당함
	 //다시 시작하기 버튼
	 if(e.getSource()==re) {
		 for(int i=0;i<bts.length;i++) 
				bts[i].setEnabled(true);  // 버튼 활성화
		this.GameStart();
	 }
	 
	 else { 
		// 클릭한 버튼 이미지 바꾸기
		idx=0;
		while(idx<9 && e.getSource()!=bts[idx]) 
			idx++;
		if(idx<9 && bts[idx].getIcon()==imgs[2]) {
			bts[idx].setIcon(imgs[0]); count ++;}
		
		if(count>=5) // 조건 검사 가능한 최소 카운트일 때 
			change_and_check(0); // 사람이 이겼는지 조건 검사
					
		// 컴퓨터가 버튼 선택을 한 것처럼 보이기
		if(set==1) {
			if(count!=9 && count%2==0 && win==0) {
				ran=(int)(Math.random()*9);
				while(bts[ran].getIcon()!=imgs[2])
					ran=(int)(Math.random()*9);
				bts[ran].setIcon(imgs[1]);
				count++;
			}
		}
		else {
			if(count!=9 && count%2!=0 && win==0) {
				ran=(int)(Math.random()*9);
				while(bts[ran].getIcon()!=imgs[2])
					ran=(int)(Math.random()*9);
				bts[ran].setIcon(imgs[1]);
				count++;
			}
		}
		if(win==0 && count>=5) // 조건 검사 가능한 최소 카운트일 때 
			change_and_check(1); // 컴퓨터가 이겼는지 조건 검사
 	}
  setVisible(true);
	
 	}
 
}