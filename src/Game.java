

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
int count,win,idx,didx,ran; // win�� 0�̸� �ƹ� �� x, 1�̸� �̱�, -1�̸� ��, 2�� ��� 
int[] dec; //��ü ��ư �̹����� ���� ��ȣ ���� (��ȣ�� �迭�� ���� ����) 0->��, 1->����, 2->�� ��
int winnumber=0;

 public Game() { // ��ü ������ �����
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
	//ImageIcon���� Image�� ����
	Image originImg = imgs[i].getImage(); 
	//����� Image�� ũ�⸦ �����Ͽ� ���ο� Image��ü ����
	Image changedImg= originImg.getScaledInstance(133, 133, Image.SCALE_SMOOTH );
	//���ο� Image�� ImageIcon��ü�� ����
	imgs[i] = new ImageIcon(changedImg);
	}
  
  add(panel1);
  add(panel2,BorderLayout.SOUTH);
 
 }
 
 public void GameStart() { // ���� ������ �����
	 win=count=0; // ī��Ʈ �� win �ʱ�ȭ
	 panel2.removeAll(); // restart��ư ����� ���� ���� �� �����
	 
	  for(int i=0;i<bts.length;i++) 
		  bts[i].setIcon(imgs[2]);
	  
	  panel2.setBackground(Color.WHITE);
	  lb.setForeground(Color.black); 
	  lb.setText("Copyright��2019 Ahn Sung Hyun All rights reserved.");
	  panel2.add(lb);
	  
	  if(set==1) {
		  ran=(int)(Math.random()*9);
		  bts[ran].setIcon(imgs[1]);
		  count++;}
	  setVisible(true);
 }
 public void change_and_check(int c) {
	 
	// ��ü ��ư �̹����� ���� ��ȣ ���� (��ȣ�� �迭�� ���� ����) 0->��, 1->����, 2->�� ��
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
		// �̰���� ���� �˻� 
		for(int i=0; i+2<dec.length;i+=3) { 		// ���� part
			if(dec[i]==0 &&dec[i+1]==0 &&dec[i+2]==0 ) // -> �̱�
				win=1;
			if(win!=0)
				break;
			}
		
		if(win==0) {
		for(int i=0; i+6<dec.length;i++) { 		// ���� part
			if(dec[i]==0 &&dec[i+3]==0 &&dec[i+6]==0) // -> �̱�
				win=1;
			if(win!=0)
				break;
			}
		}
		
		if(win==0 && dec[0]==0 &&dec[4]==0 &&dec[8]==0) 		// �밢�� part -> �̱�
			win=1;
		else if(win==0 && dec[2]==0 &&dec[4]==0 &&dec[6]==0) 
			win=1;
		else if(win==0 && count==9) // ���
			win=2;
	}
	
	else if(c==1) {
		// ������ ���� �˻� 
		for(int i=0; i+2<dec.length;i+=3) { 		// ���� part
			if(dec[i]==1 &&dec[i+1]==1 &&dec[i+2]==1) // -> ��
				win=-1;
			if(win!=0)
				break;
			}
		
		if(win==0) {
		for(int i=0; i+6<dec.length;i++) { 		// ���� part
			if(dec[i]==1 &&dec[i+3]==1 &&dec[i+6]==1) // -> ��
				win=-1;
			if(win!=0)
				break;
			}
		}
		if(win==0 && dec[0]==1 &&dec[4]==1 &&dec[8]==1) 		// �밢�� part -> ��
			win=-1;
		else if(win==0 && dec[2]==1 &&dec[4]==1 &&dec[6]==1) 
			win=-1;
		else if(win==0 && count==9) // ���
			win=2;
	}
 

	// �̱�ų� ���ų� ����� �� 
	if(win==1 || win==-1 || win==2) {
		if(win==1) {
		lb.setText("���� �¸�");
		lb.setForeground(Color.blue);
		winlb.setText("( �¸� Ƚ��:"+(++winnumber)+" )");
		}
		
		else if(win==-1) {
			lb.setText("���� �й�");
			lb.setForeground(Color.red);
			winnumber=0;
			winlb.setText("( �¸� Ƚ��:"+(winnumber)+" )");
		}
		
		else {
			lb.setText("���� ���");
			lb.setForeground(Color.yellow);
			winnumber=0;
			winlb.setText("( �¸� Ƚ��:"+(winnumber)+" )");
		}
		
		lb.setBackground(Color.WHITE);
		lb.setOpaque(true);
		
		for(int i=0;i<bts.length;i++) 
			bts[i].setEnabled(false);  // ��ư ��Ȱ��ȭ
		
		panel2.add(winlb);
		panel2.add(re);
	}
 }
		

 
 @Override
 public void actionPerformed(ActionEvent e) { // ������ �����
	 //�ٽ� �����ϱ� ��ư
	 if(e.getSource()==re) {
		 for(int i=0;i<bts.length;i++) 
				bts[i].setEnabled(true);  // ��ư Ȱ��ȭ
		this.GameStart();
	 }
	 
	 else { 
		// Ŭ���� ��ư �̹��� �ٲٱ�
		idx=0;
		while(idx<9 && e.getSource()!=bts[idx]) 
			idx++;
		if(idx<9 && bts[idx].getIcon()==imgs[2]) {
			bts[idx].setIcon(imgs[0]); count ++;}
		
		if(count>=5) // ���� �˻� ������ �ּ� ī��Ʈ�� �� 
			change_and_check(0); // ����� �̰���� ���� �˻�
					
		// ��ǻ�Ͱ� ��ư ������ �� ��ó�� ���̱�
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
		if(win==0 && count>=5) // ���� �˻� ������ �ּ� ī��Ʈ�� �� 
			change_and_check(1); // ��ǻ�Ͱ� �̰���� ���� �˻�
 	}
  setVisible(true);
	
 	}
 
}