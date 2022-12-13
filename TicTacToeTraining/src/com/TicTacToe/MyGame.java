package com.TicTacToe;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.*;

public class MyGame extends JFrame implements ActionListener
{
	JLabel heading,clockLabel;
	Font font=new Font("",Font.BOLD,40);
	JPanel mainPanel;
	JButton []btns=new JButton[9]; 
	
	// game instance variable....
	
	int gameChances[]= {2,2,2,2,2,2,2,2,2};
	int activePlayer=0;
	int wps[][]= {
			{0,1,2},
			{3,4,5},
			{6,7,8},
			{0,3,6},
			{1,4,7},
			{2,5,8},
			{0,4,8},
			{2,4,6}
	};
	
	int winner=2;
	
	boolean gameOver=false;
	
	
	MyGame(){
		System.out.println("Creating instance of Game");
		setTitle("My Tic Tac Toe Game");
		setSize(700,700);
		ImageIcon icon=new ImageIcon("src/img/tttt.png");
		setIconImage(icon.getImage());
		createGUI();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
	}
	
	private void createGUI() {
		
		this.getContentPane().setBackground(Color.decode("#2196f3"));
		this.setLayout(new BorderLayout());
		
	heading=new JLabel("Tic Tac Toe");
	heading.setIcon(new ImageIcon("src/img/tttt.png"));
	heading .setFont(font);
	heading.setHorizontalAlignment(JLabel.CENTER);
	heading.setForeground(Color.white);
	heading.setHorizontalTextPosition(SwingConstants.CENTER); 
	//heading.setVerticalTextPosition(SwingConstants.BOTTOM);
	
	this.add(heading,BorderLayout.NORTH);
	
	// creating object or clock
	
	clockLabel=new JLabel("Clock");
	clockLabel.setForeground(Color.white);
	clockLabel.setFont(font);
	clockLabel.setHorizontalAlignment(SwingConstants.CENTER);
	this.add(clockLabel,BorderLayout.SOUTH);
	
	Thread t=new  Thread() {
		
		public void run()
		{
			try {
				while(true) {
					String datetime=new Date().toLocaleString();
					clockLabel.setText(datetime);
					Thread.sleep(1000);
				}
			}
			catch(Exception e){
				e.printStackTrace();
				
			}
		}
	};
	t.start();
	
	//////////Panel section
	mainPanel=new JPanel();
	mainPanel.setLayout(new GridLayout(3, 3));
	for(int i=1;i<=9;i++) 
	{
		JButton btn=new JButton();
		//btn.setIcon(new ImageIcon("src/img/05.jpg"));
		btn.setBackground(Color.decode("#90caf9"));
		btn.setFont(font);
		mainPanel.add(btn);
		btns[i-1]=btn;
		btn.addActionListener(this);
		btn.setName(String.valueOf(i-1));
		
	}
	this.add(mainPanel,BorderLayout.CENTER);
	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton currentButton= (JButton)e.getSource();
		//System.out.println("Clicked");
		
		String nameStr=currentButton.getName();
		
		System.out.println(nameStr);
		
		int name=Integer.parseInt(nameStr.trim());
		
		if(gameOver) {
			JOptionPane.showMessageDialog(this,"Game Already over...");
			return;
		}
		
		
		if(gameChances[name]==2) 
		{
			if(activePlayer==1)
			{
				currentButton.setIcon(new ImageIcon("src/img/x2.png"));
				gameChances[name]=activePlayer;
				activePlayer=0;
			}
			else
			{
				currentButton.setIcon(new ImageIcon("src/img/05.jpg"));
				gameChances[name]=activePlayer;

				activePlayer=1;
			}
		
		//find the winner..........
			for(int[]temp:wps) {
				
			   if((gameChances[temp[0]]==gameChances[temp[1]])&&(gameChances[temp[1]]==gameChances[temp[2]])&& gameChances[temp[2]]!=2)	{
				   
				   winner=gameChances[temp[0]];
				   gameOver=true;
				   JOptionPane.showMessageDialog(null, "Player "+ winner +" has won the game winner winner chicken dinner ...");
				   int i= JOptionPane.showConfirmDialog(this,"do you want to play more ??");
				   if(i==0) {
					   this.setVisible(true);
					   new MyGame();
				   }else if(i==1){
					   System.exit(34234);
				   }else {
					   
				   }
				   System.out.println(i);
				   break;
			   }
			}
			//..........Draw Logic
			int c=0;
			for(int x:gameChances) {
				if(x==2) {
					c++;
					break;
				}
			}
			if(c==0&&gameOver==false) {
				JOptionPane.showMessageDialog(null,"Its draw");
			int i= JOptionPane.showConfirmDialog(this,"Play More??");
			if(i==0) {
				this.setVisible(false);
				new MyGame();
			}else if(i==1) {
				System.exit(1212);
			}else {
				
			}
			
			gameOver=true;
			}
			
		}else {
			JOptionPane.showMessageDialog(this,"Position already occupide...");
		}
	}
	
}
	
