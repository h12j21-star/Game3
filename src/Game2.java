import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class Game2 {
	public Game2() {
	 // JFrame생성 및 초기 세팅 
	 JFrame n=new JFrame("Setting");
	 n.setSize(270,240);
	 n.setLocation(800, 200);
	 n.setResizable(false);
	 n.setIconImage(new ImageIcon("tic-tac-toe/tic.png").getImage());
	 n.getContentPane().setBackground(Color.WHITE);
	 
	 // 나중에 관리하기 쉽게 미리 게임 클래스는 인스턴스화
	 Game f = new Game();
	 
	 // 텍스트 부분
	 JLabel label=new JLabel("                       Tic-Tac-Toe 게임 세팅 ");
	 
	 // 이미지 부분
	 JLabel imgl=new JLabel();
	 ImageIcon i=new ImageIcon("tic-tac-toe/tic.png");
	//ImageIcon에서 Image를 추출
	Image originImg = i.getImage(); 
	//추출된 Image의 크기를 조절하여 새로운 Image객체 생성
	Image changedImg= originImg.getScaledInstance(100, 100, Image.SCALE_SMOOTH );
	//새로운 Image로 ImageIcon객체를 생성
	i = new ImageIcon(changedImg);
	imgl.setIcon(i);

	// 라디오 버튼 부분
	 JRadioButton first=new JRadioButton("먼저 선택");
	 JRadioButton second=new JRadioButton("뒤늦게 선택");
	 first.addActionListener(e->{f.set=0;}); // set==0일 때, 사람이 먼저 시작 
	 second.addActionListener(e->{f.set=1;}); // set==1일 때, 컴퓨터가 먼저 시작 

	 ButtonGroup rbs=new ButtonGroup();
	 rbs.add(first);
	 rbs.add(second);
	 
	 // 패널에 라디오 버튼 추가
	 JPanel panel=new JPanel();
	 panel.setBackground(Color.WHITE);
	 panel.add(first);
	 panel.add(second);
	 panel.add(imgl);
	 
	 // 버튼 생성 및 람다식
	 JButton nb=new JButton("게임 시작");
	 nb.addActionListener(e->{
		 new Game();
	  f.GameStart();
	  n.setVisible(false);
	  f.setVisible(true);});
	 
	 // JFrame에 컴포넌트,컨테이너 추가 
	 n.add(label,BorderLayout.NORTH);
	 n.add(panel,BorderLayout.CENTER);
	 n.add(nb,BorderLayout.SOUTH);
	 n.setVisible(true);

	} 	
}

