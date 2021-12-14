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
	 // JFrame���� �� �ʱ� ���� 
	 JFrame n=new JFrame("Setting");
	 n.setSize(270,240);
	 n.setLocation(800, 200);
	 n.setResizable(false);
	 n.setIconImage(new ImageIcon("tic-tac-toe/tic.png").getImage());
	 n.getContentPane().setBackground(Color.WHITE);
	 
	 // ���߿� �����ϱ� ���� �̸� ���� Ŭ������ �ν��Ͻ�ȭ
	 Game f = new Game();
	 
	 // �ؽ�Ʈ �κ�
	 JLabel label=new JLabel("                       Tic-Tac-Toe ���� ���� ");
	 
	 // �̹��� �κ�
	 JLabel imgl=new JLabel();
	 ImageIcon i=new ImageIcon("tic-tac-toe/tic.png");
	//ImageIcon���� Image�� ����
	Image originImg = i.getImage(); 
	//����� Image�� ũ�⸦ �����Ͽ� ���ο� Image��ü ����
	Image changedImg= originImg.getScaledInstance(100, 100, Image.SCALE_SMOOTH );
	//���ο� Image�� ImageIcon��ü�� ����
	i = new ImageIcon(changedImg);
	imgl.setIcon(i);

	// ���� ��ư �κ�
	 JRadioButton first=new JRadioButton("���� ����");
	 JRadioButton second=new JRadioButton("�ڴʰ� ����");
	 first.addActionListener(e->{f.set=0;}); // set==0�� ��, ����� ���� ���� 
	 second.addActionListener(e->{f.set=1;}); // set==1�� ��, ��ǻ�Ͱ� ���� ���� 

	 ButtonGroup rbs=new ButtonGroup();
	 rbs.add(first);
	 rbs.add(second);
	 
	 // �гο� ���� ��ư �߰�
	 JPanel panel=new JPanel();
	 panel.setBackground(Color.WHITE);
	 panel.add(first);
	 panel.add(second);
	 panel.add(imgl);
	 
	 // ��ư ���� �� ���ٽ�
	 JButton nb=new JButton("���� ����");
	 nb.addActionListener(e->{
		 new Game();
	  f.GameStart();
	  n.setVisible(false);
	  f.setVisible(true);});
	 
	 // JFrame�� ������Ʈ,�����̳� �߰� 
	 n.add(label,BorderLayout.NORTH);
	 n.add(panel,BorderLayout.CENTER);
	 n.add(nb,BorderLayout.SOUTH);
	 n.setVisible(true);

	} 	
}

